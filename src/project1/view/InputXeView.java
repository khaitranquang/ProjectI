package project1.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class InputXeView extends JPanel{
	private JTextField tfIdXe, tfBienXe, tfTenXe, tfLoaiXe, tfHangSanXuat, tfNamSanXuat,
	tfNgayBaoTri, tfNhienLieu, tfTrangThai, tfGiaThue;
	
	public InputXeView() {
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(5, 0, 40, 5));
		add(createInputPanel(), BorderLayout.CENTER);
		add(createNotePanel(), BorderLayout.SOUTH);
	}
	
	private JPanel createInputPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 15, 5));
		panel.add(createInputPanelL());
		panel.add(createInputPanelR());
		
		return panel;
	}
	
	private JPanel createInputPanelL() {
		JPanel panel = new JPanel(new BorderLayout(5, 5));
		
		JPanel panelLab = new JPanel(new GridLayout(6, 1, 15, 5));
		panelLab.add(new JLabel("Mã xe:"));
		panelLab.add(new JLabel("Biển xe:"));
		panelLab.add(new JLabel("Tên xe:"));
		panelLab.add(new JLabel("Loại xe:"));
		panelLab.add(new JLabel("Hãng sản xuất:"));
		
		JPanel panelTF = new JPanel(new GridLayout(6, 1, 15, 5));
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
		JPanel panel = new JPanel(new BorderLayout(5, 5));
		
		JPanel panelLab = new JPanel(new GridLayout(6, 1, 5, 5));
		panelLab.add(new JLabel("Năm sản xuất:"));
		panelLab.add(new JLabel("Ngày bảo trì:"));
		panelLab.add(new JLabel("Nhiên liệu:"));
		panelLab.add(new JLabel("Trạng thái:"));
		panelLab.add(new JLabel("Giá thuê:"));
		
		JPanel panelTF = new JPanel(new GridLayout(6, 1, 5, 5));
		panelTF.add(tfNamSanXuat = new JTextField());
		panelTF.add(tfNgayBaoTri = new JTextField());
		panelTF.add(tfNhienLieu = new JTextField());
		panelTF.add(tfTrangThai = new JTextField("1"));
		tfTrangThai.setEditable(false);
		panelTF.add(tfGiaThue = new JTextField());
		
		panel.add(panelLab, BorderLayout.WEST);
		panel.add(panelTF, BorderLayout.CENTER);
		
		return panel;
	}
	
	private JPanel createNotePanel() {
		JPanel notePanel = new JPanel(new GridLayout(3, 1, 4, 4));
		JLabel lbNote1 = new JLabel("Trạng thái: 1 - Còn hàng");
		JLabel lbNote2 = new JLabel("                   0 - Hết hàng");
		JLabel lbNote3 = new JLabel("Nháy đúp chuột vào xe để xem hình ảnh thực tế sản phẩm");
		notePanel.add(lbNote1);
		notePanel.add(lbNote2);
		notePanel.add(lbNote3);
		return notePanel;
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
