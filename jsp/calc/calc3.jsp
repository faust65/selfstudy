<%@page import="java.lang.classfile.instruction.SwitchCase"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean id="calc" class="calc.calc" />
<jsp:setProperty name="calc" property="*" />
    
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>caljsp</title>
</head>
<body>
	<h2>계bean</h2>
	<hr>
	결과:<%=calc.calc() %>
</body>
</html>