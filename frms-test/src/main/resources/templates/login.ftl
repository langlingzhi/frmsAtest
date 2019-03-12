<#import "/spring.ftl" as spring />
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Atest</title>
    <link rel="shortcut icon" href="/images/api_logo.png" />
    <link href="/css/style.css" rel="stylesheet" type="text/css">
    <link href="/css/pages.css" rel="stylesheet" type="text/css">
    <link href="/css/editor-awesome.min.css" rel="stylesheet">
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/css/login.css" rel="stylesheet" type="text/css">

    <script type="text/javascript" src="/js/jquery.js"></script>
</head>

<body style=" gray-bg no-repeat center top;background-size:100%;" onkeydown="doKeyDown()">
<div class="body-main">
    <div class="login-box">
        <div class="login-form">
            <div class="login-title tc pb40"><span class="c-blue f30 dib pt20 vam">Atest</span></div>
            <form id="formStr">
                <div class="input-mk mb20">
                    <div class="mks">
                        <label class="userico"></label>
                        <input type="text" class="inputtip" id="no" placeholder="请输入用户名">
                    </div>
                </div>
                <div class="input-mk mb20">
                    <div class="mks">
                        <label class="keyico"></label>
                        <input type="password" class="inputtip" id="password" placeholder="请输入密码">
                    </div>
                </div>
                <div class="input-mk mb20 c-blue f14 pl16">
                    <div class="checkbox check-success">
                        <input id="checkbox2" value="1" checked="checked" type="checkbox">
                        <label for="checkbox2">记住我</label>
                    </div>
                    <div id="warnMsg" style="color: red">${msg!}</div>
                </div>
                <div class="input-mk mb20">
                    <input type="button" class="login-btn" value="登 录" onclick="tologin();">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
<script>
    window.onload = function () {
        if (top.location.href != location.href) {
            top.location.href = location.href;
        }
    }
    function tologin(){
        var no = $('#no').val();
        var password = $('#password').val();
        if (no == '' || no == null){
            $("#warnMsg").text("用户名不能为空！");
        } else if (password == '' || password == null){
            $("#warnMsg").text("密码不能为空！");
        } else {
            var para = {'no':no, 'password':password};
            $.post("${base}/login",para,function(data){
//            console.log(data);
                if(data.status == '1'){
                    window.location.href="/";
                } else {
                    $("#warnMsg").text(data.msg);
                }
            },"json");
        }
    }
    function doKeyDown(event) {
        var e=event || window.event || arguments.callee.caller.arguments[0];
        if (e.keyCode == 13) {
            tologin();
        }
    }
</script>