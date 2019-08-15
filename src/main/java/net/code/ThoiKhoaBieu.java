package net.code;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "thoi_khoa_bieu")
public class ThoiKhoaBieu {

	private int stt;
	private String maMh;
	private String tenMh;
	private String phongHoc;
	private String lop;
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getStt() {
		return stt;
	}
	public void setStt(int stt) {
		this.stt = stt;
	}
	
	@Column(name = "ma_mh")
	@Id
	public String getMaMH() {
		return maMh;
	}
	public void setMaMH(String maMH) {
		this.maMh = maMH;
	}
	
	@Column(name = "ten_mh")
	public String getTenMH() {
		return tenMh;
	}
	public void setTenMH(String tenMH) {
		this.tenMh = tenMH;
	}
	
	@Column(name = "phong_hoc")
	public String getPhongHoc() {
		return phongHoc;
	}
	public void setPhongHoc(String phongHoc) {
		this.phongHoc = phongHoc;
	}
	
	public String getLop() {
		return lop;
	}
	public void setLop(String lop) {
		this.lop = lop;
	}
}
