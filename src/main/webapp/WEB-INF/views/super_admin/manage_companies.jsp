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
        <a href="<c:url value="/create_company"/>">
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
                <a href="<c:url value="/view_company?id=${company.id}"/>">View</a>
                /
                <a href="<c:url value="/edit_company?id=${company.id}"/>">Edit</a>
                /
                <a href="<c:url value="/delete_company?id=${company.id}"/>">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<p>Displaying <strong>${companyCount} companies</strong> in total.</p>
</body>
</html>
