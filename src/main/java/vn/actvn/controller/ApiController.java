package vn.actvn.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import vn.actvn.entity.ChiTietSanPham;
import vn.actvn.entity.DanhMucSanPham;
import vn.actvn.entity.GioHang;
import vn.actvn.entity.JSON_SanPham;
import vn.actvn.entity.MauSanPham;
import vn.actvn.entity.SanPham;
import vn.actvn.entity.SizeSanPham;
import vn.actvn.service.NhanVienService;
import vn.actvn.service.SanPhamService;

@Controller
@RequestMapping("api/")
@SessionAttributes({"user","giohang"})
public class ApiController {
	
	@Autowired
	NhanVienService nhanVienService;
	
	@Autowired
	SanPhamService sanPhamService;
	
	@GetMapping("KiemTraDangNhap")
	@ResponseBody
	public String KiemTraDangNhap(@RequestParam String email,@RequestParam String matkhau,ModelMap modelMap) {
		boolean kiemtra = nhanVienService.KiemTraDangNhap(email, matkhau);
		modelMap.addAttribute("user",email);
		return ""+kiemtra;
	}
	
	@GetMapping("CapNhatGioHang")
	@ResponseBody
	public void CapNhatGioHang(HttpSession httpSession,@RequestParam int soluong,@RequestParam int masp,int mamau,@RequestParam int masize) {
		if(httpSession.getAttribute("giohang")!=null) {
			List<GioHang> listGioHang = (List<GioHang>) httpSession.getAttribute("giohang");
			int vitri = KiemTraSanPhamDaTonTaiTrongGioHang(httpSession, masp, masize, mamau);
			listGioHang.get(vitri).setSoluong(soluong);
		}
	}
	
	@GetMapping("XoaGioHang")
	@ResponseBody
	public void XoaGioHang(HttpSession httpSession,@RequestParam int masp,@RequestParam int mamau,@RequestParam int masize) {
		if(null != httpSession.getAttribute("giohang")) {
			List<GioHang> listGioHang = (List<GioHang>) httpSession.getAttribute("giohang");
			int vitri = (int) KiemTraSanPhamDaTonTaiTrongGioHang(httpSession, masp, masize, mamau);
			listGioHang.remove(vitri);
		}
	}
	
	@GetMapping("ThemGioHang")
	@ResponseBody
	public void ThemGioHang(@ModelAttribute GioHang gioHang,HttpSession httpSession) {
		if(httpSession.getAttribute("giohang")==null) {
			List<GioHang> listGioHang = new ArrayList<GioHang>();
			gioHang.setSoluong(1);
			listGioHang.add(gioHang);
			httpSession.setAttribute("giohang", listGioHang);
			List<GioHang> list = (List<GioHang>) httpSession.getAttribute("giohang");
			for(GioHang gh : list) {
				System.out.println(gh.getMachitiet());
			}
		}else {
			 int vitri = KiemTraSanPhamDaTonTaiTrongGioHang(httpSession, gioHang.getMasp(), gioHang.getMasize(), gioHang.getMamau());
			 if(vitri == -1) {
				 List<GioHang> listGioHang = (List<GioHang>) httpSession.getAttribute("giohang");
				 gioHang.setSoluong(1);
				 listGioHang.add(gioHang);
				 List<GioHang> list = (List<GioHang>) httpSession.getAttribute("giohang");
			 }else {
				 List<GioHang> listGioHang = (List<GioHang>) httpSession.getAttribute("giohang");
				 int soluongmoi = listGioHang.get(vitri).getSoluong()+1;
				 listGioHang.get(vitri).setSoluong(soluongmoi);
				 List<GioHang> list = (List<GioHang>) httpSession.getAttribute("giohang");
			 }
		}
//		List<GioHang> list = (List<GioHang>) httpSession.getAttribute("giohang");
	}
	
	private int KiemTraSanPhamDaTonTaiTrongGioHang(HttpSession httpSession,int masp,int masize,int mamau) {
		List<GioHang> listGioHang = (List<GioHang>) httpSession.getAttribute("giohang");
		for(int i=0;i<listGioHang.size();i++){
			if(listGioHang.get(i).getMasp() == masp && listGioHang.get(i).getMasize() == masize && listGioHang.get(i).getMamau() == mamau) {
				return i;
			}
		}
		return -1;
	}
	
	@GetMapping("LaySoLuongGioHang")
	@ResponseBody
	public String LaySoLuongGioHang(HttpSession httpSession) {
		if(httpSession.getAttribute("giohang")!=null) {
			List<GioHang> listGioHang = (List<GioHang>) httpSession.getAttribute("giohang");
			return listGioHang.size()+"";
		}
		return "";
	}
	
