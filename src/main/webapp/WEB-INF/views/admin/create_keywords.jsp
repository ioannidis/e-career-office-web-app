<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin  |Keyword Creation</title>
    <c:import url="/WEB-INF/views/styles.jsp"></c:import>
</head>
<body class="bg-dark">
<div class="container">
    <div class="card card-register mx-auto mt-5">
        <div class="card-header">Admin Create Keyword</div>
        <div class="card-body">
            <form method="post" action="adminkeywords?action=save">
                <div class="form-group">
                    <label for="title">Title</label>
                    <input id="title" class="form-control" type="text" name="title" placeholder="Title" required />
                </div>
                <div class="form-group">
                    <label for="slug">Slug</label>
                    <input id="slug" class="form-control" type="text" name="slug" placeholder="Slug" required />
                </div>
                <br/>
                <input  type="submit" class="btn btn-primary btn-block" value="Create">
            </form>
            <div class="text-center">
                <a class="d-block small mt-3" href="adminkeywords">Cancel</a>
            </div>
        </div>
    </div>
</div>

<c:import url="/WEB-INF/views/scripts.jsp"></c:import>
</body>
</html>