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

                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${users}" var="user">
                            <tr>
                                <td><c:out value="${user.username}" /></td>
                                <td><c:out value="${user.first_name}" /></td>
                                <td><c:out value="${user.last_name}" /></td>
                                <td><c:out value="${user.phone_number}" /></td>
                                <td><c:out value="${user.email}" /></td>
                                <td><c:out value="${user.role_id}" /></td>
                                <td><c:out value="${user_department[user.username].department_id}" /></td>
                                <td><c:out value="${keyword_cv[cvs[user.username].username].keyword_id}" /></td>
                                <td>
                                    <a class="btn btn-primary" href="adminstudents?id=${user.id}&action=show"><i class="fas fa-external-link-alt" style="margin-right:8px"></i>Show</a>
                                    <a class="btn btn-primary" href="adminclassifieds?id=${user.id}&action=assign"><i class="fas fa-external-link-alt" style="margin-right:8px"></i>Assign</a>
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