<%@ page
        contentType="text/html;charset=UTF-8"
        language="java"
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
  <head>
    <title>Departments</title>
  </head>
  <body>
  <table>
      <tr>
          <th>department_id</th>
          <th>department_name</th>
      </tr>
      <c:forEach var = "department" items='${allDepartments}'>
          <tr>
              <td><c:out value="${department.id}"/></td>
              <td><c:out value="${department.name}"/></td>
          </tr>
      </c:forEach>
  </table>
  </body>
</html>
