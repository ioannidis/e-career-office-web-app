<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | Edit Company</title>
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
                <a href="<c:url value="/manage_companies"/>">
                    Companies
                </a>
            </li>
            <li class="breadcrumb-item active">
                Edit Company
            </li>
        </ol>
        <div class="card mb-3">
            <div class="card-header d-flex flex-row align-items-center">
                <i class="fa fa-briefcase mr-2"></i>
                <strong>Edit Company</strong>
            </div>
            <div class="card-body">
                <form action="<c:url value="/edit_company?id=${company.id}"/>" method="POST" id="edit_company_form">
                    <div class="form-row">
                        <div class="form-group col-3">
                            <strong><label for="title">Title</label></strong>
                            <input class="form-control" type="text" name="title" id="title" value="${company.title}" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-3">
                            <strong><label for="address">Address</label></strong>
                            <input class="form-control" type="text" name="address" id="address" value="${company.address}" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-3">
                            <strong><label for="phone_number">Phone Number</label></strong>
                            <input class="form-control" type="text" name="phone_number" id="phone_number" value="${company.phoneNumber}"
                                   required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-3">
                            <strong><label for="email">Email</label></strong>
                            <input class="form-control" type="email" name="email" id="email" value="${company.email}" required>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-3">
                            <strong><label for="website">Website</label></strong>
                            <input class="form-control" type="text" name="website" id="website" value="${company.website}" required>
                        </div>
                    </div>
                </form>
            </div>
            <div class="card-footer d-flex flex-row align-items-center justify-content-end">
                <button class="btn btn-primary d-flex flex-row align-items-center mr-3" form="edit_company_form">
                    <i class="fa fa-save mr-2"></i>
                    Save Changes
                </button>
                <a class="btn btn-danger" href="<c:url value="/manage_companies"/>">
                    Cancel
                </a>
            </div>
        </div>
        <c:import url="/WEB-INF/views/footer.jsp"></c:import>
    </div>
    <c:import url="/WEB-INF/views/scripts.jsp"></c:import>
</div>
</body>
</html>
