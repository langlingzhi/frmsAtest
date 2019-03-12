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
    <link href="/plugins/dtree/dtree.css" rel="stylesheet" media="all">
    <link href="/plugins/dtree/font/iconfont.css" rel="stylesheet" media="all">
    <script src="/js/jquery.js"></script>
    <script src="/plugins/layui/layui.js" charset="utf-8"></script>
    <script src="/plugins/dtree/dtree.js" charset="utf-8"></script>
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
    layui.config({
        base: '../plugins/dtree/' //配置 layui 第三方扩展组件存放的基础目录
    }).extend({
        dtree: 'dtree' //定义该组件模块名
    }).use(['element', 'layer', 'dtree'], function () {
        var layer = layui.layer,
                dtree = layui.dtree,
                $ = layui.$;


        dtree.render({
            elem: "#demoTree1",  //绑定元素
            url: "${base}/frmsCase/toTreejson",  //异步接口
            useIframe: true,  //启用iframe
            menubar: true,
            iframe: {
                iframeElem: "#ifcontent",  // iframe的ID
                iframeUrl: "/frmsCase/getQryFrmsCases", // iframe路由到的地址
                iframeLoad: "leaf",// 表示点击任意节点加载iframe
                iframeDefaultRequest: {nodeId: "apiId"} // 这里就将nodeId这个参数名称改为了id这个名称
//                iframeRequest: {apiId: parseInt("id")} // 这里就自定义了需要传递的参数
            }
        });
        dtree.on("node('demoTree1')", function (param) {
            layer.msg(JSON.stringify(data));
        });
    });
</script>
<body>
<#--<div class="layui-col-lg1" style="width: 12.33333333%;">-->
<div class="layui-col-lg2">
    <div style="">
        <ul id="demoTree1" class="dtree" data-id="0"></ul>
    </div>
</div>
<#--<div class="layui-col-lg11" style="width: 87.66666667%;height: 800px">-->
<div class="layui-col-lg10" style="height: 800px">
    <iframe id="ifcontent" src="/frmsCase/getQryFrmsCase"
            name="ifcontent" width="100%" height="100%" frameborder="0" data-id="0">
</div>
</body>
</html>
