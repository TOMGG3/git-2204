<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆</title>
</head>
<body>
    <div style="text-align: center">
        <h1>请登陆</h1>
        <form action="/login.do" method="post">
            <p style="color: red;">
                ${message}
            </p>
            <p>
                <label>用户名：</label>
                <input type="text" name="username">
            </p>
            <p>
                <label>密码：&emsp;</label>
                <input type="password" name="password">
            </p>
            <p>
                <button type="submit">登陆</button>
            </p>
        </form>
    </div>
</body>
</html>
