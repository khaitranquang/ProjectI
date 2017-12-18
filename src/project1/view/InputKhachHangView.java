package project1.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class InputKhachHangView extends JPanel{
	
	private JTextField tfIdKhachHang, tfTenKhachHang, tfSoCMND, tfNgaySinh, tfDiaChi, tfSoDT, tfEmail;
	private JRadioButton radNam, radNu;
	private ButtonGroup bg;
	
	public InputKhachHangView() {
		setLayout(new BorderLayout());
		add(createInputPanel(),  BorderLayout.CENTER);
	}
	
	private JPanel createInputPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 15, 5));
		panel.setBorder(new EmptyBorder(5, 0, 155, 5));
		panel.add(createInputPanelL());
		panel.add(createInputPanelR());
		
		return panel;
	}
	
	private JPanel createInputPanelL() {
		JPanel panel = new JPanel(new BorderLayout(5, 5));
		JPanel panelLab = new JPanel(new GridLayout(4, 1, 15, 5));
		panelLab.add(new JLabel("Mã khách hàng:"));
		panelLab.add(new JLabel("Tên khách hàng:"));
		panelLab.add(new JLabel("Số CMND:"));
		panelLab.add(new JLabel("Ngày sinh:"));
		
		JPanel panelTF = new JPanel(new GridLayout(4, 1, 15, 5));
		panelTF.add(tfIdKhachHang = new JTextField());
		panelTF.add(tfTenKhachHang = new JTextField());
		panelTF.add(tfSoCMND = new JTextField());
		panelTF.add(tfNgaySinh = new JTextField());
		
		panel.add(panelLab, BorderLayout.WEST);
		panel.add(panelTF, BorderLayout.CENTER);
		
		return panel;
	}
	private JPanel createInputPanelR() {
		JPanel panel = new JPanel(new BorderLayout(5, 5));
		panel.setBorder(new EmptyBorder(0, 0, 0, 20));
		JPanel panelLab = new JPanel(new GridLayout(4, 1, 15, 5));
		panelLab.add(new JLabel("Địa chỉ:"));
		panelLab.add(new JLabel("Giới tính:"));
		panelLab.add(new JLabel("Số ĐT:"));
		panelLab.add(new JLabel("Email:"));
		
		JPanel panelTF = new JPanel(new GridLayout(4, 1, 15, 5));
		panelTF.add(tfDiaChi = new JTextField());
		panelTF.add(createPanelGT());
		panelTF.add(tfSoDT = new JTextField());
		panelTF.add(tfEmail = new JTextField());
		
		panel.add(panelLab, BorderLayout.WEST);
		panel.add(panelTF, BorderLayout.CENTER);
		
		return panel;
	}
	
	private JPanel createPanelGT() {
		radNam = new JRadioButton("Nam");
		radNu = new JRadioButton("Nữ");
		bg = new ButtonGroup();
		bg.add(radNam);
		bg.add(radNu);
		JPanel panel = new JPanel();
		((FlowLayout) panel.getLayout()).setAlignment(FlowLayout.LEFT);
		panel.add(radNam, JPanel.LEFT_ALIGNMENT);
		panel.add(radNu, JPanel.LEFT_ALIGNMENT);
		
		return panel;
	}
	
	public JTextField getTfIdKhachHang() {
		return tfIdKhachHang;
	}

	public JTextField getTfTenKhachHang() {
		return tfTenKhachHang;
	}

	public JTextField getTfSoCMND() {
		return tfSoCMND;
	}

	public JTextField getTfNgaySinh() {
		return tfNgaySinh;
	}

	public JTextField getTfDiaChi() {
		return tfDiaChi;
	}

	public JTextField getTfSoDT() {
		return tfSoDT;
	}

	public JTextField getTfEmail() {
		return tfEmail;
	}

	public JRadioButton getRadNam() {
		return radNam;
	}

	public JRadioButton getRadNu() {
		return radNu;
	}

	public ButtonGroup getBg() {
		return bg;
	}
	
}
