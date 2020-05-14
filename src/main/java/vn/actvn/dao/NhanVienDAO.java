package vn.actvn.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.actvn.daoimp.NhanVienImp;
import vn.actvn.entity.NhanVien;

@Repository
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS)
public class NhanVienDAO implements NhanVienImp{
	
	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public boolean KiemTraDangNhap(String email, String matkhau) {
		Session session = sessionFactory.getCurrentSession();
		try {
			NhanVien nhanVien = (NhanVien) session.createQuery("from nhanvien where email='"+email+"' and matkhau='"+matkhau+"'").getSingleResult();
			if(nhanVien!=null) {
				return true;
			}
		}catch (Exception e) {
			return false;
		}
		return false;
	}

	@Transactional
	public boolean ThemNhanVien(NhanVien nhanVien) {
		Session session = sessionFactory.getCurrentSession();
		int manv = (Integer) session.save(nhanVien);
		if(manv>0) {
			return true;
		}else {
			return false;
		}
	}
	
}
