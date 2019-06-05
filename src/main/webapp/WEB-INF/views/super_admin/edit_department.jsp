<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | Edit Department</title>
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
                Edit Department
            </li>
        </ol>
        <div class="card mb-3">
            <div class="card-header d-flex flex-row align-items-center">
                <i class="fa fa-warehouse mr-2"></i>
                <strong>Edit Department</strong>
            </div>
            <div class="card-body">
                <form action="<c:url value="/edit_department?id=${department.id}"/>" method="POST"
                      id="edit_department_form">
                    <div class="form-row">
                        <div class="form-group col-3">
                            <strong><label for="title">Title</label></strong>
                            <input class="form-control" type="text" name="title" id="title" value="${department.title}" required>
                        </div>
                    </div>
                </form>
            </div>
            <div class="card-footer d-flex flex-row align-items-center justify-content-end">
                <button class="btn btn-primary d-flex flex-row align-items-center mr-3" form="edit_department_form">
                    <i class="fa fa-save mr-2"></i>
                    Save Changes
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
