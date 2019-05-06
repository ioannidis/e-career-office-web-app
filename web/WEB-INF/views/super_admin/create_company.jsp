<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | Create Company</title>
    <meta charset="UTF-8">
</head>
<body>
<h2>Create Company</h2>
<p><a href="<c:url value="/manage_companies"/>"><< Back</a></p>
<hr>
<form action="<c:url value="/create_company"/>" method="POST">
    <p>
        <strong><label for="company_id">ID</label></strong>
        <br>
        <input type="text" name="company_id" id="company_id" required>
    </p>
    <p>
        <strong><label for="title">Title</label></strong>
        <br>
        <input type="text" name="title" id="title" required>
    </p>
    <p>
        <strong><label for="address">Address</label></strong>
        <br>
        <input type="text" name="address" id="address" required>
    </p>
    <p>
        <strong><label for="phone_number">Phone Number</label></strong>
        <br>
        <input type="text" name="phone_number" id="phone_number" required>
    </p>
    <p>
        <strong><label for="email">Email</label></strong>
        <br>
        <input type="email" name="email" id="email" required>
    </p>
    <p>
        <strong><label for="website">Website</label></strong>
        <br>
        <input type="text" name="website" id="website" required>
    </p>
    <input type="submit" value="Create Company">
</form>
</body>
</html>
