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
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<style type="text/css">
.ui-dialog .ui-dialog-buttonpane .ui-dialog-buttonset{
	float: none;
	text-align:center
}
.ui-dialog .ui-dialog-buttonpane button {
	margin-left:10px;
	margin-right:auto;
}

#dialog-message p {
	padding:20px 0;
	font-weight:bold;
	font-size:1.0em;
}

#dialog-delete-form p {
	padding:10px;
	font-weight:bold;
	font-size:1.0em; 
}

#dialog-delete-form p.error {
	color: #f00;
}

#dialog-delete-form input[type="password"] {
	padding:5px;
	border:1px solid #888;
	outline: none;
	width: 180px;
}

</style>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<!--<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/ejs/ejs.js"></script> -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>

var messageBox = function( title, message, callback ){
	$( "#dialog-message" ).attr( "title", title );
	$( "#dialog-message p" ).text( message );
	$( "#dialog-message" ).dialog({
		modal: true,
		buttons: {
			"확인": function() {
  				$( this ).dialog( "close" );
			}
		},
		close: callback || function() {}
	});	
}
var bool;


$(function(){
$( "#admin-config" ).submit( function( event ){
	// submit event 기본 동작을 막음
	// posting을 막음
	

	
	
	//validate form data
	title = $( "input[name='title']" ).val();
	if( title === "" ) {
		messageBox( "타이틀", "타이틀은 필수 항목입니다.", function(){
			$( "input[name='title']" ).focus();
		});
		return false;
	}
	
	/* password = $( "input[name='password']" ).val();
	if( password === "" ){
		messageBox( "로그인", "비밀번호는 필수 항목입니다.", function(){
			$( "input[name='password']" ).focus();
		});	
		return false;
	} */
	
	/*  $.ajax( {
		url : "${pageContext.request.contextPath }/user/api/logincheck?email=" + email+"&password="+password,
		type: "get",
		dataType: "json",
		data: "",
		success: function( response ){
			if( response.data === true ){
				
				console.log(response.data);
				return true;
			}
			if( response.data === false){
				
				console.log(response.data);
				messageBox( "로그인", "정보가 올바르지 않습니다.", function(){
					$( "input[name='password']" ).focus();
				});	
				
				return false;
			}
		},
		error: function( jqXHR, status, e ){
			console.error( status + " : " + e );
			return false;
		}
	} );	  */
	
	
});
});
</script>
</head>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/blog-header.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
			<c:import url="/WEB-INF/views/include/blog-admin-header.jsp">
			<c:param name="menu" value="basic" />
			</c:import>
				<form id="admin-config" action="${pageContext.request.contextPath}/${authUser.id}/admin/basic" method="post"  enctype="multipart/form-data">
	 		      	<table class="admin-config">
			      		<tr>
			      			<td class="t">블로그 제목</td>
			      			<td><input type="text" size="40" name="title" value="${blogVo.title}"></td>
			      		</tr>
			      		<tr>
			      			<td class="t">로고이미지</td>
			      			<td><img src="${pageContext.request.contextPath}${blogVo.logo}"
			      			  onerror="this.src='${pageContext.request.contextPath }/assets/images/default_profile.png'" ></td>      			
			      		</tr>      		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td><input type="file" name="logo-file"></td>      			
			      		</tr>           		
			      		<tr>
			      			<td class="t">&nbsp;</td>
			      			<td class="s"><input type="submit" value="기본설정 변경"></td>      			
			      		</tr>           		
			      	</table>
				</form>
				<div id="dialog-message" title="" style="display:none">
			    <p></p>
			   </div>
			</div>
		</div>
		<div id="footer">
			<p>
				<strong>Spring 이야기</strong> is powered by JBlog (c)2016
			</p>
		</div>
	</div>
</body>
</html>