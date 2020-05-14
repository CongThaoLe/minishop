package vn.actvn.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import vn.actvn.daoimp.SizeSanPhamImp;
import vn.actvn.entity.MauSanPham;
import vn.actvn.entity.SizeSanPham;

@Repository
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS)
public class SizeSanPhamDAO implements SizeSanPhamImp{

	@Autowired SessionFactory sessionFactory;

	@Transactional
	public List<SizeSanPham> LayDanhSachSize() {
		Session session = sessionFactory.getCurrentSession();
		List<SizeSanPham> listSize = session.createQuery("from sizesanpham").getResultList();
		return listSize;
	}
	
	
}
