<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>教室信息添加</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<body>
	<div id="mainContent">
		<!-- 在这里编辑我的代码 -->
		<h2>添加教室信息:</h2>
		<form action="addClassroom.do" method="post">


			<div class="form-group">
				<label>教室所在大楼</label>
				 <select name="building" id="building" class="form-control">

						 <c:forEach var="var" items="${applicationScope.buildings}" varStatus="status">
							 <option value="${var}">${var}</option>
						 </c:forEach>

				</select>
			</div>
			
			<div class="form-group">
				<label for="room_number">教室房间号</label> <input type="text"
					class="form-control" name="room_number" id="room_number"
					placeholder="例:301">
			</div>


			<input type="submit" class="btn btn-primary btn-lg btn-block"
				value="提交" />





		</form>



	</div>
	<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>

	<script
			src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


	<script type="text/javascript">


		$(document).ready(function(){
			$("#room_number").focusout(function(){
			    var option=$("#building option:selected");
			   $.get("ajax.do?operation=addClassroom&building="+option.val()+"&room_number="+$("#room_number").val(),

                   function(data, textStatus, req) {
			       	var rec=data.toString();
                       if(rec=="已经存在该教室了"){
                           alert("已经存在该教室了！");
					   }

			   }
			   )
			});

		});

	</script>



</body>
</html>