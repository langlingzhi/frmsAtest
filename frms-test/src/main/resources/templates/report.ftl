<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8"/>
    <meta charset="utf-8"/>
    <title>测试报告</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <!-- Bootstrap -->
    <link href="/css/editor-awesome.min.css" rel="stylesheet">
    <link href="/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="/css/jquery.sidr.light.css" rel="stylesheet" type="text/css">
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/css/style.css" rel="stylesheet" type="text/css">
    <link href="/css/pages.css" rel="stylesheet" type="text/css">
    <link href="/css/responsive.css" rel="stylesheet" type="text/css">
    <script src="/js/jquery.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/jquery.animateNumbers.js"></script>
    <script src="/js/jquery.sidr.min.js"></script>
    <script src="/js/breakpoints.js"></script>
    <script src="/js/core.js"></script>
</head>
<style>
    #customers
    {
        font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
        width:100%;
        border-collapse:collapse;
    }
    #customers td, #customers th
    {
        font-size:1em;
        border:1px solid #98bf21;
        padding:3px 7px 2px 7px;
    }
    #customers th
    {
        font-size:1.1em;
        text-align:left;
        padding-top:5px;
        padding-bottom:4px;
    }

</style>
<body>
<p>你好：<br><br>接口自动化平台在 ${Reports.create_time?if_exists} 完成一次定时测试任务<br><br>本次共执行测试场景概况如下：<br><br></p>

<body class="gray-bg">
<table id="customers">
    <tr>
        <th>报告序号</th>
        <th>用例名称</th>
        <th>用例总数</th>
        <th><span style="color:green;">用例通过</span></th>
        <th><span style="color:red;">用例失败</span></th>
        <th><span style="color:blue;">用例跳过</span></th>
        <th>运行时间</th>
    </tr>
    <tr>
        <td>${Reports.reportId?if_exists}</td>
        <td>${Reports.testName?if_exists}</td>
        <td>${Reports.testAll?if_exists}</td>
        <td><span style="color:green;">${Reports.testPass?if_exists}</span></td>
        <td><span style="color:red;">${Reports.testFail?if_exists}</span></td>
        <td><span style="color:blue;">${Reports.testSkip?if_exists}</span></td>
        <td>${Reports.totalTime?if_exists}</td>
    </tr>
</table>
<br><br>
<p><span style="color:red;">错误详情请参考附件中的离线测试报告!(请先从邮箱中下载在本地打开查看，否则会出现样式错误!)</span></p>
</body>
</html>
