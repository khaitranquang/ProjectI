package project1.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainUI extends JFrame{
	
	private PanelQuanLyXeView qlXe;
	private PanelQuanLyKhachHangView qlKH;
	private PanelQuanLyNhanVienView qlNV;
	private PanelQuanLyMT_CTMTView qlMT;

	
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
		tabbedPane.addTab("Quản Lý Xe", null, qlXe, "Quản Lý Xe");
		qlKH = new PanelQuanLyKhachHangView();
		tabbedPane.addTab("Quản Lý Khách Hàng", null, qlKH, "Quản Lý Khách Hàng");
		qlNV = new PanelQuanLyNhanVienView();
		tabbedPane.addTab("Quản Lý Nhân Viên", null, qlNV, "Quản Lý Nhân Viên");
		qlMT = new PanelQuanLyMT_CTMTView();
		tabbedPane.addTab("Quản Lý Mượn Trả",  null, qlMT, "Quản Lý Mượn Trả");
		
		return tabbedPane;
	}
	
	public static void main(String[] args) {
		MainUI main = new MainUI();
	}
}
