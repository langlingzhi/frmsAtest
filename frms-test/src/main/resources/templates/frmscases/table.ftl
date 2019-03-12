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
</head>
<body class="childBody">
<section class="layui-col-md10" style="margin: 0 auto; float: none;">
    <div class="layui-card">
        <div class="layui-card-header">基于 layui table 添加一行并实现在编辑行记录后保存数据的方法</div>
        <div class="layui-card-body">
            <div id="toolbar">
                <div>
                    <button type="button" class="layui-btn layui-btn-sm" data-type="addRow" title="添加一行">
                        <i class="layui-icon layui-icon-add-1"></i> 添加一行
                    </button>
                    <span class="layui-word-aux"><i class="layui-icon layui-icon-tips"></i> <label
                            id="batchTip">温馨提示…</label></span>
                </div>
            </div>
            <div id="tableRes" class="table-overlay">
                <table id="dataTable" lay-filter="dataTable" class="layui-hide"></table>
            </div>
            <div id="action" class="text-center">
                <button type="button" name="btnSave" class="layui-btn" data-type="save">
                    <i class="layui-icon layui-icon-ok-circle"></i>保存
                </button>
                <button type="reset" name="btnReset" class="layui-btn layui-btn-primary"
                        onclick="window.location.reload();">取消
                </button>
            </div>
        </div>
    </div>

    <!--保存结果输出-->
    <div class="layui-card">
        <div class="layui-card-header">保存结果输出：</div>
        <div class="layui-card-body layui-text">
            <blockquote class="layui-elem-quote layui-quote-nm">
                <pre id="jsonResult"><span class="layui-word-aux">请点击“保存”后查看输出信息……</span></pre>
            </blockquote>
        </div>
    </div>
