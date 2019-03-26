<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
 <style>
*{
margin:0;
padding:0;
}
#jb-container {
	width: 100%;
	margin: 0px auto;

}

#jb-header {
	padding: 20px;
	margin-bottom: 20px;
	border: 1px solid #bcbcbc;
}

#jb-content {
/* 	width: 580px; */
	padding: 20px;
	margin-bottom: 20px;
/* 	float: left; */
	border: 1px solid #bcbcbc;
}

/* #jb-sidebar {
	width: 260px;
	padding: 20px;
	margin-bottom: 20px;
	float: right;
	border: 1px solid #bcbcbc;
} */

#jb-footer {
	clear: both;
	padding: 20px;
	border: 1px solid #bcbcbc;
}

@media ( max-width : 480px ) {
	#jb-container {
		width: auto;
	}
	#jb-content {
		float: none;
		width: auto;
	}
	#jb-sidebar {
		float: none;
		width: auto;
	}
}

.center-content {
	width: 500px;
	margin: 0px auto 0px auto
}

.center-content .logo {
	width: 163px;
	height: 104px;
	background: url(/jblog/assets/images/logo.jpg) 0 0 no-repeat;
	margin: 20px auto;
/* 	text-indent: -100000px; */
	font-size: 0
}

.center-content .menu {
	/* text-align: center; */
	height: 20px;
	margin: 0 auto
}

.center-content .menu li {
	display: inline;
	padding-left: 20px
}

.center-content .menu li:first-child {
	margin !-left: 70px
}

.center-content .menu li a {
	font-size: 1.2em
}

.center-content form, .center-content .welcome-message {
	clear: both;
	margin: 15px auto 25px auto;
}

.center-content .search-form {
	width: 100%;
}

.center-content .search-form input[name='keyword'] {
	width: 350px;
	border: 3px solid #3879D9;
	padding: 5px
}

.center-content .search-form fieldset {
	margin: 10px 0
}

.center-content .search-form input[name='which'] {
	display: inline-block;
	margin-left: 10px;
}

.login-form {
	width: 250px;
	padding: 10px;
	border: 1px solid #bbb;
}

.login-form input, .login-form label {
	display: block
}

.login-form label {
	text-align: left;
	color: #555;
	margin-bottom: 2px;
	font-weight: bold;
}

.login-form input[type='text'] {
	margin-bottom: 10px;
	width: 230px;
	padding: 5px
}
</style>
</head>
<body>
<body>
    <div id="jb-container">
      <div id="jb-header">
       <div class="center-content">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<form class="search-form" action="${pageContext.request.contextPath}/search" method="post">
			<fieldset>
				<input type="text" name="keyword" />
				<input type="submit" value="검색" />
			</fieldset>
			<fieldset>
				<input type="radio" name="which" value="blog-title"> <label>블로그 제목</label>
				<input type="radio" name="which" value="tag"> <label>태그</label>
				<input type="radio" name="which" value="blog-user"> <label>블로거</label>
			</fieldset>
		</form>
	</div>
      </div>
      <div id="jb-content">
      <c:choose>
      <c:when test="${ postVoList eq null && blogVoList eq null}">
        <h2>검색 결과가 존재하지 않습니다.</h2>
        <p></p>
        <br/>
      </c:when>
      <c:when test="${ postVoList eq null}">
      <c:forEach items="${blogVoList}" var="vo">
        <h2><a href="${pageContext.request.contextPath}/${vo.userId}">${vo.title}</a></h2>
        <p>${vo.userName}님의 블로그 입니다.</p>
        <br/>
      </c:forEach>
      </c:when>
      <c:when test="${ blogVoList eq null }">
       <c:forEach items="${postVoList}" var="vo">
        <h2><a href="${pageContext.request.contextPath}/${vo.userId}/${vo.categoryNo}/${vo.no}">${vo.title}</a></h2>
        <p>${vo.userName}님의 블로그 입니다.</p>
        <p> </p>
        <br/>
      </c:forEach>
      </c:when>
      </c:choose>
      </div>
     <!--  <div id="jb-sidebar">
        <h2>Sidebar</h2>
        <ul>
          <li>Lorem</li>
          <li>Ipsum</li>
          <li>Dolor</li>
        </ul>
      </div> -->
      <div id="jb-footer">
        <p>Copyright</p>
      </div>
    </div>
  </body>
</body>
</html>