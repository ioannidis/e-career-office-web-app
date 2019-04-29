<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@page import="com.careeroffice.model.User" %>
<%
	User user = (User)session.getAttribute("user");
%>    

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav">
	    <a class="navbar-brand" href="<c:url value="/" />">CareerOfficeApp</a>
	    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarResponsive">
	      <ul class="navbar-nav navbar-sidenav" id="exampleAccordion">

			<c:if test="${ user.roleId=='super_admin' }">
	        	<%--<c:import url="/WEB-INF/views/nav-super-admin.jsp"></c:import>--%>
	        </c:if>

			<c:if test="${ user.roleId=='admin' }">
			  <%--<c:import url="/WEB-INF/views/nav-admin.jsp"></c:import>--%>
			</c:if>

	        <c:if test="${ user.roleId=='external' }">
	        	<c:import url="/WEB-INF/views/nav-external.jsp"></c:import>
	        </c:if>

	        <c:if test="${ user.roleId=='p_student' || user.roleId=='u_student' }">
				<%--<c:import url="/WEB-INF/views/nav-student.jsp"></c:import>--%>
	        </c:if>
	    	
	      </ul>
	      <ul class="navbar-nav ml-auto">
	        <li class="nav-item">
	          <a href="<c:url value="/logout"/>" class="nav-link">
	            <i class="fas fa-sign-out-alt"></i>
	            Logout
	          </a>
	        </li>
	      </ul>
	   </div>
	</nav>