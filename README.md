# 一个java实现的简单接口测试平台（Atest）
技术栈：springboot+restassured
## 功能介绍：
- 支持在界面上进行http/https接口的增删改查和调试功能
- 支持在界面上进行测试case的增删改查，简单断言，和调试功能
- 支持新建测试集，且批量运行case，已经查看接口测试报告；
- 支持定时执行，和运行完发邮件到指定邮箱；
## 环境
准备idea+maven+git+jdk（1.8）的java开发环境，并且在idea中配置好git和maven
##操作
springboot支持以Application的方式启动（不同的模块有不同的application文件）,
在idea中分别添加EurekaApplication，ConfigApplication，TestApplication，然后依次启动就能访问我们的接口测试平台了；
## 访问和使用
TestApplication启动无误后，直接访问http://localhost:21300/     就能访问该平台了
如需使用自己的数据库，请先在frms-config项目中填写数据库地址，且新建数据库和表；
##### *frms-test为主要业务代码模块，frms-eureka和frms-config为springboot的注册中心和配置中心（无业务代码）
## 待优化
- 支持复杂接口的请求（全局cookie，sign等参数的添加）
- 支持jsonshcema校验
- 支持验证数据库
- 支持在线加解密
- 批量导入接口/测试用例
- 待加+++++
##
以上~
详细讲解请见我的同名[简书](https://www.jianshu.com/u/e02af7dce116)
