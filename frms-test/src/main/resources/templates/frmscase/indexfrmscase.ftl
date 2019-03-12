<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8"/>
    <title>用例管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <!-- Bootstrap -->
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/plugins/layui/css/layui.css" rel="stylesheet" media="all">
    <script src="/js/jquery.js"></script>
    <script src="/plugins/layui/layui.js" charset="utf-8"></script>
    <script src="/js/bootstrap-datetimepicker.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/jquery.animateNumbers.js"></script>
    <script src="/js/jquery.sidr.min.js"></script>
    <script src="/js/breakpoints.js"></script>
    <script src="/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
    <script src="/plugins/bootstrap-select2/select2.min.js" type="text/javascript"></script>
    <!-- END PAGE LEVEL PLUGINS -->
    <script src="/js/core.js"></script>
    <link href="/plugins/webarch/css/style.css?v=4.0.0" rel="stylesheet">
</head>
<script>
    layui.use(['table', 'form', 'layer'], function () {
        var table = layui.table,
                layer = layui.layer,
                form = layui.form;
        //页面初始化数据
        init();

        //初始化函数
        function init() {
            var casename = $('#name').val();
//            var status = document.getElementById("status").value;
            var apiid = document.getElementById("apiid").value;


            $.ajax({
                type: 'POST',
                url: '${base}/frmsCase/qryFrmsCaseList',
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify({casename: casename, apiid: apiid}),
                async: 'true',
                success: function (data) {
                    //展示已知数据
                    table.render({
                        elem: '#frmsCaseList'
                        , data: data
                        , height: 315
                        , cols: [[ //标题栏
                            {checkbox: true, LAY_CHECKED: false} //默认全选
//                            ,{field: 'id', title: 'ID', width: 150, sort: true}
//                            ,{field: 'apiName', title: '所属接口', width: 150}
                            , {field: 'caseid', title: '用例id', width: 150}
                            , {field: 'casename', title: '用例名称', width: 150}
                            , {field: 'requestparam', title: '请求参数', width: 200}
//                            ,{field: 'relstatus', title: '真实返回状态码', width: 200}
//                            ,{field: 'relresponse', title: '真实返回结果', width: 200}
                            , {field: 'expstatus', title: '期待返回状态码', width: 150}
//                            ,{field: 'expresponse', title: '期待返回结果', width: 100}
                            , {field: 'verifyobj', title: '断言点', width: 150}
                            ,{field: 'verifyresult', title: '执行结果', width: 100}
//                            , {field: 'status', title: '是否有效', width: 100}
//                            ,{field: 'create_time', title: '创建时间', width: 100}
                            , {field: 'update_time', title: '更新时间', width: 100}
                            , {field: 'cz', title: '操作', width: 250, toolbar: '#barDemo'}
                        ]]
                        , skin: 'row' //表格风格
                        , even: true
                        , page: true //是否显示分页
                        , limits: [10, 20, 30]
                        , limit: 10 //每页默认显示的数量
                    });
                },
                error: function () {
                    parent.layer.alert('请求错误！');
                }
            })

        }

        $('#checkQuery').on('click', function () {
            init();
        });
        $('#addButton').on('click', function () {
            layer.open({
                type: 2
                , title: '添加用例'
                , area: ['900px', '700px']
                , content: '${base}/frmsCase/toaddFrmsCase?apiid=' + apiid
                , cancel: function (index) {
                    //阻止弹框右上角x掉弹框
                }
                , btn2: function (index, layero) {

                }
                , end: function () {
                    //只要窗口关闭都会执行end回调
                    init();
                }
            })
        });
        //监听工具条
        table.on('tool(entrustOrderFilter)', function (obj) {
            var data = obj.data;
            var id = data.id;
            var caseid = data.caseid;

            if (obj.event === 'del') {//删除测试用例
                layer.confirm('确定删除么', function (index) {
                    //数据库删除
                    $.ajax({
                        url: '${base}/frmsCase/delFrmsCase?caseid=' + caseid,
                        dataType: 'text',
                        success: function (data) {
                            var msg;
                            if (data == 'success') {
                                msg = "删除成功！";
                            } else if (data == 'exists') {
                                msg = "删除失败！";
                            } else if (data == 'false') {
                                msg = "后台异常,请联系管理员!";
                            }
                            layer.msg(msg, function () {
                                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                                parent.layer.close(index);
                            })
                            init();
                        },
                        error: function () {

                        }
                    })
                });
            } else if (obj.event === 'edit') {
                layer.open({
                    type: 2
                    , title: '修改测试用例'
                    , area: ['800px', '700px']
                    //,btn: ['关闭']
                    , content: '${base}/frmsCase/toFrmsCaseModify?id=' + id
                    , cancel: function (index) {
                        //阻止弹框右上角x掉弹框
                    }
                    , btn2: function (index, layero) {

                    }
                    , end: function () {
                        //只要窗口关闭都会执行end回调
                        init();
                    }
                })
            }
            else if (obj.event === 'test') {
                layer.confirm('确定执行么', function (index) {
                    //数据库删除
                    $.ajax({
                        url: '${base}/frmsCase/testFrmsCase?id=' + id,
                        dataType: 'text',
                        success: function (data) {
                            var msg;
                            if (data == 'success') {
                                msg = "执行成功！";
                            } else if (data == 'exists') {
                                msg = "执行失败！";
                            } else if (data == 'false') {
                                msg = "后台异常,请联系管理员!";
                            }
                            layer.msg(msg, function () {
                                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                                parent.layer.close(index);
                            })
                            init();
                        },
                        error: function () {

                        }
                    })
                });
            }
            else if (obj.event === 'log') {//执行日志
                layer.open({
                    type: 2
                    , title: '执行日志'
                    , area: ['800px', '700px']
                    //,btn: ['关闭']
                    , content: '${base}/frmsCase/toFrmsCaseLog?id=' + id
                    , cancel: function (index) {
                        //阻止弹框右上角x掉弹框
                    }
                    , btn2: function (index, layero) {

                    }
                    , end: function () {
                        //只要窗口关闭都会执行end回调
                        init();
                    }
                })
            }
        });
    })
