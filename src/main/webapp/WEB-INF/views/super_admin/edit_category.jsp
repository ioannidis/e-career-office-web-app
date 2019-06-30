<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | Edit Category</title>
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
                Edit Category
            </li>
        </ol>
        <div class="card mb-3">
            <div class="card-header d-flex flex-row align-items-center">
                <i class="fa fa-book mr-2"></i>
                <strong>Edit Category</strong>
            </div>
            <div class="card-body">
                <form action="<c:url value="/edit_category?id=${category.id}"/>" method="POST" id="edit_category_form">
                    <div class="form-group">
                        <strong><label for="title">Title</label></strong>
                        <input class="form-control" type="text" name="title" id="title" value="${category.title}" required>
                    </div>
                    <div class="form-group">
                        <strong><label for="slug">Slug</label></strong>
                        <input class="form-control" type="text" name="slug" id="slug" value="${category.slug}" required>
                    </div>
                </form>
            </div>
            <div class="card-footer flex-row align-items-center text-right">
                <a href="manage_categories" class="btn btn-warning"><i class="fas fa-caret-left" style="margin-right:8px"></i>Cancel</a>
                <button type="submit" class="btn btn-success" form="edit_category_form"><i class="far fa-save" style="margin-right:8px"></i>Update</button>
            </div>

        </div>
        <c:import url="/WEB-INF/views/footer.jsp"></c:import>
    </div>
    <c:import url="/WEB-INF/views/scripts.jsp"></c:import>
</div>
</body>
</html>
