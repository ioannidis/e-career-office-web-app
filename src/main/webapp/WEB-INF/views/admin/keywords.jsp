<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Admin | Keywords</title>
    <c:import url="/WEB-INF/views/styles.jsp"></c:import>
</head>
<body class="fixed-nav sticky-footer bg-dark" id="page-top"
      cz-shortcut-listen="true">

<!-- Navigation -->
<c:import url="/WEB-INF/views/nav.jsp"></c:import>
<%

%>
<div class="content-wrapper">
    <div class="container-fluid">

        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="admin">Admin</a></li>
            <li class="breadcrumb-item"><a href="adminkeywords">Keywords</a></li>
        </ol>

        <div class="card mb-3">

            <div class="card-header d-flex flex-row align-items-center">
                <strong class="mr-auto">Keywords</strong>
                <a class="btn btn-info"
                   href="<c:url value="/adminkeywords?action=create" />">
                    <i class="far fa-plus-square" style="margin-right:8px"></i>
                    Create Keyword
                </a>
            </div>
            <div class="card-body">

                <div class="table-responsive">
                    <table class="table table-bordered table-striped" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Title</th>
                            <th>Slug</th>
                            <th>Actions</th>

                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="keyword" items="${keywords}">
                            <tr>
                                <td><c:out value="${keyword.id}" default="-" /></td>
                                <td><c:out value="${keyword.title}" default="-" /></td>
                                <td><c:out value="${keyword.slug}" default="-" /></td>
                                <td>
                                    <div class="btn-group">
                                        <a class="btn btn-primary" href="adminkeywords?action=show&id=${keyword.id}"><i class="fas fa-external-link-alt" style="margin-right:8px"></i>Show</a>
                                        <a class="btn btn-warning" href="adminkeywords?action=edit&id=${keyword.id}"><i class="far fa-edit" style="margin-right:8px"></i>Edit</a>
                                        <a class="btn btn-danger" href="adminkeywords?action=delete&id=${keyword.id}"><i class="fas fa-times" style="margin-right:8px"></i>Delete</a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
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