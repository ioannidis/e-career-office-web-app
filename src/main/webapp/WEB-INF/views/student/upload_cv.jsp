<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Career Office Login</title>
    <c:import url="/WEB-INF/views/styles.jsp"></c:import>
</head>
<body class="bg-dark">
<div class="container">
    <div class="card card-register mx-auto mt-5">
        <div class="card-header">CV Details</div>
        <div class="card-body">
            <form method="post" action="upload_cv" enctype="multipart/form-data">
                <div class="form-check">
                    <c:forEach items="${keywords}" var="item">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" name="keywords" type="checkbox" id="${item.getTitle()}" value="${item.getTitle()}">
                            <label class="form-check-label" for="${item.getTitle()}">${item.getTitle()}</label>
                        </div>
                    </c:forEach>
                </div>
                <div class="form-group">
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text">Upload</span>
                        </div>
                        <div class="custom-file">
                            <label for="file" class="custom-file-label">Choose your cv</label>
                            <input name="file" id="file" type="file" size="60" accept="application/pdf" class="form-control custom-file-input" >
                        </div>
                    </div>
                </div>
                <br/>
                <input type="submit" class="btn btn-primary btn-block" value="Upload CV">
            </form>
            <div class="text-center">
                <a class="d-block small mt-3" href="<c:url value="/student"/>">Back to home</a>
            </div>
        </div>
        <%-- Error logging--%>
        <div>
            <c:if test="${keywordsError != null}">
                <div class="alert alert-danger" role="alert">
                    Please choose some keywords!
                </div>
                <c:remove var="keywordsError" scope="session" />
            </c:if>
            <c:if test="${cvError != null}">
                <div class="alert alert-danger" role="alert">
                    You must upload your CV in pdf format!
                </div>
                <c:remove var="cvError" scope="session" />
            </c:if>
        </div>
    </div>
</div>
<c:import url="/WEB-INF/views/scripts.jsp"></c:import>
</body>
</html>