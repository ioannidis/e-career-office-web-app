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
                <a href="#">View</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<p>Displaying <strong><c:out value="${roleCount}"/> roles</strong> in total.</p>
</body>
</html>
