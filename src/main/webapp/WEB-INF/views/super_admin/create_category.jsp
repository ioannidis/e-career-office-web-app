<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | Create Category</title>
    <meta charset="UTF-8">
</head>
<body>
<h2>Create Category</h2>
<p><a href="<c:url value="/manage_categories"/>"><< Back</a></p>
<hr>
<form action="<c:url value="/create_category"/>" method="POST">
    <p>
        <strong><label for="title">Title</label></strong>
        <br>
        <input type="text" name="title" id="title" required>
    </p>
    <p>
        <strong><label for="slug">Slug</label></strong>
        <br>
        <input type="text" name="slug" id="slug" required>
    </p>
    <input type="submit" value="Create Category">
</form>
</body>
</html>
