package source;
//import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import net.code.DsLopMh;
import net.code.QuanLiSinhVien;

import java.awt.Font;
import java.awt.Color;
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

public class ImportDanhSachMonHoc {

	private JFrame frame;
	private JTextField txtDuongDan;
	private JTable table;
	private int sttDSMH = 0;

	
	public JFrame getJFrameIpDSMH() {
		return frame;
	}
	/**
	 * Launch the application.
	 */
	
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImportDanhSachMonHoc window = new ImportDanhSachMonHoc();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public ImportDanhSachMonHoc() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1107, 660);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("IMPORT DANH SÁCH LỚP MÔN HỌC");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(68, 24, 943, 46);
		frame.getContentPane().add(lblNewLabel);
		
		txtDuongDan = new JTextField();
		txtDuongDan.setBounds(226, 78, 470, 39);
		frame.getContentPane().add(txtDuongDan);
		txtDuongDan.setColumns(10);
		
		JButton btnNewButton = new JButton("Import");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				QuanLiSinhVien.begin();
				List<DsLopMh> ds = QuanLiSinhVien.getSTT_DSLMH();
				if(ds.isEmpty()){		
					sttDSMH = 0;					
				}
				else {
					
					sttDSMH = ds.get(ds.size()-1).getStt();
				}
				JFileChooser chooser = new JFileChooser(".\\Data\\DanhSachLopTungMonHoc\\");
				chooser.showOpenDialog(null);
				File f = chooser.getSelectedFile();
				String filePath = f.getAbsolutePath();
				txtDuongDan.setText(filePath);
				Path pathToFile = Paths.get(filePath);
				try(BufferedReader br = Files.newBufferedReader(pathToFile,StandardCharsets.UTF_8)){
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
							
							"STT", "MSSV", "Họ Tên", "Giới Tính", "CMND", "Lớp Môn Học", "Mã Môn Học"
							
						};
					
					Object[][] content = new Object[elements.size()][7];
					
					for (int i = 0; i < elements.size(); i++) {
			
						QuanLiSinhVien.createDanhSachLopMH(++sttDSMH,elements.get(i)[1], elements.get(i)[2], elements.get(i)[3],elements.get(i)[4], 
															elements.get(i)[5],elements.get(i)[6]);
						
						for (int j = 0; j < 7; j++) {
							
							content[i][j] = elements.get(i)[j];
							
						}
						
					}
					table.setModel(new DefaultTableModel(content,columsName));
					QuanLiSinhVien.end();
					
					
				}catch(Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(68, 78, 127, 39);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("QL Lớp MH");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DanhSachLopMonHoc ds = new DanhSachLopMonHoc();
				frame.setVisible(false);
				ds.getFrmDsLopMH().setLocationRelativeTo(null);
				ds.getFrmDsLopMH().setVisible(true);
			}
		});
		btnNewButton_1.setBounds(725, 78, 144, 39);
		frame.getContentPane().add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 133, 1055, 471);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_2 = new JButton("Quay Lại");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				MainWindow main = new MainWindow();
				main.getFrmMainWindow().setLocationRelativeTo(null);
				main.getFrmMainWindow().setVisible(true);
				
			}
		});
		btnNewButton_2.setBounds(884, 78, 127, 39);
		frame.getContentPane().add(btnNewButton_2);
	}
}
