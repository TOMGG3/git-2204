<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
    <div style="text-align: center;">
        <h1>首页</h1>
        <h2>当前登陆用户：${sessionScope.loginUser}</h2>
        <p><a href="/hello.do">测试</a></p>
        <p><a href="/student/list.do">学生查询</a></p>
        <p><a href="/student/save.do">学生新增</a></p>
        <p><a href="/student/edit.do">学生编辑</a></p>
        <p><a href="/student/remove.do">学生删除</a></p>
        <p><a href="/logout.do">退出系统</a></p>
    </div>
</body>
</html>
