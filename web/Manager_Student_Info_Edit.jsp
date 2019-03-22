<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>学生信息修改和删除</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="assets/css/font-awesome.min.css" />
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="Manager_include.jsp"%>
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
		<h1>学生信息修改和删除</h1>
		<table border="1" class="table table-striped">
			<tr>

				<th>照片</th>
				<th>学号</th>
				<th>姓名</th>
				<th>性别</th>
				<th>民族</th>
				<th>出生日期</th>
				<th>专业</th>
				<th>身份证号</th>
				<th>地址</th>
				<th>编辑</th>
				<th>删除</th>


			</tr>
			<c:forEach var="student" items="${sessionScope.students}">
				<tr>
					<td><img src="${ student.image}" width="50px" height="30px"></td>
					<td>${student.id}</td>
					<td>${student.name}</td>
					<td>${student.sex }</td>
					<td>${student.nation}</td>
					<td>${student.birth}</td>
					<td>${student.major}</td>
					<td>${student.id_number}</td>
					<td>${student.address}</td>
					<td><a href="studentEdit.do?id=${student.id }">编辑</a></td>
					<td><a href="Delete.do?type=Student&id=${student.id }">删除</a></td>
				</tr>
			</c:forEach>


		</table>
	
	</div>

	<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>

	<script
		src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>