<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Career Office Login</title>
    <c:import url="/WEB-INF/views/styles.jsp"></c:import>
    <style>
        /*
 * bootstrap-tagsinput v0.8.0
 *
 */

        .bootstrap-tagsinput {
            background-color: #fff;
            border: 1px solid #ccc;
            box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.075);
            display: block;
            padding: 4px 6px;
            color: #555;
            vertical-align: middle;
            border-radius: 4px;
            max-width: 100%;
            line-height: 22px;
            cursor: text;
        }
        .bootstrap-tagsinput input {
            border: none;
            box-shadow: none;
            outline: none;
            background-color: transparent;
            padding: 0 6px;
            margin: 0;
            width: auto;
            max-width: inherit;
        }
        .bootstrap-tagsinput.form-control input::-moz-placeholder {
            color: #777;
            opacity: 1;
        }
        .bootstrap-tagsinput.form-control input:-ms-input-placeholder {
            color: #777;
        }
        .bootstrap-tagsinput.form-control input::-webkit-input-placeholder {
            color: #777;
        }
        .bootstrap-tagsinput input:focus {
            border: none;
            box-shadow: none;
        }
        .bootstrap-tagsinput .tag {
            margin-right: 2px;
            color: white;
            background: #007bff;
        }
        .bootstrap-tagsinput .tag [data-role="remove"] {
            margin-left: 8px;
            cursor: pointer;
        }
        .bootstrap-tagsinput .tag [data-role="remove"]:after {
            content: "x";
            padding: 0px 2px;
        }
        .bootstrap-tagsinput .tag [data-role="remove"]:hover {
            box-shadow: inset 0 1px 0 rgba(255, 255, 255, 0.2), 0 1px 2px rgba(0, 0, 0, 0.05);
        }
        .bootstrap-tagsinput .tag [data-role="remove"]:hover:active {
            box-shadow: inset 0 3px 5px rgba(0, 0, 0, 0.125);
        }

    </style>
</head>
<body class="bg-dark">
<div class="container">
    <div class="card card-register mx-auto mt-5">
        <div class="card-header">Job Details</div>
        <div class="card-body">
            <form method="post" action="upload_cv" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="keywords">Keywords</label>
                    <select name="keywords" id="keywords" class="form-control" multiple data-role="tagsinput">
                        <option value="Machine Learning">Machine Learning</option>
                        <option value="Python">Python</option>
                        <option value="Java">Java</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="availability">Availability</label>
                    <br/>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" id="availability" type="radio" name="full_time" id="full_time" value="full_time">
                        <label class="form-check-label" for="full_time">Full Time</label>
                    </div>
                    <div class="form-check form-check-inline">
                        <input class="form-check-input" type="radio" name="part_time" id="part_time" value="part_time">
                        <label class="form-check-label" for="part_time">Part Time</label>
                    </div>
                </div>
                <div class="form-group">
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text">Upload</span>
                        </div>
                        <div class="custom-file">
                            <label for="file" class="custom-file-label">Choose your cv</label>
                            <input name="file" id="file" type="file" size="60" class="form-control custom-file-input" >
                        </div>
                    </div>
                </div>
                <br/>
                <input type="submit" class="btn btn-primary btn-block" value="Upload CV">
            </form>
            <div class="text-center">
                <a class="d-block small mt-3" href="/CareerOfficeApp/student">Back to home</a>
            </div>
        </div>
        <%-- Error logging--%>
<%--        <div>--%>
<%--            <c:if test="${usernameError != null}">--%>
<%--                <div class="alert alert-danger" role="alert">--%>
<%--                    <c:out value="${usernameError}"/>--%>
<%--                </div>--%>
<%--                <c:remove var="usernameError" scope="session"/>--%>
<%--            </c:if>--%>
<%--            <c:if test="${emailError != null}">--%>
<%--                <div class="alert alert-danger" role="alert">--%>
<%--                    <c:out value="${emailError}"/>--%>
<%--                </div>--%>
<%--                <c:remove var="emailError" scope="session"/>--%>
<%--            </c:if>--%>
<%--        </div>--%>
    </div>
</div>
<c:import url="/WEB-INF/views/scripts.jsp"></c:import>
</body>
</html>