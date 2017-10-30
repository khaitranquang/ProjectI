package project1.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class XeInformation extends JPanel{
	private JTextField tfIdXe        = new JTextField(30);
	private JTextField tfBienXe      = new JTextField(30);
	private JTextField tfTenXe       = new JTextField(30);
	private JTextField tfLoaiXe      = new JTextField(30);
	private JTextField tfHangSanXuat = new JTextField(30);
	private JTextField tfNamSanXuat  = new JTextField(30);
	private JTextField tfNgayBaoTri  = new JTextField(30);
	private JTextField tfNhienLieu   = new JTextField(30);
	private JTextField tfTrangThai   = new JTextField(30);
	private JTextField tfGiaThue     = new JTextField(30);
	
	public XeInformation() {
		setLayout(new BorderLayout());
		setBorder(new TitledBorder("Thông tin"));
		add(createPanelLabel(), BorderLayout.WEST);
		add(createPanelTF(), BorderLayout.CENTER);
	}
	
	public JTextField getTfIdXe() {
		return tfIdXe;
	}
	public JTextField getTfBienXe() {
		return tfBienXe;
	}
	public JTextField getTfTenXe() {
		return tfTenXe;
	}
	public JTextField getTfLoaiXe() {
		return tfLoaiXe;
	}
	public JTextField getTfHangSanXuat() {
		return tfHangSanXuat;
	}
	public JTextField getTfNamSanXuat() {
		return tfNamSanXuat;
	}
	public JTextField getTfNgayBaoTri() {
		return tfNgayBaoTri;
	}
	public JTextField getTfNhienLieu() {
		return tfNhienLieu;
	}

	public JTextField getTfTrangThai() {
		return tfTrangThai;
	}
	public JTextField getTfGiaThue() {
		return tfGiaThue;
	}
	
	private JPanel createPanelLabel() {
		JPanel panel = new JPanel(new GridLayout(10, 1, 5, 5));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.add(new JLabel("Mã xe"));
		panel.add(new JLabel("Biển xe"));
		panel.add(new JLabel("Tên xe"));
		panel.add(new JLabel("Loại xe"));
		panel.add(new JLabel("Hãng sản xuất"));
		panel.add(new JLabel("Năm sản xuất"));
		panel.add(new JLabel("Ngày bảo trì"));
		panel.add(new JLabel("Nhiên liệu"));
		panel.add(new JLabel("Trạng Thái"));
		panel.add(new JLabel("Giá thuê"));
		
		return panel;
	}
	
	private JPanel createPanelTF() {
		JPanel panel = new JPanel(new GridLayout(10, 1, 5, 5));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.add(tfIdXe);
		panel.add(tfBienXe);
		panel.add(tfTenXe);
		panel.add(tfLoaiXe);
		panel.add(tfHangSanXuat);
		panel.add(tfNamSanXuat);
		panel.add(tfNgayBaoTri);
		panel.add(tfNhienLieu);
		panel.add(tfTrangThai);
		panel.add(tfGiaThue);
		
		return panel;
	}
}
