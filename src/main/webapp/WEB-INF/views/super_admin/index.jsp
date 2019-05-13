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
    <li><a href="<c:url value="/manage_users"/>">Manage Users (${userCount} users)</a></li>
    <li><a href="<c:url value="/manage_roles"/>">Manage Roles (${roleCount} roles)</a></li>
    <li><a href="<c:url value="/manage_companies"/>">Manage Companies (${companyCount} companies)</a></li>
    <li><a href="<c:url value="/manage_departments"/>">Manage Departments (${departmentCount} departments)</a></li>
    <li><a href="<c:url value="/manage_categories"/>">Manage Categories (${categoryCount} categories)</a></li>
    <li><a href="<c:url value="/manage_keywords"/>">Manage Keywords (${keywordCount} keywords)</a></li>
    <li><a href="<c:url value="/manage_classifieds"/>">Manage Classifieds (${classifiedCount} classifieds)</a></li>
    <li><a href="<c:url value="/logout"/>">Log Out</a></li>
</ul>
</body>
</html>
