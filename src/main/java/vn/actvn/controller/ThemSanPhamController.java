package vn.actvn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.actvn.entity.DanhMucSanPham;
import vn.actvn.entity.MauSanPham;
import vn.actvn.entity.SanPham;
import vn.actvn.entity.SizeSanPham;
import vn.actvn.service.DanhMucService;
import vn.actvn.service.MauSanPhamService;
import vn.actvn.service.SanPhamService;
import vn.actvn.service.SizeSanPhamService;

@Controller
@RequestMapping("/themsanpham")
public class ThemSanPhamController {
	
	@Autowired 
	SanPhamService sanPhamService;
	
	@Autowired
	DanhMucService danhMucService;
	
	@Autowired
	SizeSanPhamService sizeSanPhamService;
	
	@Autowired
	MauSanPhamService mauSanPhamService;
	
	@GetMapping
	public String Default(ModelMap modelMap) {
		List<SanPham> listSanPham = sanPhamService.LayDanhSachSanPhamLimit(0);
		List<SanPham> allSanPham = sanPhamService.LayDanhSachSanPhamLimit(-1);
		List<DanhMucSanPham> listDMSP = danhMucService.LayDanhMuc();
		List<MauSanPham> listMau = mauSanPhamService.LayDanhSachMau();
		List<SizeSanPham> listSize = sizeSanPhamService.LayDanhSachSize();
		double tongsopage = Math.ceil((double) allSanPham.size()/5);
		modelMap.addAttribute("listsanpham", listSanPham);
		modelMap.addAttribute("allsanpham", allSanPham);
		modelMap.addAttribute("tongsopage", tongsopage);
		modelMap.addAttribute("danhmuc", listDMSP);
		modelMap.addAttribute("listmau", listMau);
		modelMap.addAttribute("listsize", listSize);
		return "themsanpham";
	}
}
