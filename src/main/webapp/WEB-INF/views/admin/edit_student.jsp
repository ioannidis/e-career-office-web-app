<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Classified | Edit Student</title>
    <c:import url="/WEB-INF/views/styles.jsp"></c:import>
</head>
<body class="fixed-nav sticky-footer bg-dark" id="page-top" cz-shortcut-listen="true">

<!-- Navigation -->
<c:import url="/WEB-INF/views/nav.jsp"></c:import>


<div class="content-wrapper">
    <div class="container-fluid">

        <ol class="breadcrumb">
            <li class="breadcrumb-item text-primary"><a href="admin" >Admin</a></li>
            <li class="breadcrumb-item text-primary"><a href="adminstudents" >Students</a></li>
            <li class="breadcrumb-item text-primary"><c:out value="${student.username}" /></li>
            <li class="breadcrumb-item active">Edit</li>
        </ol>

        <div class="card mb-3">
            <div class="card-header">
                <i class="fa fa-table"></i><b>Edit Student</b></div>
            <div class="card-body">
                <form action="adminstudents?name=${student.username}&action=update" method="POST">
                    <div class="form-group">
                        <label for="username"><strong>Username</strong></label>
                        <input type="text" class="form-control"  name="username" id="username" value="${student.username}" disabled/>
                    </div>
                    <div class="form-group">
                        <label for="first_name"><strong>Name</strong></label>
                        <input type="text" class="form-control"  name="first_name" id="first_name" value="${student.name}" required />
                    </div>
                    <div class="form-group">
                        <label for="last_name"><strong>Surname</strong></label>
                        <input type="text" class="form-control"  name="last_name" id="last_name" value="${student.surname}" required />
                    </div>
                    <div class="form-group">
                        <label for="phone_number"><strong>Phone Number</strong></label>
                        <textarea type="number" class="form-control"  name="phone_number" id="phone_number" required><c:out value="${student.phoneNumber}"></c:out></textarea>
                    </div>
                    <div class="form-group">
                        <label for="email"><strong>Email Adress</strong></label>
                        <input type="text" class="form-control"  name="email" id="email" value="${student.email}" readonly />
                    </div>
                    <div class="form-group">
                        <label for="role"><strong>Role</strong></label>
                        <input type="text" class="form-control"  name="role" id="role" value="${student.role.title}" required />
                    </div>
                    <div class="form-group">
                        <label><strong>Keywords</strong></label>
                        <c:forEach var="keyword" items="${allKeywords}">
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="checkbox" id="${keyword.slug}}" name="keywordIds" value="${keyword.id}" <c:if test="${keyword.id == selectedKeywords[keyword.id].id}">checked</c:if>>
                                <label class="form-check-label" for="${keyword.slug}">${keyword.title}</label>
                            </div>
                        </c:forEach>
                    </div>
                    <br />
                    <a href="adminstudents" class="btn btn-warning"><i class="fas fa-caret-left" style="margin-right:8px"></i>Cancel</a>
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