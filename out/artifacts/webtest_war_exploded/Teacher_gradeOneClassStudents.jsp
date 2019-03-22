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


    <form action="grade.do" method="post">


        <div class="row">
            <div class="col-sm-4 col-sm-offset-4">
                <h1>课程打分</h1>
                <table border="1" class="table table-striped">
                    <tr>

                        <th>学生学号</th>
                        <th>学生姓名</th>
                        <th>分数</th>


                    </tr>
                    <c:forEach var="student" items="${sessionScope.oneClassNotScoredStudents}">
                        <tr>
                            <td>${student.id}</td>
                            <td>${student.name}</td>


                            <td><input type="text" class="form-control" name="${student.id}" id="${question.id}"></td>


                        </tr>
                    </c:forEach>


                </table>

                <input type="submit" class="btn btn-primary btn-lg btn-block" id="submit" value="提交"/>

            </div>
        </div>
    </form>
</div>

<script type="text/javascript">
    function gradeStudent(question_id, answer, class_id) {


        $.get("ajax.do?operation=insertAnswerToQuestion&question_id=" + question_id + "&answer=" + answer + "&class_id=" + class_id,
            function (data, textStatus, req) {
                var rec = data.toString();

                if (rec == "ok") {
                    location.reload();
                }
            }
        );
    }

</script>


<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>

<script
        src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>