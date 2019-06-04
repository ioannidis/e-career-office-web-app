<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<li class="nav-item ${fn:endsWith(pageContext.request.requestURI, '/super_admin') ? 'active' : ''}">
    <a class="nav-link d-flex flex-row align-items-center" href="<c:url value="/super_admin"/>">
        <i class="fas fa-home mr-2"></i>
        <div>Home</div>
    </a>
</li>

<li class="nav-item ${fn:contains(pageContext.request.requestURI, '/manage_users') ? 'active' : ''}">
    <a class="nav-link d-flex flex-row align-items-center" href="<c:url value="/manage_users"/>">
        <i class="fas fa-users-cog mr-2"></i>
        <div class="mr-auto">Users</div>
        <div class="badge badge-pill badge-light">${userCount}</div>
    </a>
</li>

<li class="nav-item ${fn:contains(pageContext.request.requestURI, '/manage_roles') ? 'active' : ''}">
    <a class="nav-link d-flex flex-row align-items-center" href="<c:url value="/manage_roles"/>">
        <i class="fas fa-shield-alt mr-2"></i>
        <div class="mr-auto">Roles</div>
        <div class="badge badge-pill badge-light">${roleCount}</div>
    </a>
</li>

<li class="nav-item" ${fn:contains(pageContext.request.requestURI, '/manage_companies') ? 'active' : ''}>
    <a class="nav-link d-flex flex-row align-items-center" href="<c:url value="/manage_companies"/>">
        <i class="fas fa-briefcase mr-2"></i>
        <div class="mr-auto">Companies</div>
        <div class="badge badge-pill badge-light">${companyCount}</div>
    </a>
</li>

<li class="nav-item ${fn:contains(pageContext.request.requestURI, '/manage_dependencies') ? 'active' : ''}">
    <a class="nav-link d-flex flex-row align-items-center" href="<c:url value="/manage_departments"/>">
        <i class="fas fa-warehouse mr-2"></i>
        <div class="mr-auto">Departments</div>
        <div class="badge badge-pill badge-light">${departmentCount}</div>
    </a>
</li>

<li class="nav-item ${fn:contains(pageContext.request.requestURI, '/manage_categories') ? 'active' : ''}">
    <a class="nav-link d-flex flex-row align-items-center" href="<c:url value="/manage_categories"/>">
        <i class="fas fa-book mr-2"></i>
        <div class="mr-auto">Categories</div>
        <div class="badge badge-pill badge-light">${categoryCount}</div>
    </a>
</li>

<li class="nav-item ${fn:contains(pageContext.request.requestURI, '/manage_keywords') ? 'active' : ''}">
    <a class="nav-link d-flex flex-row align-items-center" href="<c:url value="/manage_keywords"/>">
        <i class="fas fa-tags mr-2"></i>
        <div class="mr-auto">Keywords</div>
        <div class="badge badge-pill badge-light">${keywordCount}</div>
    </a>
</li>

<li class="nav-item ${fn:contains(pageContext.request.requestURI, '/manage_classifieds') ? 'active' : ''}">
    <a class="nav-link d-flex flex-row align-items-center" href="<c:url value="/manage_classifieds"/>">
        <i class="fas fa-address-card mr-2"></i>
        <div class="mr-auto">Classifieds</div>
        <div class="badge badge-pill badge-light">${classifiedCount}</div>
    </a>
</li>