<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8"/>
    <title>接口管理</title>
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
    layui.use(['table', 'form', 'layer', 'upload', 'element', 'laydate'], function () {
        var table = layui.table,
                laydate = layui.laydate,
                layer = layui.layer,
                upload = layui.upload,
                element = layui.element,
                form = layui.form;

        //页面初始化数据
        init();

        //初始化函数
        function init() {
            var apiName = $('#apiName').val();
            var status = document.getElementById("status").value;
            var productId = document.getElementById("productId").value;
            $.ajax({
                type: 'POST',
                url: '${base}/frmsApi/qryFrmsApiList',
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify({apiName: apiName, status: status, productId: productId}),
                async: 'true',
                success: function (data) {
                    //展示已知数据
                    table.render({
                        elem: '#frmsApiList'
                        , data: data
                        , height: 700
                        , cols: [[ //标题栏
                            {checkbox: true, LAY_CHECKED: false} //
                            , {field: 'id', title: '接口编号', width: 150, sort: true}
                            , {field: 'apiName', title: '接口名称', width: 150}
                            , {field: 'productname', title: '所属产品', width: 100}
                            , {field: 'path', title: '请求路径', width: 200}
                            , {field: 'status', title: '是否执行', width: 100}
                            , {field: 'update_time', title: '更新时间', width: 200}
                            , {field: 'cz', title: '操作', width: 200, toolbar: '#barDemo'}
                        ]]
                        , skin: 'row' //表格风格
                        , even: true
                        , page: true //是否显示分页
                        , limits: [10, 20, 30]
                        , limit: 10 //每页默认显示的数量
                    });
                },

            })

        };


        $('#entering').click(function () {
            $('#entering').attr('href', '${base}/frmsApi/toaddFrmsApi');
            return true;
        });
        $('#checkQuery').on('click', function () {
            init();
        });
        $('#layui-button').find('button').on('click', function () {
            var othis = $(this), method = othis.data('method');
            active[method] ? active[method].call(this, othis) : '';
        });
        $('#addButton').on('click', function () {
            layer.open({
                type: 2
                , title: '新增接口'
                , area: ['900px', '700px']
                , content: '${base}/frmsApi/toaddFrmsApi'
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
            if (obj.event === 'del') {//删除测试用例
                layer.confirm('确定删除么', function (index) {
                    //数据库删除
                    $.ajax({
                        url: '${base}/frmsApi/delFrmsApi?id=' + id,
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
            }
            else if (obj.event === 'edit') {//编辑用户信息
                layer.open({
                    type: 2
                    , title: '修改接口'
                    , area: ['900px', '700px']
                    //,btn: ['关闭']
                    , content: '${base}/frmsApi/toFrmsApiModify?id=' + id
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
            else if (obj.event === 'test') {//调试接口
                layer.open({
                    type: 2
                    , title: '调试接口'
                    , area: ['900px', '700px']
                    //,btn: ['关闭']
                    , content: '${base}/frmsApi/toFrmsApiTest?id=' + id
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
    });
</script>
<script id="barDemo" type="text/html">
    <a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-normal layui-btn-mini" lay-event="test">调试</a>
    <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>

</script>
<body class="gray-bg">
<div>
    <div class="row">
        <div class="col-sm-12" style="padding-bottom: 15px;"></div>
        <div class="col-sm-12" style="padding-bottom: 15px;">
            <form class="form-inline" onsubmit="return false;">
                <div class="form-group ml30">
                    <label for="exampleInputName2">接口名称</label>
                    <input type="text" class="form-control ml10" id="apiName" placeholder="请输入接口名称">
                </div>
                <div class="form-group ml30">
                    <label for="exampleInputName2">是否执行</label>
                    <select id="status" class="form-control ml10" style="width: 160px">
                        <option value="">请选择</option>
                        <option value='1'>执行</option>
                        <option value='0'>不执行</option>
                    </select>
                </div>
                <div class="form-group ml30">
                    <label for="exampleInputName2">产品:</label>
                    <select id="productId" class="form-control ml10" style="width: 160px">
                        <option value="">--请选择--</option>
                    <#if productIds?exists> 
                        <#list productIds as item>  
                            <option value="${item.id?if_exists}">${item.productname?if_exists}</option>
                        </#list> 
                    </#if>
                         
                    </select>
                </div>
                <div class="form-group ml30">
                    <button id="checkQuery" data-method="checkQuery"
                            class="layui-btn layui-btn-small layui-btn-radius layui-btn-blue">查询<span
                            class="layui-badge layui-bg-gray"><i class="layui-icon">&#xe615;</i></span></button>
                </div>
            <#--<div class="form-group ml30" id="layui-button">-->
            <#--<a class="layui-btn layui-btn-small layui-btn-radius layui-btn-warm" href="" id="entering">新增接口<span-->
            <#--class="layui-badge layui-bg-gray"><i class="layui-icon">&#xe630;</i></span></a>-->
            <#--</div>-->
                <div class="form-group ml30">
                    <button class="btn btn-warning" data-method="add" data-type="auto" id="addButton">新增接口<span
                            class="layui-badge layui-bg-gray"><i class="layui-icon">&#xe630;</i></span></button>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="pl20 pb20 table-responsive pt30">
    <table class="layui-table" lay-filter="entrustOrderFilter" id="frmsApiList">
    </table>
</div>
</body>
</html>

