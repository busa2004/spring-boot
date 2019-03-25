<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<% pageContext.setAttribute( "newLine", "\n" ); %>
<!doctype html>
<html>
<head>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<!--<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/ejs/ejs.js"></script> -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
</script>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="guestbook">
				<form:form action="${pageContext.servletContext.contextPath }/guestbook/add" method="post"
				modelAttribute="guestbookVo">
					<table>
						<tr>
							<td>이름</td><td><form:input path="name"/></td>
							<td>비밀번호</td><td><form:input path="password"/></td>
						</tr>
						<tr>
							<td colspan=4><form:textarea path="message" id="content"></form:textarea></td>
						</tr>
						<tr>
							<td colspan=4 align=right><input type="submit" VALUE=" 확인 "></td>
						</tr>
					</table>
					<!--  <p style="padding:0;margine:0; font-weight:bold;text-align:left; color:red">
					<form:errors path="name" />
					<form:errors path="password"/>
					<form:errors path="message"/>
					</p>-->
					
					
					<p style="padding: 5px 0 0 0; text-align:left; color:red">
					<spring:hasBindErrors name="guestbookVo">
						<c:choose>
   						<c:when test="${errors.hasFieldErrors('name') }">
        						<strong>
        							<spring:message 
        								code="${errors.getFieldError( 'name' ).codes[0] }"
        								text="${errors.getFieldError( 'name' ).defaultMessage }" />
        						</strong>
        					
   						</c:when>
   						<c:when test="${errors.hasFieldErrors('password') }">
        						<strong>
        							<spring:message 
        								code="${errors.getFieldError( 'password' ).codes[0] }"
        								text="${errors.getFieldError( 'password' ).defaultMessage }" />
        						</strong>
   						</c:when>
   						<c:when test="${errors.hasFieldErrors('message') }">
        						<strong>
        							<spring:message 
        								code="${errors.getFieldError( 'message' ).codes[0] }"
        								text="${errors.getFieldError( 'message' ).defaultMessage }" />
        						</strong>
   						</c:when>
   						<c:otherwise>
   						</c:otherwise>
   						</c:choose>
					</spring:hasBindErrors>
					</p>
					
				</form:form>
				<ul>
					<c:set var="count" value="${fn:length(list) }"/>
					<c:forEach items="${list }" var="vo" varStatus="status">
						<li>
							<table>
								<tr>
									<td>[${count - status.index }]</td>
									<td>${vo.name }</td>
									<td>${vo.regDate }</td>
									<td><a href="${pageContext.servletContext.contextPath }/guestbook/delete/${vo.no }">삭제</a></td>
								</tr>
								<tr>
									<td colspan="4">${fn:replace(vo.message, newLine, "<br>") }</td>
								</tr>
							</table>
							<br>
						</li>						
					</c:forEach>
				</ul>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp">
			<c:param name="menu" value="guestbook"/>
		</c:import>
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>