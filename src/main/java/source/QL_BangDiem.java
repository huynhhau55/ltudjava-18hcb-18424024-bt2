package source;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import net.code.Diem;
import net.code.QuanLiSinhVien;
import net.code.ThoiKb;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class QL_BangDiem {

	private JFrame frmQLBD;
	private JTable table;
	private JTextField txtHoTen;
	private JTextField txtDiemGK;
	private JTextField txtDiemCK;
	private JTextField txtDiemKhac;
	private JTextField txtMSSV;
	private JTextField txtDau;
	private JTextField txtRot;
	private JComboBox<String> cbbMonHoc;
	private JTextField txtDiemTong;
	
	
	

	/**
	 * Launch the application.
	 */
	public JFrame getfrmQLBD() {
		
		return this.frmQLBD;
		
	}
	public JComboBox<String> getcbbMonHoc()
	{
		return this.cbbMonHoc;
		
	}
	public String dauRot(float _tongDiem) {
		
		if(_tongDiem < 5.0 )
		{
			return "Rớt";
		}
		return "Đậu" ;
	}
	public void loadBangDiem() {
		
		int Dau = 0; int Rot = 0; double DauPercent; double RotPercent;
		String cbb = cbbMonHoc.getSelectedItem().toString();
		String[] split = cbb.split("-");
		String lop_mh = split[0].replace(" ", "");
		String ma_mh = split[1].replace(" ", "");
		try {
			QuanLiSinhVien.begin();
			List<Diem> elements = QuanLiSinhVien.fillCBBDiem("SELECT d FROM Diem d WHERE d.ma_mh ='" + ma_mh + "' AND d.lop_mh = '" + lop_mh + "'");
			QuanLiSinhVien.end();
			
			String[] columsName = new String[] {
					"STT","MSSV","Họ Tên","Điểm Giữa Kỳ", "Điểm Cuối Kỳ", "Điểm Khác", "Tổng Điểm", "Kết Quả"					
			};
			Object[][] content = new Object[elements.size()][8];
			int stt = 0;
			for(int i = 0; i < elements.size(); i++) {

				content[i][7] = dauRot(elements.get(i).getDiem_tong());
				if(content[i][7].toString().equalsIgnoreCase("Đậu")){
							
					Dau++;
				}else if(content[i][7].toString().equalsIgnoreCase("Rớt")) {
							
					Rot++;
				}
				content[i][0] = ++stt;
				content[i][1] = elements.get(i).getMa_sv();
				content[i][2] = elements.get(i).getHo_ten();
				content[i][3] = elements.get(i).getDiem_gk();
				content[i][4] = elements.get(i).getDiem_ck();
				content[i][5] = elements.get(i).getDiem_khac();
				content[i][6] = elements.get(i).getDiem_tong();
			}
			table.setModel(new DefaultTableModel(content,columsName));
			
			int Tong = Dau + Rot;
			if(Tong != 0) {
				DauPercent = Math.round(((Dau * 100) / Tong)*100.0)/100.0;
				RotPercent = 100 - DauPercent;
			}
			else {
				DauPercent = RotPercent = 0;
			}
			txtDau.setText(Integer.toString(Dau) + " - " + Double.toString(DauPercent) + "%");
			txtRot.setText(Integer.toString(Rot) + " - " + Double.toString(RotPercent) + "%");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					QL_BangDiem window = new QL_BangDiem();
					window .frmQLBD.setLocationRelativeTo(null);
					window.frmQLBD.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public QL_BangDiem() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmQLBD = new JFrame();
		frmQLBD.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				
				String tencbb = null;
				QuanLiSinhVien.begin();
				List<ThoiKb> LopMH = QuanLiSinhVien.getLopMhAndMaMH();
				for(int i = 0 ; i < LopMH.size() ; i++) {
					tencbb = LopMH.get(i).getLop().toString() +" - "+ LopMH.get(i).getMa_mh().toString() + " - " + LopMH.get(i).getTen_mh().toString();
					cbbMonHoc.addItem(tencbb);
				}
				QuanLiSinhVien.end();
			}
		});
		frmQLBD.setTitle("QU\u1EA2N L\u00DD B\u1EA2NG \u0110I\u1EC2M");
		frmQLBD.setBounds(100, 100, 1107, 661);
		frmQLBD.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmQLBD.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("QU\u1EA2N L\u00DD B\u1EA2NG \u0110I\u1EC2M");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(129, 0, 816, 43);
		frmQLBD.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 222, 1055, 343);
		frmQLBD.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				DefaultTableModel dfModel =  (DefaultTableModel)table.getModel();
				int selectedRowIndex = table.getSelectedRow();
				txtMSSV.setText(dfModel.getValueAt(selectedRowIndex, 1).toString());
				txtHoTen.setText(dfModel.getValueAt(selectedRowIndex, 2).toString());
				txtDiemGK.setText(dfModel.getValueAt(selectedRowIndex, 3).toString());
				txtDiemCK.setText(dfModel.getValueAt(selectedRowIndex, 4).toString());
				txtDiemKhac.setText(dfModel.getValueAt(selectedRowIndex, 5).toString());
				txtDiemTong.setText(dfModel.getValueAt(selectedRowIndex, 6).toString());
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_1 = new JLabel("M\u00F4n h\u1ECDc");
		lblNewLabel_1.setBounds(129, 45, 69, 20);
		frmQLBD.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("MSSV");
		lblNewLabel_2.setBounds(129, 87, 69, 20);
		frmQLBD.getContentPane().add(lblNewLabel_2);
		
		cbbMonHoc = new JComboBox<String>();
		cbbMonHoc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				loadBangDiem();
			}
		});
		cbbMonHoc.setBounds(234, 42, 701, 26);
		frmQLBD.getContentPane().add(cbbMonHoc);
		
		JLabel lblNewLabel_3 = new JLabel("H\u1ECD t\u00EAn");
		lblNewLabel_3.setBounds(129, 126, 69, 20);
		frmQLBD.getContentPane().add(lblNewLabel_3);
		
		txtHoTen = new JTextField();
		txtHoTen.setBounds(234, 123, 146, 26);
		frmQLBD.getContentPane().add(txtHoTen);
		txtHoTen.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("\u0110i\u1EC3m GK");
		lblNewLabel_4.setBounds(426, 87, 69, 20);
		frmQLBD.getContentPane().add(lblNewLabel_4);
		
		txtDiemGK = new JTextField();
		txtDiemGK.setBounds(510, 84, 146, 26);
		frmQLBD.getContentPane().add(txtDiemGK);
		txtDiemGK.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("\u0110i\u1EC3m CK");
		lblNewLabel_5.setBounds(426, 126, 69, 20);
		frmQLBD.getContentPane().add(lblNewLabel_5);
		
		txtDiemCK = new JTextField();
		txtDiemCK.setBounds(510, 123, 146, 26);
		frmQLBD.getContentPane().add(txtDiemCK);
		txtDiemCK.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("\u0110i\u1EC3m kh\u00E1c");
		lblNewLabel_6.setBounds(692, 87, 82, 20);
		frmQLBD.getContentPane().add(lblNewLabel_6);
		
		txtDiemKhac = new JTextField();
		txtDiemKhac.setBounds(789, 84, 146, 26);
		frmQLBD.getContentPane().add(txtDiemKhac);
		txtDiemKhac.setColumns(10);
		
		txtMSSV = new JTextField();
		txtMSSV.setBounds(234, 84, 146, 26);
		frmQLBD.getContentPane().add(txtMSSV);
		txtMSSV.setColumns(10);
		
		JButton btnThem = new JButton("Th\u00EAm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String valueCbbMonHoc = cbbMonHoc.getSelectedItem().toString();
				String filePath = ".\\Data\\Diem\\" + valueCbbMonHoc.substring(0,valueCbbMonHoc.lastIndexOf("-")) + ".csv";
				List<BangDiem> bangDiemS = BangDiem.readBangDiem(filePath);
				int size = bangDiemS.size() + 1;
				String STT = Integer.toString(size);
				try(PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filePath,true),StandardCharsets.UTF_8))){
					
					pw.println(STT + ";"+ txtMSSV.getText() + ";" + txtHoTen.getText() + ";" + 
								txtDiemGK.getText() + ";" + txtDiemCK.getText() + ";" +txtDiemKhac.getText() + ";" + txtDiemTong.getText());
					pw.close();
					loadBangDiem();
				}catch(Exception e) {
					
					e.printStackTrace();				
				}
			}
		});
		btnThem.setBounds(129, 165, 156, 43);
		frmQLBD.getContentPane().add(btnThem);
		
		JButton btnXoa = new JButton("X\u00F3a");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String valueCbbMonHoc = cbbMonHoc.getSelectedItem().toString();
				String filePath = ".\\Data\\Diem\\" + valueCbbMonHoc.substring(0,valueCbbMonHoc.lastIndexOf("-")) + ".csv";
				List<BangDiem> bangDiemS = BangDiem.readBangDiem(filePath);
				for(BangDiem b : bangDiemS) {
					
					if(b.getMaSV().equalsIgnoreCase(txtMSSV.getText())) {
						bangDiemS.remove(b);break;
					}
				}
				try(PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filePath,false),StandardCharsets.UTF_8))){
						
					pw.println("STT;MSSV;Họ tên;Điểm GK;Điểm CK;Điểm Khác;Điểm Tổng");
					int stt = 1;
					for(BangDiem b : bangDiemS) {
						
						pw.println(Integer.toString(stt++) + ";" + b.getMaSV() + ";" + b.getHoTen() + ";" + b.getDiemGK() 
									+ ";" + b.getDiemCK()+";" + b.getDiemKhac() + ";" + b.getDiemTong());
				
					}
						pw.close();
						loadBangDiem();
				}catch(Exception e) {
						
						e.printStackTrace();				
				}
			}
		});
		btnXoa.setBounds(365, 171, 151, 43);
		frmQLBD.getContentPane().add(btnXoa);
		
		JButton btnCapNhat = new JButton("C\u1EADp nh\u1EADt");
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String valueCbbMonHoc = cbbMonHoc.getSelectedItem().toString();
				String filePath = ".\\Data\\Diem\\" + valueCbbMonHoc.substring(0,valueCbbMonHoc.lastIndexOf("-")) + ".csv";
				List<BangDiem> bangDiemS = BangDiem.readBangDiem(filePath);
				for(BangDiem b : bangDiemS) {
					
					if(b.getMaSV().equalsIgnoreCase(txtMSSV.getText())) {
						
						b.setDiemGK(Float.parseFloat(txtDiemGK.getText()));
						b.setDiemCK(Float.parseFloat(txtDiemCK.getText()));
						b.setDiemKhac(Float.parseFloat(txtDiemKhac.getText()));
						b.setDiemTong(Float.parseFloat(txtDiemTong.getText()));
						break;
						
					}	
				}
				
				try(PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filePath,false),StandardCharsets.UTF_8))){
					
					pw.println("STT;MSSV;Họ tên;Điểm GK;Điểm CK;Điểm Khác;Điểm Tổng");
					int stt = 1;
					for(BangDiem b : bangDiemS) {
						
						pw.println(Integer.toString(stt++) + ";" + b.getMaSV() + ";" + b.getHoTen() + ";" + b.getDiemGK() + 
													";" + b.getDiemCK()+";" + b.getDiemKhac() + ";" + b.getDiemTong());
				
					}
						pw.close();
						loadBangDiem();
				}catch(Exception e) {
						
						e.printStackTrace();				
				}
				
				
			}
		});
		btnCapNhat.setBounds(584, 171, 156, 43);
		frmQLBD.getContentPane().add(btnCapNhat);
		
		JButton btnQuayLai = new JButton("Quay l\u1EA1i");
		btnQuayLai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmQLBD.setVisible(false);
				BangDiemForm bangDiem = new BangDiemForm();
				bangDiem.getFrmDiem().setLocationRelativeTo(null);
				bangDiem.getFrmDiem().setVisible(true);
				
				
			}
		});
		btnQuayLai.setBounds(789, 171, 146, 43);
		frmQLBD.getContentPane().add(btnQuayLai);
		
		JLabel lblNewLabel_7 = new JLabel("\u0110\u1EADu");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_7.setBounds(309, 581, 41, 20);
		frmQLBD.getContentPane().add(lblNewLabel_7);
		
		txtDau = new JTextField();
		txtDau.setForeground(Color.RED);
		txtDau.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtDau.setHorizontalAlignment(SwingConstants.CENTER);
		txtDau.setEditable(false);
		txtDau.setBounds(365, 578, 130, 26);
		frmQLBD.getContentPane().add(txtDau);
		txtDau.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("R\u1EDBt");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_8.setBounds(525, 581, 41, 20);
		frmQLBD.getContentPane().add(lblNewLabel_8);
		
		txtRot = new JTextField();
		txtRot.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtRot.setForeground(Color.RED);
		txtRot.setHorizontalAlignment(SwingConstants.CENTER);
		txtRot.setEditable(false);
		txtRot.setBounds(584, 578, 130, 26);
		frmQLBD.getContentPane().add(txtRot);
		txtRot.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Điểm Tổng");
		lblNewLabel_9.setBounds(692, 126, 88, 20);
		frmQLBD.getContentPane().add(lblNewLabel_9);
		
		txtDiemTong = new JTextField();
		txtDiemTong.setBounds(789, 123, 146, 26);
		frmQLBD.getContentPane().add(txtDiemTong);
		txtDiemTong.setColumns(10);
	}
}
