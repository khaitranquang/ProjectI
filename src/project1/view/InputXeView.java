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
		panelLab.add(lbIdXe = new JLabel("Mã xe "));
		panelLab.add(lbBienXe = new JLabel("Biển xe "));
		panelLab.add(lbTenXe = new JLabel("Tên xe "));
		panelLab.add(lbLoaiXe = new JLabel("Loại xe "));
		panelLab.add(lbHangSanXuat = new JLabel("Hãng sản xuất "));
		
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
		panelLab.add(lbNamSanXuat = new JLabel("Năm sản xuất "));
		panelLab.add(lbNgayBaoTri = new JLabel("Ngày bảo trì "));
		panelLab.add(lbNhienLieu = new JLabel("Nhiên liệu "));
		panelLab.add(lbTrangThai = new JLabel("Trạng thái "));
		panelLab.add(lbGiaThue = new JLabel("Giá thuê "));
		
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
	
	// Getter
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
}
