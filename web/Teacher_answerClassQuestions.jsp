<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8"/>
    <title>学生问题答疑</title>
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



    <h1>学生问题答疑</h1>

    <table border="1" class="table table-striped">
        <tr>

            <th>学生学号</th>
            <th>学生姓名</th>
            <th>问题</th>
            <th>提问时间</th>

            <th>答案编辑</th>
            <th>提交答案</th>


        </tr>
        <c:forEach var="question" items="${sessionScope.oneClassUnansweredQuestions}">
            <tr>
                <td>${question.student_id}</td>
                <td>${question.student_name}</td>
                <td>${question.question}</td>
                <td>${question.question_date}   ${question.question_time}</td>

                <td><textarea class="form-control" rows="5" name="answer" id="answer${question.id}"></textarea></td>


                <td><input id="selectCourse"  onclick="insertAnswerToQuestion(${question.id},$('#answer${question.id}').val(),${question.class_id})" type="button" class="btn btn-info btn-block btn-sm" value="提交答案"></td>



            </tr>
        </c:forEach>


    </table>

    <script type="text/javascript">
        function insertAnswerToQuestion(question_id,answer,class_id) {


            $.get("ajax.do?operation=insertAnswerToQuestion&question_id="+question_id+"&answer="+answer+"&class_id="+class_id,
                function(data, textStatus, req) {
                    var rec=data.toString();

                    if(rec=="ok"){
                        location.reload();
                    }
                }
            );
        }

    </script>

</div>

<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>

<script
        src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>