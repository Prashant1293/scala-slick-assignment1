package com.example

import scala.concurrent.ExecutionContext.Implicits.global

object Slick_MultiDB_Part extends App {

  EmployeeRepo.create
  DependentRepo.create
  ProjectRepo.create

  EmployeeRepo.insert(Employee(1, "A", 2.3))
  EmployeeRepo.insert(Employee(2, "B", 1.3))
  EmployeeRepo.insert(Employee(3, "C", 4.3))
  EmployeeRepo.insert(Employee(4, "D", 0.3))
  EmployeeRepo.insert(Employee(5, "E", 6.3))
  EmployeeRepo.insert(Employee(6, "F", 4.3))

  ProjectRepo.insert(Project(1, "ABC"))
  ProjectRepo.insert(Project(2, "DEF"))
  ProjectRepo.insert(Project(2, "GHI"))
  ProjectRepo.insert(Project(3, "ABC"))
  ProjectRepo.insert(Project(4, "GHI"))

  DependentRepo.insert(Dependent(1, "ONE", "BROTHER"))
  DependentRepo.insert(Dependent(1, "TWO", "DAD", Some(17)))
  DependentRepo.insert(Dependent(2, "THREE", "Sister", Some(19)))
  DependentRepo.insert(Dependent(2, "FOUR", "MOTHER", Some(5)))
  DependentRepo.insert(Dependent(3, "FIVE", "DAD", Some(9)))
  DependentRepo.insert(Dependent(4, "SIX", "MOTHER", Some(15)))

  val allEmployees = EmployeeRepo.getAll()
  val allProjects = ProjectRepo.getAll()
  val allDependents = DependentRepo.getAll()
  println("All Employees:")
  allEmployees.map(x=>println(x))
  Thread.sleep(1000)

  println("All Projects:")
  allProjects map println
  Thread.sleep(1000)

  println("All Dependents:")
  allDependents map println

  Thread.sleep(1000)


}





















































//    val insertRes = EmployeeRepo.insert(Employee(2, "sp", 10000.00))
//
//
//    val res = (insertRes.map(res => s"$res row inserted")).recover {
//      case ex: Throwable => ex.getMessage
//    }
//    res.map(println(_))
//    Thread.sleep(10000)
//
//
//  ProjectRepo.create
//
//  val insertPrj = ProjectRepo.insert(Project(2,"ps"))
//
//
//  val resPrj= (insertPrj.map(res => s"$res row inserted")).recover {
//    case ex: Throwable => ex.getMessage
//  }
//  res.map(println(_))
//  Thread.sleep(10000)
//
//}
