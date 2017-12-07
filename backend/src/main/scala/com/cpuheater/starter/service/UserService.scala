package com.cpuheater.starter.service

import com.cpuheater.starter.util.service.model.{AuthenticationFailure,Ok, ServiceResult}
import com.cpuheater.starter.model._
import com.cpuheater.starter.persistence.{WalletDao, UserDao}
import com.typesafe.scalalogging.LazyLogging
import scalikejdbc.AutoSession

import scalaz.Scalaz._
import scalaz._
import scala.concurrent.{ExecutionContext, Future}
import spray.json._

import scalaz.OptionT.optionT

object UserService extends LazyLogging  {

  def create(newUserRest: NewUserRest)(implicit ec: ExecutionContext, session: AutoSession): Future[ServiceResult[UserRest]] = {
    val newUser = NewUser(email = newUserRest.email,  password = newUserRest.password)
    UserDao.create(newUser).map{
      user =>
        Ok(UserRest(id = user.id, email = user.email)).right
    }

  }

  def login(user: NewUserRest)(implicit ec: ExecutionContext, session: AutoSession): Future[ServiceResult[UserRest]] = {
    val maybeUser = (for {
      user <-  optionT(UserDao.getByEmailAndPassword(user.email, user.password))
    } yield user).run

    maybeUser.map{
      case Some(user) =>
        Ok(UserRest(id = user.id,
          email = user.email)).right
      case None =>
        AuthenticationFailure.left
    }

  }

  def get(id: Long)(implicit ec: ExecutionContext, session: AutoSession): Future[ServiceResult[UserRest]] = {
    val maybeUser = (for {
      user <-  optionT(UserDao.get(id))
    } yield user).run

    maybeUser.map{
      case Some(user) =>
        Ok(UserRest(id = user.id, email = user.email)).right
      case None =>
        AuthenticationFailure.left
    }

  }

}
