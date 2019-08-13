//import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

import com.sun.xml.internal.stream.writers.UTF8OutputStreamWriter;

public class ChangePasswordForm {

	
	public  JFrame frmChangePassword;
	private JPasswordField passwOld;
	private JPasswordField passwNew;
	private JPasswordField passwConf;

	/**
	 * Launch the application.
	 */
	
	public static void editRecord(String filePath, List<Account> Accounts) {
		
		try {
			
			PrintWriter pw = new PrintWriter(new UTF8OutputStreamWriter(new FileOutputStream(filePath)));
			int i =Accounts.size();
			for(Account a : Accounts ) {
				if(--i == 0 ){ 
					pw.print(a.user + ";" + a.pass );
				}
				else {
					pw.println(a.user + ";" + a.pass );
				}
				
			}
			pw.close();
		} catch (Exception e) {
			System.out.printf("That bai");
		}	
	}
	
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePasswordForm window = new ChangePasswordForm();
					window.frmChangePassword.setLocationRelativeTo(null);
					window.frmChangePassword.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public ChangePasswordForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmChangePassword = new JFrame();
		frmChangePassword.setTitle("Change Password");
		frmChangePassword.setBounds(100, 100, 591, 357);
		frmChangePassword.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmChangePassword.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Old Password");
		lblNewLabel.setBounds(15, 76, 166, 20);
		frmChangePassword.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New Password");
		lblNewLabel_1.setBounds(15, 125, 166, 29);
		frmChangePassword.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Confirm New Password");
		lblNewLabel_2.setBounds(15, 182, 177, 20);
		frmChangePassword.getContentPane().add(lblNewLabel_2);
		
		
		
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmChangePassword.setVisible(false);
				LoginForm login = new LoginForm();
				login.frmLogin.setLocationRelativeTo(null);
				login.frmLogin.setVisible(true);
			}
		});
		btnCancel.setBounds(317, 248, 140, 37);
		frmChangePassword.getContentPane().add(btnCancel);
		
		passwOld = new JPasswordField();
		passwOld.setBounds(217, 68, 319, 37);
		frmChangePassword.getContentPane().add(passwOld);
		
		passwNew = new JPasswordField();
		passwNew.setBounds(217, 174, 319, 37);
		frmChangePassword.getContentPane().add(passwNew);
		
		passwConf = new JPasswordField();
		passwConf.setBounds(217, 121, 319, 37);
		frmChangePassword.getContentPane().add(passwConf);
		
		JLabel lblNewLabel_3 = new JLabel("Username");
		lblNewLabel_3.setBounds(15, 27, 166, 29);
		frmChangePassword.getContentPane().add(lblNewLabel_3);
		
		JTextPane txtUsername = new JTextPane();
		txtUsername.setBounds(217, 15, 319, 37);
		frmChangePassword.getContentPane().add(txtUsername);
		
	
		
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(110, 248, 140, 37);
		frmChangePassword.getContentPane().add(btnSave);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				boolean flag1 = false;//Bo qua vong lap dau tien vi dong dau la tieu de
				boolean flag2 = false;//Cho biet den cuoi file ma van chua tim duoc user
				String filePath = ".\\Data\\TaiKhoan\\matkhau.csv" ;
				List<Account> Accounts = Account.readAccounts(filePath);
			
				String uname = txtUsername.getText();
				String passOld = String.valueOf(passwOld.getPassword()) ;
				String passNew = String.valueOf(passwNew.getPassword());
				String passConf = String.valueOf(passwConf.getPassword());
				
				for(Account a : Accounts) {
						
					if(flag1 == false) {
							flag1 = true;
							continue;
						}
						
					if ((uname.equals(a.user) && passOld.equals(a.pass)) && 
							(!(passNew.isEmpty()) && (!(passConf.isEmpty()) && 
							 (passNew.equals(passConf))))){
						a.pass = passNew;
						flag2 = true;
						break;
					}
					
				}
				if (flag2 == false) {
					
					JOptionPane.showMessageDialog(frmChangePassword, "Wrong !");
					return;
					
				}
				
				editRecord(filePath,Accounts);
				txtUsername.setText("");
				passwOld.setText("");
				passwNew.setText("");
				passwConf.setText("");
				
				JOptionPane.showMessageDialog(frmChangePassword,"Thành công");
					
			}
		});
		
		
		
	}
}
