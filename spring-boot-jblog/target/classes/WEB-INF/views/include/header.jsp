<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<div class="header">
		<ul class="menu">
		<c:choose>
			<c:when test="${empty authUser }">
			<li><a href="${pageContext.servletContext.contextPath }/user/login">로그인</a></li>
			<li><a href="${pageContext.servletContext.contextPath }/user/join">회원가입</a></li>
			</c:when>
			<c:otherwise>
			<li><a href="${pageContext.servletContext.contextPath }/user/logout">로그아웃</a></li>
			<li><a href="${pageContext.servletContext.contextPath }/${authUser.id}">내블로그</a></li>
			</c:otherwise>
		</c:choose> 
		</ul>
		</div>
		<div class="profile-img">
		<c:choose>
		<c:when test="${ empty authUser }">
		<a href="${pageContext.request.contextPath}/user/login">
		<img src="${pageContext.request.contextPath}/assets/images/user.png" style="width:150px;">
		</a>
		</c:when>
		<c:otherwise>
		<div class="cut">
		<a href="${pageContext.request.contextPath}/${authUser.id}">
		<img  src="${pageContext.request.contextPath}${blogVo.logo}"
		 onerror="this.src='${pageContext.request.contextPath}/assets/images/user.png'"
		 style="width:150px;height:150px;border-radius: 50%;"/>
		 </a>
		<%-- <img  src="${pageContext.request.contextPath}/assets/images/white.png" /> --%>
		</div>
		</c:otherwise>
		</c:choose>
		</div>
		<div class="brand">
		<a href="${pageContext.servletContext.contextPath }/"><img src="${pageContext.request.contextPath}/assets/images/jblog-logo.png" style="width:250px;" ></a>
		</div>
		
