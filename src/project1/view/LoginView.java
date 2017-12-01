package project1.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginView extends JFrame{
	private JTextField tfAccount;
	private JPasswordField tfPass;
	private JButton btnLogin;
	private JButton btnCancel;
	
	public JTextField getTfAccount() {
		return tfAccount;
	}
	public JPasswordField getTfPass() {
		return tfPass;
	}
	public JButton getBtnLogin() {
		return btnLogin;
	}
	public JButton getBtnCancel() {
		return btnCancel;
	}
	public LoginView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 320);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());
		
		add(createTitlePanel(), BorderLayout.NORTH);
		add(createMainPanel(), BorderLayout.CENTER);
		setVisible(true);
	}
	
	private JPanel createTitlePanel() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(248, 148, 6));
		panel.add(createLabel("Hệ thống quản lý thuê xe - Nhóm 14", Color.WHITE, 18));
		
		return panel;
	}
	
	private JPanel createMainPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
		panel.setBackground(new Color(44, 62, 80));
		panel.add(createPanelTF());
		panel.add(createButtonPanel());
		
		return panel;
	}
	
	private JPanel createPanelTF() {
		JPanel panel = new JPanel(new BorderLayout(10, 5));
		panel.setBorder(new EmptyBorder(40, 40, 20, 100));
		panel.setBackground(new Color(44, 62, 80));
		JPanel panelL = new JPanel(new GridLayout(2, 1, 10, 10));
		panelL.setBackground(new Color(44, 62, 80));
		panelL.add(createLabel("Tài khoản:", new Color(236, 240, 241), 14));
		panelL.add(createLabel("Mật khẩu:", new Color(236, 240, 241), 14));
		JPanel panelR = new JPanel(new GridLayout(2, 1, 10, 10));
		panelR.setBackground(new Color(44, 62, 80));
		panelR.add(tfAccount = new JTextField());
		tfAccount.setForeground(new Color(228, 241, 254));
		tfAccount.setBackground(new Color(108, 122, 137));
		panelR.add(tfPass = new JPasswordField());
		tfPass.setForeground(new Color(228, 241, 254));
		tfPass.setBackground(new Color(108, 122, 137));
		
		panel.add(panelL, BorderLayout.WEST);
		panel.add(panelR, BorderLayout.CENTER);
		return panel;
	}
	
	private JPanel createButtonPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
		panel.setBorder(new EmptyBorder(0, 135, 90, 100));
		panel.setBackground(new Color(44, 62, 80));
		panel.add(btnCancel = new JButton("Hủy"));
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancel.setBackground(new Color(192, 57, 43));
		panel.add(btnLogin = new JButton("Đăng nhập"));
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogin.setBackground(new Color(34, 167, 240));
		
		return panel;
	}
	
	private JLabel createLabel(String name, Color color, int kt) {
		JLabel label = new JLabel(name);
		label.setFont(new Font("Tahoma", Font.BOLD, kt));
		label.setForeground(color);
		return label;
	}
	
}
