import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.awt.Font;
import javax.swing.SwingConstants;


public class LoginForm {

	public JFrame frmLogin;
	private JPasswordField passwordField_password;
	private JTextPane textPane_userName;
	/**
	 * Launch the application.
	 */
	
	public JFrame getFrmLogin() {
		
		return frmLogin;
		
	}
	
	public JTextPane gettextPane_userName() {
		
		return textPane_userName;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm window = new LoginForm();
					window.frmLogin.setLocationRelativeTo(null);
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 679, 496);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		
		
		JLabel lblUserName = new JLabel("User name");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUserName.setBounds(20, 274, 96, 26);
		frmLogin.getContentPane().add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(20, 322, 96, 26);
		frmLogin.getContentPane().add(lblPassword);
		
		textPane_userName = new JTextPane();
		textPane_userName.setBounds(108, 274, 123, 26);
		frmLogin.getContentPane().add(textPane_userName);
		
		passwordField_password = new JPasswordField();
		passwordField_password.setBounds(108, 322, 128, 26);
		frmLogin.getContentPane().add(passwordField_password);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String filePath = ".\\Data\\TaiKhoan\\matkhau.csv";
				boolean flag1 = false;//Bo qua vong lap dau tien vi dong dau la tieu de
				boolean flag2 = false;//Cho biet den cuoi file ma van chua tim duoc user
				List<Account> Accounts = Account.readAccounts(filePath);
					for(Account a : Accounts) {
						if(flag1 == false) {
							flag1 = true;
							continue;
						}
					
						
					String uname=textPane_userName.getText();
					String pass=String.valueOf( passwordField_password.getPassword());
					
					if (uname.equals(a.user)&& pass.equals(a.pass)) {
						
						flag2 = true;
						frmLogin.setVisible(false);
						if(uname.equals("giaovu")) {
						MainWindow mainWindow = new MainWindow();
						mainWindow.getFrmMainWindow().setLocationRelativeTo(null);
						mainWindow.getFrmMainWindow().setVisible(true);
						}
						else {
							
							XemDiemForm xemDiem = new XemDiemForm();
							xemDiem.getJFrame().setLocationRelativeTo(null);
							xemDiem.getJFrame().setVisible(true);
							xemDiem.setlblMSSV(uname);
						}
					}
					
				}
					if (flag2 == false) {
						JOptionPane.showMessageDialog(frmLogin, "Please, check your account again !");
						
					}
					
				
				
			
		}
			});
		btnLogin.setBounds(10, 392, 109, 37);
		frmLogin.getContentPane().add(btnLogin);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want exit ?","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(dialogResult == JOptionPane.YES_OPTION){
					System.exit(0);
				}
			}
		});
		
		btnExit.setBounds(137, 392, 102, 37);
		frmLogin.getContentPane().add(btnExit);
		
		JLabel lblChangePassword = new JLabel("Change password");
		lblChangePassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblChangePassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblChangePassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frmLogin.setVisible(false);
				ChangePasswordForm changePasswordF = new ChangePasswordForm();
				changePasswordF.frmChangePassword.setLocationRelativeTo(null);
				changePasswordF.frmChangePassword.setVisible(true);
				
						
			}
		});
		lblChangePassword.setBounds(71, 360, 165, 20);
		frmLogin.getContentPane().add(lblChangePassword);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(".\\images\\bk4.jpg"));
		lblNewLabel.setBounds(0, 0, 661, 440);
		frmLogin.getContentPane().add(lblNewLabel);
	}
}

