<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | Edit User</title>
    <meta charset="UTF-8">
    <c:import url="/WEB-INF/views/styles.jsp"></c:import>
</head>
<body class="fixed-nav sticky-footer bg-dark">
<c:import url="/WEB-INF/views/nav.jsp"></c:import>

<div class="content-wrapper">
    <div class="container-fluid">
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="<c:url value="/super_admin"/>">
                    Super Admin
                </a>
            </li>
            <li class="breadcrumb-item">
                <a href="<c:url value="/manage_users"/>">
                    Users
                </a>
            </li>
            <li class="breadcrumb-item active">
                Edit User
            </li>
        </ol>
        <div class="card mb-3">
            <div class="card-header d-flex flex-row align-items-center">
                <i class="fa fa-users-cog mr-2"></i>
                <strong>Edit User</strong>
            </div>
            <div class="card-body">
                <form action="<c:url value="/edit_user?id=${user.username}"/>" method="POST" id="edit_user_form">
                    <div class="form-row">
                        <div class="form-group col-3">
                            <strong><label for="first_name">First Name</label></strong>
                            <input class="form-control"
                                   type="text"
                                   name="first_name"
                                   id="first_name"
                                   value="${user.name}" required>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-3">
                            <strong><label for="last_name">Last Name</label></strong>
                            <input class="form-control"
                                   type="text"
                                   name="last_name"
                                   id="last_name"
                                   value="${user.surname}" required>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-3">
                            <strong><label for="phone_number">Phone Number</label></strong>
                            <input class="form-control"
                                   type="text"
                                   name="phone_number"
                                   id="phone_number"
                                   value="${user.phoneNumber}" required>
                        </div>
                    </div>


                    <div class="form-row">
                        <div class="form-group col-3">
                            <strong><label for="email">Email</label></strong>
                            <input class="form-control"
                                   type="email"
                                   name="email"
                                   id="email"
                                   value="${user.email}" required>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-3">
                            <strong><label for="company">Company</label></strong>
                            <select class="form-control" name="company" id="company">
                                <option value="nothing" ${user.userCompany == null ? "selected" : ""}>-</option>

                                <c:forEach items="${companies}" var="company">

                                    <option value="<c:out value="${company.id}" />"
                                        ${company.id == user.userCompany.companyId ? "selected" : ""}>
                                            ${company.title}
                                    </option>

                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-3">
                            <strong><label for="department">Department</label></strong>
                            <select class="form-control" name="department" id="department">
                                <option value="nothing" ${user.userDepartment == null ? "selected" : ""}>-</option>

                                <c:forEach items="${departments}" var="department">

                                    <option value="${department.id}"
                                        ${department.id == user.userDepartment.departmentId ? "selected" : ""}>
                                            ${department.title}
                                    </option>

                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="card-footer d-flex flex-row align-items-center justify-content-end">
                <button class="btn btn-primary d-flex flex-row align-items-center mr-3" form="edit_user_form">
                    <i class="fa fa-save mr-2"></i>
                    Save Changes
                </button>
                <a class="btn btn-danger" href="<c:url value="/manage_users"/>">
                    Cancel
                </a>
            </div>
        </div>
        <c:import url="/WEB-INF/views/footer.jsp"></c:import>
    </div>
</div>
<c:import url="/WEB-INF/views/scripts.jsp"></c:import>

</body>
</html>
