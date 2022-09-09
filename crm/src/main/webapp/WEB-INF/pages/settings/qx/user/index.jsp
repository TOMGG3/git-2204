<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="static/bootstrap/css/bootstrap.min.css">
    <!--bootstrapTable核心样式-->
    <link rel="stylesheet" href="static/bootstrap-table/bootstrap-table.min.css">
    <!--bootstrapDatetimePicker核心样式-->
    <link rel="stylesheet" href="static/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">
</head>
<body>
<!--路径导航-->
<ol class="breadcrumb">
    <li>设置</li>
    <li>权限</li>
    <li class="active">用户</li>
</ol>
<div class="panel-body">
    <!--查询条件-->
    <div class="well well-sm">
        <form id="searchForm" class="form-inline">
            <div class="row" style="margin-bottom: 15px;">
                <div class="form-group col-md-4">
                    <label>用户姓名</label>
                    <input type="text" id="name" class="form-control">
                </div>
                <div class="form-group col-md-4">
                    <label>部门名称</label>
                    <input type="text" id="deptNo" class="form-control">
                </div>
                <div class="form-group col-md-4">
                    <label>锁定状态</label>
                    <select id="lockState" class="form-control lockStateSelect"></select>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-4">
                    <label>开始时间</label>
                    <div class="input-group date myDatetime">
                        <input type="text" id="startTime" class="form-control" readonly>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label>结束时间</label>
                    <div class="input-group date myDatetime">
                        <input type="text" id="endTime" class="form-control" readonly>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <button type="button" class="btn btn-default" onclick="$('#tab').bootstrapTable('refresh')">搜索</button>
                    <button type="button" class="btn btn-default" onclick="$('#searchForm')[0].reset();$('#tab').bootstrapTable('refresh')">清空</button>
                </div>
            </div>
        </form>
    </div>
    <!--表格的工具栏-->
    <div id="toolbar">
        <button type="button" class="btn btn-primary" onclick="$('#saveModal').modal('show');">
            <span class="glyphicon glyphicon-plus"></span> 新增
        </button>
        <button type="button" class="btn btn-danger" onclick="removeBatch()">
            <span class="glyphicon glyphicon-trash"></span> 批量删除
        </button>
    </div>
    <table id="tab" class="table table-striped table-hover table-bordered"></table>
</div>
<!--新增-->
<div class="modal fade" id="saveModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">新增用户</h4>
            </div>
            <div class="modal-body">
                <form id="saveForm" class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">登录帐号<span style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="save-loginAct">
                        </div>
                        <label class="col-sm-2 control-label">用户姓名<span style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="save-name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="save-loginPwd" class="col-sm-2 control-label">登录密码<span style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="password" class="form-control" id="save-loginPwd">
                        </div>
                        <label for="save-confirmPwd" class="col-sm-2 control-label">确认密码<span style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="password" class="form-control" id="save-confirmPwd">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="save-email" class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="save-email">
                        </div>
                        <label for="save-expireTime" class="col-sm-2 control-label">失效时间</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <div class="input-group date myDatetime">
                                <input type="text" id="save-expireTime" class="form-control" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">锁定状态<span style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <select class="form-control lockStateSelect" id="save-lockState"></select>
                        </div>
                        <label class="col-sm-2 control-label">部门</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="save-deptNo">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="save-allowIps" class="col-sm-2 control-label">允许访问的IP</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="save-allowIps" style="width: 280%" placeholder="多个用逗号隔开">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="save()">保存</button>
            </div>
        </div>
    </div>
</div>
<!--编辑-->
<div class="modal fade" id="editModal" role="dialog">
    <div class="modal-dialog" role="document" style="width: 90%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">编辑用户</h4>
            </div>
            <div class="modal-body">
                <form id="editForm" class="form-horizontal" role="form">
                    <input type="hidden" id="edit-id">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">登录帐号<span style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-loginAct" readonly>
                        </div>
                        <label class="col-sm-2 control-label">用户姓名<span style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-email" class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-email">
                        </div>
                        <label for="edit-expireTime" class="col-sm-2 control-label">失效时间</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <div class="input-group date myDatetime">
                                <input type="text" id="edit-expireTime" class="form-control" readonly>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">锁定状态<span style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <select class="form-control lockStateSelect" id="edit-lockState"></select>
                        </div>
                        <label class="col-sm-2 control-label">部门</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-deptNo">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="edit-allowIps" class="col-sm-2 control-label">允许访问的IP</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-allowIps" style="width: 280%" placeholder="多个用逗号隔开">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="edit()">保存</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="static/js/jquery.js"></script>
