<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | Create Department</title>
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
                    Departments
                </a>
            </li>
            <li class="breadcrumb-item active">
                Create Department
            </li>
        </ol>
        <div class="card mb-3">
            <div class="card-header d-flex flex-row align-items-center">
                <i class="fa fa-warehouse mr-2"></i>
                <strong>Create Department</strong>
            </div>
            <div class="card-body">
                <form action="<c:url value="/create_department"/>" method="POST" id="create_department_form">
                    <div class="form-row">
                        <div class="form-group col-3">
                            <strong><label for="department_id">ID</label></strong>
                            <input class="form-control" type="text" name="department_id" id="department_id" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-3">
                            <strong><label for="title">Title</label></strong>
                            <input class="form-control" type="text" name="title" id="title" required>
                        </div>
                    </div>
                </form>
            </div>
            <div class="card-footer d-flex flex-row align-items-center justify-content-end">
                <button class="btn btn-primary d-flex flex-row align-items-center mr-3" form="create_department_form">
                    <i class="fa fa-plus-circle mr-2"></i>
                    Create Department
                </button>
                <a class="btn btn-danger" href="<c:url value="/manage_departments"/>">
                    Cancel
                </a>
            </div>
        </div>
        <c:import url="/WEB-INF/views/footer.jsp"></c:import>
    </div>
    <c:import url="/WEB-INF/views/scripts.jsp"></c:import>
</div>
</body>
</html>
