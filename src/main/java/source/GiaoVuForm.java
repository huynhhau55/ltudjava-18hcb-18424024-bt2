package source;
//import java.awt.EventQueue;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.code.QuanLiSinhVien;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class GiaoVuForm {

	public JFrame frmGiaoVu;
	private JTable table;
	public JTextField txtDuongDan;
	private JButton btnBack;
	private JLabel lblImportVaoDanh;
	private JScrollPane scrollPane;
	private JButton btnQLSV;
	private int sttDsLopMH;
	
	

	/**
	 * Launch the application.
	 */
	public JFrame getFrmGiaoVu()
	{
		return this.frmGiaoVu;
	}
	
	
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GiaoVuForm window = new GiaoVuForm();
					window.frmGiaoVu.setLocationRelativeTo(null);
					window.frmGiaoVu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public GiaoVuForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		QuanLiSinhVien.begin();
		frmGiaoVu = new JFrame();
		frmGiaoVu.setTitle("Danh Sach Lop");
		frmGiaoVu.setBounds(100, 100, 1107, 660);
		frmGiaoVu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGiaoVu.getContentPane().setLayout(null);
		
		JButton btnImport = new JButton("Import");
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					
					
					JFileChooser chooser = new JFileChooser();
					chooser.showOpenDialog(null);
					File f = chooser.getSelectedFile() ;
					String fileName = f.getAbsolutePath();
					txtDuongDan.setText(fileName);
					Path pathToFile = Paths.get(fileName);
					try (BufferedReader br  = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)){
					List<String[]> elements = new ArrayList<String[]>();
					String line = null;
					boolean flag = false;
					while ((line = br.readLine()) != null) {
						
						if(flag == false) {
							
							flag = true;
							continue;
							
						}
						else {
							
							String[] splitted = line.split(";");
							elements.add(splitted);
							
						}
						
						
					}
					br.close();
					String[] columsName = new String[] {
							
							"STT", "MSSV", "Họ Tên", "Giới Tính", "CMND", "Lớp"
							
						};
					
					Object[][] content = new Object[elements.size()][6];
					
					for (int i = 0; i < elements.size(); i++) {
						QuanLiSinhVien.createDSLop(Integer.parseInt(elements.get(i)[0].toString()),elements.get(i)[1], elements.get(i)[2],
								elements.get(i)[3],elements.get(i)[4], elements.get(i)[5]);
						for (int j = 0; j < 6; j++) {
							
							content[i][j] = elements.get(i)[j];
							
						}
						
					}
					table.setModel(new DefaultTableModel(content,columsName));
					QuanLiSinhVien.end();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		});
		btnImport.setBounds(74, 78, 127, 39);
		frmGiaoVu.getContentPane().add(btnImport);
		
		txtDuongDan = new JTextField();
		txtDuongDan.setBounds(230, 78, 447, 39);
		frmGiaoVu.getContentPane().add(txtDuongDan);
		txtDuongDan.setColumns(10);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmGiaoVu.setVisible(false);
				MainWindow mainWindow = new MainWindow();
				mainWindow.getFrmMainWindow().setLocationRelativeTo(null);
				mainWindow.getFrmMainWindow().setVisible(true);
			}
		});
		btnBack.setBounds(850, 78, 127, 39);
		frmGiaoVu.getContentPane().add(btnBack);
		
		lblImportVaoDanh = new JLabel("IMPORT V\u00C0O DANH S\u00C1CH L\u1EDAP");
		lblImportVaoDanh.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblImportVaoDanh.setForeground(Color.RED);
		lblImportVaoDanh.setHorizontalAlignment(SwingConstants.CENTER);
		lblImportVaoDanh.setBounds(320, 24, 447, 46);
		frmGiaoVu.getContentPane().add(lblImportVaoDanh);
		
		scrollPane = new JScrollPane();
		frmGiaoVu.getContentPane().add(scrollPane);
		scrollPane.setBounds(15, 133, 1055, 471);
		scrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnQLSV = new JButton("Qu\u1EA3n l\u00ED SV");
		btnQLSV.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					frmGiaoVu.setVisible(false);
					AddStudentForm studentForm = new AddStudentForm();
					studentForm.getFrmAddSd().setLocationRelativeTo(null);
					studentForm.getFrmAddSd().setVisible(true);
				}
				catch(Exception e3)
				{
					e3.printStackTrace();
				}
			}
		});
		btnQLSV.setBounds(703, 78, 127, 39);
		frmGiaoVu.getContentPane().add(btnQLSV);

	}
}

