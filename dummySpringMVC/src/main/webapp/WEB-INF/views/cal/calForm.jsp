<%@page import="kr.or.ddit.cal.OperatorType"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
</head>
<body>
<input type="radio" name="dataType" data-type="html" checked/>HTML
<input type="radio" name="dataType" data-type="json" />JSON
<form method="post">
	<input type="number" name="operand1" required value="${calVO.operand1 }"/>
	<select name="operator" required>
		<option value>연산자</option>
		<c:forEach items="<%=OperatorType.values() %>" var="single">
			<option value="${single.name() }" ${single eq calVO.operator ? "selected":"" }>${single.sign }</option>
		</c:forEach>
	</select>
	<input type="number" name="operand2" required value="${calVO.operand2 }"/>
	<input type="submit" value="=" />
</form>
<div id="resultArea">
	 ${calVO.expression }
</div>
<script type="text/javascript">
	$("form:first").on("submit", function(event){
		event.preventDefault();
		let url = this.action;
		let  method = this.method;
		let data = $(this).serialize();
		let dataType = $("[name=dataType]:checked").data("type");
		$.ajax({
			url : url,
			data : data,
			method : method,
			dataType : dataType // text, html, json, xml, script -> main type : text, 파일 업로드 처리를 비동기로? (FormData)
			,
			success : function(resp, status, jqXHR) {
				$("#resultArea").html( resp.expression );
				$("input[name=operand1]").val(resp.operand1);
				$("input[name=operand2]").val(resp.operand2);
				$("select[name=operator]").val(resp.operator);
			},
			error : function(jqXHR, status, error) {
				console.log(jqXHR);
				console.log(status);
				console.log(error);
			}
		});
		return false;
	});
</script>
</body>
</html>















