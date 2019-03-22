<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8"/>
    <title>课程打分</title>
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
            <h1>课程打分</h1>
            <table border="1" class="table table-striped">
                <tr>

                    <th>课程代号</th>
                    <th>课程名称</th>

                    <th>打分</th>


                </tr>
                <c:forEach var="lesson" items="${sessionScope.allMyTeachClasses}">
                    <tr>
                        <td>${lesson.id}</td>
                        <td>${lesson.name}</td>

                            <%--<td><input id="answerQuestion"  onclick="window.location='askQuestion.do?class_id=${lesson.id}&teacher_id=${lesson.teacher_id}'" type="button" class="btn btn-info btn-block btn-sm" value="点我提问"></td>--%>
                        <td>
                            <button class="btn btn-primary btn-block btn-sm" type="button"
                                    onclick="window.location='showGradeClasses.do?class_id=${lesson.id}'">
                                打分
                            </button>
                        </td>
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