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

        String action = req.getServletPath();
        System.out.println(action);

        switch (action) {
            case "/departments":
                allDepartments(req, resp);
                break;
            case "/departments/delete":
            deleteDepartment(req, resp);
            break;
        }


    }

    private void allDepartments (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Department> allDepartments = departmentDao.getAll();
        request.setAttribute("allDepartments", allDepartments);
        RequestDispatcher dispatcher = request.getRequestDispatcher("departments.jsp");
        dispatcher.forward(request,response);
    }

    private void deleteDepartment (HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        departmentDao.delete(id);
        response.sendRedirect("/departments");
    }

}
