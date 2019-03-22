<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8"/>
    <title>学生对我的课程评价</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link
            href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
            rel="stylesheet">
    <link rel="stylesheet" href="assets/css/font-awesome.min.css"/>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@include file="Teacher_include.jsp" %>
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

    <div class="row">
        <div class="col-sm-6 col-sm-offset-3">
            <h1>学生对我的课程评价</h1>
            <table border="1" class="table table-striped">
                <tr>

                    <th>学生学号</th>
                    <th>学生姓名</th>
                    <th>学生给我的打分</th>
                    <th>学生对我的评价</th>


                </tr>
                <c:forEach var="comment" items="${sessionScope.allCommentForOneClass}">
                    <tr>
                        <td>${comment.student_id}</td>
                        <td>${comment.student_name}</td>
                        <td>${comment.teacher_score}</td>
                        <td>${comment.comment}</td>

                    </tr>
                </c:forEach>


            </table>

        </div>
    </div>
</div>

<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>

<script
        src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>