package dao;

import model.Department;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao implements IDaoBase<Department> {

    private static final String tableName = "departments";

    @Override
    public boolean create(Department entity) {
        String query = "INSERT INTO " + tableName + " (department_name) VALUES (?)";
        boolean isInserted = false;

        try {
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, entity.getName());
            isInserted = preparedStatement.executeUpdate() > 0;

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isInserted;

    }

    @Override
    public Department read(int id) {
        Department department = null;
        String query = "SELECT * FROM "+tableName+" where department_id = ?";
        try {
            PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id );
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next() ) {
                department = new Department();
                department.setId(resultSet.getInt("department_id"));
                department.setName(resultSet.getString("department_name"));
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;
    }

    @Override
    public boolean update(Department entity) {
        String query = "UPDATE " + tableName + " SET department_name = ? WHERE department_id = ?";
        boolean isDeleted = false;

        if (read(entity.getId()) != null) {
            try {
                PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(query);
                preparedStatement.setString(1, entity.getName());
                preparedStatement.setInt(2, entity.getId());
                isDeleted = preparedStatement.executeUpdate() > 0;
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return isDeleted;
        }
        return isDeleted;

    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM " + tableName + " WHERE department_id = ?";
        boolean isDeleted = false; //todo Referential Integrity? CASCADE?
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
    public List<Department> getAll()  {
        String query = "SELECT * FROM " + tableName;
        List<Department> resultList = new ArrayList<>();
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                Department department = new Department();
                department.setId(result.getInt("department_id"));
                department.setName(result.getString("department_name"));
                resultList.add(department);
            }
            result.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
