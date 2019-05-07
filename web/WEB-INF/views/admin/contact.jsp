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
    request.getAttribute("id");
%>
<div class="content-wrapper">
    <div class="container-fluid">

        <body class="bg-dark">
        <div class="container">
            <div class="card card-register mx-auto mt-5">
                <div class="card-header">Contact Student</div>
                <div class="card-body">
                    <form method="post" action="admincontact?id=${id}">

                        <p>
                            <label for="classified">Job:</label>
                            <select class="form-control" name="classified" id="classified" required>
                                <c:forEach items="${ classifieds }" var="classified">
                                    <option value="<c:out value="${ classified.getClassified().title }"/>" >
                                        <c:out value="${ classified.getClassified().title} | ${ classified.getClassified().title}" />
                                    </option>
                                </c:forEach>
                            </select>
                        </p>
                        <div class="form-group">
                            <label for="comment">Message:</label>
                            <textarea class="form-control" rows="4" id="comment" required></textarea>
                        </div>
                        <input  type="submit" class="btn btn-info"  value="Contact Student">
                    </form>
                    <div class="text-center">
                        <a class="btn btn-danger" href="CareerOfficeApp/adminstudents"><i class="fas fa-times" style="margin-right:8px; align-self: center"></i>Cancel</a>
                    </div>
                </div>
    </div>
    <c:import url="/WEB-INF/views/footer.jsp"></c:import>
</div>

<c:import url="/WEB-INF/views/scripts.jsp"></c:import>
</body>
</html>