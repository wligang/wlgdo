queryActors(0);
var nametxt = $('.slot');
var phonetxt = $('.name');
var pcount = actors.length-1;//参加人数
var runing = true;
var trigger = true;
var inUser = (Math.floor(Math.random() * 10000)) % 5 + 1;
var num = 0;
var Lotterynumber = 5; //设置单次抽奖人数

$(function () {
	nametxt.css('background-image','url('+actors[0].headImg+')');
	phonetxt.html(actors[0].name);
});

// 开始停止
function start() {

	if (runing) {

		if ( pcount <= Lotterynumber ) {
			alert("抽奖人数不足5人");
		}else{
			runing = false;
			$('#start').text('停止');
			startNum()
		}

	} else {
		$('#start').text('自动抽取中('+ Lotterynumber+')');
		zd();
	}
	
}

// 开始抽奖

function startLuck() {
	runing = false;
	$('#btntxt').removeClass('start').addClass('stop');
	startNum()
}

// 循环参加名单
function startNum() {
	num = Math.floor(Math.random() * pcount);
	nametxt.css('background-image','url('+actors[num].headImg+')');
	phonetxt.html(actors[num].name ? actors[num].name:actors[num].nickName);
	t = setTimeout(startNum, 0);
}

// 停止跳动
function stop() {
	pcount = actors.length-1;
	clearInterval(t);
	t = 0;
}

// 打印中奖人

function zd() {
	if (trigger) {

		trigger = false;
		var i = 0;

		if ( pcount >= Lotterynumber ) {
			stopTime = window.setInterval(function () {
				if (runing) {
					runing = false;
					$('#btntxt').removeClass('start').addClass('stop');
					startNum();
				} else {
					runing = true;
					$('#btntxt').removeClass('stop').addClass('start');
					stop();

					i++;
					Lotterynumber--;

					$('#start').text('自动抽取中('+ Lotterynumber+')');

					if ( i == 5 ) {
						console.log("抽奖结束");
						window.clearInterval(stopTime);
						$('#start').text("开始");
						Lotterynumber = 5;
						trigger = true;
					};

					//打印中奖者名单
					var showName=actors[num].name ? actors[num].name : actors[num].nickName;
					$('.luck-user-list').prepend("<li><div class='portrait' style='background-image:url("+actors[num].headImg+")'></div><div class='luckuserName'>"+showName+"</div></li>");
					$('.modality-list ul').append("<li><div class='luck-img' style='background-image:url("+actors[num].headImg+")'></div><p>"+actors[num].headImg+"</p></li>");
					//将已中奖者从数组中"删除",防止二次中奖
					actors.splice($.inArray(actors[num], actors), 1);
				}
			},1000);
		};
	}
}

