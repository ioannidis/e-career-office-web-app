<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | Manage Categories</title>
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
                Categories
            </li>
        </ol>

        <div class="card mb-3">
            <div class="card-header d-flex flex-row align-items-center">
                <i class="fa fa-book mr-2"></i>
                <strong class="mr-auto">Categories</strong>
                <a class="btn btn-primary d-flex flex-row align-items-center"
                   href="<c:url value="/create_category" />">
                    <i class="fa fa-plus-circle mr-2"></i>
                    Create Category
                </a>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered table-striped" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                        <tr>
                            <td>ID</td>
                            <td>Title</td>
                            <td>Slug</td>
                            <td>Actions</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${categories}" var="category">
                            <tr>
                                <td><c:out value="${category.id}" default="-"/></td>
                                <td><c:out value="${category.title}" default="-"/></td>
                                <td><c:out value="${category.slug}" default="-"/></td>
                                <td>
                                    <div class="btn-group">
                                        <a class="btn btn-outline-primary"
                                           href="<c:url value="/view_category?id=${category.id}"/>">View</a>
                                        <a class="btn btn-outline-primary"
                                           href="<c:url value="/edit_category?id=${category.id}"/>">Edit</a>
                                        <a class="btn btn-outline-primary"
                                           href="<c:url value="/delete_category?id=${category.id}"/>">Delete</a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <c:import url="/WEB-INF/views/footer.jsp"></c:import>
    </div>
</div>
<c:import url="/WEB-INF/views/scripts.jsp"></c:import>
</body>
</html>
