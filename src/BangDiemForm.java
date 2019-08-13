//import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
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
import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;

public class BangDiemForm {

	private JFrame frmDiem;
	private JTable table;
	private JTextField txtImport;

	/**
	 * Launch the application.
	 */
	public JFrame getFrmDiem() {
		
		return this.frmDiem;
		
	}
	public static String readFileForCombo(String filePath,String maMH) {
		
		List<ThoiKhoaBieuClass> tKBs = ThoiKhoaBieuClass.readTKB(filePath);
		for(ThoiKhoaBieuClass t : tKBs) {
			
			if(t.getmaMH().equalsIgnoreCase(maMH)) {
				
				return t.gettenMH();
				
			}	
		}
		return "";
	}
	
	public static void writeFileForCombo(String filePath, String content) {
		
		try(PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filePath,true),StandardCharsets.UTF_8))) {
			
			pw.println(content);
			pw.close();
			
		}
		
		catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BangDiemForm window = new BangDiemForm();
					window.frmDiem.setLocationRelativeTo(null);;
					window.frmDiem.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public BangDiemForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDiem = new JFrame();
		frmDiem.setTitle("Diem");
		frmDiem.setBounds(100, 100, 1107, 660);
		frmDiem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDiem.getContentPane().setLayout(null);
		
		
		JButton btnImport = new JButton("Import");
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					JFileChooser choser = new JFileChooser();
					choser.showOpenDialog(null);
					File f =choser.getSelectedFile();
					String filePath = f.getAbsolutePath();
					txtImport.setText(filePath);
					String _txtImport = txtImport.getText();
					String getClass1 = _txtImport.substring(_txtImport.lastIndexOf("\\")+1);
					String getClass2 = getClass1.substring(0, getClass1.lastIndexOf("."));//18HCB-CTT001
					String getClass3 = ".\\Data\\TKB\\" + getClass2.substring(0,5) + ".csv";//18HCB
					String getClass4 = getClass2.substring(6);//CTT001
					String getClass5 = getClass2 + "-" + readFileForCombo(getClass3, getClass4);
					String fileImported = ".\\Data\\Diem\\FileDaImport.csv";
					Path pathToFile = Paths.get(".\\Data\\Diem\\" + getClass2 + ".csv");
					try(BufferedReader br  = Files.newBufferedReader(pathToFile, StandardCharsets.UTF_8)){
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
							"STT", "MSSV", "Họ Tên", "Điểm Giữa Kỳ", "Điểm Cuối Kỳ", "Điểm Khác","Tổng Điểm"
					};
					Object[][] content = new Object[elements.size()][7];
					for (int i = 0; i < elements.size(); i++) {
						for (int j = 0; j < 7; j++) {
							
							content[i][j] = elements.get(i)[j];
							
						}
						
					}
					table.setModel(new DefaultTableModel(content,columsName));
					writeFileForCombo(fileImported, getClass5);
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnImport.setBounds(68, 78, 127, 39);
		frmDiem.getContentPane().add(btnImport);
		
		txtImport = new JTextField();
		txtImport.setBounds(226, 78, 470, 39);
		frmDiem.getContentPane().add(txtImport);
		txtImport.setColumns(10);
		
		JButton btnCancel = new JButton("Tr\u1EDF l\u1EA1i");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmDiem.setVisible(false);
				MainWindow mainWindow = new MainWindow();
				mainWindow.getFrmMainWindow().setLocationRelativeTo(null);
				mainWindow.getFrmMainWindow().setVisible(true);
				
			}
		});
		btnCancel.setBounds(884, 78, 127, 39);
		frmDiem.getContentPane().add(btnCancel);
		
		JLabel lblNewLabel = new JLabel("IMPORT V\u00C0O B\u1EA2NG \u0110I\u1EC2M C\u1EE6A M\u00D4N H\u1ECCC");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(68, 24, 943, 46);
		frmDiem.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 133, 1055, 471);
		frmDiem.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnQLBD = new JButton("QL B\u1EA3ng \u0111i\u1EC3m");
		btnQLBD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmDiem.setVisible(false);
				QL_BangDiem bangDiem = new QL_BangDiem();
				bangDiem.getfrmQLBD().setLocationRelativeTo(null);
				bangDiem.getfrmQLBD().setVisible(true);
				String filePath = ".\\Data\\Diem\\FileDaImport.csv" ;
				Path pathToFile = Paths.get(filePath);
				try(BufferedReader br = Files.newBufferedReader(pathToFile)){
					String line =br.readLine() ;
					while(line != null) {
						
						bangDiem.getcbbMonHoc().addItem(line);
						line =br.readLine() ;
							
					}
					
					br.close();
				}
				catch(Exception e1) {
					
					e1.printStackTrace();
					
				}
				
			}
		});
		btnQLBD.setBounds(725, 78, 144, 39);
		frmDiem.getContentPane().add(btnQLBD);
		
	}
}
