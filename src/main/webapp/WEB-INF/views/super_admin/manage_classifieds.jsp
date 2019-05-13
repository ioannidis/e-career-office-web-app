<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Classifieds - Super Admin</title>
    <meta charset="UTF-8">
</head>
<body>
<h2>Manage Classifieds</h2>
<p><a href="<c:url value="/super_admin"/>"><< Back</a></p>
<hr>
<table>
    <thead>
    <tr>
        <td>ID</td>
        <td>Title</td>
        <td>Content</td>
        <td>Company</td>
        <td>Category</td>
        <td>Keywords</td>
        <td>Actions</td>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${classifieds}" var="classified">
        <tr>
            <td>${classified.id}</td>
            <td>${classified.title}</td>
            <td>${classified.content}</td>
            <td>${classified.company.title}</td>
            <td>${classified.category.title}</td>
            <td>
                <c:forEach items="${classified.keywordClassifiedPivotList}" var="rel" varStatus="status">
                    ${rel.keyword.title}
                    <c:if test="${!status.last}">,</c:if>
                </c:forEach>
            </td>
            <td>
                <a href="<c:url value="/view_classified?id=${classified.id}"/>">View</a>
                /
                <a href="#">Edit</a>
                /
                <a href="#">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<p>Displaying <strong>${classifiedCount} classifieds</strong> in total.</p>
</body>
</html>
