<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ include  file="/static/taglibs/header.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<div class="navbar">
	<div class="navbar-inner">
		<div class="left"><a href="javascript:window.location.href='http://www.wlgdo.com';" target="_Blank">Home</a></div>
		<div class="center sliding">Sign Up·Hi do</div>
	</div>
</div>
<div class="pages">
	<div data-page="signup" class="page">
		<form action="${ctx}/auth/reg" id="reg-form" method="post" class="ajax-submit reg-form">
			<div class="page-content">
				<div class="content-block-title">regist info </div>
				<div class="list-block">
					<ul>
						<li>
							<div class="item-content">
								<div class="item-media">
									<i class="icon icon-form-name"></i>
								</div>
								<div class="item-inner">
									<div class="item-title label">名称</div>
									<div class="item-input">
										<input type="text" name="accname" placeholder="Your name" />
									</div>
								</div>
							</div>
						</li>
						<li>
							<div class="item-content">
								<div class="item-media">
									<i class="icon icon-form-url"></i>
								</div>
								<div class="item-inner" style="height: 100px;">
									<div class="item-title label">头像</div>
									<div class="item-input img-file">
										<span id="pre_headimg" >
											<img src="${ctx }/static/f7/img/i-form-name-ios.svg" id="headimg" name="url" style="width: 80px; height: 80px; border-radius: 50%; overflow: hidden;">
										</span>
										<span style="display: none">
											<input type="file" id="headfile" name="file" accept="image/png,image/jpeg" /> 
										 	<input type="hidden" name="url" id="url" class="headimg_64" />
										</span>
									</div>
								</div>
							</div>
						</li>
						<li>
							<div class="item-content">
								<div class="item-media">
									<i class="icon icon-form-email"></i>
								</div>
								<div class="item-inner">
									<div class="item-title label">邮箱</div>
									<div class="item-input">
										<input type="email" name="email" placeholder="E-mail" />
									</div>
								</div>
							</div>
						</li>
						<li>
							<div class="item-content">
								<div class="item-media">
									<i class="icon icon-form-password"></i>
								</div>
								<div class="item-inner">
									<div class="item-title label">密码</div>
									<div class="item-input">
										<input type="password" name="password" placeholder="Password" />
									</div>
								</div>
							</div>
						</li>
						<li>
							<div class="item-content">
								<div class="item-media">
									<i class="icon icon-form-tel"></i>
								</div>
								<div class="item-inner">
									<div class="item-title label">电话</div>
									<div class="item-input">
										<input type="tel" name="phone" placeholder="Phone" />
									</div>
								</div>
							</div>
						</li>
						<li>
							<div class="item-content">
								<div class="item-media">
									<i class="icon icon-form-gender"></i>
								</div>
								<div class="item-inner">
									<div class="item-title label">性别</div>
									<div class="item-input">
										<select>
											<option>Male</option>
											<option>Female</option>
										</select>
									</div>
								</div>
							</div>
						</li>
						<li>
							<div class="item-content">
								<div class="item-media">
									<i class="icon icon-form-calendar"></i>
								</div>
								<div class="item-inner">
									<div class="item-title label">生日</div>
									<div class="item-input">
										<input type="date" name="birthday" placeholder="Birth day" value="2017-01-01" />
									</div>
								</div>
							</div>
						</li>
						<li>
							<div class="item-content">
								<div class="item-media">
									<i class="icon icon-form-toggle"></i>
								</div>
								<div class="item-inner">
									<div class="item-title label">是否公开</div>
									<div class="item-input">
										<label class="label-switch"> <input type="checkbox" />
											<div class="checkbox"></div>
										</label>
									</div>
								</div>
							</div>
						</li>
						<li class="align-top">
							<div class="item-content">
								<div class="item-media">
									<i class="icon icon-form-comment"></i>
								</div>
								<div class="item-inner">
									<div class="item-title label">签名</div>
									<div class="item-input">
										<textarea name="declaration">梦想的价值在于去坚持不懈得实现</textarea>
									</div>
								</div>
							</div>
						</li>
					</ul>
				</div>
				<div class="content-block">
					<div class="row">
						<div class="col-50">
							<a href="#" class="button button-big button-fill color-red sign-up-close">Cancel</a>
						</div>
						<div class="col-50">
							<input type="submit" value="Submit" id="sub-btn" class="button button-big button-fill color-green" />
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>
