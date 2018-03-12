import dao.EmployeeDao;
import dao.IDaoBase;
import model.Employee;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;


public class EmployeeTest {

    @Test
    public void readEmployeeTest() {
        System.out.println(System.getProperty("java.class.path"));
        IDaoBase<Employee> daoBase = new EmployeeDao();
        Employee employee = daoBase.read(1);
        assertNotNull(employee);
    }

    @Test
    public void createEmployeeTest() {
        IDaoBase<Employee> daoBase = new EmployeeDao();
        Employee employeeForInsertion = new Employee();
        employeeForInsertion.setName("Test");
        employeeForInsertion.setSurname("SurnameTest");
        employeeForInsertion.setDob(LocalDate.of(1996, 10, 17));
        employeeForInsertion.setSalary(55000.0f);
        employeeForInsertion.setEmail("testtest@gmail.com");
        employeeForInsertion.setDepartment_id(1);
        daoBase.create(employeeForInsertion);
        assertEquals(4, daoBase.read(4).getId());
    }

    @Test
    public void deleteEmployeeTest() {
        IDaoBase<Employee> daoBase = new EmployeeDao();
        assertTrue(daoBase.delete(3));

    }

    @Test
    public void updateEmployeeTest() { // fails with h2 and createEmployeeTest
        IDaoBase<Employee> daoBase = new EmployeeDao();
        Employee employeeForUpdate = new Employee();
        employeeForUpdate.setId(1);
        employeeForUpdate.setName("Test");
        employeeForUpdate.setSurname("SurnameTest");
        employeeForUpdate.setDob(LocalDate.of(1996, 10, 17));
        employeeForUpdate.setSalary(55000.0f);
        employeeForUpdate.setEmail("testtest@gmail.com");
        employeeForUpdate.setDepartment_id(1);
        daoBase.update(employeeForUpdate);
        assertEquals("Test", daoBase.read(1).getName());
    }

    @Test
    public void getAllEmployeesTest() {
        IDaoBase<Employee> daoBase = new EmployeeDao();
        assertEquals(3, daoBase.getAll().size());
    }


}
