<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<li class="nav-item" data-toggle="tooltip" data-placement="right" title="" data-original-title="External Actions">
	<a class="nav-link" href="#" style="color:#fff !important; padding-bottom: 0px;">
		<b>External Actions</b>
	</a>
	<ul class="sidenav-second-level">
		<li class="nav-item" data-toggle="tooltip" data-placement="right" title="" data-original-title="Index page">
			<a class="nav-link" href="<c:url value="/external"/>">
				<span class="nav-link-text">
					<i class="fas fa-home"></i>
					Home
				</span>
			</a>
		</li>
		<li class="nav-item ${fn:contains(pageContext.request.requestURI, 'externalclassifieds') ? 'active' : ''}" data-toggle="tooltip" data-placement="right" title="" data-original-title="Index page">
			<a class="nav-link" href="<c:url value="/externalclassifieds"/>">
				<span class="nav-link-text">
					<i class="fa fa-fw fa-clipboard-list"></i>
					Classifieds
				</span>
			</a>
		</li>
	</ul>
</li>