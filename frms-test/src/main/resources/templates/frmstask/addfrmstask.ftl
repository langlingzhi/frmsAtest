<#import "/spring.ftl" as spring />
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="/plugins/layui/css/layui.css"  media="all">
    <script src="/plugins/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/jquery-1.4.3.js"></script>
    <script type="text/javascript">
        layui.use(['form','layer'], function(){
            var form=layui.form
                    ,layer = layui.layer;

            form.on('submit(*)', function(data){
                var taskname=$("input[name='taskname']").val();
                var productid = document.getElementById("productid").value;
                var env = document.getElementById("env").value;
                var creater=$("input[name='creater']").val();
                if(taskname != "" && productid!="" && env!=""  && user!="" ){
                    //不能重复点击
                    $("#addButton").attr("class","layui-btn layui-btn-disabled");
                    $("#addButton").attr("lay-filter","addButton");
                    //提交表单
                    $.ajax({
                        url:'${base}/frmsTask/addFrmsTask',
                        async:'true',
                        type:'post',
                        contentType:'application/json',
                        data:JSON.stringify({taskname:taskname,productid:productid,env:env,creater:creater}),
                        dataType:'text',
                        success:function(data){
                            var msg ;
                            if(data=='success'){
                                msg = '添加成功!';
                            } else if(data == 'exists'){
                                msg = '添加失败!';
                            } else {
                                msg = '后台异常,请联系管理员!';
                            }
                            layer.msg(msg,function(){
                                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                                parent.layer.close(index);
                            });
                        },
                        error:function(){

                        }
                    })
                } else {
                    layer.msg('产品名称不能为空');
                }
            });
        })
    </script>
</head>
<body>
<form class="layui-form">
    <div class="layui-form-item" style="margin-top: 20px">
        <label class="layui-form-label">任务名称:</label>
        <div class="layui-input-inline">
            <input name="taskname" type="text" placeholder="" autocomplete="off" id="taskname"
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
                        <option value="${item.id?if_exists}">${item.productname?if_exists}</option>
                    </#list> 
                </#if>
                     
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">环境类型:</label>
        <div class="layui-input-inline">
            <select name="env" id="env" class="layui-select" lay-verify="required" >
                <option value="0" selected>测试</option>
                <option value="1">生产</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">操作人:</label>
        <div class="layui-input-inline">
            <input name="creater" type="text" placeholder="" autocomplete="off" id="creater" value="${frmsUser.name?if_exists}"
                   class="layui-input">
        </div>
    </div>
</form>
<div class="layui-form-item">
    <div class="layui-input-block">
        <button class="layui-btn" lay-submit lay-filter="*" id="addButton">提交</button>
    <#--<button type="reset" class="layui-btn layui-btn-primary">重置</button>-->
    </div>
</div>
</body>
</html>