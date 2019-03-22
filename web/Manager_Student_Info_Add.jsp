<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>学生信息添加</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<link
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="assets/css/font-awesome.min.css" />
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
		<h2>添加学生信息:</h2>
		<form action="addStudent.do" method="post" enctype="multipart/form-data">
			<div class="form-group">
				<label for="name">姓名</label> <input type="text" class="form-control"
					name="name" id="name" placeholder="例:张三">
			</div>

			<div class="form-group">
				<label >性别</label> <input type="radio" name="sex" id="optionsRadios1"
					value="男" checked> 男 <input type="radio" name="sex"
					id="optionsRadios2" value="女"> 女
			</div>
			<div class="form-group">
				<label for="nation">民族</label> <input type="text"
					class="form-control" name="nation" id="nation" placeholder="例:汉">
			</div>

			<div class="form-group">
				<label for="major">专业</label> <input type="text"
					class="form-control" name="major" id="major" placeholder="例:软件工程">
			</div>
			<div class="form-group">
				<label for="birth">出生日期</label> <input type="text"
					class="form-control" name="birth" id="birth"
					placeholder="例:2012-12-12">
			</div>
			<div class="form-group">
				<label for="id_number">身份证号</label> <input type="text"
					class="form-control" name="id_number" id="id_number"
					placeholder="例:350122********1617">
			</div>
			<div class="form-group">
				<label for="address">居住地址</label> <input type="text"
					class="form-control" name="address" id="address"
					placeholder="例:杨浦区516号上海理工大学2公寓">
			</div>
			<div class="form-group">
				<label for="image">图片上传</label> <input type="file" name="image"
					id="image">
			</div>


			<input type="submit" class="btn btn-primary btn-lg btn-block" value="提交"/>

		</form>



	</div>

	<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>

	<script
		src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>