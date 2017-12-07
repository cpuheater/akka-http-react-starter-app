package com.cpuheater.starter.util.service

import scalaz.\/


package object model {

    sealed trait ServiceFailure

    sealed trait ServiceSuccess[T]

    case class Ok[T](data: T) extends ServiceSuccess[T]

    case object ServiceClientFailure extends ServiceFailure

    case object AuthenticationFailure extends ServiceFailure

    case object NotFound extends ServiceFailure

    type ServiceResult[T] =  ServiceFailure \/ ServiceSuccess[T]

}
