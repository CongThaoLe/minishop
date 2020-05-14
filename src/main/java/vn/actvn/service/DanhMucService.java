package vn.actvn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.actvn.dao.DanhMucDAO;
import vn.actvn.daoimp.DanhMucImp;
import vn.actvn.entity.DanhMucSanPham;

@Service
public class DanhMucService implements DanhMucImp{

	@Autowired DanhMucDAO danhMucDAO;
	
	public List<DanhMucSanPham> LayDanhMuc() {
		return danhMucDAO.LayDanhMuc();
	}

}
