<%--
  Created by IntelliJ IDEA.
  User: Bottle
  Date: 2019/1/25
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
%>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="layui/css/layui.css"/>
    <script type="text/javascript" src="layui/layui.js"></script>
</head>
<body class="layui-layout-body">

<div class="layui-container" style="margin-top: 50px;">

    <div class="layui-container" style="width: 500px;height: 330px;padding-top: 60px;">
        <form class="layui-form" action="/book.do?method=update" method="post">
            <div class="layui-form-item">
                <label class="layui-form-label">图书编号：</label>
                <div class="layui-inline">
                    <input type="text" value="${requestScope.book1.id}" name="id" lay-verify="required" autocomplete="off" class="layui-input" readonly>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">图书名称：</label>
                <div class="layui-inline">
                    <input type="text" name="name" value="${requestScope.book1.name}" lay-verify="name" autocomplete="off" placeholder="请输入图书名" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">图书作者：</label>
                <div class="layui-inline">
                    <input type="text" name="author" value="${requestScope.book1.author}" lay-verify="author" placeholder="请输入作者名" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">出版社：</label>
                <div class="layui-inline">
                    <input type="text" name="publish" value="${requestScope.book1.publish}" lay-verify="publish" placeholder="请输入出版社" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">总页数：</label>
                <div class="layui-inline">
                    <input type="text" name="pages" value="${requestScope.book1.pages}" lay-verify="pages" placeholder="请输入总页数" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">价格：</label>
                <div class="layui-inline">
                    <input type="text" name="price" value="${requestScope.book1.price}" lay-verify="price" placeholder="请输入价格" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">图书分类：</label>
                <div class="layui-input-inline">
                    <select name="bookCaseId">
                        <c:forEach items="${requestScope.list1}" var="bookCase">
                            <c:if test="${bookCase.id == book1.bookcaseid}">
                                <option value="${bookCase.id}" selected>${bookCase.name}</option>
                            </c:if>
                            <c:if test="${bookCase.id != book1.bookcaseid}">
                                <option value="${bookCase.id}">${bookCase.name}</option>
                            </c:if>
                        </c:forEach>
                    </select>
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
            pages: [/^[0-9]*$/, "请输入正确的页数"],
            price: [/(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/, "请输入正确的价格"]
        });

    });


</script>
</body>
</html>