</script>
<script id="barDemo" type="text/html">
    <#--<a class="layui-btn layui-btn-mini" lay-event="show">查看</a>-->
    <a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-mini" lay-event="test">执行</a>
    <a class="layui-btn layui-btn-mini" lay-event="log">执行日志</a>
    <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
</script>
<body class="gray-bg">
<div>
    <div class="row">
        <div class="col-sm-12" style="padding-bottom: 15px;"></div>
        <div class="col-sm-12" style="padding-bottom: 15px;">
            <form class="form-inline" onsubmit="return false;">
                <div class="form-group ml30">
                    <label for="exampleInputName2">用例名称</label>
                    <input type="text" class="form-control ml10" id="casename" placeholder="请输入接用例名称">
                </div>
                <#--<div class="form-group ml30">-->
                    <#--<label for="exampleInputName2">是否执行</label>-->
                    <#--<select id="status" class="form-control ml10" style="width: 160px">-->
                        <#--<option value="">请选择</option>-->
                        <#--<option value="1">执行</option>-->
                        <#--<option value="0">不执行</option>-->
                    <#--</select>-->
                <#--</div>-->
                <div class="form-group ml30">
                    <label for="exampleInputName2">所属接口</label>
                    <select id="apiid" class="form-control ml10" style="width: 160px">
                        <option value="">--请选择--</option> 
                    <#if apiIds?exists> 
                    <#list apiIds as item>  
                    <option value="${item.id}">${item.apiName?if_exists}</option>
                    </#list> 
                    </#if> 
                    </select>
                </div>
                <div class="form-group ml30">
                    <button id="checkQuery" data-method="checkQuery"
                            class="layui-btn layui-btn-small layui-btn-radius layui-btn-blue">查询<span
                            class="layui-badge layui-bg-gray"><i class="layui-icon">&#xe615;</i></span></button>
                </div>
                <#--<div class="form-group ml30">-->
                    <#--<button class="btn btn-warning" data-method="add" data-type="auto" id="addButton">&nbsp;&nbsp;新增&nbsp;&nbsp;</button>-->
                <#--</div>-->
            </form>
        </div>
    <#--<div class="col-sm-12" style="padding-bottom: 15px;">-->
    <#--<div class="col-sm-offset-5 col-sm-2">-->
    <#--<button class="btn btn-warning" data-method="add" data-type="auto" id="addButton">&nbsp;&nbsp;新增&nbsp;&nbsp;</button>-->
    <#--</div>-->
    <#--</div>-->
    </div>
</div>
<div class="pl20 pb20 table-responsive pt30">
    <table class="layui-table" lay-filter="entrustOrderFilter" id="frmsCaseList" style="">
    </table>
</div>
</body>
</html>

