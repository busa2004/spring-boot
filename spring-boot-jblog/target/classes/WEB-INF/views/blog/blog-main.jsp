<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<% pageContext.setAttribute( "newLine", "\n" ); %>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<!--<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/ejs/ejs.js"></script> -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
/*  $(function(){
	var strArray = window.location.href.split('/');
	var id=strArray[4];
	
}); */
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/blog-header.jsp" />
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${post.title}</h4>
					<p>
					<%-- ${post.content} --%>
					${fn:replace(post.content, newLine, "<br>") }
					<p>
				</div>
				
				<c:if test="${post.role eq 'y' }">
				<div class="comment">
				<hr>
					<div class="input-form">
					<c:if test="${authUser ne null }">
							<form action="${pageContext.request.contextPath}/comment/${id}/${post.categoryNo}/${post.no}/${authUser.name}" method="post">
								<b class="id-p">${ authUser.name }</b>
								<input type="text" name="content">
							    <input type="submit" value="댓글"> 
							</form>
					</c:if>
					</div>
					
					<c:if test="${commentList ne null }">
					<table class="type04">
						<c:forEach items="${commentList}"	var="vo" varStatus="status">
							<tr>
								<th scope="row">${vo.userName}</th>
								<td>${fn:replace(vo.content, newLine, "<br>") }</td>
								<td>
								<c:if test="${authUser.name eq vo.userName }">
								<a href="${pageContext.request.contextPath}/comment/delete/${id}/${post.categoryNo}/${post.no}/${vo.no}"><img src ="${pageContext.request.contextPath}/assets/images/delete.jpg"></a>
								</c:if>
								<td>
							</tr>
						</c:forEach>
					</table>
					</c:if>
				<hr>
				</div>
				</c:if>
				
				<ul class="blog-list">
				<c:forEach items="${postList }"	var="vo" varStatus="status">
				<c:if test="${vo.role ne 'n' }">
				<li><a href="${pageContext.request.contextPath}/${id}/${vo.categoryNo}/${vo.no}">${vo.title}</a> <span>${vo.regDate}</span>	</li>
				</c:if>
				</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blogVo.logo}"
				  onerror="this.src='${pageContext.request.contextPath }/assets/images/default_profile.png'" >
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
			<c:forEach items="${categoryList}"	var="vo" varStatus="status">
			<li><a href="${pageContext.request.contextPath}/${id}/${vo.no}">${vo.name}</a></li>
			</c:forEach>
			</ul>
		</div>
		
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>