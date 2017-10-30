package project1.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainUI extends JFrame{
	private PanelQuanLyXeView qlXe;
	private PanelQuanLyKhachHangView qlKH;
	private PanelQuanLyNhanVienView qlNV;
	private PanelQuanLyMT_CTMTView qlMT;
	private PanelAboutUSView aboutUS;
	
	public MainUI() {
		createGUI();
		setDisplay();
	}
	
	private void setDisplay() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 720);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	private void createGUI() {
		add(createTabbedPane());
	}
	
	private JTabbedPane createTabbedPane() {
		JTabbedPane tabbedPane = new JTabbedPane();
		qlXe = new PanelQuanLyXeView();
		tabbedPane.addTab("Quản lí xe", null, qlXe, "Quản lí xe");
		qlKH = new PanelQuanLyKhachHangView();
		tabbedPane.addTab("Quản lí khách hàng", null, qlKH, "Quản lí khách hàng");
		qlNV = new PanelQuanLyNhanVienView();
		tabbedPane.addTab("Quản lí nhân viên", null, qlNV, "Quản lí nhân viên");
		qlMT = new PanelQuanLyMT_CTMTView();
		tabbedPane.addTab("Quản lí mượn trả",  null, qlMT, "Quản lí mượn trả");
		aboutUS = new PanelAboutUSView();
		tabbedPane.addTab("About us", null, aboutUS, "Thông tin chi tiết");
		
		return tabbedPane;
	}
	
	// Getter
	public PanelQuanLyXeView getQlXe() {
		return qlXe;
	}
	public PanelQuanLyKhachHangView getQlKH() {
		return qlKH;
	}
	public PanelQuanLyNhanVienView getQlNV() {
		return qlNV;
	}
	public PanelQuanLyMT_CTMTView getQlMT() {
		return qlMT;
	}
	
	public static void main(String[] args) {
		MainUI main = new MainUI();
	}

	
}
