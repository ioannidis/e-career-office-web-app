<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | Create Keyword</title>
    <meta charset="UTF-8">
</head>
<body>
<h2>Create Keyword</h2>
<p><a href="<c:url value="/manage_keywords"/>"><< Back</a></p>
<hr>
<form action="<c:url value="/create_keyword"/>" method="POST">
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
    <input type="submit" value="Create Keyword">
</form>
</body>
</html>
