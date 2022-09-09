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
    <li>工作台</li>
    <li>市场活动</li>
    <li class="active">列表</li>
</ol>
<div class="panel-body">
    <!--查询条件-->
    <div class="well well-sm">
        <form id="searchForm" class="form-inline">
            <div class="row" style="margin-bottom: 15px;">
                <div class="form-group col-md-4">
                    <label>名称&emsp;&emsp;</label>
                    <input type="text" id="name" class="form-control">
                </div>
                <div class="form-group col-md-4">
                    <label>所有者&emsp;</label>
                    <select id="lockState" class="form-control userSelect"></select>
                </div>
            </div>
            <div class="row">
                <div class="form-group col-md-4">
                    <label>开始日期</label>
                    <div class="input-group date myDatetime">
                        <input type="text" id="startDate" class="form-control" readonly>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    </div>
                </div>
                <div class="form-group col-md-4">
                    <label>结束日期</label>
                    <div class="input-group date myDatetime">
                        <input type="text" id="endDate" class="form-control" readonly>
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
                <h4 class="modal-title" id="myModalLabel">新增市场活动</h4>
            </div>
            <div class="modal-body">
                <form id="saveForm" class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <select id="save-owner" class="form-control userSelect"></select>
                        </div>
                        <label class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="save-name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">开始日期</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="date" id="save-startDate" class="form-control">
                        </div>
                        <label class="col-sm-2 control-label">结束日期</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="date" id="save-endDate" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">成本</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="save-cost">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <textarea rows="5" class="form-control" id="save-description" style="width: 280%"></textarea>
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
                <h4 class="modal-title">编辑市场活动</h4>
            </div>
            <div class="modal-body">
                <form id="editForm" class="form-horizontal" role="form">
                    <input type="hidden" id="edit-id">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">所有者<span style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <select id="edit-owner" class="form-control userSelect"></select>
                        </div>
                        <label class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">开始日期</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="date" id="edit-startDate" class="form-control">
                        </div>
                        <label class="col-sm-2 control-label">结束日期</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="date" id="edit-endDate" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">成本</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <input type="text" class="form-control" id="edit-cost">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-10" style="width: 300px;">
                            <textarea rows="5" class="form-control" id="edit-description" style="width: 280%"></textarea>
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
            format: 'yyyy-mm-dd',
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
            url: 'activity/page',
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
                params.owner = $('#owner').val();
                params.startDate = $('#startDate').val();
                params.endDate = $('#endDate').val();
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
                {title: '名称', field: 'name', halign: 'center',formatter: function (value, row){
                    return '<a href="activity/detail/' + row.id +'">' + value + '</a>';
                    // return '<a href="page/workbench/activity/detail">' + value + '</a>';
                    }},
                {title: '所有者', field: 'userName', halign: 'center'},
                {title: '开始日期', field: 'startDate', halign: 'center'},
                {title: '结束日期', field: 'endDate', halign: 'center'},
                {title: '操作', halign: 'center', align: 'center',formatter: function(value, row){
                    return '<button type="button" class="btn btn-warning btn-xs" onclick="get(\'' + row.id + '\')">编辑</button>&emsp;' +
                        '<button type="button" class="btn btn-danger btn-xs" onclick="remove(\'' + row.id + '\')">删除</button>';
                }}
            ]
        });
    }
    function initSelect(){
        $.ajax('user/list', {
            type: 'get',
            success: function (res){
                if(res.code == 0){
                    var str = '<option value=""></option>';
                    $.each(res.data, function (){
                        str += '<option value="' + this.id + '">' + this.name + '</option>';
                    });
                    $('.userSelect').html(str);
                }
            }
        });
    }
    function save(){
        $.ajax('activity/save', {
            type: 'post',
            contentType: 'application/json',
            beforeSend: function (){
                //数据校验
                var name = $('#save-name').val();
                var owner = $('#save-owner').val();
                if(!name){
                    alert('市场活动名称不能为空');
                    return false;
                }
                if(!owner){
                    alert('请选择拥有者');
                    return false;
                }
            },
            data: JSON.stringify({
                owner: $('#save-owner').val(),
                name: $('#save-name').val(),
                startDate: $('#save-startDate').val(),
                endDate: $('#save-endDate').val(),
                cost: $('#save-cost').val(),
                description: $('#save-description').val()
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
        $.ajax('activity/get/' + id, {
            type: 'get',
            success: function (res){
                if(res.code == 0){
                    //数据回显
                    $('#edit-id').val(res.data.id);
                    $('#edit-owner').val(res.data.owner);
                    $('#edit-name').val(res.data.name);
                    $('#edit-startDate').val(res.data.startDate);
                    $('#edit-endDate').val(res.data.endDate);
                    $('#edit-cost').val(res.data.cost);
                    $('#edit-description').val(res.data.description);

                    $('#editModal').modal('show');
                }
            }
        });
    }
    function edit(){
        $.ajax('activity/edit', {
            type: 'put',
            contentType: 'application/json',
            beforeSend: function (){
                //数据校验
                var name = $('#edit-name').val();
                var owner = $('#edit-owner').val();
                if(!name){
                    alert('市场活动名称不能为空');
                    return false;
                }
                if(!owner){
                    alert('请选择拥有者');
                    return false;
                }
            },
            data: JSON.stringify({
                id: $('#edit-id').val(),
                owner: $('#edit-owner').val(),
                name: $('#edit-name').val(),
                startDate: $('#edit-startDate').val(),
                endDate: $('#edit-endDate').val(),
                cost: $('#edit-cost').val(),
                description: $('#edit-description').val()
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
        $.ajax('activity/remove/'+id, {
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
        $.ajax('activity/remove', {
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