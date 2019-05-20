<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student | Manage your CV Details</title>
    <meta charset="UTF-8">
    <c:import url="/WEB-INF/views/styles.jsp"></c:import>
</head>
<body class="fixed-nav sticky-footer bg-dark" id="page-top"
      cz-shortcut-listen="true">
<!-- Navigation -->
<c:import url="/WEB-INF/views/nav.jsp"></c:import>
<div class="content-wrapper">
    <div class="container-fluid">

        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="<c:url value="/student"/>">Student</a></li>
        </ol>
        <form method="post" action="upload_cv" enctype="multipart/form-data">
            <div class="card mb-3">
                <div class="card-header">
                    <i class="fas fa-key"></i><b>Step 1: Choose keywords</b>
                </div>
                <div class="card-body">
                    <c:forEach items="${keywords}" var="item">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" name="keywords" type="checkbox" id="${item.getTitle()}" value="${item.getTitle()}">
                            <label class="form-check-label" for="${item.getTitle()}">${item.getTitle()}</label>
                        </div>
                    </c:forEach>
                    <c:forEach items="${pivotTable}" var="item">
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" name="keywords" type="checkbox" id="${item.getTitle()}" value="${item.getTitle()}"
                                   <c:if test="${item.isChecked()}">checked</c:if> >
                            <label class="form-check-label" for="${item.getTitle()}">${item.getTitle()}</label>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <div class="card mb-3">
                <div class="card-header">
                    <i class="fas fa-file-pdf"></i></i><b>Step 2: Upload your CV</b>
                </div>
                <div class="card-body">
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
                </div>
            </div>
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
            <input type="submit" class="btn btn-primary btn-block" value="Upload CV">
        </form>
    </div>
</div>
<c:import url="/WEB-INF/views/footer.jsp"></c:import>
<c:import url="/WEB-INF/views/scripts.jsp"></c:import>
</body>
</html>
