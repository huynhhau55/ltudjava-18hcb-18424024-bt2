package net.code;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sinh_vien")
public class SinhVien {
	
	private String maSv;
	private String hoTen;
	private String gioiTinh;
	private String cmnd;
	private String matKhau;
	private Lop lop;
	
	public SinhVien() {
	}
	public SinhVien(String maSv, String hoTen, String gioiTinh, String cmnd,String matKhau, Lop lop) {
		this.maSv = maSv;
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.cmnd = cmnd;
		this.matKhau = matKhau;
		this.lop = lop;
	}
	
	@Column(name = "ma_sv")
	@Id
	public String getMaSv() {
		return maSv;
	}
	public void setMaSv(String maSv) {
		this.maSv = maSv;
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
	public String getCmnd() {
		return cmnd;
	}
	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}
	
	@Column(name = "mat_khau")
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	
	@ManyToOne
	@JoinColumn(name = "lop")
	public Lop getLop() {
		return lop;
	}
	public void setLop(Lop lop) {
		this.lop = lop;
	}
	
	
	
}
