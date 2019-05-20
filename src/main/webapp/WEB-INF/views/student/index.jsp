<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student | Home</title>
    <meta charset="UTF-8">
    <c:import url="/WEB-INF/views/styles.jsp"></c:import>
</head>
<body class="fixed-nav sticky-footer bg-dark" id="page-top"
      cz-shortcut-listen="true">
<%-- In case of null cv throw error - modal --%>
<c:if test="${noCv}">
    <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle">Not enough information provided!</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="alert alert-danger" role="alert">
                        Please make sure to complete your CV information by providing your CV and keywords first.
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
</c:if>
<!-- Navigation -->
<c:import url="/WEB-INF/views/nav.jsp"></c:import>
<div class="content-wrapper">
    <div class="container-fluid">

        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="<c:url value="/student"/>">Student</a></li>
        </ol>

        <div class="card mb-3">
            <div class="card-header">
                <i class="fa fa-user-circle"></i><b>Student Information</b>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered table-striped" id="dataTable"
                           width="100%" cellspacing="0">
                        <tbody>
                        <tr>
                            <td><b>Username</b></td>
                            <td><c:out value="${user.username}" /></td>
                        </tr>
                        <tr>
                            <td><b>Name</b></td>
                            <td><c:out value="${user.name}" /></td>
                        </tr>
                        <tr>
                            <td><b>Surname</b></td>
                            <td><c:out value="${user.surname}" /></td>
                        </tr>
                        <tr>
                            <td><b>Phone</b></td>
                            <td><c:out value="${user.phoneNumber}" /></td>
                        </tr>
                        <tr>
                            <td><b>Email</b></td>
                            <td><c:out value="${user.email}" /></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div class="card mb-3">
            <div class="card-header">
                <i class="fas fa-user-md"></i><b>Your Keywords</b>
            </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-bordered table-striped" id="keywordsTable"
                           width="100%" cellspacing="0">
                        <tbody>
                        <tr>
                            <td><b>Keywords</b></td>
                            <td>
                                <c:forEach items="${keywords}" var="item">
                                    <span class="badge badge-danger">${item.getTitle()}</span>
                                </c:forEach>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="alert alert-primary" role="alert">
                    Make sure you use the right keywords. This is how recruiters will find you!
                </div>
            </div>
        </div>
        <c:import url="/WEB-INF/views/footer.jsp"></c:import>
    </div>

    <c:import url="/WEB-INF/views/scripts.jsp"></c:import>

    <c:if test="${noCv}">
    <script type="text/javascript">
        $(document).ready(function(){
            $('.modal').modal('show');
        });
    </script>
    </c:if>
</body>
</html>
