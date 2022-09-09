<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <div style="text-align: center">
<%--        <form action="/array.do">--%>
        <form action="/list.do">
            <button type="submit">提交</button>
            <table border="1" width="450" align="center">
                <tr>
                    <th></th>
                    <th>编号</th>
                    <th>姓名</th>
                    <th>年龄</th>
                </tr>
                <tr>
                    <td><input type="checkbox" name="ids" value="1"></td>
                    <td>1</td>
                    <td>张三</td>
                    <td>20</td>
                </tr>
                <tr>
                    <td><input type="checkbox" name="ids" value="2"></td>
                    <td>2</td>
                    <td>李四</td>
                    <td>21</td>
                </tr>
                <tr>
                    <td><input type="checkbox" name="ids" value="3"></td>
                    <td>3</td>
                    <td>王五</td>
                    <td>22</td>
                </tr>
                <tr>
                    <td><input type="checkbox" name="ids" value="4"></td>
                    <td>4</td>
                    <td>赵柳</td>
                    <td>25</td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>
