package project1.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class EditRentView extends JDialog{
	private MainUI mainUI;
	private JTextField tfMaMT;
	private JComboBox<String> cbMaKH;
	private JComboBox<String> cbMaNV;
	
	private JTextField tfNgayMuon;
	private JTextField tfTienCoc;
	
	private String[] date = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", 
			 "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
			 "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
	private JComboBox<String> cbNgayHenTra = new JComboBox<String>(date);
	private String[] month = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", 
			  "11", "12"};
	private JComboBox<String> cbThangHenTra = new JComboBox<>(month);
	private String[] year = {"2017", "2018", "2019", "2020"};
	private JComboBox<String> cbNamHenTra = new JComboBox<>(year);
	
	private JLabel lbHoTenNV = new JLabel();
	private JLabel lbHoTenKH = new JLabel();
	
	private JButton btnEdit = new JButton("SỬA");
	private JButton btnCancel = new JButton("HỦY");
	
	
	public EditRentView(MainUI mainUI) {
		this.mainUI = mainUI;
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Sửa Mượn trả");
		setLayout(new BorderLayout(5, 5));
		
		add(createInforPanel(), BorderLayout.CENTER);
		add(createButtonPanel(), BorderLayout.PAGE_END);
		
		pack();      					
		setLocationRelativeTo(null);
	}
	
	private JPanel createInforPanel() {
		JPanel panel = new JPanel (new BorderLayout(5, 5));
		panel.setBorder(new TitledBorder("Thông tin mượn trả"));
		
		panel.add(createLabelPanel(), BorderLayout.WEST);
		panel.add(createInputPanel(), BorderLayout.CENTER);
		panel.add(createSubPanel(), BorderLayout.EAST);
		
		return panel;
	}
	
	private JPanel createButtonPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 5, 5));
		panel.add(btnEdit);
		panel.add(btnCancel);
		return panel;
	}
	
	private JPanel createLabelPanel() {
		JPanel labelPanel = new JPanel(new GridLayout(5, 1, 5, 5));
		labelPanel.add(new JLabel("Mã mượn trả "));
		labelPanel.add(new JLabel("Mã Khách hàng "));
		labelPanel.add(new JLabel("Mã nhân viên "));
		labelPanel.add(new JLabel("Ngày mượn "));
		labelPanel.add(new JLabel("Ngày hẹn trả "));
		return labelPanel;
	}
	
	private JPanel createInputPanel() {
		JPanel inputPanel = new JPanel(new GridLayout(5, 1, 5, 5));
		inputPanel.add(tfMaMT = new JTextField(8));
		inputPanel.add(cbMaKH = new JComboBox<String>());
		inputPanel.add(cbMaNV = new JComboBox<String>());
		inputPanel.add(tfNgayMuon = new JTextField(8));
		inputPanel.add(createPanelNgayHenTra());
		return inputPanel;
	}
	
	private JPanel createSubPanel() {
		JPanel subPanel = new JPanel(new GridLayout(5, 1, 5, 5));
		subPanel.add(new JLabel(""));
		subPanel.add(lbHoTenKH = new JLabel());
		subPanel.add(lbHoTenNV = new JLabel());
		subPanel.add(new JLabel(""));
		subPanel.add(createTienCocPanel());

		return subPanel;
	}
	
	private JPanel createPanelNgayHenTra() {
		JPanel ngayHenTraPanel = new JPanel();
		ngayHenTraPanel.add(cbNgayHenTra);
		ngayHenTraPanel.add(new JLabel("-"));
		ngayHenTraPanel.add(cbThangHenTra);
		ngayHenTraPanel.add(new JLabel("-"));
		ngayHenTraPanel.add(cbNamHenTra);
		return ngayHenTraPanel;
	}
	
	private JPanel createTienCocPanel() {
		JPanel tienCocPanel = new JPanel(new BorderLayout(5, 5));
		tienCocPanel.add(new JLabel("Tiền cọc"), BorderLayout.WEST);
		tienCocPanel.add(tfTienCoc = new JTextField(10));
		return tienCocPanel;
	}
	
	public JTextField getTfMaMT() {
		return tfMaMT;
	}
	public JComboBox<String> getCbMaKH() {
		return cbMaKH;
	}
	public JComboBox<String> getCbMaNV() {
		return cbMaNV;
	}
	public JTextField getTfNgayMuon() {
		return tfNgayMuon;
	}
	public JTextField getTfTienCoc() {
		return tfTienCoc;
	}
	public JComboBox<String> getCbNgayHenTra() {
		return cbNgayHenTra;
	}
	public JComboBox<String> getCbThangHenTra() {
		return cbThangHenTra;
	}
	public JComboBox<String> getCbNamHenTra() {
		return cbNamHenTra;
	}
	public JLabel getLbHoTenNV() {
		return lbHoTenNV;
	}
	public JLabel getLbHoTenKH() {
		return lbHoTenKH;
	}
	public JButton getBtnEdit() {
		return btnEdit;
	}
	public JButton getBtnCancel() {
		return btnCancel;
	}
}
