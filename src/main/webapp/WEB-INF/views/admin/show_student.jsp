<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Classified | Show classified</title>
    <c:import url="/WEB-INF/views/styles.jsp"></c:import>
</head>
<body class="fixed-nav sticky-footer bg-dark" id="page-top" cz-shortcut-listen="true">

<!-- Navigation -->
<c:import url="/WEB-INF/views/nav.jsp"></c:import>


<div class="content-wrapper">
    <div class="container-fluid">

        <ol class="breadcrumb">
            <li class="breadcrumb-item text-primary"><a href="admin" >Admin</a></li>
            <li class="breadcrumb-item text-primary"><a href="adminstudents" >Students</a></li>
            <li class="breadcrumb-item active"><c:out value="${student.username}" /></li>
        </ol>

        <div class="card mb-3">
            <div class="card-header">
                <i class="fa fa-table"></i><b>Student</b></div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered table-striped" id="dataTable" width="100%" cellspacing="0">
                        <tbody>
                        <tr>
                            <td><b>Username</b></td>
                            <td><c:out value="${student.username}" /></td>
                        </tr>
                        <tr>
                            <td><b>Name</b></td>
                            <td><c:out value="${student.name}" /></td>
                        </tr>
                        <tr>
                            <td><b>Surname</b></td>
                            <td><c:out value="${student.username}" /></td>
                        </tr>
                        <tr>
                            <td><b>Phone Number</b></td>
                            <td><c:out value="${student.phoneNumber}" /></td>
                        </tr>
                        <tr>
                            <td><b>Email Adress</b></td>
                            <td><c:out value="${student.email}" /></td>
                        </tr>
                        <tr>
                            <td><b>Role</b></td>
                            <td><c:out value="${student.role.title}" /></td>
                        </tr>
                        <tr>
                            <td><b>Keywords</b></td>
                            <td>
                                <c:forEach var="keyword" items="${keywords}">
                                    <span class="badge badge-info">${keyword.title}</span>
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <td><b>Actions</b></td>
                            <td>
                                <a class="btn btn-primary" href="adminstudents" class="btn"><i class="fas fa-caret-left" style="margin-right:8px"></i>Back</a>
                                <a class="btn btn-warning" href="adminstudents?name=${student.username}&action=edit" class="btn"><i class="far fa-edit" style="margin-right:8px"></i>Edit</a>
                                <a class="btn btn-danger" href="adminstudents?name=${student.username}&action=delete" class="btn"><i class="fas fa-times" style="margin-right:8px"></i>Delete</a>
                            </td>
                        </tr>
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