<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | Edit Category</title>
    <meta charset="UTF-8">
</head>
<body>
<h2>Edit: ${category.title}</h2>
<p><a href="<c:url value="/manage_categories"/>"><< Back</a></p>
<hr>
<form action="<c:url value="/edit_category?id=${category.id}"/>" method="POST">
    <p>
        <strong><label for="title">Title</label></strong>
        <br>
        <input type="text" name="title" id="title" value="${category.title}" required>
    </p>
    <p>
        <strong><label for="slug">Slug</label></strong>
        <br>
        <input type="text" name="slug" id="slug" value="${category.slug}" required>
    </p>
    <input type="submit" value="Save Changes">
</form>
</body>
</html>
