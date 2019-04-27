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
		<div class="card card-login mx-auto mt-5">
			<div class="card-header">Login</div>
	        <div class="card-body">
				<form method="post" action="login">
					<div class="form-group">
						<label for="username">Username</label>
						<input id="username" class="form-control" type="text" name="username" placeholder="Username" />
					</div>
					<div class="form-group">
						<label for="password">Password</label>
						<input id="password" class="form-control" type="password" name="password" placeholder="Password" />
					</div>
					<br/>
					<input  type="submit" class="btn btn-primary btn-block" value="Log in">
				</form>
				<div class="text-center">
					<a class="d-block small mt-3" href="<c:url value="/registration"/>">Create an Account</a>
				</div>
      		</div>
    	</div>
  </div>

	<c:import url="/WEB-INF/views/scripts.jsp"></c:import>
</body>
</html>