<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<head>
 <meta charset="utf-8">
    <link rel="icon" href="/images/logo.png" type="image/x-icon">
	<link rel="shortcut icon" href="/main/images/logo.png" type="image/x-icon">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
<title>动态主页</title>
<link rel="stylesheet" href=${ctx}/static/f7/css/framework7.ios.css>
<link rel="stylesheet" href=${ctx}/static/f7/css/my-app.css>
<style>
.demo-card-header-pic .card-header {
	height: 40vw;
	background-size: cover;
	background-position: center;
}
</style>
</head>
<style>
.facebook-card .card-header {
	display: block;
	padding: 10px;
}

.facebook-card .facebook-avatar {
	float: left;
}

.facebook-card .facebook-name {
	margin-left: 44px;
	font-size: 14px;
	font-weight: 500;
}

.facebook-card .facebook-date {
	margin-left: 44px;
	font-size: 13px;
	color: #8e8e93;
}

.facebook-card .card-footer {
	background: #fafafa;
}

.facebook-card .card-footer a {
	color: #81848b;
	font-weight: 500;
}

.facebook-card .card-content img {
	display: block;
}

.facebook-card .card-content-inner {
	padding: 15px 10px;
}

.a-upload {
    padding: 4px 10px;
    height: 20px;
    line-height: 20px;
    position: relative;
    cursor: pointer;
    color: #888;
    background: #fafafa;
    border: 1px solid #ddd;
    border-radius: 4px;
    overflow: hidden;
    display: inline-block;
    *display: inline;
    *zoom: 1
}

.a-upload  input {
    position: absolute;
    font-size: 100px;
    right: 0;
    top: 0;
    opacity: 0;
    filter: alpha(opacity=0);
    cursor: pointer
}

.a-upload:hover {
    color: #444;
    background: #eee;
    border-color: #ccc;
    text-decoration: none
}



/* .comments {   */
/* 	width:100%;/*自动适应父布局宽度*/   */
/* 	overflow:auto;   */
/* 	word-break:break-all;  */
/* 	width: 98%; */
/* 	max-width: 98%; */
/*  } */
 
 
</style>
<div class="views">
	<div class="view view-main" data-page="home">
		<div class="pages">
			<div data-page="home" class="page navbar-fixed">
				<div class="navbar">
					<div class="navbar-inner">
						<div class="left">
							<a href="#" class="back link page-index"> <i class="icon icon-back"></i><span>back</span></a>
						</div>
						<div class="center" style="left: 0px;">${user.accname}·动态</div>
						<div class="right">
							<span style='float: right'> </span> 
							<a href="#" class="link icon-only open-links"> <i class="icon icon-bars"></i></a>
						</div>
					</div>
				</div>
				<div class="page-content">
					<div class="content-block-title">${user.accname ne '' ? user.accname:'我的'}动态</div>
					<div class="card">
						<div class="card-content">

							<c:forEach items="${datalist}" var="item">
								<!-- 判断类型 -->
								<c:choose >
									<c:when test="${item.type eq 0 }">
										<div class="card demo-card-header-pic">
											<div class="facebook-card">
												<div class="facebook-avatar">
<%-- 													<img src="${user.url}" width="34" height="34"> --%>
												</div>
												<div class="facebook-name">${userMp[item.uid]}</div>
												<div class="facebook-date">${item.cTime}</div>
											</div>
	                						<c:if test="${item.imgurl ne ''}">
		                						<c:forEach var="imgs" items="${fn:split(item.imgurl,'###')}">
													<div class="card-content">
