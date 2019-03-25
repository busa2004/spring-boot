<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="user">

				<form:form id="join-form" name="joinForm" method="post" action="${pageContext.servletContext.contextPath }/user/modify"
				modelAttribute="userVo">
					<label class="block-label" for="name">이름</label>
					<form:input path="name" value="${userVo.name }"/>
					<p style="padding:0;margine:0; font-weight:bold;text-align:left; color:red">
					<spring:hasBindErrors name="userVo">
						<c:if test="${errors.hasFieldErrors('name') }">
							<strong>
        							<spring:message 
        								code="${errors.getFieldError( 'name' ).codes[0] }"
        								text="${errors.getFieldError( 'name' ).defaultMessage }" />
        						</strong>
						</c:if>
					</spring:hasBindErrors>
					</p>
				
					
					<label class="block-label" for="email">이메일</label>
					<input name="email" value="${userVo.email }" readonly/>
					
					<label class="block-label">패스워드</label>
					<form:input path="password"/>
					<p style="padding:0;margine:0; font-weight:bold;text-align:left; color:red">
					<spring:hasBindErrors name="userVo">
						<c:if test="${errors.hasFieldErrors('password') }">
							<strong>
        							<spring:message 
        								code="${errors.getFieldError( 'password' ).codes[0] }"
        								text="${errors.getFieldError( 'password' ).defaultMessage }" />
        						</strong>
						</c:if>
					</spring:hasBindErrors>
					</p>


					<fieldset>
						<legend>성별</legend>
						<c:choose>
							<c:when test='${userVo.gender == "male" }'>
								<label>여</label> <input type="radio" name="gender" value="female">
								<label>남</label> <input type="radio" name="gender" value="male" checked="checked">
							</c:when>
							<c:otherwise>
								<label>여</label> <input type="radio" name="gender" value="female" checked="checked">
								<label>남</label> <input type="radio" name="gender" value="male">
							</c:otherwise>
						</c:choose>
					</fieldset>
					<input type="submit" value="수정하기">
				</form:form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp"/>
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
<c:if test='${param.result == "success" }'>
	<script>
		alert( "정상적으로 수정 하였습니다." );
	</script>
</c:if>
</html>