package net.code;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "thoi_khoa_bieu")
public class ThoiKhoaBieu {

	private String maMh;
	private String tenMh;
	private String phongHoc;
	
	@Column(name = "ma_mh")
	@Id
	public String getMaMh() {
		return maMh;
	}
	public void setMaMh(String maMh) {
		this.maMh = maMh;
	}
	
	@Column(name = "ten_mh")
	public String getTenMh() {
		return tenMh;
	}
	public void setTenMh(String tenMh) {
		this.tenMh = tenMh;
	}
	
	@Column(name = "phong_hoc")
	public String getPhongHoc() {
		return phongHoc;
	}
	public void setPhongHoc(String phongHoc) {
		this.phongHoc = phongHoc;
	}
	
	
}
