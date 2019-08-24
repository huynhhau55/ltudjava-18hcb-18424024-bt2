package net.code;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "phuc_khao")
public class PhucKhao {
	
	private int stt;
	private String ma_sv;
	private String ho_ten;
	private String mon_hoc;
	private String cot_diem_phuc_khao;
	private float diem_mong_muon;
	private String li_do;
	private String tinh_trang;
	private int ngay;
	private int thang;
	private int nam;
	
	@Id 
	public int getStt() {
		return stt;
	}
	public void setStt(int stt) {
		this.stt = stt;
	}
	public String getMa_sv() {
		return ma_sv;
	}
	public void setMa_sv(String ma_sv) {
		this.ma_sv = ma_sv;
	}
	public String getHo_ten() {
		return ho_ten;
	}
	public void setHo_ten(String ho_ten) {
		this.ho_ten = ho_ten;
	}
	public String getMon_hoc() {
		return mon_hoc;
	}
	public void setMon_hoc(String mon_hoc) {
		this.mon_hoc = mon_hoc;
	}
	public String getCot_diem_phuc_khao() {
		return cot_diem_phuc_khao;
	}
	public void setCot_diem_phuc_khao(String cot_diem_phuc_khao) {
		this.cot_diem_phuc_khao = cot_diem_phuc_khao;
	}
	public float getDiem_mong_muon() {
		return diem_mong_muon;
	}
	public void setDiem_mong_muon(float diem_mong_muon) {
		this.diem_mong_muon = diem_mong_muon;
	}
	public String getLi_do() {
		return li_do;
	}
	public void setLi_do(String li_do) {
		this.li_do = li_do;
	}
	public int getNgay() {
		return ngay;
	}
	public void setNgay(int ngay) {
		this.ngay = ngay;
	}
	public int getThang() {
		return thang;
	}
	public void setThang(int thang) {
		this.thang = thang;
	}
	public int getNam() {
		return nam;
	}
	public void setNam(int nam) {
		this.nam = nam;
	}
	public String getTinh_trang() {
		return tinh_trang;
	}
	public void setTinh_trang(String tinh_trang) {
		this.tinh_trang = tinh_trang;
	}
	
	

}
