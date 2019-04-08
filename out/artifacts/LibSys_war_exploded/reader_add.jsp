<%--
  Created by IntelliJ IDEA.
  User: Bottle
  Date: 2019/1/27
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="layui/css/layui.css"/>
    <script type="text/javascript" src="layui/layui.js"></script>
</head>
<body class="layui-layout-body">

<div class="layui-container" style="margin-top: 50px;">

    <div class="layui-container" style="width: 500px;height: 330px;padding-top: 60px;">
        <form class="layui-form" action="/reader.do?method=save" method="post">
            <div class="layui-form-item">
                <label class="layui-form-label">用户名：</label>
                <div class="layui-inline">
                    <input type="text" name="username"  lay-verify="username" autocomplete="off" placeholder="请输入用户名" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">密码：</label>
                <div class="layui-inline">
                    <input type="text" name="password" lay-verify="password" placeholder="请输入密码" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">姓名：</label>
                <div class="layui-inline">
                    <input type="text" name="name" lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">电话：</label>
                <div class="layui-inline">
                    <input type="text" name="tel" lay-verify="tel" placeholder="请输入电话" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">卡号：</label>
                <div class="layui-inline">
                    <input type="text" name="cardid" lay-verify="required" placeholder="请输入卡号" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">性别：</label>
                <div class="layui-inline">
                    <input type="radio" name="gender" value="男" autocomplete="off" checked>男
                    <input type="radio" name="gender" value="女" autocomplete="off">女
                </div>
            </div>
            <div class="layui-form-item">
                <button class="layui-btn" lay-submit="" lay-filter="demo2" style="margin-left: 160px;">提交</button>
            </div>
        </form>
    </div>
</div>
<script>
    layui.use(['form', 'element'], function(){
        var form = layui.form;
        var element = layui.element;

        //自定义验证规则
        form.verify({
            tel: [/^(13|15|18)\d{9}$/, "请输入正确的电话号码"],
            username: [/^\w+$/, "请输入正确的用户名"],
            password: [/^\w+$/, "请输入正确的密码"]
        });

    });


</script>
</body>
</html>
