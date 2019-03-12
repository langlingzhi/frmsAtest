<#import "/spring.ftl" as spring />
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all">
    <script src="/plugins/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/jquery-1.4.3.js"></script>
    <script type="text/javascript">
        layui.use(['form', 'layer'], function () {
            var form = layui.form,
                    layer = layui.layer;


            form.on('submit(*)', function (data) {
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


                if (apiName != "" && productId != "" && method != "" && path != "" && reqType!="") {
                    //不能重复点击
                    $("#addButton").attr("class", "layui-btn layui-btn-disabled");
                    $("#addButton").attr("lay-filter", "addButton");
                    //提交表单
                    $.ajax({
                        url: '${base}/frmsApi/addFrmsApi',
                        async: 'true',
                        type: 'post',
                        contentType: 'application/json',
                        data: JSON.stringify({
                            apiName: apiName,
                            productId: productId,
                            status: status,
                            reqType: reqType,
                            method: method,
                            path: path,
                            apiVersion: apiVersion,
                            writer: writer,
                            discribe: discribe,
                            body: body,
                            susResponse: susResponse
                        }),
                        dataType: 'text',
                        success: function (data) {
                            var msg;
                            if (data == 'success') {
                                msg = '添加成功!';
                            } else if (data == 'exists') {
                                msg = '添加失败!';
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
                    layer.msg('接口名称不能为空');
                }
            });
        })
    </script>
</head>
<body>
<form class="form-horizontal layui-form">
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend><span style="color:green;">|</span>
            基本信息:
        </legend>
    </fieldset>
    <div class="layui-form-item">
        <label class="layui-form-label">接口名称</label>
        <div class="layui-input-inline">
            <input name="apiName" type="text" placeholder="请输入接口名称" autocomplete="off" id="apiName"
                   class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">所属产品：<span style="color:red;">*</span></label>
            <div class="layui-input-inline">
                <select name="productId" id="productId" class="layui-select" lay-verify="required">
                    <option value=>--请选择--</option>
                <#if productIds?exists> 
                    <#list productIds as item>  
                        <option value="${item.id?if_exists}">${item.productname?if_exists}</option>
                    </#list> 
                </#if>
                     
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">状态：<span style="color:red;">*</span></label>
            <div class="layui-input-inline">
                <select name="status" id="status" class="layui-select" lay-verify="required">
                    <option value="0">不执行</option>
                    <option value="1" selected>执行</option>
                </select>
            </div>
        </div>
    </div>
    <div class="layui-form-item ">
        <label class="layui-form-label">接口路径：<span style="color:red;">*</span></label>
        <div class="layui-input-inline" style="width: 80px">
            <select name="reqType" id="reqType" class="layui-select" lay-verify="required">
                <option value="http" selected>HTTP</option>
                <option value="https">HTTPS</option>
            </select>
        </div>
        <div class="layui-input-inline" style="width: 80px">

            <select name="method" id="method" class="layui-select" lay-verify="required">
                <option value="get">GRT</option>
                <option value="post" selected>POST</option>
            </select>
        </div>
        <div class="layui-input-inline" style="width: 300px">

            <input name="path" type="text" autocomplete="off" id="path"
                   class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">接口版本：<span style="color:red;">*</span></label>
            <div class="layui-input-inline">
                <input name="apiVersion" type="text" autocomplete="off" id="apiVersion" value="1.0.0"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">添加人员：<span style="color:red;">*</span></label>
            <div class="layui-input-inline">
                <input name="writer" type="text" autocomplete="off" id="writer" value="${frmsUser.name?if_exists}" disabled
                       class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">

        <label class="layui-form-label">业务描述:<span style="color:red;">*</span></label>
        <div class="layui-input-inline" style="width: 400px">
            <input name="discribe" type="text" autocomplete="off" id="discribe"
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
                                  style="width: 500px"></textarea>
    </div>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend><span style="color:green;">|</span>
            Response设置:
        </legend>
    </fieldset>
    <label class="layui-form-label">body:<span style="color:red;">*</span></label>
    <div class="layui-input-block">
                        <textarea name="susResponse" placeholder="" class="layui-textarea"
                                  style="width: 500px"></textarea>
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