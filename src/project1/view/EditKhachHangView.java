package project1.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class EditKhachHangView extends JDialog{
	private MainUI mainUI;
	private KhachHangInformation khachHangInformation;
	
	private JButton btnEdit =  new JButton("SỬA");
	private JButton btnCancel = new JButton("HỦY");
	
	public EditKhachHangView (MainUI mainUI) {
		this.mainUI = mainUI;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Sửa nhân viên");
		
		khachHangInformation = new KhachHangInformation();
		
		add(createMainPanel());
		pack();      					//Auto update UI
		setLocationRelativeTo(null);
	}

	public KhachHangInformation getKhachHangInformation() {
		return khachHangInformation;
	}
	public JButton getBtnEdit() {
		return btnEdit;
	}
	public JButton getBtnCancel() {
		return btnCancel;
	}
	
	private JPanel createMainPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
		mainPanel.add(khachHangInformation, BorderLayout.CENTER);
		mainPanel.add(createButtonPanel(), BorderLayout.PAGE_END);
		return mainPanel;
	}
	
	private JPanel createButtonPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
		panel.setBorder(new EmptyBorder(10,10,10,10));
		panel.add(btnEdit);
		panel.add(btnCancel);
		return panel;
	}
}
