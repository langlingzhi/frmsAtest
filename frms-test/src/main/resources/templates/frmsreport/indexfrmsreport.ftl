<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8"/>
    <title>项目管理</title>
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
            var testName = document.getElementById("testName").value;
            $.ajax({
                type: 'POST',
                url: '${base}/frmsReport/qryFrmsReportList',
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify({testName: testName}),
                async: 'true',
                success: function (data) {
                    //展示已知数据
                    table.render({
                        elem: '#frmsReportList'
                        , data: data
                        , height: 700
                        , cols: [[ //标题栏
                            {checkbox: true, LAY_CHECKED: false} //默认全选
//                            ,{field: 'id', title: 'ID', width: 150, sort: true}
                            , {field: 'reportId', title: '报告序号', width: 150}
                            , {field: 'testName', title: '任务名称', width: 150}
                            , {field: 'testAll', title: '用例总数', width: 100}
                            , {field: 'testPass', title: '用例通过', width: 100}
                            , {field: 'testFail', title: '用例失败', width: 100}
                            , {field: 'testSkip', title: '用例跳过', width: 100}
                            , {field: 'beginTime', title: '开始时间', width: 200}
                            , {field: 'totalTime', title: '运行时间/ms', width: 150}
                            , {field: 'create_time', title: '创建时间', width: 200}
                            , {field: 'cz', title: '操作', width: 200, toolbar: '#barDemo'}
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
        //监听工具条
        table.on('tool(frmsReportFilter)', function (obj) {
            var data = obj.data;
            var id = data.id;
            var productid = data.productid;

            if (obj.event === 'detailreport') {//详细报告
                <#--window.parent.Addtabs.add({-->
                    <#--id: '',-->
                    <#--title: "<span> " + '详细报告' + "</span>",-->
                    <#--url: '${base}/frmsReport/toDetailReport?id='+ id-->
                <#--});-->
                layer.open({
                    type:2
//                    ,title: '详细报告'
                    ,area:['1600px','550px']
                    ,btn: ['关闭']
                    ,content:'${base}/frmsReport/toDetailReport?id='+ id
                    ,cancel: function(index){
                        //阻止弹框右上角x掉弹框
                    }
                    ,btn2:function(index, layero){

                    }
                    ,end:function(){
                        //只要窗口关闭都会执行end回调
                        init();
                    }
                })
            }
        });
    })
</script>
<script id="barDemo" type="text/html">
    <a class="layui-btn layui-btn-mini" lay-event="detailreport">详细报告</a>
</script>
<body class="gray-bg">
<div class="row">
    <div class="col-sm-12" style="padding-bottom: 15px;"></div>
    <div class="col-sm-12" style="padding-bottom: 15px;">
        <form class="form-inline" onsubmit="return false;">
            <div class="form-group ml30">
                <label for="exampleInputName2">任务名称:</label>
                <select id="testName" class="form-control ml10" style="width: 160px">
                    <option value="">--请选择--</option>
                <#if TaskNames?exists> 
                    <#list TaskNames as item>  
                        <option value="${item.testName?if_exists}">${item.testName?if_exists}</option>
                    </#list> 
                </#if>
                     
                </select>
            </div>

            <div class="form-group ml30">
                <button id="checkQuery" data-method="checkQuery"
                        class="layui-btn layui-btn-small layui-btn-radius layui-btn-blue">查询<span
                        class="layui-badge layui-bg-gray"><i class="layui-icon">&#xe615;</i></span></button>
            </div>
        </form>
    </div>
</div>
<div class="pl20 pb20 table-responsive pt30">
    <table class="layui-table" lay-filter="frmsReportFilter" id="frmsReportList">
    </table>
</div>
</body>
</html>

