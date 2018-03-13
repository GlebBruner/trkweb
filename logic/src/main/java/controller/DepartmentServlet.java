package controller;

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

    public DepartmentServlet() {
        super();
        departmentDao = new DepartmentDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Department> allDepartments = departmentDao.getAll();
        req.setAttribute("allDepartments", allDepartments);
        RequestDispatcher dispatcher = req.getRequestDispatcher("departments.jsp");
        dispatcher.forward(req,resp);
    }

}
