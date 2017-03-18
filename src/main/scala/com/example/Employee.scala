package com.example
import slick.ast.ColumnOption.PrimaryKey
import slick.jdbc.PostgresProfile.api._

case class Employee (id:Int, name:String,experience :Double)

trait EmployeeTable extends H2DBComponent{
  this:DbProvider =>
  import driver.api._


  class EmployeeTable (tag:Tag) extends Table[Employee](tag,"employee"){
    val id= column[Int]("id",O.PrimaryKey)
    val name= column[String]("name")
    val experience= column[Double]("experience")
    def * =(id,name,experience)<>(Employee.tupled,Employee.unapply)
  }
  val queryObj= TableQuery[EmployeeTable]
}


trait EmployeeComponent extends EmployeeTable{
    this:DbProvider =>
  import driver.api._


  //val db =Database.forConfig("myPostgresDB")
  def create =db.run(queryObj.schema.create)

  def insert(emp:Employee)=db.run{
    (queryObj += emp)
  }

  def delete (id:Int)={
   val query= queryObj.filter(x => x.id === id)
    val action=query.delete
    db.run(action)
  }
  def updateName (id :Int , name:String)={
    val query= queryObj.filter(_.id === id).map(_.name).update(name)
    db.run(query)
  }

  def updateExperience(id:Int, experience:Double) = {
    val query = queryObj.filter(x => x.id === id)
      .map(_.experience).update(experience)
    db.run(query)
  }

  def insertOrUpdate(emp:Employee) ={
    val query = queryObj.insertOrUpdate(emp)
    db.run(query)
  }

  def getFreshers() = {
    val query = queryObj.filter(x => x.experience < 1.0).to[List].result
    db.run(query)
  }

  def getAll()= {
    val query = queryObj.to[List].result
    db.run(query)
  }


}

object EmployeeComponent extends EmployeeComponent{
  }




