package source;
import java.awt.EventQueue;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.code.QuanLiSinhVien;
import net.code.ThoiKb;
import net.code.thoiHanPhucKhao;
import java.util.Date;
import com.toedter.calendar.JDateChooser;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PhucKhaoForm {

	private JFrame frmPhucKhaoForm;
	private JTable table;
	private JDateChooser dateBD;
	private JDateChooser dateKT;

	
	public JFrame getfrmPhucKhaoForm() {
		
		return frmPhucKhaoForm;
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PhucKhaoForm window = new PhucKhaoForm();
					window.frmPhucKhaoForm.setVisible(true);
					window.frmPhucKhaoForm.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	@SuppressWarnings("rawtypes")
	private void loadTKB() {
		try {
			QuanLiSinhVien.begin();
			List<ThoiKb> tkb = QuanLiSinhVien.queryTKB("SELECT t FROM ThoiKb t ") ;
			QuanLiSinhVien.end();	
				
			String[] columsName = new String[] {
						"STT","Mã Môn Học","Tên Môn Học","Phòng Học", "Lớp","Phúc Khảo"						
				};
				Object[][] content = new Object[tkb.size()][6];
				final Class[] columnClass = new Class[] {
			            Integer.class, String.class, String.class, String.class, String.class, Boolean.class};
				int stt = 1;
				for(int i = 0; i < tkb.size(); i++) {
							
					content[i][0] = stt++;
					content[i][1] = tkb.get(i).getMa_mh();
					content[i][2] = tkb.get(i).getTen_mh();
					content[i][3] = tkb.get(i).getPhong_hoc();
					content[i][4] = tkb.get(i).getLop();		
					content[i][5] = true;
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
			}catch (Exception e2) {
			
			e2.printStackTrace();
			
			}
	}
	/**
	 * Create the application.
	 */
	public PhucKhaoForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPhucKhaoForm = new JFrame();
		frmPhucKhaoForm.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				try {
				loadTKB();
				QuanLiSinhVien.begin();
				List <thoiHanPhucKhao> th = QuanLiSinhVien.getThoiHan();				
				QuanLiSinhVien.end();
				String bd = String.valueOf(th.get(0).getNgay_bd()) +"-"+String.valueOf(th.get(0).getThang_bd()+"-"+th.get(0).getNam_bd()) ;
				String kt = String.valueOf(th.get(0).getNgay_kt()) +"-"+String.valueOf(th.get(0).getThang_kt()+"-"+th.get(0).getNam_kt()) ;
				Date s = new SimpleDateFormat("dd-MM-yyyy").parse(bd);
				Date s2 = new SimpleDateFormat("dd-MM-yyyy").parse(kt);
				dateBD.setDate(s);
				dateKT.setDate(s2);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
				
			}
		});
		frmPhucKhaoForm.setBounds(100, 100, 1107, 660);
		frmPhucKhaoForm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPhucKhaoForm.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 171, 1055, 433);
		frmPhucKhaoForm.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		dateKT = new JDateChooser();
		dateKT.setBounds(722, 69, 248, 31);
		frmPhucKhaoForm.getContentPane().add(dateKT);
		
		JLabel lblTaoPK = new JLabel("GIÁO VỤ TẠO PHÚC KHẢO");
		lblTaoPK.setHorizontalAlignment(SwingConstants.CENTER);
		lblTaoPK.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTaoPK.setForeground(Color.RED);
		lblTaoPK.setBounds(15, 0, 1055, 68);
		frmPhucKhaoForm.getContentPane().add(lblTaoPK);
		
		JLabel lblNgayBD = new JLabel("Ngày bắt đầu");
		lblNgayBD.setBounds(102, 69, 113, 31);
		frmPhucKhaoForm.getContentPane().add(lblNgayBD);
		
		JLabel lblNgayKT = new JLabel("Ngày kết thúc");
		lblNgayKT.setBounds(596, 69, 127, 31);
		frmPhucKhaoForm.getContentPane().add(lblNgayKT);
		
		dateBD = new JDateChooser();
		dateBD.setBounds(220, 69, 248, 31);
		frmPhucKhaoForm.getContentPane().add(dateBD);
		
		JButton btnTaoPK = new JButton("Tạo Phúc Khảo");
		btnTaoPK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog(null, " Xác nhận ?", "Confirm",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (dialogResult == JOptionPane.YES_OPTION) {
				
					try {
						SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
						String[] thoigianBd =date.format(dateBD.getDate()).toString().split("-");
						String[] thoigianKt =date.format(dateKT.getDate()).toString().split("-");
						int stt =1;
						int ngay_bd = Integer.parseInt(thoigianBd[0]);
						int thang_bd = Integer.parseInt(thoigianBd[1]);
						int nam_bd = Integer.parseInt(thoigianBd[2]);
						int ngay_kt = Integer.parseInt(thoigianKt[0]);
						int thang_kt = Integer.parseInt(thoigianKt[1]);
						int nam_kt = Integer.parseInt(thoigianKt[2]);
						QuanLiSinhVien.begin();
						QuanLiSinhVien.createThoiHanPK(stt, ngay_bd, thang_bd, nam_bd, ngay_kt, thang_kt, nam_kt);
						QuanLiSinhVien.end();
					}catch(Exception ioe) {
						
						ioe.printStackTrace();
					}
				}
			}
		});
		btnTaoPK.setBounds(102, 115, 139, 40);
		frmPhucKhaoForm.getContentPane().add(btnTaoPK);
		
		JButton btnQuayLai = new JButton("Quay Lại");
		btnQuayLai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frmPhucKhaoForm.setVisible(false);
				MainWindow main = new MainWindow();
				main.getFrmMainWindow().setLocationRelativeTo(null);
				main.getFrmMainWindow().setVisible(true);
			}
		});
		btnQuayLai.setBounds(831, 116, 139, 40);
		frmPhucKhaoForm.getContentPane().add(btnQuayLai);
		
		JButton btnNewButton = new JButton("Cập Nhật");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog(null, " Xác nhận ?", "Confirm",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (dialogResult == JOptionPane.YES_OPTION) {
					try {
						SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");
						String[] thoigianBd =date.format(dateBD.getDate()).toString().split("-");
						String[] thoigianKt =date.format(dateKT.getDate()).toString().split("-");
						int ngay_bd = Integer.parseInt(thoigianBd[0]);
						int thang_bd = Integer.parseInt(thoigianBd[1]);
						int nam_bd = Integer.parseInt(thoigianBd[2]);
						int ngay_kt = Integer.parseInt(thoigianKt[0]);
						int thang_kt = Integer.parseInt(thoigianKt[1]);
						int nam_kt = Integer.parseInt(thoigianKt[2]);
						
						QuanLiSinhVien.begin();
						QuanLiSinhVien.updateThoiHan(ngay_bd, thang_bd, nam_bd, ngay_kt, thang_kt, nam_kt);
						QuanLiSinhVien.end();
						JOptionPane.showMessageDialog(frmPhucKhaoForm, "Đã cập nhật thành công");
					}catch(Exception ioe) {
						JOptionPane.showMessageDialog(frmPhucKhaoForm, "Cập nhật không thành công");
						ioe.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setBounds(279, 116, 139, 39);
		frmPhucKhaoForm.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Xóa");
		btnNewButton_1.setBounds(464, 116, 139, 39);
		frmPhucKhaoForm.getContentPane().add(btnNewButton_1);
		
		JButton btnQLPK = new JButton("QL-Phúc Khảo");
		btnQLPK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmPhucKhaoForm.setVisible(false);
				QLPhucKhao pk = new QLPhucKhao();
				pk.getfrm_QLPK().setLocationRelativeTo(null);
				pk.getfrm_QLPK().setVisible(true);;
			}
		});
		btnQLPK.setBounds(648, 116, 145, 39);
		frmPhucKhaoForm.getContentPane().add(btnQLPK);
		
		
		
	}
}
