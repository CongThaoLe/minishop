package vn.actvn.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import vn.actvn.dao.DanhMucDAO;
import vn.actvn.entity.ChiTietSanPham;
import vn.actvn.entity.DanhMucSanPham;
import vn.actvn.entity.GioHang;
import vn.actvn.entity.SanPham;
import vn.actvn.service.DanhMucService;
import vn.actvn.service.SanPhamService;


@Controller
@RequestMapping("/chitiet")
@SessionAttributes("giohang")
public class ChitietController {
	
	@Autowired
	SanPhamService sanPhamService;
	
	@Autowired 
	DanhMucService danhMucService;
	
	@GetMapping("/{masanpham}")
	public String init (@PathVariable int masanpham,ModelMap modelMap,HttpSession httpSession) {
		SanPham sanPham = sanPhamService.LayDanhSachChiTietSanPhamTheoMa(masanpham);
		List<DanhMucSanPham> listDMSP = danhMucService.LayDanhMuc();
		modelMap.addAttribute("sanpham", sanPham);
		modelMap.addAttribute("danhmuc", listDMSP);
		List<GioHang> listGioHang = (List<GioHang>) httpSession.getAttribute("giohang");
		if(listGioHang!=null) {
			modelMap.addAttribute("soluonggiohang", listGioHang.size());
		}else {
			modelMap.addAttribute("soluonggiohang", 0);
		}
		return "chitiet";
	}
}
