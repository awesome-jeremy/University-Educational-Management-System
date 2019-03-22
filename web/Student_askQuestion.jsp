<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>答疑</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="assets/css/font-awesome.min.css" />
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="Student_include.jsp"%>
<style type="text/css">
#mainContent {
	margin-left: 200px;
}
</style>
</head>
</head>
<body>
	<div id="mainContent">
	<!-- 在这里编辑我的代码 -->

		<h1 id="test">答疑</h1>
		<table border="1" class="table table-striped">
			<tr>

				<th>课程代号</th>
				<th>课程名称</th>
				<th>任课教师</th>
				<th>答疑</th>


			</tr>
			<c:forEach var="lesson" items="${sessionScope.myClassesTable}">
				<tr>
					<td>${lesson.id}</td>
					<td>${lesson.name}</td>
					<td>${lesson.teacher_name }</td>

					<%--<td><input id="askQuestion"  onclick="window.location='askQuestion.do?class_id=${lesson.id}&teacher_id=${lesson.teacher_id}'" type="button" class="btn btn-info btn-block btn-sm" value="答疑"></td>--%>

					<td><button class="btn btn-primary btn-block btn-sm"  id="askQuestion" type="button" onclick="window.location='askQuestion.do?class_id=${lesson.id}&teacher_id=${lesson.teacher_id}'">
						答疑 <span class="badge">${lesson.haveAnswer_number}条回复</span>
					</button></td>
				</tr>
			</c:forEach>


		</table>
	
	</div>


	<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>

	<script
		src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>