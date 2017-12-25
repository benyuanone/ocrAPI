var timeData=[];
var levelData=[];
var tempData=[];
var realVData=[];
var weightData=[];
var flowData=[];
var index="";
function drawChart(k,tankId){
	var beginTime=GetDateStr(-1);
	var endTime=GetDateStr(0);
	var dataobj={
		"tankId":tankId,
		"beginTime":beginTime,
		"endTime":endTime
	}
	window.parent.ajax("monitor/detailTankHistoryMonitor", dataobj, function (data) {
		var str="";
		if (data.backCode == 0) {
            // 基于准备好的dom，初始化echarts图表
            // myChart.clear();
            timeData=[];
            levelData=[];
            tempData=[];
            realVData=[];
            weightData=[];
            flowData=[];
             if(data.bean.length>0) {
                 $.each(data.bean, function (k, p) {
                     timeData.push(p.synchroTime);
                     levelData.push(p.level);
                     tempData.push(p.temperature);
                     realVData.push(p.realVolume);
                     weightData.push(p.weight);
                     flowData.push(p.flowSpeed);
                 });
                 var myChart = echarts.init(document.getElementById("placeholder"+k));
                 index=data.bean.length-1;
                var option = {
                    tooltip : {
                        trigger: 'axis'
                    },
                    legend: {
                        show:false,
                        data:[getCookie('level'),getCookie('temperature'),getCookie('realVolume'),getCookie('weight'),getCookie('flowSpeed')]
                    },
                    xAxis : [
                        {
                            type : 'category',
                            boundaryGap : false,
                            data : timeData,
                            splitLine : {
                                show : true,
                            },
                            axisLine:{
                                lineStyle:{
                                    color:'#269dd5',
                                    width:2,//这里是为了突出显示加上的
                                }
                            },
                            axisLabel: {
                                show: true,
                                textStyle: {
                                    color: '#6f7c95',
                                    fontSize:'14'
                                }
                            }
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value',
                            name: '',
							/*position: 'left',*/
                            splitArea : {
                                show : true,
                                areaStyle:{
                                    color: [
                                        'rgba(255,255,255,0.5)',
                                        'rgba(225,225,225,0.2)'
                                    ]
                                }
                            },
                            splitLine : {
                                show : true,
                            },
                            axisLine:{
                                show : true,
                                lineStyle:{
                                    color:'#269dd5',
                                    width:2,//这里是为了突出显示加上的
                                }
                            },
                            axisLabel: {
                                show: true,
                                textStyle: {
                                    color: '#6f7c95',
                                    fontSize:'14'
                                }
                            }
                        },
                        {
                            type : 'value',
                            name: '',
							/*min:'0',
							 max:'50',*/
							/*position: 'right',*/
                            splitArea : {
                                show : true,
                                areaStyle:{
                                    color: [
                                        'rgba(255,255,255,0.5)',
                                        'rgba(225,225,225,0.2)'
                                    ]
                                }
                            },

                            axisLine:{
                                show : true,
                                lineStyle:{
                                    color:'#269dd5',
                                    width:2,//这里是为了突出显示加上的
                                }
                            },
                            axisLabel: {
                                show: true,
                                textStyle: {
                                    color: '#6f7c95',
                                    fontSize:'14'
                                }
                            }
                        }
                    ],
                    series : [
                        {
                            name:getCookie('level'),
                            type:'line',
                            symbol:'emptyCircle',
                            stack: 'group1',
                            itemStyle: {
                                normal: {
                                    color: '#4bd9db',
                                },
                            },
                            yAxisIndex: 1,
                            data:levelData
                        },
                        {
                            name:getCookie('temperature'),
                            type:'line',
                            symbol:'emptyCircle',
                            stack: 'group2',
                            itemStyle: {
                                normal: {
                                    color: '#a0a0a0',
                                },
                            },
                            yAxisIndex: 1,
                            data:tempData
                        },
                        {
                            name:getCookie('realVolume'),
                            type:'line',
                            symbol:'emptyCircle',
                            stack: 'group3',
                            itemStyle: {
                                normal: {
                                    color: '#2f9cfb',
                                },
                            },
                            yAxisIndex: 0,
                            data:realVData
                        },
                        {
                            name:getCookie('weight'),
                            type:'line',
                            symbol:'emptyCircle',
                            stack: 'group4',
                            itemStyle: {
                                normal: {
                                    color: '#fc9e53',
                                },
                            },
                            yAxisIndex: 0,
                            data:weightData
                        },
                        {
                            name:getCookie('flowSpeed'),
                            type:'line',
                            symbol:'emptyCircle',
                            stack: 'group5',
                            itemStyle: {
                                normal: {
                                    color: '#e2616f',
                                },
                            },
                            yAxisIndex: 1,
                            data:flowData
                        }
                    ]
                };

                // 为echarts对象加载数据
                myChart.setOption(option);
                myChart.dispatchAction({
                    type: 'showTip',
                    // 系列的 index，在 tooltip 的 trigger 为 axis 的时候可选。
                    seriesIndex: 1,
                    // 数据的 index，如果不指定也可以通过 name 属性根据名称指定数据
                    dataIndex: index
                    // 可选，数据名称，在有 dataIndex 的时候忽略
                    //name?: string
                });
            }
		}
	});




}