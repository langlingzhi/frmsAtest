<#import "/spring.ftl" as spring />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="/plugins/layui/css/layui.css" media="all">
    <script src="/plugins/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/js/jquery-1.4.3.js"></script>
    <script type="text/javascript">
        layui.use(['form', 'layer'], function () {
            var form = layui.form
                    , layer = layui.layer
                    , $ = layui.jquery;
            $('#reportButton').on('click', function () {
                layer.open({
                    type:2
//                    ,title: '详细报告'
                    ,area:['1600px','550px']
                    ,btn: ['关闭']
                    ,content:'${base}/frmsReport/toTable'
                    ,cancel: function(index){
                        //阻止弹框右上角x掉弹框
                    }
                    ,btn2:function(index, layero){

                    }
                    ,end:function(){
                        //只要窗口关闭都会执行end回调
                        init();
                    }
                })
            });

        })
    </script>
    <title>Insert title here</title>
</head>
<body>
<div class="layui-form-item" style="margin-top: 20px;margin-right: 20px" >
    <div class="layui-inline" style="text-align:center" >
        <span style="color:red;">*执行成功！*</span>
    </div>
</div>
<div class="layui-form-item">
    <div class="form-group ml30">
        <button class="btn btn-warning" data-method="add" data-type="auto" id="reportButton">
            &nbsp;&nbsp;查看报告&nbsp;&nbsp;
        </button>
    </div>
    <a class="J_menuItem" href="/frmsReport/toTable"
       data-index="6">点击查看报告</a>
</div>
</body>
</html>