<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>13/jstlDesc.jsp</title>
</head>
<body>
<h4>JSTL(Jsp Standard Tag Library) : EL 과 함께 사용</h4>
<pre>
	: 커스텀 태그들 중 널리 쓰이는 것들을 모아놓은 라이브러리.
	taglib 로 사용할 커스텀 태그의 라이브러리 파일을 로딩.
	1. core : 제어문에서 사용
		조건문
			- 단일 조건문 : if
			- 다중 조건문 : choose~when~otherwise
		반복문
			- 일반 반복문 : foreach(begin, step, end) -> for(int i=0; i<10; i++)
			- 향상된 반복문	: foreach(items, var) for(element 참조 블럭 변수:collection)
		속성 제어 : set, remove
			- 속성 생성 : set (속성명-var, 속성값-value, 영역-scope)
			- 속성 제거 : remove (속성명-var, 영역-scope)	
		기타 : url(url rewriting), import, out	
		<c:url value="/02/first.jsp" var="newURL">
			<c:param name="name1" value="value1" />
			<c:param name="name2" value="value2" />
		</c:url>
		${newURL }
		<a href="${newURL }">first.jsp</a>
	2. fmt : parsing, formatting
		<c:set var="today" value="<%=new Date() %>" />
		<fmt:formatDate value="${today }" dateStyle="long" timeStyle="long" type="both" var="todayStr"/>
		before parsing -> ${todayStr }
		<fmt:parseDate value="${todayStr }"  dateStyle="long" timeStyle="long" type="both" var="today2" />
		after parsing -> ${today2 }, ${today2.time }
		
	3. functions : ${fn:replace("abc", "b", "B") }, ${"abc".replace("b", "B") }
</pre>
<c:if test="${empty sample}">
	"sample 속성 없음."
</c:if>
<c:if test="${not empty sample }">
	"sample 속성 있음."
</c:if>
<c:choose>
	<c:when test="${empty sample }">
		"sample 속성 없음."
	</c:when>
	<c:otherwise>
		"sample 속성 있음."
	</c:otherwise>
</c:choose>
<c:forEach begin="1" step="1" end="10" var="i">
	${i }
</c:forEach>

<c:set var="array" value='<%=new String[]{"value1", "value2"} %>' scope="page"/>
<c:forEach items="${array }" var="element">
	${element	 }
</c:forEach>
<c:remove var="array" scope="page"/>
<c:if test="${not empty array}">
	배열 있음
</c:if>
<c:if test="${empty array}">
	배열 없음
</c:if>
<c:import url="https://www.naver.com" var="naver"></c:import>
<c:out value="${naver }" escapeXml="true" />
</body>
</html>























