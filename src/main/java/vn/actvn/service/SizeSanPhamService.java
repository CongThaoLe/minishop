package vn.actvn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.actvn.dao.SizeSanPhamDAO;
import vn.actvn.daoimp.SizeSanPhamImp;
import vn.actvn.entity.SizeSanPham;

@Service
public class SizeSanPhamService implements SizeSanPhamImp{
	
	@Autowired
	SizeSanPhamDAO sizeSanPhamDAO;

	public List<SizeSanPham> LayDanhSachSize() {
		return sizeSanPhamDAO.LayDanhSachSize();
	}

}
