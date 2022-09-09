<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="UTF-8">
    <title>新增部门</title>
    <link rel="stylesheet" href="static/bootstrap/css/bootstrap.min.css">
</head>
<body>
    <div>
        <h1 style="text-align: center">新增部门</h1>
        <form action="dept/save" method="post" class="form-horizontal" style="width: 450px;margin: 10px auto">
            <div class="form-group">
                <label class="control-label col-md-3">部门名称</label>
                <div class="col-md-9">
                    <input type="text" name="dname" class="form-control">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-md-3">部门位置</label>
                <div class="col-md-9">
                    <input type="text" name="loc" class="form-control">
                </div>
            </div>
            <div class="form-group" style="text-align: center">
                <button type="submit" class="btn btn-primary">确定</button>
                <button type="reset" class="btn btn-default">取消</button>
                <button type="button" onclick="history.back()" class="btn btn-default">返回</button>
            </div>
        </form>
    </div>
</body>
</html>