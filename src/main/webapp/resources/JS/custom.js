$(document).ready(function(){
	var masanpham = 0;
	
	$("#btndangnhap").click(function(){
		var email = $("#email").val();
		var password = $("#matkhau").val();
		$.ajax({
			url:"/minishop/api/KiemTraDangNhap",
			type:"GET",
			data:{
				email:email,
				matkhau:password
			},
			success: function(value){
				if(value=="true"){
					var duongdan = window.location.href;
					duongdan = duongdan.replace("dangnhap/","");
					window.location = duongdan;
				}else{
					$("#ketqua").text("Dang nhap that bai !");
				}
			}
		})
	});
	
	
	$("#dangnhap").click(function(){
		$(this).addClass("active");
		$("#dangky").removeClass("active");
		$(".container-login-form").show();
		$(".container-signup-form").hide();
	});
	
	
	$("#dangky").click(function(){
		$(this).addClass("active");
		$("#dangnhap").removeClass("active");
		$(".container-login-form").hide();
		$(".container-signup-form").show();
	});
	
	
	$(".btn-giohang").click(function(){
		var machitiet = $(this).attr("data-machitiet");
		var masp = $("#tensanpham").attr("data-masp");
		var masize = $(this).closest("tr").find(".size").attr("data-masize");
		var mamau = $(this).closest("tr").find(".mau").attr("data-mamau");
		var tensp = $("#tensanpham").text();
		var giatien = $("#giatien").attr("data-value");
		var tenmau = $(this).closest("tr").find(".mau").text();
		var tensize = $(this).closest("tr").find(".size").text();
		var soluong = $(this).closest("tr").find(".soluong").attr("data-soluong");
		$.ajax({
			url:"/minishop/api/ThemGioHang",
			type:"GET",
			data:{
				machitiet:machitiet,
				masp:masp,
				masize:masize,
				mamau:mamau,
				tensp:tensp,
				giatien:giatien,
				tenmau:tenmau,
				tensize:tensize,
				soluong:soluong
			},
			success: function(value){
				
			}
		}).done(function(){
			$.ajax({
				url:"/minishop/api/LaySoLuongGioHang",
				type:"GET",
				success: function(value){
					$(".circle-giohang").text(value);
				}
			})
		});
	});
	
	function GanTongTienGioHang(isEventChange){
		var tongtiensp = 0;
		$(".giatien-giohang").each(function(){
			var giatien = $(this).text();
			var soluong = $(this).closest("tr").find(".soluong-giohang").attr("data-soluong");
			var tongtien = parseInt(giatien)*soluong;
//			var format = parseFloat(tongtien).toFixed(3).replace(/(\d)(?=(\d{3})+\.)/g, "$1.").toString();
//			tongtiensp = tongtiensp + parseFloat(format);
			tongtiensp = tongtiensp + tongtien;
			if(!isEventChange){
//				$(this).closest("tr").find(".giatien-giohang").html(format);
				$(this).closest("tr").find(".giatien-giohang").html(tongtien);
			};
//			var formatTongTien = tongtiensp.toFixed(3).replace(/(\d)(?=(\d{3})+\.)/g, "$1.").toString();
//			$("#tongtiensp").html(formatTongTien+"");
		});
		$("#tongtiensp").html(tongtiensp+"");
	};
	
	GanTongTienGioHang(false);
	
	$(".soluong-giohang").change(function(){
		var soluong = $(this).val();
		var giatien = $(this).closest("tr").find(".giatien-giohang").attr("data-value");
		var tongtien = soluong*parseInt(giatien);
//		var format = tongtien.toFixed(3).replace(/(\d)(?=(\d{3})+\.)/g, "$1.").toString();
		$(this).closest("tr").find(".giatien-giohang").html(tongtien);
		GanTongTienGioHang(true);
		
		var masp = $(this).closest("tr").find(".sanpham").attr("data-masp");
		var masize = $(this).closest("tr").find(".size").attr("data-masize");
		var mamau = $(this).closest("tr").find(".mau").attr("data-mamau");
		
		$.ajax({
			url:"/minishop/api/CapNhatGioHang",
			type:"GET",
			data:{
				masp:masp,
				masize:masize,
				mamau:mamau,
				soluong:soluong
			},
			success: function(){
				
			}
		});
		
	});
	
	$(".xoa-giohang").click(function(){
		var self = $(this);
		var masp = $(this).closest("tr").find(".sanpham").attr("data-masp");
		var masize = $(this).closest("tr").find(".size").attr("data-masize");
		var mamau = $(this).closest("tr").find(".mau").attr("data-mamau");
		
		$.ajax({
			url:"/minishop/api/XoaGioHang",
			type:"GET",
			data:{
				masp:masp,
				masize:masize,
				mamau:mamau
			},
			success: function(value){
				self.closest("tr").remove();
				GanTongTienGioHang(true);
			}
		});
	});
	
	$("body").on("click",".paging-item",function(){
		$(".paging-item").removeClass("active");
		$(this).addClass("active");
		var sotrang = $(this).text();
		var spbatdau = (sotrang-1)*5;
		$.ajax({
			url:"/minishop/api/LaySanPhamLimit",
			type:"GET",
			data:{
				sanphambatdau:spbatdau
			},
			success: function(value){
				var tbodysanpham = $("#table-sanpham").find("tbody");
				tbodysanpham.empty();
				tbodysanpham.append(value);
			}
		});
	});
	
	$("#check-all").change(function(){
		if(this.checked){
			$("#table-sanpham input").each(function(){
				$(this).attr("checked",true);
			});
		}else{
			$("#table-sanpham input").each(function(){
				$(this).attr("checked",false);
			});
		}
	});
	
	$("#xoa-sanpham").click(function(){
		$("#table-sanpham > tbody input:checked").each(function(){
			var masanpham = $(this).val();
			var This = $(this);
			$.ajax({
				url:"/minishop/api/xoasanpham",
				type:"GET",
				data:{
					masp:masanpham
				},
				success: function(){
					This.closest("tr").remove();
				}
			});
		});
	});
	
	var files = [];
	var tenhinh = 
	$("#hinhanh").change(function(event){
		files = event.target.files;
		tenhinh = files[0].name;
		forms = new FormData();
		forms.append("file",files[0]);
		
		$.ajax({
			url:"/minishop/api/UploadFile",
			type:"POST",
			data:forms,
			contentType:false,
			processData:false,
			enctype: "multipart/form-data",
			success: function(){
				
			}
		});
	});
	
	$("body").on("click",".btn-chitiet",function(){
		$(this).remove();
		var chitietclone = $("#chitietsanpham").clone().removeAttr("id");
		$("#containerchitietsanpham").append(chitietclone);
	});
	
	$("#btnthemsanpham").click(function(event){
		event.preventDefault();
		var formdata = $("#form-sanpham").serializeArray();
		
		json = {};
		arraychitiet = [];
		
		$.each(formdata, function(i, field){
		      json[field.name] = field.value;
		    });
		
		$("#containerchitietsanpham > .chitietsanpham").each(function(){
			objectChitiet = {};
			mausanpham = $(this).find("#mausanpham").val();
			sizesanpham = $(this).find("#sizesanpham").val();
			soluong = $(this).find("#soluong").val();
			objectChitiet["mausanpham"] = mausanpham;
			objectChitiet["sizesanpham"] = sizesanpham;
			objectChitiet["soluong"] = soluong;
			
			arraychitiet.push(objectChitiet);
		});
		
		json["chitietsanpham"] = arraychitiet;
		json["hinhsanpham"] = tenhinh;
		
		$.ajax({
			url:"/minishop/api/themsanpham",
			type:"POST",
			data:{
				dataJson: JSON.stringify(json)
			},
			success: function(){
				
			}
		});
	});
	
	$("#btncapnhatsanpham").click(function(event){
		event.preventDefault();
		var formdata = $("#form-sanpham").serializeArray();
		
		json = {};
		arraychitiet = [];
		
		$.each(formdata, function(i, field){
		      json[field.name] = field.value;
		    });
		
		$("#containerchitietsanpham > .chitietsanpham").each(function(){
			objectChitiet = {};
			mausanpham = $(this).find("#mausanpham").val();
			sizesanpham = $(this).find("#sizesanpham").val();
			soluong = $(this).find("#soluong").val();
			objectChitiet["mausanpham"] = mausanpham;
			objectChitiet["sizesanpham"] = sizesanpham;
			objectChitiet["soluong"] = soluong;
			
			arraychitiet.push(objectChitiet);
		});
		
		json["masanpham"] = masanpham;
		json["chitietsanpham"] = arraychitiet;
		json["hinhsanpham"] = tenhinh;
		$.ajax({
			url:"/minishop/api/capnhatsanpham",
			type:"POST",
			data:{
				dataJson: JSON.stringify(json)
			},
			success: function(){
				
			}
		});
	});
	
	$("body").on("click",".capnhatsanpham",function(){
		masanpham = $(this).attr("data-id");
		
		$("#btncapnhatsanpham").removeClass("hidden");
		$("#btnthoat").removeClass("hidden");
		$("#btnthemsanpham").addClass("hidden");
		
		$.ajax({
			url:"/minishop/api/LayDanhSachSanPhamTheoMa",
			type:"POST",
			data:{
				masanpham: masanpham
			},
			success: function(value){
				console.log(value);
				$("#tensanpham").val(value.tensanpham);
				$("#giatien").val(value.giatien);
				$("#mota").val(value.mota);
				if(value.gianhcho === "Nam"){
					$("#rd-nam").prop("checked",true);
				}else{
					$("#rd-nu").prop("checked",true);
				}
				
				$("#danhMucSanPham").val(value.danhMucSanPham.madanhmuc);
				
				$("#containerchitietsanpham").empty();
				
				var countchitiet = value.chitietsanpham.length;
				for(i=0;i<countchitiet;i++){
					var chitietclone = $("#chitietsanpham").clone().removeAttr("id");
					
					if(i<countchitiet-1){
						chitietclone.find(".btn-chitiet").remove();
					}
					
					chitietclone.find("#mausanpham").val(value.chitietsanpham[i].mausanpham.mamau);
					chitietclone.find("#sizesanpham").val(value.chitietsanpham[i].sizesanpham.masize);
					chitietclone.find("#soluong").val(value.chitietsanpham[i].soluong);
					
					$("#containerchitietsanpham").append(chitietclone);
				}

			}
		});
	});
	
});