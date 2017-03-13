package com.example

import slick.driver.MySQLDriver

trait MySqlDBProvider extends DbProvider{
  val driver = MySQLDriver

  import driver.api._

  val db = Database.forConfig("myMySqlDB")

}
