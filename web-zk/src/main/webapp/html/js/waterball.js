function drawWaterBall(level,dom,IsdrawCircled){
	var canvas = document.getElementById(dom);
  var ctx = canvas.getContext('2d');
  
 
  //range控件信息
  var rangeValue = 0;
  var nowRange = 0; //用于做一个临时的range
 
  //画布属性
  var mW = canvas.width = 200;
  var mH = canvas.height = 200;
  var lineWidth = 1;
  /*if(IsdrawCircled==true){
  	var mW = canvas.width = 200;
  	var mH = canvas.height = 200;
  }*/
 
  //圆属性
  var r = mH / 2; //圆心
  var cR = r - 33 * lineWidth; //圆半径
  //圆角矩形属性
  var qx=36;
  var qy=51;
  var qwidth=122;
  var qheight=118;
  //Sin 曲线属性
  var sX = 0;
  var sY = mH / 2;
  var axisLength = mW; //轴长
  var waveWidth = 0.033 ; //波浪宽度,数越小越宽 
  var waveHeight = 3; //波浪高度,数越大越高
  var speed = 0.07; //波浪速度，数越大速度越快
  var xOffset = 0; //波浪x偏移量
 
  ctx.lineWidth = lineWidth;
 
  //画圈函数
  var IsdrawCircled = IsdrawCircled;
  var drawCircle = function(){
 
   ctx.beginPath();
   ctx.strokeStyle = '#e9f8fd';
   ctx.arc(r, r, cR, 0, 2 * Math.PI);
   ctx.stroke();
   ctx.beginPath();
   ctx.arc(r, r, cR, 0, 2 * Math.PI);
   ctx.clip();
 
  }
  var drawQua = function(){
   ctx.beginPath();
   ctx.moveTo(qx,qy);
   ctx.lineTo(qx,qy+qheight);
   ctx.lineTo(qx+qwidth,qy+qheight);
   ctx.lineTo(qx+qwidth,qy);
   ctx.quadraticCurveTo(qx+qwidth,qy-24,100,qy-24);
   ctx.quadraticCurveTo(qx,qy-24,qx,qy);
   ctx.strokeStyle = '#e9f8fd';
   ctx.stroke();
   
   ctx.beginPath();
   ctx.moveTo(qx,qy);
   ctx.lineTo(qx,qy+qheight);
   ctx.lineTo(qx+qwidth,qy+qheight);
   ctx.lineTo(qx+qwidth,qy);
   ctx.quadraticCurveTo(qx+qwidth,qy-24,100,qy-24);
   ctx.quadraticCurveTo(qx,qy-24,qx,qy);
   ctx.clip();
  }
  //画圆角矩形
  
 
  //画sin 曲线函数
  var drawSin = function(xOffset){
   ctx.save();
 
   var points=[]; //用于存放绘制Sin曲线的点
 
   ctx.beginPath();
   //在整个轴长上取点
  
   for(var x = sX; x < sX + axisLength; x += 20 / axisLength){
    //此处坐标(x,y)的取点，依靠公式 “振幅高*sin(x*振幅宽 + 振幅偏移量)”
    var y = -Math.sin((sX + x) * waveWidth + xOffset);
if(0<level&&level<=1){
   var dY = mH * (1 - nowRange / 6 );
}else if(1<level&&level<=2){
   var dY = mH * (1 - nowRange / 12 );
}else if(2<level&&level<=3){
   var dY = mH * (1 - nowRange / 18 );
}else if(3<level&&level<=4){
   var dY = mH * (1 - nowRange / 23 );
}else if(4<level&&level<=5){
   var dY = mH * (1 - nowRange / 30 );
}else if(5<level&&level<=6){
   var dY = mH * (1 - nowRange / 35 );
}else if(6<level&&level<=7){
   var dY = mH * (1 - nowRange / 40 );
}else if(7<level&&level<=8){
   var dY = mH * (1 - nowRange / 45 );
}else if(8<level&&level<=10){
   var dY = mH * (1 - nowRange / 48 );
}else if(10<level&&level<=12){
   var dY = mH * (1 - nowRange / 52 );
}else if(12<level&&level<=14){
   var dY = mH * (1 - nowRange / 55 );
}else if(14<level&&level<=16){
   var dY = mH * (1 - nowRange / 60 );
}else if(16<level&&level<=18){
   var dY = mH * (1 - nowRange / 65 );
}else if(18<level&&level<=19){
   var dY = mH * (1 - nowRange / 70 );
}else if(19<level&&level<=20){
   var dY = mH * (1 - nowRange / 75 );
}else if(20<level&&level<=22){
   var dY = mH * (1 - nowRange / 80 );
}else if(22<level&&level<=24){
   var dY = mH * (1 - nowRange / 85 );
}else if(24<level&&level<=26){
   var dY = mH * (1 - nowRange / 90 );
}else if(26<level&&level<=28){
   var dY = mH * (1 - nowRange / 92 );
}else if(28<level&&level<=30){
   var dY = mH * (1 - nowRange / 95 );
}else if(30<level&&level<=40){
   var dY = mH * (1 - nowRange / 95 );
}else if(40<level&&level<=50){
   var dY = mH * (1 - nowRange / 100 );
}else if(50<level&&level<=60){
   var dY = mH * (1 - nowRange / 105 );
}else if(60<level&&level<=70){
   var dY = mH * (1 - nowRange / 110 );
}else if(70<level&&level<=80){
   var dY = mH * (1 - nowRange / 115 );
}else if(80<level&&level<=90){
   var dY = mH * (1 - nowRange / 120 );
}else if(90<level&&level<=100){
   var dY = mH * (1 - nowRange / 120 );
}


       points.push([x, dY + y * waveHeight]);
    ctx.lineTo(x, dY + y * waveHeight);  
   }
 

 
   //封闭路径
   ctx.lineTo(axisLength, mH);
   ctx.lineTo(sX, mH);
   ctx.lineTo(points[0][0],points[0][1]);
   ctx.fillStyle = '#66c7ff';
   ctx.fill();
 
   ctx.restore();
  };
 
  //写百分比文本函数
  var drawText = function(){
   ctx.save();
 
   var size = 0.5*cR;
   ctx.font = size + 'px Microsoft Yahei';
   ctx.textAlign = 'center';
   ctx.fillStyle = "rgba(80, 80, 80, 0.8)";
   ctx.fillText(nowRange, r, r + size*1.5);
   ctx.font = 0.3*cR + 'px Microsoft Yahei';
   ctx.fillStyle = "rgba(80, 80, 80, 0.8)";
   ctx.fillText("%", r+0.5*cR, r + size*1.5);
 
   ctx.restore();
  };
 
  var render = function(){
   ctx.clearRect(0, 0, mW, mH);
 
   rangeValue = level;
 
   if(IsdrawCircled == false){
    drawCircle(); 
   }else{
   	drawQua();
   }
 
   if(nowRange <= rangeValue){
    var tmp = 1;
    nowRange += tmp;
    xOffset += speed;
//  console.log(1);
   }
 /*if(rangeValue-nowRange<1){
 	nowRange +=(rangeValue-nowRange);
 }*/
   if(nowRange > rangeValue){
   	var tmp = 1;
    nowRange -= tmp;
	   	if(speed>0.055){
	   		speed-=0.0001;
	   		xOffset += speed;
	   	}else{
	   		if(waveHeight>0){
	   			waveHeight-=0.08;
	   		}else{
	   			waveHeight=0;
	   			speed=0;
	   		}
	   	}
//  console.log(1);
   }
 
   drawSin(xOffset);
   drawText(); 
   
   requestAnimationFrame(render);
  }
 
  render(level); 
}
