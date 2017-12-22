<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ include  file="/static/taglibs/header.jsp"%>
<html>
  <head>
    <meta charset="utf-8">
    <link rel="icon" href="/images/logo.png" type="image/x-icon">
	<link rel="shortcut icon" href="/main/images/logo.png" type="image/x-icon">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <title>Hi do</title>
    <!-- Path to Framework7 Library CSS-->
    <link rel="stylesheet" href=/apt37/static/f7/css/framework7.ios.css>
    <link rel="stylesheet" href=/apt37/static/f7/css/my-app.css>
    <!-- Path to your custom app styles-->
 	<style type="text/css">
 		.panel{
 			background: #e2e1ec;
 		}
 	</style>
  </head>
  <body>
    <!-- Status bar overlay for fullscreen mode-->
    <div class="statusbar-overlay"></div>
    <!-- Panels overlay-->
    <div class="panel-overlay"></div>
    <!-- Left panel with reveal effect-->
    <div class="panel panel-left panel-reveal">
      <div class="content-block">
        <p>个人中心</p>
      </div>
    </div>
    <!-- Right panel with cover effect-->
    <div class="panel panel-right panel-cover">
      <div class="content-block">
        <p>活动中心</p>
      </div>
    </div>
    <!-- Views-->
    <div class="views">
      <div class="view view-main">
        <div class="navbar">
          <div class="navbar-inner">
          	<div class="left"><a href="javascript:loadPage('');" target="_Blank">Hi do</a></div>
            <div class="center sliding">${user.accname}</div>
            <div class="right">
              <span style='float:right'>
	          </span>
              <a href="#" class="link icon-only open-links"> <i class="icon icon-bars"></i></a>
            </div>
          </div>
        </div>
        <!-- 页面组件 -->
        <div class="pages navbar-through toolbar-through">
          <div data-page="index" class="page">
            <div class="page-content">
              <div class="content-block-title">欢迎来到 Hi do，让我们一起见证改变
              </div>
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
			                      <input type="text" name="accname" placeholder="Your username">
			                    </div>
			                  </div>
			                </li>
			                <li class="item-content">
			                  <div class="item-inner">
			                    <div class="item-title label">Password</div>
			                    <div class="item-input">
			                      <input type="password" name="password" placeholder="Your password">
			                    </div>
			                  </div>
			                </li>
			              </ul>
			            </div>
			            <div class="list-block">
			              <ul>
			                <li><a href="#" class="item-link list-button sign-btn">Sign In</a></li>
			              </ul>
			              <div class="list-block-label">
			                <p>如果你还没有Hido账号，点击<a href="#" class="list-button sign-up">Sign Up</a>注册</p>
			              </div>
			            </div>
			          </form>
			        </div>
			      </div>
			    </div>
			  </div>
	          <!--end login -->
	          
              <div class="content-block">
                <div class="content-block-inner">
                  <p>Hi do 是什么？</p>
                  <p>我们一直在被选择，一直在被改变，Hi do 给了你一个让你可以主动改变的机会！</p>
                  <p>我们的价值观是:崇高的理想、高度的信任、不断的坚持与与时俱进的创新思维。</p>
                  <p>加入我们，你的人生将发生意想不到的改变！</p>
                </div>
              </div>
              
              <div class="content-block-title">Hi do 应用</div>
              <div class="list-block">
                <ul>
                <%--   <li><a href="${ctx}/mob/forward?id=about" class="item-link">
                      <div class="item-content">
                        <div class="item-inner"> 
                          <div class="item-title"></div>
                        </div>
                      </div></a></li> --%>
     <%--              <li><a href="${ctx}/mob/forward?id=services" class="item-link">
                      <div class="item-content"> 
                        <div class="item-inner">
                          <div class="item-title">应用中心</div>
                        </div>
                      </div></a></li>
                  --%>
                  <li> 
                  <a href="#" onclick="loadPage('${ctx}/mob/forward?id=culture');" class="item-link"  data-ignore-cache="true" >
<%--                   <a href="${ctx}/mob/forward?id=culture" class="item-link"  data-ignore-cache="true" > --%>
                      <div class="item-content"> 
                        <div class="item-inner">
                          <div class="item-title">个人中心</div>
                        </div>
                      </div></a></li>
                  <li> 
                  <a href="#" onclick="loadPage('${ctx}/act/words');" class="item-link"  data-ignore-cache="true" >
