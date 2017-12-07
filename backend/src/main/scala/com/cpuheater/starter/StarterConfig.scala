package com.cpuheater.starter

import com.typesafe.config.ConfigFactory

object StarterConfig {

  private val config = ConfigFactory.load()
  private val starterConfig = config.getConfig("starter")

  object http {
    private val httpConfig = starterConfig.getConfig("http")
    val port = httpConfig.getInt("port")
    val interface = httpConfig.getString("interface")
  }

  object db {
    private val dbConfig = starterConfig.getConfig("db")
    val user = dbConfig.getString("user")
    val password = dbConfig.getString("password")
    val url = dbConfig.getString("url")
  }

}
