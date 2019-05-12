<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | Delete Keyword</title>
    <meta charset="UTF-8">
</head>
<body>
<h2>Delete: ${keyword.title}</h2>
<p><a href="<c:url value="/manage_keywords"/>"><< Back</a></p>
<hr>
<p>Are you sure you want to delete <strong>${keyword.title} (${keyword.id})</strong>?</p>
<form action="<c:url value="/delete_keyword?id=${keyword.id}"/>" method="POST">
    <input type="submit" value="Yes, Delete">
</form>
</body>
</html>
