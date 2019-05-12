<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Keywords - Super Admin</title>
    <meta charset="UTF-8">
</head>
<body>
<h2>Manage Keywords</h2>
<p><a href="<c:url value="/super_admin"/>"><< Back</a></p>
<hr>
<p>
    <strong>
        <a href="<c:url value="/create_keyword"/>">
            + Create Keyword
        </a>
    </strong>
</p>
<table>
    <thead>
    <tr>
        <td>ID</td>
        <td>Title</td>
        <td>Slug</td>
        <td>Actions</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${keywords}" var="keyword">
        <tr>
            <td><c:out value="${keyword.id}" default="-" /></td>
            <td><c:out value="${keyword.title}" default="-" /></td>
            <td><c:out value="${keyword.slug}" default="-" /></td>
            <td>
                <a href="<c:url value="/view_keyword?id=${keyword.id}"/>">View</a>
                /
                <a href="<c:url value="/edit_keyword?id=${keyword.id}"/>">Edit</a>
                /
                <a href="<c:url value="/delete_keyword?id=${keyword.id}"/>">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<p>Displaying <strong>${keywordCount} keywords</strong> in total.</p>
</body>
</html>
