<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Classified | Create classified</title>
    <c:import url="/WEB-INF/views/styles.jsp"></c:import>
</head>
<body class="fixed-nav sticky-footer bg-dark" id="page-top" cz-shortcut-listen="true">

<!-- Navigation -->
<c:import url="/WEB-INF/views/nav.jsp"></c:import>


<div class="content-wrapper">
    <div class="container-fluid">

        <ol class="breadcrumb">
            <li class="breadcrumb-item text-primary"><a href="external" >External</a></li>
            <li class="breadcrumb-item text-primary"><a href="externalclassifieds" >Classifieds</a></li>
            <li class="breadcrumb-item active">Create</li>
        </ol>

        <div class="card mb-3">
            <div class="card-header">
                <i class="fa fa-table"></i><b>Create classified</b></div>
            <div class="card-body">
                <form action="externalclassifieds?action=save" method="POST">
                    <div class="form-group">
                        <label for="title"><strong>Title</strong></label>
                        <input type="text" class="form-control"  name="title" id="title" value="" required />
                    </div>
                    <div class="form-group">
                        <label for="content"><strong>Content</strong></label>
                        <textarea type="number" class="form-control"  name="content" id="content" required></textarea>
                    </div>
                    <div class="form-group">
                        <label for="companyId"><strong>Company</strong></label>
                        <input type="text" class="form-control"  name="companyId" id="companyId" value="" required />
                    </div>
                    <div class="form-group">
                        <label for="categoryId"><strong>Categoty</strong></label>
                        <select class="form-control" name="categoryId" id="categoryId" required>
                        <c:forEach items="${ categories }" var="category">
                            <option value="<c:out value="${ category.id }"/>" >
                            <c:out value="${ category.title}" />
                            </option>
                        </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label><strong>Keywords</strong></label>
                        <c:forEach var="keyword" items="${allKeywords}">
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="checkbox" id="${keyword.slug}}" value="${keyword.id}">
                                <label class="form-check-label" for="${keyword.slug}">${keyword.title}</label>
                            </div>
                        </c:forEach>
                    </div>
                    <br />
                    <a href="externalclassifieds" class="btn btn-warning"><i class="fas fa-caret-left" style="margin-right:8px"></i>Cancel</a>
                    <button type="submit" class="btn btn-success"><i class="far fa-save" style="margin-right:8px"></i>Save</button>
                </form>
            </div>
        </div>
    </div>
    <c:import url="/WEB-INF/views/footer.jsp"></c:import>
</div>

<c:import url="/WEB-INF/views/scripts.jsp"></c:import>
</body>
</html>
