<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="UTF-8">
    <link href="static/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
</head>
<body>
<div style="position: absolute; top: 0px; left: 0px; width: 60%;">
    <img src="static/image/IMG_7114.JPG" style="width: 100%; height: 90%; position: relative; top: 50px;">
</div>
<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
    <div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">CRM &nbsp;<span style="font-size: 12px;">&copy;2022&nbsp;动力节点</span></div>
</div>

<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
    <div style="position: absolute; top: 0px; right: 60px;">
        <div class="page-header">
            <h1>登录</h1>
        </div>
        <form class="form-horizontal" role="form">
            <div class="form-group form-group-lg">
                <div style="width: 350px;">
                    <input class="form-control" type="text" placeholder="用户名" id="loginAct">
                </div>
                <div style="width: 350px; position: relative;top: 20px;">
                    <input class="form-control" type="password" placeholder="密码" id="loginPwd">
                </div>
                <div class="checkbox"  style="position: relative;top: 30px; left: 10px;">
                    <label>
                        <input type="checkbox"> 十天内免登录
                    </label>
                    <span id="msg"></span>
                </div>
                <button type="button" class="btn btn-primary btn-lg btn-block"  style="width: 350px; position: relative;top: 45px;" onclick="login()">登录</button>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript" src="static/js/jquery.js"></script>
<script type="text/javascript" src="static/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
    function login(){
        $.ajax('auth/login', {
            type: 'post',
            data: {
                loginAct: $('#loginAct').val(),
                loginPwd: $('#loginPwd').val()
            },
            beforeSend: function (){
                var loginAct = $('#loginAct').val();
                var loginPwd = $('#loginPwd').val();
                if(!loginAct){
                    alert('登陆账号不能为空');
                    return false;
                }
                if(!loginPwd){
                    alert('登陆密码不能为空');
                    return false;
                }
            },
            success: function (res) {
                if(res.code == 0){
                    //登陆成功，前端页面跳转
                    location.href = 'page/workbench/index';
                }else{
                    alert(res.message);
                }
            }
        });
    }
</script>
</body>
</html>