package project1.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class InputMT_CTMTView extends JPanel{
	
	private JLabel lbIdMuonTra, lbIdKhachHang, lbIdNhanVien, lbNgayMuon, lbNgayHenTra, lbTienCoc, lbIdXe, lbNgayTra, lbTienPhat, lbTienThue, LbTienKM;
	private JTextField tfIdMuonTra, tfIdKhachHang, tfIdNhanVien, tfNgayMuon, tfNgayHenTra, tfTienCoc, tfIdXe, tfNgayTra, tfTienPhat, tfTienThue, tfTienKM;
	
	public InputMT_CTMTView() {
		setLayout(new BorderLayout());
		add(createInputPanel());
	}
	
	private JPanel createInputPanel() {
		JPanel panel = new JPanel(new GridLayout(1,  2, 5, 5));
		panel.add(createInputPanelL());
		panel.add(createInputPanelR());
		
		return panel;
	}
	
	private JPanel createInputPanelL() {
		JPanel panel = new JPanel(new BorderLayout());
		JPanel panelLab = new JPanel(new GridLayout(6,  1, 5, 5));
		panelLab.add(lbIdMuonTra = new JLabel("Mã Mượn Trả"));
		panelLab.add(lbIdKhachHang = new JLabel("Mã Khách Hàng"));
		panelLab.add(lbIdNhanVien = new JLabel("Mã Nhân Viên"));
		panelLab.add(lbIdXe = new JLabel("Mã xe"));
		panelLab.add(lbNgayTra = new JLabel("Ngày Trả"));
		panelLab.add(lbTienThue = new JLabel("Tiền Thuê"));
		
		JPanel panelTF = new JPanel(new GridLayout(6,  1, 5, 5));
		panelTF.add(tfIdMuonTra = new JTextField());
		panelTF.add(tfIdKhachHang = new JTextField());
		panelTF.add(tfIdNhanVien = new JTextField());
		panelTF.add(tfIdXe = new JTextField());
		panelTF.add(tfNgayTra = new JTextField());
		panelTF.add(tfTienThue = new JTextField());
		
		panel.add(panelLab, BorderLayout.WEST);
		panel.add(panelTF, BorderLayout.CENTER);
		
		return panel;
	}
	
	private JPanel createInputPanelR() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(0, 0, 18, 0));
		JPanel panelLab = new JPanel(new GridLayout(5,  1, 5, 5));
		panelLab.add(lbNgayMuon = new JLabel("Ngày Mượn"));
		panelLab.add(lbNgayHenTra = new JLabel("Ngày Hẹn Trả"));
		panelLab.add(lbTienCoc = new JLabel("Tiền Cọc"));
		panelLab.add(lbTienPhat = new JLabel("Tiền Phạt"));
		panelLab.add(LbTienKM = new JLabel("Tiền Khuyến Mại"));
		
		JPanel panelTF = new JPanel(new GridLayout(5,  1, 5, 5));
		panelTF.add(tfNgayMuon = new JTextField());
		panelTF.add(tfNgayHenTra = new JTextField());
		panelTF.add(tfTienCoc = new JTextField());
		panelTF.add(tfTienPhat = new JTextField());
		panelTF.add(tfTienKM = new JTextField());
		
		panel.add(panelLab, BorderLayout.WEST);
		panel.add(panelTF, BorderLayout.CENTER);
		
		return panel;
	}

	public JLabel getLbIdMuonTra() {
		return lbIdMuonTra;
	}

	public void setLbIdMuonTra(JLabel lbIdMuonTra) {
		this.lbIdMuonTra = lbIdMuonTra;
	}

	public JLabel getLbIdKhachHang() {
		return lbIdKhachHang;
	}

	public void setLbIdKhachHang(JLabel lbIdKhachHang) {
		this.lbIdKhachHang = lbIdKhachHang;
	}

	public JLabel getLbIdNhanVien() {
		return lbIdNhanVien;
	}

	public void setLbIdNhanVien(JLabel lbIdNhanVien) {
		this.lbIdNhanVien = lbIdNhanVien;
	}

	public JLabel getLbNgayMuon() {
		return lbNgayMuon;
	}

	public void setLbNgayMuon(JLabel lbNgayMuon) {
		this.lbNgayMuon = lbNgayMuon;
	}

	public JLabel getLbNgayHenTra() {
		return lbNgayHenTra;
	}

	public void setLbNgayHenTra(JLabel lbNgayHenTra) {
		this.lbNgayHenTra = lbNgayHenTra;
	}

	public JLabel getLbTienCoc() {
		return lbTienCoc;
	}

	public void setLbTienCoc(JLabel lbTienCoc) {
		this.lbTienCoc = lbTienCoc;
	}

	public JLabel getLbIdXe() {
		return lbIdXe;
	}

	public void setLbIdXe(JLabel lbIdXe) {
		this.lbIdXe = lbIdXe;
	}

	public JLabel getLbNgayTra() {
		return lbNgayTra;
	}

	public void setLbNgayTra(JLabel lbNgayTra) {
		this.lbNgayTra = lbNgayTra;
	}

	public JLabel getLbTienPhat() {
		return lbTienPhat;
	}

	public void setLbTienPhat(JLabel lbTienPhat) {
		this.lbTienPhat = lbTienPhat;
	}

	public JLabel getLbTienThue() {
		return lbTienThue;
	}

	public void setLbTienThue(JLabel lbTienThue) {
		this.lbTienThue = lbTienThue;
	}

	public JLabel getLbTienKM() {
		return LbTienKM;
	}

	public void setLbTienKM(JLabel lbTienKM) {
		LbTienKM = lbTienKM;
	}

	public JTextField getTfIdMuonTra() {
		return tfIdMuonTra;
	}

	public void setTfIdMuonTra(JTextField tfIdMuonTra) {
		this.tfIdMuonTra = tfIdMuonTra;
	}

	public JTextField getTfIdKhachHang() {
		return tfIdKhachHang;
	}

	public void setTfIdKhachHang(JTextField tfIdKhachHang) {
		this.tfIdKhachHang = tfIdKhachHang;
	}

	public JTextField getTfIdNhanVien() {
		return tfIdNhanVien;
	}

	public void setTfIdNhanVien(JTextField tfIdNhanVien) {
		this.tfIdNhanVien = tfIdNhanVien;
	}

	public JTextField getTfNgayMuon() {
		return tfNgayMuon;
	}

	public void setTfNgayMuon(JTextField tfNgayMuon) {
		this.tfNgayMuon = tfNgayMuon;
	}

	public JTextField getTfNgayHenTra() {
		return tfNgayHenTra;
	}

	public void setTfNgayHenTra(JTextField tfNgayHenTra) {
		this.tfNgayHenTra = tfNgayHenTra;
	}

	public JTextField getTfTienCoc() {
		return tfTienCoc;
	}

	public void setTfTienCoc(JTextField tfTienCoc) {
		this.tfTienCoc = tfTienCoc;
	}

	public JTextField getTfIdXe() {
		return tfIdXe;
	}

	public void setTfIdXe(JTextField tfIdXe) {
		this.tfIdXe = tfIdXe;
	}

	public JTextField getTfNgayTra() {
		return tfNgayTra;
	}

	public void setTfNgayTra(JTextField tfNgayTra) {
		this.tfNgayTra = tfNgayTra;
	}

	public JTextField getTfTienPhat() {
		return tfTienPhat;
	}

	public void setTfTienPhat(JTextField tfTienPhat) {
		this.tfTienPhat = tfTienPhat;
	}

	public JTextField getTfTienThue() {
		return tfTienThue;
	}

	public void setTfTienThue(JTextField tfTienThue) {
		this.tfTienThue = tfTienThue;
	}

	public JTextField getTfTienKM() {
		return tfTienKM;
	}

	public void setTfTienKM(JTextField tfTienKM) {
		this.tfTienKM = tfTienKM;
	}
	
}
