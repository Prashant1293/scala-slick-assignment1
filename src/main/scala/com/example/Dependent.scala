package com.example
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global



case class Dependent(emp_id:Int, name: String, relation:String, age: Option[Int] = None)


trait DependentTable extends EmployeeTable{
  this:DbProvider =>
  import driver.api._
  class DependentTable(tag: Tag) extends Table[Dependent](tag, "dependent") {
    val name = column[String]("name")
    val emp_id = column[Int]("emp_id")
    val relation = column[String]("relation")
    val age = column[Option[Int]]("age")
    def empFK = foreignKey("emp_dependent_fk", emp_id, queryObj)(_.id)
    def * = (emp_id, name, relation, age) <>(Dependent.tupled, Dependent.unapply)

  }

  val queryObj2 = TableQuery[DependentTable]

}


trait DependentComponent extends DependentTable{
  this:DbProvider =>
  import driver.api._

  def create = db.run(queryObj2.schema.create)

  def insert(dependent: Dependent) = db.run(queryObj2 += dependent)

  def deleteByEmployee(empId: Int) = {
    val query = queryObj2.filter(x => x.emp_id === empId)
    val action = query.delete
    db.run(action)
  }

  def delete(dependent: Dependent) = {
    val query = queryObj2.filter(x => x.emp_id === dependent.emp_id && x.name ===dependent.name &&
                x.relation === dependent.relation)
    val action = query.delete
    db.run(action)
  }

  def updateAge(dependent: Dependent, age:Int) = {
    val query = queryObj2.filter(x =>  x.emp_id === dependent.emp_id && x.name ===dependent.name &&
                x.relation === dependent.relation).map(_.age).update(Some(age))
    db.run(query)
  }

  def updateName(dependent: Dependent, name: String) = {
    val query = queryObj2.filter(x =>  x.emp_id === dependent.emp_id && x.name ===dependent.name &&
                 x.relation === dependent.relation).map(_.name).update(name)
    db.run(query)
  }

  def updateRelation(dependent: Dependent, relation: String) = {
    val query = queryObj2.filter(x =>  x.emp_id === dependent.emp_id && x.name ===dependent.name &&
      x.relation === dependent.relation).map(_.relation).update(relation)
    db.run(query)
  }

  def insertOrUpdate(dependent:Dependent) ={
    val query = queryObj2.insertOrUpdate(dependent)
    db.run(query)
  }

  def getAllByEmployeeId(empId:Int)={
    val query = queryObj2.filter(x => x.emp_id === empId).to[List].result
    db.run(query)
  }

  def getAll()= {
    val query = queryObj2.to[List].result
    db.run(query)
  }

}


object DependentRepo extends DependentComponent{

}


