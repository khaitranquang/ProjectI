package project1.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class PanelTKDoanhThu extends JPanel {
	private String[] date = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", 
			 				 "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
			 				 "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
	private String[] month = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", 
			 				  "11", "12"};
	private String[] year = {"2017", "2018", "2019", "2020"};
	private String[] quy = {"1", "2", "3", "4"};
	
	private JButton btnTkNgay = new JButton("Thống kê");
	private JButton btnTkThang = new JButton("Thống kê");
	private JButton btnTkQuy = new JButton("Thống kê");
	private JButton btnPrintNgay = new JButton ("In");
	private JButton btnPrintThang = new JButton("In");
	private JButton btnPrintQuy = new JButton("In");
	
	private JTextField tfResultNgay = new JTextField(40);
	private JTextField tfResultThang = new JTextField(40);
	private JTextField tfResultQuy = new JTextField(40);
	
	private JComboBox<String> cbNgayBatDau = new JComboBox<>(date);
	private JComboBox<String> cbThangBatDau = new JComboBox<>(month);
	private JComboBox<String> cbNamBatDau = new JComboBox<>(year);
	private JComboBox<String> cbNgayKetThuc = new JComboBox<>(date);
	private JComboBox<String> cbThangKetThuc = new JComboBox<>(month);
	private JComboBox<String> cbNamKetThuc = new JComboBox<>(year);
	private JComboBox<String> cbDoanhThuThang = new JComboBox<>(month);
	private JComboBox<String> cbTkThang = new JComboBox<>(month);
	private JComboBox<String> cbTkQuy = new JComboBox<>(quy);
	
	private JTextField tfThueMatBang = new JTextField("5000000");
	private JTextField tfSoNV = new JTextField(40);
	private JTextField tfLuongCoBan = new JTextField("8000000");
	private JTextField tfPhiBaoTri = new JTextField("30000000");
	private JTextField tfPhiVeSinh = new JTextField("600000");
	private JTextField tfPhiKhac = new JTextField("1400000");
	private JTextField tfTongChi = new JTextField(40);
	private JTextField tfTongDoanhThu = new JTextField(40);
	private JTextField tfDoanhSo = new JTextField(40);
	
	private JButton btnTinhDoanhSo = new JButton("Tính doanh số tháng");
	private JButton btnInDoanhSo = new JButton("In");
	private JButton btnEdit = new JButton("Sửa");
	
	public JButton getBtnTkNgay() {
		return btnTkNgay;
	}
	public JComboBox<String> getCbDoanhThuThang() {
		return cbDoanhThuThang;
	}
	public JComboBox<String> getCbTkThang() {
		return cbTkThang;
	}
	public JComboBox<String> getCbTkQuy() {
		return cbTkQuy;
	}
	public JButton getBtnTkThang() {
		return btnTkThang;
	}
	public JButton getBtnEdit() {
		return btnEdit;
	}
	public JButton getBtnTkQuy() {
		return btnTkQuy;
	}
	public JButton getBtnPrintNgay() {
		return btnPrintNgay;
	}
	public JButton getBtnPrintThang() {
		return btnPrintThang;
	}
	public JButton getBtnPrintQuy() {
		return btnPrintQuy;
	}
	public JTextField getTfResultNgay() {
		return tfResultNgay;
	}
	public JTextField getTfResultThang() {
		return tfResultThang;
	}
	public JTextField getTfResultQuy() {
		return tfResultQuy;
	}
	public JComboBox<String> getCbNgayBatDau() {
		return cbNgayBatDau;
	}
	public JComboBox<String> getCbThangBatDau() {
		return cbThangBatDau;
	}
	public JComboBox<String> getCbNamBatDau() {
		return cbNamBatDau;
	}
	public JComboBox<String> getCbNgayKetThuc() {
		return cbNgayKetThuc;
	}
	public JComboBox<String> getCbThangKetThuc() {
		return cbThangKetThuc;
	}
	public JComboBox<String> getCbNamKetThuc() {
		return cbNamKetThuc;
	}
	public JTextField getTfThueMatBang() {
		return tfThueMatBang;
	}
	public JTextField getTfSoNV() {
		return tfSoNV;
	}
	public JTextField getTfLuongCoBan() {
		return tfLuongCoBan;
	}
	public JTextField getTfPhiBaoTri() {
		return tfPhiBaoTri;
	}
	public JTextField getTfPhiVeSinh() {
		return tfPhiVeSinh;
	}
	public JTextField getTfPhiKhac() {
		return tfPhiKhac;
	}
	public JTextField getTfTongChi() {
		return tfTongChi;
	}
	public JTextField getTfTongDoanhThu() {
		return tfTongDoanhThu;
	}
	public JTextField getTfDoanhSo() {
		return tfDoanhSo;
	}
	public JButton getBtnTinhDoanhSo() {
		return btnTinhDoanhSo;
	}
	public JButton getBtnInDoanhSo() {
		return btnInDoanhSo;
	}
	
	public PanelTKDoanhThu() {
		setLayout(new BorderLayout(10, 10));
		add(createTkDoanhThuPanel(), BorderLayout.PAGE_START);
		add(createDoanhSoPanel(), BorderLayout.CENTER);
	}
	
	private JPanel createTkDoanhThuPanel() {
		JPanel panel = new JPanel (new GridLayout(3, 1, 5, 5));
		panel.setBorder(new TitledBorder("Thống kê doanh thu"));
		panel.add(createTheoNgay());
		panel.add(createTheoThang());
		panel.add(createTheoQuy());
		return panel;
	}
	
	private JPanel createTheoNgay() {
		JPanel ngayPanel = new JPanel (new BorderLayout(5, 5));
		ngayPanel.setBorder(new TitledBorder("Theo ngày"));
		ngayPanel.add(createNgayCombo(), BorderLayout.CENTER);
		ngayPanel.add(createActionPanel(btnTkNgay, btnPrintNgay, tfResultNgay), BorderLayout.EAST);
		return ngayPanel;
	}
	
	private JPanel createNgayCombo() {
		JPanel panel = new JPanel(new BorderLayout(5, 5));
		panel.setBorder(new EmptyBorder(0, 100, 0, 200));
		panel.add(createTitlePanel(), BorderLayout.WEST);
		panel.add(createCombo(), BorderLayout.CENTER);
		return panel;
	}
	
	private JPanel createTitlePanel() {
		JPanel panel = new JPanel(new GridLayout(2, 1, 5, 5));
		panel.add(new JLabel("Ngày bắt đầu "));
		panel.add(new JLabel("Ngày kết thúc "));
		return panel;
	}
	
	private JPanel createCombo() {
		JPanel panel = new JPanel(new GridLayout(2, 1, 5, 5));
		panel.add(createCbNgay(cbNgayBatDau, cbThangBatDau, cbNamBatDau));
		panel.add(createCbNgay(cbNgayKetThuc, cbThangKetThuc, cbNamKetThuc));
		return panel;
	}
	
	private JPanel createCbNgay(JComboBox<String> cbNgay, JComboBox<String> cbThang, JComboBox<String> cbNam) {
		JPanel panel = new JPanel(new GridLayout(1, 5));
		panel.add(cbNgay);
		panel.add(new JLabel("        tháng "));
		panel.add(cbThang);
		panel.add(new JLabel("        năm "));
		panel.add(cbNam);
		return panel;
	}
	
	private JPanel createActionPanel(JButton btnTk, JButton btnPrint, JTextField tfResult) {
		JPanel panel = new JPanel (new GridLayout(2, 1));
		panel.setBorder(new EmptyBorder(0, 30, 0, 100));
		JPanel row1 = new JPanel(new GridLayout(1, 2));
		row1.add(btnTk);
		row1.add(btnPrint);
		panel.add(row1);
		panel.add(tfResult);
		return panel;
	}
	
	private JPanel createTheoThang() {
		JPanel thangPanel = new JPanel(new BorderLayout(5, 5));
		thangPanel.setBorder(new TitledBorder("Theo tháng"));
		JPanel titlePanel = new JPanel(new BorderLayout(5, 5));
		titlePanel.setBorder(new EmptyBorder(8, 100, 8, 200));
		titlePanel.add(new JLabel("Tháng" ), BorderLayout.WEST);
		titlePanel.add(cbTkThang, BorderLayout.CENTER);
		thangPanel.add(titlePanel, BorderLayout.CENTER);
		thangPanel.add(createActionPanel(btnTkThang, btnPrintThang, tfResultThang), BorderLayout.EAST);
		return thangPanel;
	}
	
	private JPanel createTheoQuy() {
		JPanel quyPanel = new JPanel(new BorderLayout(5, 5));
		quyPanel.setBorder(new TitledBorder("Theo quý"));
		JPanel titlePanel = new JPanel(new BorderLayout(5, 5));
		titlePanel.setBorder(new EmptyBorder(8, 100, 8, 200));
		titlePanel.add(new JLabel("Qúy   " ), BorderLayout.WEST);
		titlePanel.add(cbTkQuy, BorderLayout.CENTER);
		quyPanel.add(titlePanel, BorderLayout.CENTER);
		quyPanel.add(createActionPanel(btnTkQuy, btnPrintQuy, tfResultQuy), BorderLayout.EAST);
		return quyPanel;
	}
	
	private JPanel createDoanhSoPanel() {
		JPanel panel = new JPanel(new BorderLayout(5, 5));
		panel.setBorder(new TitledBorder("Doanh số dự trù"));
		JPanel titlePanel = new JPanel(new GridLayout(11, 1, 5, 5));
		titlePanel.setBorder(new EmptyBorder(0, 100, 0, 0));
		titlePanel.add(new JLabel("Chi phí mặt bằng hàng tháng "));
		titlePanel.add(new JLabel("Số nhân viên "));
		titlePanel.add(new JLabel("Lương cơ bản mỗi nhân viên "));
		titlePanel.add(new JLabel("Chi phí bảo trì xe "));
		titlePanel.add(new JLabel("Chi phí vệ sinh hàng tháng "));
		titlePanel.add(new JLabel("Chi phí khác "));
		titlePanel.add(new JLabel("Tổng chi "));
		titlePanel.add(new JLabel("Tổng thu "));
		titlePanel.add(new JLabel("Doanh số "));
		titlePanel.add(btnTinhDoanhSo);
		JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 5, 5));
		buttonPanel.add(btnEdit);
		buttonPanel.add(btnInDoanhSo);
		titlePanel.add(buttonPanel);
		
		JPanel showPanel = new JPanel(new GridLayout(11, 1, 5, 5));
		showPanel.setBorder(new EmptyBorder(0, 0, 0, 110));
		showPanel.add(tfThueMatBang);
		showPanel.add(tfSoNV);
		showPanel.add(tfLuongCoBan);
		showPanel.add(tfPhiBaoTri);
		showPanel.add(tfPhiVeSinh);
		showPanel.add(tfPhiKhac);
		showPanel.add(tfTongChi);
		showPanel.add(tfTongDoanhThu);
		showPanel.add(tfDoanhSo);
		showPanel.add(cbDoanhThuThang);
		showPanel.add(new JLabel(""));
		
		panel.add(titlePanel, BorderLayout.WEST);
		panel.add(showPanel, BorderLayout.CENTER);
		
		return panel;
	}
}
