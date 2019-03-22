<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8"/>
    <title>学生信息修改</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link
            href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
            rel="stylesheet">
    <link rel="stylesheet" href="assets/css/font-awesome.min.css"/>
    <%@include file="Manager_include.jsp" %>
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
    <form action="teacherEdit.do" method="post">

        <div class="form-group">
            <label for="name">姓名</label> <input type="text" class="form-control"
                                                name="name" id="name" value="${sessionScope.editTeacher.name}" >
        </div>
        <div class="form-group">
            <label for="password">密码</label> <input type="text" class="form-control"
                                                    name="password" id="password"
                                                    value="${sessionScope.editTeacher.password}">
        </div>

        <div class="form-group">
            <label>性别</label> <input type="radio" name="sex" id="optionsRadios1"
                                     value="男" checked> 男 <input type="radio" name="sex"
                                                                 id="optionsRadios2" value="女"> 女
        </div>

        <script language="javascript">
            //获取网页中的单选按钮，挑选其中name=angel，value=0的，让其选中
            $("input[type=radio][name=sex][value=${sessionScope.editTeacher.sex}]").attr("checked",'checked')
        </script>

        <div class="form-group">
            <label for="nation">民族</label> <input type="text" value="${sessionScope.editTeacher.nation}"
                                                  class="form-control" name="nation" id="nation">
        </div>


        <div class="form-group">
            <label for="birth">出生日期</label> <input type="text"
                                                   class="form-control" name="birth" id="birth"
                                                   value="${sessionScope.editTeacher.birth}"
        >
        </div>
        <div class="form-group">
            <label for="id_number">身份证号</label> <input type="text"
                                                       class="form-control" name="id_number" id="id_number"
                                                       value="${sessionScope.editTeacher.id_number}"
        >
        </div>

        <div class="form-group">
            <label for="education">学历</label> <input type="text"
                                                 class="form-control" name="education" id="education"
                                                 value="${sessionScope.editTeacher.education}">
        </div>
        <div class="form-group">
            <label for="professional_title">居住地址</label> <input type="text"
                                                     class="form-control" name="professional_title" id="professional_title"
                                                     value="${sessionScope.editTeacher.professional_title}">
        </div>


        <input type="submit" class="btn btn-primary btn-lg btn-block" value="确认修改"/>

    </form>


</div>

<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>

<script
        src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>