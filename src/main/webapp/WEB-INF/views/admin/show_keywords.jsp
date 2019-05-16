<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Classified | Show classified</title>
    <c:import url="/WEB-INF/views/styles.jsp"></c:import>
</head>
<body class="fixed-nav sticky-footer bg-dark" id="page-top" cz-shortcut-listen="true">

<!-- Navigation -->
<c:import url="/WEB-INF/views/nav.jsp"></c:import>


<div class="content-wrapper">
    <div class="container-fluid">

        <ol class="breadcrumb">
            <li class="breadcrumb-item text-primary"><a href="admin" >Admin</a></li>
            <li class="breadcrumb-item text-primary"><a href="adminkeywords" >Keywords</a></li>
            <li class="breadcrumb-item active"><c:out value="${keyword.title}" /></li>
        </ol>

        <div class="card mb-3">
            <div class="card-header">
                <i class="fa fa-table"></i><b>Keyword</b></div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered table-striped" id="dataTable" width="100%" cellspacing="0">
                        <tbody>
                        <tr>
                            <td><b>Title</b></td>
                            <td><c:out value="${keyword.title}" /></td>
                        </tr>
                        <tr>
                            <td><b>Slug</b></td>
                            <td><c:out value="${keyword.slug}" /></td>
                        </tr>
                        <tr>
                            <td><b>Actions</b></td>
                            <td>
                                <a class="btn btn-primary" href="adminkeywords" class="btn"><i class="fas fa-caret-left" style="margin-right:8px"></i>Back</a>
                                <a class="btn btn-warning" href="adminkeywords?action=edit&id=${keyword.id}" class="btn"><i class="far fa-edit" style="margin-right:8px"></i>Edit</a>
                                <a class="btn btn-danger" href="adminkeywords?action=delete&id=${keyword.id}" class="btn"><i class="fas fa-times" style="margin-right:8px"></i>Delete</a>
                            </td>
                        </tr>
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