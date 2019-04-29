<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home - Super Admin</title>
    <meta charset="UTF-8">
</head>
<body>
<h2><c:out value="${user.getFullName()}"/></h2>
<hr>
<p><strong>What would you like to do?</strong></p>
<ul>
    <li><a href="<c:url value="/manage_users"/>">Manage Users (<c:out value="${userCount}"/> users)</a></li>
    <li><a href="<c:url value="/manage_roles"/>">Manage Roles (<c:out value="${roleCount}"/> roles)</a></li>
    <li><a href="<c:url value="/manage_companies"/>">Manage Companies (<c:out value="${companyCount}"/> companies)</a></li>
    <li><a href="<c:url value="/manage_departments"/>">Manage Departments (<c:out value="${departmentCount}"/> departments)</a></li>
    <li><a href="#">Manage Classifieds</a></li>
    <li><a href="<c:url value="/logout"/>">Log Out</a></li>
</ul>
</body>
</html>
