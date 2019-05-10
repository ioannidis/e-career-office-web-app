<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | Edit Department</title>
    <meta charset="UTF-8">
</head>
<body>
<h2>Edit: ${department.title}</h2>
<p><a href="<c:url value="/manage_departments"/>"><< Back</a></p>
<hr>
<form action="<c:url value="/edit_department?id=${department.id}"/>" method="POST">
    <p>
        <strong><label for="title">Title</label></strong>
        <br>
        <input type="text" name="title" id="title" value="${department.title}" required>
    </p>
    <input type="submit" value="Save Changes">
</form>
</body>
</html>
