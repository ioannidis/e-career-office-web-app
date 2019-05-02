<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | Delete User</title>
    <meta charset="UTF-8">
</head>
<body>
<h2>Delete: ${user.fullName}</h2>
<p><a href="<c:url value="/manage_users"/>"><< Back</a></p>
<hr>
<p>Are you sure you want to delete <strong>${user.fullName} (${user.username})</strong>?</p>
<form action="<c:url value="/delete_user"/>" method="POST">
    <input type="submit" value="Yes, Delete">
</form>
</body>
</html>
