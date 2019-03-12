<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
    <meta charset="utf-8" />
    <title>风控催收系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    <!-- Bootstrap -->

    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css">

    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all">
    <script src="/js/jquery.js"></script>
    <script src="/js/bootstrap-datetimepicker.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <#--<script src="/js/jquery.animateNumbers.js"></script>-->
    <#--<script src="/js/jquery.sidr.min.js"></script>-->
    <#--<script src="/js/breakpoints.js"></script>-->
    <#--<script src="/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>-->
    <#--<script src="/plugins/bootstrap-select2/select2.min.js" type="text/javascript"></script>-->
    <!-- END PAGE LEVEL PLUGINS -->
    <#--<script src="/js/core.js"></script>-->
    <#--<script src="/js/form_validations.js" type="text/javascript"></script>-->
    <script src="/plugins/layui/layui.js"></script>

    <link href="/plugins/webarch/css/style.css?v=4.0.0" rel="stylesheet">

</head>

<body class="gray-bg">
<div class="ibox-content">
    <div class="row">
        <div class="col-sm-12" style="padding-bottom: 15px;">
            <form class="form-inline">
                <div class="form-group">
                    <label for="exampleInputName2">Name</label>
                    <input type="text" class="form-control ml10" id="exampleInputName2" placeholder="Jane Doe">
                </div>
                <div class="form-group ml30">
                    <label for="exampleInputName2">Name</label>
                    <input type="text" class="form-control ml10" id="exampleInputName2" placeholder="Jane Doe">
                </div>
                <div class="form-group ml30">
                    <label for="exampleInputName2">Name</label>
                    <input type="text" class="form-control ml10" id="exampleInputName2" placeholder="Jane Doe">
                </div>
                <button type="button" class="btn btn-primary ml30"> 搜索</button>
            </form>
        </div>
    </div>
</div>
<div class="ibox-content">
    <div class="row" style="padding-bottom: 15px;">
        <div class="col-sm-12" style="padding-bottom: 15px;">
            <div class="form-inline">
                <div class="form-group">
                    <label for="exampleInputName2">Name</label>
                    <input type="text" class="form-control ml10" id="exampleInputName2" placeholder="Jane Doe">
                </div>
                <div class="form-group ml30">
                    <label for="exampleInputName2">Name</label>
                    <input type="text" class="form-control ml10" id="exampleInputName2" placeholder="Jane Doe">
                </div>
                <div class="form-group ml30">
                    <label for="exampleInputName2">Name</label>
                    <input type="text" class="form-control ml10" id="exampleInputName2" placeholder="Jane Doe">
                </div>
                <div style="padding-top: 20px;text-align: center">
                    <button type="button" class="btn btn-primary ml30"> 搜索1</button>
                </div>
            </div>
        </div>
    </div>
    <div class="row" style="padding-bottom: 15px;">
        <div class="col-sm-12" style="padding-bottom: 15px;">
            <div class="form-inline">
                <div class="form-group">
                    <label for="exampleInputName2">Name</label>
                    <input type="text" class="form-control ml10" id="exampleInputName2" placeholder="Jane Doe">
                </div>
                <div class="form-group ml30">
                    <label for="exampleInputName2">Name</label>
                    <input type="text" class="form-control ml10" id="exampleInputName2" placeholder="Jane Doe">
                </div>
                <div class="form-group ml30">
                    <label for="exampleInputName2">Name</label>
                    <input type="text" class="form-control ml10" id="exampleInputName2" placeholder="Jane Doe">
                </div>
                <div style="padding-top: 20px;text-align: center">
                    <button type="button" class="btn btn-primary ml30"> 搜索1</button>
                </div>
            </div>
        </div>
    </div>
    <div class="row" style="padding-bottom: 15px;">
        <button type="button" class="btn btn-primary ml30"> 搜索2</button>
    </div>
</div>

<div class="pl20 pb20 table-responsive pt30">
    <table class="layui-table" height="400px;" lay-data="{ url:'/testlayuidata', page:true, id:'idTest'}" lay-filter="demo">
        <thead>
        <tr>
            <th lay-data="{field:'id', width:80, sort: true, fixed: true}">ID</th>
            <th lay-data="{field:'username', width:80}">用户名</th>
            <th lay-data="{field:'sex', width:80, sort: true}">性别</th>
            <th lay-data="{field:'city', width:80}">城市</th>
            <th lay-data="{field:'sign', width:177}">签名</th>
            <th lay-data="{field:'experience', width:80, sort: true}">积分</th>
            <th lay-data="{field:'score', width:80, sort: true}">评分</th>
            <th lay-data="{field:'classify', width:80}">职业</th>
            <th lay-data="{field:'wealth', width:135, sort: true}">财富</th>
            <th lay-data="{field:'wealth1', width:135, sort: true}">财富1</th>
            <th lay-data="{field:'wealth2', width:135, sort: true}">财富2</th>
            <th lay-data="{field:'wealth3', width:135, sort: true}">财富3</th>
            <th lay-data="{field:'wealth4', width:135, sort: true}">财富4</th>
            <th lay-data="{field:'wealth5', width:135, sort: true}">财富5</th>
            <th lay-data="{field:'wealth6', width:135, sort: true}">财富6</th>
            <th lay-data="{fixed: 'right', width:160, align:'center', toolbar: '#barDemo'}"></th>
        </tr>
        </thead>
    </table>
</div>


<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-mini" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
</script>
<script>
    layui.use(['laypage', 'layer', 'table','laydate'], function(){
        var laydate = layui.laydate //日期
                ,laypage = layui.laypage //分页
        layer = layui.layer //弹层
                ,table = layui.table //表格
                ,carousel = layui.carousel //轮播
                ,upload = layui.upload //上传
                ,element = layui.element; //元素操作

        laydate.render({
            elem: '#datetimepicker' //指定元素
        });

        //监听工具条
        table.on('tool(demo)', function(obj){ //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                    ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                layer.msg('查看操作');
            } else if(layEvent === 'del'){
                layer.confirm('真的删除行么', function(index){
                    obj.del(); //删除对应行（tr）的DOM结构
                    layer.close(index);
                    //向服务端发送删除指令
                });
            } else if(layEvent === 'edit'){
                layer.msg('编辑操作');
                console.log(obj);
                console.log(obj.data.id);
//                var checkStatus = table.checkStatus('idTest'); //test即为基础参数id对应的值
//                console.log(checkStatus.data) //获取选中行的数据
//                console.log(checkStatus.data.length) //获取选中行数量，可作为是否有选中行的条件
//                console.log(checkStatus.isAll ) //表格是否全选
            }
        });
        //分页
        laypage.render({
            elem: 'pageDemo' //分页容器的id
            ,count: 1000 //总页数
            ,skin: '#1E9FFF' //自定义选中色值
            //,skip: true //开启跳页
            ,jump: function(obj, first){
                if(!first){
                    layer.msg('第'+ obj.curr +'页');
                }
            }
        });
    });
</script>
</body>
</html>
