package source;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import net.code.Diem;
import net.code.QuanLiSinhVien;
import net.code.thoiHanPhucKhao;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
public class XemDiemForm {

	private JFrame frmThongTinDiem;
	private JTable table;
	private JLabel lblMSSV;
	private JLabel lblXinChao;
	private JLabel lblXinChao2;
	private JButton btnPhucKhao;
	private String ma_sv;
	private JLabel lblThoiHanPK;
	
	
	public String getMa_sv() {
		return ma_sv;
	}
	public void setMa_sv(String ma_sv) {
		this.ma_sv = ma_sv;
	}
	public JFrame getJFrame() {
		
		return frmThongTinDiem;
	}
	public JLabel getlblMSSV() {
		
		return lblMSSV;
	}
	public void  setlblMSSV (String maSV){
		
		this.lblMSSV.setText(maSV);
	}
	
	
	
	public String dauRot(float _tongDiem) {

		if(_tongDiem < 5.0 )
		{
			return "Rớt";
		}
		return "Đậu";
	}
	
	void readDiem() {

		try {
			
			Date phuckhaoBD, phuckhaoKT ;
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
			Date dateCurrent = new Date();
			String dateCurrent2 = formatter.format(dateCurrent);
			//String maSV = lblMSSV.getText().toString();
			//String maSV = ma_sv;
			Date dateCurrent3 = new SimpleDateFormat("dd-MM-yyyy").parse(dateCurrent2);
			QuanLiSinhVien.begin();
			List<thoiHanPhucKhao> thoihan = QuanLiSinhVien.getThoiHan();
			
			List<Diem> dsLop = QuanLiSinhVien.dsMonHoc("SELECT d FROM Diem d WHERE d.ma_sv = '" + ma_sv + "'");
			
			String bd = String.valueOf(thoihan.get(0).getNgay_bd()) + "-" + String.valueOf(thoihan.get(0).getThang_bd()) + "-" + String.valueOf(thoihan.get(0).getNam_bd()) ;
			String kt = String.valueOf(thoihan.get(0).getNgay_kt()) + "-" + String.valueOf(thoihan.get(0).getThang_kt()) + "-" + String.valueOf(thoihan.get(0).getNam_kt()) ;
			lblThoiHanPK.setText("Thời gian phúc khảo từ "+bd+" đến "+kt);
			phuckhaoBD = new SimpleDateFormat("dd-MM-yyyy").parse(bd);
			phuckhaoKT = new SimpleDateFormat("dd-MM-yyyy").parse(kt);
			if((dateCurrent3.compareTo(phuckhaoBD) > 0 ||dateCurrent3.compareTo(phuckhaoBD) == 0) &&
			   (dateCurrent3.compareTo(phuckhaoKT) < 0 ||dateCurrent3.compareTo(phuckhaoKT) == 0)) 
			{
				
				btnPhucKhao.setEnabled(true);
			}
			
			lblXinChao2.setText(dsLop.get(0).getHo_ten().toString().toUpperCase());
			
			String[] columsName = new String[] {
					"STT","Lớp Môn Học","Mã Môn Học","Tên Môn Học","Điểm Giữa Kỳ", "Điểm Cuối Kỳ", "Điểm Khác", "Tổng Điểm", "Kết Quả", "Phúc Khảo"				
			};
			Object[][] content = new Object[dsLop.size()][10];
			@SuppressWarnings("rawtypes")
			final Class[] columnClass = new Class[] {
		            Integer.class, String.class, String.class, String.class, Float.class, Float.class, Float.class, Float.class, String.class , Boolean.class};
			int sTT = 1;
			for(int i = 0; i < dsLop.size(); i++) {
						
					content[i][0] = Integer.toString(sTT++);
					content[i][1] = dsLop.get(i).getLop_mh();
					content[i][2] = dsLop.get(i).getMa_mh();
					content[i][3] = QuanLiSinhVien.getTenMH(dsLop.get(i).getMa_mh());
					content[i][4] = dsLop.get(i).getDiem_gk();
					content[i][5] = dsLop.get(i).getDiem_ck();
					content[i][6] = dsLop.get(i).getDiem_khac();
					content[i][7] = dsLop.get(i).getDiem_tong();
					content[i][8] = dauRot(dsLop.get(i).getDiem_tong());
					content[i][9] = false;
			}
			@SuppressWarnings("serial")
			DefaultTableModel model = new DefaultTableModel(content,columsName) {
				
				@Override
	            public boolean isCellEditable(int row, int column)
	            {
	                return true;
	            }
	            @Override
	            public Class<?> getColumnClass(int columnIndex)
	            {
	                return columnClass[columnIndex];
	            }
			};
			table.setModel(model);
			QuanLiSinhVien.end();
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
	}
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
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
	}

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
		
				
				readDiem();
				
			}
			@Override
			public void windowClosed(WindowEvent arg0) {
				btnPhucKhao.setEnabled(false);
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
		lblXinChao.setBounds(15, 16, 1055, 59);
		frmThongTinDiem.getContentPane().add(lblXinChao);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 119, 1055, 485);
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
		btnLogout.setBounds(920, 46, 115, 29);
		frmThongTinDiem.getContentPane().add(btnLogout);
		
		JLabel lblLogOut = new JLabel("\u0110\u0103ng Xu\u1EA5t");
		lblLogOut.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogOut.setBounds(920, 16, 115, 29);
		frmThongTinDiem.getContentPane().add(lblLogOut);
		
		lblMSSV = new JLabel("");
		lblMSSV.setHorizontalAlignment(SwingConstants.CENTER);
		lblMSSV.setBounds(15, 0, 96, 36);
		frmThongTinDiem.getContentPane().add(lblMSSV);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(".\\images\\Add.png"));
		lblNewLabel.setBounds(37, 26, 63, 77);
		frmThongTinDiem.getContentPane().add(lblNewLabel);
		
		lblXinChao2 = new JLabel("");
		lblXinChao2.setForeground(Color.RED);
		lblXinChao2.setHorizontalAlignment(SwingConstants.CENTER);
		lblXinChao2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblXinChao2.setBounds(367, 59, 346, 36);
		frmThongTinDiem.getContentPane().add(lblXinChao2);
		
		btnPhucKhao = new JButton("Phúc Khảo");
		btnPhucKhao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmThongTinDiem.setVisible(false);
				FormDienThongtinPhucKhao dientt = new FormDienThongtinPhucKhao();
				dientt.getfrmDienTT().setLocationRelativeTo(null);
				dientt.getfrmDienTT().setVisible(true);
				dientt.setMa_sv(ma_sv);
			}
		});
		btnPhucKhao.setEnabled(false);
		btnPhucKhao.setBounds(920, 80, 115, 29);
		frmThongTinDiem.getContentPane().add(btnPhucKhao);
		
		lblThoiHanPK = new JLabel("");
		lblThoiHanPK.setBounds(631, 80, 280, 29);
		frmThongTinDiem.getContentPane().add(lblThoiHanPK);
	}
}
