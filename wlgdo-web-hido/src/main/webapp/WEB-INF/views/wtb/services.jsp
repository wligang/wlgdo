<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include  file="/static/taglibs/header.jsp"%><html>
<!-- We don't need full layout here, because this page will be parsed with Ajax-->
<!-- Top Navbar-->
<div class="navbar">
	<div class="navbar-inner">
		<div class="left">
			<a href="#" class="back link"> <i class="icon icon-back"></i><span>Back</span></a>
		</div>
		<div class="center sliding">Services</div>
		<div class="right">
			<!-- Right link contains only icon - additional "icon-only" class-->
			<a href="#" class="link icon-only open-panel"> <i
				class="icon icon-bars"></i></a>
		</div>
	</div>
</div>
<div class="pages">
	<!-- Page, data-page contains page name-->
	<div data-page="services" class="page">
		<!-- Scrollable page content-->
		<div class="page-content">
			<div class="content-block">
				<div class="content-block-inner">
					<p>Here is Services page!</p>
					<p>
						Go <a href="#" class="back">back</a> or go to <a href="about.html">About</a>
						page.
					</p>
					<p>
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
					</p>

				</div>
			</div>
		</div>
	</div>
</div>
<script>

</script>