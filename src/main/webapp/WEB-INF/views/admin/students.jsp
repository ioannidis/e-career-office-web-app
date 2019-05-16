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
    request.getAttribute("name");
    request.getAttribute("studentKeyword");
%>
<div class="content-wrapper">
    <div class="container-fluid">

        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="admin">Admin</a></li>
            <li class="breadcrumb-item"><a href="adminstudents">Students</a></li>
        </ol>

        <div style="text-align:right">
            <a href="adminregister" class="btn btn-info"><i class="far fa-plus-square" style="margin-right:8px"></i>Add Student</a>
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
                            <th>Email</th>
                            <th>Keywords</th>
                            <th>Actions</th>

                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="student" items="${users}">
                            <tr>
                                <td><c:out value="${student.user.username}" /></td>
                                <td><c:out value="${student.user.name}" /></td>
                                <td><c:out value="${student.user.surname}" /></td>
                                <td><c:out value="${student.user.email}" /></td>
                                <td>
                                    <c:forEach var="keyword" items="${student.keywords}">
                                        <span class="badge badge-info">${keyword.title}</span>
                                    </c:forEach>
                                </td>
                                <td>
                                    <a class="btn btn-info" href="<c:url value="admincontact?id=${student.user.username}"/>"><i class="fas fa-phone" style="margin-right:8px"></i>Contact</a>
                                    <a class="btn btn-primary" href="adminstudents?name=${student.user.username}&action=show"><i class="fas fa-external-link-alt" style="margin-right:8px"></i>Show</a>
                                    <a class="btn btn-warning" href="adminstudents?name=${student.user.username}&action=edit"><i class="far fa-edit" style="margin-right:8px"></i>Edit</a>
                                    <a class="btn btn-danger" href="adminstudents?name=${student.user.username}&action=delete"><i class="far fa-times" style="margin-right:8px"></i>Delete</a>

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