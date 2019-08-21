package source;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import net.code.DsLop;
import net.code.QuanLiSinhVien;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AddStudentForm {

	private JFrame frmAddSd;
	private JTable table;
	private JTextField txtMSSV;
	private JTextField txtHoTen;
	private JLabel lblCmnd;
	private JTextField txtCMND;
	private JLabel lblGioiTinh;
	private JButton btnThem;
	private JButton btnQuayLai;
	private JScrollPane scrollPane;
	private JComboBox<String> cbbLop;
	private JLabel lblQunLSinh;
	private JLabel lblThem;
	private JLabel lblXoa;
	private JLabel lblCapNhat;
	

	/**
	 * Launch the application.
	 */
	
	public JComboBox<String> getCbbLop(){
	
		return this.cbbLop;
	}
	
	public JFrame getFrmAddSd() {
		
		return this.frmAddSd;
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddStudentForm window = new AddStudentForm();
					window.frmAddSd.setLocationRelativeTo(null);
					window.frmAddSd.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddStudentForm() {
		
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void loadSinhVien() {
		try {
			QuanLiSinhVien.begin();
			List<DsLop> lop = QuanLiSinhVien.queryGenetic("SELECT d FROM DsLop d WHERE d.lop = '" + cbbLop.getSelectedItem() + "'") ;
			QuanLiSinhVien.end();
			String[] columsName = new String[] {
					"STT","MSSV","Họ Tên","Giới Tính", "CMND", "Lớp"							
			};
			Object[][] content = new Object[lop.size()][6];
			int stt = 1;
			for(int i = 0; i < lop.size(); i++) {
				
				content[i][0] = stt++;
				content[i][1] = lop.get(i).getMa_sv();
				content[i][2] = lop.get(i).getHo_ten();
				content[i][3] = lop.get(i).getGioi_tinh();
				content[i][4] = lop.get(i).getCmnd();
				content[i][5] = lop.get(i).getLop();
				
			}
			table.setModel(new DefaultTableModel(content,columsName));
	}catch (Exception e2) {
			
			e2.printStackTrace();
			
			}
		
		
	}
	private void initialize() {
		frmAddSd = new JFrame();
		frmAddSd.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				
				try {
					QuanLiSinhVien.begin();
					List <String> lop =QuanLiSinhVien.query();
					QuanLiSinhVien.end();
					int size = lop.size();
					for (int i = 0; i < size; i++) {
						getCbbLop().addItem(lop.get(i));
					}
					
					
				}
				catch(Exception e3)
				{
					e3.printStackTrace();
				}
				
				
				
			}
		});
		frmAddSd.setTitle("Qu\u1EA3n l\u00FD sinh vi\u00EAn");
		frmAddSd.setBounds(100, 100, 1107, 660);
		frmAddSd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAddSd.getContentPane().setLayout(null);
		
		
		
		
		txtMSSV = new JTextField();
		txtMSSV.setBounds(208, 104, 146, 29);
		frmAddSd.getContentPane().add(txtMSSV);
		txtMSSV.setColumns(10);
		
		JLabel lblMssv = new JLabel("MSSV");
		lblMssv.setBounds(131, 106, 69, 25);
		frmAddSd.getContentPane().add(lblMssv);
		
		JLabel lblLop = new JLabel("L\u1EDBp");
		lblLop.setBounds(131, 63, 69, 25);
		frmAddSd.getContentPane().add(lblLop);
		
		JLabel lblHoTen = new JLabel("H\u1ECD t\u00EAn");
		lblHoTen.setBounds(406, 63, 69, 25);
		frmAddSd.getContentPane().add(lblHoTen);
		
		txtHoTen = new JTextField();
		txtHoTen.setColumns(10);
		txtHoTen.setBounds(508, 61, 146, 29);
		frmAddSd.getContentPane().add(txtHoTen);
		
		lblCmnd = new JLabel("CMND");
		lblCmnd.setBounds(406, 106, 69, 25);
		frmAddSd.getContentPane().add(lblCmnd);
		
		txtCMND = new JTextField();
		txtCMND.setColumns(10);
		txtCMND.setBounds(508, 104, 146, 29);
		frmAddSd.getContentPane().add(txtCMND);
		
		lblGioiTinh = new JLabel("Gi\u1EDBi t\u00EDnh");
		lblGioiTinh.setBounds(694, 59, 69, 29);
		frmAddSd.getContentPane().add(lblGioiTinh);
		
		JComboBox<String> cbbGioiTinh = new JComboBox<String>();
		cbbGioiTinh.setBounds(799, 60, 146, 26);
		frmAddSd.getContentPane().add(cbbGioiTinh);
		cbbGioiTinh.addItem("Nam");
		cbbGioiTinh.addItem("Nữ");
		
		btnThem = new JButton("Th\u00EAm");
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblXoa.setText(null);
				lblCapNhat.setText(null);
				boolean flag = false;
				try {
					
					QuanLiSinhVien.begin();
					QuanLiSinhVien.createDSLop(txtMSSV.getText(), txtHoTen.getText(), cbbGioiTinh.getSelectedItem().toString(), txtCMND.getText(), cbbLop.getSelectedItem().toString());
					QuanLiSinhVien.end();
				}catch (Exception ioe) {
					flag = true;
					JOptionPane.showMessageDialog(null, "Đã tồn tại sinh viên");
					ioe.printStackTrace();
				}
					loadSinhVien();
					txtCMND.setText("");
					txtHoTen.setText("");
					txtMSSV.setText("");
					if(flag == false) {
						lblThem.setText("Đã thêm thành công");
					}
						
			}
		});
		btnThem.setBounds(131, 158, 156, 43);
		frmAddSd.getContentPane().add(btnThem);
		
		btnQuayLai = new JButton("Quay l\u1EA1i");
		btnQuayLai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				frmAddSd.setVisible(false);
				GiaoVuForm giaovu = new GiaoVuForm();
				giaovu.getFrmGiaoVu().setLocationRelativeTo(null);
				giaovu.getFrmGiaoVu().setVisible(true);
			}
		});
		btnQuayLai.setBounds(799, 158, 146, 43);
		frmAddSd.getContentPane().add(btnQuayLai);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 243, 1055, 345);
		frmAddSd.getContentPane().add(scrollPane);
		
	
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				DefaultTableModel dfModel = (DefaultTableModel)table.getModel();
				int selectedRowIndex = table.getSelectedRow();
				txtMSSV.setText(dfModel.getValueAt(selectedRowIndex, 1).toString());
				txtHoTen.setText(dfModel.getValueAt(selectedRowIndex, 2).toString());
				cbbGioiTinh.setSelectedItem(dfModel.getValueAt(selectedRowIndex, 3).toString());
				txtCMND.setText(dfModel.getValueAt(selectedRowIndex, 4).toString());
				cbbLop.setSelectedItem(dfModel.getValueAt(selectedRowIndex, 5).toString());
				
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnXoa = new JButton("Xóa");
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblThem.setText(null);
				lblCapNhat.setText(null);
				boolean flag = false;
				String maSV= txtMSSV.getText().toString();
				QuanLiSinhVien.removeStudent(maSV);
				
					//flag = true;
					//OptionPane.showMessageDialog(frmAddSd, "Sinh viên không tồn tại !");
					//e.printStackTrace();
				//loadSinhVien();
				txtCMND.setText("");
				txtHoTen.setText("");
				txtMSSV.setText("");
				if(flag == false) {
					lblXoa.setText("Đã xóa thành công");
				}
				
			}
		});
		btnXoa.setBounds(365, 158, 151, 43);
		frmAddSd.getContentPane().add(btnXoa);
		
		JButton btnCapNhat = new JButton("C\u1EADp nh\u1EADt");
		btnCapNhat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean flag = false;
				try {
					lblThem.setText(null);
					lblXoa.setText(null);
					QuanLiSinhVien.begin();
					QuanLiSinhVien.update(txtMSSV.getText(), txtHoTen.getText(), cbbGioiTinh.getSelectedItem().toString(), txtCMND.getText(), cbbLop.getSelectedItem().toString());
					QuanLiSinhVien.end();
					loadSinhVien();
				}catch(Exception e) {
					flag = true;
					JOptionPane.showMessageDialog(frmAddSd, "Kiểm tra lại");
					e.printStackTrace();
				}
				if(flag == false) {
					lblCapNhat.setText("Đã cập nhật thành công");
				}
			}
		});
		btnCapNhat.setBounds(584, 158, 156, 43);
		frmAddSd.getContentPane().add(btnCapNhat);
		
		cbbLop = new JComboBox<String>();
		cbbLop.setEditable(true);
		cbbLop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				loadSinhVien();
				
			}
		});
		cbbLop.setBounds(208, 61, 146, 28);
		frmAddSd.getContentPane().add(cbbLop);
		
		lblQunLSinh = new JLabel("QU\u1EA2N L\u00DD SINH VI\u00CAN");
		lblQunLSinh.setHorizontalAlignment(SwingConstants.CENTER);
		lblQunLSinh.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblQunLSinh.setForeground(Color.RED);
		lblQunLSinh.setBounds(337, 0, 376, 59);
		frmAddSd.getContentPane().add(lblQunLSinh);
		
		lblThem = new JLabel("");
		lblThem.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblThem.setForeground(new Color(255, 0, 0));
		lblThem.setHorizontalAlignment(SwingConstants.CENTER);
		lblThem.setBounds(113, 203, 186, 43);
		frmAddSd.getContentPane().add(lblThem);
		
		lblXoa = new JLabel("");
		lblXoa.setHorizontalAlignment(SwingConstants.CENTER);
		lblXoa.setForeground(Color.RED);
		lblXoa.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblXoa.setBounds(347, 203, 186, 43);
		frmAddSd.getContentPane().add(lblXoa);
		
		lblCapNhat = new JLabel("");
		lblCapNhat.setHorizontalAlignment(SwingConstants.CENTER);
		lblCapNhat.setForeground(Color.RED);
		lblCapNhat.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCapNhat.setBounds(561, 203, 202, 43);
		frmAddSd.getContentPane().add(lblCapNhat);
		
		
	}
}
