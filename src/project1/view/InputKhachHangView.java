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
		panelLab.add(lbIdKhachHang = new JLabel("Mã khách hàng"));
		panelLab.add(lbTenKhachHang = new JLabel("Tên khách hàng"));
		panelLab.add(lbSoCMND = new JLabel("Số CMND"));
		panelLab.add(lbNgaySinh = new JLabel("Ngày sinh"));
		
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
		panelLab.add(lbDiaChi = new JLabel("Địa chỉ"));
		panelLab.add(lbGioiTinh = new JLabel("Giới tính"));
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

	public JTextField getTfGioiTinh() {
		return tfGioiTinh;
	}

	public JTextField getTfSoDT() {
		return tfSoDT;
	}

	public JTextField getTfEmail() {
		return tfEmail;
	}
	
}
