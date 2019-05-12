<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Categories - Super Admin</title>
    <meta charset="UTF-8">
</head>
<body>
<h2>Manage Categories</h2>
<p><a href="<c:url value="/super_admin"/>"><< Back</a></p>
<hr>
<p>
    <strong>
        <a href="<c:url value="/create_category"/>">
            + Create Category
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
    <c:forEach items="${categories}" var="category">
        <tr>
            <td><c:out value="${category.id}" default="-" /></td>
            <td><c:out value="${category.title}" default="-" /></td>
            <td><c:out value="${category.slug}" default="-" /></td>
            <td>
                <a href="<c:url value="/view_category?id=${category.id}"/>">View</a>
                /
                <a href="<c:url value="/edit_category?id=${category.id}"/>">Edit</a>
                /
                <a href="<c:url value="/delete_category?id=${category.id}"/>">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<p>Displaying <strong>${categoryCount} categories</strong> in total.</p>
</body>
</html>
