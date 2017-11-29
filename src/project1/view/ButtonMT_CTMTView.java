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

import org.apache.poi.ss.usermodel.HorizontalAlignment;

public class ButtonMT_CTMTView extends JPanel{
	
//	private JButton btnThem, btnSua, btnXoa, btnTimKiem, btnThongKe, btnHuy, btnInPhieu, btnXuatFile;
//	private JTextField tfTimKiem;
//	private JComboBox<String> timKiemCB, thongKeCB, tableCB;
	private String[] timKiemVal = {"Mã mượn trả", "Mã khách hàng", "Mã nhân viên", "Ngày mượn", "Ngày hẹn trả"};
	private String[] thongKeVal = {"-- Chọn 1 kiểu thống kê --", "Mã khách hàng", "Mã nhân viên", "Ngày mượn", "Ngày hẹn trả",
								   "Số lần vi phạm của khách hàng", "Tổng số tiền phạt của khách hàng",
								   "Tổng số tiền khuyến mại khách hàng", "Số xe chưa trả của khách", "Số xe nhân viên đã cho mượn",
								   "Tổng sỗ tiền khách chi ra"};
//	private String tableVal[] = {"Mượn trả", "Chi tiết mượn trả"};
	private JButton btnThem      = new JButton("THÊM THUÊ XE");
	private JButton btnSua       = new JButton("SỬA THUÊ");
	private JButton btnChiTiet   = new JButton("XEM CHI TIẾT");
	private JButton btnXoa       = new JButton("XOÁ");
	private JButton btnThongKe   = new JButton("THỐNG KÊ");
	private JButton btnXuatFile  = new JButton("IN TÌM KIẾM");
	private JTextField tfTimKiem = new JTextField();
	private JComboBox<String> cbTimKiem = new JComboBox<>(timKiemVal);
	private JComboBox<String> cbThongKe	= new JComboBox<>(thongKeVal);
	
	public ButtonMT_CTMTView() {
		setLayout(new BorderLayout(20, 20));
		setBorder(new EmptyBorder(60, 200, 100, 200));
		add(createTimKiemPanel(), BorderLayout.PAGE_START);
		add(createButtonPanel(), BorderLayout.CENTER);
	}
	
	private JPanel createTimKiemPanel() {
		JPanel panel = new JPanel(new BorderLayout(20, 20));
		panel.add(new JLabel("Tìm kiếm", new ImageIcon(this.getClass().getResource("/search.png")),
				 (int)CENTER_ALIGNMENT), BorderLayout.WEST);
		panel.add(tfTimKiem, BorderLayout.CENTER);
		panel.add(createButtonTimKiemPanel(), BorderLayout.EAST);
		return panel;
	}
	
	private JPanel createButtonTimKiemPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 5, 5));
		btnXuatFile.setIcon(new ImageIcon(this.getClass().getResource("/save.png")));
		panel.add(cbTimKiem);
		panel.add(btnXuatFile);
		return panel;
	}
	
	private JPanel createButtonPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 1, 20, 20));
		panel.add(createThongKePanel());
		panel.add(createActionPanel());
		return panel;
	}
	
	private JPanel createThongKePanel() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 20, 20));
		btnThongKe.setIcon(new ImageIcon(this.getClass().getResource("/tk.png")));
		panel.add(cbThongKe);
		panel.add(btnThongKe);
		return panel;
	}
	
	private JPanel createActionPanel() {
		JPanel panel = new JPanel (new GridLayout(1, 4, 5, 5));
		btnThem.setIcon(new ImageIcon(this.getClass().getResource("/addMT.png")));
		btnSua.setIcon(new ImageIcon(this.getClass().getResource("/updateMT.png")));
		btnChiTiet.setIcon(new ImageIcon(this.getClass().getResource("/inP.png")));

		panel.add(btnThem);
		panel.add(btnSua);
		panel.add(btnChiTiet);
		panel.add(btnXoa);
		return panel;
	}

	public JButton getBtnThem() {
		return btnThem;
	}

	public JButton getBtnSua() {
		return btnSua;
	}

	public JButton getBtnChiTiet() {
		return btnChiTiet;
	}

	public JButton getBtnThongKe() {
		return btnThongKe;
	}

	public JButton getBtnXuatFile() {
		return btnXuatFile;
	}

	public JButton getBtnXoa() {
		return btnXoa;
	}

	public JTextField getTfTimKiem() {
		return tfTimKiem;
	}

	public JComboBox<String> getCbTimKiem() {
		return cbTimKiem;
	}

	public JComboBox<String> getCbThongKe() {
		return cbThongKe;
	}
	
	
	
	
	
	
	
	
	
	
	
