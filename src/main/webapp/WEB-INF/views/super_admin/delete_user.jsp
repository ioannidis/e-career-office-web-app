<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | Delete User</title>
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
                Delete User
            </li>
        </ol>

        <div class="card mb-3">
            <div class="card-header d-flex flex-row align-items-center">
                <i class="fa fa-users-cog mr-2"></i>
                <strong>Delete User</strong>
            </div>
            <div class="card-body">
                <div>Are you sure you want to delete <strong>${user.fullName}</strong>?</div>
                <form action="<c:url value="/delete_user?id=${user.username}"/>"
                      method="POST"
                      id="delete_user_form"></form>
            </div>
            <div class="card-footer d-flex flex-row align-items-center justify-content-end">
                <button class="btn btn-primary d-flex flex-row align-items-center mr-3" form="delete_user_form">
                    <i class="fa fa-trash mr-2"></i>
                    Delete
                </button>
                <a class="btn btn-danger" href="<c:url value="/manage_users"/>">
                    Cancel
                </a>
            </div>
        </div>
        <c:import url="/WEB-INF/views/footer.jsp"></c:import>
    </div>
</div>
</body>
</html>
