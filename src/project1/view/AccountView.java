package project1.view;

import javax.swing.JButton;
import javax.swing.JPanel;

public class AccountView extends JPanel{
	private JButton btnLogout = new JButton("Đăng xuất");
	private JButton btnChangePass = new JButton("Đổi mật khẩu");
	
	public JButton getBtnLogout() {
		return btnLogout;
	}
	public JButton getBtnChangePass() {
		return btnChangePass;
	}
	
	public AccountView() {
		add(btnChangePass);
		add(btnLogout);
	}
	
}
