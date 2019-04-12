<#import "/spring.ftl" as spring />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8"/>
    <meta name="renderer" content="webkit"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="format-detection" content="telephone=no"/>
    <title>layui table 添加一行并实现在编辑行记录后保存数据的方法</title>
    <!-- 注意：layui.css 路径需要改成你本地的 -->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all">
    <script src="/plugins/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/jquery-1.4.3.js"></script>
    <script type="text/javascript"></script>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <script type="text/javascript">

        layui.use(['form', 'layer', 'layedit', 'element'], function () {
            var element = layui.element;
            var form = layui.form
                    , layer = layui.layer
                    , layedit = layui.layedit;
            var editIndex = layedit.build('LAY_demo_editor');

            form.on('submit(*)', function (data) {
                var reqType = document.getElementById("productId").value;
                var method = document.getElementById("status").value;
                var path = $("input[name='path']").val();
                var body = $("textarea[name='body']").val();



                if (reqType != "" && method != "" && path != "") {
                    $.ajax({
                        url: '${base}/frmsRun/totestcases',
                        async: 'true',
                        type: 'post',
                        contentType: 'application/json',
                        data: JSON.stringify({
                            reqType: reqType,
                            method: method,
                            path: path,
                            body: body
                        }),
                        dataType: 'text',
                        success: function (data) {
                            $.ajax({
                                url: '${base}/frmsRun/toTableAdd'
                            })


            }
                })

        }
            })
        })

    </script>

</head>
<body class="">

    <form class="layui-form">
        <div class="layui-form-item " style="margin-top: 20px">
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
            <div class="layui-input-inline" style="width: 500px">

                <input name="path" type="text" autocomplete="off" id="path"
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
                Response:
            </legend>
        </fieldset>

        <div class="layui-form-item">
            <label class="layui-form-label">code：<span style="color:red;">*</span></label>
            <div class="layui-input-inline">
                <input name="falResponse" id="falResponse" type="text" autocomplete="off"
                       class="layui-input" >
            <#--value="${frmsapi.falResponse?if_exists}"-->


            </div>
        </div>
        <div class="layui-form-item" style="margin-top: 20px">
            <label class="layui-form-label">body:<span style="color:red;">*</span></label>
            <div class="layui-input-block">
                        <textarea name="susResponse" id="susResponse" placeholder="" class="layui-textarea"
                                  style="width: 500px" ></textarea>
            <#--${frmsapi.susResponse?if_exists}-->
            </div>
        </div>
    </form>
    <div class="layui-form-item">
        <div class="layui-input-block" style="margin-top: 20px">
            <button class="layui-btn" lay-submit lay-filter="*" id="runbutton">运行</button>
        <#--<button  class="layui-btn layui-btn-primary" lay-filter="*" id="testButton">调试</button>-->
        </div>
</body>
</html>