	@GetMapping(path="LaySanPhamLimit",produces="text/plain;charset=utf-8")
	@ResponseBody
	public String LaySanPhamLimit(@RequestParam int sanphambatdau,ModelMap modelMap) {
		String html = "";
		List<SanPham> listSanPham = sanPhamService.LayDanhSachSanPhamLimit(sanphambatdau);
		for(SanPham sp : listSanPham) {
			html += "<tr>";
			html += "<td>\r\n" + 
					"								<input class=\"form-check-input checkbox-sanpham\" type=\"checkbox\" value=\" "+sp.getMasanpham()+" \" id=\"defaultCheck1\">\r\n" + 
					"								 <label class=\"form-check-label\" for=\"defaultCheck1\">\r\n" + 
					"									    Chọn\r\n" + 
					"								 </label>\r\n" + 
					"							</td>";
			html += "<td class=\"sanpham\" data-masp="+sp.getTensanpham()+">" + sp.getTensanpham() + "</td>\r\n" + 
					"							<td class="+sp.getGiatien()+">"+sp.getGiatien()+"</td>\r\n" + 
					"							<td class=\"gianhcho\">"+sp.getGianhcho()+"</td>"+
					"<td style=\"color: white;\" class=\"btn btn-primary capnhatsanpham\" data-id=\" "+sp.getMasanpham()+" \">Sửa</td>";
			
			html += "</tr>";
		};
		return html;
	}
	
	@GetMapping("xoasanpham")
	@ResponseBody
	public String XoaSanPhamTheoMaSanPham(@RequestParam int masp) {
		System.out.println(masp);
		sanPhamService.XoaSanPhamTheoMaSanPham(masp);
		return "";
	}
	
	@Autowired
	ServletContext context;
	
