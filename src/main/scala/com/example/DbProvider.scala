package com.example

import slick.driver.JdbcProfile

trait DbProvider {

  val driver:JdbcProfile
  //val driver: slick.jdbc.JdbcProfile

  import driver.api._

  val db:Database

}
