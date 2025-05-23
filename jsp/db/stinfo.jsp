<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>학생정보</title>
</head>
<body>
<h2>학생정보</h2>[<a href="/jwbbok/stdcon">새로고침</a>]
<hr>
<table border=1>
<tr><th>id</th><th>이름</th><th>대학</th><th>생일</th><th>이메일</th></tr>
<c:forEach items="${st}" var="s">
<tr>
<td>${s.id}</td><td>${s.un}</td><td>${s.univ}</td><td>${s.birth}</td><td>${s.email}</td>
</tr>
</c:forEach>
</table>
<hr>
<h2>학생추가</h2>
<form method="post" action="/jwbbok/stcon?action=insert">
<label>이름</label>
<input type="text" name="un"><br>
<label>대학</label>
<input type="text" name="univ"><br>
<label>생일</label>
<input type="text" name="birth"><br>
<label>이메일</label>
<input type="text" name="email"><br>
<button type="submit">등록</button>
</form>
</body>
</html>