package project1.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class InputXeView extends JPanel{
	
	private JLabel lbIdXe, lbBienXe, lbTenXe, lbLoaiXe, lbHangSanXuat, lbNamSanXuat, lbNgayBaoTri,
	lbNhienLieu, lbTrangThai, lbGiaThue;
	private JTextField tfIdXe, tfBienXe, tfTenXe, tfLoaiXe, tfHangSanXuat, tfNamSanXuat,
	tfNgayBaoTri, tfNhienLieu, tfTrangThai, tfGiaThue;
	
	public InputXeView() {
		
		setLayout(new BorderLayout());
		add(createInputPanel(), BorderLayout.CENTER);
	}
	
	private JPanel createInputPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 15, 5));
		panel.setBorder(new EmptyBorder(5, 0, 127, 60));
		panel.add(createInputPanelL());
		panel.add(createInputPanelR());
		
		return panel;
	}
	
	private JPanel createInputPanelL() {
		JPanel panel = new JPanel(new BorderLayout());
		
		JPanel panelLab = new JPanel(new GridLayout(5, 1, 15, 5));
		panelLab.add(lbIdXe = new JLabel("Mã Xe"));
		panelLab.add(lbBienXe = new JLabel("Biển Xe"));
		panelLab.add(lbTenXe = new JLabel("Tên Xe"));
		panelLab.add(lbLoaiXe = new JLabel("Loại Xe"));
		panelLab.add(lbHangSanXuat = new JLabel("Hãng Sản Xuất"));
		
		JPanel panelTF = new JPanel(new GridLayout(5, 1, 15, 5));
		panelTF.add(tfIdXe = new JTextField());
		panelTF.add(tfBienXe = new JTextField());
		panelTF.add(tfTenXe = new JTextField());
		panelTF.add(tfLoaiXe = new JTextField());
		panelTF.add(tfHangSanXuat = new JTextField());
		
		panel.add(panelLab, BorderLayout.WEST);
		panel.add(panelTF, BorderLayout.CENTER);
		
		return panel;
	}
	private JPanel createInputPanelR() {
		JPanel panel = new JPanel(new BorderLayout());
		
		JPanel panelLab = new JPanel(new GridLayout(5, 1, 5, 5));
		panelLab.add(lbNamSanXuat = new JLabel("Năm Sản Xuất"));
		panelLab.add(lbNgayBaoTri = new JLabel("Ngày Bảo Trì"));
		panelLab.add(lbNhienLieu = new JLabel("Nhiên Liệu"));
		panelLab.add(lbTrangThai = new JLabel("Trạng Thái"));
		panelLab.add(lbGiaThue = new JLabel("Giá Thuê"));
		
		JPanel panelTF = new JPanel(new GridLayout(5, 1, 5, 5));
		panelTF.add(tfNamSanXuat = new JTextField());
		panelTF.add(tfNgayBaoTri = new JTextField());
		panelTF.add(tfNhienLieu = new JTextField());
		panelTF.add(tfTrangThai = new JTextField());
		panelTF.add(tfGiaThue = new JTextField());
		
		panel.add(panelLab, BorderLayout.WEST);
		panel.add(panelTF, BorderLayout.CENTER);
		
		return panel;
	}
	
	public JLabel getLbIdXe() {
		return lbIdXe;
	}

	public void setLbIdXe(JLabel lbIdXe) {
		this.lbIdXe = lbIdXe;
	}

	public JLabel getLbBienXe() {
		return lbBienXe;
	}

	public void setLbBienXe(JLabel lbBienXe) {
		this.lbBienXe = lbBienXe;
	}

	public JLabel getLbTenXe() {
		return lbTenXe;
	}

	public void setLbTenXe(JLabel lbTenXe) {
		this.lbTenXe = lbTenXe;
	}

	public JLabel getLbLoaiXe() {
		return lbLoaiXe;
	}

	public void setLbLoaiXe(JLabel lbLoaiXe) {
		this.lbLoaiXe = lbLoaiXe;
	}

	public JLabel getLbHangSanXuat() {
		return lbHangSanXuat;
	}

	public void setLbHangSanXuat(JLabel lbHangSanXuat) {
		this.lbHangSanXuat = lbHangSanXuat;
	}

	public JLabel getLbNamSanXuat() {
		return lbNamSanXuat;
	}

	public void setLbNamSanXuat(JLabel lbNamSanXuat) {
		this.lbNamSanXuat = lbNamSanXuat;
	}

	public JLabel getLbNgayBaoTri() {
		return lbNgayBaoTri;
	}

	public void setLbNgayBaoTri(JLabel lbNgayBaoTri) {
		this.lbNgayBaoTri = lbNgayBaoTri;
	}

	public JLabel getLbNhienLieu() {
		return lbNhienLieu;
	}

	public void setLbNhienLieu(JLabel lbNhienLieu) {
		this.lbNhienLieu = lbNhienLieu;
	}

	public JLabel getLbTrangThai() {
		return lbTrangThai;
	}

	public void setLbTrangThai(JLabel lbTrangThai) {
		this.lbTrangThai = lbTrangThai;
	}

	public JLabel getLbGiaThue() {
		return lbGiaThue;
	}

	public void setLbGiaThue(JLabel lbGiaThue) {
		this.lbGiaThue = lbGiaThue;
	}

	public JTextField getTfIdXe() {
		return tfIdXe;
	}

	public void setTfIdXe(JTextField tfIdXe) {
		this.tfIdXe = tfIdXe;
	}

	public JTextField getTfBienXe() {
		return tfBienXe;
	}

	public void setTfBienXe(JTextField tfBienXe) {
		this.tfBienXe = tfBienXe;
	}

	public JTextField getTfTenXe() {
		return tfTenXe;
	}

	public void setTfTenXe(JTextField tfTenXe) {
		this.tfTenXe = tfTenXe;
	}

	public JTextField getTfLoaiXe() {
		return tfLoaiXe;
	}

	public void setTfLoaiXe(JTextField tfLoaiXe) {
		this.tfLoaiXe = tfLoaiXe;
	}

	public JTextField getTfHangSanXuat() {
		return tfHangSanXuat;
	}

	public void setTfHangSanXuat(JTextField tfHangSanXuat) {
		this.tfHangSanXuat = tfHangSanXuat;
	}

	public JTextField getTfNamSanXuat() {
		return tfNamSanXuat;
	}

	public void setTfNamSanXuat(JTextField tfNamSanXuat) {
		this.tfNamSanXuat = tfNamSanXuat;
	}

	public JTextField getTfNgayBaoTri() {
		return tfNgayBaoTri;
	}

	public void setTfNgayBaoTri(JTextField tfNgayBaoTri) {
		this.tfNgayBaoTri = tfNgayBaoTri;
	}

	public JTextField getTfNhienLieu() {
		return tfNhienLieu;
	}

	public void setTfNhienLieu(JTextField tfNhienLieu) {
		this.tfNhienLieu = tfNhienLieu;
	}

	public JTextField getTfTrangThai() {
		return tfTrangThai;
	}

	public void setTfTrangThai(JTextField tfTrangThai) {
		this.tfTrangThai = tfTrangThai;
	}

	public JTextField getTfGiaThue() {
		return tfGiaThue;
	}

	public void setTfGiaThue(JTextField tfGiaThue) {
		this.tfGiaThue = tfGiaThue;
	}

}
