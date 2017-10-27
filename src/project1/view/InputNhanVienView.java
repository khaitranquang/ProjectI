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
		panelLab.add(lbIdNhanVien = new JLabel("Mã Nhân Viên"));
		panelLab.add(lbTenNhanVien = new JLabel("Tên Nhân Viên"));
		panelLab.add(lbSoCMND = new JLabel("Số CMND"));
		panelLab.add(lbNgaySinh = new JLabel("Ngày Sinh"));
		
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
		panelLab.add(lbDiaChi = new JLabel("Địa Chỉ"));
		panelLab.add(lbGioiTinh = new JLabel("Giới Tính"));
		panelLab.add(lbSoDT = new JLabel("Số ĐT"));
		
		JPanel panelTF = new JPanel(new GridLayout(3,  1, 5, 5));
		panelTF.add(tfDiaChi = new JTextField());
		panelTF.add(tfGioiTinh = new JTextField());
		panelTF.add(tfSoDT = new JTextField());
		
		panel.add(panelLab, BorderLayout.WEST);
		panel.add(panelTF, BorderLayout.CENTER);
		
		return panel;
	}
	
	public JLabel getLbIdNhanVien() {
		return lbIdNhanVien;
	}
	public void setLbIdNhanVien(JLabel lbIdNhanVien) {
		this.lbIdNhanVien = lbIdNhanVien;
	}
	public JLabel getLbTenNhanVien() {
		return lbTenNhanVien;
	}
	public void setLbTenNhanVien(JLabel lbTenNhanVien) {
		this.lbTenNhanVien = lbTenNhanVien;
	}
	public JLabel getLbSoCMND() {
		return lbSoCMND;
	}
	public void setLbSoCMND(JLabel lbSoCMND) {
		this.lbSoCMND = lbSoCMND;
	}
	public JLabel getLbNgaySinh() {
		return lbNgaySinh;
	}
	public void setLbNgaySinh(JLabel lbNgaySinh) {
		this.lbNgaySinh = lbNgaySinh;
	}
	public JLabel getLbDiaChi() {
		return lbDiaChi;
	}
	public void setLbDiaChi(JLabel lbDiaChi) {
		this.lbDiaChi = lbDiaChi;
	}
	public JLabel getLbGioiTinh() {
		return lbGioiTinh;
	}
	public void setLbGioiTinh(JLabel lbGioiTinh) {
		this.lbGioiTinh = lbGioiTinh;
	}
	public JLabel getLbSoDT() {
		return lbSoDT;
	}
	public void setLbSoDT(JLabel lbSoDT) {
		this.lbSoDT = lbSoDT;
	}
	public JTextField getTfIdNhanVien() {
		return tfIdNhanVien;
	}
	public void setTfIdNhanVien(JTextField tfIdNhanVien) {
		this.tfIdNhanVien = tfIdNhanVien;
	}
	public JTextField getTfTenNhanVien() {
		return tfTenNhanVien;
	}
	public void setTfTenNhanVien(JTextField tfTenNhanVien) {
		this.tfTenNhanVien = tfTenNhanVien;
	}
	public JTextField getTfSoCMND() {
		return tfSoCMND;
	}
	public void setTfSoCMND(JTextField tfSoCMND) {
		this.tfSoCMND = tfSoCMND;
	}
	public JTextField getTfNgaySinh() {
		return tfNgaySinh;
	}
	public void setTfNgaySinh(JTextField tfNgaySinh) {
		this.tfNgaySinh = tfNgaySinh;
	}
	public JTextField getTfDiaChi() {
		return tfDiaChi;
	}
	public void setTfDiaChi(JTextField tfDiaChi) {
		this.tfDiaChi = tfDiaChi;
	}
	public JTextField getTfGioiTinh() {
		return tfGioiTinh;
	}
	public void setTfGioiTinh(JTextField tfGioiTinh) {
		this.tfGioiTinh = tfGioiTinh;
	}
	public JTextField getTfSoDT() {
		return tfSoDT;
	}
	public void setTfSoDT(JTextField tfSoDT) {
		this.tfSoDT = tfSoDT;
	}
	
}