	@PostMapping("UploadFile")
	@ResponseBody
	public String XoaSanPhamTheoMaSanPham(MultipartHttpServletRequest request) {
		String path_save_file = context.getRealPath("/resources/image/SanPham/");
		Iterator<String> listNames = request.getFileNames();
		MultipartFile multipartFile = request.getFile(listNames.next());
		File file_save = new File(path_save_file + multipartFile.getOriginalFilename());
		try {
			multipartFile.transferTo(file_save);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(path_save_file);
		return "";
	}
	
	@PostMapping("themsanpham")
	@ResponseBody
	public void ThemSanPham(@RequestParam String dataJson) {
		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode jsonObject;
		try {
			SanPham sanPham = new SanPham();
			jsonObject = objectMapper.readTree(dataJson);
			
			DanhMucSanPham danhMucSanPham = new DanhMucSanPham();
			danhMucSanPham.setMadanhmuc(jsonObject.get("danhMucSanPham").asInt());
			System.out.println(jsonObject.get("danhMucSanPham").asInt());
			
			JsonNode jsonChitiet = jsonObject.get("chitietsanpham");
			Set<ChiTietSanPham> listChiTiet = new HashSet<ChiTietSanPham>();
			for (JsonNode objectChitiet : jsonChitiet) {
				ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
				
				MauSanPham mausanpham = new MauSanPham();
				mausanpham.setMamau(objectChitiet.get("mausanpham").asInt());
				
				SizeSanPham sizeSanPham = new SizeSanPham();
				sizeSanPham.setMasize(objectChitiet.get("sizesanpham").asInt());
				
				chiTietSanPham.setMausanpham(mausanpham);
				chiTietSanPham.setSizesanpham(sizeSanPham);
				chiTietSanPham.setSoluong(objectChitiet.get("soluong").asInt());
				
				listChiTiet.add(chiTietSanPham);
			}
			
			String tensanpham = jsonObject.get("tensanpham").asText();
			String giatien = jsonObject.get("giatien").asText();
			String mota = jsonObject.get("mota").asText();
			String hinhsanpham = jsonObject.get("hinhsanpham").asText();
			String gianhcho = jsonObject.get("gianhcho").asText();
			
			sanPham.setChitietsanpham(listChiTiet);
			sanPham.setDanhMucSanPham(danhMucSanPham);
			sanPham.setTensanpham(tensanpham);
			sanPham.setGiatien(giatien);
			sanPham.setMota(mota);
			sanPham.setHinhsanpham(hinhsanpham);
			sanPham.setGianhcho(gianhcho);
			
			sanPhamService.ThemSanPham(sanPham);
			
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping("capnhatsanpham")
	@ResponseBody
	public void CapNhatSanPham(@RequestParam String dataJson) {
		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode jsonObject;
		try {
			SanPham sanPham = new SanPham();
			jsonObject = objectMapper.readTree(dataJson);
			
			DanhMucSanPham danhMucSanPham = new DanhMucSanPham();
			danhMucSanPham.setMadanhmuc(jsonObject.get("danhMucSanPham").asInt());
			System.out.println(jsonObject.get("danhMucSanPham").asInt());
			
			JsonNode jsonChitiet = jsonObject.get("chitietsanpham");
			Set<ChiTietSanPham> listChiTiet = new HashSet<ChiTietSanPham>();
			for (JsonNode objectChitiet : jsonChitiet) {
				ChiTietSanPham chiTietSanPham = new ChiTietSanPham();
				
				MauSanPham mausanpham = new MauSanPham();
				mausanpham.setMamau(objectChitiet.get("mausanpham").asInt());
				
				SizeSanPham sizeSanPham = new SizeSanPham();
				sizeSanPham.setMasize(objectChitiet.get("sizesanpham").asInt());
				
				chiTietSanPham.setMausanpham(mausanpham);
				chiTietSanPham.setSizesanpham(sizeSanPham);
				chiTietSanPham.setSoluong(objectChitiet.get("soluong").asInt());
				
				listChiTiet.add(chiTietSanPham);
			}
			
			String tensanpham = jsonObject.get("tensanpham").asText();
			String giatien = jsonObject.get("giatien").asText();
			String mota = jsonObject.get("mota").asText();
			String hinhsanpham = jsonObject.get("hinhsanpham").asText();
			String gianhcho = jsonObject.get("gianhcho").asText();
			int masanpham = jsonObject.get("masanpham").asInt();
			
			sanPham.setChitietsanpham(listChiTiet);
			sanPham.setDanhMucSanPham(danhMucSanPham);
			sanPham.setTensanpham(tensanpham);
			sanPham.setGiatien(giatien);
			sanPham.setMota(mota);
			sanPham.setHinhsanpham(hinhsanpham);
			sanPham.setGianhcho(gianhcho);
			sanPham.setMasanpham(masanpham);
			
			sanPhamService.CapNhatSanPham(sanPham);
			
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@PostMapping(path = "LayDanhSachSanPhamTheoMa",produces="application/json;charset=utf-8")
	@ResponseBody
	public JSON_SanPham LayDanhSachSanPhamTheoMa(@RequestParam int masanpham){
		JSON_SanPham json_SanPham = new JSON_SanPham();
		SanPham sanPham = sanPhamService.LayDanhSachChiTietSanPhamTheoMa(masanpham);
		
		json_SanPham.setMasanpham(sanPham.getMasanpham());
		json_SanPham.setTensanpham(sanPham.getTensanpham());
		json_SanPham.setGiatien(sanPham.getGiatien());
		json_SanPham.setMota(sanPham.getMota());
		json_SanPham.setHinhsanpham(sanPham.getHinhsanpham());
		json_SanPham.setGianhcho(sanPham.getGianhcho());
		
		DanhMucSanPham danhMucSanPham = new DanhMucSanPham();
		danhMucSanPham.setMadanhmuc(sanPham.getDanhMucSanPham().getMadanhmuc());
		danhMucSanPham.setTendanhmuc(sanPham.getDanhMucSanPham().getTendanhmuc());
		
		Set<ChiTietSanPham> chiTietSanPhams = new HashSet<ChiTietSanPham>();
		for(ChiTietSanPham value : sanPham.getChitietsanpham()) {
			ChiTietSanPham  chiTietSanPham = new ChiTietSanPham();
			chiTietSanPham.setMachitietsanpham(value.getMachitietsanpham());
			
			MauSanPham mauSanPham = new MauSanPham();
			mauSanPham.setMamau(value.getMausanpham().getMamau());
			mauSanPham.setTenmau(value.getMausanpham().getTenmau());
			
			chiTietSanPham.setMausanpham(mauSanPham);
			
			SizeSanPham sizeSanPham = new SizeSanPham();
			sizeSanPham.setMasize(value.getSizesanpham().getMasize());
			sizeSanPham.setSize(value.getSizesanpham().getSize());
			
			chiTietSanPham.setSizesanpham(sizeSanPham);
			
			chiTietSanPham.setSoluong(value.getSoluong());
			
			chiTietSanPhams.add(chiTietSanPham);
		}
		
		json_SanPham.setDanhMucSanPham(danhMucSanPham);
		
		json_SanPham.setChitietsanpham(chiTietSanPhams);
		
		return json_SanPham;
	}
}
