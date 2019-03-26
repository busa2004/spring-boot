<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<!--<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/ejs/ejs.js"></script> -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
var i = 0;
var render = function( vo, mode ) {
	// 상용 app에서는 template library 를 사용한다. ex) ejs, underscore, mustache
	console.log(vo.no);
	var html =
		
	"<tr>"+
	"<td>"+(++i)+"</td>"+
	"<td>"+vo.name+"</td>"+
	"<td>"+(vo.count-1)+"</td>"+
	"<td>"+vo.description+"</td>"+
	"<td><a href='' data-no='"+vo.no+"'><img src='${pageContext.request.contextPath}/assets/images/delete.jpg'></a></td>"+
	"</tr>";
	/*
	var html = listItemTemplate.render( vo );
	*/
	if( mode === true ) {
		$(".admin-cat #tbody").prepend( html );	
	} else {
		$(".admin-cat #tbody").append( html );
	}
}


var fetchList = function(){
	
	
	
	$.ajax( {
		url : "${pageContext.request.contextPath }/${authUser.id}/admin/category/list",
		type: "get",
		dataType: "json",
		data: "",
		//contentType: 'application/json', //JSON Type으로 데이터를 보낼 때,
		success: function( response ){
			if( response.result === "fail" ){
				console.warn( response.message );
				return;
			}
			
			
				
			//rendering
			
			$.each( response.data, function( index, vo ){
				render( vo, true );
			} );
			
			/*
			var htmls = listTemplate.render( response );
			*/
			//$( "#list-guestbook" ).append( html );
			//$( "#list-guestbook" ).hello();
		},
		error: function( jqXHR, status, e ){
			console.error( status + " : " + e );
		}
	} );	
}
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
	
	fetchList();
	
	$(document).on("click",".admin-cat a",function(){
//	$(".admin-cat a").click(function(){
		var no = $(this).data().no;
		console.log(no);
		$.ajax( {
			url : "${pageContext.request.contextPath }/${authUser.id}/admin/category/delete?no="+no,
			type: "get",
			dataType: "json",
			data: "",
			contentType: 'application/json', //JSON Type으로 데이터를 보낼 때,
			success: function( response ){
				if( response.result === "fail" ) {
					console.error( response.message );
					return;
				}
				// rendering
				//remove
				$(".admin-cat a[data-no="+no+"]").parent().parent().remove();
			
			},
			error: function( jqXHR, status, e ){
				console.error( status + " : " + e );
			}
		} );	
	});
	$("#admin-cat-add input[type='submit']").click(function(){
		
		name = $( "input[name='name']" ).val();
		if( name === "" ) {
			messageBox( "카테고리추가", "카테고리명은 필수 항목입니다.", function(){
				$( "input[name='name']" ).focus();
			});
			return false;
		}
		
		desc = $( "input[name='desc']" ).val();
		if( desc === "" ) {
			messageBox( "카테고리추가", "설명은 필수 항목입니다.", function(){
				$( "input[name='desc']" ).focus();
			});
			return false;
		}
		
		var name = $("#admin-cat-add input[name='name']").val();
		var description = $("#admin-cat-add input[name='desc']").val();
		$.ajax( {
			url : "${pageContext.request.contextPath }/${authUser.id}/admin/category/insert?name="+name+"&description="+description+"&userNo="+"${authUser.no}",
			type: "get",
			dataType: "json",
			data: "",
			contentType: 'application/json', //JSON Type으로 데이터를 보낼 때,
			success: function( response ){
				if( response.result === "fail" ) {
					console.error( response.message );
					return;
				}
				
				// rendering
				render( response.data, true );
				console.log("rendering")
				
			},
			error: function( jqXHR, status, e ){
				console.error( status + " : " + e );
			}
		} );	
	});
	


	
	
	
	
});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/blog-header.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
			<c:import url="/WEB-INF/views/include/blog-admin-header.jsp">
			<c:param name="menu" value="category" />
			</c:import>
			  	
		      	<table class="admin-cat">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>   
		      		</tr>   
		      		<tbody id="tbody">
		      		</tbody>		
		      		<!--<c:forEach items="${list }"	var="vo" varStatus="status">
		      		  <tr>
						<td>${size-status.index}</td>
						<td>${vo.name }</td>
						<td>${vo.count }</td>
						<td>${vo.description}</td>
						<td><a href='' data-no='${vo.no}'><img src='${pageContext.request.contextPath}/assets/images/delete.jpg'></a></td>--> 
						<!--  <td><a href="${pageContext.request.contextPath}/${authUser.id}/admin/category/delete?${vo.no}"><img src="${pageContext.request.contextPath}/assets/images/delete.jpg"></a></td>-->
				<!-- 	</tr> 
		      		</c:forEach> --> 
										  
				</table>
      	
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input type="submit" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table> 
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