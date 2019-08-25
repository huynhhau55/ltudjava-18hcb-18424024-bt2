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
	@SuppressWarnings("unchecked")
	public static List<DsLopMh> getSTT_DSLMH() {
		
		String sql = "SELECT d FROM DsLopMh d";
		Query query = entityManager.createQuery(sql);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public static List<DsLopMh> getDs(String ma_sv, String ma_mh, String lop_mh){
		
		String sql = "SELECT d FROM DsLopMh d WHERE d.ma_sv = '" +ma_sv+"' AND d.ma_mh = '"+ma_mh+"' AND d.lop_mh = '"+lop_mh+"'";
		Query query = entityManager.createQuery(sql);
		return query.getResultList();
	}
	
	public static void xoaSV(String ma_sv, String ma_mh, String lop_mh) {
		
		List <DsLopMh> ds = getDs(ma_sv, ma_mh, lop_mh);
		//int k = .getStt();
		//DsLopMh ds = entityManager.getReference(DsLopMh.class, k);
		entityManager.remove(ds.get(0));
		
		
		 
		    
		
		
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
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Diem> fillCBBDiem(String sql) {
		Query query = entityManager.createQuery(sql);
		return query.getResultList();
	}
	public static void capNhatDiem(String ma_sv, String ma_mh, String lop_mh, float diem_gk, float diem_ck, float diem_khac, float diem_tong) {
		String sql = "SELECT d FROM Diem d WHERE d.ma_sv = '" + ma_sv + "' AND d.ma_mh = '" + ma_mh + "' AND d.lop_mh = '" + lop_mh + "'" ;
		List<Diem> sv = fillCBBDiem(sql);
		Diem d = new Diem();
		d = sv.get(0);
		d.setDiem_gk(diem_gk);
		d.setDiem_ck(diem_ck);
		d.setDiem_khac(diem_khac);
		d.setDiem_tong(diem_tong);
		entityManager.merge(d);
		
	}
	//KẾT THÚC -- QUẢN LÝ ĐIỂM
	
	//BẮT ĐẦU -- QUẢN LÝ SINH VIÊN HỌC NHỮNG MÔN HỌC NÀO
	
	@SuppressWarnings("unchecked")
	public static List<Diem> dsMonHoc(String sql){
		Query query = entityManager.createQuery(sql);
		return query.getResultList();
		
	}
	//KẾT THÚC -- QUẢN LÝ SINH VIÊN HỌC NHỮNG MÔN HỌC NÀO
	
	//BẮT ĐẦU -- QUẢN LÝ PHÚC KHẢO
	
	public static void createThoiHanPK(int stt, int ngay_bd, int thang_bd, int nam_bd, int ngay_kt, int thang_kt, int nam_kt) {
		
		thoiHanPhucKhao th = new thoiHanPhucKhao();
		th.setStt(stt);
		th.setNgay_bd(ngay_bd);
		th.setThang_bd(thang_bd);
		th.setNam_bd(nam_bd);
		th.setNgay_kt(ngay_kt);
		th.setThang_kt(thang_kt);
		th.setNam_kt(nam_kt);
		entityManager.persist(th);
		
	}
	
	@SuppressWarnings("unchecked")
	public static List<thoiHanPhucKhao> getThoiHan() {
		
		String sql = "SELECT t FROM thoiHanPhucKhao t";
		Query query = entityManager.createQuery(sql);
		return query.getResultList();
	}
	
	public static void updateThoiHan(int ngay_bd, int thang_bd, int nam_bd, int ngay_kt, int thang_kt, int nam_kt) {
		
		
		thoiHanPhucKhao th = new thoiHanPhucKhao();
		th = getThoiHan().get(0);
		th.setNgay_bd(ngay_bd);
		th.setThang_bd(thang_bd);
		th.setNam_bd(nam_bd);
		th.setNgay_kt(ngay_kt);
		th.setThang_kt(thang_kt);
		th.setNam_kt(nam_kt);
		entityManager.merge(th);
		
	}
	@SuppressWarnings("unchecked")
	public static List<phucKhao> getPhucKhao() {
		
		String sql = "SELECT p FROM phucKhao p ";
		Query query = entityManager.createQuery(sql);
		return query.getResultList();
		
	}
	@SuppressWarnings("unchecked")
	public static List<phucKhao> getPhucKhao2(String ma_sv) {
		
		String sql = "SELECT p FROM phucKhao p WHERE p.ma_sv = '"+ ma_sv +"'";
		Query query = entityManager.createQuery(sql);
		return query.getResultList();
		
	}
	@SuppressWarnings("unchecked")
	public static List<phucKhao> getSttPhucKhao(String ma_sv, String mon_hoc) {
		
		String sql = "SELECT p FROM phucKhao p WHERE p.ma_sv = '"+ ma_sv +"' AND p.monhoc = '" + mon_hoc +"'";
		Query query = entityManager.createQuery(sql);
		return query.getResultList();
		
	}
	@SuppressWarnings("unchecked")
	public static List<phucKhao> getSttPhucKhao2() {
		
		String sql = "SELECT p FROM phucKhao p";
		Query query = entityManager.createQuery(sql);
		return query.getResultList();
		
	}
	public static void updatePhucKhao(String ma_sv, String ho_ten, String mon_hoc, 
			                         String cot_diem_phuc_khao, String diem_mong_muon, String li_do,String tinh_trang ) {
		phucKhao pk = new phucKhao();
		List<phucKhao> dsPhucKhao = getSttPhucKhao2();
		for(int i = 0 ; i < dsPhucKhao.size(); i++) {
			
			if((dsPhucKhao.get(i).getMa_sv().equalsIgnoreCase(ma_sv)) &&
				dsPhucKhao.get(i).getMon_hoc().equalsIgnoreCase(mon_hoc)) {
				pk = dsPhucKhao.get(i);
				break;
			}	
		}
		pk.setStt(pk.getStt());
		pk.setMa_sv(ma_sv);
		pk.setHo_ten(ho_ten);
		pk.setMon_hoc(mon_hoc);
		pk.setCot_diem_phuc_khao(cot_diem_phuc_khao);
		pk.setCot_diem_phuc_khao(diem_mong_muon);
		pk.setLi_do(li_do);
		pk.setTinh_trang(tinh_trang);
		entityManager.merge(pk);
		
	}
	public static void createPhucKhao(int stt, String ma_sv, String ho_ten, String mon_hoc, String cot_diem_phuc_khao,
							   float diem_mong_muon, String li_do ) {
		
		phucKhao pk = new phucKhao();
		pk.setStt(stt);
		pk.setMa_sv(ma_sv);
		pk.setHo_ten(ho_ten);
		pk.setMon_hoc(mon_hoc);
		pk.setCot_diem_phuc_khao(cot_diem_phuc_khao);
		pk.setDiem_mong_muon(diem_mong_muon);
		pk.setLi_do(li_do);
		pk.setTinh_trang("Chưa xem");
		entityManager.persist(pk);
	}
	//KẾT THÚC -- QUẢN LÝ PHÚC KHẢO
	
	
	public static void end() {
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}
}
