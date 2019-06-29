<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Career Office Login</title>
	<c:import url="/WEB-INF/views/styles.jsp"></c:import>
</head>
<body class="bg-dark">
 	<div class="container">
		<h2 class="text-center mt-5" style="color:#fff">Career Office App</h2>
		<div class="card card-register mx-auto mt-5">
			<div class="card-header">Registration</div>
	        <div class="card-body">
				<form method="post" action="registration">
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
								<input class="form-check-input" type="radio" name="role" id="roleRadio1" value="u_student">
								<label class="form-check-label" for="roleRadio1">Undergraduate student</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="role" id="roleRadio2" value="p_student">
								<label class="form-check-label" for="roleRadio2">Postgraduate student</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio" name="role" id="roleRadio3" value="external">
								<label class="form-check-label" for="roleRadio3">External</label>
							</div>
						<div class="form-group company" style="display: none">
							<label for="company">Company</label>
							<select class="form-control" name="company" id="company" required>
								<c:forEach items="${ companies }" var="company">
									<option value="<c:out value="${ company.id }"/>" >
										<c:out value="${ company.title}" />
									</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group department" style="display: none">
							<label for="department">Department</label>
							<select class="form-control" name="department" id="department" required>
								<c:forEach items="${ departments }" var="department">
									<option value="<c:out value="${ department.id }"/>" >
										<c:out value="${ department.title}" />
									</option>
								</c:forEach>
							</select>
						</div>
					</div>
					<br/>
					<input  type="submit" class="btn btn-primary btn-block" value="Register">
				</form>
				<div class="text-center">
					<a class="d-block small mt-3" href="/CareerOfficeApp/login">Back to login</a>
				</div>
      		</div>
			<%-- Error logging--%>
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
    	</div>
  </div>

	<c:import url="/WEB-INF/views/scripts.jsp"></c:import>

</body>
</html>