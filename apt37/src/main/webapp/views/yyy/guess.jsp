<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<head>
<meta name="viewport" content="width=device-width,initial-scale=1.0" />
<title>Hi do·你做我猜</title>
<script type="text/javascript" src="js/zepto.min.js"></script>
<style type="text/css">

	.game_bg {
		height: 100%;
		width: 100%;
		background-size: 30px;
		background-color: teal 
	}
	
	.game-xy{
		background-color: yellowgreen;
		position: fixed;
	}
	
	.game-text{
        overflow:hidden;   
		width: 100%;
	    text-align: center;
		position:absolute;
		font-size: 100px;
		font-weight: bold;
		color: cornsilk;
 		top: 80px;
	}


</style>


</head>
<body>
<body>
	<div class="game_bg ">
		<div class="slider game-text text-cls" >小蛮腰</div>
		<div class="game-xy">
			<div id="arrow"></div>
		</div>
	</div>
	
	
<script type="text/javascript">
	var colors = [ 'rebeccapurple', 'royalblue', 'chocolate', 'goldenrod', 'forestgreen','lightskyblue', 'yellowgreen','peru' ];
	
	
	var titles = [ '小苹果', '大裤衩', '糖葫芦', '金刚葫芦娃', '女汉子','咖啡', '抓耳挠腮','单身狗' ];
	$(function() {
		try {
			var text = "";        
			window.addEventListener("deviceorientation", orientationHandler, false);
		}catch (e) {
			$("#arrow").html(e.message)
		}

	})
		
	//偏转方向
	var deg=40;
	var stop=true;
	function orientationHandler(event) {
			text = ""            
			var arrow = document.getElementById("arrow");
			text += "左右旋转：rotate alpha{" + Math.round(event.alpha) + "deg)<p>";
			text += "前后旋转：rotate beta{" + Math.round(event.beta) + "deg)<p>";
			text += "扭转设备：rotate gamma{" + Math.round(event.gamma) + "deg)<p>";
			text += "设备宽度：rotate gamma{" +(document.body.clientWidth)/7+"px }<p>";
			
// 			arrow.innerHTML = text;
			
			//y轴方向
			var _y= Math.round(event.beta);
			//y轴方向
			var yo= Math.round(event.beta);
			//x轴方向
			var _x= Math.round(event.alpha);
			//x轴方向
			var xo= Math.round(event.alpha);
			
			//判断
			if(stop){
				if(_y>=deg)	{
					
					var text=titles[Math.floor(Math.random() * titles.length)];
					$(".text-cls").html(text);
					$(".game_bg").css("background-color", colors[Math.floor(Math.random() * 8)]);
					$(".game-text").css("font-size",((document.body.clientWidth)/(text.length+1))+"px");
					stop=false;
				}
			}else{
				if(_y>0 && _y<10){
					stop=true;
				}
			}
			
			if(_y<-40){
				stop=true;
				$(".game_bg").css("background-color", colors[Math.floor(Math.random() * 8)]);
				$(".text-cls").html('手机太偏了亲！！');
				$(".game-text").css("font-size",((document.body.clientWidth)/(text.length+1))+"px");
			}
	}
			
</script>
</body>
</html>