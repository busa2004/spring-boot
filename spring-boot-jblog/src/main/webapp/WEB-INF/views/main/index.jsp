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
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<style>
* {box-sizing: border-box;}
body{height:100%;}
div {padding: 0px 0}
form {
  position: relative;
  width: 300px;
  margin: 0 auto;
}
.d1 {margin:0px;}
.d1 .d2 { background: #A3D0C3; }
.d1 .d2 input {
  width: 100%;
  height: 42px;
  padding-left: 10px;
  border: 2px solid #7BA7AB;
  border-radius: 5px;
  outline: none;
  background: #F9F0DA;
  color: #9E9C9C;
}
.d1 .d2 button {
  position: absolute; 
  top: 0;
  right: 0px;
  width: 42px;
  height: 42px;
  border: none;
  background: #7BA7AB;
  border-radius: 0 5px 5px 0;
  cursor: pointer;
}
.d1 .d2 button:before {
  content: "\f002";
  font-family: FontAwesome;
  font-size: 16px;
  color: #F9F0DA;
}
.header{
padding-top:20px;
padding-right:10px;
width:100%;
background: #A3D0C3;
height:50px;
color:balck;
}
ul {
    list-style:none;
    margin:0;
    padding:0;
    text-align:center;
    display:inline 
}

li {
	margin-right:10px;
    border : 0;
   float: right; 
}
.d3{
margin:10px;
}
.brand{
margin-top:0px;
/* display:inline */
}
.menu{
margin-top:50px;
margin:10px;

}
a:visited{
color:black;
}
.profile-img{
margin-top:100px;

}

.cut{


  /*   background: url(cat.jpg) center center no-repeat; */
/*     border-radius: 50%;
	width:100px;
	height:100px; */
}
</style>
</head>
<body>
		
		
		
		<div class="d1">
		<c:import url="/WEB-INF/views/include/header.jsp" />
			<form class="search-form" action="${pageContext.request.contextPath}/search" method="post">
				<fieldset class="d2">
					<input type="text" name="keyword" placeholder="검색어 입력">
					<button type="submit"></button>
				</fieldset>

				
				
				<fieldset class="d3">
					<input type="radio" name="which" value="blog-title"> <label>블로그 제목</label>
					<input type="radio" name="which" value="tag"> <label>태그</label>
					<input type="radio" name="which" value="blog-user"> <label>블로거</label>
				</fieldset>
			</form>
		</div>

	<%-- <div class="center-content">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<form class="search-form" action="${pageContext.request.contextPath}/search" method="post">
		<div class="d1">
				<fieldset>
					<input type="text" name="keyword" /> 
					<input type="submit" value="검색" />
				</fieldset>
		</div>
			<fieldset>
				<input type="radio" name="which" value="blog-title"> <label>블로그 제목</label>
				<input type="radio" name="which" value="tag"> <label>태그</label>
				<input type="radio" name="which" value="blog-user"> <label>블로거</label>
			</fieldset>
		</form>
	</div>  --%>
</body>
</html>