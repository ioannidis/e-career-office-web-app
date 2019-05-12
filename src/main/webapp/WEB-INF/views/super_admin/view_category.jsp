<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | View Category</title>
    <meta charset="UTF-8">
</head>
<body>
<h2>View: ${category.title}</h2>
<p>
    <a href="<c:url value="/edit_category?id=${category.id}"/>">Edit</a>
    /
    <a href="<c:url value="/delete_category?id=${category.id}"/>">Delete</a>
</p>
<p><a href="<c:url value="/manage_categories"/>"><< Back</a></p>
<hr>

<h4>ID</h4>
<p>${category.id}</p>

<h4>Title</h4>
<p>${category.title}</p>

<h4>Slug</h4>
<p>${category.slug}</p>

</body>
</html>
