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
                var productname=$("input[name='productname']").val();
                var envtest=$("input[name='envtest']").val();
                var envpro=$("input[name='envpro']").val();
//                var protocol = document.getElementById("protocol").value;
                var status = document.getElementById("status").value;

                if(productname != "" && envtest!="" && protocol!=""  && status!="" ){
                    //不能重复点击
                    $("#addButton").attr("class","layui-btn layui-btn-disabled");
                    $("#addButton").attr("lay-filter","addButton");
                    //提交表单
                    $.ajax({
                        url:'${base}/frmsEnv/addFrmsEnv',
                        async:'true',
                        type:'post',
                        contentType:'application/json',
                        data:JSON.stringify({productname:productname,envtest:envtest,envpro:envpro,protocol:protocol,status:status}),
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
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>产品名称添加</legend>
    </fieldset>
    <div class="layui-form-item">
        <label class="layui-form-label">产品名称</label>
        <div class="layui-input-inline">
            <input name="productname" type="text" placeholder="请输入产品名称" autocomplete="off" id="productname"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">测试环境地址</label>
        <div class="layui-input-inline">
            <input name="envtest" type="text" placeholder="请输入测试环境地址" autocomplete="off" id="envtest"
                   class="layui-input" lay-verify="required">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">生产环境地址</label>
        <div class="layui-input-inline">
            <input name="envpro" type="text" placeholder="请输入生产环境地址" autocomplete="off" id="envpro"
                   class="layui-input">
        </div>
    </div>
    <#--<div class="layui-form-item">-->
        <#--<label class="layui-form-label">协议类型</label>-->
        <#--<div class="layui-input-inline">-->
            <#--<select name="protocol" id="protocol" class="layui-select" lay-verify="required" >-->
                <#--<option value="">--请选择--</option>-->
                <#--<option value="http">HTTP</option>-->
                <#--<option value="https">HTTPS</option>-->
            <#--</select>-->
        <#--</div>-->
    <#--</div>-->
    <div class="layui-form-item">
        <label class="layui-form-label">是否有效</label>
        <div class="layui-input-inline">
            <select name="status" id="status" class="layui-select" lay-verify="required" >
                <option value="">--请选择--</option>
                <option value="0">失效</option>
                <option value="1">有效</option>
            </select>
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