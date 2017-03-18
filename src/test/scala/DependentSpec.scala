import com.example._
import org.scalatest.AsyncFunSuite
//import slick.driver.MySQLDriver
//import slick.jdbc.H2Profile


class DependentSpec extends AsyncFunSuite{

  object DependentTesting extends DependentComponent with H2DBComponent

  test("Add new Dependent ") {
    DependentTesting.insert(Dependent(16, "super", "friend",Some(23))).map( x =>assert(x == 1))
  }

  test("update Dependent Name ") {
    DependentTesting.updateName(11,"prashant").map(x=>assert(x == 1))
  }

  test("delete Employee ") {
    DependentTesting.delete(11).map(x=>assert(x == 1))
  }

  test("get all employees") {
    DependentTesting.getAll.map( x => assert( x.size == 2))
  }

  test("Cross Join Dependent and Employee") {
    DependentTesting.crossJoin.map(x => assert(x == List(("ps","ps"),("ps","jar"),("jar","ps"),("jar","jar"), ("nitin","ps"),("nitin","jar"))))
  }

  test("Inner Join Dependent and Employee") {
    DependentTesting.innerJoin.map(x => assert(x == List(("ps", "ps"),("jar","jar"))))
  }


  test("Full Join Dependent and Employee") {
    DependentTesting.leftOuterJoin.map(x => assert(x == List(("ps", Some(Some(23))),("jar",Some(Some(22))),("nitin",None))))
  }
}
