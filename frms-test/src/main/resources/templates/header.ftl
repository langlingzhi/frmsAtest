<link rel="stylesheet" href="/layui-v2.1.5/css/layui.css"  media="all">
<script src="/plugins/layui/layui.js" charset="utf-8"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="/js/bootstrap-tooltip.js"></script>
<script type="text/javascript">
    layui.use(['tree', 'layer','table'], function(){
        $('.modifyPassword').bind('click',function(){
            var url="${base}/toUpPwd";
            openDialogView('修改密码',url,'450px','350px');
        })

        function openDialogView(title,url,width,height){
            parent.layer.open({
                type:2,
                title: '修改密码',
                area:[width,height],
                content: url,
                /*btn: ['关闭'],*/
                cancel: function(index){}
            });
        }
    });
</script>

<div class="header navbar navbar-inverse">
    <div class="navbar-inner">
        <div class="header-seperation"> <a class="dib pt12 pl18"><img src="images/api_left_logo.png" width=""></a><span class="logotext">风控催收平台</span>
            <ul class="nav pull-left notifcation-center" id="main-menu-toggle-wrapper" style="display:none">
                <li class="dropdown"> <a id="main-menu-toggle" href="#main-menu"  class="" > <span class="glyphicon glyphicon-align-justify"></span> </a> </li>
            </ul>
        </div>
        <div class="header-quick-nav">
            <div class="pull-left">
                <ul class="nav quick-section f16">
                    <li class="quicklinks"><a class=" c-8 cp" id="layout-condensed-toggle"> <span class="glyphicon glyphicon-align-justify"></span> </a></li>
                </ul>
                <!--
                <ul class="nav quick-section f16" style="margin-left:0px;">
                    <li class="quicklinks"> <span class="h-seperate"></span></li>
                    <li class="m-r-10 input-prepend inside search-form no-boarder"> <span class="add-on f16"><span class="glyphicon glyphicon-search"></span></span>
                        <input name="" type="text" class="no-boarder " placeholder="Search Dashboard" style="width:250px;">
                    </li>
                </ul>
                -->
            </div>
            <div class="pull-right">
                <!--
                <ul class="nav quick-section top-notice">
                    <li class="quicklinks"><a href="#" class="c-8"> <span class="iconset top-msg-dark "></span><span class="name">系统提醒</span><span class="badge badge-important animated bounceIn" id="chat-message-count">1</span> </a></li>
                </ul>
                -->
                <div class="chat-toggler">
                    <!-- <div class="profile-pic"><img src="images/img1.jpg" width="35" height="35"></div> -->
                欢迎您,${username!}${userInfo.password!}</div>
                <ul class="nav quick-section" style="margin-left:8px;">
                    <li class="quicklinks"> <span class="h-seperate"></span></li>
                    <li class="quicklinks"> <a data-toggle="dropdown" class="dropdown-toggle  pull-right c-8 f16 " href="#" id="user-options"> <span class="glyphicon glyphicon-cog"></span> </a>
                        <ul class="dropdown-menu  pull-right" role="menu" aria-labelledby="user-options">
                            <li><a href="#" id="updatePass" class="modifyPassword">修改密码</a> </li>
                            <li class="divider"></li>
                            <li><a href=""><span class="glyphicon glyphicon-off mr10 c-8"></span>退出</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>