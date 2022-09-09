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
</head>
<body>
<!--路径导航-->
<ol class="breadcrumb">
    <li>设置</li>
    <li>数据字典</li>
    <li class="active">字典类型</li>
</ol>
<div class="panel-body">
    <!--查询条件-->
    <div class="well well-sm">
        <form id="searchForm" class="form-inline">
            <div class="row">
                <div class="form-group col-md-4">
                    <label>类型名称</label>
                    <input type="text" id="name" class="form-control">
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
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">新增字典类型</h4>
            </div>
            <div class="modal-body">
                <form id="saveForm" class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">编码<span style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="save-code">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="save-name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" id="save-description"></textarea>
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
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title">编辑字典类型</h4>
            </div>
            <div class="modal-body">
                <form id="editForm" class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">编码<span style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit-code" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">名称<span style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit-name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" id="edit-description"></textarea>
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
<script type="text/javascript">
    $(function (){
        initTable();

        //模态框事件
        $('#saveModal').on('hidden.bs.modal', function (){
            $('#saveForm')[0].reset();
        });
        $('#editModal').on('hidden.bs.modal', function (){
            $('#editForm')[0].reset();
        });
    });
    function initTable(){
        $('#tab').bootstrapTable({
            method: 'get',
            url: 'dicType/page',
            responseHandler: function (res) {
                if(res.code == 0){
                    return {
                        rows: res.data.list,
                        total: res.data.total
                    };
                }
                return [];
            },
            pagination: true,
            sidePagination: 'server',
            pageSize: 10,
            pageList: [10, 20, 50, 100],
            paginationPreText: '上一页',
            paginationNextText: '下一页',
            queryParamsType: '',
            queryParams: function (params){
                params.name = $('#name').val();
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
                {title: '序号', formatter(value,row,index) {
                        return index+1;
                    }},
                {title: '编码', field: 'code', halign: 'center'},
                {title: '名称', field: 'name', halign: 'center'},
                {title: '描述', field: 'description', halign: 'center'},
                {title: '操作', halign: 'center', align: 'center',formatter: function(value, row){
                    return '<button type="button" class="btn btn-warning btn-xs" onclick="get(\'' + row.code + '\')">编辑</button>&emsp;' +
                        '<button type="button" class="btn btn-danger btn-xs" onclick="remove(\'' + row.code + '\')">删除</button>';
                }}
            ]
        });
    }
    function save(){
        $.ajax('dicType/save', {
            type: 'post',
            contentType: 'application/json',
            beforeSend: function (){
                //数据校验
                var code = $('#save-code').val();
                var name = $('#save-name').val();
                if(!code){
                    alert('字典类型编码不能为空');
                    return false;
                }
                if(!name){
                    alert('字典类型名称不能为空');
                    return false;
                }
            },
            data: JSON.stringify({
                code: $('#save-code').val(),
                name: $('#save-name').val(),
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
        $.ajax('dicType/get/' + id, {
            type: 'get',
            success: function (res){
                if(res.code == 0){
                    //数据回显
                    $('#edit-code').val(res.data.code);
                    $('#edit-name').val(res.data.name);
                    $('#edit-description').val(res.data.description);

                    $('#editModal').modal('show');
                }
            }
        });
    }
    function edit(){
        $.ajax('dicType/edit', {
            type: 'put',
            contentType: 'application/json',
            beforeSend: function (){
                //数据校验
                var name = $('#edit-name').val();
                if(!name){
                    alert('字典类型名称不能为空');
                    return false;
                }
            },
            data: JSON.stringify({
                code: $('#edit-code').val(),
                name: $('#edit-name').val(),
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
        $.ajax('dicType/remove/'+id, {
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
            ids.push(this.code);
        });
        $.ajax('dicType/remove', {
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