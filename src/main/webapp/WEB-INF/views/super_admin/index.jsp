<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | Home</title>
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
                Home
            </li>
        </ol>

        <div class="card mb-3">
            <div class="card-header d-flex flex-row align-items-center">
                <i class="fa fa-user-circle"></i>
                <strong>User Info</strong>
            </div>

            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered table-striped" width="100%" cellspacing="0">
                        <tbody>
                        <tr>
                            <td><b>Username</b></td>
                            <td>${user.username}</td>
                        </tr>
                        <tr>
                            <td><b>Name</b></td>
                            <td>${user.name}</td>
                        </tr>
                        <tr>
                            <td><b>Surname</b></td>
                            <td>${user.surname}</td>
                        </tr>
                        <tr>
                            <td><b>Phone</b></td>
                            <td>${user.phoneNumber}</td>
                        </tr>
                        <tr>
                            <td><b>Email</b></td>
                            <td>${user.email}</td>
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
