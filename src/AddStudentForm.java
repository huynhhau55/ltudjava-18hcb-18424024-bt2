//import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import com.sun.xml.internal.stream.writers.UTF8OutputStreamWriter;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;

public class AddStudentForm {

	private JFrame frmAddSd;
	private JTable table;
	private JTextField txtMSSV;
	private JTextField txtHoTen;
	private JLabel lblCmnd;
	private JTextField txtCMND;
	private JLabel lblGioiTinh;
	private JButton btnThem;
	private JButton btnQuayLai;
	private JScrollPane scrollPane;
	private JComboBox<String> cbbLop;
	private JLabel lblQunLSinh;
	

	/**
	 * Launch the application.
	 */
	
	public JComboBox<String> getCbbLop(){
	
		return this.cbbLop;
	}
	
	public JFrame getFrmAddSd() {
		
		return this.frmAddSd;
	}
	
	
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStudentForm window = new AddStudentForm();
					window.frmAddSd.setLocationRelativeTo(null);
					window.frmAddSd.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public AddStudentForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void writeSinhVien(String filePath, List<Student> Students) {
		
		try(PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filePath),
				StandardCharsets.UTF_8))) {
			int stt = 1;
			boolean flag = false;
			for (Student sd : Students) {

				if(flag == false) {

					flag = true;
					pw.println(("STT" + ";" + sd.getstudentID() + ";" + sd.getnameStudent() + ";" + 
							sd.getgenderStudent() + ";" + sd.getidentityCard() + ";" + sd.getclassRoom() ));


				}
				else {
					pw.println((Integer.toString(stt++) + ";" + sd.getstudentID() + ";" + sd.getnameStudent() + ";" + 
							sd.getgenderStudent() + ";" + sd.getidentityCard() + ";" + sd.getclassRoom() ));
				}


			}
			pw.close();
			txtCMND.setText("");
			txtHoTen.setText("");
			txtMSSV.setText("");
		
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
	}
	private void loadSinhVien() {
			
			String filePath = ".\\Data\\Lop\\" + cbbLop.getSelectedItem() + ".csv";
			Path pathToFile = Paths.get(filePath);
			try(BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)){
			List<String[]> elements = new ArrayList<String[]>();
			String line = null; boolean flag = false;
			while((line = br.readLine()) != null ) {
				if(flag == false) {
					flag = true;
					continue;
				}
				else {
					
					String[] spiltted = line.split(";");
					elements.add(spiltted);
				}
			}
			br.close();
			String[] columsName = new String[] {
					"STT","MSSV","Họ Tên","Giới Tính", "CMND", "Lớp"							
			};
			Object[][] content = new Object[elements.size()][6];
			for(int i = 0; i < elements.size(); i++) {
				for(int j = 0; j < 6; j++) {
					
					content[i][j] = elements.get(i)[j];
				}
			}
			table.setModel(new DefaultTableModel(content,columsName));
		}catch (Exception e2) {
			
			e2.printStackTrace();
			
			}
		
		
	}
	private void initialize() {
		frmAddSd = new JFrame();
		frmAddSd.setTitle("Qu\u1EA3n l\u00FD sinh vi\u00EAn");
		frmAddSd.setBounds(100, 100, 1107, 660);
		frmAddSd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAddSd.getContentPane().setLayout(null);
		
		
		
		
		txtMSSV = new JTextField();
		txtMSSV.setBounds(208, 104, 146, 29);
		frmAddSd.getContentPane().add(txtMSSV);
		txtMSSV.setColumns(10);
		
		JLabel lblMssv = new JLabel("MSSV");
		lblMssv.setBounds(131, 106, 69, 25);
		frmAddSd.getContentPane().add(lblMssv);
		
		JLabel lblLop = new JLabel("L\u1EDBp");
		lblLop.setBounds(131, 63, 69, 25);
		frmAddSd.getContentPane().add(lblLop);
		
		JLabel lblHoTen = new JLabel("H\u1ECD t\u00EAn");
		lblHoTen.setBounds(406, 63, 69, 25);
		frmAddSd.getContentPane().add(lblHoTen);
		
		txtHoTen = new JTextField();
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(508, 61, 146, 29);
		frmAddSd.getContentPane().add(txtHoTen);
		
		lblCmnd = new JLabel("CMND");
		lblCmnd.setBounds(406, 106, 69, 25);
		frmAddSd.getContentPane().add(lblCmnd);
		
		txtCMND = new JTextField();
		txtCMND.setColumns(10);
		txtCMND.setBounds(508, 104, 146, 29);
		frmAddSd.getContentPane().add(txtCMND);
		
		lblGioiTinh = new JLabel("Gi\u1EDBi t\u00EDnh");
		lblGioiTinh.setBounds(694, 59, 69, 29);
		frmAddSd.getContentPane().add(lblGioiTinh);
		
		JComboBox<String> cbbGioiTinh = new JComboBox<String>();
		cbbGioiTinh.setBounds(799, 60, 146, 26);
		frmAddSd.getContentPane().add(cbbGioiTinh);
		cbbGioiTinh.addItem("Nam");
		cbbGioiTinh.addItem("Nữ");
		
		btnThem = new JButton("Th\u00EAm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
					String filePath = ".\\Data\\Lop\\" + cbbLop.getSelectedItem().toString() + ".csv";
					List<Student> Students = Student.readStudents(filePath);
					String stt = Integer.toString(Students.size());
					String gioiTinh = String.valueOf(cbbGioiTinh.getSelectedItem());
					Student sd = new Student(stt, txtMSSV.getText(), txtHoTen.getText(), 
										gioiTinh,txtCMND.getText() , cbbLop.getSelectedItem().toString());
					try(PrintWriter pw = new PrintWriter(new UTF8OutputStreamWriter(new FileOutputStream(filePath,true)))){
					pw.println(sd.getsttStudent() + ";" + sd.getstudentID() + ";" + sd.getnameStudent() + ";" + 
							   sd.getgenderStudent() + ";" + sd.getidentityCard() + ";" + sd.getclassRoom() );
					
					pw.close();
					loadSinhVien();
					txtCMND.setText("");
					txtHoTen.setText("");
					txtMSSV.setText("");
						
				}
				catch(Exception ioe) {
					
					ioe.printStackTrace();
				}	
				
			}
		});
		btnThem.setBounds(131, 158, 156, 43);
		frmAddSd.getContentPane().add(btnThem);
		
		btnQuayLai = new JButton("Quay l\u1EA1i");
		btnQuayLai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frmAddSd.setVisible(false);
				GiaoVuForm giaovu = new GiaoVuForm();
				giaovu.getFrmGiaoVu().setLocationRelativeTo(null);
				giaovu.getFrmGiaoVu().setVisible(true);
				
			}
		});
		btnQuayLai.setBounds(799, 158, 146, 43);
		frmAddSd.getContentPane().add(btnQuayLai);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 217, 1055, 371);
		frmAddSd.getContentPane().add(scrollPane);
		
	
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				DefaultTableModel dfModel = (DefaultTableModel)table.getModel();
				int selectedRowIndex = table.getSelectedRow();
				txtMSSV.setText(dfModel.getValueAt(selectedRowIndex, 1).toString());
				txtHoTen.setText(dfModel.getValueAt(selectedRowIndex, 2).toString());
				cbbGioiTinh.setSelectedItem(dfModel.getValueAt(selectedRowIndex, 3).toString());
				txtCMND.setText(dfModel.getValueAt(selectedRowIndex, 4).toString());
				cbbLop.setSelectedItem(dfModel.getValueAt(selectedRowIndex, 5).toString());
				
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnXoa = new JButton("X\u00F3a");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					String filePath = ".\\Data\\Lop\\" + cbbLop.getSelectedItem() + ".csv";
					//String filePath = "18HCB.csv";
					List<Student> Students = Student.readStudents(filePath);
					for (Student sd : Students) {
						
						if( txtMSSV.getText().equalsIgnoreCase(sd.getstudentID()) ) 
							{
							
							Students.remove(sd);
							break;
							
						}
						
					}
					try(PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filePath),
																			StandardCharsets.UTF_8))) {
						int stt = 1;
						boolean flag = false;
						for (Student sd : Students) {
							
							if(flag == false) {
								
								flag = true;
								pw.println(("STT" + ";" + sd.getstudentID() + ";" + sd.getnameStudent() + ";" + 
										   sd.getgenderStudent() + ";" + sd.getidentityCard() + ";" + sd.getclassRoom() ));
							
								
							}
							else {
								pw.println((Integer.toString(stt++) + ";" + sd.getstudentID() + ";" + sd.getnameStudent() + ";" + 
										   sd.getgenderStudent() + ";" + sd.getidentityCard() + ";" + sd.getclassRoom() ));
							}
							
							
						}
						pw.close();
						loadSinhVien();
						txtCMND.setText("");
						txtHoTen.setText("");
						txtMSSV.setText("");
					}catch(Exception e) {
						e.printStackTrace();
					}
					
					
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				
				
			}
		});
		btnXoa.setBounds(365, 158, 151, 43);
		frmAddSd.getContentPane().add(btnXoa);
		
		JButton btnCapNhat = new JButton("C\u1EADp nh\u1EADt");
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String filePath = ".\\Data\\Lop\\" + cbbLop.getSelectedItem() + ".csv";
				List<Student> Students = Student.readStudents(filePath);
				for (Student s : Students) {
					
					if(txtMSSV.getText().equalsIgnoreCase(s.getstudentID())) {
						s.setnameStudent(txtHoTen.getText());
						s.setidentityCard(txtCMND.getText());
						s.setgenderStudent(cbbGioiTinh.getSelectedItem().toString());
						break;
					}
				}
				writeSinhVien(filePath, Students);
				loadSinhVien();
				
			}
		});
		btnCapNhat.setBounds(584, 158, 156, 43);
		frmAddSd.getContentPane().add(btnCapNhat);
		
		cbbLop = new JComboBox<String>();
		cbbLop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				loadSinhVien();
				
			}
		});
		cbbLop.setBounds(208, 61, 146, 28);
		frmAddSd.getContentPane().add(cbbLop);
		
		lblQunLSinh = new JLabel("QU\u1EA2N L\u00DD SINH VI\u00CAN");
		lblQunLSinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblQunLSinh.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblQunLSinh.setForeground(Color.RED);
		lblQunLSinh.setBounds(337, 0, 376, 59);
		frmAddSd.getContentPane().add(lblQunLSinh);
	
		
	}
}
