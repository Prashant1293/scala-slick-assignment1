package com.example
import slick.ast.ColumnOption.PrimaryKey
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.Future

case class Project  (emp_id:Int,name:String)

trait ProjectTable extends EmployeeTable{
  this:DbProvider =>
  import driver.api._

  class ProjectTable (tag:Tag) extends Table[Project](tag,"project_table_name"){
    val emp_id= column[Int]("emp_id",O.PrimaryKey)
    val name= column[String]("name")
    def empFK = foreignKey("Emp_Prj_FK",emp_id,queryObj)(_.id)
    def * =(emp_id , name) <>(Project.tupled,Project.unapply)
  }
  val queryObj1= TableQuery[ProjectTable]
}

trait ProjectComponent extends ProjectTable{
  this:DbProvider =>
  import driver.api._


 // val db =Database.forConfig("myPostgresDB")
  def create =db.run(queryObj1.schema.create)

  def insert(prj:Project)=db.run{
    (queryObj1 += prj)
  }

  def delete(project: Project) = {
    val query = queryObj1.filter(x => x.emp_id === project.emp_id && x.name === project.name)
    val action = query.delete
    db.run(action)
  }

  def updateName(empId: Int, oldName:String, newName:String) : Future[Int] = {
    val query = queryObj1.filter(x => x.emp_id === empId && x.name === oldName)
      .map(_.name).update(newName)
    db.run(query)
  }

  def insertOrUpdate(project: Project) ={
    val query = queryObj1.insertOrUpdate(project)
    db.run(query)
  }

  def getEmployeeProjects(id: Int) = {
    val query = queryObj1.filter(x => x.emp_id === id).to[List].result
    db.run(query)
  }

  def getAll()= {
    val query = queryObj1.to[List].result
    db.run(query)
  }

}

object ProjectRepo extends ProjectComponent{

}
