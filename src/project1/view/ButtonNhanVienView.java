package project1.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ButtonNhanVienView extends JPanel{
	
	private JButton btnThem, btnSua, btnXoa, btnTimKiem, btnThongKe, btnHuy, btnNhapFile, btnXuatFile;
	private JTextField tfTimKiem;
	private JComboBox<String> timKiemCB, thongKeCB;
	private String[] timKiemVal = {"All", "Mã nhân viên", "Tên nhân viên", "Ngày sinh", "Địa chỉ", 
			"Số ĐT", "Giới tính"};
	private String[] thongKeVal = {"Tên nhân viên", "Ngày Sinh", "Địa chỉ", "Giới tính"};
	
	public ButtonNhanVienView() {
		setLayout(new GridLayout(2, 1, 10, 15));
		setBorder(new EmptyBorder(5, 0, 90, 0));
		add(createButTkThongKe());
		add(createButOther());
	}
	
	private JPanel createButTkThongKe() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 5, 5));
		JPanel panelL = new JPanel(new GridLayout(2, 2, 5, 15));
		panelL.add(btnTimKiem = createButton("Tìm kiếm"));
		btnTimKiem.setIcon(new ImageIcon(this.getClass().getResource("/search.png")));
		panelL.add(timKiemCB = new JComboBox<>(timKiemVal));
		panelL.add(btnThongKe = createButton("Thống kê"));
		btnThongKe.setIcon(new ImageIcon(this.getClass().getResource("/tk.png")));
		panelL.add(thongKeCB = new JComboBox<>(thongKeVal));
		btnTimKiem.setToolTipText("Tìm kiếm");
		btnThongKe.setToolTipText("Thống kê");
		
		JPanel panelR = new JPanel(new GridLayout(1, 1));
		panelR.add(tfTimKiem = new JTextField());
		panelR.setBorder(new EmptyBorder(0, 0, 52, 0));
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

	public JButton getBtnTimKiem() {
		return btnTimKiem;
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
