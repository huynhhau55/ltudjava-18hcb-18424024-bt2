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
	public static  List<String> queryLopTKB(){
		String sql = "select distinct lop from ThoiKb;";
		Query query = entityManager.createQuery(sql);
		@SuppressWarnings("unchecked")
		List<String> _lop = query.getResultList();
		return _lop;
	}
	
	//KẾT THÚC -- QUẢN LÝ THỜI KHÓA BIỂU
	
	//BẮT ĐẦU -- QUẢN LÝ DANH SÁCH LỚP MÔN HỌC
	
	public static void createDanhSachLopMH(int stt, String ma_sv, String ho_ten, String gioi_tinh, String cmnd,String lop_mh, String ma_mh ) {
		
		DsLopMh lopMH = new DsLopMh();
		lopMH.setStt(stt);
		lopMH.setHo_ten(ho_ten);
		lopMH.setGioi_tinh(gioi_tinh);
		lopMH.setCmnd(cmnd);
		lopMH.setLop_mh(lop_mh);
		lopMH.setMa_sv(ma_sv);
		lopMH.setMa_mh(ma_mh);
		entityManager.persist(lopMH);
	}
	
	@SuppressWarnings("unchecked")
	public static List<ThoiKb> getLopMhAndMaMH(){
		String sql = "SELECT t FROM ThoiKb t";
		Query query = entityManager.createQuery(sql);
		return query.getResultList();
	}
	
	public static String getTenMH(String maMH)
	{
		ThoiKb tkb = find(maMH, ThoiKb.class);
		return tkb.getTen_mh();
			
	}
	@SuppressWarnings("unchecked")
	public static List<DsLopMh> loadDsLMH(String sql){
		Query query = entityManager.createQuery(sql);
		return query.getResultList();							
				
	}
	//KẾT THÚC -- QUẢN LÝ DANH SÁCH LỚP MÔN HỌC
	
	//BẮT ĐẦU -- QUẢN LÝ ĐIỂM
	
	public static void createBangDien(int stt, String ma_sv, String ho_ten, float diem_gk, float diem_ck, float diem_khac, float diem_tong, String ma_mh, String lop_mh) {
		
		Diem ds = new Diem();
		ds.setStt(stt);
		ds.setMa_sv(ma_sv);
		ds.setHo_ten(ho_ten);
		ds.setDiem_gk(diem_gk);
		ds.setDiem_ck(diem_ck);
		ds.setDiem_khac(diem_khac);
		ds.setDiem_tong(diem_tong);
		ds.setLop_mh(lop_mh);
		ds.setMa_mh(ma_mh);
		entityManager.persist(ds);
	}
	@SuppressWarnings("unchecked")
	public static List<Diem> getSTT() {
		
		String sql = "SELECT d FROM Diem d";
		Query query = entityManager.createQuery(sql);
		try
		{
			query.getResultList();
		}catch(Exception e)
		{
			return null;
		}
		return query.getResultList();
	}
	//KẾT THÚC -- QUẢN LÝ ĐIỂM
	
	public static void end() {
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
}
