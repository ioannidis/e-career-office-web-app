<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | Delete Category</title>
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
                <a href="<c:url value="/manage_categories"/>">
                    Categories
                </a>
            </li>
            <li class="breadcrumb-item active">
                Delete Category
            </li>
        </ol>
        <div class="card mb-3">
            <div class="card-header d-flex flex-row align-items-center">
                <i class="fa fa-book mr-2"></i>
                <strong>Delete Category</strong>
            </div>
            <div class="card-body">
                <div>Are you sure you want to delete <strong>${category.title}</strong>?</div>
                <form action="<c:url value="/delete_category?id=${category.id}"/>" method="POST" id="delete_category_form"></form>
            </div>
            <div class="card-footer d-flex flex-row align-items-center justify-content-end">
                <button class="btn btn-primary d-flex flex-row align-items-center mr-3" form="delete_category_form">
                    <i class="fa fa-trash mr-2"></i>
                    Delete
                </button>
                <a class="btn btn-danger" href="<c:url value="/manage_categories"/>">
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
