package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.EmployeeDao;
import dao.IDaoBase;
import model.Employee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeServlet extends HttpServlet {

    private IDaoBase<Employee> employeeDao;
    private ObjectMapper objectMapper;

    public EmployeeServlet() {
        super();
        employeeDao = new EmployeeDao();
        objectMapper = new ObjectMapper();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        List<Employee> allEmployees = employeeDao.getAll();
        String allEmployeesAsJsonString = objectMapper.writeValueAsString(allEmployees);
        resp.getWriter().write(allEmployeesAsJsonString);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
