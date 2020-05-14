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
						alt="" src='<c:url value="/resources/image/icon_giohang2.png"/>'><span
						class="circle-giohang">${soluonggiohang }</span></a></li>
			</ul>
		</div>
		</nav>
	</div>

	<div class="container">
		<div class="row" style="margin-top: 16px;">
			<div class="col-md-6 col-sm-12">
				<h3>Danh sách sản phẩm trong giỏ hàng</h3>
				<table class="table">
					<tbody>
						<tr>
							<th>Tên sản phẩm</th>
							<th>Màu sản phẩm</th>
							<th>Size</th>
							<th>Số lượng</th>
							<th>Giá tiền</th>
						</tr>
						<c:forEach var="giohang"
							items="${giohang }">
							<tr>
								<td class="sanpham" data-masp="${giohang.getMasp() }">${giohang.getTensp() }</td>
								<td class="mau"
									data-mamau="${giohang.getMamau() }">${giohang.getTenmau() }</td>
								<td class="size"
									data-masize="${giohang.getMasize() }">${giohang.getTensize() }</td>
								<td class="soluong"><input class="soluong-giohang" data-soluong="${giohang.getSoluong() }" min="1" type="number" value="${giohang.getSoluong() }"/></td>
								<td class="giatien-giohang" data-value="${giohang.getGiatien() }">${giohang.getGiatien() }</td>
								<td><button class="xoa-giohang btn btn-danger btn-giohang">Xóa</button></td>
							</tr>
						</c:forEach>
					</tbody>	
				</table>
				<h4>Tổng tiền : <span id="tongtiensp" style="color: red">1.500.000 VNĐ</span></h4>
			</div>
			<div class="col-md-6 col-sm-12">
				<h3>Thông tin người nhận/mua</h3>
				
				<div class="form-group">
					<form action="" method="post">
						<label for="tenkhachhang">Tên người mua/nhận</label>
						<input id="tenkhachhang" name="tenkhachhang" class="form-control"/><br>
						
						<label for="sodt">Số điện thoại liên lạc</label>
						<input id="sodt" name="sodt" class="form-control"/><br>
						
						<div class="form-check">
							  <label class="form-check-label">
							    	<input type="radio" class="form-check-input" name="hinhthucgiaohang" checked value="Giao hàng tận nơi">Giao hàng tận nơi
							  </label>
						</div>
						
						<div class="form-check disabled">
							  <label class="form-check-label">
							    	<input type="radio" class="form-check-input" name="hinhthucgiaohang" value="Nhận hàng tại cửa hàng gần nhất">Nhận hàng tại cửa hàng gần nhất
							  </label>
						</div><br>
						
						<label for="diachigiaohang">Địa chỉ nhận hàng</label>
						<input id="diachigiaohang" name="diachigiaohang" class="form-control"/><br>
						
						<label for="ghichu">Ghi chú</label>
	  					<textarea class="form-control" rows="5" id="ghichu" name="ghichu"></textarea><br>
	  					
	  					<input type="submit" class="btn btn-primary" value="Đặt hàng"/><br><span style="color: red;font-size: 15px">${ketquadathang}</span>
  					</form>
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