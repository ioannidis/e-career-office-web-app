<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Companies - Super Admin</title>
    <meta charset="UTF-8">
</head>
<body>
<h2>Manage Companies</h2>
<p><a href="<c:url value="/super_admin"/>"><< Back</a></p>
<hr>
<p>
    <strong>
        <a href="#">
            + Create Company
        </a>
    </strong>
</p>
<table>
    <thead>
    <tr>
        <td>ID</td>
        <td>Title</td>
        <td>Address</td>
        <td>Phone Number</td>
        <td>Email</td>
        <td>Website</td>
        <td>Actions</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${companies}" var="company">
        <tr>
            <td><c:out value="${company.id}" default="-" /></td>
            <td><c:out value="${company.title}" default="-" /></td>
            <td><c:out value="${company.address}" default="-" /></td>
            <td><c:out value="${company.phoneNumber}" default="-" /></td>
            <td><c:out value="${company.email}" default="-" /></td>
            <td><c:out value="${company.website}" default="-" /></td>
            <td>
                <a href="#">Edit</a> / <a href="#">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<p>Displaying <strong><c:out value="${companyCount}"/> companies</strong> in total.</p>
</body>
</html>
