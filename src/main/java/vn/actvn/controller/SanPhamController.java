package vn.actvn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.actvn.entity.DanhMucSanPham;
import vn.actvn.entity.SanPham;
import vn.actvn.service.DanhMucService;
import vn.actvn.service.SanPhamService;

@Controller
@RequestMapping("sanpham/")
public class SanPhamController {
	
	@Autowired 
	DanhMucService danhMucService;
	
	@Autowired 
	SanPhamService 
	sanPhamService;
	
	@GetMapping("{id}/{tendanhmuc}")
	public String Default(ModelMap modelMap,@PathVariable int id,@PathVariable String tendanhmuc) {
		
		List<SanPham> listSanPham = sanPhamService.LaySanPhamTheoMaDanhMuc(id);
		
		List<DanhMucSanPham> listDMSP = danhMucService.LayDanhMuc();
		modelMap.addAttribute("danhmuc", listDMSP);
		modelMap.addAttribute("listSanPham", listSanPham);
		modelMap.addAttribute("tendanhmuc", tendanhmuc);
		return "sanpham";
	}
}
