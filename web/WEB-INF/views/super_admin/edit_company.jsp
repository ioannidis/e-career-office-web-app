<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | Edit Company</title>
    <meta charset="UTF-8">
</head>
<body>
<h2>Edit: ${company.title}</h2>
<p><a href="<c:url value="/manage_companies"/>"><< Back</a></p>
<hr>
<form action="<c:url value="/edit_company?id=${company.id}"/>" method="POST">
    <p>
        <strong><label for="title">Title</label></strong>
        <br>
        <input type="text" name="title" id="title" value="${company.title}" required>
    </p>
    <p>
        <strong><label for="address">Address</label></strong>
        <br>
        <input type="text" name="address" id="address" value="${company.address}" required>
    </p>
    <p>
        <strong><label for="phone_number">Phone Number</label></strong>
        <br>
        <input type="text" name="phone_number" id="phone_number" value="${company.phoneNumber}" required>
    </p>
    <p>
        <strong><label for="email">Email</label></strong>
        <br>
        <input type="email" name="email" id="email" value="${company.email}" required>
    </p>
    <p>
        <strong><label for="website">Website</label></strong>
        <br>
        <input type="text" name="website" id="website" value="${company.website}" required>
    </p>
    <input type="submit" value="Save Changes">
</form>
</body>
</html>
