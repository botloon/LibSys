<%--
  Created by IntelliJ IDEA.
  User: Bottle
  Date: 2019/1/24
  Time: 11:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>借阅列表</title>
    <link rel="stylesheet" href="layui/css/layui.css"/>
    <script src="layui/layui.js" charset="UTF-8"></script>
</head>
<body>
    <div class="layui-container" style="width:850px;hexight: 600px;margin-top: 0px;padding-top: 60px;">
        <div style="margin-left: 300px; width: 500px;">
            <a href="login.jsp">首页</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="admin.do?method=exportBorrowedByReaderId">导出数据</a>&nbsp;&nbsp;|&nbsp;&nbsp;欢迎回来！${sessionScope.reader.name}<a href="/account.do?method=logout">&nbsp;&nbsp;&nbsp;&nbsp;<button class="layui-btn layui-btn-warm layui-btn-radius">注销</button></a>
        </div>
        <table class="layui-hide" id="test" lay-filter="test"></table>
    </div>

    <%--<script type="text/html" id="barDemo">--%>
        <%--<a class="layui-btn layui-btn-xs" lay-event="borrow">借阅</a>--%>
    <%--</script>--%>
    <%--<script type="text/html" id="barDemo">--%>
        <%--<a class="layui-btn layui-btn-xs" lay-event="return">归还</a>--%>
    <%--</script>--%>




<script>
    layui.use('table', function(){
        var table = layui.table;

        table.render({
            elem: '#test'
            ,url:'/borrow.do?method=findByReaderId'
            ,title: '图书列表'
            ,cols: [[
                {field:'id', width:60, title: '编号'}
                ,{field:'book', width:150, title: '图书',templet:function(data){
                        return data.book.name;
                    }
                }

                ,{field:'reader', width:80, title: '读者',templet:function(data){
                        return data.reader.name;
                    }
                }
                ,{field:'borrowtime', width:150, title: '借阅时间'}
                ,{field:'returntime', width:150, title: '归还时间'}
                ,{field:'adminid', width:100, title: '管理员编号'}
                ,{field:'state',width:100,  title: '状态',templet:function(data){
                    var result = "";
                    switch(data.state){
                        case 0:
                            result ="未审核";
                            break;
                        case 1:
                            result = "审核通过";
                            break;
                        case 2:
                            result = "审核未通过";
                            break;
                        case 3:
                            result = "已归还";
                            break;
                    }
                    return result;
                    }
                }

            ]]
            ,page: true
        });

        // //监听行工具事件
        // table.on('tool(test)', function(obj){
        //     var data = obj.data;
        //     if(obj.event === 'borrow'){
        //         window.location.href="borrow.do?bookid="+data.id;
        //     }
        // });

    });
</script>
</body>
</html>
