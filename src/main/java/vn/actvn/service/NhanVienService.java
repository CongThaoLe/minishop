package vn.actvn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.actvn.dao.NhanVienDAO;
import vn.actvn.daoimp.NhanVienImp;
import vn.actvn.entity.NhanVien;

@Service
public class NhanVienService implements NhanVienImp{
	
	@Autowired
	NhanVienDAO nhanVienDAO;
	public boolean KiemTraDangNhap(String email, String matkhau) {
		
		return nhanVienDAO.KiemTraDangNhap(email, matkhau);
	}
	public boolean ThemNhanVien(NhanVien nhanVien) {
		boolean ktThem = nhanVienDAO.ThemNhanVien(nhanVien);
		return ktThem;
	}
}
