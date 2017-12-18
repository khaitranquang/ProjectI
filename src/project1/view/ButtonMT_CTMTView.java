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
	private String[] timKiemVal = {"Mã mượn trả", "Mã khách hàng", "Mã nhân viên", "Ngày mượn", "Ngày hẹn trả"};
	private String[] thongKeVal = {"-- Chọn 1 kiểu thống kê --", "Mã khách hàng", "Mã nhân viên", "Ngày mượn", "Ngày hẹn trả",
								   "Số lần vi phạm của khách hàng", "Tổng số tiền phạt của khách hàng",
								   "Tổng số tiền khuyến mại khách hàng", "Số xe chưa trả của khách", "Số xe nhân viên đã cho mượn",
								   "Tổng sỗ tiền khách chi ra"};
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
}
