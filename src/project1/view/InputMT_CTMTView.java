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
		panelLab.add(lbIdMuonTra = new JLabel("Mã mượn trả"));
		panelLab.add(lbIdKhachHang = new JLabel("Mã khách hàng"));
		panelLab.add(lbIdNhanVien = new JLabel("Mã nhân viên"));
		panelLab.add(lbIdXe = new JLabel("Mã xe"));
		panelLab.add(lbNgayTra = new JLabel("Ngày trả"));
		panelLab.add(lbTienThue = new JLabel("Tiền thuê"));
		
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
		panelLab.add(lbNgayMuon = new JLabel("Ngày mượn"));
		panelLab.add(lbNgayHenTra = new JLabel("Ngày hẹn trả"));
		panelLab.add(lbTienCoc = new JLabel("Tiền cọc"));
		panelLab.add(lbTienPhat = new JLabel("Tiền phạt"));
		panelLab.add(LbTienKM = new JLabel("Tiền khuyến mại"));
		
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

	public JTextField getTfIdMuonTra() {
		return tfIdMuonTra;
	}

	public JTextField getTfIdKhachHang() {
		return tfIdKhachHang;
	}

	public JTextField getTfIdNhanVien() {
		return tfIdNhanVien;
	}

	public JTextField getTfNgayMuon() {
		return tfNgayMuon;
	}

	public JTextField getTfNgayHenTra() {
		return tfNgayHenTra;
	}

	public JTextField getTfTienCoc() {
		return tfTienCoc;
	}

	public JTextField getTfIdXe() {
		return tfIdXe;
	}

	public JTextField getTfNgayTra() {
		return tfNgayTra;
	}

	public JTextField getTfTienPhat() {
		return tfTienPhat;
	}

	public JTextField getTfTienThue() {
		return tfTienThue;
	}

	public JTextField getTfTienKM() {
		return tfTienKM;
	}

}
