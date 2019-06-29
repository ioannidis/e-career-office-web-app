<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Classified | Edit Keyword</title>
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
            <li class="breadcrumb-item text-primary"><c:out value="${keyword.title}" /></li>
            <li class="breadcrumb-item active">Edit</li>
        </ol>

        <div class="card mb-3">
            <div class="card-header">
                <i class="fa fa-table"></i><b>Edit Keyword</b></div>
            <div class="card-body">
                <form action="adminkeywords?action=update&id=${keyword.id}" method="POST">
                    <div class="form-group">
                        <label for="title"><strong>Title</strong></label>
                        <input type="title" class="form-control"  name="title" id="title" value="${keyword.title}" required/>
                    </div>
                    <div class="form-group">
                        <label for="slug"><strong>Slug</strong></label>
                        <input type="text" class="form-control"  name="slug" id="slug" value="${keyword.slug}" readonly />
                    </div>
                    <a href="adminkeywords" class="btn btn-warning"><i class="fas fa-caret-left" style="margin-right:8px"></i>Cancel</a>
                    <button type="submit" class="btn btn-success"><i class="far fa-save" style="margin-right:8px"></i>Update</button>
                </form>
            </div>
        </div>
    </div>
    <c:import url="/WEB-INF/views/footer.jsp"></c:import>
</div>

<c:import url="/WEB-INF/views/scripts.jsp"></c:import>
</body>
</html>