package net.code;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "thoi_han_pk")
public class thoiHanPhucKhao {

	private int stt;
	private int ngay_bd;
	private int thang_bd;
	private int nam_bd;
	private int ngay_kt;
	private int thang_kt;
	private int nam_kt;
	
	
	@Column(name = "stt")
	@Id
	public int getStt() {
		return stt;
	}
	public void setStt(int stt) {
		this.stt = stt;
	}
	public int getNgay_bd() {
		return ngay_bd;
	}
	public void setNgay_bd(int ngay_bd) {
		this.ngay_bd = ngay_bd;
	}
	public int getThang_bd() {
		return thang_bd;
	}
	public void setThang_bd(int thang_bd) {
		this.thang_bd = thang_bd;
	}
	public int getNam_bd() {
		return nam_bd;
	}
	public void setNam_bd(int nam_bd) {
		this.nam_bd = nam_bd;
	}
	public int getNgay_kt() {
		return ngay_kt;
	}
	public void setNgay_kt(int ngay_kt) {
		this.ngay_kt = ngay_kt;
	}
	public int getThang_kt() {
		return thang_kt;
	}
	public void setThang_kt(int thang_kt) {
		this.thang_kt = thang_kt;
	}
	public int getNam_kt() {
		return nam_kt;
	}
	public void setNam_kt(int nam_kt) {
		this.nam_kt = nam_kt;
	}
	
	
}
