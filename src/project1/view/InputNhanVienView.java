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

public class InputNhanVienView extends JPanel{
	private JTextField tfIdNhanVien, tfTenNhanVien, tfSoCMND, tfNgaySinh, tfDiaChi, tfSoDT;
	private JRadioButton radNam, radNu;
	private ButtonGroup bg;
	
	public InputNhanVienView() {
		setLayout(new BorderLayout());
		add(createInputPanel(), BorderLayout.CENTER);
	}
	
	private JPanel createInputPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 15, 5));
		panel.setBorder(new EmptyBorder(5, 0, 150, 5));
		panel.add(createInputPanelL());
		panel.add(createInputPanelR());
		
		return panel;
	}
	
	private JPanel createInputPanelL() {
		JPanel panel = new JPanel(new BorderLayout(5, 5));
		JPanel panelLab = new JPanel(new GridLayout(4, 1, 15, 5));
		panelLab.add(new JLabel("Mã nhân viên:"));
		panelLab.add(new JLabel("Tên nhân viên:"));
		panelLab.add(new JLabel("Số CMND:"));
		panelLab.add(new JLabel("Ngày sinh:"));
		
		JPanel panelTF = new JPanel(new GridLayout(4,  1, 15, 5));
		panelTF.add(tfIdNhanVien = new JTextField());
		panelTF.add(tfTenNhanVien = new JTextField());
		panelTF.add(tfSoCMND = new JTextField());
		panelTF.add(tfNgaySinh = new JTextField());
		
		panel.add(panelLab, BorderLayout.WEST);
		panel.add(panelTF, BorderLayout.CENTER);

		return panel;
	}
	private JPanel createInputPanelR() {
		JPanel panel = new JPanel(new BorderLayout(5, 5));
		panel.setBorder(new EmptyBorder(0, 0, 0, 20));
		JPanel panelLab = new JPanel(new GridLayout(4, 1, 5, 5));
		panelLab.add(new JLabel("Địa chỉ:"));
		panelLab.add(new JLabel("Giới tính:"));
		panelLab.add(new JLabel("Số ĐT:"));
		
		JPanel panelTF = new JPanel(new GridLayout(4,  1, 5, 5));
		panelTF.add(tfDiaChi = new JTextField());
		panelTF.add(createPanelGT());
		panelTF.add(tfSoDT = new JTextField());
		
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
	
	public JTextField getTfIdNhanVien() {
		return tfIdNhanVien;
	}
	
	public JTextField getTfTenNhanVien() {
		return tfTenNhanVien;
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
	
	public JRadioButton getRadNam() {
		return radNam;
	}

	public JRadioButton getRadNu() {
		return radNu;
	}

	public ButtonGroup getBg() {
		return bg;
	}

	public JTextField getTfSoDT() {
		return tfSoDT;
	}
	
}
