<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | Delete Company</title>
    <meta charset="UTF-8">
</head>
<body>
<h2>Delete: ${company.title}</h2>
<p><a href="<c:url value="/manage_companies"/>"><< Back</a></p>
<hr>
<p>Are you sure you want to delete <strong>${company.title} (${company.id})</strong>?</p>
<form action="<c:url value="/delete_company?id=${company.id}"/>" method="POST">
    <input type="submit" value="Yes, Delete">
</form>
</body>
</html>
