<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>layui在线</title>
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all">
    <style>
        body{margin: 10px;}
        .demo-carousel{height: 200px; line-height: 200px; text-align: center;}
    </style>
</head>
<body>

<table class="layui-table" lay-data="{ url:'/testlayuidata', page:true, id:'idTest'}" lay-filter="demo">
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


<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-mini" lay-event="detail">查看</a>
    <a class="layui-btn layui-btn-mini" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del">删除</a>
</script>
<script src="/plugins/layui/layui.js"></script>
<script>
    layui.use(['laypage', 'layer', 'table'], function(){
        var laydate = layui.laydate //日期
                ,laypage = layui.laypage //分页
        layer = layui.layer //弹层
                ,table = layui.table //表格
                ,carousel = layui.carousel //轮播
                ,upload = layui.upload //上传
                ,element = layui.element; //元素操作

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
