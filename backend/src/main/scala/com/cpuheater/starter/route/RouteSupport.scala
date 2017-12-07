package com.cpuheater.starter.route

import akka.http.scaladsl.model.{HttpHeader, StatusCodes}
import akka.http.scaladsl.server.{Directives}
import com.typesafe.scalalogging.LazyLogging
import spray.json.JsonFormat
import scalaz.{-\/, \/-}
import com.cpuheater.starter.util.service.model._
import scala.concurrent.{ExecutionContext, Future}


trait RouteSupport extends LazyLogging with Directives {

  protected def toRestResponse[T: JsonFormat](serviceResponse: Future[ServiceResult[T]])(implicit ec: ExecutionContext) =
    serviceResponse.map{
      _ match {
        case \/-(Ok(data)) => (StatusCodes.OK, List.empty[HttpHeader], Some(Left(data)))
        case -\/(NotFound) => (StatusCodes.NotFound, List.empty[HttpHeader], None)
        case -\/(AuthenticationFailure) => (StatusCodes.Unauthorized, List.empty[HttpHeader], Some(Right("")))
        case -\/(ServiceClientFailure) => (StatusCodes.UnprocessableEntity, List.empty[HttpHeader], Some(Right("")))
      }
    }

}

