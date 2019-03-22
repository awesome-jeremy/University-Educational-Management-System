<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8"/>
    <title>教师评价</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link
            href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
            rel="stylesheet">
    <link rel="stylesheet" href="assets/css/font-awesome.min.css"/>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@include file="Student_include.jsp" %>
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

    <h1 id="test">教师评价</h1>
    <form action="gradeTeachers.do" method="post">


        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <table border="1" class="table table-striped">
                    <tr>

                        <th>课程代号</th>
                        <th>课程名称</th>
                        <th>教师名字</th>
                        <th>打分</th>
                        <th>评语</th>

                    </tr>
                    <c:forEach var="lesson" items="${sessionScope.myClassesTable}">
                        <c:if test="${lesson.teacher_score==-1}">
                            <tr>
                                <td>${lesson.id}</td>
                                <td>${lesson.name}</td>
                                <td>${lesson.teacher_name}</td>


                                <%--<td><input type="text" class="form-control" name="${lesson.id}score"--%>
                                           <%--id="${lesson.id}score"></td>--%>
                                <td>
                                    <select name="${lesson.id}score" class="form-control">
                                        <option value="100">100</option>
                                        <option value="90">90</option>
                                        <option selected value="80">80</option>
                                        <option value="70">70</option>
                                        <option value="60">60</option>
                                        <option value="50">50</option>
                                        <option value="40">40</option>
                                        <option value="30">30</option>
                                    </select>
                                </td>
                                <td><textarea class="form-control" rows="3" name="${lesson.id}comment"
                                              id="${lesson.id}comment"></textarea></td>


                            </tr>
                        </c:if>

                    </c:forEach>


                </table>

                <input type="submit" class="btn btn-primary btn-lg btn-block" id="submit" value="提交"/>

            </div>
        </div>
    </form>
</div>


<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>

<script
        src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>