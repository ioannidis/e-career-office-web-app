<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | View Classified</title>
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
                <a href="<c:url value="/manage_classifieds"/>">
                    Classifieds
                </a>
            </li>
            <li class="breadcrumb-item active">
                View Classified
            </li>
        </ol>

        <div class="card mb-3">
            <div class="card-header d-flex flex-row align-items-center">
                <i class="fa fa-address-card mr-2"></i>
                <strong class="mr-auto">Classifieds</strong>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered table-striped" id="dataTable" width="100%" cellspacing="0">
                        <tbody>
                        <tr>
                            <td><strong>ID</strong></td>
                            <td>${classified.id}</td>
                        </tr>
                        <tr>
                            <td><strong>Title</strong></td>
                            <td>${classified.title}</td>
                        </tr>
                        <tr>
                            <td><strong>Content</strong></td>
                            <td>${classified.content}</td>
                        </tr>
                        <tr>
                            <td><strong>Company</strong></td>
                            <td>${classified.company.title}</td>
                        </tr>
                        <tr>
                            <td><strong>Category</strong></td>
                            <td>${classified.category.title}</td>
                        </tr>
                        <tr>
                            <td><strong>Keywords</strong></td>
                            <td>
                                <c:forEach items="${classified.keywordClassifiedPivotList}" var="rel"
                                           varStatus="status">
                                    ${rel.keyword.title}
                                    <c:if test="${!status.last}">,</c:if>
                                </c:forEach>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="card-footer flex-row align-items-center text-right">
                <a class="btn btn-primary"
                   href="<c:url value="/manage_classifieds"/>"><i class="fas fa-caret-left" style="margin-right:8px"></i>Back</a>
                <a class="btn btn-warning"
                   href="<c:url value="/edit_classified?id=${classified.id}"/>"><i class="far fa-edit" style="margin-right:8px"></i>Edit</a>
                <a class="btn btn-danger"
                   href="<c:url value="/delete_classified?id=${classified.id}"/>"><i class="fas fa-times" style="margin-right:8px"></i>Delete</a>
            </div>

        </div>
        <c:import url="/WEB-INF/views/footer.jsp"></c:import>
    </div>
</div>
<c:import url="/WEB-INF/views/scripts.jsp"></c:import>
</body>
</html>
