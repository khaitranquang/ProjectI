package project1.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class InputKhachHangView extends JPanel{
	
	private JLabel lbIdKhachHang, lbTenKhachHang, lbSoCMND, lbNgaySinh, lbDiaChi, lbGioiTinh, lbSoDT, lbEmail;
	private JTextField tfIdKhachHang, tfTenKhachHang, tfSoCMND, tfNgaySinh, tfDiaChi, tfGioiTinh, tfSoDT, tfEmail;
	
	public InputKhachHangView() {
		setLayout(new BorderLayout());
		add(createInputPanel(),  BorderLayout.CENTER);
	}
	
	private JPanel createInputPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 15, 5));
		panel.setBorder(new EmptyBorder(5, 0, 155, 60));
		panel.add(createInputPanelL());
		panel.add(createInputPanelR());
		
		return panel;
	}
	
	private JPanel createInputPanelL() {
		JPanel panel = new JPanel(new BorderLayout());
		JPanel panelLab = new JPanel(new GridLayout(4, 1, 15, 5));
		panelLab.add(lbIdKhachHang = new JLabel("Mã Khách Hàng"));
		panelLab.add(lbTenKhachHang = new JLabel("Tên Khách Hàng"));
		panelLab.add(lbSoCMND = new JLabel("Số CMND"));
		panelLab.add(lbNgaySinh = new JLabel("Ngày Sinh"));
		
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
		JPanel panel = new JPanel(new BorderLayout());
		JPanel panelLab = new JPanel(new GridLayout(4, 1, 15, 5));
		panelLab.add(lbDiaChi = new JLabel("Địa Chỉ"));
		panelLab.add(lbGioiTinh = new JLabel("Giới Tính"));
		panelLab.add(lbSoDT = new JLabel("Số ĐT"));
		panelLab.add(lbEmail = new JLabel("Email"));
		
		JPanel panelTF = new JPanel(new GridLayout(4, 1, 15, 5));
		panelTF.add(tfDiaChi = new JTextField());
		panelTF.add(tfGioiTinh = new JTextField());
		panelTF.add(tfSoDT = new JTextField());
		panelTF.add(tfEmail = new JTextField());
		
		panel.add(panelLab, BorderLayout.WEST);
		panel.add(panelTF, BorderLayout.CENTER);
		
		return panel;
	}
	
	
	public JLabel getLbIdKhachHang() {
		return lbIdKhachHang;
	}
	public void setLbIdKhachHang(JLabel lbIdKhachHang) {
		this.lbIdKhachHang = lbIdKhachHang;
	}
	public JLabel getLbTenKhachHang() {
		return lbTenKhachHang;
	}
	public void setLbTenKhachHang(JLabel lbTenKhachHang) {
		this.lbTenKhachHang = lbTenKhachHang;
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
	public JLabel getLbEmail() {
		return lbEmail;
	}
	public void setLbEmail(JLabel lbEmail) {
		this.lbEmail = lbEmail;
	}
	public JTextField getTfIdKhachHang() {
		return tfIdKhachHang;
	}
	public void setTfIdKhachHang(JTextField tfIdKhachHang) {
		this.tfIdKhachHang = tfIdKhachHang;
	}
	public JTextField getTfTenKhachHang() {
		return tfTenKhachHang;
	}
	public void setTfTenKhachHang(JTextField tfTenKhachHang) {
		this.tfTenKhachHang = tfTenKhachHang;
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
	public JTextField getTfEmail() {
		return tfEmail;
	}
	public void setTfEmail(JTextField tfEmail) {
		this.tfEmail = tfEmail;
	}
	
}
