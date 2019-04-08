<%--
  Created by IntelliJ IDEA.
  User: Bottle
  Date: 2019/1/27
  Time: 9:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="layui/css/layui.css" />
    <script type="text/javascript" src="layui/layui.js"></script>
</head>
<body class="layui-layout-body">
<div class="layui-container" style="margin-top: 50px;">

    <table class="layui-hide" id="test" lay-filter="test"></table>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" lay-event="agree">同意</a>
    </script>
    <script>
        layui.use('table', function(){
            var table = layui.table;

            table.render({
                elem: '#test'
                ,url:'borrow.do?method=findNoBack'
                ,title: '借书列表'
                ,cols: [[
                    {field:'id', width:60, title: 'ID', sort: true}
                    ,{field:'book', width:150, title: '图书',templet:function(data){
                            return data.book.name
                        }
                    }
                    ,{field:'reader', width:60, title: '读者',templet:function(data){
                            return data.reader.name
                        }
                    }
                    ,{field:'borrowtime', width:130, title: '借书时间'}
                    ,{field:'returntime',width:130,  title: '还书时间'}
                    ,{field:'state',width:120,  title: '状态',templet:function(data){
                            var result = "";
                            switch (data.state) {
                                case 1:
                                    result = "审核通过";
                                    break;
                            }
                            return result;
                        }
                    }
                    ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:120}
                ]]
                ,page: true
            });

            //监听行工具事件
            table.on('tool(test)', function(obj){
                var data = obj.data;
                if(obj.event === 'agree'){
                    layer.confirm('确定通过？', function(index){
                        window.location.href="admin.do?method=agreeBack&id="+data.id;
                        layer.close(index);
                    });
                }
            });
        });
    </script>

</div>
<script>
    //二级菜单联动
    layui.use('element', function(){
        var element = layui.element;

    });
</script>
</body>
</html>