<script type="text/javascript" src="static/bootstrap/js/bootstrap.min.js"></script>
<!--bootstrapTable核心js-->
<script type="text/javascript" src="static/bootstrap-table/bootstrap-table.min.js"></script>
<!--bootstrapTable中文语言包-->
<script type="text/javascript" src="static/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
<!--bootstrapDatetimePicker核心js-->
<script type="text/javascript" src="static/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<!--bootstrapDatetimePicker中文语言包-->
<script type="text/javascript" src="static/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">
    $(function (){
        initTable();
        initSelect();

        //模态框事件
        $('#saveModal').on('hidden.bs.modal', function (){
            $('#saveForm')[0].reset();
        });
        $('#editModal').on('hidden.bs.modal', function (){
            $('#editForm')[0].reset();
        });

        //配置日期组件
        $('.myDatetime').datetimepicker({
            //语言
            language: 'zh-CN',
            //日期格式
            format: 'yyyy-mm-dd hh:ii:ss',
            //周起始
            weekStart: 1,
            //选择日期后自动关闭
            autoclose: true,
            //默认打开界面
            startView: 'month',
            todayBtn: true,
            forceParse: false
        });
    });
    function initTable(){
        $('#tab').bootstrapTable({
            //http请求方式
            method: 'get',
            //请求路径
            url: 'user/page',
            //处理响应
            responseHandler: function (res) {
                if(res.code == 0){
                    return {
                        rows: res.data.list,
                        total: res.data.total
                    };
                }
                return [];
            },
            //开启分页
            pagination: true,
            //设置服务器端分页
            sidePagination: 'server',
            //设置每页条数
            pageSize: 10,
            //设置切换每页条数
            pageList: [10, 20, 50, 100],
            paginationPreText: '上一页',
            paginationNextText: '下一页',
            //设置查询的请求参数
            queryParamsType: '',
            queryParams: function (params){
                params.name = $('#name').val();
                params.deptNo = $('#deptNo').val();
                params.lockState = $('#lockState').val();
                params.startTime = $('#startTime').val();
                params.endTime = $('#endTime').val();
                return params;
            },
            //设置表格的工具栏
            toolbar: '#toolbar',
            //辅助功能
            clickToSelect: true,
            singleSelect: false,
            showRefresh: true,
            showToggle: true,
            showFullscreen: true,
            //表格中的列描述
            columns: [
                {checkbox: true},
                {field: 'id', visible: false},
                {title: '登录帐号', field: 'loginAct', halign: 'center'},
                {title: '用户姓名', field: 'name', halign: 'center'},
                {title: '部门名称', field: 'deptNo', halign: 'center'},
                {title: '邮箱', field: 'email', halign: 'center'},
                {title: '失效时间', field: 'expireTime', halign: 'center'},
                {title: '允许访问IP', field: 'allowIps', halign: 'center'},
                // {title: '锁定状态', field: 'lockState', halign: 'center', formatter(value){
                //     return value == '1' ? '启用' : '锁定';
                //     }},
                {title: '锁定状态', field: 'lockState', halign: 'center'},
                {title: '操作', halign: 'center', align: 'center',formatter: function(value, row){
                    //js中的函数调用问题:在字符串拼接中调用js函数，传递参数，如果参数值为字符串类型，需要手动使用引号包裹
                        // var str = "get(abc123)";
                        return '<button type="button" class="btn btn-warning btn-xs" onclick="get(\'' + row.id + '\')">编辑</button>&emsp;' +
                            '<button type="button" class="btn btn-danger btn-xs" onclick="remove(\'' + row.id + '\')">删除</button>';
                    }}
            ]
        });
    }
    function initSelect(){
        $.ajax('dicValue/list', {
            type: 'get',
            data: {
                typeCode: 'lockState'
            },
            success: function (res){
                if(res.code == 0){
                    var str = '<option value=""></option>';
                    $.each(res.data, function (){
                        str += '<option value="' + this.value + '">' + this.text + '</option>';
                    });
                    $('.lockStateSelect').html(str);
                }
            }
        });
    }
    function save(){
        $.ajax('user/save', {
            type: 'post',
            contentType: 'application/json',
            beforeSend: function (){
                //数据校验
                var loginAct = $('#save-loginAct').val();
                var name = $('#save-name').val();
                var loginPwd = $('#save-loginPwd').val();
                var confirmPwd = $('#save-confirmPwd').val();
                var lockState = $('#save-lockState').val();
                if(!loginAct){
                    alert('登陆账号不能为空');
                    return false;
                }
                if(!name){
                    alert('用户姓名不能为空');
                    return false;
                }
                if(!loginPwd){
                    alert('登陆密码不能为空');
                    return false;
                }
                if(!confirmPwd){
                    alert('确认密码不能为空');
                    return false;
                }
                if(loginPwd != confirmPwd){
                    alert('登陆密码与确认密码不一致');
                    return false;
                }
                if(!lockState){
                    alert('请选择锁定状态');
                    return false;
                }
            },
            data: JSON.stringify({
                loginAct: $('#save-loginAct').val(),
                name: $('#save-name').val(),
                loginPwd: $('#save-loginPwd').val(),
                email: $('#save-email').val(),
                expireTime: $('#save-expireTime').val(),
                deptNo: $('#save-deptNo').val(),
                allowIps: $('#save-allowIps').val(),
                lockState: $('#save-lockState').val()
            }),
            success: function (res) {
                if(res.code == 0){
                    alert('新增成功');
                    $('#saveModal').modal('hide');
                    $('#tab').bootstrapTable('refresh');
                }else{
                    alert(res.message);
                }
            }
        });
    }
    function get(id){
        $.ajax('user/get/' + id, {
            type: 'get',
            success: function (res){
                if(res.code == 0){
                    //数据回显
                    $('#edit-id').val(res.data.id);
                    $('#edit-loginAct').val(res.data.loginAct);
                    $('#edit-name').val(res.data.name);
                    $('#edit-email').val(res.data.email);
                    $('#edit-expireTime').val(res.data.expireTime);
                    $('#edit-lockState').val(res.data.lockState);
                    $('#edit-deptNo').val(res.data.deptNo);
                    $('#edit-allowIps').val(res.data.allowIps);

                    $('#editModal').modal('show');
                }
            }
        });
    }
    function edit(){
        $.ajax('user/edit', {
            type: 'put',
            contentType: 'application/json',
            beforeSend: function (){
                //数据校验
                var loginAct = $('#edit-loginAct').val();
                var name = $('#edit-name').val();
                var lockState = $('#edit-lockState').val();
                if(!loginAct){
                    alert('登陆账号不能为空');
                    return false;
                }
                if(!name){
                    alert('用户姓名不能为空');
                    return false;
                }
                if(!lockState){
                    alert('请选择锁定状态');
                    return false;
                }
            },
            data: JSON.stringify({
                id: $('#edit-id').val(),
                loginAct: $('#edit-loginAct').val(),
                name: $('#edit-name').val(),
                email: $('#edit-email').val(),
                expireTime: $('#edit-expireTime').val(),
                deptNo: $('#edit-deptNo').val(),
                allowIps: $('#edit-allowIps').val(),
                lockState: $('#edit-lockState').val()
            }),
            success: function (res) {
                if(res.code == 0){
                    alert('编辑成功');
                    $('#editModal').modal('hide');
                    $('#tab').bootstrapTable('refresh');
                }
            }
        });
    }
    function remove(id){
        if(!confirm('确定要删除吗?')){
            return;
        }
        $.ajax('user/remove/'+id, {
            type: 'delete',
            success: function (res){
                if(res.code == 0){
                    alert('删除成功');
                    $('#tab').bootstrapTable('refresh');
                }
            }
        });
    }
    function removeBatch(){
        var rows = $('#tab').bootstrapTable('getSelections');
        if(rows.length == 0){
            alert('请选择要删除的数据');
            return;
        }
        if(!confirm('确定要删除吗?')){
            return;
        }
        var ids = [];
        $.each(rows, function (){
            ids.push(this.id);
        });
        $.ajax('user/remove', {
            type: 'post',
            data: {
                _method: 'delete',
                ids: ids.join()
            },
            success: function (res){
                if(res.code == 0){
                    alert('删除成功');
                    $('#tab').bootstrapTable('refresh');
                }
            }
        });
    }
</script>
</body>
</html>