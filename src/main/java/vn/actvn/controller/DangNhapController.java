package vn.actvn.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import vn.actvn.entity.NhanVien;
import vn.actvn.service.NhanVienService;

@Controller
@RequestMapping("dangnhap/")
@SessionAttributes("email")
public class DangNhapController {

	@Autowired
	NhanVienService nhanVienService;
	
	@GetMapping
	public String init() {
		return "dangnhap";
	}

	@PostMapping
	public String DangKy(@RequestParam String email, @RequestParam String matkhau,
			@RequestParam String nhaplaimatkhau,ModelMap modelMap) {
		boolean ktmail = validate(email);
		if(ktmail) {
			if(matkhau.equalsIgnoreCase(nhaplaimatkhau)) {
				NhanVien nhanVien = new NhanVien();
				nhanVien.setEmail(email);
				nhanVien.setTendangnhap(email);
				nhanVien.setMatkhau(matkhau);
				boolean ktThem = nhanVienService.ThemNhanVien(nhanVien);
				if(ktThem) {
					modelMap.addAttribute("kiemtradangnhap", "Tạo tài khoản thành công");
				}else {
					modelMap.addAttribute("kiemtradangnhap", "Tạo tài khoản thất bại");
				}
			}else {
				modelMap.addAttribute("kiemtradangnhap", "Xác nhận mật khẩu không chính xác");
			}
		}else {
			modelMap.addAttribute("kiemtradangnhap", "định dạng email không hợp lệ");
		}
		return "dangnhap";
	}

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	public static boolean validate(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
		return matcher.find();
	}
}
