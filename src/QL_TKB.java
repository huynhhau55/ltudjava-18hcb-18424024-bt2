//import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QL_TKB {

	private JFrame frmQL_TKB;
	private JTextField txtMH;
	private JTextField txtTenMH;
	private JTextField txtPH;
	private JTable table;
	private JComboBox<String> cbbLop;

	/**
	 * Launch the application.
	 */
	
	public JFrame getFrmQLTKB() {
		
		return this.frmQL_TKB;
		
	}
	
	public JComboBox<String> getcbbMonHoc(){
		
		return this.cbbLop;
		
	}
	
	private void loadTKB() {
		
		try {
			
			String filePath = ".\\Data\\TKB\\" + cbbLop.getSelectedItem().toString() + ".csv";
			Path pathTofile = Paths.get(filePath);
			BufferedReader br = Files.newBufferedReader(pathTofile, StandardCharsets.UTF_8);
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
					"STT","Mã Môn Học","Tên Môn Học","Phòng Học", "Lớp"							
			};
			Object[][] content = new Object[elements.size()][5];
			for(int i = 0; i < elements.size(); i++) {
				for(int j = 0; j < 5; j++) {
					if( j == 4) {
						
						content[i][j] = cbbLop.getSelectedItem();
						
					}
					else {
						
						content[i][j] = elements.get(i)[j];
						
					}
				}
			}
			table.setModel(new DefaultTableModel(content,columsName));
		}catch (Exception e2) {
			
			e2.printStackTrace();
			
			}
		
		
	}
	
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QL_TKB window = new QL_TKB();
					window.frmQL_TKB.setLocationRelativeTo(null);
					window.frmQL_TKB.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public QL_TKB() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmQL_TKB = new JFrame();
		frmQL_TKB.setTitle("QL TKB");
		frmQL_TKB.setBounds(100, 100, 1107, 660);
		frmQL_TKB.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmQL_TKB.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("QU\u1EA2N L\u00DD TH\u1EDCI KH\u00D3A BI\u1EC2U");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(131, 0, 814, 49);
		frmQL_TKB.getContentPane().add(lblNewLabel);
		
		JButton btnThem = new JButton("Th\u00EAm ");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String filePath = ".\\Data\\TKB\\" + cbbLop.getSelectedItem().toString() + ".csv";
				List<ThoiKhoaBieuClass> tKBs = ThoiKhoaBieuClass.readTKB(filePath);
				int size = tKBs.size();
				try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filePath,true),StandardCharsets.UTF_8))){
					pw.println(Integer.toString(size) + ";" + txtMH.getText() + ";" + txtTenMH.getText() + ";" + txtPH.getText());
					pw.close();
					loadTKB();
				} catch (Exception e) {

					e.printStackTrace();

				}

			}
		});
		btnThem.setBounds(131, 158, 156, 43);
		frmQL_TKB.getContentPane().add(btnThem);
		
		JButton btnXoa = new JButton("X\u00F3a");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String filePath = ".\\Data\\TKB\\" + cbbLop.getSelectedItem().toString() + ".csv";
				List<ThoiKhoaBieuClass> tKBs= ThoiKhoaBieuClass.readTKB(filePath);
				for(ThoiKhoaBieuClass t : tKBs) {
					
					if(t.getmaMH().equalsIgnoreCase(txtMH.getText())) {
						
						tKBs.remove(t);
						break;
	
					}
				}
				try(PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filePath),
						StandardCharsets.UTF_8))) {
					int stt=1; boolean flag = false;
					for(ThoiKhoaBieuClass t : tKBs) {
						
						if(flag == false) {
							
							flag = true;
							pw.println("STT" + ";" + t.getmaMH() + ";" + t.gettenMH() + ";" + 
									t.getphongHoc());
							
						}
						else {
							
							pw.println(Integer.toString(stt++) + ";" + t.getmaMH() + ";" + t.gettenMH() + ";" + 
									t.getphongHoc());
							
						}
					}
					pw.close();
					loadTKB();
					txtMH.setText("");
					txtPH.setText("");
					txtTenMH.setText("");
					
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				
				
			}
		});
		btnXoa.setBounds(365, 158, 151, 43);
		frmQL_TKB.getContentPane().add(btnXoa);
		
		JButton btnCapNhat = new JButton("C\u1EADp nh\u1EADt");
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filePath = ".\\Data\\TKB\\" + cbbLop.getSelectedItem().toString() + ".csv";
				List<ThoiKhoaBieuClass> tKBs = ThoiKhoaBieuClass.readTKB(filePath);
				for(ThoiKhoaBieuClass t : tKBs) {
					
					if(t.getmaMH().equalsIgnoreCase(txtMH.getText())) {
						
						t.setphongHoc(txtPH.getText());
						t.settenMH(txtTenMH.getText());
						break;
						
					}
					
				}
				
				try(PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filePath),StandardCharsets.UTF_8))){
				
					int stt=1;
					boolean flag = false;
					for(ThoiKhoaBieuClass t : tKBs) {
						if(flag == false) {
							
							flag = true;
							pw.println("STT" + ";" + t.getmaMH() + ";" + t.gettenMH() + ";" + t.getphongHoc());
							
						}
						else {
							
							pw.println(Integer.toString(stt++) + ";" + t.getmaMH() + ";" + t.gettenMH() + ";" + t.getphongHoc());
							
						}
					}
					pw.close();
					loadTKB();
					
					
				}catch(Exception e2) {
					
					e2.printStackTrace();
					
				}
				
				
			}
		});
		btnCapNhat.setBounds(584, 158, 156, 43);
		frmQL_TKB.getContentPane().add(btnCapNhat);
		
		JButton btnQuayLai = new JButton("Quay l\u1EA1i");
		btnQuayLai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmQL_TKB.setVisible(false);
				ThoiKhoaBieuForm tkb = new ThoiKhoaBieuForm();
				tkb.getFramThoiKhoaBieu().setLocationRelativeTo(null);
				tkb.getFramThoiKhoaBieu().setVisible(true);
				
			}
		});
		btnQuayLai.setBounds(799, 158, 146, 43);
		frmQL_TKB.getContentPane().add(btnQuayLai);
		
		cbbLop = new JComboBox<String>();
		cbbLop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				loadTKB();
				
			}
		});
		cbbLop.setBounds(331, 62, 146, 26);
		frmQL_TKB.getContentPane().add(cbbLop);
		
		JLabel lblNewLabel_1 = new JLabel("L\u1EDBp");
		lblNewLabel_1.setBounds(205, 65, 69, 20);
		frmQL_TKB.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("M\u00E3 m\u00F4n h\u1ECDc");
		lblNewLabel_2.setBounds(205, 110, 87, 20);
		frmQL_TKB.getContentPane().add(lblNewLabel_2);
		
		txtMH = new JTextField();
		txtMH.setBounds(331, 104, 146, 26);
		frmQL_TKB.getContentPane().add(txtMH);
		txtMH.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("T\u00EAn m\u00F4n h\u1ECDc");
		lblNewLabel_3.setBounds(579, 65, 109, 20);
		frmQL_TKB.getContentPane().add(lblNewLabel_3);
		
		txtTenMH = new JTextField();
		txtTenMH.setBounds(703, 62, 146, 26);
		frmQL_TKB.getContentPane().add(txtTenMH);
		txtTenMH.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Ph\u00F2ng h\u1ECDc");
		lblNewLabel_4.setBounds(579, 110, 117, 20);
		frmQL_TKB.getContentPane().add(lblNewLabel_4);
		
		txtPH = new JTextField();
		txtPH.setBounds(703, 107, 146, 26);
		frmQL_TKB.getContentPane().add(txtPH);
		txtPH.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 217, 1055, 371);
		frmQL_TKB.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DefaultTableModel dfModel = (DefaultTableModel)table.getModel();
				int selectedRowIndex = table.getSelectedRow();
				txtMH.setText(dfModel.getValueAt(selectedRowIndex, 1).toString());
				txtTenMH.setText(dfModel.getValueAt(selectedRowIndex, 2).toString());
				txtPH.setText(dfModel.getValueAt(selectedRowIndex, 3).toString());
				
			}
		});
		scrollPane.setViewportView(table);
	}
}
