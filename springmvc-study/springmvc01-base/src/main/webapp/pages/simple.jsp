<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div style="text-align: center">
        <form action="/simple.do" method="post">
            <p>
                <label>编号：</label>
                <input type="text" name="id">
            </p>
            <p>
                <label>姓名：</label>
                <input type="text" name="name">
            </p>
            <p>
                <label>生日：</label>
                <input type="date" name="birth">
            </p>
            <p>
                <button type="submit">提交</button>
            </p>
        </form>
    </div>
</body>
</html>