<%-- 				               		 					<div  style="background-image:url(${imgs});height:auto;" valign="bottom" class="card-header color-white no-border">hi do</div> --%>
													<img alt="" src="${imgs}" width="100%">
													</div>
			                    				</c:forEach>
	                						</c:if>
	                						<div class="card-content">
	                		 			 	<div class="card-content-inner">
                 	   							<p>${item.context}</p>
	                 	 					</div>
	               							</div>
	               		 					<div class="card-footer" >
	               		 						<a href="#" class="link link-zan" data-id="${item.id}"><span class="zan-text">${fn:contains(item.likeList, user.uid) ?'已赞':'赞'}</span><span class="zan-num">+${item.zanNum}</span></a>  
	               		 						<a href="#" class="link link-comment" data-id="${item.id}">评论<span class="comments-num-${item.id}">${fn:length(item.commenList)}</span></a>
	               		 					  	<a href="#" class="link link-share" data-id="${item.id}">分享</a>
	               		 					 </div>
	               		 					<div class="card-footer commnent-foot-${item.id}" style="display:${fn:length(item.commenList)>0 ?'block':'none'}">
	               		 					  	<span class="comment-list" id="comment-id_${item.id}">
	               		 					  		<c:set value="${item.commenList}" var="c"/>
													<c:forEach items="${c}" var="it" begin="0"  end="${fn:length(c)}" varStatus="stat">
													 <div>
			               		 					  	<a href="#" data-id="">${userMp[it.uid]}:</a>
			               		 					  	${it.context}
			               		 					  	</div>
													</c:forEach>
	               		 					  	</span>
	               		 					 </div>
	               		 					  	
	               		 					 <div class="card-footer ">
										        <div style="width: 90%;">
								              		<textarea  class="comments comment-msg-${item.id}"  
								              		style="height:expression((this.scrollHeight>50)?'50px':(this.scrollHeight+5)+'px');
								              		overflow:auto; width: 98%; max-width: 98%" placeholder="评论"></textarea>
												</div>
										        <div style="width: 12%">
										        	<button href="#" class="button comment-btn" data-id="${item.id}">发送</button>
										        </div>
	               		 					 </div>
             		 					</div>
									</c:when>			
									<c:otherwise >
										<div class="card facebook-card">
											<div class="card-header no-border">
												<div class="facebook-avatar">
