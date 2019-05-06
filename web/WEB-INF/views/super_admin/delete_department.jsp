<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | Delete Department</title>
    <meta charset="UTF-8">
</head>
<body>
<h2>Delete: ${department.title}</h2>
<p><a href="<c:url value="/manage_departments"/>"><< Back</a></p>
<hr>
<p>Are you sure you want to delete <strong>${department.title} (${department.id})</strong>?</p>
<form action="<c:url value="/delete_department?id=${department.id}"/>" method="POST">
    <input type="submit" value="Yes, Delete">
</form>
</body>
</html>
