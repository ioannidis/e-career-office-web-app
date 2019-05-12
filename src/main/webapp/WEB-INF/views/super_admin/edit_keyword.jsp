<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | Edit Keyword</title>
    <meta charset="UTF-8">
</head>
<body>
<h2>Edit: ${keyword.title}</h2>
<p><a href="<c:url value="/manage_keywords"/>"><< Back</a></p>
<hr>
<form action="<c:url value="/edit_keyword?id=${keyword.id}"/>" method="POST">
    <p>
        <strong><label for="title">Title</label></strong>
        <br>
        <input type="text" name="title" id="title" value="${keyword.title}" required>
    </p>
    <p>
        <strong><label for="slug">Slug</label></strong>
        <br>
        <input type="text" name="slug" id="slug" value="${keyword.slug}" required>
    </p>
    <input type="submit" value="Save Changes">
</form>
</body>
</html>
