<#import "/spring.ftl" as spring />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="/plugins/layui/css/layui.css"  media="all">
    <script src="/plugins/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/jquery-1.4.3.js"></script>
    <script type="text/javascript">
        layui.use(['form','layer'], function(){
            var form=layui.form
                    ,layer = layui.layer
                    ,$ = layui.jquery;
            form.on('submit(*)', function(data){
                var id=$("input[name='id']").val();
                var productname=$("input[name='productname']").val();
                var envtest=$("input[name='envtest']").val();
                var envpro=$("input[name='envpro']").val();
//                var protocol = document.getElementById("protocol").value;
                var status = document.getElementById("status").value;
                if(id != "" && productname!= ""  && status!="" ){
                    //不能重复点击
                    $("#editButton").attr("class","layui-btn layui-btn-disabled");
                    $("#editButton").attr("lay-filter","editButton");
                    //提交表单
                    $.ajax({
                        url:'${base}/frmsEnv/frmsEnvModify',
                        async:'true',
                        type:'post',
                        contentType:'application/json',
                        data:JSON.stringify({id:id,productname:productname,envtest:envtest,envpro:envpro,status:status}),
                        dataType:'text',
                        success:function(data){
                            var msg ;
                            if(data=='success'){
                                msg = '修改成功!';
                            } else if(data == 'exists'){
                                msg = '修改失败!';
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
                    layer.msg("机构名称或状态不能为空!",function(){
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
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>产品信息修改</legend>
</fieldset>
<form class="layui-form">
    <div class="layui-form-item">
        <label class="layui-form-label">产品名称</label>
        <div class="layui-input-inline">
            <input type="hidden" name="id" value="${frmsenv.id}"/>
            <input name="productname" class="layui-input" type="text" value="${frmsenv.productname?if_exists}" autocomplete="off">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">测试环境地址</label>
        <div class="layui-input-inline">
            <input name="envtest" class="layui-input" type="text" value="${frmsenv.envtest?if_exists}" autocomplete="off">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label" style="">生产环境地址</label>
        <div class="layui-input-inline">
            <input name="envpro" class="layui-input" type="text" value="${frmsenv.envpro?if_exists}" autocomplete="off">
        </div>
    </div>
    <#--<div class="layui-form-item">-->
        <#--<label class="layui-form-label">协议类型</label>-->
        <#--<div class="layui-input-inline">-->
            <#--<select name="protocol" id="protocol" class="layui-select" lay-verify="required" >-->
                <#--<option value="">--请选择--</option>-->
                <#--<option value="http" <#if frmsenv.protocol == 'http'>selected</#if>>HTTP</option>-->
                <#--<option value="https" <#if frmsenv.protocol == 'https'>selected</#if>>HTTPS</option>-->
            <#--</select>-->
        <#--</div>-->
    <#--</div>-->
    <div class="layui-form-item">
        <label class="layui-form-label">是否有效</label>
        <div class="layui-input-inline">
            <select name="status" id="status" class="layui-select" lay-verify="required" >
                <option value="">--请选择--</option>
                <option value="0" <#if frmsenv.status == '0'>selected</#if>>无效</option>
                <option value="1" <#if frmsenv.status == '1'>selected</#if>>有效</option>
            </select>
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