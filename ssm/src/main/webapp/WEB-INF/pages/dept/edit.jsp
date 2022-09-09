<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="UTF-8">
    <title>编辑部门</title>
    <link rel="stylesheet" href="static/bootstrap/css/bootstrap.min.css">
</head>
<body>
    <div style="text-align: center">
        <h1>编辑部门</h1>
        <form action="dept/edit" method="post" class="form-horizontal" style="width: 450px;margin: 10px auto">
            <input type="hidden" name="deptno" value="${dept.deptno}">
            <input type="hidden" name="_method" value="put">
            <div class="form-group">
                <label class="control-label col-md-3">部门名称：</label>
                <div class="col-md-9">
                    <input type="text" name="dname" value="${dept.dname}" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-3">部门位置：</label>
                <div class="col-md-9">
                    <input type="text" name="loc" value="${dept.loc}" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">确定</button>
                <button type="reset" class="btn btn-default">取消</button>
                <button type="button" onclick="history.back()" class="btn btn-default">返回</button>
            </div>
        </form>
    </div>
</body>
</html>