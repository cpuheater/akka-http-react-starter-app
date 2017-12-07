package com.cpuheater.starter.util

import scala.concurrent.{Await, Future}
import scala.concurrent.duration._


trait FutureSupport {

   implicit class FutureAwait[T](f: Future[T]){
     def await() : T = Await.result(f, 5 seconds)
   }

}
