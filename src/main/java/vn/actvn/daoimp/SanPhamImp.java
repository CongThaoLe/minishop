package vn.actvn.daoimp;

import java.util.List;

import vn.actvn.entity.SanPham;

public interface SanPhamImp {
	List<SanPham> LayDanhSachSanPhamLimit(int spbatdau);
	SanPham LayDanhSachChiTietSanPhamTheoMa(int masanpham);
	List<SanPham> LaySanPhamTheoMaDanhMuc(int madanhmuc);
	boolean XoaSanPhamTheoMaSanPham(int masp);
	boolean ThemSanPham(SanPham sanPham);
	boolean CapNhatSanPham(SanPham sanPham);
}
