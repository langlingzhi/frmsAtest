<#import "/spring.ftl" as spring />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="/plugins/layui/css/layui.css"  media="all">
    <script src="/plugins/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/jquery-1.4.3.js"></script>
    <script type="text/javascript">
        layui.use(['form','layer','layedit'], function(){
            var form=layui.form
                    ,layer = layui.layer
                    ,layedit = layui.layedit;
            var editIndex = layedit.build('LAY_demo_editor');

            form.on('submit(*)', function(data){
                var id=$("input[name='id']").val();
                var caseid=$("input[casename='caseid']").val();
                var casename=$("input[casename='casename']").val();
                var requestparam=$("textarea[name='requestparam']").val();
                var expstatus=$("input[name='expstatus']").val();
                var expresponse=$("textarea[name='expresponse']").val();
                var apiid = document.getElementById("apiid").value;
                var status= document.getElementById("status").value;
                if(id != ""){
                    //不能重复点击
                    $("#editButton").attr("class","layui-btn layui-btn-disabled");
                    $("#editButton").attr("lay-filter","editButton");
                    //提交表单
                    $.ajax({
                        url:'${base}/frmsCase/frmsCaseModify',
                        async:'true',
                        type:'post',
                        contentType:'application/json',
                        data:JSON.stringify({id:id,caseid:caseid,casename:casename,requestparam:requestparam,expstatus:expstatus,expresponse:expresponse,apiid:apiid,status:status}),
                        dataType:'text',
                        success:function(data){
                            var msg ;
                            if(data=='success'){
                                msg = '修改成功!';
                            } else if(data == 'exists'){
                                msg = '修改失败!';
                            } else {
                                msg = '后台异常,请联系管理员!';
                            }
                            layer.msg(msg,function(){
                                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                                parent.layer.close(index);
                            });
                        },
                        error:function(){
                        }
                    })
                } else {
                    layer.msg("" +
                            "用例名称或状态不能为空!",function(){
                        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                        parent.layer.close(index);
                    });
                }
            });
            form.verify({
                //数据验证
            });
        })
    </script>
</head>
<body>
<form class="layui-form" style="margin-top: 20px">
    <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">预期返回码：</label>
                <div class="layui-input-inline">
                    <input name="expstatus" type="text" value="${frmscase.expstatus?if_exists}" autocomplete="off" id="expstatus"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label ">实际返回码：</label>
                <div class="layui-input-inline ">
                    <input name="relstatus" type="text"  value="${frmscase.relstatus?if_exists}" autocomplete="off" id="relstatus" class="layui-input">
                </div>
            </div>
    </div>
    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">真实返回结果：</label>
        <div class="layui-input-block">
            <textarea name="relresponse" required lay-verify="required" class="layui-textarea"  autocomplete="off">${frmscase.relresponse?if_exists}</textarea>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">断言结果：</label>
        <div class="layui-input-inline">
            <input name="verifyresult" class="layui-input" type="text" value="${frmscase.verifyresult?if_exists}" autocomplete="off">
        </div>
    </div>

    <div class="layui-form-item layui-form-text">
        <label class="layui-form-label">Log：</label>
        <div class="layui-input-block">
            <textarea name="log" required lay-verify="required"  class="layui-textarea"  autocomplete="off">${frmscase.log?if_exists}</textarea>
        </div>
    </div>
</form>
</body>
</html>