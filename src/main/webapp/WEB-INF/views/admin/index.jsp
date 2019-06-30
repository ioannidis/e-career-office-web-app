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

<div class="content-wrapper">
    <div class="container-fluid">

        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="admin">Admin</a></li>
        </ol>

        <div class="card mb-3">
            <div class="card-header d-flex flex-row align-items-center">
                <i class="fa fa-user-circle mr-2"></i>
                <strong class="mr-auto">Admin Information</strong>
                <a class="btn btn-warning"
                   href="admin?action=edit">
                    <i class="fas fa-pencil-alt" style="margin-right:8px"></i>
                    Edit details
                </a>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered table-striped" id="dataTable"
                           width="100%" cellspacing="0">
                        <tbody>
                        <tr>
                            <td><b>Username</b></td>
                            <td><c:out value="${user.username}" /></td>
                        </tr>
                        <tr>
                            <td><b>Name</b></td>
                            <td><c:out value="${user.name}" /></td>
                        </tr>
                        <tr>
                            <td><b>Surname</b></td>
                            <td><c:out value="${user.surname}" /></td>
                        </tr>
                        <tr>
                            <td><b>Phone</b></td>
                            <td><c:out value="${user.phoneNumber}" /></td>
                        </tr>
                        <tr>
                            <td><b>Email</b></td>
                            <td><c:out value="${user.email}" /></td>
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