//	public ButtonMT_CTMTView() {
//		setLayout(new GridLayout(2, 1, 10, 15));
//		setBorder(new EmptyBorder(35, 0, 90, 0));
//		add(createButTkThongKe());
//		add(createButOther());
//	}
//	
//	private JPanel createButTkThongKe() {
//		JPanel panel = new JPanel(new GridLayout(1, 2, 10, 5));
//		JPanel panelL = new JPanel(new GridLayout(2, 2, 5, 15));
//		panelL.add(btnTimKiem = createButton("Tìm kiếm"));
//		btnTimKiem.setIcon(new ImageIcon(this.getClass().getResource("/search.png")));
//		panelL.add(timKiemCB = new JComboBox<>(timKiemVal));
//		panelL.add(btnThongKe = createButton("Thống kê"));
//		btnThongKe.setIcon(new ImageIcon(this.getClass().getResource("/tk.png")));
//		panelL.add(thongKeCB = new JComboBox<>(thongKeVal));
//		btnTimKiem.setToolTipText("Tìm kiếm");
//		btnThongKe.setToolTipText("Thống kê");
//		
//		JPanel panelR = new JPanel(new GridLayout(2, 2, 5, 15));
//		panelR.add(tfTimKiem = new JTextField());
//		panelR.add(tableCB = new JComboBox<>(tableVal));
//		panel.add(panelL);
//		panel.add(panelR);
//		
//		return panel;
//	}
//	
//	private JPanel createButOther() {
//		JPanel panel = new JPanel(new GridLayout(2, 3, 10, 10));
//		panel.add(btnThem = createButton("Thêm"));
//		btnThem.setIcon(new ImageIcon(this.getClass().getResource("/addMT.png")));
//		panel.add(btnSua = createButton("Sửa"));
//		btnSua.setIcon(new ImageIcon(this.getClass().getResource("/updateMT.png")));
//		panel.add(btnXoa = createButton("Xóa"));
//		btnXoa.setIcon(new ImageIcon(this.getClass().getResource("/deleteMT.png")));
//		panel.add(btnHuy = createButton("Hủy"));
//		btnHuy.setIcon(new ImageIcon(this.getClass().getResource("/cancel.png")));
//		panel.add(btnInPhieu = createButton("In phiếu"));
//		btnInPhieu.setIcon(new ImageIcon(this.getClass().getResource("/inP.png")));
//		panel.add(btnXuatFile = createButton("Xuất file"));
//		btnXuatFile.setIcon(new ImageIcon(this.getClass().getResource("/save.png")));
//		
//		return panel;
//	}
//	
//	private JButton createButton(String name) {
//		JButton btn = new JButton(name);
//		return btn;
//	}
//
//	
//	public JButton getBtnThem() {
//		return btnThem;
//	}
//	public JButton getBtnSua() {
//		return btnSua;
//	}
//	public JButton getBtnXoa() {
//		return btnXoa;
//	}
//	public JButton getBtnTimKiem() {
//		return btnTimKiem;
//	}
//	public JButton getBtnThongKe() {
//		return btnThongKe;
//	}
//	public JButton getBtnHuy() {
//		return btnHuy;
//	}
//	public JButton getBtnInPhieu() {
//		return btnInPhieu;
//	}
//	public JButton getBtnXuatFile() {
//		return btnXuatFile;
//	}
//	public JTextField getTfTimKiem() {
//		return tfTimKiem;
//	}
//	public JComboBox<String> getTimKiemCB() {
//		return timKiemCB;
//	}
//	public JComboBox<String> getThongKeCB() {
//		return thongKeCB;
//	}
//	public JComboBox<String> getTableCB() {
//		return tableCB;
//	}

	
}
