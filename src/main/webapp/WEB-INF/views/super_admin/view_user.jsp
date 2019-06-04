<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | View User</title>
    <meta charset="UTF-8">
    <c:import url="/WEB-INF/views/styles.jsp"></c:import>
</head>
<body class="fixed-nav sticky-footer bg-dark">
<c:import url="/WEB-INF/views/nav.jsp"></c:import>

<div class="content-wrapper">
    <div class="container-fluid">

        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="<c:url value="/super_admin"/>">
                    Super Admin
                </a>
            </li>
            <li class="breadcrumb-item">
                <a href="<c:url value="/manage_users"/>">
                    Users
                </a>
            </li>
            <li class="breadcrumb-item active">
                View User
            </li>
        </ol>

        <div class="card mb-3">
            <div class="card-header d-flex flex-row align-items-center">
                <i class="fa fa-users-cog mr-2"></i>
                <strong>View User</strong>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered table-striped" width="100%" cellspacing="0">
                        <tbody>
                        <tr>
                            <td><strong>Username</strong></td>
                            <td>${user.username}</td>
                        </tr>
                        <tr>
                            <td><strong>Full Name</strong></td>
                            <td>${user.fullName}</td>
                        </tr>
                        <tr>
                            <td><strong>Phone Number</strong></td>
                            <td>${user.phoneNumber}</td>
                        </tr>
                        <tr>
                            <td><strong>Email Address</strong></td>
                            <td>${user.email}</td>
                        </tr>
                        <tr>
                            <td><strong>Role</strong></td>
                            <td>${user.role.title}</td>
                        </tr>
                        <c:if test="${user.userCompany != null}">
                            <tr>
                                <td><strong>Company</strong></td>
                                <td>${user.userCompany.company.title}</td>
                            </tr>
                        </c:if>

                        <c:if test="${user.userDepartment != null}">
                            <tr>
                                <td><strong>Department</strong></td>
                                <td>${user.userDepartment.department.title}</td>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="card-footer d-flex flex-row align-items-center justify-content-end">
                <a class="btn btn-primary mr-3" href="<c:url value="/edit_user?id=${user.username}"/>">
                    Edit
                </a>
                <a class="btn btn-danger" href="<c:url value="/delete_user?id=${user.username}"/>">
                    Delete
                </a>
            </div>
        </div>
        <c:import url="/WEB-INF/views/footer.jsp"></c:import>
    </div>
</div>
<c:import url="/WEB-INF/views/scripts.jsp"></c:import>
</body>
</html>
