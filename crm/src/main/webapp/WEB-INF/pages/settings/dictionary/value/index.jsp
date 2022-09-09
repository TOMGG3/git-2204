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
    <li class="active">字典值</li>
</ol>
<div class="panel-body">
    <!--查询条件-->
    <div class="well well-sm">
        <form id="searchForm" class="form-inline">
            <div class="row">
                <div class="form-group col-md-4">
                    <label>字典类型</label>
                    <select id="typeCode" class="form-control typeSelect"></select>
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
                <h4 class="modal-title" id="myModalLabel">新增数据字典值</h4>
            </div>
            <div class="modal-body">
                <form id="saveForm" class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">字典类型<span style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10">
                            <select id="save-typeCode" class="form-control typeSelect"></select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">字典值<span style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="save-value">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">文本<span style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="save-text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">排序号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="save-orderNo">
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
                <h4 class="modal-title">编辑字典值</h4>
            </div>
            <div class="modal-body">
                <form id="editForm" class="form-horizontal" role="form">
                    <input type="hidden" id="edit-id">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">字典类型<span style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10">
                            <select id="edit-typeCode" class="form-control typeSelect" disabled></select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">字典值<span style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit-value" readonly>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">文本<span style="font-size: 15px; color: red;">*</span></label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit-text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">排序号</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="edit-orderNo">
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
        initSelect();

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
            url: 'dicValue/page',
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
                params.typeCode = $('#typeCode').val();
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
                {title: '序号', formatter(value,row,index) {
                        return index+1;
                }},
                {title: '字典值', field: 'value', halign: 'center'},
                {title: '文本', field: 'text', halign: 'center'},
                {title: '排序号', field: 'orderNo', halign: 'center'},
                {title: '类型编码', field: 'typeCode', halign: 'center'},
                {title: '类型名称', field: 'typeName', halign: 'center'},
                {title: '操作', halign: 'center', align: 'center',formatter: function(value, row){
                        return '<button type="button" class="btn btn-warning btn-xs" onclick="get(\'' + row.id + '\')">编辑</button>&emsp;' +
                            '<button type="button" class="btn btn-danger btn-xs" onclick="remove(\'' + row.id + '\')">删除</button>';
                    }}
            ]
        });
    }
    function initSelect(){
        $.ajax('dicType/list', {
            type: 'get',
            success: function (res){
                if(res.code == 0){
                    var str = '<option value=""></option>';
                    $.each(res.data, function (){
                        str += '<option value="' + this.code + '">' + this.name + '</option>';
                    });
                    $('.typeSelect').html(str);
                }
            }
        });
    }
    function save(){
        $.ajax('dicValue/save', {
            type: 'post',
            contentType: 'application/json',
            beforeSend: function (){
                //数据校验
                var value = $('#save-value').val();
                var text = $('#save-text').val();
                var typeCode = $('#save-typeCode').val();
                if(!value){
                    alert('字典值不能为空');
                    return false;
                }
                if(!text){
                    alert('字典名称不能为空');
                    return false;
                }
                if(!typeCode){
                    alert('请选择字典类型');
                    return false;
                }
            },
            data: JSON.stringify({
                value: $('#save-value').val(),
                text: $('#save-text').val(),
                orderNo: $('#save-orderNo').val(),
                typeCode: $('#save-typeCode').val()
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
        $.ajax('dicValue/get/' + id, {
            type: 'get',
            success: function (res){
                if(res.code == 0){
                    //数据回显
                    $('#edit-id').val(res.data.id);
                    $('#edit-value').val(res.data.value);
                    $('#edit-text').val(res.data.text);
                    $('#edit-typeCode').val(res.data.typeCode);
                    $('#edit-orderNo').val(res.data.orderNo);

                    $('#editModal').modal('show');
                }
            }
        });
    }
    function edit(){
        $.ajax('dicValue/edit', {
            type: 'put',
            contentType: 'application/json',
            beforeSend: function (){
                //数据校验
                var text = $('#edit-text').val();
                if(!text){
                    alert('字典名称不能为空');
                    return false;
                }
            },
            data: JSON.stringify({
                id: $('#edit-id').val(),
                value: $('#edit-value').val(),
                text: $('#edit-text').val(),
                orderNo: $('#edit-orderNo').val(),
                typeCode: $('#edit-typeCode').val()
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
        $.ajax('dicValue/remove/'+id, {
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
        $.ajax('dicValue/remove', {
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