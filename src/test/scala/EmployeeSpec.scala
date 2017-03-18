import com.example._
import org.scalatest.AsyncFunSuite

class EmployeeSpec extends AsyncFunSuite{

  object testing extends EmployeeComponent with H2DBComponent
  test("Add new Employee ") {
    testing.insert(Employee(17, "prashant", 32)).map( x =>assert(x == 1))
  }

  test("update Employee record ") {
    testing.updateName(16,"prashu").map(x=>assert(x == 1))
  }

  test("delete Employee by experience") {
    testing.delete(23).map(x=>assert(x == 0 ))
  }

  test("get all employees") {
    testing.getAll.map( x => assert( x.size == 3))
  }
}