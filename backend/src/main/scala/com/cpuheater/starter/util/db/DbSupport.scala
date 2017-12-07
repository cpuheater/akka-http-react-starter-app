package com.cpuheater.starter.util.db

import com.cpuheater.starter.StarterConfig
import com.cpuheater.starter.StarterConfig
import com.cpuheater.starter.StarterConfig.db
import com.typesafe.scalalogging.LazyLogging
import scalikejdbc.{AutoSession, WrappedResultSet}
import scalikejdbc._

trait DbSupport extends LazyLogging {

  Class.forName("org.h2.Driver")
  ConnectionPool.singleton(db.url, StarterConfig.db.user, StarterConfig.db.password)

  implicit val session = AutoSession

}
