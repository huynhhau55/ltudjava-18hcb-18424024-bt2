package net.code;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ds_lop")
public class DsLop {

	private int stt;
	private String maSv;
	private String hoTen;
	private String gioiTinh;
	private String cmnd;
	private String lop;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getStt() {
		return stt;
	}
	public void setStt(int stt) {
		this.stt = stt;
	}
	
	@Column(name = "mssv")
	@Id
	public String getMaSV() {
		return maSv;
	}
	public void setMaSV(String maSV) {
		this.maSv = maSV;
	}
	
	@Column(name = "ho_ten")
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	
	@Column(name = "gioi_tinh")
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	
	public String getcMND() {
		return cmnd;
	}
	public void setcMND(String cMND) {
		this.cmnd = cMND;
	}
	public String getLop() {
		return lop;
	}
	public void setLop(String lop) {
		this.lop = lop;
	}
}