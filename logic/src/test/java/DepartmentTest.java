import dao.DepartmentDao;
import dao.IDaoBase;
import model.Department;
import org.junit.Test;

import static org.junit.Assert.*;


public class DepartmentTest {

    @Test
    public void readDepartmentTest() {
        IDaoBase<Department> dao = new DepartmentDao();
        Department department = dao.read(1);
        assertNotNull(department);
    }

    @Test
    public void createDepartmentTest() {
        IDaoBase<Department> daoBase = new DepartmentDao();
        Department insertThis = new Department();
        insertThis.setName("Test");
        daoBase.create(insertThis);
        assertEquals(4, daoBase.read(4).getId());
    }

    @Test
    public void deleteDepartmentTest() {
        IDaoBase<Department> daoBase = new DepartmentDao();
        assertTrue(daoBase.delete(3));
    }

    @Test
    public void updateDepartmentTest() {
        IDaoBase<Department> daoBase = new DepartmentDao();
        Department update = new Department();
        update.setId(1);
        update.setName("Modified Sales");
        //update.setName("Managers"); // todo Unique index or primary key violation
//      assertTrue(daoBase.update(update));
        daoBase.update(update);
        assertEquals("Modified Sales", daoBase.read(1).getName());
    }

    @Test
    public void getAllDepartmentsTest() {
        IDaoBase<Department> daoBase = new DepartmentDao();
        assertEquals(3, daoBase.getAll().size());
    }

}
