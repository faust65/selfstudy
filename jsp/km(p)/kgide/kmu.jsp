<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>커뮤니티</title>
</head>
<body>
	<div class="container w-75 mt-5 mx-auto">
		<ul class="list-group">
			<c:forEach var="km" items="kmlist" varStatus="status">
				<li class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
					<a href="/kgd/delete/${km.gid}" class="text-decoration-none"><span class="badge bg-secondary">&times;</span></a>
				</li>
			</c:forEach>
		</ul>
		<hr>
		<button class="btn btn-outline-info mb-3" type="button" data-bs-togle="collapse" data-bs-target="#addForm" aria-expended="false" aria-controls="addForm">등록</button>
		<div class="collapse" id="addForm">
			<div class="card card-body">
				<form method="post" action="/kgd/addkmu" enctype="multipart/form-data">
					<label class="form-label">내용</label>
					<textarea rows="5" cols="50" name="content" class=form-control></textarea>
					<button type="submit" class="btn btn-success mt-3">등록</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>