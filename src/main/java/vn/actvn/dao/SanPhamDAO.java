package vn.actvn.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.cj.Query;

import vn.actvn.daoimp.SanPhamImp;
import vn.actvn.entity.ChiTietHoaDon;
import vn.actvn.entity.ChiTietHoaDonId;
import vn.actvn.entity.ChiTietSanPham;
import vn.actvn.entity.SanPham;

@Repository
@Scope(proxyMode=ScopedProxyMode.TARGET_CLASS)
public class SanPhamDAO implements SanPhamImp {
	
	@Autowired SessionFactory sessionFactory;

	@Transactional
	public List<SanPham> LayDanhSachSanPhamLimit(int spbatdau) {
		Session session = sessionFactory.getCurrentSession();
		List<SanPham> listSanPham = new ArrayList<SanPham>();
		if(spbatdau<0) {
			listSanPham = session.createQuery("from sanpham").getResultList();
		}else {
			listSanPham = session.createQuery("from sanpham").setFirstResult(spbatdau).setMaxResults(5).getResultList();
		}
		return listSanPham;
	}
	
	@Transactional
	public SanPham LayDanhSachChiTietSanPhamTheoMa(int masanpham){
		Session session = sessionFactory.getCurrentSession();
		SanPham sanPham = (SanPham) session.createQuery("from sanpham where masanpham="+masanpham).getSingleResult();
		Set<ChiTietSanPham> list = sanPham.getChitietsanpham();
		for(ChiTietSanPham ctsp: list) {
			System.out.println(ctsp.getMausanpham().getTenmau());
			System.out.println(ctsp.getSizesanpham().getSize());
		}
		return sanPham;
	}

	@Transactional
	public List<SanPham> LaySanPhamTheoMaDanhMuc(int madanhmuc) {
		Session session = sessionFactory.getCurrentSession();
		List<SanPham> listSanPham = (List<SanPham>) session.createQuery("from sanpham sp where sp.danhMucSanPham.madanhmuc="+madanhmuc).getResultList();
		return listSanPham;
	}

	@Transactional
	public boolean XoaSanPhamTheoMaSanPham(int masp) {
		Session session = sessionFactory.getCurrentSession();
//		SanPham sanPham = new SanPham();
//		sanPham.setMasanpham(masp);
//		session.delete(sanPham);
		
		SanPham sanPham = session.get(SanPham.class, masp);
		
		Set<ChiTietSanPham> chiTietSanPhams = sanPham.getChitietsanpham();
		for(ChiTietSanPham chiTietSanPham : chiTietSanPhams) {
			session.createQuery("delete chitiethoadon where machitietsanpham = "+chiTietSanPham.getMachitietsanpham()).executeUpdate();
		}
		session.createQuery("delete chitietsanpham where masanpham = "+masp).executeUpdate();
//		session.createQuery("delete chitietkhuyenmai where masanpham = "+masp).executeUpdate();
		session.createQuery("delete sanpham where masanpham = "+masp).executeUpdate();
		return false;
	}

	@Transactional
	public boolean ThemSanPham(SanPham sanPham) {
		Session session = sessionFactory.getCurrentSession();
		int id = (Integer) session.save(sanPham);
		return false;
	}

	@Transactional
	public boolean CapNhatSanPham(SanPham sanPham) {
		Session session = sessionFactory.getCurrentSession();
		session.update(sanPham);
		return false;
	}

}
