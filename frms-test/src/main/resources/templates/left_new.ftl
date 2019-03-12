<nav class="navbar-default navbar-static-side" role="navigation">
    <div class="nav-close"><i class="fa fa-times-circle"></i>
    </div>
    <div class="sidebar-collapse">
        <ul class="nav" id="side-menu">
            <li class="nav-header">
                <div class="dropdown profile-element">
                    <span><img alt="image" class="" src="/images/api_logo.png"
                               style="width: 110px;height: 50px"/></span>
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                                <span class="text-muted text-xs block">${frmsUser.name?if_exists}<b class="caret"></b></span>
                                </span>
                    </a>
                    <ul class="dropdown-menu animated fadeInRight m-t-xs">
                        <li><a class="J_menuItem" href="/toUpPwd">修改密码</a>
                        <li class="divider"></li>
                        <li><a href="/logout">安全退出</a></li>
                    </ul>
                </div>
                <div class="logo-element">接口管理
                </div>
            </li>
            <li>
                <a href="#">
                    <i class="fa fa-home"></i>
                    <span class="nav-label">数据管理</span>
                    <span class="fa arrow"></span>
                </a>
                <ul class="nav nav-second-level">
                    <li>
                        <a class="J_menuItem" href="/frmsEnv/getQryFrmsEnv" data-index="1">项目管理</a>
                    </li>
                    <li>
                        <a class="J_menuItem" href="/frmsApi/getQryFrmsApi" data-index="2">接口管理</a>
                    </li>
                    <li>
                        <a class="J_menuItem" href="/frmsCase/toFrmsCaseLeft" data-index="3">用例管理</a>
                    </li>
                </ul>
            </li>

            <li>
                <a href="#">
                    <i class="fa fa-home"></i>
                    <span class="nav-label">任务管理</span>
                    <span class="fa arrow"></span>
                </a>
                <ul class="nav nav-second-level">
                    <li>
                        <a class="J_menuItem" href="/frmsTask/getQryFrmsTask" data-index="4">任务管理</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="#">
                    <i class="fa fa-home"></i>
                    <span class="nav-label">日志管理</span>
                    <span class="fa arrow"></span>
                </a>
                <ul class="nav nav-second-level">
                    <li>
                        <a class="J_menuItem" href="/frmsReport/getQryFrmsReport"
                           data-index="5">日志管理</a>
                    </li>
                </ul>
                <ul class="nav nav-second-level">
                    <li>
                        <a class="J_menuItem" href="/frmsReport/toTable"
                           data-index="6">报告</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="#">
                    <i class="fa fa-home"></i>
                    <span class="nav-label">工具集</span>
                    <span class="fa arrow"></span>
                </a>
                <ul class="nav nav-second-level">
                    <li>
                        <a class="J_menuItem" href="/frmsRun/toTable" data-index="7">jsonschma工具</a>
                    </li>
                    <li>
                        <a class="J_menuItem" href="/frmsRun/toTableAdd" data-index="8">接口调试</a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</nav>