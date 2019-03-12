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
    <title>Insert title here</title>
</head>
<body>
<#--<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">-->
    <#--<legend>接口测试用例修改</legend>-->
<#--</fieldset>-->
<form class="layui-form">
    <#--<div class="layui-form-item">-->
        <#--<label class="layui-form-label">用例id</label>-->
        <#--<div class="layui-input-inline">-->
            <#--<input type="hidden" name="id" value="${frmscase.id}"/>-->
            <#--<input name="caseid" class="layui-input" type="text" value="${frmscase.caseid?if_exists}" autocomplete="off">-->
        <#--</div>-->
    <#--</div>-->
    <#--<div class="layui-form-item">-->
        <#--<label class="layui-form-label">用例名称</label>-->
        <#--<div class="layui-input-inline">-->
            <#--<input name="casename" class="layui-input" type="text" value="${frmscase.casename?if_exists}" autocomplete="off">-->
        <#--</div>-->
    <#--</div>-->
    <div class="layui-form-item">
        <div class="layui-row">
            <div class="layui-inline layui-col-md6">

                <label class="layui-form-label">用例id</label>
                <div class="layui-input-inline layui-col-md6">
                    <input name="caseid" type="text" placeholder="请输入用例id" value="${frmscase.caseid?if_exists}" autocomplete="off" id="caseid"
                           class="layui-input" disabled>
                </div>
            </div>
            <div class="layui-inline layui-col-md6">
                <label class="layui-form-label ">用例名称</label>
                <div class="layui-input-inline ">
                    <input name="casename" type="text"  value="${frmscase.casename?if_exists}" placeholder="请输入用例名称" autocomplete="off" id="casename" class="layui-input">
                </div>
            </div>
        </div>
    </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">用例参数</label>
            <div class="layui-input-block">
                <textarea name="requestparam" required lay-verify="required" class="layui-textarea"  autocomplete="off">${frmscase.requestparam?if_exists}</textarea>
            </div>
        </div>
    <#--<div class="layui-form-item">-->
        <#--<label class="layui-form-label">用例参数</label>-->
        <#--<div class="layui-input-inline">-->
            <#--&lt;#&ndash;<input name="requestparam" class="layui-input" type="text" value="${frmscase.requestparam?if_exists}" autocomplete="off">&ndash;&gt;-->
            <#--<textarea name="requestparam" required lay-verify="required" placeholder="请输入用例参数" class="layui-textarea" value="${frmscase.requestparam?if_exists}" autocomplete="off"></textarea>-->

        <#--</div>-->
    <#--</div>-->

    <div class="layui-form-item">
        <label class="layui-form-label">期待返回状态码</label>
        <div class="layui-input-inline">
            <input name="expstatus" class="layui-input" type="text" value="${frmscase.expstatus?if_exists}" autocomplete="off">
        </div>
    </div>
    <#--<div class="layui-form-item">-->
        <#--<label class="layui-form-label">期待返回结果</label>-->
        <#--<div class="layui-input-inline">-->
            <#--&lt;#&ndash;<input name="expresponse" class="layui-input" type="text" value="${frmscase.expresponse?if_exists}" autocomplete="off">&ndash;&gt;-->
            <#--<textarea name="expresponse" required lay-verify="required" placeholder="请输入用例参数" class="layui-textarea" value="${frmscase.expresponse?if_exists}" autocomplete="off"></textarea>-->
        <#--</div>-->
    <#--</div>-->
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">期待返回结果</label>
            <div class="layui-input-block">
                <textarea name="expresponse" required lay-verify="required"  class="layui-textarea"  autocomplete="off">${frmscase.expresponse?if_exists}</textarea>
            </div>
        </div>
    <div class="layui-form-item">
        <label class="layui-form-label">接口</label>
        <div class="layui-input-inline">
            <select name="apiid" id="productid" class="layui-select" lay-verify="required" >
                <option value="">--请选择--</option>
            <#if apiIds?exists> 
                <#list apiIds as item>  
                    <option value="${item.id}"
                            <#if frmscase.apiid == item.id>selected</#if>
                    >${item.apiName?if_exists}</option>
                </#list> 
            </#if> 
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">是否执行</label>
        <div class="layui-input-inline">
            <select name="status" id="status" class="layui-select" lay-verify="required" >
                <option value="">--请选择--</option>
                <option value="0" <#if frmscase.status == 0>selected</#if>>不执行</option>
                <option value="1" <#if frmscase.status == 1>selected</#if>>执行</option>
            </select>
        </div>
    </div>
</form>
<div class="layui-form-item">
    <div class="layui-input-block">
        <button class="layui-btn" lay-submit lay-filter="*" id="editButton">提交</button>
    <button id="testButton" lay-filter="*" class="layui-btn layui-btn-primary">调试</button>
    </div>
</div>
</body>
</html>