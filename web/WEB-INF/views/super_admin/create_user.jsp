<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | Create User</title>
    <meta charset="UTF-8">
</head>
<body>
<h2>Create User</h2>
<p><a href="<c:url value="/manage_users"/>"><< Back</a></p>
<hr>
<form action="<c:url value="/create_user"/>" method="POST">
    <p>
        <strong><label for="username">Username</label></strong>
        <br>
        <input type="text" name="username" id="username" required>
    </p>

    <p>
        <strong><label for="password">Password</label></strong>
        <br>
        <input type="password" name="password" id="password" required>
    </p>

    <p>
        <strong><label for="first_name">First Name</label></strong>
        <br>
        <input type="text" name="first_name" id="first_name" required>
    </p>

    <p>
        <strong><label for="last_name">Last Name</label></strong>
        <br>
        <input type="text" name="last_name" id="last_name" required>
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
        <strong><label for="role">Select Role</label></strong>
        <br>
        <select name="role" id="role">
            <c:forEach items="${roles}" var="role">
                <option value="${role.id}">${role.title}</option>
            </c:forEach>
        </select>
    </p>

    <p>
        <strong><label for="company">Select Company</label></strong>
        <br>
        <select name="company" id="company">
            <option value="nothing" selected>-</option>

            <c:forEach items="${companies}" var="company">

                <option value="${company.id}">
                        ${company.title}
                </option>

            </c:forEach>
        </select>
    </p>

    <p>
        <strong><label for="department">Select Department</label></strong>
        <br>
        <select name="department" id="department">
            <option value="nothing" selected>-</option>

            <c:forEach items="${departments}" var="department">

                <option value="${department.id}">
                        ${department.title}
                </option>

            </c:forEach>
        </select>
    </p>

    <input type="submit" value="Create User">
</form>

</body>
</html>
