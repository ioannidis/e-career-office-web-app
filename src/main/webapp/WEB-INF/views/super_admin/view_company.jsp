<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | View Company</title>
    <meta charset="UTF-8">
</head>
<body>
<h2>View: ${company.title}</h2>
<p>
    <a href="<c:url value="/edit_company?id=${company.id}"/>">Edit</a>
    /
    <a href="<c:url value="/delete_company?id=${company.id}"/>">Delete</a>
</p>
<p><a href="<c:url value="/manage_companies"/>"><< Back</a></p>
<hr>

<h4>Title</h4>
<p>${company.title}</p>

<h4>Address</h4>
<p>${company.address}</p>

<h4>Phone Number</h4>
<p>${company.phoneNumber}</p>

<h4>Email</h4>
<p><a href="mailto:${company.email}">${company.email}</a></p>

<h4>Website</h4>
<p><a href="${company.website}" target="_blank">${company.website}</a></p>

</body>
</html>
