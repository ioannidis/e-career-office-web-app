<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student | Edit Information</title>
    <meta charset="UTF-8">
    <c:import url="/WEB-INF/views/styles.jsp"></c:import>
</head>
<body class="fixed-nav sticky-footer bg-dark" id="page-top"
      cz-shortcut-listen="true">

<!-- Navigation -->
<c:import url="/WEB-INF/views/nav.jsp"></c:import>
<div class="content-wrapper">
    <div class="container-fluid">

        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="<c:url value="/student"/>">Student</a></li>
        </ol>

        <div class="card mb-3">
            <div class="card-header">
                <i class="fas fa-user-edit"></i>Edit person details</div>
            <div class="card-body">
                <form method="post" action="<c:url value="/student_edit_details"/>" id="edit-student-details">
                    <div class="form-group">
                        <label for="firstname">First Name</label>
                        <input id="firstname" class="form-control" type="text" name="firstname" placeholder="First name" value="${user.getName()}" required />
                    </div>
                    <div class="form-group">
                        <label for="lastname">Last Name</label>
                        <input id="lastname" class="form-control" type="text" name="lastname" placeholder="Last name" value="${user.getSurname()}" required />
                    </div>
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input id="email" class="form-control" type="email" name="email" placeholder="email@gmail.com" value="${user.getEmail()}" required />
                    </div>
                    <div class="form-group">
                        <label for="phone">Phone</label>
                        <input id="phone" class="form-control" type="text" pattern="\d*" maxlength="10" name="phone" value="${user.getPhoneNumber()}" placeholder="Phone" />
                    </div>
                    <div class="form-group">
                        <label for="phone">Role</label>
                        <br/>
                        <c:if test="${ user.getRoleId() == 'u_student'}">
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="role" id="roleRadio1" value="u_student" checked>
                                <label class="form-check-label" for="roleRadio1">Undergraduate student</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="role" id="roleRadio2" value="p_student">
                                <label class="form-check-label" for="roleRadio2">Postgraduate student</label>
                            </div>
                        </c:if>
                        <c:if test="${ user.getRoleId() == 'p_student'}">
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="role" id="roleRadio1" value="u_student">
                                <label class="form-check-label" for="roleRadio1">Undergraduate student</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="role" id="roleRadio2" value="p_student" checked>
                                <label class="form-check-label" for="roleRadio2">Postgraduate student</label>
                            </div>
                        </c:if>
                    </div>
                </form>
            </div>
            <div class="card-footer flex-row align-items-center text-right">
                <a href="${ user.getRoleId()}" class="btn btn-warning"><i class="fas fa-caret-left" style="margin-right:8px"></i>Cancel</a>
                <button type="submit" class="btn btn-success" form="edit-student-details"><i class="far fa-save" style="margin-right:8px"></i>Update</button>
            </div>
        </div>

        <c:import url="/WEB-INF/views/footer.jsp"></c:import>
    </div>

    <c:import url="/WEB-INF/views/scripts.jsp"></c:import>


</body>
</html>
