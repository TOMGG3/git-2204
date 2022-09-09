<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="UTF-8">
    <link href="static/bootstrap/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
</head>
<body>

<!--
此页面为市场活动详情页：
1.实现市场活动单条记录查询
2.实现市场活动备注的列表查询，以及新增，编辑，删除
-->

<!-- 修改市场活动备注的模态窗口 -->
<div class="modal fade" id="editRemarkModal" role="dialog">
    <%-- 备注的id --%>
    <input type="hidden" id="remarkId">
    <div class="modal-dialog" role="document" style="width: 40%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">修改备注</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form">
                    <input type="hidden" id="edit-id">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">内容</label>
                        <div class="col-sm-10" style="width: 81%;">
                            <textarea class="form-control" rows="3" id="edit-noteContent"></textarea>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="editRemark()">更新</button>
            </div>
        </div>
    </div>
</div>

<!-- 返回按钮 -->
<div style="position: relative; top: 35px; left: 10px;">
    <a href="javascript:void(0);" onclick="window.history.back();"><span class="glyphicon glyphicon-arrow-left" style="font-size: 20px; color: #DDDDDD"></span></a>
</div>

<!-- 大标题 -->
<div style="position: relative; left: 40px; top: -30px;">
    <div class="page-header">
        <h3>市场活动-${activity.name} <small>${activity.startDate} ~ ${activity.endDate}</small></h3>
    </div>
</div>

<br/>
<br/>
<br/>

<!-- 详细信息 -->
<div style="position: relative; top: -70px;">
    <div style="position: relative; left: 40px; height: 30px;">
        <div style="width: 300px; color: gray;">所有者</div>
        <div style="width: 300px;position: relative; left: 200px; top: -20px;"><b>${activity.userName}</b></div>
        <div style="width: 300px;position: relative; left: 450px; top: -40px; color: gray;">名称</div>
        <div style="width: 300px;position: relative; left: 650px; top: -60px;"><b>${activity.name}</b></div>
        <div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
        <div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
    </div>

    <div style="position: relative; left: 40px; height: 30px; top: 10px;">
        <div style="width: 300px; color: gray;">开始日期</div>
        <div style="width: 300px;position: relative; left: 200px; top: -20px;"><b>${activity.startDate}</b></div>
        <div style="width: 300px;position: relative; left: 450px; top: -40px; color: gray;">结束日期</div>
        <div style="width: 300px;position: relative; left: 650px; top: -60px;"><b>${activity.endDate}</b></div>
        <div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px;"></div>
        <div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -60px; left: 450px;"></div>
    </div>
    <div style="position: relative; left: 40px; height: 30px; top: 20px;">
        <div style="width: 300px; color: gray;">成本</div>
        <div style="width: 300px;position: relative; left: 200px; top: -20px;"><b>${activity.cost}</b></div>
        <div style="height: 1px; width: 400px; background: #D5D5D5; position: relative; top: -20px;"></div>
    </div>
    <div style="position: relative; left: 40px; height: 30px; top: 30px;">
        <div style="width: 300px; color: gray;">创建者</div>
        <div style="width: 500px;position: relative; left: 200px; top: -20px;"><b>${activity.createBy}&nbsp;&nbsp;</b><small style="font-size: 10px; color: gray;">${activity.createTime}</small></div>
        <div style="height: 1px; width: 550px; background: #D5D5D5; position: relative; top: -20px;"></div>
    </div>
    <div style="position: relative; left: 40px; height: 30px; top: 40px;">
        <div style="width: 300px; color: gray;">修改者</div>
        <div style="width: 500px;position: relative; left: 200px; top: -20px;"><b>${activity.editBy}&nbsp;&nbsp;</b><small style="font-size: 10px; color: gray;">${activity.editTime}</small></div>
        <div style="height: 1px; width: 550px; background: #D5D5D5; position: relative; top: -20px;"></div>
    </div>
    <div style="position: relative; left: 40px; height: 30px; top: 50px;">
        <div style="width: 300px; color: gray;">描述</div>
        <div style="width: 630px;position: relative; left: 200px; top: -20px;">
            <b>${activity.description}</b>
        </div>
        <div style="height: 1px; width: 850px; background: #D5D5D5; position: relative; top: -20px;"></div>
    </div>
</div>

<!-- 备注 -->
<div style="position: relative; top: 30px; left: 40px;">
    <div class="page-header">
        <h4>备注</h4>
    </div>

    <div id="remarkList">
    </div>

    <div id="remarkDiv" style="background-color: #E6E6E6; width: 870px; height: 90px;">
        <form role="form" style="position: relative;top: 10px; left: 10px;">
            <textarea id="remark" class="form-control" style="width: 850px; resize : none;" rows="2"  placeholder="添加备注..."></textarea>
            <p id="cancelAndSaveBtn" style="position: relative;left: 737px; top: 10px; display: none;">
                <button id="cancelBtn" type="button" class="btn btn-default">取消</button>
                <button type="button" class="btn btn-primary" onclick="saveRemark()">保存</button>
            </p>
        </form>
    </div>
