package net.code;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "danh_sach_lop_mon_hoc")
public class DsLopMonHoc {

	private int stt;
	private String maSv;
	private String lopMh;
	public int getStt() {
		return stt;
	}
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public void setStt(int stt) {
		this.stt = stt;
	}
	
	@Column(name = "mssv")
	@Id
	public String getMssv() {
		return maSv;
	}
	public void setMssv(String mssv) {
		this.maSv = mssv;
	}
	
	@Column(name = "lop_mon_hoc")
	public String getLopMH() {
		return lopMh;
	}
	public void setLopMH(String lopMH) {
		this.lopMh = lopMH;
	}
}
