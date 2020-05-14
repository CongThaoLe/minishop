package vn.actvn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.actvn.dao.SanPhamDAO;
import vn.actvn.daoimp.SanPhamImp;
import vn.actvn.entity.SanPham;

@Service
public class SanPhamService implements SanPhamImp{

	@Autowired 
	SanPhamDAO sanPhamDAO;
	
	public List<SanPham> LayDanhSachSanPhamLimit(int spbatdau) {
		
		return sanPhamDAO.LayDanhSachSanPhamLimit(spbatdau);
	}

	public SanPham LayDanhSachChiTietSanPhamTheoMa(int masanpham) {
		
		return sanPhamDAO.LayDanhSachChiTietSanPhamTheoMa(masanpham);
	}

	public List<SanPham> LaySanPhamTheoMaDanhMuc(int madanhmuc) {
		return sanPhamDAO.LaySanPhamTheoMaDanhMuc(madanhmuc);
	}

	public boolean XoaSanPhamTheoMaSanPham(int masp) {
		sanPhamDAO.XoaSanPhamTheoMaSanPham(masp);
		return false;
	}

	public boolean ThemSanPham(SanPham sanPham) {
		return sanPhamDAO.ThemSanPham(sanPham);
	}

	public boolean CapNhatSanPham(SanPham sanPham) {
		return sanPhamDAO.CapNhatSanPham(sanPham);
	}

}
