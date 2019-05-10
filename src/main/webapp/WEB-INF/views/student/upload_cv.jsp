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
                        <c:forEach items="${keywords}" var="item">
                            <option value="${item.getTitle()}">${item.getTitle()}</option>
                        </c:forEach>
                    </select>
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
                <a class="d-block small mt-3" href="<c:url value="/student"/>">Back to home</a>
            </div>
        </div>
    </div>
</div>
<c:import url="/WEB-INF/views/scripts.jsp"></c:import>
</body>
</html>