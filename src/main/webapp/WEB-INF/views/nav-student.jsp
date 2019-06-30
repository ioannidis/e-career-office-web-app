<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
		 pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<li class="nav-item" data-toggle="tooltip" data-placement="right" title="" data-original-title="User Actions">
	<a class="nav-link" href="#" style="color:#fff !important; padding-bottom: 0px;">
		<b>Student Actions</b>
	</a>

	<ul class="sidenav-second-level">
		<li class="nav-item" data-toggle="tooltip" data-placement="right" title="" data-original-title="Home">
			<a class="nav-link" href="<c:url value="/student"/>">
				<span class="nav-link-text">
					<i class="fas fa-home"></i>
					Home
				</span>
			</a>
		</li>
		<%--<li class="nav-item" data-toggle="tooltip" data-placement="right" title="" data-original-title="Home">--%>
			<%--<a class="nav-link" href="<c:url value="/student_edit_details"/>">--%>
				<%--<span class="nav-link-text">--%>
					<%--<i class="fas fa-user-edit"></i>--%>
					<%--Edit Information--%>
				<%--</span>--%>
			<%--</a>--%>
		<%--</li>--%>
		<li class="nav-item ${fn:contains(pageContext.request.requestURI, 'upload_cv') ? 'active' : ''}" data-toggle="tooltip" data-placement="right" title="" data-original-title="Upload CV">
			<a class="nav-link" href="<c:url value="/upload_cv"/>">
				<i class="fas fa-upload"></i>
				<span class="nav-link-text">CV Details</span>
			</a>
		</li>
		<%--<li class="nav-item" data-toggle="tooltip" data-placement="right" title="" data-original-title="Get your CV">--%>
			<%--<a class="nav-link" href="<c:url value="/get_cv"/>">--%>
				<%--<span class="nav-link-text">--%>
					<%--<i class="fas fa-download"></i>--%>
					<%--Get your CV--%>
				<%--</span>--%>
			<%--</a>--%>
		<%--</li>--%>
	</ul>
</li>