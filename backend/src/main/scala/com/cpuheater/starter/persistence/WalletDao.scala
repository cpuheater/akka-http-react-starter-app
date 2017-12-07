package com.cpuheater.starter.persistence

import com.cpuheater.starter.model.{Wallet}
import scalikejdbc.{AutoSession, WrappedResultSet, _}
import scala.concurrent.{ExecutionContext, Future}

object WalletDao {

  def create(wallet: Wallet)(implicit ec: ExecutionContext, session: AutoSession) : Future[Wallet] = {
    val id = sql"""insert into WALLET(ID, BALANCE) values (${wallet.id}, ${wallet.balance})""".update.apply()
    Future.successful(Wallet(id = id, balance = wallet.balance))
  }


  def update(wallet: Wallet)(implicit ec: ExecutionContext, session: AutoSession) : Future[Wallet] = {
    val id = sql"""UPDATE WALLET SET BALANCE=${wallet.balance} WHERE ID=${wallet.id};""".update().apply()
    Future.successful(wallet)
  }


  def get(id: Long)(implicit ec: ExecutionContext, session: AutoSession) : Future[Option[Wallet]] = {
    val wallet = sql"select * from WALLET where id = ${id}".map(rs => WalletDB(rs)).first().apply()
    Future.successful(wallet)
  }

  private object WalletDB extends SQLSyntaxSupport[Wallet] {
    override val tableName = "WALLET"
    def apply(rs: WrappedResultSet): Wallet =
      Wallet(id = rs.long("id"), balance = rs.bigDecimal("balance"))
  }

}
