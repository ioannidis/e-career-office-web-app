<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<li class="nav-item ${fn:contains(pageContext.request.requestURI, 'index') ? 'active' : ''}">
    <a class="nav-link d-flex flex-row align-items-center" href="<c:url value="/super_admin"/>">
        <i class="fas fa-home mr-2"></i>
        <div>Home</div>
    </a>
</li>

<li class="nav-item ${fn:contains(pageContext.request.requestURI, 'user') ? 'active' : ''}">
    <a class="nav-link d-flex flex-row align-items-center" href="<c:url value="/manage_users"/>">
        <i class="fas fa-users-cog mr-2"></i>
        <div>Users</div>
    </a>
</li>

<li class="nav-item ${fn:contains(pageContext.request.requestURI, 'role') ? 'active' : ''}">
    <a class="nav-link d-flex flex-row align-items-center" href="<c:url value="/manage_roles"/>">
        <i class="fas fa-shield-alt mr-2"></i>
        <div>Roles</div>
    </a>
</li>

<li class="nav-item ${fn:contains(pageContext.request.requestURI, 'compan') ? 'active' : ''}">
    <a class="nav-link d-flex flex-row align-items-center" href="<c:url value="/manage_companies"/>">
        <i class="fas fa-briefcase mr-2"></i>
        <div>Companies</div>
    </a>
</li>

<li class="nav-item ${fn:contains(pageContext.request.requestURI, 'department') ? 'active' : ''}">
    <a class="nav-link d-flex flex-row align-items-center" href="<c:url value="/manage_departments"/>">
        <i class="fas fa-warehouse mr-2"></i>
        <div>Departments</div>
    </a>
</li>

<li class="nav-item ${fn:contains(pageContext.request.requestURI, 'categor') ? 'active' : ''}">
    <a class="nav-link d-flex flex-row align-items-center" href="<c:url value="/manage_categories"/>">
        <i class="fas fa-book mr-2"></i>
        <div>Categories</div>
    </a>
</li>

<li class="nav-item ${fn:contains(pageContext.request.requestURI, 'keyword') ? 'active' : ''}">
    <a class="nav-link d-flex flex-row align-items-center" href="<c:url value="/manage_keywords"/>">
        <i class="fas fa-tags mr-2"></i>
        <div>Keywords</div>
    </a>
</li>

<li class="nav-item ${fn:contains(pageContext.request.requestURI, 'classifieds') ? 'active' : ''}">
    <a class="nav-link d-flex flex-row align-items-center" href="<c:url value="/manage_classifieds"/>">
        <i class="fas fa-address-card mr-2"></i>
        <div>Classifieds</div>
    </a>
</li>