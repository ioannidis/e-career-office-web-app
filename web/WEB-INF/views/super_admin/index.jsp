<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home - Super Admin</title>
    <meta charset="UTF-8">
</head>
<body>
<h2><c:out value="${user.getFullName()}" /> </h2>
<hr>
<p><strong>What would you like to do?</strong></p>
<ul>
    <li><a href="<c:url value="/manage_users"/>">Manage Users</a></li>
    <li><a href="#">Manage Departments</a></li>
    <li><a href="<c:url value="/logout"/>">Log Out</a></li>
</ul>
</body>
</html>
