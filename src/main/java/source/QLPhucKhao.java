package source;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import net.code.QuanLiSinhVien;
import net.code.phucKhao;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QLPhucKhao {

	private JFrame frm_QLPK;
	private JTextField txtMaSV;
	private JTextField txtHoTen;
	private JTable table;
	private JTextField txtMonHoc;
	private JTextField txtCotDiemPK;
	private JTextField txtDiemMongMuon;
	private JTextField txtLiDo;
	private JComboBox<String> cbbTinhTrang;

	public JFrame getfrm_QLPK() {
		
		return frm_QLPK;
	}
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QLPhucKhao window = new QLPhucKhao();
					window.frm_QLPK.setVisible(true);
					window.frm_QLPK.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	private void loadPhucKhao() {
		try {
			QuanLiSinhVien.begin();
			List<phucKhao> pk = QuanLiSinhVien.getPhucKhao();
			QuanLiSinhVien.end();
			String[] columsName = new String[] {
					"STT","Mã Sinh Viên","Họ Tên","Môn Học", "Cột Điểm Phúc Khảo", 	"Điểm Mong Muốn", "Lí Do" ,	"Tình Trạng"					
			};
			Object[][] content = new Object[pk.size()][8];
			int stt = 1;
			for(int i = 0; i < pk.size(); i++) {
						
				content[i][0] = stt++;
				content[i][1] = pk.get(i).getMa_sv();
				content[i][2] = pk.get(i).getHo_ten();
				content[i][3] = pk.get(i).getMon_hoc();
				content[i][4] = pk.get(i).getCot_diem_phuc_khao();	
				content[i][5] = pk.get(i).getDiem_mong_muon();
				content[i][6] = pk.get(i).getLi_do();
				content[i][7] = pk.get(i).getTinh_trang();
										
			}
			table.setModel(new DefaultTableModel(content,columsName));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public QLPhucKhao() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frm_QLPK = new JFrame();
		frm_QLPK.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				
				loadPhucKhao();
				
			}
		});
		frm_QLPK.setBounds(100, 100, 1107, 660);
		frm_QLPK.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm_QLPK.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã Sinh Viên");
		lblNewLabel.setBounds(83, 81, 102, 20);
		frm_QLPK.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Họ Tên");
		lblNewLabel_1.setBounds(83, 123, 102, 20);
		frm_QLPK.getContentPane().add(lblNewLabel_1);
		
		txtMaSV = new JTextField();
		txtMaSV.setBounds(195, 78, 146, 26);
		frm_QLPK.getContentPane().add(txtMaSV);
		txtMaSV.setColumns(10);
		
		txtHoTen = new JTextField();
		txtHoTen.setBounds(195, 120, 146, 26);
		frm_QLPK.getContentPane().add(txtHoTen);
		txtHoTen.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 281, 1055, 323);
		frm_QLPK.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				DefaultTableModel dfModel =  (DefaultTableModel)table.getModel();
				int selectedRowIndex = table.getSelectedRow();
				txtMaSV.setText(dfModel.getValueAt(selectedRowIndex, 1).toString());
				txtHoTen.setText(dfModel.getValueAt(selectedRowIndex, 2).toString());
				txtMonHoc.setText(dfModel.getValueAt(selectedRowIndex, 3).toString());
				txtCotDiemPK.setText(dfModel.getValueAt(selectedRowIndex, 4).toString());
				txtDiemMongMuon.setText(dfModel.getValueAt(selectedRowIndex, 5).toString());
				txtLiDo.setText(dfModel.getValueAt(selectedRowIndex, 6).toString());
				cbbTinhTrang.setSelectedItem(dfModel.getValueAt(selectedRowIndex, 7).toString());
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel_2 = new JLabel("Môn Học");
		lblNewLabel_2.setBounds(376, 81, 69, 20);
		frm_QLPK.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Cột Điểm Phúc Khảo");
		lblNewLabel_3.setBounds(376, 123, 156, 20);
		frm_QLPK.getContentPane().add(lblNewLabel_3);
		
		txtMonHoc = new JTextField();
		txtMonHoc.setBounds(547, 78, 146, 26);
		frm_QLPK.getContentPane().add(txtMonHoc);
		txtMonHoc.setColumns(10);
		
		txtCotDiemPK = new JTextField();
		txtCotDiemPK.setBounds(547, 120, 146, 26);
		frm_QLPK.getContentPane().add(txtCotDiemPK);
		txtCotDiemPK.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Điểm Mong Muốn");
		lblNewLabel_4.setBounds(724, 81, 125, 20);
		frm_QLPK.getContentPane().add(lblNewLabel_4);
		
		JLabel TinhTrang = new JLabel("Tình Trạng");
		TinhTrang.setBounds(724, 123, 125, 20);
		frm_QLPK.getContentPane().add(TinhTrang);
		
		txtDiemMongMuon = new JTextField();
		txtDiemMongMuon.setBounds(868, 78, 146, 26);
		frm_QLPK.getContentPane().add(txtDiemMongMuon);
		txtDiemMongMuon.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Lí Do");
		lblNewLabel_6.setBounds(83, 164, 69, 37);
		frm_QLPK.getContentPane().add(lblNewLabel_6);
		
		txtLiDo = new JTextField();
		txtLiDo.setBounds(195, 162, 819, 40);
		frm_QLPK.getContentPane().add(txtLiDo);
		txtLiDo.setColumns(10);
		
		cbbTinhTrang = new JComboBox<String>();
		cbbTinhTrang.setBounds(868, 120, 146, 26);
		cbbTinhTrang.addItem("Đã cập nhật điểm");
		cbbTinhTrang.addItem("Không cập nhật điểm");
		cbbTinhTrang.addItem("Chưa xem");
		
		frm_QLPK.getContentPane().add(cbbTinhTrang);
		
		JLabel lblTieuDe = new JLabel("QUẢN LÝ PHÚC KHẢO");
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTieuDe.setForeground(Color.RED);
		lblTieuDe.setBounds(15, 16, 1055, 40);
		frm_QLPK.getContentPane().add(lblTieuDe);
		
		JButton btnCapNhat = new JButton("Cập Nhật");
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int dialogResult = JOptionPane.showConfirmDialog(null, " Xác nhận ?", "Confirm",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (dialogResult == JOptionPane.YES_OPTION) {
					try {
						String ma_sv = txtMaSV.getText();
						String ho_ten = txtHoTen.getText();
						String mon_hoc = txtMonHoc.getText();
						String cot_diem_phuc_khao = txtCotDiemPK.getText();
						float diem_mong_muon = Float.parseFloat(txtDiemMongMuon.getText());
						String li_do = txtLiDo.getText();
						String tinh_trang = cbbTinhTrang.getSelectedItem().toString();
						QuanLiSinhVien.begin();
						QuanLiSinhVien.updatePhucKhao(ma_sv, ho_ten, mon_hoc, cot_diem_phuc_khao, diem_mong_muon, li_do, tinh_trang);
						QuanLiSinhVien.end();
						loadPhucKhao();
						JOptionPane.showMessageDialog(frm_QLPK, "Cập nhật thành công");
					}catch(Exception e2) {
						
						JOptionPane.showMessageDialog(frm_QLPK, "Cập nhật thất bại");
						e2.printStackTrace();
					}
				}
			}
		});
		btnCapNhat.setBounds(596, 218, 137, 47);
		frm_QLPK.getContentPane().add(btnCapNhat);
		
		JButton btnQuayLai = new JButton("Quay Lại");
		btnQuayLai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frm_QLPK.setVisible(false);
				MainWindow main = new MainWindow();
				main.getFrmMainWindow().setLocationRelativeTo(null);
				main.getFrmMainWindow().setVisible(true);
			}
		});
		btnQuayLai.setBounds(835, 218, 137, 47);
		frm_QLPK.getContentPane().add(btnQuayLai);
		
		JButton btnNewButton = new JButton("Thêm");
		btnNewButton.setBounds(129, 218, 137, 47);
		frm_QLPK.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Xóa");
		btnNewButton_1.setBounds(363, 218, 137, 47);
		frm_QLPK.getContentPane().add(btnNewButton_1);
	}
}
