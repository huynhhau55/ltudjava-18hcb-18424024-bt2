package net.code;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "thoi_khoa bieu")
public class ThoiKb {

	private String ma_mh;
	private String ten_mh;
	private String phong_hoc;
	private String lop;
	
	@Id
	public String getMa_mh() {
		return ma_mh;
	}
	public void setMa_mh(String ma_mh) {
		this.ma_mh = ma_mh;
	}
	public String getTen_mh() {
		return ten_mh;
	}
	public void setTen_mh(String ten_mh) {
		this.ten_mh = ten_mh;
	}
	public String getPhong_hoc() {
		return phong_hoc;
	}
	public void setPhong_hoc(String phong_hoc) {
		this.phong_hoc = phong_hoc;
	}
	public String getLop() {
		return lop;
	}
	public void setLop(String lop) {
		this.lop = lop;
	}
	
	
}
