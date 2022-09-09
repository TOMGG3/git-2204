<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="UTF-8">
    <title>部门列表</title>
    <link rel="stylesheet" href="static/bootstrap/css/bootstrap.min.css">
</head>
<body>
    <div style="text-align: center" class="container-fluid">
        <h1>部门列表</h1>
        <a href="page/dept/save" class="btn btn-primary">新增</a>
        <table class="table table-striped table-hover table-bordered">
            <tr>
                <th>部门编号</th>
                <th>部门名称</th>
                <th>部门位置</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${deptList}" var="dept">
            <tr>
                <td>${dept.deptno}</td>
                <td>${dept.dname}</td>
                <td>${dept.loc}</td>
                <td>
                    <a href="dept/get/${dept.deptno}" class="btn btn-warning btn-xs">编辑</a>&emsp;&emsp;
                    <a href="javascript:remove(${dept.deptno});" class="btn btn-danger btn-xs">删除</a>
                </td>
            </tr>
            </c:forEach>
        </table>
    </div>
    <form id="deleteForm" method="post">
        <input type="hidden" name="_method" value="delete">
    </form>
    <script type="text/javascript">
        function remove(id){
            if(confirm('确定删除数据吗?')){
                //通过选择器获取form表单对象
                var form = document.querySelector('#deleteForm');
                //设置表单提交路径
                form.action = 'dept/remove/' + id;
                //提交表单
                form.submit();
            }
        }
    </script>
</body>
</html>