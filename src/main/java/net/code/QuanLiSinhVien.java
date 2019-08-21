package net.code;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class QuanLiSinhVien {

	 static EntityManagerFactory factory;
	 static EntityManager entityManager;
	/*public static void main(String[] args) {
		
		
		try {
			LoginForm window = new LoginForm();
			window.frmLogin.setLocationRelativeTo(null);
			window.frmLogin.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			end();
		}
		
		
		
		
	}*/


	

	
	public static void begin() {
			factory = Persistence.createEntityManagerFactory("quanlisinhvienUnit");
			entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();
		}
	//BẮT ĐẦU -- QUẢN LÝ DANH SÁCH LỚP
	public static <T> T find(String primaryKey, Class<T> _class) {
		T lop = entityManager.find(_class, primaryKey);
		return lop;
	}
	public static void createDSLop(String maSV, String hoTen, String gioiTinh, String cmnd, String lop) {
		DsLop dsLop= new DsLop();
		dsLop.setMa_sv(maSV);
		dsLop.setHo_ten(hoTen);
		dsLop.setGioi_tinh(gioiTinh);
		dsLop.setCmnd(cmnd);
		dsLop.setLop(lop);
		dsLop.setMat_khau(maSV);
		entityManager.persist(dsLop);
	}
	
	public static void update(String maSV, String hoTen,String gioiTinh,String cmnd,String lop) {
		//String matKhau = null;
		DsLop existLop = new DsLop();
		existLop = find(maSV,DsLop.class);
		//matKhau = existLop.getMat_khau();
		existLop.setMa_sv(maSV);
		existLop.setHo_ten(hoTen);
		existLop.setGioi_tinh(gioiTinh);
		existLop.setCmnd(cmnd);
		existLop.setLop(lop);
		entityManager.merge(existLop);	
		
		}
	public static void changePassword(String maSV, String matKhau) {
		
		DsLop existLop = new DsLop();
		existLop.setMa_sv(maSV);
		existLop.setMat_khau(matKhau);
		entityManager.merge(existLop);	
		
		}
	
	public static void removeStudent(String maSv) {	
		EntityManagerFactory entityManagerFactory2 = Persistence.createEntityManagerFactory("quanlisinhvienUnit");
		EntityManager entityManager2 = entityManagerFactory2.createEntityManager();
		EntityTransaction entityTransaction = null;
		try {
			entityTransaction = entityManager2.getTransaction();
			entityTransaction.begin();
			
			DsLop sinhvien = entityManager2.find(DsLop.class,maSv);
			entityManager2.remove(sinhvien);
			entityTransaction.commit();
			
		}catch(RuntimeException e) {
			if(entityTransaction.isActive()) {
				entityTransaction.rollback();
				throw e;
			}
		}
		
	}
	public static  List<String> query(){
		String sql = "select distinct lop from DsLop;";
		Query query = entityManager.createQuery(sql);
		@SuppressWarnings("unchecked")
		List<String> _lop = query.getResultList();
		return _lop;
		
	}
	@SuppressWarnings("unchecked")
	public static  List<DsLop> queryGenetic(String sql){
		
		Query query = entityManager.createQuery(sql);
		List<DsLop> _object = query.getResultList();
		return _object;
		
	}
	//KẾT THÚC -- QUẢN LÝ DANH SÁCH LỚP
	
	//BẮT ĐẦU -- QUẢN LÝ THỜI KHÓA BIỂU
	
	public static void createThoiKB(String ma_mh, String ten_mh, String phong_hoc, String lop) {
		
		ThoiKb thoiKB= new ThoiKb();
		thoiKB.setMa_mh(ma_mh);
		thoiKB.setTen_mh(ten_mh);
		thoiKB.setPhong_hoc(phong_hoc);
		thoiKB.setLop(lop);
		entityManager.persist(thoiKB);
		
	}
	
	public static  List<ThoiKb> queryTKB(String sql ){
		
		Query query = entityManager.createQuery(sql);
		@SuppressWarnings("unchecked")
		List<ThoiKb> tkb = query.getResultList();
		return tkb;
		
	}
	
	
	//KẾT THÚC -- QUẢN LÝ THỜI KHÓA BIỂU
	
	//BẮT ĐẦU -- QUẢN LÝ DANH SÁCH LỚP MÔN HỌC
	//KẾT THÚC -- QUẢN LÝ DANH SÁCH LỚP MÔN HỌC
	
	//BẮT ĐẦU -- QUẢN LÝ ĐIỂM
	//KẾT THÚC -- QUẢN LÝ ĐIỂM
	
	public static void end() {
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
}
