package source;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import net.code.QuanLiSinhVien;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class ThoiKhoaBieuForm {

	private JFrame frmThoiKhoaBieu;
	private JTable table;
	private JTextField txtDuongDan;
	private JButton btnBack;
	private String fileWriteClass = ".\\Data\\TKB\\CacLopDaImport.csv";

	/**
	 * Launch the application.
	 */
	public JFrame getFramThoiKhoaBieu() {
		
		return this.frmThoiKhoaBieu;
	}
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThoiKhoaBieuForm window = new ThoiKhoaBieuForm();
					window.frmThoiKhoaBieu.setLocationRelativeTo(null);
					window.frmThoiKhoaBieu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ThoiKhoaBieuForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmThoiKhoaBieu = new JFrame();
		frmThoiKhoaBieu.setTitle("Thoi Khoa Bieu");
		frmThoiKhoaBieu.setBounds(100, 100, 1107, 660);
		frmThoiKhoaBieu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmThoiKhoaBieu.getContentPane().setLayout(null);
		
		JButton btnImport = new JButton("Import");
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				QuanLiSinhVien.begin();
				try {
					JFileChooser chooser = new JFileChooser(".\\Data\\TKB\\");
					chooser.showOpenDialog(null);
					File f = chooser.getSelectedFile();
					String filePath = f.getAbsolutePath();
					txtDuongDan.setText(filePath);
					String str = txtDuongDan.getText() ;
					String str2 = str.substring(str.lastIndexOf("\\")+1);
					String str3 = str2.substring(0, 5);
					Path pathToFile = Paths.get(filePath);
					BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8);
					List<String[]> elements = new ArrayList<String[]>();
					String line = null;
					boolean flag = false ;
					while ((line = br.readLine()) != null) {
						
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
							"STT","Mã Môn Học","Tên Môn Học","Phòng Học"
					};
					Object[][] content = new Object[elements.size()][4];
					for(int i = 0; i < elements.size() ; i++) {
						QuanLiSinhVien.createThoiKB(elements.get(i)[1], elements.get(i)[2], elements.get(i)[3],str3);
						for (int j = 0; j < 4 ; j++) {
							
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
		frmThoiKhoaBieu.getContentPane().add(btnImport);
		txtDuongDan = new JTextField();
		txtDuongDan.setBounds(230, 78, 447, 39);
		frmThoiKhoaBieu.getContentPane().add(txtDuongDan);
		txtDuongDan.setColumns(10);
		
		btnBack = new JButton("Tr\u1EDF l\u1EA1i");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmThoiKhoaBieu.setVisible(false);
				MainWindow mainWindow = new MainWindow();
				mainWindow.getFrmMainWindow().setLocationRelativeTo(null);
				mainWindow.getFrmMainWindow().setVisible(true);
				
			}
		});
		btnBack.setBounds(850, 78, 127, 39);
		frmThoiKhoaBieu.getContentPane().add(btnBack);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 133, 1055, 471);
		frmThoiKhoaBieu.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblImportVoThi = new JLabel("IMPORT V\u00C0O TH\u1EDCI KH\u00D3A BI\u1EC2U");		
		lblImportVoThi.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblImportVoThi.setForeground(Color.RED);
		lblImportVoThi.setHorizontalAlignment(SwingConstants.CENTER);
		lblImportVoThi.setBounds(320, 24, 447, 46);
		frmThoiKhoaBieu.getContentPane().add(lblImportVoThi);
		
		JButton btnQlTkb = new JButton("QL TKB");
		btnQlTkb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmThoiKhoaBieu.setVisible(false);
				QL_TKB tkb = new QL_TKB();
				tkb.getFrmQLTKB().setLocationRelativeTo(null);
				tkb.getFrmQLTKB().setVisible(true);
				Path pathToFile = Paths.get(fileWriteClass);
				try(BufferedReader br = Files.newBufferedReader(pathToFile,StandardCharsets.UTF_8)){
					String line = br.readLine();
					while(line != null) {
						
						tkb.getcbbMonHoc().addItem(line);
						line = br.readLine();
						
					}
					br.close();
				}catch(Exception e1) {
					e1.printStackTrace();
					
					
				}		
			}
		});
		btnQlTkb.setBounds(703, 78, 127, 39);
		frmThoiKhoaBieu.getContentPane().add(btnQlTkb);
		
	}
}
