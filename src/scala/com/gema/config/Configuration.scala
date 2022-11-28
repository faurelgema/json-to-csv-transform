package com.gema.config

import com.typesafe.config.{Config, ConfigFactory}

object Configuration{
    val config:Config = ConfigFactory.load().getConfig("jsontocsv")
}
