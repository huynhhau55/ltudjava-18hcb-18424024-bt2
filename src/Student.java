import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Student {
	
	private String sttStudent;
	private String studentID;
	private String nameStudent;
	private String genderStudent;
	private String identityCard;
	private String classRoom;
	
	public String getsttStudent() {
		
		return this.sttStudent;
	}
	
	public void setsttStudent(String a) {
		
		sttStudent = a;
	}
	
	public String getstudentID() {
		
		return this.studentID;
	}
	
	public void setstudentID(String a) {
		
		studentID = a;
	}
	
	public String getnameStudent() {
		
		return this.nameStudent;
		
	}
	
	public void setnameStudent(String a) {
		
		nameStudent = a;
	}
	
	public String getgenderStudent() {
			
			return this.genderStudent;
			
	}
	
	public void setgenderStudent(String a) {
		
		genderStudent = a;
	}
	
	public String getidentityCard() {
		
		return this.identityCard;
		
	}
	
	public void setidentityCard(String a) {
		
		identityCard = a;
	}
	
	public String getclassRoom() {
		
		return this.classRoom;
		
	}
	
	public void setclassRoom(String a) {
		
		classRoom = a;
	}
	
	
	
	@Override
    public String toString() {
        return sttStudent +  studentID + nameStudent + genderStudent + identityCard + classRoom ;
 }
	
	public Student(String stt, String id, String name,String gender,String idCard,String classR)
	{
		this.sttStudent = stt;
		this.studentID = id;
		this.nameStudent = name;
		this.genderStudent = gender;
		this.identityCard = idCard;
		this.classRoom = classR;
	}
	
	public static List<Student> readStudents(String fileName) {
		
		List<Student> Students = new ArrayList<Student>();
		Path pathToFile = Paths.get(fileName);
		try (BufferedReader br = Files.newBufferedReader(pathToFile,StandardCharsets.UTF_8)) {
			
			String line = br.readLine();
			while(line != null) {
				
					String[] attributes = line.split(";");
					Student student = createStudent(attributes);
					Students.add(student);
					line = br.readLine();
				
				
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return Students;
	}
		
		private static Student createStudent(String[] metadata) {
			
			String stt = metadata[0];
			String id = metadata[1];
			String name = metadata[2];
			String gender = metadata[3];
			String idCard = metadata[4];
			String classR = metadata[5];
			return new Student(stt,id,name,gender,idCard,classR);
		}
		
		public static void writeFile(String filePath, List<Student> Students) {
			
			try(FileWriter fw = new FileWriter(filePath,true);
					BufferedWriter bw = new BufferedWriter(fw);
					PrintWriter pw = new PrintWriter(bw)){
				
				String stt = Integer.toString(Students.size());
				
				for (Student sd : Students) {
					
					pw.println(stt + ";" + sd.getstudentID() + ";" + sd.getnameStudent() + ";" + 
							   sd.getgenderStudent() + ";" + sd.getidentityCard() + ";" + sd.getclassRoom() );
				}
				pw.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			
		}
	

}
