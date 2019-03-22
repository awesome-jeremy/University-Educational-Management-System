<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8" />
<title>课程信息添加</title>
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
</head>
<body>
	<div id="mainContent">
		<!-- 在这里编辑我的代码 -->
		<h2>添加课程信息:</h2>
		<form action="addClass.do" method="post">
			<div class="form-group">
				<label for="name">课程名</label> <input type="text" class="form-control"
					name="name" id="name" placeholder="例:Java Web">
			</div>

			<div class="form-group">
				<label>任课教师</label>
				<select name="teacher" id="teacher">
					<c:forEach var="teacher" items="${sessionScope.teachers}" varStatus="status">
						<option value="${teacher.id},${teacher.name}">${teacher.id}-----${teacher.name}</option>
					</c:forEach>
				</select>
			</div>

			<div class="form-group">
				<label for="info">课程介绍</label>
				<textarea name="info" id="info" cols="60" rows="8" placeholder="例:走过不要错过，Java Web开班啦！" ></textarea>
			</div>

			<div class="form-group">
				<label>上课教室</label>
				 <select name="building" id="building">
					 <c:forEach var="var" items="${applicationScope.buildings}" varStatus="status">
						 <option value="${var}">${var}</option>
					 </c:forEach>
				</select>

				<select name="room" id="room">
					<c:forEach var="room" items="${sessionScope.第1教学大楼room_numbers}">
						<option value="${room}">${room}</option>
					</c:forEach>

				</select>
				
			</div>
			<div class="form-group">
				<label>上课时间</label>
				<select name="day" id="day" >
					<option value="1,星期一">星期一</option>
					<option value="2,星期二">星期二</option>
					<option value="3,星期三">星期三</option>
					<option value="4,星期四">星期四</option>
					<option value="5,星期五">星期五</option>
				
				</select>
				 <select name="time" id="time">
					<option value="0,1-2节">1-2节</option>
					<option value="1,3-4节">3-4节</option>
					<option value="2,3-5节">3-5节</option>
					<option value="3,6-7节">6-7节</option>
					<option value="4,8-9节">8-9节</option>
					<option value="5,10-12节">10-12节</option>
				
				</select>
			</div>
			
			<div class="form-group">
			<input type="button" class="btn  btn-small btn-info"  id="check" value="检测时间地点是否冲突">

			
			</div>

			<h1 id="conflict"></h1>
			<input type="submit" class="btn btn-primary btn-lg btn-block" id="submit"  value="提交"/>

		</form>



	</div>

	<script type="text/javascript">
		$("#building").change(function(){

            var option=$("#building option:selected");
            $.get("ajax.do?operation=getRoom_numbers&building="+option.val(),

                function(data, textStatus, req) {
                    var rec=data.toString();
                    var rooms=rec.split(',');
                    var roomOptions="";
                    for(var i=0; i<rooms.length; i++){
                        roomOptions+="<option value='"+rooms[i]+"'>"+rooms[i]+"</option>";
						$("#room").html(roomOptions);
                    }
                }
            )

        });

		$("#check").click(function () {
		    var building=$("#building option:selected");
		    var room=$("#room option:selected");
			var day=$("#day option:selected");
			var time=$("#time option:selected");

			$.get("ajax.do?operation=location_time_conflict&building="+building.val()+"&room_number="+room.val()
				+"&day="+day.val()+"&time="+time.val(),

                function(data, textStatus, req) {
                    var rec=data.toString();
					$("#conflict").html(rec);
                }

			);

        });



	</script>

	<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>

	<script
		src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>