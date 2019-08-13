import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BangDiem {
	
	private String sTT;
	private String maSV;
	private String hoTen;
	private float diemGK;
	private float diemCK;
	private float diemKhac;
	private float diemTong;
	
	public String getSTT(){
		
		return sTT;
	}
	public void setSTT(String sTT) {
		
		this.sTT=sTT;
	}
	public String getMaSV() {
		
		return maSV;
	}
	public void setMaSV(String maSV) {
		
		this.maSV=maSV;
	}
	public String getHoTen() {
		
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		
		this.hoTen = hoTen;
	}
	public float getDiemGK() {
		
		return diemGK;
	}
	public void setDiemGK(float diemGK) {
		
		this.diemGK = diemGK;
	}
	public float getDiemCK() {
		
		return diemCK;
	}
	public void setDiemCK(float diemCK) {
		
		this.diemCK =diemCK;
	}
	public float getDiemKhac() {
		
		return diemKhac;
	}
	public void setDiemKhac(float diemKhac) {
		
		this.diemKhac = diemKhac;
	}
	public float getDiemTong() {
		
		return diemTong;
	}
	public void setDiemTong(float diemTong) {
		
		this.diemTong = diemTong;
	}
	
	/*@Override
	public String toString() {
		
		return sTT + maSV + hoTen + diemGK + diemCK + diemKhac + diemTong;
	}*/
	public BangDiem(String sTT, String maSV, String hoTen, float diemGK,
					float diemCK, float diemKhac, float diemTong) {
		
		this.sTT = sTT; this.maSV = maSV;
		this.hoTen = hoTen; this.diemGK = diemGK;
		this.diemCK = diemCK; this.diemKhac = diemKhac;
		this.diemTong = diemTong;
	}
	
	private static BangDiem createBangDiem(String[] metadata) {
		
		String sTT = metadata[0];
		String maSV = metadata[1];
		String hoTen = metadata[2];
		float diemGK = Float.parseFloat(metadata[3]);
		float diemCK = Float.parseFloat(metadata[4]);
		float diemKhac = Float.parseFloat(metadata[5]);
		float diemTong = Float.parseFloat(metadata[6]);
		return new BangDiem(sTT, maSV, hoTen, diemGK, diemCK, diemKhac, diemTong);
		
	}
	
	public static List<BangDiem> readBangDiem(String filePath){
		List<BangDiem> bangDiemS = new ArrayList<BangDiem>();
		Path pathToFile = Paths.get(filePath);
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)) {		
			String line = br.readLine(); boolean flag =false;
			while(line != null) {
				if(flag == false) {
					
					flag = true;
					line = br.readLine();
					continue;					
				}
				else {
					
					String[] attributes = line.split(";");
					BangDiem bangDiem = createBangDiem(attributes);
					bangDiemS.add(bangDiem);
					line = br.readLine();
					
				}
				
			}
			
		}
		catch (Exception e) {
			
			e.printStackTrace();
		}
		return bangDiemS;
	}
}
	
