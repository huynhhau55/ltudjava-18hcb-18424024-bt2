//import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class MainWindow {

	private JFrame frmMainWindow;

	/**
	 * Launch the application.
	 */
	public JFrame getFrmMainWindow() {
		
		return this.frmMainWindow;
	}
	
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frmMainWindow.setLocationRelativeTo(null);
					window.frmMainWindow.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMainWindow = new JFrame();
		frmMainWindow.setBounds(100, 100, 791, 534);
		frmMainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMainWindow.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(".\\images\\bk3.jpg"));
		lblNewLabel.setBounds(0, 29, 769, 449);
		frmMainWindow.getContentPane().add(lblNewLabel);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 769, 31);
		frmMainWindow.getContentPane().add(menuBar);

		JMenu _import = new JMenu("Import");
		menuBar.add(_import);

		JMenuItem classList = new JMenuItem("Import danh sách lớp");
		classList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmMainWindow.setVisible(false);
				GiaoVuForm giaoVu = new GiaoVuForm();
				giaoVu.getFrmGiaoVu().setLocationRelativeTo(null);
				giaoVu.getFrmGiaoVu().setVisible(true);
			}
		});
		_import.add(classList);

		JMenuItem courseList = new JMenuItem("Import thời khóa biểu");
		courseList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frmMainWindow.setVisible(false);
				ThoiKhoaBieuForm thoiKhoaBieu = new ThoiKhoaBieuForm();
				thoiKhoaBieu.getFramThoiKhoaBieu().setLocationRelativeTo(null);
				thoiKhoaBieu.getFramThoiKhoaBieu().setVisible(true);

			}
		});
		_import.add(courseList);

		JMenuItem pointTable = new JMenuItem("Import bảng điểm");
		pointTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frmMainWindow.setVisible(false);
				BangDiemForm bangDiem = new BangDiemForm();
				bangDiem.getFrmDiem().setLocationRelativeTo(null);
				bangDiem.getFrmDiem().setVisible(true);

			}
		});
		_import.add(pointTable);

		JButton btnNewButton = new JButton();
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want logout ?", "Confirm",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (dialogResult == JOptionPane.YES_OPTION) {
					frmMainWindow.setVisible(false);
					LoginForm loginForm = new LoginForm();
					loginForm.frmLogin.setLocationRelativeTo(null);
					loginForm.frmLogin.setVisible(true);

				}
			}
		});
		btnNewButton.setIcon(new ImageIcon(".\\images\\logout.png"));
		btnNewButton.setBounds(692, 51, 41, 37);
		frmMainWindow.getContentPane().add(btnNewButton);
	}
}
