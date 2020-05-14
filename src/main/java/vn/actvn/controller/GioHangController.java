package vn.actvn.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.actvn.entity.ChiTietHoaDon;
import vn.actvn.entity.ChiTietHoaDonId;
import vn.actvn.entity.DanhMucSanPham;
import vn.actvn.entity.GioHang;
import vn.actvn.entity.HoaDon;
import vn.actvn.service.ChiTietHoaDonService;
import vn.actvn.service.DanhMucService;
import vn.actvn.service.HoaDonService;

@Controller
@RequestMapping("giohang/")
public class GioHangController {

	@Autowired
	HoaDonService hoadonService;
	
	@Autowired
	ChiTietHoaDonService chiTietHoaDonService;
	
	@Autowired 
	DanhMucService danhMucService;

	@GetMapping
	public String Default(HttpSession httpSession, ModelMap modelMap) {
		List<DanhMucSanPham> listDMSP = danhMucService.LayDanhMuc();
		modelMap.addAttribute("danhmuc", listDMSP);
		List<GioHang> listGioHang = (List<GioHang>) httpSession.getAttribute("giohang");
		if (listGioHang != null) {
			modelMap.addAttribute("soluonggiohang", listGioHang.size());
			modelMap.addAttribute("giohang", listGioHang);
		} else {
			modelMap.addAttribute("soluonggiohang", 0);
		}
		return "giohang";
	}

	@PostMapping
	public String ThemHoaDon(HttpSession httpSession,@RequestParam String tenkhachhang, @RequestParam String sodt,
			@RequestParam String diachigiaohang, @RequestParam String hinhthucgiaohang, @RequestParam String ghichu,ModelMap modelMap) {
		List<GioHang> listGioHang = (List<GioHang>) httpSession.getAttribute("giohang");
		if (listGioHang != null) {
			
			HoaDon hoaDon = new HoaDon();
			hoaDon.setTenkhachhang(tenkhachhang);
			hoaDon.setSodt(sodt);
			hoaDon.setDiachigiaohang(diachigiaohang);
			hoaDon.setHinhthucgiaohang(hinhthucgiaohang);
			hoaDon.setGhichu(ghichu);
			
			int idHoaDon = hoadonService.ThemHoaDon(hoaDon);
			if(idHoaDon > 0) {
				Set<ChiTietHoaDon> chiTietHoaDons = new HashSet<ChiTietHoaDon>();
				for(GioHang gioHang : listGioHang) {
					ChiTietHoaDonId chiTietHoaDonId = new ChiTietHoaDonId();
					chiTietHoaDonId.setMachitietsanpham(gioHang.getMachitiet());
					chiTietHoaDonId.setMahoadon(hoaDon.getMahoadon());
					
					ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
					chiTietHoaDon.setChiTietHoaDonId(chiTietHoaDonId);
					chiTietHoaDon.setGiatien(gioHang.getGiatien());
					chiTietHoaDon.setSoluong(gioHang.getSoluong());
					
					chiTietHoaDonService.ThemChiTietHoaDon(chiTietHoaDon);
					
				}
				modelMap.addAttribute("ketquadathang", "Đặt hàng thành công!");
			}else {
				modelMap.addAttribute("ketquadathang", "Đặt hàng thất bại!");
			}
		}else {
			modelMap.addAttribute("ketquadathang", "Giỏ hàng trống. Bạn cần thêm sản phẩm vào giỏ hàng để tiến hành đặt hàng!");
		}
		return "giohang";
	}
}
