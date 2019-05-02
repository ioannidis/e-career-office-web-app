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
    <li><a href="<c:url value="/upload_cv"/>">CRUD CV DETAILS</a></li>
    <li><a href="<c:url value="/get_cv"/>">Download CV</a></li>
    <li><a href="<c:url value="/logout"/>">Log Out</a></li>
</ul>
</body>
</html>
