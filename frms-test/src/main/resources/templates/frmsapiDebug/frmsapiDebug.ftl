<#import "/spring.ftl" as spring />
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all">
    <script src="/plugins/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/jquery-1.4.3.js"></script>
    <script type="text/javascript">
        layui.use(['form', 'layer', 'layedit', 'element'], function () {
            var element = layui.element;
            var form = layui.form
                    , layer = layui.layer
                    , layedit = layui.layedit;
            var editIndex = layedit.build('LAY_demo_editor');

            //获取hash来切换选项卡，假设当前地址的hash为lay-id对应的值
            var layid = location.hash.replace(/^#test1=/, '');
            element.tabChange('test1', layid); //假设当前地址为：http://a.com#test1=222，那么选项卡会自动切换到“发送消息”这一项

            //监听Tab切换，以改变地址hash值
            element.on('tab(test1)', function () {
                location.hash = 'test1=' + this.getAttribute('lay-id');
            });
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


                if (apiName != "" && productId != "" && method != "" && path != "" && reqType != "") {
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
    <div class="layui-form-item ">
        <div class="layui-tab layui-tab-card" lay-filter="test1">
            <ul class="layui-tab-title">
                <li class="layui-this" lay-id="1">header</li>
                <li lay-id="2" href="/frmsApi/toFrmsApiModify">body</li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item ">

                </div>
                <div class="layui-tab-item layui-show">
                    <div class="layui-input-block">
                        <textarea name="body" placeholder="" class="layui-textarea"
                                  style="width: 500px"></textarea>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-form-item ">
    <label class="layui-form-label">返回结果:<span style="color:red;">*</span></label>
    <div class="layui-input-block">
                        <textarea name="susResponse" placeholder="" class="layui-textarea"
                                  style="width: 500px"></textarea>
    </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="*" id="addButton">发送</button>
        <#--<button type="reset" class="layui-btn layui-btn-primary">重置</button>-->
        </div>
    </div>
</body>
</html>