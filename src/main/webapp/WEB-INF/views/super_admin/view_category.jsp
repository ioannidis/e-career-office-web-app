<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | View Category</title>
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
                View Category
            </li>
        </ol>
        <div class="card mb-3">
            <div class="card-header d-flex flex-row align-items-center">
                <i class="fa fa-book mr-2"></i>
                <strong class="mr-auto">View Category</strong>
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
                            ${category.id}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <strong>Title</strong>
                        </td>
                        <td>
                            ${category.title}
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <strong>Slug</strong>
                        </td>
                        <td>
                            ${category.slug}
                        </td>
                    </tr>
                    </tbody>
                    </table>
                </div>
            </div>
            <div class="card-footer d-flex flex-row align-items-center justify-content-end">
                <a class="btn btn-primary mr-3" href="<c:url value="/edit_category?id=${category.id}"/>">
                    Edit
                </a>
                <a class="btn btn-danger" href="<c:url value="/delete_category?id=${category.id}"/>">
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
