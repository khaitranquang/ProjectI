package project1.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class InputNhanVienView extends JPanel{
	
	private JLabel lbIdNhanVien, lbTenNhanVien, lbSoCMND, lbNgaySinh, lbDiaChi, lbGioiTinh, lbSoDT;
	private JTextField tfIdNhanVien, tfTenNhanVien, tfSoCMND, tfNgaySinh, tfDiaChi, tfGioiTinh, tfSoDT;
	
	public InputNhanVienView() {
		setLayout(new BorderLayout());
		add(createInputPanel(), BorderLayout.CENTER);
	}
	
	private JPanel createInputPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 15, 5));
		panel.setBorder(new EmptyBorder(5, 0, 150, 60));
		panel.add(createInputPanelL());
		panel.add(createInputPanelR());
		
		return panel;
	}
	
	private JPanel createInputPanelL() {
		JPanel panel = new JPanel(new BorderLayout());
		JPanel panelLab = new JPanel(new GridLayout(4, 1, 15, 5));
		panelLab.add(lbIdNhanVien = new JLabel("Mã nhân viên"));
		panelLab.add(lbTenNhanVien = new JLabel("Tên nhân viên"));
		panelLab.add(lbSoCMND = new JLabel("Số CMND"));
		panelLab.add(lbNgaySinh = new JLabel("Ngày sinh"));
		
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
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(0, 0, 38, 0));
		JPanel panelLab = new JPanel(new GridLayout(3, 1, 5, 5));
		panelLab.add(lbDiaChi = new JLabel("Địa chỉ"));
		panelLab.add(lbGioiTinh = new JLabel("Giới tính"));
		panelLab.add(lbSoDT = new JLabel("Số ĐT"));
		
		JPanel panelTF = new JPanel(new GridLayout(3,  1, 5, 5));
		panelTF.add(tfDiaChi = new JTextField());
		panelTF.add(tfGioiTinh = new JTextField());
		panelTF.add(tfSoDT = new JTextField());
		
		panel.add(panelLab, BorderLayout.WEST);
		panel.add(panelTF, BorderLayout.CENTER);
		
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
	
	public JTextField getTfGioiTinh() {
		return tfGioiTinh;
	}
	
	public JTextField getTfSoDT() {
		return tfSoDT;
	}
	
}
