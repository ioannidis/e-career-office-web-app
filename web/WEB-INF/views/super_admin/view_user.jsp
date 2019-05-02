<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | View User</title>
    <meta charset="UTF-8">
</head>
<body>
<h2>View: ${user.fullName}</h2>
<p>
    <a href="<c:url value="/edit_user?id=${user.username}"/>">Edit</a>
    /
    <a href="<c:url value="/delete_user?id=${user.username}"/>">Delete</a>
</p>
<p>
    <a href="<c:url value="/manage_users"/>"><< Back</a>
</p>
<hr>
<h4>Username</h4>
<p>${user.username}</p>

<h4>Full Name</h4>
<p>${user.fullName}</p>

<h4>Phone Number</h4>
<p>${user.phoneNumber}</p>

<h4>Email Address</h4>
<p>${user.email}</p>

<h4>Role</h4>
<p>${user.role.title}</p>

<c:if test="${user.company != null}">
    <h4>Company</h4>
    <p>${user.company.title}</p>
</c:if>

<c:if test="${user.department != null}">
    <h4>Department</h4>
    <p>${user.department.title}</p>
</c:if>
</body>
</html>
