<#import "/spring.ftl" as spring />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all">
    <script src="/plugins/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/jquery-1.4.3.js"></script>
    <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
    <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
    <style type="text/css">
        /*以下样式可写入自己的样式表中，也可写在当前页面中*/
        .childBody {
            padding: 15px;
        }

        /*layui 元素样式改写*/
        .layui-btn-sm {
            line-height: normal;
            font-size: 12.5px;
        }

        .layui-table-view .layui-table-body {
            min-height: 256px;
        }

        .layui-table-cell .layui-input.layui-unselect {
            height: 30px;
            line-height: 30px;
        }

        /*设置 layui 表格中单元格内容溢出可见样式*/
        .table-overlay .layui-table-view,
        .table-overlay .layui-table-box,
        .table-overlay .layui-table-body {
            overflow: visible;
        }

        /*根据自己的需要，是否应用此样式：隐藏单元格中 tips 层*/
        /*.layui-table-tips{display: none;}*/

        /*文本对齐方式*/
        .text-center {
            text-align: center;
        }

        /*超链接悬浮时出现下划线*/
        .text-link-underline:hover {
            text-decoration: underline;
        }

        /*注：以下样式须写在当前页面中，根据 date-filed 的名称来设置该列内容溢出可见*/
        td[data-field='type'] .layui-table-cell,
        td[data-field='createTime'] .layui-table-cell {
            height: auto;
            overflow: visible;
        }
    </style>
    <script type="text/javascript">
        layui.use(['form', 'table', 'layer', 'layedit', 'jquery', 'laydate'], function () {

            var $ = layui.$
                    , form = layui.form
                    , table = layui.table
                    , layer = layui.layer
                    , layedit = layui.layedit
                    , laydate = layui.laydate;

//            init();//页面初始化数据
            var tableRes = $("#tableRes"), tbWidth = tableRes.width();
            var layTableId = "layTable";
            var tableIns;
            var caseid = document.getElementById("caseid").value;
            $.ajax({
                type: 'POST',
                url: '${base}/frmsVparam/qryFrmsVparamList',
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify({caseid: caseid}),
                async: 'true',
                success: function (data) {
                    tableIns = table.render({
                        elem: '#dataTable',
                        id: layTableId,
                        data: data,
                        width: tbWidth,
                        page: false,  //是否显示分页
                        limits: [10, 20, 30],
                        limit: 10,//每页默认显示的数量                    loading: true,
                        even: false, //不开启隔行背景
                        text: {none: '无数据，去添加一行~'},
                        cols: [[
                            {field: 'id', title: '序号', width: 100, edit: 'text'},
                            {field: 'paramKey', title: '参数名', width: 100, edit: 'text'},
                            {field: 'paramType', title: '参数类型', width: 100, edit: 'text'},
                            {field: 'pramValue', title: '参数值', width: 100, edit: 'text'},
//                            {field: 'caseid', title: '备注', width: 100, edit: 'text'},
                            {field: 'cz', title: '操作', width: 100, toolbar: '#barDemo'}
                        ]],
                    })
                }
            })

            var active = {
                addRow: function () {	//添加一行
                    var rows = table.cache[layTableId];	//table.cahce 中保存着当前页面中的表格数据
                    var fid = 0;
                    console.log(rows);
                    if (rows == undefined) {
                        fid = 1
                    }
                    else {
                        fid = rows.length + 1
                    }

                    var newRow = {
                        id: rows.length + 1,
                        paramKey: "",
                        paramType: '',
                        pramValue: "",
                        caseid: caseid
                    };
                    rows.push(newRow);
                    tableIns.reload({
                        data: rows
                    });
                },
                updateRow: function (obj) {	//更新行记录对象
                    var rows = table.cache[layTableId];
                    console.log(rows);
                    for (var i = 0, row; i < rows.length; i++) {
                        row = rows[i];
                        if (row.id == obj.id) {
                            $.extend(rows[i], obj);
                            break;
                        }
                    }
                    tableIns.reload({
                        data: rows
                    });
                },
                removeEmptyRow: function () {	//移除表格数据中的空对象记录
                    var rows = table.cache[layTableId];
                    console.log(rows);
                    for (var i = 0, row; i < rows.length; i++) {
                        row = rows[i];
                        if (!row || !row.id) {
                            rows.splice(i, 1);    //删除一项
                        }
                        continue;
                    }
                    console.log(rows);
                    tableIns.reload({
                        data: rows
                    });
                },
                save: function () {	//保存
                    var rows = table.cache[layTableId];
                    console.log(rows);
                    if (rows.length > 0) {
                        for (var i = 0, row; i < rows.length; i++) {
                            row = rows[i];
                            if (!row && paramKey != "" && paramType != "" && pramValue != "") {
                                layer.msg("检查每一行，请选择分类！", {icon: 5, offset: 't'}); //提示
                                return;
                            }
                        }
                        //这里是保存结果输出
//                        document.getElementById("jsonResult").innerHTML = JSON.stringify(rows, null, 2);	//使用JSON.stringify()格式化输出JSON字符串
//                    tableIns.reload({data: []});	//表格数据置空

//                    to do more：            //将 rows 传给服务端进行保存或更新
                        for (var i = 0, row; i < rows.length; i++) {
                            row = rows[i];
                            $.ajax({
                                url: '${base}/frmsVparam/addFrmsVparam',
                                async: 'true',
                                type: 'post',
                                contentType: 'application/json',
                                data: JSON.stringify({
                                    paramKey: row.paramKey,
                                    paramType: row.paramType,
                                    pramValue: row.pramValue,
                                    caseid: caseid
                                }),
                            })
                            //将 rows 传给服务端进行保存或更新
                        }

                        layer.msg(rows.length + ' 条记录保存成功！', {icon: 1, offset: 't'});
                    } else {
                        layer.msg('无数据，去添加一行吧！', {icon: 5, offset: 't'});
                    }
                }
            }

            //激活事件的通用调用方法
            var activeByType = function (type, arg) {
                active[type] ? (arguments.length === 2 ? active[type].call(this, arg) : active[type].call(this)) : '';
            }

            //注册按钮事件
            $('.layui-btn[data-type]').on('click', function () {
                var type = $(this).data('type');
                activeByType(type);
            });

            //监听工具条
            table.on('tool(dataTable)', function (obj) {
                var data = obj.data, event = obj.event, tr = obj.tr; //获得当前行 tr 的DOM对象;
                //console.log(data);
                switch (event) {
                    case "state":
                        var stateVal = tr.find("input[name='state']").prop('checked') ? 1 : 0;
                        $.extend(data, {'state': stateVal})
                        activeByType('updateRow', data);	//更新行记录对象
                        break;
                    case "del":
                        layer.confirm('真的删除行吗？', function (index) {
                            layer.close(index);
                            obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                            activeByType('removeEmptyRow');
                        });
                        break;
                }
            });

            form.on('submit(*)', function (data) {
//                var id = $("input[name='id']").val();
                var caseid = $("input[casename='caseid']").val();
                var casename = $("input[casename='casename']").val();
                var requestparam = $("textarea[name='requestparam']").val();
                var expstatus = $("input[name='expstatus']").val();
                var expresponse = $("textarea[name='expresponse']").val();
                var apiid = document.getElementById("apiid").value;
                if (id != "") {
                    //不能重复点击
                    $("#editButton").attr("class", "layui-btn layui-btn-disabled");
                    $("#editButton").attr("lay-filter", "editButton");
                    //提交表单
                    $.ajax({
                        url: '${base}/frmsCase/frmsCaseModify',
                        async: 'true',
                        type: 'post',
                        contentType: 'application/json',
                        data: JSON.stringify({
//                            id: id,
                            caseid: caseid,
                            casename: casename,
                            requestparam: JSON.stringify(requestparam),
                            expstatus: expstatus,
                            expresponse: expresponse,
                            apiid: apiid
                        }),
                        dataType: 'text',
                        success: function (data) {
                            var msg;
                            if (data == 'success') {
                                msg = '修改成功!';
//                                activeByType('save');

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
                    layer.msg("" +
                            "用例名称或状态不能为空!", function () {
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
    <script id="barDemo" type="text/html">
        <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
    </script>
</head>
<body>
<form class="layui-form">
    <div class="layui-form-item">
        <div class="layui-row">
            <div class="layui-inline layui-col-md6">

                <label class="layui-form-label">用例id</label>
                <div class="layui-input-inline layui-col-md6">
                    <input name="caseid" type="text" placeholder="请输入用例id" value="${frmscase.caseid?if_exists}"
                           autocomplete="off" id="caseid"
                           class="layui-input" disabled>
                </div>
            </div>
            <div class="layui-inline layui-col-md6">
                <label class="layui-form-label ">用例名称</label>
                <div class="layui-input-inline ">
                    <input name="casename" type="text" value="${frmscase.casename?if_exists}" placeholder="请输入用例名称"
                           autocomplete="off" id="casename" class="layui-input">
                </div>
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">接口</label>
        <div class="layui-input-inline">
            <select name="apiid" id="productid" class="layui-select" lay-verify="required" disabled>
                <option value="">--请选择--</option>
            <#if apiIds?exists> 
                <#list apiIds as item>  
                    <option value="${item.id}"
                            <#if frmscase.apiid == item.id>selected</#if>
                    >${item.apiName?if_exists}</option>
                </#list> 
            </#if>
                 
            </select>
        </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">用例参数</label>
        <div class="layui-input-block">
            <textarea name="requestparam" required lay-verify="required" class="layui-textarea"
                      autocomplete="off">${frmscase.requestparam?if_exists}</textarea>
        </div>
    </div>
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend><span style="color:green;">|</span>
            断言:
        </legend>
    </fieldset>
    <div class="layui-form-item">
        <label class="layui-form-label">期待返回状态码</label>
        <div class="layui-input-inline">
            <input name="expstatus" class="layui-input" type="text" value="${frmscase.expstatus?if_exists}"
                   autocomplete="off">
        </div>
    </div>
    <div id="toolbar">
        <div>
            <button type="button" class="layui-btn layui-btn-sm" data-type="addRow" title="添加一行">
                <i class="layui-icon layui-icon-add-1"></i> +
            </button>
        </div>
    </div>

    <div class="tableRes">
        <table class="layui-hide" lay-even lay-skin="nob" lay-filter="dataTable" id="dataTable" style="">
        </table>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">期待返回结果</label>
        <div class="layui-input-block">
            <textarea name="expresponse" required lay-verify="required" class="layui-textarea" value="${frmscase.expresponse?if_exists}"
                      autocomplete="off"></textarea>
        </div>
    </div>
</form>
<div class="layui-form-item">
    <div class="layui-input-block">
        <button class="layui-btn" lay-submit lay-filter="*" id="editButton">提交</button>
        <button id="testButton" lay-filter="*" class="layui-btn layui-btn-primary">调试</button>
    </div>
</div>
</body>
</html>