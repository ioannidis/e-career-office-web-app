<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>


<li class="nav-item" data-toggle="tooltip" data-placement="right" title="" data-original-title="Admin Actions">
    <a class="nav-link" href="#" style="color:#fff !important; padding-bottom: 0px;">
        <b>Admin Actions</b>
    </a>

    <ul class="sidenav-second-level">
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="" data-original-title="View Available Students">
            <a class="nav-link" href="<c:url value="/students"/>">
				<span class="nav-link-text">
					<i class="fas fa-home"></i>
					Home
				</span>
            </a>
        </li>
        <li class="nav-item" data-toggle="tooltip" data-placement="right" title="" data-original-title="Manage Available Classifieds">
            <a class="nav-link" href="<c:url value="/classifieds"/>">
                <i class="fa fa-fw fa-bookmark"></i>
                <span class="nav-link-text">Manage Available Classifieds</span>
            </a>
        </li>
    </ul>
</li>