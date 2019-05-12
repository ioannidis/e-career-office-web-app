<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | Delete Category</title>
    <meta charset="UTF-8">
</head>
<body>
<h2>Delete: ${category.title}</h2>
<p><a href="<c:url value="/manage_categories"/>"><< Back</a></p>
<hr>
<p>Are you sure you want to delete <strong>${category.title} (${category.id})</strong>?</p>
<form action="<c:url value="/delete_category?id=${category.id}"/>" method="POST">
    <input type="submit" value="Yes, Delete">
</form>
</body>
</html>
