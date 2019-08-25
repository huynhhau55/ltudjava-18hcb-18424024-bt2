package source;

//import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import net.code.Diem;
import net.code.QuanLiSinhVien;
import net.code.phucKhao;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class FormDienThongtinPhucKhao {

	private JFrame frmDienTT;
	private JTextField txtMSSV;
	private JTextField txtHoten;
	private JTable table;
	private JTextField txtDiemMM;
	private String ma_sv;
	private JComboBox<String> cbbMon;
	private int sttPK = 1;
	private JTextArea txtLiDo;

	
	public JFrame getfrmDienTT() {
		
		return frmDienTT;
	}
	
	
	public String getMa_sv() {
		return ma_sv;
	}


	public void setMa_sv(String ma_sv) {
		this.ma_sv = ma_sv;
	}


	/**
	 * Launch the application.
	 */
	void filCombobox () {
		
	}
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormDienThongtinPhucKhao window = new FormDienThongtinPhucKhao();
					window.frmDienTT.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	void loadPhucKhao()
	{
		try {
			QuanLiSinhVien.begin();
			List<phucKhao> dsPhucKhao1 = QuanLiSinhVien.getPhucKhao();
			
			if(dsPhucKhao1.isEmpty())
			{
				JOptionPane.showMessageDialog(frmDienTT, "Thông tin phúc khảo trống");
				return;
			}
			else{
				
				List<phucKhao>  dsPhucKhao = QuanLiSinhVien.getPhucKhao2(ma_sv);
			
				String[] columsName = new String[] {
						"STT","Mã Sinh Viên","Họ Tên","Môn Học", "Cột Điểm Phúc Khảo", "Điểm mong muốn", "Lí Do"						
				};
				Object[][] content = new Object[dsPhucKhao.size()][7];
				int stt = 1;
				for(int i = 0; i < dsPhucKhao.size(); i++) {
							
					content[i][0] = stt++;
					content[i][1] = dsPhucKhao.get(i).getMa_sv();
					content[i][2] = dsPhucKhao.get(i).getHo_ten();
					content[i][3] = dsPhucKhao.get(i).getMon_hoc();
					content[i][4] = dsPhucKhao.get(i).getCot_diem_phuc_khao();
					content[i][5] = dsPhucKhao.get(i).getDiem_mong_muon();	
					content[i][6] = dsPhucKhao.get(i).getLi_do();
				}
				table.setModel(new DefaultTableModel(content,columsName));
				QuanLiSinhVien.end();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public FormDienThongtinPhucKhao() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDienTT = new JFrame();
		frmDienTT.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				
				QuanLiSinhVien.begin();
				List<Diem> dsLop = QuanLiSinhVien.dsMonHoc("SELECT d FROM Diem d WHERE d.ma_sv = '" + ma_sv + "'");
				txtHoten.setText(dsLop.get(0).getHo_ten());
				txtMSSV.setText(ma_sv);
				for(int i = 0 ; i < dsLop.size() ; i++) {
					cbbMon.addItem(QuanLiSinhVien.getTenMH(dsLop.get(i).getMa_mh()));
				}
				QuanLiSinhVien.end();
				loadPhucKhao();
			}
		});
		frmDienTT.setBounds(100, 100, 1009, 641);
		frmDienTT.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDienTT.getContentPane().setLayout(null);
		
		JLabel lblTieuDe = new JLabel("THÔNG TIN PHÚC KHẢO");
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTieuDe.setForeground(Color.RED);
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setBounds(0, 0, 987, 67);
		frmDienTT.getContentPane().add(lblTieuDe);
		
		JLabel lblNewLabel = new JLabel("Mã Sinh Viên");
		lblNewLabel.setBounds(95, 69, 110, 20);
		frmDienTT.getContentPane().add(lblNewLabel);
		
		txtMSSV = new JTextField();
		txtMSSV.setBounds(276, 66, 203, 26);
		frmDienTT.getContentPane().add(txtMSSV);
		txtMSSV.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Họ Tên");
		lblNewLabel_1.setBounds(95, 111, 69, 20);
		frmDienTT.getContentPane().add(lblNewLabel_1);
		
		txtHoten = new JTextField();
		txtHoten.setBounds(276, 108, 203, 26);
		frmDienTT.getContentPane().add(txtHoten);
		txtHoten.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Môn");
		lblNewLabel_2.setBounds(95, 231, 69, 20);
		frmDienTT.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Cột Điểm Phúc Khảo");
		lblNewLabel_3.setBounds(95, 189, 146, 26);
		frmDienTT.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Lí Do");
		lblNewLabel_4.setBounds(524, 69, 69, 20);
		frmDienTT.getContentPane().add(lblNewLabel_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 329, 957, 240);
		frmDienTT.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JComboBox<String> cbbCotDiemPK = new JComboBox<>();
		cbbCotDiemPK.setBounds(276, 189, 203, 26);
		cbbCotDiemPK.addItem("Điểm giữa kỳ");
		cbbCotDiemPK.addItem("Điểm cuối kỳ");
		cbbCotDiemPK.addItem("Điểm khác");
		cbbCotDiemPK.addItem("Điểm tổng");
		frmDienTT.getContentPane().add(cbbCotDiemPK);
		
		JLabel lblimMongMun = new JLabel("Điểm Mong Muốn");
		lblimMongMun.setBounds(95, 153, 160, 20);
		frmDienTT.getContentPane().add(lblimMongMun);
		
		txtDiemMM = new JTextField();
		txtDiemMM.setBounds(276, 147, 203, 26);
		frmDienTT.getContentPane().add(txtDiemMM);
		txtDiemMM.setColumns(10);
		
		cbbMon = new JComboBox<>();
		
		cbbMon.setBounds(276, 228, 203, 26);
		frmDienTT.getContentPane().add(cbbMon);
		
		JButton btnThem = new JButton("Thêm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int dialogResult = JOptionPane.showConfirmDialog(null, " Xác nhận ?", "Confirm",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (dialogResult == JOptionPane.YES_OPTION) {
					try {
						
						String ma_sv = txtMSSV.getText();
						String ho_ten = txtHoten.getText();
						String mon_hoc = cbbMon.getSelectedItem().toString();
						String cot_diem_phuc_khao = cbbCotDiemPK.getSelectedItem().toString();
						float diem_mong_muon = Float.parseFloat(txtDiemMM.getText());
						String li_do = txtLiDo.getText().toString();
						
						QuanLiSinhVien.begin();
						List<phucKhao> pk = QuanLiSinhVien.getSttPhucKhao2();
						if(pk.isEmpty()) {
							sttPK = 0;
						}
						else {
							
							sttPK = pk.size();
						}
						QuanLiSinhVien.createPhucKhao(++sttPK, ma_sv, ho_ten, mon_hoc, cot_diem_phuc_khao, diem_mong_muon, li_do);
						QuanLiSinhVien.end();
						loadPhucKhao();
						JOptionPane.showMessageDialog(frmDienTT, "Đã thêm thành công");
					}catch(Exception e) {
						
						e.printStackTrace();
					}
				}
			}
		});
		btnThem.setBounds(167, 270, 115, 43);
		frmDienTT.getContentPane().add(btnThem);
		
		JButton btnNewButton_1 = new JButton("Xóa");
		btnNewButton_1.setBounds(351, 270, 115, 43);
		frmDienTT.getContentPane().add(btnNewButton_1);
		
		JButton btnCapNhat = new JButton("Câp Nhật");
		btnCapNhat.setBounds(520, 270, 115, 43);
		frmDienTT.getContentPane().add(btnCapNhat);
		
		JButton btnQuayLai = new JButton("Quay Lại");
		btnQuayLai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmDienTT.setVisible(false);
				XemDiemForm diem = new XemDiemForm();
				diem.getJFrame().setLocationRelativeTo(null);
				diem.getJFrame().setVisible(true);
				diem.setMa_sv(ma_sv);
			}
		});
		btnQuayLai.setBounds(701, 270, 115, 43);
		frmDienTT.getContentPane().add(btnQuayLai);
		
		txtLiDo = new JTextArea();
		txtLiDo.setBounds(620, 69, 278, 182);
		frmDienTT.getContentPane().add(txtLiDo);
		
		
	     
	}
}
