package vn.actvn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.actvn.dao.ChiTietHoaDonDAO;
import vn.actvn.daoimp.ChiTietHoaDonImp;
import vn.actvn.entity.ChiTietHoaDon;

@Service
public class ChiTietHoaDonService implements ChiTietHoaDonImp{

	@Autowired
	ChiTietHoaDonDAO chiTietHoaDonDAO;
	
	public boolean ThemChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
		return chiTietHoaDonDAO.ThemChiTietHoaDon(chiTietHoaDon);
	}

}
