package com.example

import slick.jdbc.PostgresProfile
import slick.driver.PostgresDriver

trait PostGresDBProvider extends DbProvider{

  val driver = PostgresProfile

  import driver.api._

  val db = Database.forConfig("myPostgresDB")

}
