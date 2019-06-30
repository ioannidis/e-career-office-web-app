<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>External | Home</title>
<c:import url="/WEB-INF/views/styles.jsp"></c:import>
</head>
<body class="fixed-nav sticky-footer bg-dark" id="page-top"
	cz-shortcut-listen="true">

	<!-- Navigation -->
	<c:import url="/WEB-INF/views/nav.jsp"></c:import>

	<div class="content-wrapper">
		<div class="container-fluid">

			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="external">External</a></li>
			</ol>

			<div class="card mb-3">
				<div class="card-header">
					<i class="fa fa-user-circle"></i><b>Edit user details</b>
				</div>
				<div class="card-body">
					<form method="post" action="external?action=update" id="edit_external_form">
						<div class="form-group">
							<label for="username">Username</label>
							<input id="username" class="form-control" type="text" name="username" placeholder="Username" value="${user.username}" readonly />
						</div>
						<div class="form-group">
							<label for="firstname">First Name</label>
							<input id="firstname" class="form-control" type="text" name="firstname" placeholder="First name" value="${user.name}" required />
						</div>
						<div class="form-group">
							<label for="lastname">Last Name</label>
							<input id="lastname" class="form-control" type="text" name="lastname" placeholder="Last name" value="${user.surname}" required />
						</div>
						<div class="form-group">
							<label for="email">Email</label>
							<input id="email" class="form-control" type="email" name="email" placeholder="email@gmail.com" value="${user.email}" required />
						</div>
						<div class="form-group">
							<label for="phone">Phone</label>
							<input id="phone" class="form-control" type="text" pattern="\d*" maxlength="10" name="phone" value="${user.phoneNumber}" placeholder="Phone" />
						</div>
						<div class="form-group">
							<label for="companyId"><strong>Company</strong></label>
							<select class="form-control" name="companyId" id="companyId" required>
								<c:forEach items="${ companies }" var="company">
									<option value="<c:out value="${ company.id }"/>" <c:if test="${company.id == userCompany.companyId}">selected</c:if> >
										<c:out value="${ company.title}" />
									</option>
								</c:forEach>
							</select>
						</div>
					</form>
				</div>
				<div class="card-footer flex-row align-items-center text-right">
					<a href="external" class="btn btn-warning"><i class="fas fa-caret-left" style="margin-right:8px"></i>Cancel</a>
					<button type="submit" class="btn btn-success" form="edit_external_form"><i class="far fa-save" style="margin-right:8px"></i>Update</button>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/footer.jsp"></c:import>
	</div>

	<c:import url="/WEB-INF/views/scripts.jsp"></c:import>
</body>
</html>