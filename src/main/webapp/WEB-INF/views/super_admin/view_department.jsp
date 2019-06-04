<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | View Department</title>
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
                <a href="<c:url value="/manage_departments"/>">
                    Departments
                </a>
            </li>
            <li class="breadcrumb-item active">
                View Department
            </li>
        </ol>

        <div class="card mb-3">
            <div class="card-header d-flex flex-row align-items-center">
                <i class="fa fa-warehouse mr-2"></i>
                <strong class="mr-auto">View Department</strong>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered table-striped" id="dataTable" width="100%" cellspacing="0">
                        <tbody>
                        <tr>
                            <td>
                                <strong>ID</strong>
                            </td>
                            <td>
                                ${department.id}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <strong>Title</strong>
                            </td>
                            <td>
                                ${department.title}
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="card-footer d-flex flex-row align-items-center justify-content-end">
                <a class="btn btn-primary mr-3" href="<c:url value="/edit_department?id=${department.id}"/>">
                    Edit
                </a>
                <a class="btn btn-danger" href="<c:url value="/delete_department?id=${department.id}"/>">
                    Delete
                </a>
            </div>
        </div>
        <c:import url="/WEB-INF/views/footer.jsp"></c:import>
    </div>
</div>
<c:import url="/WEB-INF/views/scripts.jsp"></c:import>

<h2>View: ${department.title}</h2>
<p>
    <a href="<c:url value="/edit_department?id=${department.id}"/>">Edit</a>
    /
    <a href="<c:url value="/delete_department?id=${department.id}"/>">Delete</a>
</p>
<p><a href="<c:url value="/manage_departments"/>"><< Back</a></p>
<hr>
<h4>ID</h4>
<p>${department.id}</p>

<h4>Title</h4>
<p>${department.title}</p>
</body>
</html>
