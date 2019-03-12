<#import "/spring.ftl" as spring />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all">
    <script src="/plugins/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/jquery-1.4.3.js"></script>
    <script type="text/javascript">
        layui.use(['form', 'layer'], function () {
            var form = layui.form
                    , layer = layui.layer
                    , $ = layui.jquery;
            form.on('submit(*)', function (data) {
                var taskname = $("input[name='taskname']").val();
                var productid = document.getElementById("productid").value;
                var env = document.getElementById("env").value;
                var creater = $("input[name='creater']").val();
                if (taskname != "" && productid != "" && env != "" && user != "") {
                    //不能重复点击
                    $("#editButton").attr("class", "layui-btn layui-btn-disabled");
                    $("#editButton").attr("lay-filter", "editButton");
                    //提交表单
                    $.ajax({
                        url: '${base}/frmsTask/frmsTaskModify',
                        async: 'true',
                        type: 'post',
                        contentType: 'application/json',
                        data: JSON.stringify({taskname: taskname, productid: productid, env: env, creater: creater}),
                        dataType: 'text',
                        success: function (data) {
                            var msg;
                            if (data == 'success') {
                                msg = '修改成功!';
                            } else if (data == 'exists') {
                                msg = '修改失败!';
                            } else {
                                msg = '后台异常,请联系管理员!';
                            }
                            layer.msg(msg, function () {
                                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                                parent.layer.close(index);
                            });
                        },
                        error: function () {
                        }
                    })
                } else {
                    layer.msg("任务名称或项目不能为空!", function () {
                        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                        parent.layer.close(index);
                    });
                }
            });
            form.verify({
                //数据验证
            });
        })
    </script>
    <title>Insert title here</title>
</head>
<body>
<form class="layui-form">
    <div class="layui-form-item" style="margin-top: 20px">
        <label class="layui-form-label">任务名称:</label>
        <div class="layui-input-inline">
            <input name="taskname" type="text" placeholder="" autocomplete="off" id="taskname"
                   value="${frmstask.taskname?if_exists}"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">所属产品：<span style="color:red;">*</span></label>
            <div class="layui-input-inline">
                <select name="productid" id="productid" class="layui-select" lay-verify="required">
                    <option value=>--请选择--</option>
                <#if productIds?exists> 
                    <#list productIds as item>  
                        <option value="${item.id?if_exists}"<#if frmstask.productid == item.id>
                            selected></#if>${item.productname?if_exists}</option>
                    </#list> 
                </#if>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">环境类型:</label>
        <div class="layui-input-inline">
            <select name="env" id="env" class="layui-select" lay-verify="required">
                <option value="0"<#if frmstask.env == "0">selected</#if>>测试</option>
                <option value="1"<#if frmstask.env == "1">selected</#if>>生产</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">操作人:</label>
        <div class="layui-input-inline">
            <input name="user" type="text" placeholder="" autocomplete="off" id="user"
                   value="${frmsUser.name?if_exists}"
                   class="layui-input">
        </div>
    </div>
</form>
<div class="layui-form-item">
    <div class="layui-input-block" style="margin-top: 10px">
        <button class="layui-btn" lay-submit lay-filter="*" id="editButton">提交</button>
    <#--<button type="reset" class="layui-btn layui-btn-primary">重置</button>-->
    </div>
</div>
</body>
</html>