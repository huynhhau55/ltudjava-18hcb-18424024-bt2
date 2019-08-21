package net.code;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ds_lop")
public class DsLopMh {
	
	private String ma_sv;
	private String ho_ten;
	private String gioi_tinh;
	private String cmnd;
	private String lop_mh;
	private String ma_mh;
	
	@Id
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
	public String getGioi_tinh() {
		return gioi_tinh;
	}
	public void setGioi_tinh(String gioi_tinh) {
		this.gioi_tinh = gioi_tinh;
	}
	public String getCmnd() {
		return cmnd;
	}
	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}
	public String getLop_mh() {
		return lop_mh;
	}
	public void setLop_mh(String lop_mh) {
		this.lop_mh = lop_mh;
	}
	public String getMa_mh() {
		return ma_mh;
	}
	public void setMa_mh(String ma_mh) {
		this.ma_mh = ma_mh;
	}
	
	
}
