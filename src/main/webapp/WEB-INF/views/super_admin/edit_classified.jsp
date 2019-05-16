<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | Edit Classified</title>
    <meta charset="UTF-8">
</head>
<body>
<h2>Edit: ${classified.title}</h2>
<p><a href="<c:url value="/manage_classifieds"/>"><< Back</a></p>
<hr>
<form action="<c:url value="/edit_classified?id=${classified.id}"/>" method="POST">
    <p>
        <strong><label for="title">Title</label></strong>
        <br>
        <input type="text" name="title" id="title" value="${classified.title}" required>
    </p>

    <p>
        <strong><label for="content">Content</label></strong>
        <br>
        <textarea name="content" id="content" cols="30" rows="10" required>${classified.content}</textarea>
    </p>

    <p>
        <strong><label for="company">Company</label></strong>
        <br>
        <select name="company" id="company">
            <c:forEach items="${companies}" var="company">
                <option value="<c:out value="${company.id}" />" ${company.id == classified.company.id ? "selected" : ""}>
                        ${company.title}
                </option>
            </c:forEach>
        </select>
    </p>

    <p>
        <strong><label for="category">Category</label></strong>
        <br>
        <select name="category" id="category">
            <c:forEach items="${categories}" var="category">
                <option value="${category.id}" ${category.id == classified.categoryId? "selected" : ""}>
                        ${category.title}
                </option>
            </c:forEach>
        </select>
    </p>

    <p>
        <strong>Keywords</strong>
        <br>
        <c:forEach items="${keywords}" var="keyword">
            <input type="checkbox" name="keywords" value="${keyword.id}" id="${keyword.slug}" ${fn:contains(selectedKeywords, keyword) ? 'checked' : ''}>
            <label for="${keyword.slug}">${keyword.title}</label>
            <br>
        </c:forEach>
    </p>

    <input type="submit" value="Save Changes">
</form>

</body>
</html>
