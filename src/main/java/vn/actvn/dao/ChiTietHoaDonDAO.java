package vn.actvn.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.actvn.daoimp.ChiTietHoaDonImp;
import vn.actvn.entity.ChiTietHoaDon;
import vn.actvn.entity.ChiTietHoaDonId;

@Repository
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS)
public class ChiTietHoaDonDAO implements ChiTietHoaDonImp{

	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public boolean ThemChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
		Session session = sessionFactory.getCurrentSession();
		ChiTietHoaDonId id = (ChiTietHoaDonId) session.save(chiTietHoaDon);
		if(id != null) {
			return true;
		}else {
			return false;
		}
	}

	
}
