<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Super Admin | View Classified</title>
    <meta charset="UTF-8">
</head>
<body>
<h2>View: ${classified.title}</h2>
<p>
    <a href="<c:url value="/edit_classified?id=${classified.id}"/>">Edit</a>
    /
    <a href="<c:url value="/delete_classified?id=${classified.id}"/>">Delete</a>
</p>
<p><a href="<c:url value="/manage_classifieds"/>"><< Back</a></p>
<hr>

<h4>ID</h4>
<p>${classified.id}</p>

<h4>Title</h4>
<p>${classified.title}</p>

<h4>Content</h4>
<p>${classified.content}</p>

<h4>Company</h4>
<p>${classified.company.title}</p>

<h4>Category</h4>
<p>${classified.category.title}</p>

<h4>Keywords</h4>
<p>
    <c:forEach items="${classified.keywordClassifiedPivotList}" var="rel" varStatus="status">
        ${rel.keyword.title}
        <c:if test="${!status.last}">,</c:if>
    </c:forEach>
</p>
</body>
</html>
