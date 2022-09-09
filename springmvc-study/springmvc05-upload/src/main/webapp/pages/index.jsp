<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!--
    在当前页面中所有路径前追加内容，限制条件仅在相对路径
    -->
    <base href="${pageContext.request.contextPath}/">
    <title>首页</title>
</head>
<body>
    <div style="text-align: center">
        <h1>首页</h1>
        <h2>单个文件上传</h2>
        <!--
        1.上传文件表单必须使用post请求
        2.form表单的enctype属性：
        application/x-www-form-urlencoded 默认值，将表单中的数据以名值形式提交到后台
        multipart/form-data 提交二进制文件数据
        3.上传组件<input type="file">
        -->
        <form action="upload" method="post" enctype="multipart/form-data">
            <p>文件：<input type="file" name="file"></p>
            <p><button type="submit">上传</button></p>
        </form>
        <h2>批量文件上传</h2>
        <form action="uploads" method="post" enctype="multipart/form-data">
            <p>文件1：<input type="file" name="file"></p>
            <p>文件2：<input type="file" name="file"></p>
            <p>文件3：<input type="file" name="file"></p>
            <p><button type="submit">上传</button></p>
        </form>
        <h2>路径跳转</h2>
        <!--在路径前添加contextPath使路径成为绝对路径，动态获取当前项目根路径-->
        <a href="${pageContext.request.contextPath}/pages/college/course.jsp">跳转到course.jsp</a><br>
        <a href="${pageContext.request.contextPath}/pages/system/menu.jsp">跳转到menu.jsp</a><br>
        <a href="${pageContext.request.contextPath}/user/find.do">user/find</a><br>
        <a href="${pageContext.request.contextPath}/student/find.do">student/find</a><br>
        <hr>
        <a href="pages/college/course.jsp">跳转到course.jsp</a><br>
        <a href="pages/system/menu.jsp">跳转到menu.jsp</a><br>
        <a href="user/find.do">user/find</a><br>
        <a href="student/find.do">student/find</a><br>
    </div>
</body>
</html>
