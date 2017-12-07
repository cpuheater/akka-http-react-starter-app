package com.cpuheater.starter.route

import akka.http.scaladsl.marshalling.ToResponseMarshallable

import scala.concurrent.ExecutionContext
import akka.http.scaladsl.server.Directives
import akka.actor.ActorSystem
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import com.cpuheater.starter.model.{RequestRest, SendMoney}
import com.cpuheater.starter.service.WalletService
import com.typesafe.scalalogging.LazyLogging
import com.cpuheater.starter.json.StarterProtocol._
import com.cpuheater.starter.service.WalletService
import scalikejdbc.AutoSession

trait WalletRoute extends  Directives with LazyLogging with RouteSupport {

  protected implicit def ec: ExecutionContext
  protected implicit def session: AutoSession

  private val walletService = WalletService

  val walletRoute = {
      post {
        path("wallets" / LongNumber / "sendmoney") { (id) =>
          entity(as[SendMoney]) { deposit =>
            complete {
              ToResponseMarshallable(toRestResponse(walletService.requestMoney(id, deposit)))
            }
          }
        }
      }~
      get {
        path("wallets" / LongNumber) { (id) =>
          complete {
            ToResponseMarshallable(toRestResponse(walletService.get(id)))
          }
        }
      } ~
      post {
        path("wallets" / LongNumber / "requestmoney") { (id) =>
          entity(as[RequestRest]) { withdrawal =>
            complete {
              ToResponseMarshallable(toRestResponse(walletService.requestMoney(id, withdrawal)))
            }
          }
        }
      }
    }
}