<%--                   <a href="${ctx}/mob/forward?id=culture" class="item-link"  data-ignore-cache="true" > --%>
                      <div class="item-content"> 
                        <div class="item-inner">
                          <div class="item-title">你说我猜</div>
                        </div>
                      </div></a></li>
                </ul>
              </div>
              
              <!-- <div class="content-block-title">服务</div>
              <div class="content-block">
                <div class="row">
                  <div class="col-50"><a href="#" data-panel="left" class="button open-panel">个人中心</a></div>
                  <div class="col-50"><a href="#" data-panel="right" class="button open-panel" id='act-btn'>活动中心</a></div>
                </div>
              </div> -->
              
            </div>
          </div>
        </div>

      </div>
    </div>
    <!-- links -->
    <div class="popover popover-links">
        <div class="popover-inner">
          <div class="list-block">
            <ul>
            	<c:if test="${login eq true}">
  					<li><a href="#" class="list-button item-link login-out">Sign Out</a></li>
  				</c:if>
  				<c:if test="${login eq false}">
					<li><a href="#" class="list-button item-link open-login-screen" >Sign In</a></li>
				</c:if>
				<li><a href="#" class="list-button item-link sign-up">Sign Up</a></li>
            </ul>
          </div>
        </div>
      </div>
    <!-- links -->
    
    <script type="text/javascript" src="${ctx}/static/f7/js/framework7.min.js"></script>
  	<script type="text/javascript">
 // Initialize your app
  	var myApp = new Framework7({modalTitle: 'Hi do',modalButtonOk :"好得"});
  	// Export selectors engine
  	var $$ = Dom7;
  	// Add view
  	var mainView = myApp.addView('.view-main', {
  	    dynamicNavbar: true
  	});
	//links
  	$$('.open-links').on('click', function () {
  	  var clickedLink = this;
	  myApp.popover('.popover-links', clickedLink);
  	});
	
  	//定义注册
	$$('.sign-up').on('click', function () {
	  	mainView.router.loadPage('${ctx}/auth/page?id=/views/wtb/signup');
	  	myApp.closeModal();
	});
	
	loadtime();
  	
	//login
	$$(".login-screen").on("opened",function(e){
		$$(".login-out").hide();
		myApp.closeModal(".popover-links");
		$$(".sign-btn").once("click",function(){
			var data={accname:$$("input[name='accname']").val(),password:$$("input[name='password']").val()};
			if(data.accname=="" || data.password==""){
				 myApp.alert('请填写登陆账号和密码');
				 return false;
			}
  			$$.ajax({
				url:"${ctx}/auth/login",
				data:data,
				type:"POST",
				dataType:"json",
				success:function(data){
					if(data && data.retCd==0){
						myApp.closeModal();
						$$(".login-out").show();
						mainView.router.loadPage("index");
						window.location.reload();
					}else{
						myApp.alert("登陆失败，请输入正确的用户名和密码");
					}
				},
				error:function(data){
					console.log(data);
				}
			});  			
  		})
	})
	
		$$(".login-out").on("click",function(){
  			$$.ajax({
				url:"${ctx}/auth/exit",
				data:{},
				type:"POST",
				dataType:"json",
				success:function(data){
				 console.log(data);
					if(data.retCd==0){
						myApp.closeModal();
						myApp.loginScreen();
						mainView.router.loadPage('${ctx}/auth/page?id=/login');
					} 
				},
				error:function(data){
					console.log(data);
				}
			});  			
		})
	
  	myApp.onPageInit('about', function (page) {
  	    // run createContentPage func after link was clicked
  		$$('.create-page').on('click', function () {
  	        createContentPage();
  	    });
  	});
  	
	//注册页面
  	
  	myApp.onPageInit('signup', function (page) {
  		
  		$$(".sign-up-close").on("click",function(){
	  		mainView.router.back("index")
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
  			var xhr = e.detail.xhr; // actual XHR object
  			var data = e.detail.data; // Ajax repsonse from action file
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
  				myApp.alert(data.msg ? data.msg:'注册失败，请稍后重试');
  				$$("#sub-btn").removeAttr("disabled");
  			}
  		});
  	});
	//重新转发页面
  	function loadPage(page){
		if(page==""){
			page=("http://www.wlgdo.com");
		}
	  	window.location.href=page;
		
  	}
  	
  	// Generate dynamic page
  	var dynamicPageIndex = 0;
  	function createContentPage() {
  		mainView.router.loadContent(
  	        '<!-- Top Navbar-->' +
  	        '<div class="navbar">' +
  	        '  <div class="navbar-inner">' +
  	        '    <div class="left"><a href="#" class="back link"><i class="icon icon-back"></i><span>Back</span></a></div>' +
  	        '    <div class="center sliding">Dynamic Page ' + (++dynamicPageIndex) + '</div>' +
  	        '  </div>' +
  	        '</div>' +
  	        '<div class="pages">' +
  	        '  <!-- Page, data-page contains page name-->' +
  	        '  <div data-page="dynamic-pages" class="page">' +
  	        '    <!-- Scrollable page content-->' +
  	        '    <div class="page-content">' +
  	        '      <div class="content-block">' +
  	        '        <div class="content-block-inner">' +
  	        '          <p>Here is a dynamic page created on ' + new Date() + ' !</p>' +
  	        '          <p>Go <a href="#" class="back">back</a> or go to <a href="services.html">Services</a>.</p>' +
  	        '        </div>' +
  	        '      </div>' +
  	        '    </div>' +
  	        '  </div>' +
  	        '</div>'
  	    );
  		return;
  	}
  	
// 	点击次数
	function loadtime(){
		$$.ajax({
			url:"${ctx}/act/loadtime",
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
 	 <script src="${ctx}/static/js/jquery-3.1.1.min.js"></script>
 	 <script src="${ctx}/static/js/readerfileutil.js"></script>
  </body>
</html>