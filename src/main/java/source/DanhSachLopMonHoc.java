package source;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DanhSachLopMonHoc {

	private JFrame frame;
	private JTextField txtMSSV;
	private JTextField txtHoTen;
	private JTextField txtGioiTinh;
	private JTextField txtCMND;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}

	/**
	 * Create the application.
	 */
	public DanhSachLopMonHoc() {
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
		
		JLabel lblNewLabel = new JLabel("Mã Sinh Viên");
		lblNewLabel.setBounds(93, 108, 92, 20);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Họ Tên");
		lblNewLabel_1.setBounds(429, 66, 69, 26);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtMSSV = new JTextField();
		txtMSSV.setBounds(212, 105, 146, 26);
		frame.getContentPane().add(txtMSSV);
		txtMSSV.setColumns(10);
		
		txtHoTen = new JTextField();
		txtHoTen.setBounds(527, 66, 146, 26);
		frame.getContentPane().add(txtHoTen);
		txtHoTen.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Giới Tính");
		lblNewLabel_2.setBounds(429, 108, 69, 20);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("CMND");
		lblNewLabel_3.setBounds(753, 69, 69, 20);
		frame.getContentPane().add(lblNewLabel_3);
		
		txtGioiTinh = new JTextField();
		txtGioiTinh.setBounds(527, 105, 146, 26);
		frame.getContentPane().add(txtGioiTinh);
		txtGioiTinh.setColumns(10);
		
		txtCMND = new JTextField();
		txtCMND.setBounds(823, 66, 146, 26);
		frame.getContentPane().add(txtCMND);
		txtCMND.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("DANH SÁCH LỚP MÔN ");
		lblNewLabel_4.setForeground(Color.RED);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_4.setBounds(348, 0, 342, 52);
		frame.getContentPane().add(lblNewLabel_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 217, 1055, 371);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(212, 66, 146, 26);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel_5 = new JLabel("Lớp");
		lblNewLabel_5.setBounds(93, 69, 69, 20);
		frame.getContentPane().add(lblNewLabel_5);
		
		JButton btnNewButton = new JButton("Xóa");
		btnNewButton.setBounds(348, 158, 151, 43);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cập nhật");
		btnNewButton_1.setBounds(594, 161, 146, 43);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Quay lại");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
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
	}
}
