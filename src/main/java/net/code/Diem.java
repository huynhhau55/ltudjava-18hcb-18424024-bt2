package net.code;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "diem")
public class Diem {

	private int stt;
	private String ma_sv;
	private String ho_ten;
	private float diem_gk;
	private float diem_ck;
	private float diem_khac;
	private float diem_tong;
	private String ma_mh;
	private String lop_mh;
	
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
	public float getDiem_gk() {
		return diem_gk;
	}
	public void setDiem_gk(float diem_gk) {
		this.diem_gk = diem_gk;
	}
	public float getDiem_ck() {
		return diem_ck;
	}
	public void setDiem_ck(float diem_ck) {
		this.diem_ck = diem_ck;
	}
	public float getDiem_khac() {
		return diem_khac;
	}
	public void setDiem_khac(float diem_khac) {
		this.diem_khac = diem_khac;
	}
	public float getDiem_tong() {
		return diem_tong;
	}
	public void setDiem_tong(float diem_tong) {
		this.diem_tong = diem_tong;
	}
	
	public String getMa_mh() {
		return ma_mh;
	}
	public void setMa_mh(String ma_mh) {
		this.ma_mh = ma_mh;
	}
	public String getLop_mh() {
		return lop_mh;
	}
	public void setLop_mh(String lop_mh) {
		this.lop_mh = lop_mh;
	}
	
	
}
