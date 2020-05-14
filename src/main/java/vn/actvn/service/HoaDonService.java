package vn.actvn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.actvn.dao.HoaDonDAO;
import vn.actvn.daoimp.HoaDonImp;
import vn.actvn.entity.HoaDon;

@Service
public class HoaDonService implements HoaDonImp {

	@Autowired
	HoaDonDAO hoaDonDAO;

	public int ThemHoaDon(HoaDon hoaDon) {
		return hoaDonDAO.ThemHoaDon(hoaDon);
	}
}
