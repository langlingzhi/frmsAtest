<#import "/spring.ftl" as spring />
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all">
    <script src="/plugins/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/jquery-1.4.3.js"></script>
    <script type="text/javascript">
        layui.use(['form', 'layer', 'layedit'], function () {
            var form = layui.form
                    , layer = layui.layer
                    , layedit = layui.layedit;
            var editIndex = layedit.build('LAY_demo_editor');

            form.on('submit(*)', function (data) {
                var caseid = $("input[name='caseid']").val();
                var casename = $("input[name='casename']").val();
                var apiid = document.getElementById("apiid").value;
                var status = document.getElementById("status").value;
                var requestparam = $("textarea[name='requestparam']").val();
                var expstatus = document.getElementById("expstatus").value;
                var expresponse = $("textarea[name='expresponse']").val();


                if (casename != "" && apiid != "" && requestparam != "" && expstatus != "") {
                    //不能重复点击
                    $("#addButton").attr("class", "layui-btn layui-btn-disabled");
                    $("#addButton").attr("lay-filter", "addButton");
                    //提交表单
                    $.ajax({
                        url: '${base}/frmsCase/addFrmsCase',
                        async: 'true',
                        type: 'post',
                        contentType: 'application/json',
                        data: JSON.stringify({
                            caseid: caseid,
                            casename: casename,
                            requestparam: requestparam,
                            expstatus: expstatus,
                            expresponse: expresponse,
                            apiid: apiid,
                            status: status
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
        });
        layui.code({
            height: '60px' //请注意必须加px。如果该key不设定，则会自适应高度，且不会出现滚动条。
            , skin: 'notepad'
        });
    </script>
</head>
<body>
<form class="layui-form">
    <div class="layui-form-item">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
            <legend><span style="color:green;">|</span>
                基本信息:
            </legend>
        </fieldset>
        <div class="layui-row">
            <div class="layui-inline layui-col-md6">
                <label class="layui-form-label">用例id</label>
                <div class="layui-input-inline layui-col-md6">
                    <input name="caseid" type="text" placeholder="请输入用例id" autocomplete="off" id="caseid"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline layui-col-md6">
                <label class="layui-form-label ">用例名称</label>
                <div class="layui-input-inline ">
                    <input name="casename" type="text" placeholder="请输入用例名称" autocomplete="off" id="casename"
                           class="layui-input">
                </div>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">接口</label>
        <div class="layui-input-inline">
            <select name="apiid" id="apiid" class="layui-select" lay-verify="required">
                <option value=>--请选择--</option>
            <#if apiIds?exists> 
                <#list apiIds as item>  
                    <option value="${item.id?if_exists}">${item.apiName?if_exists}</option>
                </#list> 
            </#if>
                 
            </select>
        </div>
        <label class="layui-form-label">是否执行</label>
        <div class="layui-input-inline">
            <select name="status" id="status" class="layui-select" lay-verify="required">
                <option value="0">不执行</option>
                <option value="1" selected>执行</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">用例参数</label>
        <div class="layui-input-block">
            <textarea name="requestparam" required lay-verify="required" placeholder="请输入用例参数"
                      class="layui-textarea"></textarea>
        </div>
    </div>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend><span style="color:green;">|</span>
            断言:
        </legend>
    </fieldset>
    <div class="layui-form-item">
        <label class="layui-form-label">预期返回状态码</label>
        <div class="layui-input-inline">
            <input name="expstatus" type="text" autocomplete="off" id="expstatus"
                   class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">返回结果</label>
        <div class="layui-input-block">
            <textarea name="expresponse" required lay-verify="required"
                      class="layui-textarea"></textarea>

        </div>
    </div>

</form>
<div class="layui-form-item">
    <div class="layui-input-block">
        <button class="layui-btn" lay-submit lay-filter="*" id="addButton">提交</button>
        <#--<button id="testButton" lay-filter="*" class="layui-btn layui-btn-primary">调试</button>-->
    <#--<button type="reset" class="layui-btn layui-btn-primary">重置</button>-->
    </div>
</div>
</body>
</html>