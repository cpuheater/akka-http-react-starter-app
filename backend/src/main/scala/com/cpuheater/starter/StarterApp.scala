package com.cpuheater.starter

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.directives.DebuggingDirectives
import akka.util.Timeout
import akka.stream.{ActorMaterializer, ActorMaterializerSettings, Supervision}
import com.cpuheater.starter.persistence.UserDao
import com.cpuheater.starter.route.{UserRoute, WalletRoute}
import com.cpuheater.starter.util.FutureSupport
import com.cpuheater.starter.util.db.DbSupport
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging
import scala.concurrent.duration._

object StarterApp extends App with UserRoute
                              with WalletRoute with DbSupport with LazyLogging  with FutureSupport{

  val decider: Supervision.Decider = { e =>
    logger.error(s"Ups exception while processing a stream$e")
    Supervision.Stop
  }

  implicit val actorSystem = ActorSystem("StarterApp", ConfigFactory.load)
  val materializerSettings = ActorMaterializerSettings(actorSystem).withSupervisionStrategy(decider)
  implicit val materializer = ActorMaterializer(materializerSettings)(actorSystem)

  implicit val ec = actorSystem.dispatcher

  val routes = {
    logRequestResult("starter") {
      userRoute ~ walletRoute
    }
  }

  implicit val timeout = Timeout(30.seconds)

  val routeLogging = DebuggingDirectives.logRequestResult("RouteLogging", Logging.InfoLevel)(routes)

  Http().bindAndHandle(routeLogging, StarterConfig.http.interface, StarterConfig.http.port)
  logger.info("App Started")
  logger.info("List of available users:")
  val users = UserDao.getAll().await()
  users.foreach(user => logger.info(s"User email: ${user.email}, password: ${user.password} "))

}
