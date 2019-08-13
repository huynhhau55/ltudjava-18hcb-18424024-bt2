//import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public class XemDiemForm {

	private JFrame frmThongTinDiem;
	private JTable table;
	private JLabel lblMSSV;
	private JLabel lblXinChao;
	private JLabel lblXinChao2;
	
	public JFrame getJFrame() {
		
		return frmThongTinDiem;
	}
	public JLabel getlblMSSV() {
		
		return lblMSSV;
	}
	public void  setlblMSSV (String maSV){
		
		this.lblMSSV.setText(maSV);
	}
	String readFile(String filePath2) {
		
		Path pathToFile2 = Paths.get(filePath2);
		try(BufferedReader br2 = Files.newBufferedReader(pathToFile2, StandardCharsets.UTF_8)){
			String line2 = null;
			while ((line2 = br2.readLine()) != null) {
				
				String[] spiltted = line2.split(";");
				if(lblMSSV.getText().equalsIgnoreCase(spiltted[1])) {
					br2.close();
					return spiltted[2];
				}
			}
			br2.close();
		}catch(Exception e2) {
			
			e2.printStackTrace();
		}
		return null;
		
	}
	
	String readFileForClass(String filePath, String maSV) {
		
		Path pathToFile = Paths.get(filePath);
		List<String> dsLop = new ArrayList<String>();
		try(BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)){
			
			String line = null;
			String hoTen_ = null;
			String fileName = null;
			String line2 = null;
			while ((line = br.readLine()) != null) {

					dsLop.add(line);
			}
			br.close();
			String a ;
			for(int i = 0; i < dsLop.size(); i++ ) {
				//if(i == 0) {
					a = dsLop.get(i).substring(1);
				//}
				//else {
					a = dsLop.get(i);
				//}
				
				
				fileName = ".\\Data\\Lop\\" + a + ".csv";
				Path pathToFile2 = Paths.get(fileName);
				try(BufferedReader br2 = Files.newBufferedReader(pathToFile2, StandardCharsets.UTF_8)){
					
					while ((line2 = br2.readLine()) != null) {
						String[] spiltted = line2.split(";");
						if(spiltted[1].equalsIgnoreCase(maSV)) {
							hoTen_ =  spiltted[2];
						}
					}
					br2.close();
				}catch(Exception e2) {
					
					e2.printStackTrace();
				}
				
				if(hoTen_ != null) {
					
					return hoTen_.toUpperCase() + ", LỚP " +dsLop.get(i);
				}
				
			}
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	public String dauRot(String tongDiem) {
		
		float _tongDiem = Float.parseFloat(tongDiem);
		if(_tongDiem < 5.0 )
		{
			return "Rớt";
		}
		return "Đậu";
	}
	
	void readDiem(String filepath, String maSV) {
		
		List<String> bangDiemS = new ArrayList<String>();
		List<String[]> bangDiemS2 = new ArrayList<String[]>();
		Path pathToFile3 = Paths.get(filepath);
		String line3 = null; 
		String duongDan = null; String line4 = null; String fileCacLop = null;
		try(BufferedReader br3 = Files.newBufferedReader(pathToFile3, StandardCharsets.UTF_8)){
			
			while((line3 = br3.readLine()) != null){
				
				bangDiemS.add(line3);
			}
			br3.close();
			for (int i = 0; i < bangDiemS.size(); i++) {
				
				fileCacLop = bangDiemS.get(i) ;
				duongDan = ".\\Data\\Diem\\" + fileCacLop.substring(0, fileCacLop.lastIndexOf("-"))  + ".csv";
				Path pathToFile4 = Paths.get(duongDan);
				try(BufferedReader br4 = Files.newBufferedReader(pathToFile4, StandardCharsets.UTF_8)){
					while((line4 = br4.readLine()) != null) {
						
						String[]  spiltted4 = line4.split(";");
						if(spiltted4[1].equalsIgnoreCase(maSV)) {
							spiltted4[0]= fileCacLop.substring(0, fileCacLop.indexOf("-"));
							spiltted4[1] = fileCacLop.substring(fileCacLop.indexOf("-") +1 , fileCacLop.lastIndexOf("-"));
							spiltted4[2] = fileCacLop.substring(fileCacLop.lastIndexOf("-") + 1);
							bangDiemS2.add(spiltted4);						
							break;
						}
					}
					br4.close();
				}catch (Exception e4) {
					e4.printStackTrace();
				}
			}	
			
			String[] columsName = new String[] {
					"STT","Lớp Môn Học","Mã Môn Học","Tên Môn Học","Điểm Giữa Kỳ", "Điểm Cuối Kỳ", "Điểm Khác", "Tổng Điểm", "Kết Quả"					
			};
			Object[][] content = new Object[bangDiemS2.size()][9];
			int sTT = 1;
			for(int i = 0; i < bangDiemS2.size(); i++) {
				for(int j = 0; j < 9; j++) {
					if(j == 0){
						
						content[i][j] = Integer.toString(sTT++);
					}
					
					else if(j == 8) {
						
						content[i][8] = dauRot(bangDiemS2.get(i)[6].toString());
					}
					else {
						
						content[i][j] = bangDiemS2.get(i)[j -1];
					}
				}
			}
			table.setModel(new DefaultTableModel(content,columsName));
			
		}catch(Exception e3){
			
			e3.printStackTrace();
		}
		
	}
	/**
	 * Launch the application.
	 */
	
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					XemDiemForm window = new XemDiemForm();
					window.frmThongTinDiem.setLocationRelativeTo(null);
					window.frmThongTinDiem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public XemDiemForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmThongTinDiem = new JFrame();
		frmThongTinDiem.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				String filePath = ".\\Data\\Lop\\CacLopHienCo.csv";
				String maSV = lblMSSV.getText();
				lblXinChao2.setText(readFileForClass(filePath,maSV));
				readDiem(".\\Data\\Diem\\FileDaImport.csv",maSV);
				
			}
		});
		frmThongTinDiem.setTitle("TH\u00D4NG TIN \u0110I\u1EC2M");
		frmThongTinDiem.setBounds(100, 100, 1107, 660);
		frmThongTinDiem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmThongTinDiem.getContentPane().setLayout(null);
		
		lblXinChao = new JLabel("TH\u00D4NG TIN \u0110I\u1EC2M C\u1EE6A SINH VI\u00CAN");
		lblXinChao.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblXinChao.setHorizontalAlignment(SwingConstants.CENTER);
		lblXinChao.setForeground(Color.RED);
		lblXinChao.setBounds(15, 0, 1055, 60);
		frmThongTinDiem.getContentPane().add(lblXinChao);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 91, 1055, 513);
		frmThongTinDiem.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnLogout = new JButton("");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want logout ?", "Confirm",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (dialogResult == JOptionPane.YES_OPTION) {
					
					frmThongTinDiem.setVisible(false);
					LoginForm loginForm = new LoginForm();
					loginForm.frmLogin.setLocationRelativeTo(null);
					loginForm.frmLogin.setVisible(true);

				}
			}
		});
		btnLogout.setIcon(new ImageIcon(".\\images\\logout.png"));
		btnLogout.setBounds(966, 46, 69, 29);
		frmThongTinDiem.getContentPane().add(btnLogout);
		
		JLabel lblLogOut = new JLabel("\u0110\u0103ng Xu\u1EA5t");
		lblLogOut.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogOut.setBounds(943, 16, 105, 29);
		frmThongTinDiem.getContentPane().add(lblLogOut);
		
		lblMSSV = new JLabel("");
		lblMSSV.setHorizontalAlignment(SwingConstants.CENTER);
		lblMSSV.setBounds(15, 0, 96, 36);
		frmThongTinDiem.getContentPane().add(lblMSSV);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(".\\images\\Add.png"));
		lblNewLabel.setBounds(37, 26, 63, 60);
		frmThongTinDiem.getContentPane().add(lblNewLabel);
		
		lblXinChao2 = new JLabel("");
		lblXinChao2.setForeground(Color.RED);
		lblXinChao2.setHorizontalAlignment(SwingConstants.CENTER);
		lblXinChao2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblXinChao2.setBounds(375, 39, 335, 36);
		frmThongTinDiem.getContentPane().add(lblXinChao2);
	}
}
