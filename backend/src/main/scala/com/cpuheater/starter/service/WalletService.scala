package com.cpuheater.starter.service

import com.cpuheater.starter.util.service.model._
import com.cpuheater.starter.model._
import com.cpuheater.starter.persistence.{WalletDao, UserDao}
import scalikejdbc.AutoSession
import scalaz.Scalaz._
import scalaz._
import scala.concurrent.{ExecutionContext, Future}
import spray.json._
import scalaz.OptionT._

object WalletService {

  def get(userId: Long)(implicit ec: ExecutionContext, session: AutoSession): Future[ServiceResult[WalletRest]] = {

    val maybeWalet = (for {
      user <-  optionT(UserDao.get(userId))
      wallet <- optionT(WalletDao.get(user.id))
    } yield wallet).run


    maybeWalet.map{
      case Some(wallet) =>
        Ok(WalletRest(balance = wallet.balance)).right
      case None =>
        NotFound.left
    }

  }

  def requestMoney(userId: Long, deposit: SendMoney)(implicit ec: ExecutionContext, session: AutoSession): Future[ServiceResult[WalletRest]] = {
    val maybeWallet = (for {
      user <-  optionT(UserDao.get(userId))
      wallet <- optionT(WalletDao.get(user.id))
    } yield wallet).run


    maybeWallet.flatMap{
      case Some(wallet) =>
        val updatedWallet = wallet.copy(balance = wallet.balance + deposit.amount)
        WalletDao.update(updatedWallet).map{
          wallet =>
            Ok(WalletRest(balance = wallet.balance)).right
        }
      case None =>
        Future.successful(NotFound.left)
    }
  }

  def requestMoney(userId: Long, withdrawal: RequestRest)(implicit ec: ExecutionContext, session: AutoSession): Future[ServiceResult[WalletRest]] = {
    val maybeWallet = (for {
      user <-  optionT(UserDao.get(userId))
      wallet <- optionT(WalletDao.get(user.id))
    } yield wallet).run


    maybeWallet.flatMap{
      case Some(wallet) =>
        val newBalance = wallet.balance - withdrawal.amount
        if(newBalance < 0 ){
             Future.successful(ServiceClientFailure.left)
        } else {
           val updatedWallet = wallet.copy(balance = newBalance)
           WalletDao.update(updatedWallet).map{
             wallet =>
               Ok(WalletRest(balance = wallet.balance)).right
           }
        }
      case None =>
        Future.successful(NotFound.left)
    }
  }


}
