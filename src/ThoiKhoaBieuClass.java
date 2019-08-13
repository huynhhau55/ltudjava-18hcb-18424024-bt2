import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ThoiKhoaBieuClass {
	
	private String STT;
	private String maMH;
	private String tenMH;
	private String phongHoc;
	
	public String getSTT() {
		
		return this.STT;
		
	}
	public void setSTT(String a) {
		
		this.STT = a;
		
	}
	public String getmaMH() {
		
		return this.maMH;
		
	}
	public void setmaMH(String a) {
		
		this.maMH = a;
		
	}
	public String gettenMH() {
		
		return this.tenMH;
		
	}
	public void settenMH(String a) {
		
		this.tenMH = a;
		
	}
	
	public String getphongHoc() {
		
		return this.phongHoc;
		
	}
	
	public void setphongHoc(String a) {
		
		this.phongHoc = a;
		
	}
	
	@Override
	public String toString() {
		
		return STT + maMH + tenMH + phongHoc;
		
	}
	
	public ThoiKhoaBieuClass (String stt, String maMH, String tenMH, String phongHoc) {
		
		this.STT = stt;
		this.maMH = maMH;
		this.tenMH = tenMH;
		this.phongHoc = phongHoc;
		
	}
	
	public static ThoiKhoaBieuClass createTKB(String[] metadata) {
		
		String STT = metadata[0];
		String maMH = metadata[1];
		String tenMH = metadata[2];
		String phongHoc = metadata[3];
		return new ThoiKhoaBieuClass(STT, maMH, tenMH, phongHoc);
	}
	
	public static List<ThoiKhoaBieuClass> readTKB(String filePath){
		List<ThoiKhoaBieuClass> tKBs = new ArrayList<ThoiKhoaBieuClass>();
		Path pathtoFile = Paths.get(filePath);
		try(BufferedReader br = Files.newBufferedReader(pathtoFile, StandardCharsets.UTF_8)) {
			
			String line = br.readLine();
			while(line != null) {
				
				String[] attributes = line.split(";");
				ThoiKhoaBieuClass TKB = createTKB(attributes);
				tKBs.add(TKB);
				line = br.readLine();
				
			}
			br.close();
		}
		catch(Exception ioe) {
			
			ioe.printStackTrace();
			
		}
		return tKBs;
		
	}
}
