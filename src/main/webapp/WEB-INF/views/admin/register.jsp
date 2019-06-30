<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Admin | Create User</title>
    <meta charset="UTF-8">
    <c:import url="/WEB-INF/views/styles.jsp"></c:import>
</head>
<body class="fixed-nav sticky-footer bg-dark">
<c:import url="/WEB-INF/views/nav.jsp"></c:import>

<div class="content-wrapper">
    <div class="container-fluid">
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="<c:url value="/admin"/>">
                    Admin
                </a>
            </li>
            <li class="breadcrumb-item">
                <a href="<c:url value="/adminstudents"/>">
                    Students
                </a>
            </li>
            <li class="breadcrumb-item active">
                Create User
            </li>
        </ol>

        <div class="card mb-3">
            <div class="card-header d-flex flex-row align-items-center">
                <i class="fa fa-users-cog mr-2"></i>
                <strong>Create User</strong>
            </div>
            <div class="card-body">

                <div>
                    <c:if test="${usernameError != null}">
                        <div class="alert alert-danger" role="alert">
                            <c:out value="${usernameError}" />
                        </div>
                        <c:remove var="usernameError" scope="session" />
                    </c:if>
                    <c:if test="${emailError != null}">
                        <div class="alert alert-danger" role="alert">
                            <c:out value="${emailError}" />
                        </div>
                        <c:remove var="emailError" scope="session" />
                    </c:if>
                </div>

                <form method="post" action="adminregister" id="create_user_form">
                    <div class="form-group">
                        <label for="username">Username</label>
                        <input id="username" class="form-control" type="text" name="username" placeholder="Username" required />
                    </div>
                    <div class="form-group">
                        <label for="password">Password</label>
                        <input id="password" class="form-control" type="password" name="password" placeholder="Password" required />
                    </div>
                    <div class="form-group">
                        <label for="firstname">First Name</label>
                        <input id="firstname" class="form-control" type="text" name="firstname" placeholder="First name" required />
                    </div>
                    <div class="form-group">
                        <label for="lastname">Last Name</label>
                        <input id="lastname" class="form-control" type="text" name="lastname" placeholder="Last name" required />
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input id="email" class="form-control" type="email" name="email" placeholder="email@gmail.com" required />
                    </div>
                    <div class="form-group">
                        <label for="phone">Phone</label>
                        <input id="phone" class="form-control" type="text" pattern="\d*" maxlength="10" name="phone" placeholder="Phone" />
                    </div>
                    <div class="form-group">
                        <label for="phone">Role</label>
                        <br/>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="role" id="roleRadio1" value="u_student" checked>
                            <label class="form-check-label" for="roleRadio1">Undergraduate student</label>
                        </div>
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="role" id="roleRadio2" value="p_student">
                            <label class="form-check-label" for="roleRadio2">Postgraduate student</label>
                        </div>
                    </div>
                    <div class="form-group department">
                        <label for="department">Department</label>
                        <select class="form-control" name="department" id="department" required>
                            <c:forEach items="${ departments }" var="department">
                                <option value="<c:out value="${ department.id }"/>" >
                                    <c:out value="${ department.title}" />
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </form>
            </div>
            <div class="card-footer flex-row align-items-center text-right">
                <a href="adminstudents" class="btn btn-warning"><i class="fas fa-caret-left" style="margin-right:8px"></i>Cancel</a>
                <button type="submit" class="btn btn-success" form="create_user_form"><i class="far fa-save" style="margin-right:8px"></i>Save</button>
            </div>

        </div>
    </div>
    <c:import url="/WEB-INF/views/footer.jsp"></c:import>
</div>
<c:import url="/WEB-INF/views/scripts.jsp"></c:import>
</body>
</html>
