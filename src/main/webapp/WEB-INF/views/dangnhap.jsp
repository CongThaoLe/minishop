<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<jsp:include page="header.jsp"></jsp:include>
<body id="body-login">
	<a href="chitiet" id="link">Next Page Chitiet</a>
	<div id="body-flex-login">
		<div id="container-login">
			<div id="container-login-left">
				<div class="header-login" id="header-top-left">
					<span id="text-logo">WELCOME</span><br> 
					<span id="hint-text-logo">Hãy tạo nên phong cách của bạn cùng minishop</span>
				</div>
				<div id="header-bottom-left">
					<p><img alt="icon-oval" src='<c:url value="/resources/image/verified.png"></c:url>' style="width: 20px;height: 20px"><span>Luôn cập nhật xu hướng thời trang mới nhất</span></p>
					<p><img alt="icon-oval" src='<c:url value="/resources/image/verified.png"></c:url>' style="width: 20px;height: 20px"><span>Giảm hơn 50% tất cả các mặt hàng cho khách VIP</span></p>
					<p><img alt="icon-oval" src='<c:url value="/resources/image/verified.png"></c:url>' style="width: 20px;height: 20px"><span>Tận tình tư vấn để tạo nên phong cách của bạn</span></p>
				</div>
			</div>
			<div id="container-login-right">
				<div id="header-top-right" class="header-login">
					<span class="active" id="dangnhap">Đăng nhập</span> / <span id="dangky">Đăng ký</span>
				</div>
				
				<div id="container-center-login-right">
						<div id="container-center-login-right" class="container-login-form">
							<input id="email" name="email" type="text" class="material-text-input input-icon-email" placeholder="email"><br>
							<input id="matkhau" name="matkhau" type="password" class="material-text-input input-icon-password" placeholder="password"><br>
							<input id="btndangnhap" type="button" class="material-primary-button" value="Đăng nhập" style="margin-top: 14px">
						</div>
						
						<div id="container-center-login-right" class="container-signup-form">
						<form action="" method="post">
							<input id="email" name="email" type="text" class="material-text-input input-icon-email" placeholder="email"><br>
							<input id="matkhau" name="matkhau" type="password" class="material-text-input input-icon-password" placeholder="password"><br>
							<input id="matkhau" name="nhaplaimatkhau" type="password" class="material-text-input input-icon-password" placeholder="Confirm Password"><br>
							<input id="btndangnhap" type="submit" class="material-primary-button" value="Đăng ký" style="margin-top: 14px">
						</form>
						</div>
						
						<span id="ketqua"></span>
						<span>${kiemtradangnhap}</span>
				</div>
				
				<div id="container-social-login">
					<img alt="icon-oval" src='<c:url value="/resources/image/facebook.png"></c:url>' style="width: 40px;height: 40px">
					<img alt="icon-oval" src='<c:url value="/resources/image/instagram-sketched.png"></c:url>' style="width: 40px;height: 40px">
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>