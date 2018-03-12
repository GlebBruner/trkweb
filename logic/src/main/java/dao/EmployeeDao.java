package dao;


import model.Department;
import model.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao implements IDaoBase<Employee> {

    private static final String tableName = "employees";

    private IDaoBase<Department> departmentDao = null;

    @Override
    public boolean create(Employee entity) {
        String query = "INSERT INTO " + tableName + " (employee_name, employee_surname, employee_dob, employee_salary, employee_email, department_id) " +
                " VALUES (?, ?, ?, ?, ?, ?)";
        boolean isInserted = false;

        departmentDao = new DepartmentDao();

        if (departmentDao.read(entity.getDepartment_id()) != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
                preparedStatement.setString(1, entity.getName());
                preparedStatement.setString(2, entity.getSurname());
                preparedStatement.setDate(3, java.sql.Date.valueOf(entity.getDob()));
                preparedStatement.setFloat(4, entity.getSalary());
                preparedStatement.setString(5, entity.getEmail());
                preparedStatement.setInt(6, entity.getDepartment_id());
                isInserted = preparedStatement.executeUpdate() > 0;

                preparedStatement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return isInserted;
        }

        return isInserted; // todo if department exists exception

    }

    @Override
    public Employee read(int id) {
        String query = "SELECT * FROM " + tableName + " WHERE employee_id = ?";
        Employee resultEmployee = null;

        try {
            PreparedStatement statement = DBConnection.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                resultEmployee = new Employee();
                resultEmployee.setId(resultSet.getInt("employee_id"));
                resultEmployee.setName(resultSet.getString("employee_name"));
                resultEmployee.setSurname(resultSet.getString("employee_surname"));
                resultEmployee.setDob((resultSet.getDate("employee_dob")).toLocalDate());
                resultEmployee.setSalary(resultSet.getFloat("employee_salary"));
                resultEmployee.setEmail(resultSet.getString("employee_email"));
                resultEmployee.setDepartment_id(resultSet.getInt("department_id"));
            }
            statement.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultEmployee;
    }

    @Override
    public boolean update(Employee entity) {
        String query = "UPDATE " + tableName + " SET employee_name = ?, employee_surname = ?, employee_dob = ?, employee_salary = ?, employee_email = ?, department_id = ? "+
                " WHERE employee_id = ?";
        boolean isUpdated = false;
        departmentDao = new DepartmentDao();
        if (departmentDao.read(entity.getDepartment_id()) != null && read(entity.getId()) != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
                preparedStatement.setString(1, entity.getName());
                preparedStatement.setString(2, entity.getSurname());
                preparedStatement.setDate(3, java.sql.Date.valueOf(entity.getDob()));
                preparedStatement.setFloat(4, entity.getSalary());
                preparedStatement.setString(5, entity.getEmail());
                preparedStatement.setInt(6, entity.getDepartment_id());
                preparedStatement.setInt(7, entity.getId());
                isUpdated = preparedStatement.executeUpdate() > 0;
                preparedStatement.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return isUpdated;
        }

        return isUpdated;
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM " +tableName+" WHERE employee_id = ?";
        boolean isDeleted = false; // todo code duplicate.
        try {
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            isDeleted = preparedStatement.executeUpdate() > 0;
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isDeleted;

    }

    @Override
    public List<Employee> getAll() {
        String query = "SELECT * FROM " + tableName;
        List<Employee> employees = new ArrayList<>();
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Employee employeeForList = new Employee();
                employeeForList.setId(resultSet.getInt("employee_id"));
                employeeForList.setName(resultSet.getString("employee_name"));
                employeeForList.setSurname(resultSet.getString("employee_surname"));
                employeeForList.setDob(resultSet.getDate("employee_dob").toLocalDate());
                employeeForList.setSalary(resultSet.getFloat("employee_salary"));
                employeeForList.setEmail(resultSet.getString("employee_email"));
                employeeForList.setSalary(resultSet.getInt("department_id"));
                employees.add(employeeForList);
            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
