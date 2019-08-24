package source;
//import java.awt.EventQueue;
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

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
public class XemDiemForm {

	private JFrame frmThongTinDiem;
	private JTable table;
	private JLabel lblMSSV;
	private JLabel lblXinChao;
	private JLabel lblXinChao2;
	
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

		String maSV = lblMSSV.getText().toString();		
		try{
			QuanLiSinhVien.begin();
			List<Diem> dsLop = QuanLiSinhVien.dsMonHoc("SELECT d FROM Diem d WHERE d.ma_sv = '" + maSV + "'");
			lblXinChao2.setText(dsLop.get(0).getHo_ten().toString().toUpperCase());
			
			String[] columsName = new String[] {
					"STT","Lớp Môn Học","Mã Môn Học","Tên Môn Học","Điểm Giữa Kỳ", "Điểm Cuối Kỳ", "Điểm Khác", "Tổng Điểm", "Kết Quả"					
			};
			Object[][] content = new Object[dsLop.size()][9];
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
			}
			table.setModel(new DefaultTableModel(content,columsName));
			QuanLiSinhVien.end();
		}catch(Exception e){
			
			e.printStackTrace();
		}
		
	}
	/**
	 * Launch the application.
	 */
	
	/*public static void main(String[] args) {
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
	}*/

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
		});
		frmThongTinDiem.setTitle("TH\u00D4NG TIN \u0110I\u1EC2M");
		frmThongTinDiem.setBounds(100, 100, 1107, 660);
		frmThongTinDiem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmThongTinDiem.getContentPane().setLayout(null);
		
		lblXinChao = new JLabel("TH\u00D4NG TIN \u0110I\u1EC2M C\u1EE6A SINH VI\u00CAN");
		lblXinChao.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblXinChao.setHorizontalAlignment(SwingConstants.CENTER);
		lblXinChao.setForeground(Color.RED);
		lblXinChao.setBounds(15, 0, 1055, 60);
		frmThongTinDiem.getContentPane().add(lblXinChao);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 91, 1055, 513);
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
		btnLogout.setBounds(966, 46, 69, 29);
		frmThongTinDiem.getContentPane().add(btnLogout);
		
		JLabel lblLogOut = new JLabel("\u0110\u0103ng Xu\u1EA5t");
		lblLogOut.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogOut.setBounds(943, 16, 105, 29);
		frmThongTinDiem.getContentPane().add(lblLogOut);
		
		lblMSSV = new JLabel("");
		lblMSSV.setHorizontalAlignment(SwingConstants.CENTER);
		lblMSSV.setBounds(15, 0, 96, 36);
		frmThongTinDiem.getContentPane().add(lblMSSV);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(".\\images\\Add.png"));
		lblNewLabel.setBounds(37, 26, 63, 60);
		frmThongTinDiem.getContentPane().add(lblNewLabel);
		
		lblXinChao2 = new JLabel("");
		lblXinChao2.setForeground(Color.RED);
		lblXinChao2.setHorizontalAlignment(SwingConstants.CENTER);
		lblXinChao2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblXinChao2.setBounds(375, 39, 335, 36);
		frmThongTinDiem.getContentPane().add(lblXinChao2);
	}
}
