package com.cpuheater.starter.json

import com.cpuheater.starter.model._
import spray.json.{DeserializationException, JsString, JsValue, JsonFormat, _}


object StarterProtocol extends  DefaultJsonProtocol{

  implicit val sendMoneyJson = jsonFormat1(SendMoney)

  implicit val walletJson = jsonFormat1(WalletRest)

  implicit val userJson = jsonFormat2(UserRest)

  implicit val newUserJson = jsonFormat2(NewUserRest)

  implicit val requestMoneyJson = jsonFormat1(RequestRest)

}
