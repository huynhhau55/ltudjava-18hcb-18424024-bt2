package source;
//import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import net.code.QuanLiSinhVien;
import net.code.ThoiKb;
import net.code.DsLopMh;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DanhSachLopMonHoc {

	private JFrame frame;
	private JTextField txtMSSV;
	private JTextField txtHoTen;
	private JTextField txtCMND;
	private JTable table;
	private JComboBox<String> cbbLop;
	private JComboBox<String> cbbGioiTinh;

	
	public JFrame getFrmDsLopMH() {
		
		return frame;
	}
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DanhSachLopMonHoc window = new DanhSachLopMonHoc();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	void loadDanhSachLopMH() {
		try {
			String cbb = cbbLop.getSelectedItem().toString();
			String[] split = cbb.split("-");
			String lop_mh = split[0].replace(" ", "");
			String ma_mh = split[1].replace(" ", "");
			QuanLiSinhVien.begin();
			List<DsLopMh> ds = QuanLiSinhVien.loadDsLMH("SELECT d FROM DsLopMh d WHERE d.lop_mh = '"+ lop_mh + "'"+" AND d.ma_mh = '" + ma_mh +"'");
			QuanLiSinhVien.end();
			String[] columsName = new String[] {
					"STT","MSSV","Họ Tên","Giới Tính", "CMND","Mã Môn Học", "Lớp Môn Học"							
			};
			Object[][] content = new Object[ds.size()][7];
			int stt = 1;
			for(int i = 0; i < ds.size(); i++) {
				
				content[i][0] = stt++;
				content[i][1] = ds.get(i).getMa_sv();
				content[i][2] = ds.get(i).getHo_ten();
				content[i][3] = ds.get(i).getGioi_tinh();
				content[i][4] = ds.get(i).getCmnd();
				content[i][5] = ds.get(i).getMa_mh();
				content[i][6] = ds.get(i).getLop_mh();
				
			}
			table.setModel(new DefaultTableModel(content,columsName));
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
	}
	public DanhSachLopMonHoc() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				String tencbb = null;
				QuanLiSinhVien.begin();
				List<ThoiKb> LopMH = QuanLiSinhVien.getLopMhAndMaMH();
				for(int i = 0 ; i < LopMH.size() ; i++) {
					tencbb = LopMH.get(i).getLop().toString() +" - "+ LopMH.get(i).getMa_mh().toString() + " - " + LopMH.get(i).getTen_mh().toString();
					cbbLop.addItem(tencbb);
				}
				QuanLiSinhVien.end();
				
			}
		});
		frame.setBounds(100, 100, 1107, 660);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã Sinh Viên");
		lblNewLabel.setBounds(93, 108, 92, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Họ Tên");
		lblNewLabel_1.setBounds(429, 102, 69, 26);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtMSSV = new JTextField();
		txtMSSV.setBounds(212, 105, 146, 26);
		frame.getContentPane().add(txtMSSV);
		txtMSSV.setColumns(10);
		
		txtHoTen = new JTextField();
		txtHoTen.setBounds(527, 102, 146, 26);
		frame.getContentPane().add(txtHoTen);
		txtHoTen.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Giới Tính");
		lblNewLabel_2.setBounds(725, 105, 69, 20);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("CMND");
		lblNewLabel_3.setBounds(725, 69, 69, 20);
		frame.getContentPane().add(lblNewLabel_3);
		
		txtCMND = new JTextField();
		txtCMND.setBounds(823, 66, 146, 26);
		frame.getContentPane().add(txtCMND);
		txtCMND.setColumns(10);
		
		JLabel lblDS = new JLabel("DANH SÁCH LỚP MÔN ");
		lblDS.setForeground(Color.RED);
		lblDS.setHorizontalAlignment(SwingConstants.CENTER);
		lblDS.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDS.setBounds(0, 0, 1070, 52);
		frame.getContentPane().add(lblDS);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 217, 1055, 387);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				DefaultTableModel dfModel = (DefaultTableModel)table.getModel();
				int selectedRowIndex = table.getSelectedRow();
				
				txtMSSV.setText(dfModel.getValueAt(selectedRowIndex,1).toString());
				txtHoTen.setText(dfModel.getValueAt(selectedRowIndex, 2).toString());
				cbbGioiTinh.setSelectedItem(dfModel.getValueAt(selectedRowIndex, 3).toString());
				txtCMND.setText(dfModel.getValueAt(selectedRowIndex, 4).toString());				
			}
		});
		scrollPane.setViewportView(table);
		
		cbbLop = new JComboBox<String>();
		cbbLop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String tieuDe = cbbLop.getSelectedItem().toString();
				String tieuDe2 = "DANH SÁCH LỚP MÔN " + tieuDe.substring(tieuDe.lastIndexOf("-")+1);
				lblDS.setText(tieuDe2.toUpperCase());
				loadDanhSachLopMH();
				
			}
		});
		cbbLop.setBounds(212, 66, 461, 26);
		frame.getContentPane().add(cbbLop);
		
		JLabel lblNewLabel_5 = new JLabel("Lớp");
		lblNewLabel_5.setBounds(93, 69, 69, 20);
		frame.getContentPane().add(lblNewLabel_5);
		
		JButton btnNewButton = new JButton("Xóa");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int dialogResult = JOptionPane.showConfirmDialog(null, " Xác nhận ?", "Confirm",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (dialogResult == JOptionPane.YES_OPTION) {
					try {
						String[] spitted = cbbLop.getSelectedItem().toString().split("-");
						String lop_mh = spitted[0].replace(" ", "");
						String ma_mh = spitted[1].replace(" ", "");
						String ma_sv = txtMSSV.getText();
						QuanLiSinhVien.begin();
						QuanLiSinhVien.xoaSV(ma_sv, ma_mh, lop_mh);
						QuanLiSinhVien.end();
						loadDanhSachLopMH();
					}catch(Exception ex){
						ex.printStackTrace();
						}
					}
				
				
			}
		});
		btnNewButton.setBounds(348, 158, 151, 43);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cập nhật");
		btnNewButton_1.setBounds(594, 161, 146, 43);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Quay lại");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				MainWindow main = new MainWindow();
				main.getFrmMainWindow().setLocationRelativeTo(null);
				main.getFrmMainWindow().setVisible(true);
				
			}
		});
		btnNewButton_2.setBounds(813, 158, 156, 43);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Thêm");
		btnNewButton_3.setBounds(93, 158, 156, 43);
		frame.getContentPane().add(btnNewButton_3);
		
		cbbGioiTinh = new JComboBox<String>();
		cbbGioiTinh.addItem("Nam");
		cbbGioiTinh.addItem("Nữ");
		cbbGioiTinh.setBounds(823, 105, 146, 26);
		frame.getContentPane().add(cbbGioiTinh);
	}
}
