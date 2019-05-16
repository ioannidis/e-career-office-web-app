<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | Delete Classified</title>
    <meta charset="UTF-8">
</head>
<body>
<h2>Delete: ${classified.title}</h2>
<p><a href="<c:url value="/manage_classifieds"/>"><< Back</a></p>
<hr>
<p>Are you sure you want to delete <strong>${classified.title} (${classified.id})</strong>?</p>
<form action="<c:url value="/delete_classified?id=${classified.id}"/>" method="POST">
    <input type="submit" value="Yes, Delete">
</form>
</body>
</html>
