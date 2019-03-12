<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>接口测试平台</title>
    <!--[if lt IE 8]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <link rel="shortcut icon" href="images/api_logo.png" style=""/>
    <link href="/plugins/webarch/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="/plugins/webarch/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="/plugins/webarch/css/animate.css" rel="stylesheet">
    <link href="/plugins/webarch/css/style.css?v=4.0.0" rel="stylesheet">
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<div id="wrapper">
    <!--左侧导航开始-->
    <#include "left_new.ftl">
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                <div class=""><a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i></a>
                </div>
            </nav>
        </div>
        <div class="row content-tabs">
            <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
            </button>
            <nav class="page-tabs J_menuTabs">
                <div class="page-tabs-content">
                    <a href="javascript:;" class="active J_menuTab" data-id="index_page_link">首页</a>
                </div>
            </nav>
            <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
            </button>
            <div class="btn-group roll-nav roll-right">
                <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span></button>
                <ul role="menu" class="dropdown-menu dropdown-menu-right">
                    <li class="J_tabShowActive"><a>定位当前选项卡</a></li>
                    <li class="divider"></li>
                    <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                    </li>
                    <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                    </li>
                </ul>
            </div>
            <a href="/logout" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
        </div>
        <div class="row J_mainContent" id="content-main">
            <iframe class="J_iframe" style="overflow-y: hidden;overflow-x: hidden;" name="iframe0" width="100%" height="100%" src="" frameborder="0" data-id="index_page_link" seamless></iframe>
        </div>
    </div>
</div>
</div>
</div>

<!-- 全局js -->
<script src="/plugins/webarch/js/jquery.min.js?v=2.1.4"></script>
<script src="/plugins/webarch/js/bootstrap.min.js?v=3.3.5"></script>
<script src="/plugins/webarch/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="/plugins/webarch/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="/plugins/webarch/js/plugins/layer/layer.min.js"></script>

<!-- 自定义js -->
<script src="/plugins/webarch/js/webarch.js?v=4.0.0"></script>
<script type="text/javascript" src="/plugins/webarch/js/contabs.js"></script>



</body>

</html>