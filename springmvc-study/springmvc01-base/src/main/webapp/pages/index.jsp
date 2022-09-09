<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
</head>
<body>
    <div style="text-align: center;">
        <h1>首页</h1>
        <p><a href="/hello.do">测试</a></p>
        <p><a href="/url.do">url1</a></p>
        <p><a href="/getUrl.do">url2</a></p>
        <p><a href="/test.do">url3</a></p>
        <p><a href="/student/list.do">学生查询</a></p>
        <p><a href="/student/save.do">学生新增</a></p>
        <p><a href="/student/edit.do">学生编辑</a></p>
        <p><a href="/student/remove.do">学生删除</a></p>
        <p><a href="/doGet.do">get请求1</a></p>
        <form action="/doGet.do" method="post">
            <button type="submit">post请求1</button>
        </form>

        <p><a href="/doPost.do">get请求2</a></p>
        <form action="/doPost.do" method="post">
            <button type="submit">post请求2</button>
        </form>
        <p><a href="/testStr.do">String返回值</a></p>
        <p><a href="/user/list.do">请求转发到页面</a></p>
        <p><a href="/user/save.do">重定向到页面</a></p>
        <p><a href="/user/edit.do">请求转发到处理器方法</a></p>
        <p><a href="/user/remove.do">重定向到处理器方法</a></p>
        <p><a href="/param.do">Model对象使用</a></p>
        <p><a href="/simple2.do?num=100">@RequestParam</a></p>
    </div>
</body>
</html>
