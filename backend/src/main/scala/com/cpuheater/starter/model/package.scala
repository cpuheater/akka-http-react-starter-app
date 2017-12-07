package com.cpuheater.starter

package object model {

  trait DbModel

  case class User(id: Long, email: String, password: String) extends DbModel

  case class NewUser(email: String, password: String) extends DbModel

  case class Wallet(id: Long, balance: BigDecimal) extends DbModel


  sealed trait Rest

  case class UserRest(id: Long, email: String) extends Rest

  case class NewUserRest(email: String,
                         password: String) extends Rest

  case class RequestRest(amount: BigDecimal) extends Rest

  case class SendMoney(amount: BigDecimal) extends Rest

  case class WalletRest(balance: BigDecimal) extends Rest

}
