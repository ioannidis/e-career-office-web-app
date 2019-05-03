<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Admin | Home</title>
    <c:import url="/WEB-INF/views/styles.jsp"></c:import>
</head>
<body class="fixed-nav sticky-footer bg-dark" id="page-top"
      cz-shortcut-listen="true">

<!-- Navigation -->
<c:import url="/WEB-INF/views/nav.jsp"></c:import>
<%
    request.getAttribute("skills");
    request.getAttribute("name");
    request.getAttribute("classifiedSkills");
%>
<div class="content-wrapper">
    <div class="container-fluid">

        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="admin">Admin</a></li>
            <li class="breadcrumb-item"><a href="adminstudents">Students</a></li>
        </ol>

        <div style="text-align:right">
            <a href="adminregister" class="btn btn-info"><i class="far fa-plus-square" style="margin-right:8px"></i>Register a Student</a>
        </div>
        <br/>
        <div class="card mb-3">

            <div class="card-header">
                <i class="fa fa-table"></i><b>Students Table</b></div>
            <div class="card-body">

                <div class="table-responsive">
                    <table class="table table-bordered table-striped" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                        <tr>
                            <th>Username</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Phone Number</th>
                            <th>Email</th>
                            <th>Role</th>
                            <th>Skills</th>
                            <th>Actions</th>

                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${users}" var="users">
                            <tr>
                                <td><c:out value="${users.username}" /></td>
                                <td><c:out value="${users.name}" /></td>
                                <td><c:out value="${users.surname}" /></td>
                                <td><c:out value="${users.phoneNumber}" /></td>
                                <td><c:out value="${users.email}" /></td>
                                <td><c:out value="${users.roleId}" /></td>
                                <td><c:out value="${users.getUserSkills().skills}" /></td>
                                <td><c:out value="${all}" /></td>
                                <td><c:out value="${classifiedAll}" /></td>
                                <td>
                                    <a class="btn btn-primary" href="adminclassifieds?name=${users.name} ${users.surname}&studentSkills=${users.getUserSkills().getSlug()}"><i class="fas fa-clipboard" style="margin-right:8px"></i>Assign Job</a>

                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <c:import url="/WEB-INF/views/footer.jsp"></c:import>
</div>

<c:import url="/WEB-INF/views/scripts.jsp"></c:import>
</body>
</html>