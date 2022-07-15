<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>12/elDesc.jsp</title>
</head>
<body>
<h4>표현 언어(Expression Language): Model2 구조에서 활용</h4>
<pre>
	: 값을 출력하기 위한 기능만을 갖고있는 언어.제어문의 형태가 없음., \${속성명 }, 스크립트 형태 언어
	<%
		String sample = "1000";
		pageContext.setAttribute("sample", sample);
		pageContext.setAttribute("blank", "   ");
		pageContext.setAttribute("list", Arrays.asList());
		pageContext.setAttribute("test", "ab");
		
		MemberVO member1 = new MemberVO();
		pageContext.setAttribute("member", member1);		
		MemberVO member2 = new MemberVO();
		member2.setMemName("신규");
		request.setAttribute("member", member2);
	%>
	<%=sample %>, ${sample }
	
	1. EL 연산자
		1) 산술연산자 : +-/*%
			${1+1 }, ${"1"+1 }, \${"a"+1 }, ${a+1 }, ${sample+1 }
		2) 논리연산자 : &&(and), ||(or), !(not)
			${true and true }, ${"true" and true }, ${a or true }
		3) 단항연산자 : empty 
			- 속성 존재여부 -> null 여부 -> String(length), Collection(size)
 			${not empty sample }, ${empty a }, ${empty blank }, ${empty list }
 		4) 3 항 연산자 : 조건식? 참 	: 거짓
 			${not empty test? "있다":"없다"}
	2. 객체 접근 방법
		${requestScope.member.getMemName() }, ${requestScope.member.memName }, ${requestScope.member.getMemTest()}, ${requestScope.member.memTest}
		${requestScope.member["memName"] }
	3. 집합 객체 접근 방법 : <a href="elCollection.jsp">집합객체</a>
	4. EL 기본객체 : <a href="elObject.jsp">기본객체</a>
		1) scope 객체 : pageScope, requestScope, sessionScope, applicaitonScope
	
</pre>
</body>
</html>