</div>
<div style="height: 200px;"></div>
<script type="text/javascript" src="static/js/jquery.js"></script>
<script type="text/javascript" src="static/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript">
    //默认情况下取消和保存按钮是隐藏的
    var cancelAndSaveBtnDefault = true;
    $(function(){
        $("#remark").focus(function(){
            if(cancelAndSaveBtnDefault){
                //设置remarkDiv的高度为130px
                $("#remarkDiv").css("height","130px");
                //显示
                $("#cancelAndSaveBtn").show("2000");
                cancelAndSaveBtnDefault = false;
            }
        });

        $("#cancelBtn").click(function(){
            //显示
            $("#cancelAndSaveBtn").hide();
            //设置remarkDiv的高度为130px
            $("#remarkDiv").css("height","90px");
            cancelAndSaveBtnDefault = true;
        });

        listRemark();
    });

    //在jsp页面中域对象中的数据可以在js中使用, 如果数据为数字类型，那么可以直接使用，如果为字符串类型，需要用引号包裹
    var activityId = '${activity.id}';
    var userName = '${activity.userName}';
    function listRemark(){
        $.ajax('activityRemark/list', {
            type: 'get',
            data: {
                activityId: activityId
            },
            success: function (res){
                var str = '';
                if(res.code == 0){
                    $.each(res.data, function (){
                        str += '<div class="remarkDiv" style="height: 60px;">';
                        str += '<img title="' + userName + '" src="static/image/user-thumbnail.png" style="width: 30px; height:30px;">';
                        str += '<div style="position: relative; top: -40px; left: 40px;" >';
                        str += '<h5>' + this.noteContent + '</h5>';
                        str += '<small style="color: gray;"> ' + this.createTime + ' 由' + this.createBy + '</small>';
                        str += '<div style="position: relative; left: 500px; top: -30px; height: 30px; width: 100px; display: none;">';
                        str += '<a class="myHref" href="javascript:getRemark(\''+ this.id +'\');"><span class="glyphicon glyphicon-edit" style="font-size: 20px; color: #E6E6E6;"></span></a>';
                        str += '&nbsp;&nbsp;&nbsp;&nbsp;';
                        str += '<a class="myHref" href="javascript:removeRemark(\''+ this.id +'\');"><span class="glyphicon glyphicon-remove" style="font-size: 20px; color: #E6E6E6;"></span></a>';
                        str += '</div></div></div>';
                    });
                }
                //将数据渲染到页面
                $('#remarkList').html(str);
                //为页面上的图标设置特效
                $(".remarkDiv").mouseover(function(){
                    $(this).children("div").children("div").show();
                });
                $(".remarkDiv").mouseout(function(){
                    $(this).children("div").children("div").hide();
                });
                $(".myHref").mouseover(function(){
                    $(this).children("span").css("color","red");
                });
                $(".myHref").mouseout(function(){
                    $(this).children("span").css("color","#E6E6E6");
                });
            }
        });
    }
    function saveRemark(){
        $.ajax('activityRemark/save',{
            type: 'post',
            contentType: 'application/json',
            data: JSON.stringify({
                noteContent: $('#remark').val(),
                activityId: activityId
            }),
            success: function (res){
                if(res.code == 0){
                    alert('备注新增成功');
                    //清空
                    $('#remark').val('');
                    //添加时特效
                    $("#cancelAndSaveBtn").hide();
                    $("#remarkDiv").css("height","90px");
                    cancelAndSaveBtnDefault = true;
                    //重新加载备注列表
                    listRemark();
                }
            }
        });
    }
    function getRemark(id){
        $.ajax('activityRemark/get/' + id,{
            type: 'get',
            success: function (res){
                if(res.code == 0){
                    $('#edit-id').val(res.data.id);
                    $('#edit-noteContent').val(res.data.noteContent);

                    $('#editRemarkModal').modal('show');
                }
            }
        });
    }
    function editRemark(){
        $.ajax('activityRemark/edit',{
            type: 'put',
            contentType: 'application/json',
            data: JSON.stringify({
                id: $('#edit-id').val(),
                noteContent: $('#edit-noteContent').val()
            }),
            success: function (res){
                if(res.code == 0){
                    alert('备注编辑成功');
                    //隐藏模态框
                    $('#editRemarkModal').modal('hide');
                    //重新加载备注列表
                    listRemark();
                }
            }
        });
    }
    function removeRemark(id){
        if(confirm('确定删除吗?')){
            $.ajax('activityRemark/remove/'+id,{
                type: 'delete',
                success: function (res){
                    if(res.code == 0){
                        alert('备注删除成功');
                        listRemark();
                    }
                }
            });
        }
    }
</script>
</body>
</html>