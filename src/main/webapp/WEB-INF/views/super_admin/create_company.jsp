<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | Create Company</title>
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
                Create Company
            </li>
        </ol>
        <div class="card mb-3">
            <div class="card-header d-flex flex-row align-items-center">
                <i class="fa fa-briefcase mr-2"></i>
                <strong>Create Company</strong>
            </div>
            <div class="card-body">
                <form action="<c:url value="/create_company"/>" method="POST" id="create_company_form">
                    <div class="form-group">
                        <strong><label for="company_id">ID</label></strong>
                        <input class="form-control" type="text" name="company_id" id="company_id" required>
                    </div>
                    <div class="form-group">
                        <strong><label for="title">Title</label></strong>
                        <input class="form-control" type="text" name="title" id="title" required>
                    </div>
                    <div class="form-group">
                        <strong><label for="address">Address</label></strong>
                        <input class="form-control" type="text" name="address" id="address" required>
                    </div>
                    <div class="form-group">
                        <strong><label for="phone_number">Phone Number</label></strong>
                        <input class="form-control" type="text" name="phone_number" id="phone_number" required>
                    </div>
                    <div class="form-group">
                        <strong><label for="email">Email</label></strong>
                        <input class="form-control" type="email" name="email" id="email" required>
                    </div>
                    <div class="form-group">
                        <strong><label for="website">Website</label></strong>
                        <input class="form-control" type="text" name="website" id="website" required>
                    </div>
                </form>
            </div>
            <div class="card-footer flex-row align-items-center text-right">
                <a href="/manage_companies" class="btn btn-warning"><i class="fas fa-caret-left" style="margin-right:8px"></i>Cancel</a>
                <button type="submit" class="btn btn-success" form="create_company_form"><i class="far fa-save" style="margin-right:8px"></i>Save</button>
            </div>
        </div>
        <c:import url="/WEB-INF/views/footer.jsp"></c:import>
    </div>
    <c:import url="/WEB-INF/views/scripts.jsp"></c:import>

</body>
</html>
