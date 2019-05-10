<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Admin | Home</title>
    <c:import url="/WEB-INF/views/styles.jsp"></c:import>
</head>
<body class="fixed-nav sticky-footer bg-dark" id="page-top"
      cz-shortcut-listen="true">

<!-- Navigation -->
<c:import url="/WEB-INF/views/nav.jsp"></c:import>

<%
    request.getAttribute("name");
%>

<div class="content-wrapper">
    <div class="container-fluid">

        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="admin">Admin</a></li>
            <li class="breadcrumb-item"><a href="adminclassifieds">Classifieds</a></li>
        </ol>
            <div class="card-header">
                <i class="fa fa-table"></i><b>Available Classifieds</b></div>
            <div class="card-body">

                <div class="table-responsive">
                    <table class="table table-bordered table-striped" id="dataTable" width="100%" cellspacing="0">
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>Title</th>
                            <th>Company</th>
                            <th>Category</th>
                            <th>Keywords</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${classifieds}" var="classifieds" varStatus="loop">
                            <tr>
                                <td><c:out value="${classifieds.classified.id}" /></td>
                                <td><c:out value="${classifieds.classified.title}" /></td>
                                <td><c:out value="${classifieds.classified.companyId}" /></td>
                                <td><c:out value="${categories[classifieds.classified.categoryId].title}" /></td>
                                <td>
                                    <c:forEach var="keyword" items="${classifieds.keywords}">
                                        <span class="badge badge-info">${keyword.title}</span>
                                    </c:forEach>
                                </td>

                                <td>
                                    <a class="btn btn-info" href="adminstudents?cl_id=${classifieds.classified.id}"><i class="fas fa-user" style="margin-right:8px"></i>Assign Job</a>
                                    <a class="btn btn-primary" href="adminclassifieds?id=${classifieds.classified.id}&action=show"><i class="fas fa-external-link-alt" style="margin-right:8px"></i>Show</a>
                                    <a class="btn btn-warning" href="adminclassifieds?id=${classifieds.classified.id}&action=edit"><i class="far fa-edit" style="margin-right:8px"></i>Edit</a>
                                    <a class="btn btn-danger" href="adminclassifieds?id=${classifieds.classified.id}&action=delete"><i class="fas fa-times" style="margin-right:8px"></i>Delete</a>
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