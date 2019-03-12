<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8" />
    <meta charset="utf-8" />
    <title>风控催收平台</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta content="" name="description" />
    <meta content="" name="author" />
    <!-- Bootstrap -->
    <link href="/css/editor-awesome.min.css" rel="stylesheet">
    <link href="/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="/css/jquery.sidr.light.css" rel="stylesheet" type="text/css">
    <link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/css/style.css" rel="stylesheet" type="text/css">
    <link href="/css/pages.css" rel="stylesheet" type="text/css">
    <link href="/css/responsive.css" rel="stylesheet" type="text/css">
</head>

<body>
<#include "header.ftl">
<div class="page-container row condensed">
<#include "left.ftl">
    <div class="page-content">
        <div class="content">
            <div id="container">
                <div class="row spacing-bottom">
                    <div class="col-md-4 col-sm-6">
                        <div class="tiles blue added-margin">
                            <div class="tiles-body">
                                <p class="pt10 pb30"><span class="animate-number f30 fr" data-value="26" data-animation-duration="1200">0</span></p>
                                <p align="right" class="f16">待处理工单</p>
                            </div>
                            <div class="showmore f14"><a href=""><span class="fr glyphicon glyphicon-arrow-right mt6"></span>查看全部</a></div>
                        </div>
                    </div>
                    <div class="col-md-4 col-sm-6">
                        <div class="tiles red added-margin">
                            <div class="tiles-body">
                                <p class="f16 pt10 pb30"><span class="animate-number f30 fr" data-value="36" data-animation-duration="1200">0</span></p>
                                <p align="right" class="f16">进行中的项目</p>
                            </div>
                            <div class="showmore f14"><a href=""><span class="fr glyphicon glyphicon-arrow-right mt6"></span>查看全部</a></div>
                        </div>
                    </div>
                    <div class="col-md-4 col-sm-6">
                        <div class="tiles green added-margin">
                            <div class="tiles-body">
                                <p class="f16 pt10 pb30"><span class="animate-number f30 fr" data-value="16" data-animation-duration="1200">0</span></p>
                                <p align="right" class="f16">待结清款项(元)</p>
                            </div>
                            <div class="showmore f14"><a href=""><span class="fr glyphicon glyphicon-arrow-right mt6"></span>查看全部</a></div>
                        </div>
                    </div>
                </div>
                <div class="row f14">
                    <div class="col-md-9">
                        <div class="bg-f">
                            <div class="py10 px20 border-bottom-gray"><a href="" class="fr">查看更多 &gt;</a><span class="f18">待处理工单</span> <span class="badge badge-red animated bounceIn">1</span></div>
                            <div class="pl20 pb20 table-responsive">
                                <table width="100%" border="0" cellspacing="0" cellpadding="0" class="table table-hover c-6">
                                    <thead>
                                    <tr>
                                        <th width="7%">优先级</th>
                                        <th width="21%">主题</th>
                                        <th width="35%">所属项目</th>
                                        <th width="11%">负责人</th>
                                        <th width="12%">当前状态</th>
                                        <th width="14%">更新时间</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td><span class="label label-danger">严重</span></td>
                                        <td><a href="">查看讲师页面</a></td>
                                        <td>吉的堡后台站群管理制作</td>
                                        <td>戚总</td>
                                        <td><span class="text-danger">待处理</span></td>
                                        <td>01-18 16:20</td>
                                    </tr>
                                    <tr>
                                        <td><span class="label label-warning">紧急</span></td>
                                        <td><a href="">查看讲师页面</a></td>
                                        <td>吉的堡后台站群管理制作</td>
                                        <td>戚总</td>
                                        <td><span class="text-danger">待处理</span></td>
                                        <td>01-18 16:20</td>
                                    </tr>
                                    <tr>
                                        <td><span class="label label-success">普通</span></td>
                                        <td><a href="">查看讲师页面</a></td>
                                        <td>吉的堡后台站群管理制作</td>
                                        <td>戚总</td>
                                        <td><span class="text-danger">待处理</span></td>
                                        <td>01-18 16:20</td>
                                    </tr>
                                    <tr>
                                        <td><span class="label label-primary">不急</span></td>
                                        <td><a href="">查看讲师页面</a></td>
                                        <td>吉的堡后台站群管理制作</td>
                                        <td>戚总</td>
                                        <td><span class="text-danger">待处理</span></td>
                                        <td>01-18 16:20</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div><a href=""><img src="images/indexbanner1.png" width="100%"></a></div>
                        <div class="mt10"><a href=""><img src="images/indexbanner2.png" width="100%"></a></div>
                        <div class="mt10"><a href=""><img src="images/indexbanner3.png" width="100%"></a></div>
                        <div class="mt10"><a href=""><img src="images/indexbanner4.png" width="100%"></a></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="/js/jquery.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/jquery.animateNumbers.js"></script>
<script src="/js/jquery.sidr.min.js"></script>
<script src="/js/breakpoints.js"></script>
<script src="/js/core.js"></script>
</body>
</html>
