<%--
  Created by IntelliJ IDEA.
  User: Bottle
  Date: 2019/1/28
  Time: 8:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="js/echarts.common.min.js"></script>
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript">
        $(function(){
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('main'));

            $.ajax({
                url:"borrow.do?method=getBarData",
                type:"POST",
                dataType:"JSON",
                success:function(data){
                    // 指定图表的配置项和数据
                    var option = {
                        title: {
                            text: '图书借阅数据'
                        },
                        tooltip: {},
                        legend: {
                            data:['次数']
                        },
                        xAxis: {
                            data: data.names
                        },
                        yAxis: {},
                        series: [{
                            name: '次数',
                            type: 'bar',
                            data: data.counts
                        }]
                    };

                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);
                }
            });


        })
    </script>
</head>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>
</body>
</html>