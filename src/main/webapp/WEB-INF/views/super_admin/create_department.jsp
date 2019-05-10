<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | Create Department</title>
    <meta charset="UTF-8">
</head>
<body>
<h2>Create Department</h2>
<p><a href="<c:url value="/manage_departments"/>"><< Back</a></p>
<hr>
<form action="<c:url value="/create_department"/>" method="POST">
    <p>
        <strong><label for="department_id">ID</label></strong>
        <br>
        <input type="text" name="department_id" id="department_id" required>
    </p>
    <p>
        <strong><label for="title">Title</label></strong>
        <br>
        <input type="text" name="title" id="title" required>
    </p>
    <input type="submit" value="Create Department">
</form>
</body>
</html>
