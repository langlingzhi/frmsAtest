<#import "/spring.ftl" as spring />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="/plugins/layui/css/layui.css"  media="all">
    <script src="/plugins/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/jquery-1.4.3.js"></script>
    <script type="text/javascript">
        layui.use(['form','layer','layedit'], function(){
            var form=layui.form
                    ,layer = layui.layer
                    ,layedit = layui.layedit;
            var editIndex = layedit.build('LAY_demo_editor');
            form.on('submit(*)', function(data){
                var id = $("input[name='id']").val();
                var apiName = $("input[name='apiName']").val();
                var productId = document.getElementById("productId").value;
                var status = document.getElementById("status").value;
                var reqType = document.getElementById("reqType").value;
                var method = document.getElementById("method").value;
                var path = $("input[name='path']").val();
                var apiVersion = $("input[name='apiVersion']").val();
                var writer = $("input[name='writer']").val();
                var discribe = $("input[name='discribe']").val();
                var body = $("textarea[name='body']").val();
                var susResponse = $("textarea[name='susResponse']").val();
                if(id != ""){
                    //不能重复点击
                    $("#runbutton").attr("class","layui-btn layui-btn-disabled");
                    $("#runbutton").attr("lay-filter","runbutton");
                    //提交表单
                    $.ajax({
                        url:'${base}/frmsApi/frmsApiTest',
                        async:'true',
                        type:'post',
                        contentType:'application/json',
                        data:JSON.stringify({id:id,apiName:apiName,productId:productId,status:status,reqType:reqType,method:method,path:path,apiVersion:apiVersion,writer:writer,discribe:discribe,body:body,susResponse:susResponse}),
                        dataType:'text',
                        success:function(data){
                            var msg ;
                            if(data=='success'){
                                window.location.href="${base}/frmsApi/toFrmsApiTest?id="+ id;
                                <#--$.ajax({-->
                                    <#--content:'${base}/frmsApi/toFrmsApiTest?id='+ id,-->
                                <#--})-->
                            } else if(data == 'exists'){
                                msg = '失败!';
                            } else {
                                msg = '后台异常,请联系管理员!';
                            }
//                            layer.msg(msg,function(){
//                                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
//                                parent.layer.close(index);
//                            });
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
        layui.code({
            height: '60px' //请注意必须加px。如果该key不设定，则会自适应高度，且不会出现滚动条。
            , skin: 'notepad'
        });
    </script>
    <title>Insert title here</title>
</head>
<body>
<form class="layui-form">
    <div class="layui-form-item">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend><span style="color:green;">|</span>
                基本信息:
            </legend>
        </fieldset>
        <div class="layui-inline">
            <label class="layui-form-label">接口编号：<span style="color:red;">*</span></label>
            <div class="layui-input-inline">
                <input name="id" type="text" value="${frmsapi.id?if_exists}" autocomplete="off" id="id" disabled
                       class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">接口名称：<span style="color:red;">*</span></label>
            <div class="layui-input-inline">
                <input name="apiName" type="text" value="${frmsapi.apiName?if_exists}" autocomplete="off" id="apiName"
                       class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">所属产品：<span style="color:red;">*</span></label>
            <div class="layui-input-inline">
                <select name="productId" id="productId" class="layui-select" lay-verify="required" >
                    <option value="">--请选择--</option>
                <#if productIds?exists> 
                    <#list productIds as item>  
                        <option value="${item.id}"
                                <#if frmsapi.productId == item.id>selected</#if>
                        >${item.productname?if_exists}</option>
                    </#list> 
                </#if> 
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">状态：<span style="color:red;">*</span></label>
            <div class="layui-input-inline">
                <select name="status" id="status" class="layui-select" lay-verify="required">
                    <option value='0'<#if frmsapi.status ==0>selected</#if>>不执行</option>
                    <option value='1'<#if frmsapi.status ==1>selected</#if>>执行</option>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item ">
        <label class="layui-form-label">接口路径：<span style="color:red;">*</span></label>
        <div class="layui-input-inline" style="width: 80px">
            <select name="reqType" id="reqType" class="layui-select" lay-verify="required">
                <option value="http" <#if frmsapi.reqType == 'http'>selected</#if>>HTTP</option>
                <option value="https" <#if frmsapi.reqType == 'https'>selected</#if>>HTTPS</option>
            </select>
        </div>
        <div class="layui-input-inline" style="width: 80px">
            <select name="method" id="method" class="layui-select" lay-verify="required">
                <option value="">--请选择--</option>
                <option value="get" <#if frmsapi.method == 'get'>selected</#if>>GRT</option>
                <option value="post" <#if frmsapi.method == 'post'>selected</#if>>POST</option>
            </select>
        </div>
        <div class="layui-input-inline" style="width: 300px">
            <input name="path" type="text" autocomplete="off" id="path" value="${frmsapi.path?if_exists}"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">接口版本：<span style="color:red;">*</span></label>
            <div class="layui-input-inline">
                <input name="apiVersion" type="text" autocomplete="off" id="apiVersion" value="${frmsapi.apiVersion?if_exists}"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">添加人员：<span style="color:red;">*</span></label>
            <div class="layui-input-inline">
                <input name="writer" type="text" autocomplete="off" id="writer"
                       class="layui-input" value="${frmsapi.writer?if_exists}">
            </div>
        </div>
    </div>
    <div class="layui-form-item">

        <label class="layui-form-label">业务描述:<span style="color:red;">*</span></label>
        <div class="layui-input-inline" style="width: 400px">
            <input name="discribe" type="text" autocomplete="off" id="discribe" value="${frmsapi.discribe?if_exists}"
                   class="layui-input">
        </div>
    </div>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend><span style="color:green;">|</span>
            Request设置:
        </legend>
    </fieldset>
    <label class="layui-form-label">body:<span style="color:red;">*</span></label>
    <div class="layui-input-block">
                        <textarea name="body" placeholder="" class="layui-textarea"
                                  style="width: 500px">${frmsapi.body?if_exists}</textarea>
    </div>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend><span style="color:green;">|</span>
            Response设置:
        </legend>
    </fieldset>
    <label class="layui-form-label">body:<span style="color:red;">*</span></label>
    <div class="layui-input-block">
                        <textarea name="susResponse" placeholder="" class="layui-textarea"
                                  style="width: 500px">${frmsapi.susResponse?if_exists}</textarea>
    </div>
</form>
<div class="layui-form-item">
    <div class="layui-input-block">
        <button class="layui-btn" lay-submit lay-filter="*" id="runbutton">调试</button>
    <#--<button  class="layui-btn layui-btn-primary" lay-filter="*" id="testButton">调试</button>-->
    </div>
</div>
</body>
</html>