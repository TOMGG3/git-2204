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
    <div class="panel-body">
        <h1 style="text-align: center">员工列表</h1>
        <form id="searchForm" class="form-inline">
            <div class="form-group">
                <label>员工姓名</label>
                <input type="text" placeholder="员工姓名" id="ename" class="form-control">
            </div>
            <div class="form-group">
                <label>部门</label>
                <select id="deptno" class="form-control deptSelect">
                </select>
            </div>
            <div class="form-group">
                <button type="button" class="btn btn-default" onclick="$('#tab').bootstrapTable('refresh')">搜索</button>
                <button type="button" class="btn btn-default" onclick="$('#searchForm')[0].reset();$('#tab').bootstrapTable('refresh')">清空</button>
            </div>
            <div class="form-group">
                <button type="button" class="btn btn-primary" onclick="$('#saveModal').modal('show');">新增</button>
                <button type="button" class="btn btn-danger" onclick="removeBatch()">批量删除</button>
            </div>
        </form>
        <table id="tab" class="table table-striped table-hover table-bordered"></table>
    </div>
    <!--新增-->
    <div id="saveModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">新增员工</h4>
                </div>
                <div class="modal-body">
                    <form id="saveForm" class="form-horizontal">
                        <div class="form-group">
                            <label class="control-label col-md-3">员工姓名</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control" id="save-ename">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">员工岗位</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control" id="save-job">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">管理者</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control" id="save-mgr">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">入职日期</label>
                            <div class="col-md-9">
                                <input type="date" class="form-control" id="save-hiredate">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">员工薪资</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control" id="save-sal">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">员工奖金</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control" id="save-comm">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">部门</label>
                            <div class="col-md-9">
                                <select id="save-deptno" class="form-control deptSelect">
                                </select>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" onclick="save()">确定</button>
                </div>
            </div>
        </div>
    </div>
    <!--编辑-->
    <div id="editModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">编辑员工</h4>
                </div>
                <div class="modal-body">
                    <form id="editForm" class="form-horizontal">
                        <input type="hidden" id="edit-empno">
                        <div class="form-group">
                            <label class="control-label col-md-3">员工姓名</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control" id="edit-ename">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">员工岗位</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control" id="edit-job">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">管理者</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control" id="edit-mgr">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">入职日期</label>
                            <div class="col-md-9">
                                <input type="date" class="form-control" id="edit-hiredate">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">员工薪资</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control" id="edit-sal">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">员工奖金</label>
                            <div class="col-md-9">
                                <input type="text" class="form-control" id="edit-comm">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">部门</label>
                            <div class="col-md-9">
                                <select id="edit-deptno" class="form-control deptSelect">
                                </select>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" onclick="edit()">确定</button>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="static/js/jquery-3.6.0.js"></script>
    <script type="text/javascript" src="static/bootstrap/js/bootstrap.min.js"></script>
    <!--bootstrapTable核心js-->
    <script type="text/javascript" src="static/bootstrap-table/bootstrap-table.min.js"></script>
    <!--bootstrapTable中文语言包-->
    <script type="text/javascript" src="static/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>
    <script type="text/javascript">
        $(function (){
            initTable();
            initSelect();

            //模态框时间
            $('#saveModal').on('hidden.bs.modal', function (){
                console.log('窗体完全隐藏...')
                $('#saveForm')[0].reset();
            });
            $('#editModal').on('hidden.bs.modal', function (){
                $('#editForm')[0].reset();
            });
        });
        function initTable(){
            $('#tab').bootstrapTable({
                //表格的配置
                // height: 450,
                //本地数据
                // data: [
                //     {id: 1001, name: '张三', age: 20},
                //     {id: 1002, name: '李四', age: 21},
                //     {id: 1003, name: '王五', age: 22},
                //     {id: 1004, name: '汤姆', age: 23},
                //     {id: 1005, name: '杰瑞', age: 24}
                // ],
                //http请求方式
                method: 'get',
                //请求路径
                url: 'emp/page',
                //处理响应
                responseHandler: function (res) {
                    // console.log(res);
                    if(res.code == 0){
                        // return res.data;
                        //分页：返回自定义字面量对象
                        return {
                            //当前页列表数据
                            rows: res.data.list,
                            //总记录数
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
                pageSize: 5,
                //设置切换每页条数
                pageList: [5, 10, 20, 50],
                paginationPreText: '上一页',
                paginationNextText: '下一页',
                //设置查询的请求参数
                queryParamsType: '',
                queryParams: function (params){
                    // console.log(params);
                    params.ename = $('#ename').val();
                    params.deptno = $('#deptno').val();
                    return params;
                },
                //设置表格的工具栏
                toolbar: '#searchForm',
                //辅助功能
                clickToSelect: true,
                singleSelect: false,
                showRefresh: true,
                showToggle: true,
                showFullscreen: true,
                //表格中的列描述
                columns: [
                    //title列标题，列名
                    //field列数据，其值是数据对象中的属性名
                    //halign列名对齐方式
                    {checkbox: true},
                    {field: 'empno', visible: false},
                    {title: '员工姓名', field: 'ename', halign: 'center'},
                    {title: '员工岗位', field: 'job', halign: 'center'},
                    {title: '管理者', field: 'mgr', halign: 'center'},
                    {title: '入职日期', field: 'hiredate', halign: 'center'},
                    {title: '员工薪资', field: 'sal', halign: 'center'},
                    {title: '员工奖金', field: 'comm', halign: 'center'},
                    {title: '部门名称', field: 'dept.dname', halign: 'center'},
                    {title: '部门位置', field: 'dept', halign: 'center', formatter: function (value, row){
                        return value.loc;
                    }},
                    //formatter列数据格式化，其值为函数，函数的参数value是当前列的数据，当前行的数据
                    {title: '操作', halign: 'center', align: 'center',formatter: function(value, row){
                        // console.log(value);
                        // console.log(row);
                        return '<button type="button" class="btn btn-warning btn-xs" onclick="get(' + row.empno + ')">编辑</button>&emsp;' +
                            '<button type="button" class="btn btn-danger btn-xs" onclick="remove(' + row.empno + ')">删除</button>';
                    }}
                ]
            });
        }
        function initSelect(){
            $.ajax('dept/listAll', {
                type: 'get',
                success: function (res){
                    if(res.code == 0){
                        var str = '<option value=""></option>';
                        $.each(res.data, function (){
                            str += '<option value="' + this.deptno + '">' + this.dname + '</option>';
                        });
                        $('.deptSelect').html(str);
                    }
                }
            });
        }
        function save(){
            $.ajax('emp/save', {
                type: 'post',
                contentType: 'application/json',
                beforeSend: function (){
                    //发送之前的处理：数据校验
                    var ename = $('#save-ename').val();
                    var job = $('#save-job').val();
                    var deptno = $('#save-deptno').val();
                    //在js中所有类型的变量都可以放入到if判断中
                    //if判断会判断当前数据是否为null,undefined,空字符串,0,NaN，判断结果为false
                    if(!ename){
                        alert('员工姓名不能为空');
                        return false;
                    }
                    if(!job){
                        alert('员工岗位不能为空');
                        return false;
                    }
                    if(!deptno){
                        alert('请选择部门');
                        return false;
                    }
                },
                data: JSON.stringify({
                    ename: $('#save-ename').val(),
                    job: $('#save-job').val(),
                    mgr: $('#save-mgr').val(),
                    hiredate: $('#save-hiredate').val(),
                    sal: $('#save-sal').val(),
                    comm: $('#save-comm').val(),
                    deptno: $('#save-deptno').val(),
                }),
                success: function (res) {
                    if(res.code == 0){
                        alert('新增成功');
                        $('#saveModal').modal('hide');
                        $('#tab').bootstrapTable('refresh');
                    }
                }
            });
        }
        function get(id){
            $.ajax('emp/get/' + id, {
                type: 'get',
                success: function (res){
                    if(res.code == 0){
                        //数据回显
                        $('#edit-empno').val(res.data.empno);
                        $('#edit-ename').val(res.data.ename);
                        $('#edit-job').val(res.data.job);
                        $('#edit-hiredate').val(res.data.hiredate);
                        $('#edit-mgr').val(res.data.mgr);
                        $('#edit-sal').val(res.data.sal);
                        $('#edit-comm').val(res.data.comm);
                        $('#edit-deptno').val(res.data.deptno);

                        $('#editModal').modal('show');
                    }
                }
            });
        }
        function edit(){
            $.ajax('emp/edit', {
                type: 'put',
                contentType: 'application/json',
                beforeSend: function (){
                    var ename = $('#edit-ename').val();
                    var job = $('#edit-job').val();
                    var deptno = $('#edit-deptno').val();
                    if(!ename){
                        alert('员工姓名不能为空');
                        return false;
                    }
                    if(!job){
                        alert('员工岗位不能为空');
                        return false;
                    }
                    if(!deptno){
                        alert('请选择部门');
                        return false;
                    }
                },
                data: JSON.stringify({
                    empno: $('#edit-empno').val(),
                    ename: $('#edit-ename').val(),
                    job: $('#edit-job').val(),
                    mgr: $('#edit-mgr').val(),
                    hiredate: $('#edit-hiredate').val(),
                    sal: $('#edit-sal').val(),
                    comm: $('#edit-comm').val(),
                    deptno: $('#edit-deptno').val(),
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
            $.ajax('emp/remove/'+id, {
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
                ids.push(this.empno);
            });
            $.ajax('emp/remove', {
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