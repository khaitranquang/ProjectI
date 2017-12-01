package project1.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import project1.model.AccountDB;
import project1.model.NhanVien;
import project1.model.NhanVienDB;
import project1.view.ChangePassView;
import project1.view.LoginView;
import project1.view.MainUI;

public class LoginController {
	private LoginView loginView;
	private MainUI mainUI;
	
	private JTextField tfAccount;
	private JPasswordField tfPass;
	private JButton btnLogin;
	private JButton btnCancel;
	
	private JButton btnLogout;
	private JButton btnChangePass;
	
	private AccountDB accountDB;
	private String account;
	
	public LoginController(MainUI mainUI) {
		accountDB = new AccountDB();
		this.mainUI = mainUI;
		
		loginView = new LoginView();
		tfAccount = loginView.getTfAccount();
		tfPass = loginView.getTfPass();
		btnLogin = loginView.getBtnLogin();
		btnCancel = loginView.getBtnCancel();
		
		btnLogout = mainUI.getAccountView().getBtnLogout();
		btnChangePass = mainUI.getAccountView().getBtnChangePass();
		
		setActions();
	}
	
	private void createController() {
		new AddXeController(mainUI);
		new EditXeController(mainUI);
		new DeleteXeController(mainUI);
		new AddNhanVienController(mainUI);
		new EditNhanVienController(mainUI);
		new DeleteNhanVienController(mainUI);
		new AddKhachHangController(mainUI);
		new EditKhachHangController(mainUI);
		new DeleteKhachHangController(mainUI);
		new SearchXeController(mainUI);
		new TKXeController(mainUI);
		new DeleteRentController(mainUI);
		new SearchKHController(mainUI);
		new TKKHController(mainUI);
		new SearchNVController(mainUI);
		new TKNVController(mainUI);
		new AddThueXeController(mainUI);
		new EditRentController(mainUI);
		new ShowDetailInformation(mainUI);
		new TKRentController(mainUI);
		new SearchDetailController(mainUI);
		new PrintSearchController(mainUI);
		new TKDoanhThuController(mainUI);
	}
	
	private void setActions() {
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				account = tfAccount.getText().toString().trim();
				String pass = new String (tfPass.getPassword());
				System.out.println(pass);
				
				if(isLogin(account, pass)) {
					System.out.println("Login success");
					tfPass.setText("");
					loginView.setVisible(false);
					mainUI.setVisible(true);
//					mainUI.getMenuView().setVisible(true);
					if (!account.equals("admin")) {
						mainUI.getTabbedPane().setEnabledAt(2,false);
					}
					else {
						mainUI.getTabbedPane().setEnabledAt(2,true);
					}
						
						
						
					createController();
					return;
				}
				else {
					JOptionPane.showMessageDialog(new JDialog(), "Tài khoản hoặc Mật khẩu sai - Vui lòng nhập lại");
				}
				return;
				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tfAccount.setText("");
				tfPass.setText("");
			}
		});
		
		btnLogout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int select = JOptionPane.showOptionDialog(null, "Bạn có đăng xuất không?",
						 "Đăng xuất", JOptionPane.YES_NO_OPTION,
						 JOptionPane.QUESTION_MESSAGE, null, null, null);
				
				if (select == 0) {
					account = "";
					
//					mainUI.getMainPanel().setVisible(false);
//					mainUI.getMenuView().setVisible(false);
					mainUI.setVisible(false);
					mainUI = null;
					loginView.setVisible(false);
					loginView = null;
					MainUI mainUI2 = new MainUI();
					LoginController newLogin = new LoginController(mainUI2);
					
					return;
					
				}
			}
		});
		
		btnChangePass.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ChangePassView changePassView = new ChangePassView(mainUI);
				changePassView.setVisible(true);
				JLabel 		   lbAccount   = changePassView.getLbAccount();
				lbAccount.setText(account);
				JPasswordField tfOldPass   = changePassView.getTfOldPass();
				JPasswordField tfNewPass   = changePassView.getTfNewPass();
				JPasswordField tfReNewPass = changePassView.getTfReNewPass();
				JButton btnChange = changePassView.getBtnChange();
				JButton btnCancel = changePassView.getBtnCancel();

				btnChange.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String oldPassFromDB = "";
						if (account.equals("admin")) {
							oldPassFromDB = accountDB.getPassAdmin();
						}
						else oldPassFromDB = accountDB.getPassEmpl(account);
						
						String oldPass   = new String(tfOldPass.getPassword());
						String newPass   = new String(tfNewPass.getPassword());
						String reNewPass = new String(tfReNewPass.getPassword());
						
						if (!oldPass.equals(oldPassFromDB)) {
							System.out.println(oldPass + "   " + oldPassFromDB);
							JOptionPane.showMessageDialog(new JDialog(), "Mật khẩu cũ không đúng");
						}
						else if(!newPass.equals(reNewPass)) {
							JOptionPane.showMessageDialog(new JDialog(), "Vui lòng nhập lại đúng mật khẩu mới");
						}
						else {
							changePassFromDB(account, newPass);
							JOptionPane.showMessageDialog(new JDialog(), "Đổi mật khẩu thành công");
							changePassView.setVisible(false);
						}
					}
				});
				
				btnCancel.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						tfOldPass.setText("");
						tfNewPass.setText("");
						tfReNewPass.setText("");
						changePassView.setVisible(false);
					}
				});
			}
		});
	}
	
	
	
	
	
	
	
	/* Login */
	private boolean isLogin (String account, String pass) {
		if (account.equals("admin")) {
			String passOfAdmin = accountDB.getPassAdmin();
			if (pass.equals(passOfAdmin)) return true;
			else return false;
		}
		else {
			ArrayList<NhanVien> listEmpl = new NhanVienDB().getAllNhanVien();
			ArrayList<String> listMaNV = new ArrayList<String>();
			for (int i = 0; i < listEmpl.size(); i++) {
				listMaNV.add(listEmpl.get(i).getIdNhanVien());
			}
			if (listMaNV.indexOf(account) >= 0 && pass.equals(accountDB.getPassEmpl(account))) {
				return true;
			}
			else return false;
		}
	}
	
	/* Change Pass */
	private void changePassFromDB(String account, String newPass) {
		if (account.equals("admin")) {
			accountDB.changePassAdminFromDB(newPass);
		} else {
			accountDB.changePassEmplFromDB(account, newPass);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