<%-- 													<img src="${user.url}" width="34" height="34"> --%>
												</div>
												<div class="facebook-name">${userMp[item.uid]}</div>
												<div class="facebook-date">${item.cTime}</div>
											</div>
											 <c:if test="${item.imgurl  != ''}">
												<c:forEach var="imgs" items="${fn:split(item.imgurl,'###')}">
													<div class="card-content-inner">
													<p>${item.context}</p>
													</div>
			                    				</c:forEach>
			                    			</c:if>
             							<div class="card-footer ">
											<a href="#" class="link link-zan" data-id="${item.id}"><span class="zan-text">${fn:contains(item.likeList, user.uid) ?'已赞':'赞'}</span><span class="zan-num">+${item.zanNum}</span></a>  
	               		 						<a href="#" class="link link-comment" data-id="${item.id}">评论<span class="comments-num-${item.id}">${fn:length(item.commenList)}</span></a>
	               		 					  	<a href="#" class="link link-share" data-id="${item.id}">分享</a>
										</div>
										
										<div class="card-footer commnent-foot-${item.id}" style="display:${fn:length(item.commenList)>0 ?'block':'none'}">
	               		 					  	<span class="comment-list" id="comment-id_${item.id}">
	               		 					  		<c:set value="${item.commenList}" var="c"/>
													<c:forEach items="${c}" var="it" begin="0"  end="${fn:length(c)}" varStatus="stat">
													 <div>
			               		 					  	<a href="#" data-id="">${userMp[it.uid]}:</a>
			               		 					  	${it.context}
			               		 					  	</div>
													</c:forEach>
	               		 					  	</span>
	               		 					 </div>
	               		 					  	
	               		 					 <div class="card-footer ">
										        <div style="width: 90%;">
								              		<textarea  class="comments comment-msg-${item.id}"  
								              		style="height:expression((this.scrollHeight>50)?'50px':(this.scrollHeight+5)+'px');
								              		overflow:auto; width: 98%; max-width: 98%" placeholder="评论"></textarea>
												</div>
										        <div style="width: 12%">
										        	<button href="#" class="button comment-btn" data-id="${item.id}">发送</button>
										        </div>
	               		 					 </div>
										</div>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</div>
					</div>
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
<!-- 							<li><a href="#" class="list-button item-link login-out">Sign Out</a></li> -->
							<li><a href="#" class="list-button item-link edit-popup">Add Edit</a></li>
						</ul>
					</div>
				</div>
			</div>
			<!-- links -->
		
		<!-- 	essat-edit -->
		<div class="popup popup-about">
    		<div class="content-block-title">动态内容 </div>
    		
    		
	    	<div class="content-block">
				<div class="list-block">
					<ul>
						<li class="align-top">
							<div class="item-content">
								<div class="item-media">
									<i class="icon icon-form-comment"></i>
								</div>
								<div class="item-inner">
									<div class="item-title label">内容</div>
									<div class="item-input">
										<textarea name="context" class="context-cls">梦想的价值在于去坚持不懈得实现</textarea>
									</div>
								</div>
							</div>
						</li>
						
						<li>
							<div class="item-content">
								<div class="item-media">
									<i class="icon icon-form-url"></i>
								</div>
								<div class="item-inner" style="height: auto;">
									<div class="item-title label">图片</div>
									<div class="item-input">
										<!-- 图片预区 -->
										<div class="imag-file-pre">
											<span><img src="${ctx}/static/f7/img/i-form-name-ios.svg" style="max-width:80px;min-width:60px;"></span>
										</div>
										
										<!--上传图片的按钮 -->
										<div class="imag-files">
											<a href="javascript:;" class="a-upload"> 
											<input type="file" name="" id="imag_file" accept="image/png,image/jpeg" /> 点击这里上传图片 </a>
										</div>
									</div>
								</div>
							</div>
						</li>
						<li>
				            <div class="item-content">
				              <div class="item-media"><i class="icon icon-form-toggle"></i></div>
				              <div class="item-inner"> 
				                <div class="item-title label">是否公开</div>
				                <div class="item-input">
				                  <label class="label-switch">
				                    <input type="checkbox" name="isopen"/>
				                    <div class="checkbox"></div>
				                  </label>
				                </div>
				              </div>
				            </div>
				         </li>
					</ul>
	    	
		    	</div>
	    	</div>
	    	
	    	<!-- button -->
				<div class="content-block">
                <div class="row">
                  <div class="col-50"><a href="#" class="button button-fill close-popup">取消</a></div>
                  <div class="col-50"><a href="#" class="button  button-fill color-green sub-btn">发布</a></div>
                </div>
              </div>
              <div id="console"></div>
  		</div>
		
		


