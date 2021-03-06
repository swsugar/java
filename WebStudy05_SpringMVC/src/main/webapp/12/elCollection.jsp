<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>12/elCollection.jsp</title>
</head>
<body>
<h4>EL을 이용한 집합객체 접근</h4>
<pre>
	<%
		String[] array = new String[]{"value1", "value2"};
		List<String> list = Arrays.asList(array);
		Set<String> set = new HashSet<>(list);
		
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("key1", "value1");
		map.put("key 2", "value2");
		pageContext.setAttribute("sampleArray", array);
		pageContext.setAttribute("sampleList", list);
		pageContext.setAttribute("sampleSet", set);
		pageContext.setAttribute("sampleMap", map);
	%>
	1. array : <%--=array[2] --%>, ${sampleArray[2] }
	2. list : <%--=list.get(2) --%>, \${sampleList.get(2) }, ${sampleList[2] }
	3. set : <%=set %>, ${sampleSet }
	4. map : <%=map.get("key 2") %>, ${sampleMap.get("key 2") }, \${sampleMap.key 2 }, ${sampleMap["key 2"] }
</pre>
</body>
</html>

















