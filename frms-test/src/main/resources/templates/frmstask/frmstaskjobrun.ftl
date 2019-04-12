<#import "/spring.ftl" as spring />
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all">
    <script src="/plugins/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/jquery-1.4.3.js"></script>
    <script type="text/javascript">
        layui.use(['form', 'layer', 'laydate'], function () {
            var form = layui.form
                    , laydate = layui.laydate
                    , layer = layui.layer;

            form.on('submit(*)', function (data) {
                var id=${frmstask.id};
                var taskname="${frmstask.taskname}";
                var productid=${frmstask.productid};

                var jobcron = $("#jobcron").val();

                var email = $("input[name='email']").val();

                if (jobcron != "" && email != "") {
                    //不能重复点击
                    $("#addButton").attr("class", "layui-btn layui-btn-disabled");
                    $("#addButton").attr("lay-filter", "addButton");
                    //提交表单
                    $.ajax({
                        url: '${base}/frmsTask/frmsTaskTaskJobrun',
                        async: 'true',
                        type: 'post',
                        contentType: 'application/json',
                        data: JSON.stringify({id:id,taskname:taskname,productid:productid,jobcron: jobcron, email: email}),
                        dataType: 'text',
                        success: function (data) {
                            var msg;
                            if (data == 'success') {
                                msg = '添加成功!  ';
                            } else if (data == 'exists') {
                                msg = '添加失败!';
                            } else {
                                msg = '后台异常,请联系管理员!';
                            }
                            layer.msg(msg, function () {
                                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                                parent.layer.close(index);
                            });
                        },
                        error: function () {

                        }
                    })
                } else {
                    layer.msg('时间或邮件不能为空');
                }
            });

            var myDate = new Date();//获取系统当前时间
            var ins22 = laydate.render({
                elem: '#testdata'
                , min: myDate.toLocaleDateString()
                , max: '2070-10-14'
                , ready: function () {
                    ins22.hint('不选日期则默认每天定时执行，选择则按指定日期执行一次');
                }
            });
            laydate.render({
                elem: '#testtime'
                ,type: 'time'
                ,btns: ['clear', 'confirm']
            });
        });

    </script>
</head>
<body>
<form class="layui-form">
    <div class="layui-form-item" style="margin-top: 20px;margin-right: 20px" >
        <div class="layui-inline" style="text-align:center" >
            <span style="color:red;">*定时任务根据填写的cron表达式在后台执行</span>
        </div>
        <div class="layui-inline" style="margin-top: 20px" >
            <label class="layui-form-label">定时cron:</label>
            <div class="layui-input-inline">
                <input name="jobcron" type="text" placeholder="cron表达式" autocomplete="off" id="jobcron" value="${frmstask.jobcron?if_exists}"
                       class="layui-input">
            </div>
        </div>
        <#--<div class="layui-inline" style="margin-top: 20px;margin-right: 20px" >-->
            <#--<label class="layui-form-label">日&nbsp&nbsp&nbsp&nbsp期:</label>-->
            <#--<div class="layui-input-inline">-->
                <#--<input type="text" class="layui-input" id="testdata" placeholder="yyyy-MM-dd" >-->
            <#--</div>-->
        <#--</div>-->
        <#--<div class="layui-inline" style="margin-top: 20px;margin-right: 20px">-->
            <#--<label class="layui-form-label">时&nbsp&nbsp&nbsp&nbsp间:</label>-->
            <#--<div class="layui-input-inline">-->
                <#--<input type="text" class="layui-input" id="testtime" placeholder="HH:mm:ss" >-->
            <#--</div>-->
        <#--</div>-->
    </div>
    <div class="layui-form-item" style="margin-top: 20px;margin-right: 20px">
        <label class="layui-form-label">邮箱地址:</label>
        <div class="layui-input-inline">
            <input name="email" type="text" placeholder="每次任务执行邮箱发送地址" autocomplete="off" id="email" value="${frmstask.email?if_exists}"
                   class="layui-input">
        </div>
    </div>
</form>
<div class="layui-form-item">
    <div class="layui-input-block" style="margin-top: 20px">
        <button class="layui-btn" lay-submit lay-filter="*" id="addButton">提交</button>
    <#--<button type="reset" class="layui-btn layui-btn-primary">重置</button>-->
    </div>
</div>
</body>
</html>