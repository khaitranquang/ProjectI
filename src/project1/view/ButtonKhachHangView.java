package project1.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ButtonKhachHangView extends JPanel{
	
	private JButton btnThem, btnSua, btnXoa, btnThongKe, btnHuy, btnNhapFile, btnXuatFile;
	private JTextField tfTimKiem;
	private JComboBox<String> timKiemCB, thongKeCB;
	private String[] timKiemVal = {"Mã khách hàng", "Tên khách hàng", "Số CMND", "Ngày sinh", "Địa chỉ", "Giới tính",
								   "Số ĐT", "Email"};
	private String[] thongKeVal = {"-- Chọn 1 kiểu thống kê --", "Ngày sinh", "Địa chỉ", "Giới tính"};
	
	public ButtonKhachHangView() {
		setLayout(new GridLayout(2, 1, 10, 15));
		setBorder(new EmptyBorder(5, 15, 120, 0));
		add(createButTkThongKe());
		add(createButOther());
	}
	
	private JPanel createButTkThongKe() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 15, 5));
		JPanel panelL = new JPanel(new GridLayout(2, 2, 5, 15));
		panelL.add(new JLabel("Tìm kiếm", new ImageIcon(this.getClass().getResource("/search.png")), JLabel.CENTER));
		panelL.add(timKiemCB = new JComboBox<>(timKiemVal));
		panelL.add(btnThongKe = createButton("Thống kê"));
		btnThongKe.setIcon(new ImageIcon(this.getClass().getResource("/tk.png")));
		panelL.add(thongKeCB = new JComboBox<>(thongKeVal));
		btnThongKe.setToolTipText("Thống kê");
		
		JPanel panelR = new JPanel(new GridLayout(2, 1, 5, 15));
		panelR.add(tfTimKiem = new JTextField());
		panel.add(panelL);
		panel.add(panelR);
		
		return panel;
	}
	
	private JPanel createButOther() {
		JPanel panel = new JPanel(new GridLayout(2, 3, 10, 10));
		panel.add(btnThem = createButton("Thêm"));
		btnThem.setIcon(new ImageIcon(this.getClass().getResource("/addp.png")));
		panel.add(btnSua = createButton("Sửa"));
		btnSua.setIcon(new ImageIcon(this.getClass().getResource("/updatep.png")));
		panel.add(btnXoa = createButton("Xóa"));
		btnXoa.setIcon(new ImageIcon(this.getClass().getResource("/deletep.png")));
		panel.add(btnHuy = createButton("Hủy"));
		btnHuy.setIcon(new ImageIcon(this.getClass().getResource("/cancel.png")));
		panel.add(btnNhapFile = createButton("Nhập file"));
		btnNhapFile.setIcon(new ImageIcon(this.getClass().getResource("/importF.png")));
		panel.add(btnXuatFile = createButton("Xuất file"));
		btnXuatFile.setIcon(new ImageIcon(this.getClass().getResource("/save.png")));
		
		return panel;
	}
	
	private JButton createButton(String name) {
		JButton btn = new JButton(name);
		return btn;
	}

	
	
	public JButton getBtnThem() {
		return btnThem;
	}
	public JButton getBtnSua() {
		return btnSua;
	}
	public JButton getBtnXoa() {
		return btnXoa;
	}
	public JButton getBtnThongKe() {
		return btnThongKe;
	}
	public JButton getBtnHuy() {
		return btnHuy;
	}
	public JButton getBtnNhapFile() {
		return btnNhapFile;
	}
	public JButton getBtnXuatFile() {
		return btnXuatFile;
	}
	public JTextField getTfTimKiem() {
		return tfTimKiem;
	}
	public JComboBox<String> getTimKiemCB() {
		return timKiemCB;
	}
	public JComboBox<String> getThongKeCB() {
		return thongKeCB;
	}

	
}
