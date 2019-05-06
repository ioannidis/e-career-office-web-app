<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | View Department</title>
    <meta charset="UTF-8">
</head>
<body>
<h2>View: ${department.title}</h2>
<p>
    <a href="<c:url value="/edit_department?id=${department.id}"/>">Edit</a>
    /
    <a href="<c:url value="/delete_department?id=${department.id}"/>">Delete</a>
</p>
<p><a href="<c:url value="/manage_departments"/>"><< Back</a></p>
<hr>
<h4>ID</h4>
<p>${department.id}</p>

<h4>Title</h4>
<p>${department.title}</p>
</body>
</html>