</section>
<!--recommended script position-->
<!-- 注意：layui.js 路径需要改成你本地的 -->
<script type="text/javascript">
    //准备视图数据、可访问对象及方法
    var vm = {
        batchSize: 100,	//每批次保存记录数（这个值可以自定义）
        tbData: [{
            tempId: new Date().valueOf(),
            type: 2,
            name: '测试项名称',
            state: 1,
            createTime: '2018-08-06',
            remark: '测试项备注'
        }],
        typeData: [
            {id: 1, name: '分类一'},
            {id: 2, name: '分类二'},
            {id: 3, name: '分类三'},
            {id: 4, name: '分类四'}
        ],
        renderSelectOptions: function (data, settings) {
            if (data && data.length > 0) {
                settings = settings || {};
                var valueField = settings.valueField || 'value',	//配置下拉选项集合中渲染成 option value 的属性名称
                        textField = settings.textField || 'text',	//配置下拉选项集合中渲染成 option text 的属性名称
                        selectedValue = settings.selectedValue || "";	//选中值
                var html = [];
                for (var i = 0, item; i < data.length; i++) {
                    item = data[i];
                    html.push('<option value="');
                    html.push(item[valueField]);	//给 option 的 value 赋值
                    html.push('"');
                    if (selectedValue && item[valueField] == selectedValue) {
                        html.push(' selected="selected"');	//设置选中值
                    }
                    html.push('>');
                    html.push(item[textField]);	//给 option 的显示文本赋值
                    html.push('</option>');
                }
                return html.join('');	//将返回一组 <option> 标签
            } else {
                return "";
            }
        }
    };

    //layui 模块化引用
    layui.use(['jquery', 'layer', 'table', 'laydate'], function () {
        var $ = layui.$, layer = layui.layer, table = layui.table, form = layui.form, laydate = layui.laydate;

        //每批次处理数的温馨提示
        var batchTips = "一次至多添加" + vm.batchSize + "行数据记录，若要继续添加，请先保存本批次的行记录！";
        $("#batchTip").text(batchTips);

        //数据表格实例化
        var tableRes = $("#tableRes"), tbWidth = tableRes.width();
        var layTableId = "layTable";
        var tableIns = table.render({
            elem: '#dataTable',
            id: layTableId,
            data: vm.tbData,
            width: tbWidth,
            page: {limit: vm.batchSize, limits: [vm.batchSize]},
            loading: true,
            even: false, //不开启隔行背景
            text: {none: '无数据，去添加一行~'},
            cols: [[
                {title: '序号', type: 'numbers'},
                {
                    field: 'type', title: '分类（type）',width: 100, templet: function (d) {
                    var options = vm.renderSelectOptions(vm.typeData, {
                        valueField: "id",
                        textField: "name",
                        selectedValue: d.type
                    });
                    return '<a lay-event="type"></a><select name="type" lay-search lay-filter="type"><option value="">请选择分类</option>' + options + '</select>';
                }
                },
                {field: 'name', title: '名称（name）',width: 100, edit: 'text'},
                {
                    field: 'state', title: '是否启用（state）', event: 'state', templet: function (d) {
                    var html = ['<input type="checkbox" name="state" lay-skin="switch" lay-text="是|否"'];
                    html.push(d.state > 0 ? ' checked' : '');
                    html.push('/>');
                    return html.join('');
                }
                },
                {
                    field: 'createTime', title: '创建时间（createAt）',width: 100, templet: function (d) {
                    return '<input type="text" name="createTime" value="' + (d.createTime || '') + '" placeholder="yyyy-MM-dd" readonly="readonly" class="layui-input layui-input-date" />';
                }
                },
                {
                    field: 'remark', title: '备注（弹窗修改）',width: 100, event: 'remark', templet: function (d) {
                    return d.remark ? '<a href="javascript:void(0);" class="text-link-underline" title="点击修改备注">' + d.remark + '</a>' :
                            '<a href="javascript:void(0);" class="layui-badge layui-bg-gray" title="点击添加备注">添加备注</a>';
                }
                },
                {
                    field: 'tempId', title: '操作',width: 100, templet: function (d) {
                    return '<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del" lay-id="' +
                            d.tempId + '"><i class="layui-icon layui-icon-delete"></i>移除</a>';
                }
                }
            ]],
            done: function (res, curr, count) {
                //console.log(res.data);
                var layTable = tableRes.find(".layui-table-body>table");    //渲染后的 layui-table Dom 对象
                //日期控件
                $(".layui-input-date").each(function (i) {
                    laydate.render({
                        elem: this,
                        format: "yyyy-MM-dd",
                        done: function (value, date) {
                            if (res && res.data[i]) {
                                $.extend(res.data[i], {'createTime': value})
                                activeByType('updateRow', res.data[i]);	//更新行记录对象
                            }
                        }
                    });
                });
            }
        });

        //定义事件集合
        var active = {
            addRow: function () {	//添加一行
                var rows = table.cache[layTableId];	//table.cahce 中保存着当前页面中的表格数据
                console.log(rows);
                if (rows.length >= vm.batchSize) {
                    layer.msg(batchTips, {icon: 5, anim: 6}); //抖动提示一次至多添加多少行
                    return;
                }
                var newRow = {
                    tempId: new Date().valueOf(),
                    type: null,
                    name: '请填写名称',
                    state: 0,
                    createTime: '',
                    remark: ''
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
                    if (row.tempId == obj.tempId) {
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
                for (var i = 0, row; i < rows.length; i++) {
                    row = rows[i];
                    if (!row || !row.tempId) {
                        rows.splice(i, 1);    //删除一项
                    }
                    continue;
                }
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
                        if (!row.type) {
                            layer.msg("检查每一行，请选择分类！", {icon: 5, offset: 't'}); //提示
                            return;
                        }
                    }
                    //这里是保存结果输出
                    document.getElementById("jsonResult").innerHTML = JSON.stringify(rows, null, 2);	//使用JSON.stringify()格式化输出JSON字符串
                    tableIns.reload({data: []});	//表格数据置空

                    //to do more：将 rows 传给服务端进行保存或更新

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

        //监听select下拉选中事件
        form.on('select(type)', function (data) {
            var elem = data.elem; //得到select原始DOM对象
            $(elem).prev("a[lay-event='type']").trigger("click");
        });

        //监听工具条
        table.on('tool(dataTable)', function (obj) {
            var data = obj.data, event = obj.event, tr = obj.tr; //获得当前行 tr 的DOM对象;
            //console.log(data);
            switch (event) {
                case "type":
                    var select = tr.find("select[name='type']");
                    if (select) {
                        var selectedVal = select.val();
                        if (!selectedVal) {
                            layer.tips("请选择一个分类", select.next('.layui-form-select'), {tips: [3, '#FF5722']}); //吸附提示
                        }
                        console.log('selected value of type is: ', selectedVal);
                        $.extend(data, {'type': selectedVal});
                        activeByType('updateRow', data);	//更新行记录对象
                        form.render('select');	//更新select下拉选择框渲染
                    }
                    break;
                case "state":
                    var stateVal = tr.find("input[name='state']").prop('checked') ? 1 : 0;
                    $.extend(data, {'state': stateVal})
                    activeByType('updateRow', data);	//更新行记录对象
                    break;
                case "remark":
                    var oldRemark = data.remark || '';

                    //弹出输入框
                    layer.prompt({
                        formType: 2,	//输入框类型，支持0（文本）默认1（密码）2（多行文本）
                        value: oldRemark,
                        title: '请填写备注',
                        area: ['512px', '128px'] //自定义文本域宽高
                    }, function (value, index, elem) {
                        var loadInx = layer.load(1);
                        //console.log(value); //得到value
                        layer.close(index);	//关闭 prompt
                        $.extend(data, {'remark': value})	//更新 data 的指定属性值
                        activeByType('updateRow', data);	//更新行记录对象
                        layer.close(loadInx);	//关闭 load
                    });
                    break;
                case "del":
                    layer.confirm('真的删除行吗？', function (index) {
                        obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                        layer.close(index);
                        activeByType('removeEmptyRow');
                    });
                    break;
            }
        });
    });
</script>
</body>
</html>