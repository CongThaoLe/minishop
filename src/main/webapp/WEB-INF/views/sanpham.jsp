<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="vn.actvn.entity.NhanVien"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="header.jsp"></jsp:include>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="resources/Style.css">
<title>Home Page</title>
</head>
<body>
	<div id="header-chitiet" class="container-fluid">
		<nav class="navbar navbar-expand-lg navbar-light none-nav"> <a
			class="navbar-brand" href='<c:url value="/"/>'><img alt=""
			src='<c:url value="/resources/image/logo2.png"/>'></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto navbar-center">
				<li class="nav-item active"><a class="nav-link" href='<c:url value="/"/>'>TRANG
						CHỦ <span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> Sản Phẩm </a>
					<div class="dropdown-menu open" aria-labelledby="navbarDropdown">
						<c:forEach var="value" items="${danhmuc }">
							<a class="dropdown-item" href='<c:url value="/sanpham/${value.getMadanhmuc()}/${value.getTendanhmuc() }"/>'>${value.getTendanhmuc() }</a>
							<div class="dropdown-divider"></div>
						</c:forEach>
					</div></li>
				<li class="nav-item"><a class="nav-link" href="#">DỊCH VỤ</a></li>
				<li class="nav-item"><a class="nav-link" href="#">LIÊN HỆ</a></li>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<c:choose>
					<c:when test="${chucaidau != null}">
						<li class="nav-item"><a class="nav-link circle-avartar"
							href=""><span>${chucaidau}</span></a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link" href='<c:url value="/dangnhap/"/>'>ĐĂNG
								NHẬP</a></li>
					</c:otherwise>
				</c:choose>
				<li class="nav-item"><a class="nav-link" href='<c:url value="/giohang/"/>'><img
						alt="" src='<c:url value="/resources/image/icon_giohang2.png"/>'><span class="circle-giohang">${soluonggiohang }</span></a></li>
			</ul>
		</div>
		</nav>
	</div>

	<div class="container">
		<div class="row" style="margin-top: 16px">
			<div class="col-sm-2 col-md-2">
				<h3>Danh mục</h3>
				<ul class="mymenu">
					<c:forEach var="value" items="${danhmuc }">
						<li><a href='<c:url value="/sanpham/${value.getMadanhmuc()}/${value.getTendanhmuc() }"/>'>${value.getTendanhmuc() }</a></li>
					</c:forEach>
				</ul>
			</div>
			<div class="col-sm-10 col-md-10">
					<h3>${tendanhmuc }</h3>
					<div class="row">
					
						<c:forEach var="sanpham" items="${listSanPham }">
					
						<div class="card col-md-4 col-sm-6 sanpham wow zoomIn"
							style="width: 18rem;">
							<img class="card-img-top"
								src='<c:url value="/resources/image/SanPham/${sanpham.getHinhsanpham() }"/>'
								alt="Card image cap" style="height: 379px">
							<div class="card-body title-sanpham-loaddanhmuc">
								<h5 class="card-title">${sanpham.getTensanpham() }</h5>
								<p class="card-text">${sanpham.getGiatien() } VNĐ</p>
								<a href='<c:url value="/chitiet/${sanpham.getMasanpham() }"/>' class="btn btn-primary">Xem Chi Tiết</a>
							</div>
						</div>
						
						</c:forEach>
					</div>
			</div>
		</div>
	</div>

	<div id="footer" class="container-fluid">
		<div class="row">
			<div class="col-md-4 col-sm-4 wow tada" data-wow-duration="1s">
				<p>
					<span class="title-footer">THÔNG TIN SHOP</span>
				</p>
				<br> <span>LCT Shop là một shop thời trang đầy uy tín,
					luôn đảm bảo chất lượng sản phẩm tốt nhất cho khách hàng</span>
			</div>
			<div class="col-md-4 col-sm-4 wow tada" data-wow-duration="1s"
				data-wow-delay="0.5s">
				<p>
					<span class="title-footer">LIÊN HỆ</span>
				</p>
				<br> <span>30N05, khu tái định cư Triều Khúc, Thanh
					Xuân, Hà Nội</span><br> <span>congthao97ht@gmail.com</span><br>
				<span>0344595156</span>
			</div>
			<div class="col-md-4 col-sm-4 wow tada" data-wow-duration="1s"
				data-wow-delay="1s">
				<p>
					<span class="title-footer">GÓP Ý</span>
				</p>
				<br>
				<form action="" method="post">
					<input class="footer-input" style="height: 38px;" type="text"
						placeholder="Email" name="tenNhanVien"><br>
					<textarea class="footer-input" rows="4" cols="50"
						placeholder="Nội dung" name="tuoi"></textarea>
					<button class="material-primary-button">Đồng ý</button>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>