<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | View Keyword</title>
    <meta charset="UTF-8">
</head>
<body>
<h2>View: ${keyword.title}</h2>
<p>
    <a href="<c:url value="/edit_keyword?id=${keyword.id}"/>">Edit</a>
    /
    <a href="<c:url value="/delete_keyword?id=${keyword.id}"/>">Delete</a>
</p>
<p><a href="<c:url value="/manage_keywords"/>"><< Back</a></p>
<hr>

<h4>ID</h4>
<p>${keyword.id}</p>

<h4>Title</h4>
<p>${keyword.title}</p>

<h4>Slug</h4>
<p>${keyword.slug}</p>

</body>
</html>
