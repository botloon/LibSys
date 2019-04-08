<%--
  Created by IntelliJ IDEA.
  User: Bottle
  Date: 2019/1/23
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#next").click(function(){
                var currentpage = $("#currentpage").text();
                var pages = $("#pages").text();
                if(currentpage == pages){
                    return false;
                }
                var nextpage = parseInt(currentpage)+1;
                window.location.href="book.do?page="+nextpage;
            });
            $("#previous").click(function(){
                var currentpage = $("#currentpage").text();
                if(currentpage == 1){
                    return false;
                }
                var nextpage = parseInt(currentpage)-1;
                window.location.href="book.do?page="+nextpage;
            });
            $("#first").click(function(){
                window.location.href="book.do?page=1";
            });
            $("#last").click(function(){
                var pages = $("#pages").text();
                window.location.href="book.do?page="+parseInt(pages);
            });
        });

    </script>
</head>
<body>
    <table>
        <tr>
            <th>图书编号</th>
            <th>图书名称</th>
            <th>作者</th>
            <th>出版社</th>
            <th>总页数</th>
            <th>价格</th>
        </tr>
        <c:forEach items="${requestScope.list}" var="book">
            <tr>
                <th>${book.id}</th>
                <th>${book.name}</th>
                <th>${book.author}</th>
                <th>${book.publish}</th>
                <th>${book.pages}</th>
                <th>${book.price}</th>
            </tr>
        </c:forEach>
    </table>
    <a href="javascript:void(0)" id="first">首页</a>
    <a href="javascript:void(0)" id="previous">上一页</a>
    <span id="currentpage">${requestScope.currentpage}</span>/<span id="pages">${requestScope.pages}</span>
    <a href="javascript:void(0)" id="next">下一页</a>
    <a href="javascript:void(0)" id="last">尾页</a>
</body>
</html>
