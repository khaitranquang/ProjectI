package project1.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class NhanVienInformation extends JPanel{
	private JTextField tfIdNhanVien  = new JTextField(30);
	private JTextField tfTenNhanVien = new JTextField(30);
	private JTextField tfSoCMND      = new JTextField(30);
	private JTextField tfNgaySinh    = new JTextField(30);
	private JTextField tfDiaChi      = new JTextField(30);
	private JTextField tfGioiTinh    = new JTextField(30);
	private JTextField tfSDT         = new JTextField(30);
	
	public NhanVienInformation() {
		setLayout(new BorderLayout());
		setBorder(new TitledBorder("Thông tin"));
		add(createPanelLabel(), BorderLayout.WEST);
		add(createPanelTF(), BorderLayout.CENTER);
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
	public JTextField getTfGioiTinh() {
		return tfGioiTinh;
	}
	public JTextField getTfSDT() {
		return tfSDT;
	}
	
	private JPanel createPanelLabel() {
		JPanel panel = new JPanel(new GridLayout(7, 1, 5, 5));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.add(new JLabel("Mã nhân viên"));
		panel.add(new JLabel("Họ tên nhân viên"));
		panel.add(new JLabel("Số CMND"));
		panel.add(new JLabel("Ngày sinh"));
		panel.add(new JLabel("Địa chỉ"));
		panel.add(new JLabel("Giới tính"));
		panel.add(new JLabel("Số điện thoại"));
		
		return panel;
	}
	
	private JPanel createPanelTF() {
		JPanel panel = new JPanel(new GridLayout(7, 1, 5, 5));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.add(tfIdNhanVien);
		panel.add(tfTenNhanVien);
		panel.add(tfSoCMND);
		panel.add(tfNgaySinh);
		panel.add(tfDiaChi);
		panel.add(tfGioiTinh);
		panel.add(tfSDT);
		
		return panel;
	}
	
}
