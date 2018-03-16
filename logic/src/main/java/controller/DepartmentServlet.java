package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import dao.DepartmentDao;
import dao.IDaoBase;
import model.Department;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DepartmentServlet extends HttpServlet{

    private IDaoBase<Department> departmentDao;
    private ObjectMapper objectMapper;

    public DepartmentServlet() {
        super();
        departmentDao = new DepartmentDao();
        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT); // for pretty output
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        List<Department> allDepartments = departmentDao.getAll();
        String allDepartmentsAsJsonString = objectMapper.writeValueAsString(allDepartments);
        resp.getWriter().write(allDepartmentsAsJsonString);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Department departmentToUpdate = new Department();
        departmentToUpdate.setName(req.getParameter("department_name"));
        departmentToUpdate.setId(Integer.parseInt(req.getParameter("department_id")));
        departmentDao.update(departmentToUpdate);
        //return {}
        //resp.getWriter().write(objectMapper.writeValueAsString(JsonNodeFactory.instance.objectNode()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Department departmentToCreate = new Department();
        departmentToCreate.setName(req.getParameter("department_name"));
        departmentDao.create(departmentToCreate);

        resp.getWriter().write(objectMapper.writeValueAsString(JsonNodeFactory.instance.objectNode())); // how to get {} json
        //or

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        departmentDao.delete(Integer.parseInt(req.getParameter("department_id")));
        //return {}
        //resp.getWriter().write(objectMapper.writeValueAsString(JsonNodeFactory.instance.objectNode()));
    }



}
