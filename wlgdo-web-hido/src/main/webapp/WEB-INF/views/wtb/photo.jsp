<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include  file="/static/taglibs/header.jsp"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<title>在此处插入标题</title>
<link rel="stylesheet" href="css/framework7.ios.min.css">
<!-- Path to your custom app styles-->
<link rel="stylesheet" href="css/my-app.css">

</head>
<body>
	<div class="views">
		<div class="view view-main">
			<div class="page-content">
				<div class="content-block-title">Light Theme</div>
				<div class="content-block row">
					<div class="col-33">
						<a href="#" class="button pb-standalone">Standalone</a>
					</div>
					<div class="col-33">
						<a href="#" class="button pb-popup">Popup</a>
					</div>
					<div class="col-33">
						<a href="#" class="button pb-page">Page</a>
					</div>
				</div>
				<div class="content-block-title">Dark Theme</div>
				<div class="content-block row">
					<div class="col-50">
						<a href="#" class="button pb-standalone-dark">Standalone</a>
					</div>
					<div class="col-50">
						<a href="#" class="button pb-popup-dark">Popup</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="js/framework7.min.js"></script>
<script type="text/javascript">
	var myApp = new Framework7();

	var $$ = Dom7;

	var mainView = myApp.addView('.view-main', {
		dynamicNavbar : true
	});

	/*=== 默认为 standalone ===*/
	var myPhotoBrowserStandalone = myApp.photoBrowser({
		photos : [ 
				'<%=path%>/views/wtb/imgs/1.jpg',
				'<%=path%>/views/wtb/imgs/2.jpg',
				'<%=path%>/views/wtb/imgs/3.jpg',
				'<%=path%>/views/wtb/imgs/4.jpg',
				'<%=path%>/views/wtb/imgs/5.jpg',
				'<%=path%>/views/wtb/imgs/6.jpg'
				]
	});
	//点击时打开图片浏览器
	$$('.pb-standalone').on('click', function() {
		myPhotoBrowserStandalone.open();
	});

	/*=== Popup ===*/
	var myPhotoBrowserPopup = myApp.photoBrowser({
		photos : [ 
			'<%=path%>/views/wtb/imgs/1.jpg',
			'<%=path%>/views/wtb/imgs/2.jpg',
			'<%=path%>/views/wtb/imgs/3.jpg',
			'<%=path%>/views/wtb/imgs/4.jpg',
			'<%=path%>/views/wtb/imgs/5.jpg',
			'<%=path%>/views/wtb/imgs/6.jpg'
			],
		type : 'popup'
	});
	$$('.pb-popup').on('click', function() {
		myPhotoBrowserPopup.open();
	});

	/*=== 作为Page ===*/
	var myPhotoBrowserPage = myApp.photoBrowser({
		photos : [ 
			'<%=path%>/views/wtb/imgs/1.jpg',
			'<%=path%>/views/wtb/imgs/2.jpg',
			'<%=path%>/views/wtb/imgs/3.jpg',
			'<%=path%>/views/wtb/imgs/4.jpg',
			'<%=path%>/views/wtb/imgs/5.jpg',
			'<%=path%>/views/wtb/imgs/4.jpg'
			],		type : 'page',
		backLinkText : 'Back'
	});
	$$('.pb-page').on('click', function() {
		myPhotoBrowserPage.open();
	});

	/*=== Standalone Dark ===*/
	var myPhotoBrowserDark = myApp.photoBrowser({
		photos : [ 
			'<%=path%>/views/wtb/imgs/1.jpg',
			'<%=path%>/views/wtb/imgs/2.jpg',
			'<%=path%>/views/wtb/imgs/3.jpg',
			'<%=path%>/views/wtb/imgs/4.jpg',
			'<%=path%>/views/wtb/imgs/5.jpg',
			'<%=path%>/views/wtb/imgs/4.jpg'
			],		theme : 'dark'
	});
	$$('.pb-standalone-dark').on('click', function() {
		myPhotoBrowserDark.open();
	});

	/*=== Popup Dark ===*/
	var myPhotoBrowserPopupDark = myApp.photoBrowser({
		photos : [ 
			'<%=path%>/views/wtb/imgs/1.jpg',
			'<%=path%>/views/wtb/imgs/2.jpg',
			'<%=path%>/views/wtb/imgs/3.jpg',
			'<%=path%>/views/wtb/imgs/4.jpg',
			'<%=path%>/views/wtb/imgs/5.jpg',
			'<%=path%>/views/wtb/imgs/4.jpg'
			],
		theme : 'dark',
		type : 'popup'
	});
	$$('.pb-popup-dark').on('click', function() {
		myPhotoBrowserPopupDark.open();
	});

	/*=== 有标题 ===*/
	var myPhotoBrowserPopupDark = myApp.photoBrowser({
		photos : [ {
			url : 'http://lorempixel.com/1024/1024/sports/1/',
			caption : 'Caption 1 Text'
		}, {
			url : 'http://lorempixel.com/1024/1024/sports/2/',
			caption : 'Second Caption Text'
		},
		// 这个没有标题
		{
			url : 'http://lorempixel.com/1024/1024/sports/3/',
		}, ],
		theme : 'dark',
		type : 'standalone'
	});
	$$('.pb-standalone-captions').on('click', function() {
		myPhotoBrowserPopupDark.open();
	});

	/*=== 有视频 ===*/
	var myPhotoBrowserPopupDark = myApp
			.photoBrowser({
				photos : [
						{
							html : '<iframe src="//www.youtube.com/embed/lmc21V-zBq0?list=PLpj0FBQgLGEr3mtZ5BTwtmSwF1dkPrPRM" frameborder="0" allowfullscreen></iframe>',
							caption : 'Woodkid - Run Boy Run (Official HD Video)'
						}, {
							url : 'http://lorempixel.com/1024/1024/sports/2/',
							caption : 'Second Caption Text'
						}, {
							url : 'http://lorempixel.com/1024/1024/sports/3/',
						}, ],
				theme : 'dark',
				type : 'standalone'
			});
	$$('.pb-standalone-video').on('click', function() {
		myPhotoBrowserPopupDark.open();
	});
</script>
</html>