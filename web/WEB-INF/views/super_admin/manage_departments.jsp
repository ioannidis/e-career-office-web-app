<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Departments - Super Admin</title>
    <meta charset="UTF-8">
</head>
<body>
<h2>Manage Departments</h2>
<p><a href="<c:url value="/super_admin"/>"><< Back</a></p>
<hr>
<p>
    <strong>
        <a href="<c:url value="/create_department"/>">
            + Create Department
        </a>
    </strong>
</p>
<table>
    <thead>
    <tr>
        <td>ID</td>
        <td>Title</td>
        <td>Actions</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${departments}" var="department">
        <tr>
            <td><c:out value="${department.id}" default="-"/></td>
            <td><c:out value="${department.title}" default="-"/></td>
            <td>
                <a href="<c:url value="/view_department?id=${department.id}"/>">View</a>
                /
                <a href="<c:url value="/edit_department?id=${department.id}"/>">Edit</a>
                /
                <a href="<c:url value="/delete_department?id=${department.id}"/>">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<p>Displaying <strong><c:out value="${departmentCount}"/> departments</strong> in total.</p>
</body>
</html>
