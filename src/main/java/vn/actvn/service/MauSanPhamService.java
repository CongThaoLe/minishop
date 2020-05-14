package vn.actvn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.actvn.dao.MauSanPhamDAO;
import vn.actvn.daoimp.MauSanPhamImp;
import vn.actvn.entity.MauSanPham;

@Service
public class MauSanPhamService implements MauSanPhamImp {
	
	@Autowired
	MauSanPhamDAO mauSanPhamDao;

	public List<MauSanPham> LayDanhSachMau() {
		return mauSanPhamDao.LayDanhSachMau();
	}

}
