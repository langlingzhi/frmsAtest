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

        layui.use(['form', 'table', 'layer', 'layedit', 'jquery', 'laydate'], function () {

            var $ = layui.$
                    , form = layui.form
                    , table = layui.table
                    , layer = layui.layer
                    , layedit = layui.layedit
                    , laydate = layui.laydate;

            //定义事件集合
            form.on('submit(*)', function (data) {
                var reqType = document.getElementById("reqType").value;
                var method = document.getElementById("method").value;
                var path = $("input[name='path']").val();
                var body = $("textarea[name='body']").val();

                //不能重复点击
                $("#runbutton").attr("class", "layui-btn layui-btn-disabled");
                $("#runbutton").attr("lay-filter", "runbutton");
                //提交表单

                $.ajax({
                    url: '${base}/frmsRun/totestcases',
                    async: 'true',
                    type: 'post',
                    contentType: 'application/json',
                    data: JSON.stringify({reqType: reqType, method: method, path: path, body: body}),
                    dataType: 'json',
                    success: function (data) {
                        document.getElementById("jsonResult").innerHTML = JSON.stringify(data.response);
                        document.getElementById("reqType").innerHTML = JSON.stringify(data.reqType);	//使用JSON.stringify()格式化输出JSON字符串

                        document.getElementById("method").innerHTML = JSON.stringify(data.method);	//使用JSON.stringify()格式化输出JSON字符串

                        $("input[name='path']").innerHTML = JSON.stringify(data.path);	//使用JSON.stringify()格式化输出JSON字符串

                        $("textarea[name='body']").innerHTML = JSON.stringify(data.body);	//使用JSON.stringify()格式化输出JSON字符串

//使用JSON.stringify()格式化输出JSON字符串
                    }
                })



            });
            var active = {
                        save: function () {	//保存

                            //这里是保存结果输出
                            document.getElementById("jsonResult").innerHTML = JSON.stringify(rows, null, 2);	//使用JSON.stringify()格式化输出JSON字符串
//                    tableIns.reload({data: []});	//表格数据置空

//                    to do more：            //将 rows 传给服务端进行保存或更新


                        }
                    }

//        //激活事件的通用调用方法
//        var activeByType = function (type, arg) {
//            active[type] ? (arguments.length === 2 ? active[type].call(this, arg) : active[type].call(this)) : '';
//        }
//
//        //注册按钮事件
//        $('.layui-btn[data-type]').on('click', function () {
//            var type = $(this).data('type');
//            activeByType(type);
//        });

                    //监听工具条
//        table.on('tool(dataTable)', function (obj) {
//            var data = obj.data, event = obj.event, tr = obj.tr; //获得当前行 tr 的DOM对象;
//            //console.log(data);
//            switch (event) {
//                case "type":
//                    var select = tr.find("select[name='type']");
//                    if (select) {
//                        var selectedVal = select.val();
//                        if (!selectedVal) {
//                            layer.tips("请选择一个分类", select.next('.layui-form-select'), {tips: [3, '#FF5722']}); //吸附提示
//                        }
//                        console.log('selected value of type is: ', selectedVal);
//                        $.extend(data, {'type': selectedVal});
//                        activeByType('updateRow', data);	//更新行记录对象
//                        form.render('select');	//更新select下拉选择框渲染
//                    }
//                    break;
//                case "state":
//                    var stateVal = tr.find("input[name='state']").prop('checked') ? 1 : 0;
//                    $.extend(data, {'state': stateVal})
//                    activeByType('updateRow', data);	//更新行记录对象
//                    break;
//                case "remark":
//                    var oldRemark = data.remark || '';
//
//                    //弹出输入框
//                    layer.prompt({
//                        formType: 2,	//输入框类型，支持0（文本）默认1（密码）2（多行文本）
//                        value: oldRemark,
//                        title: '请填写备注',
//                        area: ['512px', '128px'] //自定义文本域宽高
//                    }, function (value, index, elem) {
//                        var loadInx = layer.load(1);
//                        //console.log(value); //得到value
//                        layer.close(index);	//关闭 prompt
//                        $.extend(data, {'remark': value})	//更新 data 的指定属性值
//                        activeByType('updateRow', data);	//更新行记录对象
//                        layer.close(loadInx);	//关闭 load
//                    });
//                    break;
//                case "del":
//                    layer.confirm('真的删除行吗？', function (index) {
//                        layer.close(index);
//                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
//                        activeByType('removeEmptyRow');
//                    });
//                    break;
//            }
//        });
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
    <div class="layui-card">
    <div class="layui-card">
        <div class="layui-card-header">Response设置:</div>
        <div class="layui-card-body layui-text">
            <blockquote class="layui-elem-quote layui-quote-nm">
                <pre id="jsonResult"><span class="layui-word-aux">请点击“运行”查看Response……</span></pre>
            </blockquote>
        </div>
    </div>

<#--<label class="layui-form-label">body:<span style="color:red;">*</span></label>-->
<#--<div class="layui-input-block">-->
<#--<textarea name="susResponse" placeholder="" class="layui-textarea"-->
<#--style="width: 500px">${frmsapi.susResponse?if_exists}</textarea>-->
<#--</div>-->
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="*" id="runbutton">运行</button>
        </div>
    </div>
    </form>

</body>
</html>