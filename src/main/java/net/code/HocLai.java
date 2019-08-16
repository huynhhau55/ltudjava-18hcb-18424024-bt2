package net.code;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hoc_lai")
public class HocLai {

	private String maSv;
	private String maMh;
	private float diemGk;
	private float diemCk;
	private float diemKhac;
	private float diemTong;
	
	@Column(name = "ma_sv")
	@Id
	public String getMaSv() {
		return maSv;
	}
	public void setMaSv(String maSv) {
		this.maSv = maSv;
	}
	
	@Column(name = "ma_mh")
	@Id
	public String getMaMh() {
		return maMh;
	}
	public void setMaMh(String maMh) {
		this.maMh = maMh;
	}
	
	@Column(name = "diem_gk")
	public float getDiemGk() {
		return diemGk;
	}
	public void setDiemGk(float diemGk) {
		this.diemGk = diemGk;
	}
	
	@Column(name="diem_ck")
	public float getDiemCk() {
		return diemCk;
	}
	public void setDiemCk(float diemCk) {
		this.diemCk = diemCk;
	}
	
	@Column(name ="diem_khac")
	public float getDiemKhac() {
		return diemKhac;
	}
	public void setDiemKhac(float diemKhac) {
		this.diemKhac = diemKhac;
	}
	
	@Column(name = "diem_tong")
	public float getDiemTong() {
		return diemTong;
	}
	public void setDiemTong(float diemTong) {
		this.diemTong = diemTong;
	}
	
	
}
