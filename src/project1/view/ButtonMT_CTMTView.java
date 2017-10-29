package project1.view;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ButtonMT_CTMTView extends JPanel{
	
	private JButton btnThem, btnSua, btnXoa, btnTimKiem, btnThongKe, btnHuy, btnInPhieu, btnXuatFile;
	private JTextField tfTimKiem;
	private JComboBox<String> timKiemCB, thongKeCB, tableCB;
	private String[] timKiemVal = {"All", "Mã khách hàng", "Mã nhân viên", "Mã mượn trả", "Ngày mượn", "Ngày trả"};
	private String[] thongKeVal = {"Ngày mượn", "Mã khách hàng", "Mã nhân viên", "Ngày trả"};
	private String tableVal[] = {"Mượn trả", "Chi tiết mượn trả"};
	
	public ButtonMT_CTMTView() {
		setLayout(new GridLayout(2, 1, 10, 15));
		setBorder(new EmptyBorder(35, 0, 90, 0));
		add(createButTkThongKe());
		add(createButOther());
	}
	
	private JPanel createButTkThongKe() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 10, 5));
		JPanel panelL = new JPanel(new GridLayout(2, 2, 5, 15));
		panelL.add(btnTimKiem = createButton("Tìm kiếm"));
		btnTimKiem.setIcon(new ImageIcon(this.getClass().getResource("/search.png")));
		panelL.add(timKiemCB = new JComboBox<>(timKiemVal));
		panelL.add(btnThongKe = createButton("Thống kê"));
		btnThongKe.setIcon(new ImageIcon(this.getClass().getResource("/tk.png")));
		panelL.add(thongKeCB = new JComboBox<>(thongKeVal));
		btnTimKiem.setToolTipText("Tìm kiếm");
		btnThongKe.setToolTipText("Thống kê");
		
		JPanel panelR = new JPanel(new GridLayout(2, 2, 5, 15));
		panelR.add(tfTimKiem = new JTextField());
		panelR.add(tableCB = new JComboBox<>(tableVal));
		panel.add(panelL);
		panel.add(panelR);
		
		return panel;
	}
	
	private JPanel createButOther() {
		JPanel panel = new JPanel(new GridLayout(2, 3, 10, 10));
		panel.add(btnThem = createButton("Thêm"));
		btnThem.setIcon(new ImageIcon(this.getClass().getResource("/addMT.png")));
		panel.add(btnSua = createButton("Sửa"));
		btnSua.setIcon(new ImageIcon(this.getClass().getResource("/updateMT.png")));
		panel.add(btnXoa = createButton("Xóa"));
		btnXoa.setIcon(new ImageIcon(this.getClass().getResource("/deleteMT.png")));
		panel.add(btnHuy = createButton("Hủy"));
		btnHuy.setIcon(new ImageIcon(this.getClass().getResource("/cancel.png")));
		panel.add(btnInPhieu = createButton("In phiếu"));
		btnInPhieu.setIcon(new ImageIcon(this.getClass().getResource("/inP.png")));
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
	public JButton getBtnInPhieu() {
		return btnInPhieu;
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
	public JComboBox<String> getTableCB() {
		return tableCB;
	}

	
}
