package project1.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project1.view.MainUI;

public class EditNhanVienView extends JDialog{
	private MainUI mainUI;
	private NhanVienInformation nhanVienInformation;
	
	private JButton btnEdit =  new JButton("SỬA");
	private JButton btnCancel = new JButton("HỦY");
	
	public EditNhanVienView(MainUI mainUI) {
		this.mainUI = mainUI;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Sửa nhân viên");
		
		nhanVienInformation = new NhanVienInformation();
		
		add(createMainPanel());
		pack();      					//Auto update UI
		setLocationRelativeTo(null);
	}

	public NhanVienInformation getNhanVienInformation() {
		return nhanVienInformation;
	}
	public JButton getBtnEdit() {
		return btnEdit;
	}
	public JButton getBtnCancel() {
		return btnCancel;
	}
	
	private JPanel createMainPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(nhanVienInformation, BorderLayout.CENTER);
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
