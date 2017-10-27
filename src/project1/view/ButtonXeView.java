package project1.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ButtonXeView extends JPanel{
	
	private JButton btnThem, btnSua, btnXoa, btnTimKiem, btnThongKe, btnHuy, btnNhapFile, btnXuatFile;
	private JTextField tfTimKiem;
	private JComboBox<String> timKiemCB, thongKeCB;
	private String[] timKiemVal = {"All", "Mã Xe", "Tên Xe", "Loại Xe", "Hãng Sản Xuất", 
			"Năm Sản Xuất", "Ngày Bảo Trì", "Trạng Thái", "Giá Thuê"};
	private String[] thongKeVal = {"Tên Xe", "Loại Xe", "Hãng Sản Xuất", "Năm Sản Xuất",
			"Ngày Bảo Trì", "Trạng Thái", "Giá Thuê"};
	
	public ButtonXeView() {
		setLayout(new GridLayout(2, 1, 10, 15));
		setBorder(new EmptyBorder(5, 0, 90, 0));
		add(createButTkThongKe());
		add(createButOther());
	}
	
	private JPanel createButTkThongKe() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 5, 5));
		JPanel panelL = new JPanel(new GridLayout(2,  2, 5, 15));
		panelL.add(btnTimKiem = new JButton("Tìm Kiếm"));
		panelL.add(timKiemCB = new JComboBox<>(timKiemVal));
		panelL.add(btnThongKe = new JButton("Thống Kê"));
		panelL.add(thongKeCB = new JComboBox<>(thongKeVal));
		
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
		panel.add(btnSua = createButton("Sửa"));
		panel.add(btnXoa = createButton("Xóa"));
		panel.add(btnHuy = createButton("Hủy"));
		panel.add(btnNhapFile = createButton("Nhập File"));
		panel.add(btnXuatFile = createButton("Xuất File"));
		
		return panel;
	}
	
	private JButton createButton(String title) {
		JButton btn = new JButton(title);
		return btn;
	}

	public JButton getBtnThem() {
		return btnThem;
	}

	public void setBtnThem(JButton btnThem) {
		this.btnThem = btnThem;
	}

	public JButton getBtnSua() {
		return btnSua;
	}

	public void setBtnSua(JButton btnSua) {
		this.btnSua = btnSua;
	}

	public JButton getBtnXoa() {
		return btnXoa;
	}

	public void setBtnXoa(JButton btnXoa) {
		this.btnXoa = btnXoa;
	}

	public JButton getBtnTimKiem() {
		return btnTimKiem;
	}

	public void setBtnTimKiem(JButton btnTimKiem) {
		this.btnTimKiem = btnTimKiem;
	}

	public JButton getBtnThongKe() {
		return btnThongKe;
	}

	public void setBtnThongKe(JButton btnThongKe) {
		this.btnThongKe = btnThongKe;
	}

	public JButton getBtnHuy() {
		return btnHuy;
	}

	public void setBtnHuy(JButton btnHuy) {
		this.btnHuy = btnHuy;
	}

	public JButton getBtnNhapFile() {
		return btnNhapFile;
	}

	public void setBtnNhapFile(JButton btnNhapFile) {
		this.btnNhapFile = btnNhapFile;
	}

	public JButton getBtnXuatFile() {
		return btnXuatFile;
	}

	public void setBtnXuatFile(JButton btnXuatFile) {
		this.btnXuatFile = btnXuatFile;
	}

	public JTextField getTfTimKiem() {
		return tfTimKiem;
	}

	public void setTfTimKiem(JTextField tfTimKiem) {
		this.tfTimKiem = tfTimKiem;
	}

	public JComboBox<String> getTimKiemCB() {
		return timKiemCB;
	}

	public void setTimKiemCB(JComboBox<String> timKiemCB) {
		this.timKiemCB = timKiemCB;
	}

	public JComboBox<String> getThongKeCB() {
		return thongKeCB;
	}

	public void setThongKeCB(JComboBox<String> thongKeCB) {
		this.thongKeCB = thongKeCB;
	}
	
}
