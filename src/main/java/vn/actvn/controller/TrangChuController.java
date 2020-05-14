 package vn.actvn.controller;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import vn.actvn.entity.DanhMucSanPham;
import vn.actvn.entity.SanPham;
import vn.actvn.service.DanhMucService;
import vn.actvn.service.SanPhamService;

@Controller
@RequestMapping(path="/")
public class TrangChuController {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Autowired
	SanPhamService sanPhamService;
	
	@Autowired 
	DanhMucService danhMucService;
	
	@GetMapping
	@Transactional
	public String Trangchu(ModelMap modelMap,HttpSession httpSession) {
		
		List<DanhMucSanPham> listDMSP = danhMucService.LayDanhMuc();
		modelMap.addAttribute("danhmuc", listDMSP);
		if(httpSession.getAttribute("user") != null) {
			String email = (String) httpSession.getAttribute("user");
			String chucaidau = email.substring(0,1).toUpperCase();
			modelMap.addAttribute("chucaidau", chucaidau);
		}
		List<SanPham> listSanPham = sanPhamService.LayDanhSachSanPhamLimit(-1);
		for(SanPham sp : listSanPham) {
			System.out.println(sp.getTensanpham());
		}
		modelMap.addAttribute("listSanPham", listSanPham);
		return "trangchu";
	}
}	
