<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/guestbook-ajax.css" rel="stylesheet" type="text/css">
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
$( "#login-form" ).submit( function( event ){
	// submit event 기본 동작을 막음
	// posting을 막음
	

	
	
	//validate form data
	email = $( "input[name='email']" ).val();
	if( email === "" ) {
		messageBox( "로그인", "이메일은 필수 항목입니다.", function(){
			$( "input[name='email']" ).focus();
		});
		return false;
	}
	
	password = $( "input[name='password']" ).val();
	if( password === "" ){
		messageBox( "로그인", "비밀번호는 필수 항목입니다.", function(){
			$( "input[name='password']" ).focus();
		});	
		return false;
	}
	
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
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<div id="content">
			<div id="user">
				<div id="dialog-message" title="" style="display:none">
			  <p></p>
			</div>
				<form id="login-form" name="loginform" method="post" action="${pageContext.servletContext.contextPath }/user/auth">
					<label class="block-label" for="email">이메일</label>
					<input id="email" name="email" type="text" value="">
					<label class="block-label" >패스워드</label>
					<input name="password" type="password" value="">
					<c:if test='${result == "fail" or param.result == "fail" }'>
						<p>
							로그인이 실패 했습니다.
						</p>
					</c:if>
					<input type="submit" value="로그인">
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp"/>
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>