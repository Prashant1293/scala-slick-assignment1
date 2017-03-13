package com.example

import slick.driver.PostgresDriver

trait PostGresDBProvider extends DbProvider{

  val driver = PostgresDriver

  import driver.api._

  val db = Database.forConfig("myPostgresDB")

}
