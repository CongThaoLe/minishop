package vn.actvn.daoimp;

import vn.actvn.entity.NhanVien;

public interface NhanVienImp {
	boolean KiemTraDangNhap(String email,String matkhau);
	boolean ThemNhanVien(NhanVien nhanVien);
}
