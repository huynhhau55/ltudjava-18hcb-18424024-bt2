package net.code;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="diem")
public class Diem {

	private int stt;
	private String maSV;
	private float diemGK;
	private float diemCK;
	private float diemKhac;
	private float diemTong;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getStt() {
		return stt;
	}
	public void setStt(int stt) {
		this.stt = stt;
	}
	
	@Column(name = "mssv")
	@Id
	public String getMssv() {
		return maSV;
	}
	public void setMssv(String mssv) {
		this.maSV = mssv;
	}
	
	@Column(name = "diem_gk")
	public float getDiemGK() {
		return diemGK;
	}
	public void setDiemGK(float diemGK) {
		this.diemGK = diemGK;
	}
	
	@Column(name = "diem_ck")
	public float getDiemCK() {
		return diemCK;
	}
	public void setDiemCK(float diemCK) {
		this.diemCK = diemCK;
	}
	
	@Column(name = "diem_khac")
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