<script type="text/javascript" src="${ctx}/static/f7/js/framework7.min.js"></script>
<script>
    var myApp = new Framework7({modalTitle: 'Hi do',modalButtonOk :"好得"});
  	// Export selectors engine
  	var $$ = Dom7;
  	//声明本地存储
  	var myStorage = window.localStorage;
  	// Add view
  	var mainView = myApp.addView('.view-main', {
  	    dynamicNavbar: true
  	});
  	
	//links
  	$$('.open-links').off("click").on('click', function () {
  	  var clickedLink = this;
	  myApp.popover('.popover-links', clickedLink);
  	});
	
	//打开动态编辑器
  	$$('.edit-popup').on('click', function () {
  		myApp.closeModal();
		myApp.popup('.popup-about');
		$$(".imag-file-pre").html("");
		loadImgFile();
  	});
	
  	//放回前一页
  	$$(".page-index").on("click",function(){
  		window.location.href='${ctx}/mob/index.do';
  	})
	
	//生成图片组件
	function loadImgFile(){
		$$('#imag_file').on("change",function(e,i){
			var file = $$(this).files()[0];
			var flat=true;
			if(file.size>500*1024){
				flat=false;
			}
			if($$(".imag-cls").length>9){
				myApp.alert('亲，您的图片太多了，最多支持9个哦！');
				return false
			}
			if(flat){
				addImg();
			}else{
				myApp.alert('亲，您的图片太大了，选个小于500K的再试吧！');
			}
		})
	}
	
	//添加多张图片
	function addImg(){
		var eid="img_"+new Date().getMilliseconds();
		var d=$$("#imag_file").readAsDataURL(handler,$$("#pre_headimg"),"#"+eid);
		var img='<span >'
				+'<img class="imag-cls" id="'+eid+'" src="${ctx}/static/f7/img/i-form-name-ios.svg" style="max-width:80px;min-width:60px;">'
				+'</span>';
		$$(".imag-file-pre").append(img);
	}
	
	
  	
  	/* 提交动态 */
  	$$(".sub-btn").on('click',function(){
  		$$(this).attr("disabled","disabled");
  		var imags=[];
  		$$.each($$(".imag-cls"),function(e,i){
  			imags.push($$(i).attr("src"));
  		})
 		console.log(imags);
  		var isopen=1;
  		if($$("input[name='isopen']").is(':checked')) {   // do something
  			isopen=0;
  		}
  		var data={'context':$$(".context-cls").val(),'imgurl':imags.join("###"),"isOpen":isopen};
	  	$$.ajax({
				url:"${ctx}/essay/save.do",
				data:data,
				type:"POST",
				dataType:"json",
				success:function(data){
					if(data && data.retCd==0){
						window.location.reload() ;
// 						myApp.closeModal();
					}else{
						myApp.alert("提交失败，请稍后再试！");
					}
				},
				error:function(data){
					console.log(data);
				},
				complete:function(xml){
					console.log(xml);
				}
			});  	
  	});
  	
  	
  	//赞方法
  	function zanEvent(){
  		$$(".link-zan").on("click",function(e){
  			var zan=$$(this);
  			if(zan.find("span.zan-text").html()=="已赞"){
  				return false;
  			}
  			var dataid=$$(this).attr("data-id");
  			var userkey="${user.uid}";
  			console.log(userkey);
  		  	$$.ajax({
				url:"${ctx}/essay/zan.do",
				data:{id:dataid},
				type:"POST",
				dataType:"json",
				success:function(data){
					if(data && data.retCode>-1){
						if(data.retCode==1){
							zan.find("span.zan-num").html("+"+data.data);
						}
						zan.find("span.zan-text").html("已赞");
					}else{
						myApp.alert("网络繁忙，请稍后再试！");
					}
				},
				error:function(data){
					console.log(data);
				},
				complete:function(xml){
// 					window.location.reload();
					console.log(xml);
				}
			}); 
  		})
  	}
  	
	//评论事件  	
  	function commentEvent(){
		
		/* step 0:
			绑定
			step 2: 
			
			
		*/
		
		$$(".link-comment").on("click",function(){
			$$(".comment-msg-"+$$(this).attr("data-id")).focus();
		})
			
		
		
		
  		$$(".comment-btn").on("click",function(){
  			var essayId=$$(this).attr("data-id");
  			var context=$$(".comment-msg-"+essayId).val();
  			if(context.trim()==""){
  				return false;
  			}
  			$$.ajax({
				url:"${ctx}/essay/addComment.do",
				data:{id:essayId,context:context},
				type:"POST",
				dataType:"json",
				success:function(data){
					if(data && data.retCode>-1){
						if(data.retCode==1){
							var html='<div ><a href="#" data-id="${item.id}">${user.accname}</a>:'
		 					  	+context+'</div>';
							$$("#comment-id_"+essayId).append(html);
							$$(".commnent-foot-"+essayId).show(200);
							$$(".comment-msg-"+essayId).val("");
							$$(".comments-num-"+essayId).html(parseInt($$(".comments-num-"+essayId).html())+1);
						}
					}else{
						myApp.alert("网络繁忙，请稍后再试！");
					}
				},
				error:function(data){
					console.log(data);
				},
				complete:function(xml){
					if(xml.response.indexOf('<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">')>-1){
						myApp.alert("登录已经超时，请重新登录",function(){
							window.location.reload();
						});
					}
				}
			}); 
  		})
  	}
  	
  	zanEvent();
  	commentEvent();
  </script>
  <script src="${ctx}/static/js/jquery-3.1.1.min.js"></script>
  <script src="${ctx}/static/js/readerfileutil.js"></script>