package com.cpuheater.starter.route

import akka.http.scaladsl.marshalling.ToResponseMarshallable
import akka.stream.ActorMaterializer

import scala.concurrent.ExecutionContext
import akka.http.scaladsl.server.{Directives, Route, RouteResult}
import akka.actor.{ActorRef, ActorSystem}
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.HttpRequest
import com.cpuheater.starter.model.{NewUserRest, UserRest}
import com.cpuheater.starter.service.UserService
import com.typesafe.scalalogging.LazyLogging
import com.cpuheater.starter.json.StarterProtocol._
import com.cpuheater.starter.service.UserService
import scalikejdbc.AutoSession

trait UserRoute extends  Directives with LazyLogging with RouteSupport {

  protected implicit def ec: ExecutionContext
  protected implicit def session: AutoSession

  private val userService = UserService

  val userRoute = {
    extractRequest { request: HttpRequest =>
      get {
        path("users" / LongNumber) { (id) =>
            complete {
              ToResponseMarshallable(toRestResponse(userService.get(id)))
            }
        }
      } ~
      post {
        path("login") {
          entity(as[NewUserRest]) { (user) =>
            complete {
              ToResponseMarshallable(toRestResponse(userService.login(user)))
            }
          }
        }
      } ~
      post {
        path("signup") {
          entity(as[NewUserRest]) { (user) =>
            complete {
              ToResponseMarshallable(toRestResponse(userService.create(user)))
            }
          }
        }
      }
    }
  }
}


