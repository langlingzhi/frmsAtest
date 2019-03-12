<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
    <title>项目管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    <!-- Bootstrap -->
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/plugins/layui/css/layui.css" rel="stylesheet" media="all">
    <script src="/js/jquery.js"></script>
    <script src="/plugins/layui/layui.js" charset="utf-8"></script>
    <script src="/js/bootstrap-datetimepicker.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/jquery.animateNumbers.js"></script>
    <script src="/js/jquery.sidr.min.js"></script>
    <script src="/js/breakpoints.js"></script>
    <script src="/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
    <script src="/plugins/bootstrap-select2/select2.min.js" type="text/javascript"></script>
    <!-- END PAGE LEVEL PLUGINS -->
    <script src="/js/core.js"></script>
    <link href="/plugins/webarch/css/style.css?v=4.0.0" rel="stylesheet">
</head>
<script>
    layui.use(['table','form','layer'], function() {
        var table = layui.table,
                layer = layui.layer,
                form = layui.form;
        //页面初始化数据
        $('#runButton').on('click', function() {
            $.ajax({
                url: '${base}/frmsRun/frmsRun',
                async: 'true',
                type: 'post',
                contentType: 'application/json',
                data: JSON.stringify(),
                dataType: 'text',
            });
        });
    })
</script>
<body class="gray-bg">
<div class="col-sm-12" style="padding-bottom: 15px;">
    <div class="col-sm-offset-5 col-sm-2">
        <button class="btn btn-warning" data-method="run" data-type="auto" id="runButton">&nbsp;&nbsp;批量运行&nbsp;&nbsp;</button>
    </div>
</div>
</body>
</html>

