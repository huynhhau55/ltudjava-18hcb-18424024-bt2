package net.code;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ds_lop")
public class DsLop {

	private String ma_sv;
	private String ho_ten;
	private String gioi_tinh;
	private String cmnd;
	private String lop;
	private String mat_khau;
	
	@Id
	@Column(name = "ma_sv")
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
	public String getLop() {
		return lop;
	}
	public void setLop(String lop) {
		this.lop = lop;
	}
	public String getMat_khau() {
		return mat_khau;
	}
	public void setMat_khau(String mat_khau) {
		this.mat_khau = mat_khau;
	}
	
}
