<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | Edit User</title>
    <meta charset="UTF-8">
</head>
<body>
<h2>Edit: ${user.fullName}</h2>
<p><a href="<c:url value="/manage_users"/>"><< Back</a></p>
<hr>
<form action="<c:url value="/edit_user?id=${user.username}"/>" method="POST">
    <p>
        <strong><label for="first_name">First Name</label></strong>
        <br>
        <input type="text" name="first_name" id="first_name" value="${user.name}" required>
    </p>

    <p>
        <strong><label for="last_name">Last Name</label></strong>
        <br>
        <input type="text" name="last_name" id="last_name" value="${user.surname}" required>
    </p>

    <p>
        <strong><label for="phone_number">Phone Number</label></strong>
        <br>
        <input type="text" name="phone_number" id="phone_number" value="${user.phoneNumber}" required>
    </p>

    <p>
        <strong><label for="email">Email</label></strong>
        <br>
        <input type="email" name="email" id="email" value="${user.email}" required>
    </p>

    <p>
        <strong><label for="company">Company</label></strong>
        <br>
        <select name="company" id="company">
            <option value="nothing" ${user.userCompany == null ? "selected" : ""}>-</option>

            <c:forEach items="${companies}" var="company">

                <option value="<c:out value="${company.id}" />" ${company.id == user.userCompany.companyId ? "selected" : ""}>
                    ${company.title}
                </option>

            </c:forEach>
        </select>
    </p>

    <p>
        <strong><label for="department">Department</label></strong>
        <br>
        <select name="department" id="department">
            <option value="nothing" ${user.userDepartment == null ? "selected" : ""}>-</option>

            <c:forEach items="${departments}" var="department">

                <option value="${department.id}" ${department.id == user.userDepartment.departmentId ? "selected" : ""}>
                    ${department.title}
                </option>

            </c:forEach>
        </select>
    </p>

    <input type="submit" value="Save Changes">
</form>

</body>
</html>
