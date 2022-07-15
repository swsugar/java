<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>   
<%--
	String message =(String) request.getAttribute("message");
--%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%-- <%
	if(StringUtils.isBlank(message)){
		%>
		<script type="text/javascript">
			alert('<%=message %>');
		</script>
		<%
	}
%> --%>
<c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message}");
	</script>
</c:if>
<style type="text/css">
	.error{
		background-color: red;
	}
</style>
</head>
<body>
<h4> 대덕인재 개발원 학생 등록 양식 </h4>
<!-- 학생 : 이름(필), 나이, 전화번호이름(필), 이메일, 주소이름(필), 최종학력이름(필), 학교이름(필), 학과이름(필), 졸업여부, 사진, 성별이름(필), 자격증 -->
<form:form modelAttribute="student" method="post" enctype="multipart/form-data">
<%-- <form  method="post" enctype="multipart/form-data"> --%>
	<ul>
		<li>
			이름 : <input type="text" name="name" value="${student.name }" />
			<form:errors path="name" element="span" cssClass="error" />
		</li>
		<li>
			나이 : <input type="number" name="age" value="${student.age }"/>
			<form:errors path="age"  element="span" cssClass="error" />
		</li>
		<li>
			전화번호 : <input type="text" name="hp"  value="${student.hp }"/>
		</li>
		<li>
			이메일 : <input type="email" name="email"  value="${student.email }"/>
		</li>
		<li>
			주소 : <input type="text" name="address" value="${student.address }"/>
		</li>
		<li>
			최종학력 : 
				<select name="grade">
					<option value>학력</option>
					<c:forEach items="${gradeMap }" var="entry">
						<option value='${entry.key }'>${entry.value[1] }</option>
					</c:forEach>
					<%-- <%
						for(Entry<String,String[]> entry:gradeMap.entrySet()){
							String gradeCode = entry.getKey();
							String gradeText = entry.getValue()[1];
							%>
							<option value='<%=gradeCode %>'><%=gradeText %></option>
							<%
						}
					%> --%>
				</select>
		</li>
		<li>
			학교 : <input type="text" name="school"  value="${student.school }"/>
		</li>
		<li>
			학과 : <input type="text" name="subject"  value="${student.subject }"/>
		</li>
		<li>
			졸업여부 : <input type="radio" name="gdt" value="졸업"/>여
			<input type="radio" name="gdt" value="예정" />부
 		</li>
 		<li>
 			성별 : <input type="radio" name="gender" value="F"/>여
			<input type="radio" name="gender" value="M" />남
 		</li>
 		<li>
 			자격증 : 
 				<select name="licenses" multiple size="10">
 					<c:forEach items="${licenseMap }" var="entry">
 						<option value='${entry.key }'>${entry.value[1] }</option>
 					</c:forEach>
 					<%-- <%
						for(Entry<String,String[]> entry:licenseMap.entrySet()){
							String licCode = entry.getKey();
							String licText = entry.getValue()[1];
							%>
							<option value='<%=licCode %>'><%=licText %></option>
							<%
						}
					%> --%>
 				</select>			
  		</li>
  		<li>
  			사진 : <input type="file" name="photo" />
  		</li>
  		<li>
  			<input type="submit" value="등록" />
  		</li>
	</ul>
<%-- </form> --%>
</form:form>
</body>
</html>





