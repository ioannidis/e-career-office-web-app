<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Roles - Super Admin</title>
    <meta charset="UTF-8">
</head>
<body>
<h2>Manage Roles</h2>
<p><a href="<c:url value="/super_admin"/>"><< Back</a></p>
<hr>
<p>
    <strong>
        <a href="#">
            + Create Role
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
    <c:forEach items="${roles}" var="role">
        <tr>
            <td><c:out value="${role.id}" default="-"/></td>
            <td><c:out value="${role.title}" default="-"/></td>
            <td>
                <a href="#">Edit</a> / <a href="#">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
