<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | Manage Users</title>
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
            <li class="breadcrumb-item active">
                Users
            </li>
        </ol>

        <div class="card mb-3">
            <div class="card-header d-flex flex-row align-items-center">
                <i class="fa fa-users-cog mr-2"></i>
                <strong class="mr-auto">Registered Users</strong>
                <a class="btn btn-primary d-flex flex-row align-items-center" href="<c:url value="/create_user" />">
                    <i class="fa fa-plus-circle mr-2"></i>
                    Create User
                </a>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered table-striped" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                        <tr>
                            <td>Username</td>
                            <td>First Name</td>
                            <td>Last Name</td>
                            <td>Phone Number</td>
                            <td>Email</td>
                            <td>Role</td>
                            <td>Company</td>
                            <td>Department</td>
                            <td>Actions</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${users}" var="otherUser">
                            <tr>
                                <td><c:out value="${otherUser.username}" default="-"/></td>
                                <td><c:out value="${otherUser.name}" default="-"/></td>
                                <td><c:out value="${otherUser.surname}" default="-"/></td>
                                <td><c:out value="${otherUser.phoneNumber}" default="-"/></td>
                                <td><c:out value="${otherUser.email}" default="-"/></td>
                                <td><c:out value="${otherUser.role.title}" default="-"/></td>
                                <td><c:out value="${otherUser.userCompany.company.title}" default="-"/></td>
                                <td><c:out value="${otherUser.userDepartment.department.title}" default="-"/></td>
                                <td>
                                    <div class="btn-group">
                                        <a class="btn btn-outline-primary"
                                           href="<c:url value="/view_user?id=${otherUser.username}"/>">View</a>
                                        <a class="btn btn-outline-primary"
                                           href="<c:url value="/edit_user?id=${otherUser.username}"/>">Edit</a>
                                        <a class="btn btn-outline-primary"
                                           href="<c:url value="/delete_user?id=${otherUser.username}"/>">Delete</a>
                                    </div>
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
