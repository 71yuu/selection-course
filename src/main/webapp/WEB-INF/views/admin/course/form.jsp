<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>教务管理系统 | 管理员</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.7 -->
    <link rel="stylesheet" href="/static/assets/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="/static/assets/bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="/static/assets/bower_components/Ionicons/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="/static/assets/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="/static/assets/css/skins/_all-skins.min.css">
    <!-- Select2 -->
    <link rel="stylesheet" href="/static/assets/bower_components/select2/dist/css/select2.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Google Font -->
    <link rel="stylesheet" href="https://fonts.lug.ustc.edu.cn/css?family=Source+Sans+Pro:300,400,600,700,300italic,400italic,600italic">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <header class="main-header">
        <!-- Logo -->
        <a href="/admin/index" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini"><b>管</b></span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>教务管理系统</b></span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>

            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <!-- User Account: style can be found in dropdown.less -->
                    <li class="dropdown user user-menu">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                            <img src="/static/assets/img/user2-160x160.jpg" class="user-image" alt="User Image">
                            <span class="hidden-xs">${userLogin.userName}</span>
                        </a>
                        <ul class="dropdown-menu">
                            <!-- User image -->
                            <li class="user-header">
                                <img src="/static/assets/img/user2-160x160.jpg" class="img-circle" alt="User Image">

                                <p>
                                    ${userLogin.userName}
                                </p>
                            </li>
                            <!-- Menu Footer-->
                            <li class="user-footer">
                                <div class="pull-right">
                                    <a href="logout" class="btn btn-default btn-flat">退出登录</a>
                                </div>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <ul class="sidebar-menu" data-widget="tree">
                <li class="active treeview">
                    <a href="#">
                        <i class="fa  fa-archive"></i> <span>课程管理</span>
                        <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                        </span>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="/admin/course/list"><i class="fa fa-circle-o"></i>课程列表</a></li>
                        <li class="active"><a href="/admin/course/form"><i class="fa fa-circle-o"></i>新增课程</a></li>
                    </ul>
                </li>
                <li class="treeview">
                    <a href="#">
                        <i class="fa  fa-user-plus"></i> <span>教师管理</span>
                        <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                        </span>
                    </a>
                    <ul class="treeview-menu">
                        <li class="active"><a href="/admin/teacher/list"><i class="fa fa-circle-o"></i>教师列表</a></li>
                        <li><a href="/admin/teacher/form"><i class="fa fa-circle-o"></i>新增教师</a></li>
                    </ul>
                </li>
                <li class="treeview">
                    <a href="#">
                        <i class="fa  fa-users"></i> <span>学生管理</span>
                        <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                        </span>
                    </a>
                    <ul class="treeview-menu">
                        <li  class="active"><a href="/admin/student/list"><i class="fa fa-circle-o"></i>学生列表</a></li>
                        <li><a href="/admin/student/form"><i class="fa fa-circle-o"></i>新增学生</a></li>
                    </ul>
                </li>
                <li class="treeview">
                    <a href="#">
                        <i class="fa  fa-mortar-board"></i> <span>院系管理</span>
                        <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                        </span>
                    </a>
                    <ul class="treeview-menu">
                        <li class="active"><a href="/admin/college/list"><i class="fa fa-circle-o"></i>院系列表</a></li>
                        <li><a href="/admin/college/form"><i class="fa fa-circle-o"></i>新增院系</a></li>
                    </ul>
                </li>
                <li class="treeview">
                    <a href="#">
                        <i class="fa  fa-user-secret"></i> <span>选课管理</span>
                        <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                        </span>
                    </a>
                    <ul class="treeview-menu">
                        <li class="active"><a href="/admin/selectedcourse/index"><i class="fa fa-circle-o"></i>选课列表</a></li>
                    </ul>
                </li>
            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Main content -->
        <section class="content">
            <!-- Main row -->
            <div class="row">
                <div class="col-xs-12">
                    <div class="box box-warning">
                        <div class="box-header with-border">
                            <h3 class="box-title">${course.courseId == null ? "新增" : "编辑"}课程</h3>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body">
                            <form id="courseForm" role="form" action="/admin/course/save" method="post">
                                <input type="hidden" name="courseId" value="${course.courseId}"/>
                                <!-- text input -->
                                <div class="form-group">
                                    <label>课程名称</label>
                                    <input id="courseName" name="courseName" type="text" class="form-control required" value="${course.courseName}" placeholder="请输入课程名称">
                                </div>
                                <div class="form-group">
                                    <label>任课老师</label>
                                    <select id="teacherId" name="teacherId" class="form-control select2 required" style="width: 100%;">
                                        <c:forEach items="${teacherList}" var="teacher">
                                            <option value="${teacher.userId}" ${course.teacherId == teacher.userId ? "selected" : ""}>${teacher.userName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>课程时间</label>
                                    <input id="courseTime" name="courseTime" type="text" class="form-control required" value="${course.courseTime}" placeholder="请输入课程时间，例如：周三（1-4）节">
                                </div>
                                <div class="form-group">
                                    <label>课程教室</label>
                                    <input id="classRoom" name="classRoom" type="text" class="form-control required" value="${course.classRoom}" placeholder="请输入课程教室，例如 13-501">
                                </div>
                                <div class="form-group">
                                    <label>课程周数</label>
                                    <input id="courseWeek" name="courseWeek" type="text" class="form-control required digits" value="${course.courseWeek}" placeholder="请输入课程周数：例如 16">
                                </div>
                                <div class="form-group">
                                    <label>课程类型</label>
                                    <select id="courseType" name="courseType" class="form-control required" style="width: 100%;">
                                        <option value="必修课" ${course.courseType.equals("必修课") ? "selected" : ""}>必修课</option>
                                        <option value="选修课" ${course.courseType.equals("选修课") ? "selected" : ""}>选修课</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>所属院系</label>
                                    <select id="collegeId" name="collegeId" class="form-control required" style="width: 100%;">
                                        <c:forEach items="${collegeList}" var="college">
                                            <option value="${college.collegeId}" ${college.collegeId == course.collegeId ? "selected" : ""}>${college.collegeName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>学分</label>
                                    <input id="score" name="score" type="text" class="form-control required digits" value="${course.score}" placeholder="请输入学分，例如：3">
                                </div>
                                <div class="box-footer">
                                    <button type="button" class="btn btn-default" onclick="history.go(-1);">返回</button>
                                    <button type="submit" class="btn btn-info pull-right">提交</button>
                                </div>
                            </form>
                        </div>
                        <!-- /.box-body -->
                    </div>
                </div>
            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            <b>Version</b> 1.0.0
        </div>
        <strong>Copyright &copy; 2019-2019 傅加发. </strong> All rights
        reserved.
    </footer>
</div>
<!-- ./wrapper -->

<!-- jQuery 3 -->
<script src="/static/assets/bower_components/jquery/dist/jquery.min.js"></script>
<!-- jQuery UI 1.11.4 -->
<script src="/static/assets/bower_components/jquery-ui/jquery-ui.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="/static/assets/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="/static/assets/js/adminlte.min.js"></script>
<!-- Select2 -->
<script src="/static/assets/bower_components/select2/dist/js/select2.full.min.js"></script>
<!-- jQuery Validation 1.14.0 -->
<script src="/static/assets/plugins/jquery-validation/js/jquery.validate.js"></script>
<script src="/static/assets/plugins/jquery-validation/js/additional-methods.js"></script>
<script src="/static/assets/plugins/jquery-validation/js/localization/messages_zh.js"></script>
</body>

<script type="text/javascript">
    $(function () {
        //Initialize Select2 Elements
        $('.select2').select2()
    });

    // validate
    $("#courseForm").validate({
        errorElement: 'span',
        errorClass: 'help-block',

        errorPlacement: function(error, element) {
            element.parent().attr("class", "form-group has-error");
            error.insertAfter(element);
        }
    });

</script>
</html>
