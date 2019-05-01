<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | Edit User</title>
    <meta charset="UTF-8">
</head>
<body>
<h2>Edit: <c:out value="${user.fullName}" /></h2>
<p><a href="<c:url value="/manage_users"/>"><< Back</a></p>
<hr>
<form action="<c:url value="/edit_user?id=${user.username}"/>" method="POST">
    <p>
        <strong><label for="username">Username</label></strong>
        <br>
        <input type="text" name="username" id="username" value="<c:out value="${user.username}" />" required>
    </p>

    <p>
        <strong><label for="first_name">First Name</label></strong>
        <br>
        <input type="text" name="first_name" id="first_name" value="<c:out value="${user.name}" />" required>
    </p>

    <p>
        <strong><label for="last_name">Last Name</label></strong>
        <br>
        <input type="text" name="last_name" id="last_name" value="<c:out value="${user.surname}" />" required>
    </p>

    <p>
        <strong><label for="phone_number">Phone Number</label></strong>
        <br>
        <input type="text" name="phone_number" id="phone_number" value="<c:out value="${user.phoneNumber}" />" required>
    </p>

    <p>
        <strong><label for="email">Email</label></strong>
        <br>
        <input type="email" name="email" id="email" value="<c:out value="${user.email}" />" required>
    </p>

    <p>
        <strong><label for="role">Role</label></strong>
        <br>
        <input type="text" name="role" id="role" value="<c:out value="${user.role.title}" />" required>
    </p>

    <p>
        <strong><label for="role">Company</label></strong>
        <br>
        <input type="text" name="company" id="company" value="<c:out value="${user.company.title}" />" required>
    </p>

    <p>
        <strong><label for="department">Department</label></strong>
        <br>
        <input type="text" name="department" id="department" value="<c:out value="${user.department.title}" />" required>
    </p>

    <input type="submit" value="Save Changes">
</form>

</body>
</html>
