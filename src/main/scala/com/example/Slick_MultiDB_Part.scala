package com.example

import scala.concurrent.ExecutionContext.Implicits.global


object Slick_MultiDB_Part extends App {

  EmployeeComponent.create
  DependentComponent.create
  ProjectComponent.create

  EmployeeComponent.insert(Employee(1, "A", 2.3))
  EmployeeComponent.insert(Employee(2, "B", 1.3))
  EmployeeComponent.insert(Employee(3, "C", 4.3))
  EmployeeComponent.insert(Employee(4, "D", 0.3))
  EmployeeComponent.insert(Employee(5, "E", 6.3))
  EmployeeComponent.insert(Employee(6, "F", 4.3))

  ProjectComponent.insert(Project(1, "ABC"))
  ProjectComponent.insert(Project(2, "DEF"))
  ProjectComponent.insert(Project(2, "GHI"))
  ProjectComponent.insert(Project(3, "ABC"))
  ProjectComponent.insert(Project(4, "GHI"))

  DependentComponent.insert(Dependent(1, "ONE", "BROTHER"))
  DependentComponent.insert(Dependent(1, "TWO", "DAD", Some(17)))
  DependentComponent.insert(Dependent(2, "THREE", "Sister", Some(19)))
  DependentComponent.insert(Dependent(2, "FOUR", "MOTHER", Some(5)))
  DependentComponent.insert(Dependent(3, "FIVE", "DAD", Some(9)))
  DependentComponent.insert(Dependent(4, "SIX", "MOTHER", Some(15)))

  val allEmployees = EmployeeComponent.getAll()
  val allProjects = ProjectComponent.getAll()
  val allDependents = DependentComponent.getAll()
  println("All Employees:")
  allEmployees map println
  Thread.sleep(3000)

  println("All Projects:")
  allProjects map println
  Thread.sleep(3000)

  println("All Dependents:")
  allDependents map println

  Thread.sleep(3000)


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
