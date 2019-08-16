package net.code;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ds_lop")
public class Lop {

	private String lop;
	
	private Set<SinhVien> sinhViens;
	
	public Lop() {
		
	}

	public Lop(String lop) {
		this.lop = lop;
	}
	
	@Column(name = "lop")
	@Id
	public String getLop() {
		return lop;
	}

	public void setLop(String lop) {
		this.lop = lop;
	}

	@OneToMany(mappedBy = "lop",cascade = CascadeType.ALL)
	public Set<SinhVien> getSinhViens() {
		return sinhViens;
	}

	public void setSinhViens(Set<SinhVien> sinhViens) {
		this.sinhViens = sinhViens;
	}
	
	
	
	
	
	
}