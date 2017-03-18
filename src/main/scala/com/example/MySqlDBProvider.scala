package com.example

import slick.jdbc.MySQLProfile


trait MySqlDBProvider extends DbProvider{
  val driver = MySQLProfile

  import driver.api._

  val db = Database.forConfig("myMySqlDB")

}
