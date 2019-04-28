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
<table>
    <thead>
    <tr>
        <td>Username</td>
        <td>First Name</td>
        <td>Last Name</td>
        <td>Phone Number</td>
        <td>Email</td>
        <td>Role</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="otherUser">
        <tr>
            <td><c:out value="${otherUser.username}" /></td>
            <td><c:out value="${otherUser.name}" /></td>
            <td><c:out value="${otherUser.surname}" /></td>
            <td><c:out value="${otherUser.phoneNumber}" /></td>
            <td><c:out value="${otherUser.email}" /></td>
            <td><c:out value="${otherUser.roleId}" /></td>
        </tr>
    </c:forEach>

    </tbody>
</table>
</body>
</html>
