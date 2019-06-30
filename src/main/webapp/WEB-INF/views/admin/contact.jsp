<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Admin | Contact Student</title>
    <c:import url="/WEB-INF/views/styles.jsp"></c:import>
</head>
<body class="fixed-nav sticky-footer bg-dark" id="page-top"
      cz-shortcut-listen="true">

<!-- Navigation -->
<c:import url="/WEB-INF/views/nav.jsp"></c:import>
<%
    request.getAttribute("id");
%>

<div class="content-wrapper">
    <div class="container-fluid">
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="<c:url value="/admin"/>">
                    Admin
                </a>
            </li>
            <li class="breadcrumb-item active">
                Contact
            </li>
        </ol>
        <div class="card mb-3">
            <div class="card-header d-flex flex-row align-items-center">
                <i class="fa fa-tags mr-2"></i>
                <strong>Create Keyword</strong>
            </div>
            <div class="card-body">
                <form action="<c:url value="/admincontact?id=${id}"/>" method="POST" id="contact_student">
                    <div class="form-group">
                        <label for="classified"><strong>Job</strong></label>
                        <select class="form-control" name="classified" id="classified" required>
                            <c:forEach items="${ classifieds }" var="classified">
                                <option value="<c:out value="${ classified.getClassified().title }"/>" >
                                    <c:out value="${ classified.getClassified().title} | ${ classified.getClassified().title}" />
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="message">Message:</label>
                        <textarea class="form-control" rows="4" id="message" name="message" required></textarea>
                    </div>
                </form>
            </div>
            <div class="card-footer flex-row align-items-center text-right">
                <a href="adminstudents" class="btn btn-warning"><i class="fas fa-caret-left" style="margin-right:8px"></i>Cancel</a>
                <button type="submit" class="btn btn-success" form="contact_student"><i class="far fa-save" style="margin-right:8px"></i>Send</button>
            </div>
        </div>
        <c:import url="/WEB-INF/views/footer.jsp"></c:import>
    </div>
    <c:import url="/WEB-INF/views/scripts.jsp"></c:import>
</div>

</html>