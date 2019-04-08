<%--
  Created by IntelliJ IDEA.
  User: Bottle
  Date: 2019/1/25
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="layui/css/layui.css"/>
    <script type="text/javascript" src="layui/layui.js"></script>
</head>
<body class="layui-layout-body">
<div class="layui-container" style="margin-top: 50px;">

    <table class="layui-hide" id="test" lay-filter="test"></table>
    <script type="text/html" id="barDemo">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
    <script>
        layui.use('table', function(){
            var table = layui.table;

            table.render({
                elem: '#test'
                ,url:'/book.do?method=findAll'
                ,title: '图书列表'
                ,cols: [[
                    {field:'id', width:50, title: 'ID', sort: true}
                    ,{field:'name', width:110, title: '图书名称'}
                    ,{field:'author', width:100, title: '作者'}
                    ,{field:'publish', width:130, title: '出版社'}
                    ,{field:'pages',width:70,  title: '页数', sort: true}
                    ,{field:'price',width:70,  title: '价格', sort: true}
                    ,{field:'bookCase',width:80,  title: '分类',templet:function(data){
                            return data.bookCase.name
                        }
                    }
                    ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:120}
                ]]
                ,page: true
            });

            //监听行工具事件
            table.on('tool(test)', function(obj){
                var data = obj.data;
                if(obj.event === 'detail'){
                    layer.msg('ID：'+ data.id + ' 的查看操作');
                } else if(obj.event === 'del'){
                    layer.confirm('真的删除行么', function(index){
                        window.location.href="book.do?method=deleteById&id="+data.id;
                        layer.close(index);
                    });
                } else if(obj.event === 'edit'){
                    window.location.href="book.do?method=findById&id="+data.id;
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
