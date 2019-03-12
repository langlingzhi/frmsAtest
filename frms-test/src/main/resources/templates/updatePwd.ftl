<#import "/spring.ftl" as spring />
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="/plugins/layui/css/layui.css"  media="all">
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="/plugins/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/jquery-1.4.3.js"></script>
    <script type="text/javascript">
        layui.use(['form','layer'], function(){
            $("#editButton").click(function () {
                var newPwd=$('#newPwd').val();
                var oldPwd=$('#oldPwd').val();
                var verifyPwd=$('#verifyPwd').val();
                var loginNm= "${username}";
                if(oldPwd == ""){
                    layui.layer.msg("原始密码不能为空!", {icon: 5});
                } else if (!newPwd.match(/^[0-9a-zA-Z_#]{6,12}$/)) {
                    layui.layer.msg("密码必须6到12位数字字母符号组成!", {icon: 5});
                } else if(newPwd != verifyPwd){
                    layui.layer.msg("两次密码输入不一致！", {icon: 5});
                } else {
                    $.ajax({
                        url:'${base}/updatePwd',
                        async:'false',
                        type:'post',
                        contentType:'application/json',
                        data:JSON.stringify({newPwd:newPwd,no:loginNm,oldPwd:oldPwd}),
                        dataType:'json',
                        success:function(data){
                            var msg ;
                            if(data == "0"){
                                msg = '修改密码成功！';
                            } else if(data == "2"){
                                msg = '原始密码不一致！';
                            } else if(data == "3"){
                                msg = '获取原始密码错误，请重新登陆！';
                            } else {
                                msg = '修改密码失败！';
                            }
                            layui.layer.msg(msg,function(){
                                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                                parent.layer.close(index);
                            });
                        },
                        error:function(){
                        }
                    })
                }
            });
        })
    </script>
</head>
<body class="gray-bg">
<form class="form-horizontal layui-form">
    <div class="form-group col-sm-10">
        <label class="col-sm-4 control-label"></label>
    </div>
    <div class="form-group col-sm-10">
        <label class="col-sm-4 control-label">原始密码</label>
        <div class="col-sm-6">
            <input type="password" name="" placeholder="请输入" autocomplete="off" lay-verify="oldPwd" id="oldPwd"
                   class="layui-input">
        </div>
    </div>
    <div class="form-group col-sm-10">
        <label class="col-sm-4 control-label">新密码</label>
        <div class="col-sm-6">
            <input id="newPwd" type="password" name="" placeholder="请输入" autocomplete="off" lay-verify="newPwd"
                   class="layui-input">
        </div>
    </div>
    <div class="form-group col-sm-10">
        <label class="col-sm-4 control-label">确认新密码</label>
        <div class="col-sm-6">
            <input type="password" name="" placeholder="请输入" autocomplete="off" lay-verify="verifyPwd" id="verifyPwd"
                   class="layui-input">
        </div>
    </div>
</form>
<div class="form-group col-sm-10">
    <label class="col-sm-4 control-label"></label>
    <div class="col-sm-6">
        <button class="layui-btn" lay-submit lay-filter="*" id="editButton">立即提交</button>
        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
    </div>
</div>
</body>
</html>