<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | Edit Classified</title>
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
                <a href="<c:url value="/manage_classifieds"/>">
                    Classifieds
                </a>
            </li>
            <li class="breadcrumb-item active">
                Edit Classified
            </li>
        </ol>
        <div class="card mb-3">
            <div class="card-header d-flex flex-row align-items-center">
                <i class="fa fa-address-card mr-2"></i>
                <strong>Edit Classified</strong>
            </div>
            <div class="card-body">
                <form action="<c:url value="/edit_classified?id=${classified.id}"/>" method="POST" id="edit_classified_form">
                    <div class="form-row">
                        <div class="form-group col-3">
                            <strong><label for="title">Title</label></strong>
                            <input class="form-control"
                                   type="text"
                                   name="title"
                                   id="title"
                                   value="${classified.title}" required>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-3">
                            <strong><label for="content">Content</label></strong>
                            <textarea class="form-control"
                                      name="content"
                                      id="content"
                                      cols="30"
                                      rows="10" required>${classified.content}</textarea>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-3">
                            <strong><label for="company">Company</label></strong>
                            <select class="form-control" name="company" id="company">
                                <c:forEach items="${companies}" var="company">
                                    <option value="<c:out value="${company.id}" />"
                                        ${company.id == classified.company.id ? "selected" : ""}>
                                            ${company.title}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-3">
                            <strong><label for="category">Category</label></strong>
                            <select class="form-control" name="category" id="category">
                                <c:forEach items="${categories}" var="category">
                                    <option value="${category.id}"
                                        ${category.id == classified.categoryId? "selected" : ""}>
                                            ${category.title}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-3">
                            <strong>Keywords</strong>
                            <c:forEach items="${keywords}" var="keyword">
                                <div class="form-check">
                                    <input class="form-check-input"
                                           type="checkbox"
                                           name="keywords"
                                           value="${keyword.id}"
                                           id="${keyword.slug}"
                                        ${fn:contains(selectedKeywords, keyword) ? 'checked' : ''}>
                                    <label class="form-check-label" for="${keyword.slug}">${keyword.title}</label>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </form>
            </div>
            <div class="card-footer d-flex flex-row align-items-center justify-content-end">
                <button class="btn btn-primary d-flex flex-row align-items-center mr-3" form="edit_classified_form">
                    <i class="fa fa-save mr-2"></i>
                    Save Changes
                </button>
                <a class="btn btn-danger" href="<c:url value="/manage_classifieds"/>">
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
