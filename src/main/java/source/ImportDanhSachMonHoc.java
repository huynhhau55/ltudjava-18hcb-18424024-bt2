package source;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ImportDanhSachMonHoc {

	private JFrame frame;
	private JTextField textField;
	private JTable table;

	
	public JFrame getJFrameIpDSMH() {
		return frame;
	}
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ImportDanhSachMonHoc window = new ImportDanhSachMonHoc();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ImportDanhSachMonHoc() {
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
		
		JLabel lblNewLabel = new JLabel("IMPORT DANH SÁCH LỚP MÔN HỌC");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(68, 24, 943, 46);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(226, 78, 470, 39);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Import");
		btnNewButton.setBounds(68, 78, 127, 39);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("QL Lớp MH");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DanhSachLopMonHoc ds = new DanhSachLopMonHoc();
				frame.setVisible(false);
				ds.getFrmDsLopMH().setLocationRelativeTo(null);
				ds.getFrmDsLopMH().setVisible(true);
			}
		});
		btnNewButton_1.setBounds(725, 78, 144, 39);
		frame.getContentPane().add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 133, 1055, 471);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_2 = new JButton("Quay Lại");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				MainWindow main = new MainWindow();
				main.getFrmMainWindow().setLocationRelativeTo(null);
				main.getFrmMainWindow().setVisible(true);
				
			}
		});
		btnNewButton_2.setBounds(884, 78, 127, 39);
		frame.getContentPane().add(btnNewButton_2);
	}
}
