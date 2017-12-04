<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include  file="/static/taglibs/header.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 	<link rel="icon" href="/images/logo.png" type="image/x-icon">
	<link rel="shortcut icon" href="/main/images/logo.png" type="image/x-icon">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <title>Hi do</title>
    <link rel="stylesheet" href=/apt37/static/f7/css/framework7.ios.css>
    <link rel="stylesheet" href=/apt37/static/f7/css/my-app.css>
<html>
<body>
	 <div class="views">
      <!-- Your main view, should have "view-main" class-->
      <div class="view view-main">
        <!-- Top Navbar-->
        <div class="navbar">
          <div class="navbar-inner">
            <div class="center sliding">Hi do</div>
            <div class="right ">
           
            </div>
          </div>
        </div>
        <div class="pages navbar-through toolbar-through">
          <div data-page="index" class="page">
	          <!--start login -->
	          <div class="login-screen">
			    <div class="view">
			      <div class="page">
			        <div class="page-content login-screen-content">
			          <div class="login-screen-title">Welcome To Hi do</div>
			          <form>
			            <div class="list-block">
			              <ul>
			                <li class="item-content">
			                  <div class="item-inner">
			                    <div class="item-title label">Username</div>
			                    <div class="item-input">
			                      <input type="text"  name="accname" AutoComplete="off" placeholder="Your username">
			                    </div>
			                  </div>
			                </li>
			                <li class="item-content">
			                  <div class="item-inner">
			                    <div class="item-title label">Password</div>
			                    <div class="item-input">
			                      <input type="password"   name="password" autocomplete="off" placeholder="Your password">
			                    </div>
			                  </div>
			                </li>
			              </ul>
			            </div>
			            <div class="list-block">
			              <ul>
			                <li><a href="#" class="item-link list-button sign-btn">Sign In</a>
			              </ul>
			              <div class="list-block-label">
			                <p>如果你还没有Hido账号，点击<a href="#" class="list-button sign-up">Sign Up</a>注册</p>
			                <p><a href="#" class="close-login-screen"> </a></p>
			              </div>
			            </div>
			          </form>
			        </div>
			      </div>
			    </div>
			  </div>
	          <!--end login -->
	          </div>
	   </div>
	 </div>
<script type="text/javascript" src="${ctx}/static/f7/js/framework7.js"></script>
<script src="${ctx}/static/js/readerfileutil.js"></script>
<script type="text/javascript">
	 // Initialize your app
  	var myApp = new Framework7({modalTitle: 'Hi do',modalButtonOk :"吉道啦"});
  	// Export selectors engine
  	var $$ = Dom7;
  	// Add view
  	var mainView = myApp.addView('.view-main', {
  	    dynamicNavbar: true
  	});
	//login
	myApp.loginScreen();
	
	$$(".login-screen").on("open",function(e){
		$$("input[name='accname']").val("")
	})
	
	loadtime();
	
	//注册页面
	myApp.onPageInit('signup', function (page) {
  		
  		$$(".sign-up-close").on("click",function(){
	  		mainView.router.back("index");
	  		myApp.loginScreen();
	  	})
  		
  	//进入注册页之后执行下面的方法
  		$$(".img-file").on("touchend",function(){
  			$$('#headfile').click();
  		})
  		$$('#headfile').on("change",function(){
  			var d=$$("#headfile").readAsDataURL(handler,$$("#pre_headimg"),"#headimg");
  		})
  		
  		//提交之前
  		$$('#reg-form').on('beforeSubmit', function (e) {
  			$$("#sub-btn").attr("disabled","disabled");
  			var accname=$$("input[name='accname']").val();
  			var password=$$("input[name='password']").val();
  		});
  	
  		//提交表但之后
  		$$('#reg-form').on('submitted', function (e) {
  			var xhr = e.detail.xhr; 
  			var data = e.detail.data;
  			data=eval('('+data+')');
  			if(data.retCd==0){
  				$$("#sub-btn").removeAttr("disabled");
  				$$("#url").val("");
  				setTimeout(function(){
  					myApp.closeModal(); 
  					mainView.router.back("index");
  					window.location.reload();
  				},3000)
  				myApp.alert('恭喜你已经注册成功', function(){
 					mainView.router.back("index");
 					window.location.reload();
  				})
  			}else{
  				$$("#sub-btn").removeAttr("disabled");
  				myApp.alert(data.msg ? data.msg:'注册失败，请稍后重试');
  			}
  		});
  		
	});
	
	
	//定义注册
	$$('.sign-up').on('click', function () {
	  	mainView.router.loadPage('${ctx}/auth/page.do?id=/views/wtb/signup');
	  	myApp.closeModal();
	});
	
	//提交登录
	$$(".sign-btn").on("click",function(){
		var data={accname:$$("input[name='accname']").val(),password:$$("input[name='password']").val()};
		if(data.accname=="" || data.password==""){
			 myApp.alert('请填写登陆账号和密码');
			 return false;
		}
		var redire=$$.parseUrlQuery(location.href);
		$$.ajax({
			url:"${ctx}/auth/login.do",
			data:data,
			type:"POST",
			dataType:"json",
			success:function(data){
				if(data && data.retCd==0){
					myApp.closeModal();
					if(location.href.indexOf("?")>1 && location.href.indexOf("=")>2){
						window.location.reload();
					}else if(data.redire){
						window.location.href=data.redire;
					}
				}else{
					myApp.alert("<div style='text-align:center;'>请填写正确的用户名和密码！</div>");
					
				}
				
			},
			error:function(data){
				console.log(data);
			}
		});  			
 	})
//  		页面统计
 		function loadtime(){
			$$.ajax({
				url:"${ctx}/act/loadtime.do",
				data:{actid:'0'},
				type:"POST",
				dataType:"json",
				success:function(data){
					console.log('成功参与一次');
				},
				error:function(data){
					console.log(data);
				},
				complete:function(xml){
					console.log(xml);
				}
			});  	
		}
 	
	 	 </script>
 	 </body>
 </html>