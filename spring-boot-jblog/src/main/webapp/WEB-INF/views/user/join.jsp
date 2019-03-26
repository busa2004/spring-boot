<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script>
var FormValidator = {

	$imageCheck: null,
	$buttonCheckEmail: null,
	$inputTextEmail: null,
	
	init: function() {
		this.$imageCheck = $( "#img-checkemail" );
		this.$buttonCheckEmail = $( "#btn-checkemail" );
		this.$inputTextEmail = $( "#blog-id" );
		
		this.$inputTextEmail.change( this.onEmailInputTextChanged.bind( this ) );
		this.$buttonCheckEmail.click( this.onCheckEmailButtonClicked.bind( this ) );
		$( "#join-form" ).submit( this.onFormSubmit.bind( this ) );		
	},
	onEmailInputTextChanged: function() {
		this.$imageCheck.hide();
		this.$buttonCheckEmail.show();		
	},	
	onCheckEmailButtonClicked: function( event ) {
		console.log( event.currentTarget );
		
		var id = this.$inputTextEmail.val();
		if( id === "" ) {
			return;
		}
		
		//ajax 통신
		$.ajax( {
			url : "${pageContext.request.contextPath }/user/api/checkid?id=" + id,
			type: "get",
			dataType: "json",
			data: "",
			success: this.onCheckEmailAjaxSuccess.bind( this ),
			error: this.onCheckEmailAjaxError.bind( this )
		} );	
	},
	onCheckEmailAjaxSuccess: function( response ) {
		if(response.result == "fail"){
			console.error(response.message);
			return;
		}
		
		if( response.data == true ) {
			alert( "이미 존재하는 이메일 입니다. 다른 이메일을 사용해 주세요." );
			// email 입력 창 비우고 포커싱
			this.$inputTextEmail.val( "" ).focus();
		} else {
			this.$imageCheck.show();
			this.$buttonCheckEmail.hide();
		}		
	},
	onCheckEmailAjaxError: function( jqXHR, status, error ){
		console.error( status + " : " + error );
	},
	onFormSubmit: function() {
		//1. 이름
		var $inputTextName = $( "#name" );
		if( $inputTextName.val() === "" ) {
			alert( "이름은 필수 항목입니다." );
			$inputTextName.focus();
			return false;
		}

		var $email = $( "#blog-id" );
		if( this.$inputTextEmail.val() === "" ) {
			alert( "이메일은 필수 항목입니다." );
			this.$inputTextEmail.focus();
			return false;
		}
		
		//3. 이메일 중복 체크 여부
		if( this.$imageCheck.is( ":visible" ) === false ) {
			alert( "이메일 중복 체크를 해 주세요." );
			return false;
		}
		
		//4. 비밀번호
		var $inputPassword = $( "#password" );
		if( $inputPassword.val() === "" ) {
			alert( "비밀번호는 필수 항목입니다." );
			$inputPassword.focus();
			return false;
		}
		
		//5. 약관동의
		var $inputCheckBoxAgree = $( "#agree-prov" );
		if( $inputCheckBoxAgree.is( ":checked" ) === false ) {
			alert( "가입 약관에 동의 하셔야 합니다." );
			$inputCheckBoxAgree.focus();
			return false;
		}		

		// valid!
		return true;				
	}
}

$(function(){
	FormValidator.init();
});
</script>
</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/include/header.jsp" />
		<form:form class="join-form" id="join-form" method="post" action="${pageContext.servletContext.contextPath }/user/join"
		 modelAttribute="userVo">
			<label class="block-label" for="name">이름</label>
			<input id="name"name="name" type="text" value="">
			
			<label class="block-label" for="blog-id">아이디</label>
			<input id="blog-id" name="id" type="text"> 
			<input id="btn-checkemail" type="button" value="id 중복체크">
			<img id="img-checkemail" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">
			
			<spring:hasBindErrors name="userVo">
   						<c:if test="${errors.hasFieldErrors('id') }">
   							<p style="padding: 5px 0 0 0; text-align:left; color:red">
        						<strong>
        							<spring:message 
        								code="${errors.getFieldError( 'id' ).codes[0] }"
        								text="${errors.getFieldError( 'id' ).defaultMessage }" />
        						</strong>
        					</p>
   						</c:if>
			</spring:hasBindErrors>
			
			<label class="block-label" for="password">패스워드</label>
			<input id="password" name="password" type="password" />
			
			<spring:hasBindErrors name="userVo">
   						<c:if test="${errors.hasFieldErrors('password') }">
   							<p style="padding: 5px 0 0 0; text-align:left; color:red">
        						<strong>
        							<spring:message 
        								code="${errors.getFieldError( 'password' ).codes[0] }"
        								text="${errors.getFieldError( 'password' ).defaultMessage }" />
        						</strong>
        					</p>
   						</c:if>
			</spring:hasBindErrors>
			
			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form:form>
	</div>
</body>
</html>
