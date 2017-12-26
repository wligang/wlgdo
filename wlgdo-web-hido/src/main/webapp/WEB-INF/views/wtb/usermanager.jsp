<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ page isELIgnored="false"%>

<meta charset="utf-8">
<link rel="icon" href="/images/logo.png" type="image/x-icon">
<link rel="shortcut icon" href="/main/images/logo.png"
	type="image/x-icon">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no, minimal-ui">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<title>Hi do</title>
<!-- Path to Framework7 Library CSS-->
<link rel="stylesheet" href=/static/f7/css/framework7.ios.css>
<link rel="stylesheet" href=/static/f7/css/my-app.css>
<!-- Path to your custom app styles-->
<style type="text/css">
.panel {
	background: #e2e1ec;
}
</style>
</head>
<body>
	<div class="views">
		<div class="view view-main" data-page="home">
			<div class="pages">
				<div data-page="home" class="page navbar-fixed">
					<div class="navbar">
						<div class="navbar-inner">
							<div class="left"></div>
							<div class="center" style="left: 0px;">Media List View</div>
							<div class="right"></div>
						</div>
					</div>
					<div class="page-content">
						<div class="content-block-title">用户${fn:length(userlist)}位,参与活动${act.acttime},点击次数${act.loadtime},页面次数${ivt.loadtime},</div>
						<div class="list-block media-list">
							<ul>
								<c:forEach items="${userlist}" var="item">
									<li><a href="#" class="item-link item-content">
											<div class="item-media">
												<img src="/images/t-1.png" width="80">
											</div>
											<div class="item-inner">
												<div class="item-title-row">
													<div class="item-title">${item.accname }</div>
													<div class="item-after">${item.ctime }</div>
												</div>
												<div class="item-subtitle">${item.email}</div>
												<div class="item-text">${item.phone}</div>
											</div>
									</a></li>

								</c:forEach>

								<!-- <li><a href="#" class="item-link item-content">
                      <div class="item-media"><img src="http://hhhhold.com/160/d/jpg?1" width="80"></div>
                      <div class="item-inner">
                        <div class="item-title-row">
                          <div class="item-title">Yellow Submarine</div>
                          <div class="item-after">$15</div>
                        </div>
                        <div class="item-subtitle">Beatles</div>
                        <div class="item-text">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla sagittis tellus ut turpis condimentum, ut dignissim lacus tincidunt. Cras dolor metus, ultrices condimentum sodales sit amet, pharetra sodales eros. Phasellus vel felis tellus. Mauris rutrum ligula nec dapibus feugiat. In vel dui laoreet, commodo augue id, pulvinar lacus.</div>
                      </div></a></li> -->
							</ul>
						</div>
						<div class="content-block-title">Mail App</div>
						<c:if test="${1==2 }">
						

						<div class="list-block media-list">
							<ul>
								<li><a href="#" class="item-link item-content">
										<div class="item-inner">
											<div class="item-title-row">
												<div class="item-title">Facebook</div>
												<div class="item-after">17:14</div>
											</div>
											<div class="item-subtitle">New messages from John Doe</div>
											<div class="item-text">Lorem ipsum dolor sit amet,
												consectetur adipiscing elit. Nulla sagittis tellus ut turpis
												condimentum, ut dignissim lacus tincidunt. Cras dolor metus,
												ultrices condimentum sodales sit amet, pharetra sodales
												eros. Phasellus vel felis tellus. Mauris rutrum ligula nec
												dapibus feugiat. In vel dui laoreet, commodo augue id,
												pulvinar lacus.</div>
										</div>
								</a></li>
								<li><a href="#" class="item-link item-content">
										<div class="item-inner">
											<div class="item-title-row">
												<div class="item-title">John Doe (via Twitter)</div>
												<div class="item-after">17:11</div>
											</div>
											<div class="item-subtitle">John Doe (@_johndoe)
												mentioned you on Twitter!</div>
											<div class="item-text">Lorem ipsum dolor sit amet,
												consectetur adipiscing elit. Nulla sagittis tellus ut turpis
												condimentum, ut dignissim lacus tincidunt. Cras dolor metus,
												ultrices condimentum sodales sit amet, pharetra sodales
												eros. Phasellus vel felis tellus. Mauris rutrum ligula nec
												dapibus feugiat. In vel dui laoreet, commodo augue id,
												pulvinar lacus.</div>
										</div>
								</a></li>
								<li><a href="#" class="item-link item-content">
										<div class="item-inner">
											<div class="item-title-row">
												<div class="item-title">Facebook</div>
												<div class="item-after">16:48</div>
											</div>
											<div class="item-subtitle">New messages from John Doe</div>
											<div class="item-text">Lorem ipsum dolor sit amet,
												consectetur adipiscing elit. Nulla sagittis tellus ut turpis
												condimentum, ut dignissim lacus tincidunt. Cras dolor metus,
												ultrices condimentum sodales sit amet, pharetra sodales
												eros. Phasellus vel felis tellus. Mauris rutrum ligula nec
												dapibus feugiat. In vel dui laoreet, commodo augue id,
												pulvinar lacus.</div>
										</div>
								</a></li>
								<li><a href="#" class="item-link item-content">
										<div class="item-inner">
											<div class="item-title-row">
												<div class="item-title">John Doe (via Twitter)</div>
												<div class="item-after">15:32</div>
											</div>
											<div class="item-subtitle">John Doe (@_johndoe)
												mentioned you on Twitter!</div>
											<div class="item-text">Lorem ipsum dolor sit amet,
												consectetur adipiscing elit. Nulla sagittis tellus ut turpis
												condimentum, ut dignissim lacus tincidunt. Cras dolor metus,
												ultrices condimentum sodales sit amet, pharetra sodales
												eros. Phasellus vel felis tellus. Mauris rutrum ligula nec
												dapibus feugiat. In vel dui laoreet, commodo augue id,
												pulvinar lacus.</div>
										</div>
								</a></li>
							</ul>
						</div>
						<div class="content-block-title">Something more simple</div>
						<div class="list-block media-list">
							<ul>
								<li>
									<div class="item-content">
										<div class="item-media">
											<img src="http://hhhhold.com/88/d/jpg?13" width="44">
										</div>
										<div class="item-inner">
											<div class="item-title-row">
												<div class="item-title">Yellow Submarine</div>
											</div>
											<div class="item-subtitle">Beatles</div>
										</div>
									</div>
								</li>
								<li><a href="#" class="item-link item-content">
										<div class="item-media">
											<img src="http://hhhhold.com/88/d/jpg?23" width="44">
										</div>
										<div class="item-inner">
											<div class="item-title-row">
												<div class="item-title">Don't Stop Me Now</div>
											</div>
											<div class="item-subtitle">Queen</div>
										</div>
								</a></li>
								<li>
									<div class="item-content">
										<div class="item-media">
											<img src="http://hhhhold.com/88/d/jpg?33" width="44">
										</div>
										<div class="item-inner">
											<div class="item-title-row">
												<div class="item-title">Billie Jean</div>
											</div>
											<div class="item-subtitle">Michael Jackson</div>
										</div>
									</div>
								</li>
							</ul>
						</div>
						<div class="content-block-title">Inset</div>
						<div class="list-block media-list inset">
							<ul>
								<li><a href="#" class="item-link item-content">
										<div class="item-media">
											<img src="http://hhhhold.com/88/d/jpg?13" width="44">
										</div>
										<div class="item-inner">
											<div class="item-title-row">
												<div class="item-title">Yellow Submarine</div>
											</div>
											<div class="item-subtitle">Beatles</div>
										</div>
								</a></li>
								<li><a href="#" class="item-link item-content">
										<div class="item-media">
											<img src="http://hhhhold.com/88/d/jpg?23" width="44">
										</div>
										<div class="item-inner">
											<div class="item-title-row">
												<div class="item-title">Don't Stop Me Now</div>
											</div>
											<div class="item-subtitle">Queen</div>
										</div>
								</a></li>
								<li><a href="#" class="item-link item-content">
										<div class="item-media">
											<img src="http://hhhhold.com/88/d/jpg?33" width="44">
										</div>
										<div class="item-inner">
											<div class="item-title-row">
												<div class="item-title">Billie Jean</div>
											</div>
											<div class="item-subtitle">Michael Jackson</div>
										</div>
								</a></li>
							</ul>
						</div>
					</c:if>
					</div>
				</div>
			</div>
		</div>


	</div>


	<script type="text/javascript" src="/static/f7/js/framework7.min.js"></script>
	<script type="text/javascript">
		// Initialize your app
		var myApp = new Framework7({
			modalTitle : 'Hi do',
			modalButtonOk : "好得"
		});
		// Export selectors engine
		var $$ = Dom7;

		// Add view
		var mainView = myApp.addView('.view-main', {
			dynamicNavbar : true
		});
	</script>

</body>
</html>