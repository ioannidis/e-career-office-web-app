<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Users - Super Admin</title>
    <meta charset="UTF-8">
</head>
<body>
<h2>Manage Users</h2>
<p><a href="<c:url value="/super_admin"/>"><< Back</a></p>
<hr>
<p>
    <strong>
        <a href="#">
            + Create User
        </a>
    </strong>
</p>
<table>
    <thead>
    <tr>
        <td>Username</td>
        <td>First Name</td>
        <td>Last Name</td>
        <td>Phone Number</td>
        <td>Email</td>
        <td>Role</td>
        <td>Company</td>
        <td>Department</td>
        <td>Actions</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="otherUser">
        <tr>
            <td><c:out value="${otherUser.username}" default="-" /></td>
            <td><c:out value="${otherUser.name}" default="-" /></td>
            <td><c:out value="${otherUser.surname}" default="-" /></td>
            <td><c:out value="${otherUser.phoneNumber}" default="-" /></td>
            <td><c:out value="${otherUser.email}" default="-" /></td>
            <td><c:out value="${otherUser.role.title}" default="-" /></td>
            <td><c:out value="${otherUser.company.title}" default="-" /></td>
            <td><c:out value="${otherUser.department.title}" default="-" /></td>
            <td>
                <a href="#">Edit</a> / <a href="#">Delete</a>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>
</body>
</html>
