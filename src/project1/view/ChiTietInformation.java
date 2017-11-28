package project1.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChiTietInformation extends JPanel{
	private JLabel lbMaMT;
	private JLabel lbMaKH;
	private JLabel lbHoTenKH;
	private JLabel lbMaNV;
	private JLabel lbHoTenNV;
	
	private TableChiTietView tableChiTietView;
	
	private JLabel lbNgayMuon;
	private JLabel lbNgayHenTra;
	private JLabel lbTrangThai;
	private JLabel lbNgayTra;
	private JLabel lbSoTienPhat;
	private JLabel lbSoTienCoc;
	private JLabel lbSoKhuyenMai;
	private JLabel lbTongTienPhat;
	private JLabel lbTongTienThue;
	private JLabel lbTongKhuyenMai;
	
	public JLabel getLbMaMT() {
		return lbMaMT;
	}
	public JLabel getLbMaKH() {
		return lbMaKH;
	}
	public JLabel getLbHoTenKH() {
		return lbHoTenKH;
	}
	public JLabel getLbMaNV() {
		return lbMaNV;
	}
	public JLabel getLbHoTenNV() {
		return lbHoTenNV;
	}
	public TableChiTietView getTableChiTietView() {
		return tableChiTietView;
	}
	public JLabel getLbNgayMuon() {
		return lbNgayMuon;
	}
	public JLabel getLbNgayHenTra() {
		return lbNgayHenTra;
	}
	public JLabel getLbTrangThai() {
		return lbTrangThai;
	}
	public JLabel getLbNgayTra() {
		return lbNgayTra;
	}
	public JLabel getLbSoTienPhat() {
		return lbSoTienPhat;
	}
	public JLabel getLbSoTienCoc() {
		return lbSoTienCoc;
	}
	public JLabel getLbTongTienPhat() {
		return lbTongTienPhat;
	}
	public JLabel getLbTongTienThue() {
		return lbTongTienThue;
	}
	public JLabel getLbTongKhuyenMai() {
		return lbTongKhuyenMai;
	}
	public JLabel getLbSoKhuyenMai() {
		return lbSoKhuyenMai;
	}
	
	public ChiTietInformation() {
		setLayout(new BorderLayout());
		add(createHeaderPanel(), BorderLayout.PAGE_START);
		add(createCenterPanel(),  BorderLayout.CENTER);
		add(createFooterPanel(), BorderLayout.PAGE_END);
	}
	
	private JPanel createHeaderPanel() {
		JPanel header = new JPanel (new GridLayout(5, 2, 10, 10));
		header.add(new JLabel("Mã mượn trả:      "));	  		header.add(lbMaMT    = new JLabel());
		header.add(new JLabel("Mã khách hàng:    "));			header.add(lbMaKH    = new JLabel());
		header.add(new JLabel("Họ tên khách hàng:"));			header.add(lbHoTenKH = new JLabel());
		header.add(new JLabel("Mã nhân viên:     "));			header.add(lbMaNV    = new JLabel());
		header.add(new JLabel("Họ tên nhân viên: "));			header.add(lbHoTenNV = new JLabel());
		
		return header;
	}
	
	private JPanel createCenterPanel() {
		JPanel center = new JPanel (new BorderLayout());
		center.add(new JLabel("Danh sách Xe được mượn"), BorderLayout.PAGE_START);
		
		
		// Hmmmm .......... Load Data???????????
		tableChiTietView = new TableChiTietView();
		center.add(tableChiTietView, BorderLayout.CENTER);
		
		return center;
	}
	
	private JPanel createFooterPanel() {
		JPanel footer = new JPanel(new GridLayout(10, 2, 10, 10));
		footer.add(new JLabel("Ngày mượn: "));				footer.add(lbNgayMuon = new JLabel());
		footer.add(new JLabel("Ngày hẹn trả:"));			footer.add(lbNgayHenTra = new JLabel());
		footer.add(new JLabel("Trạng thái:"));				footer.add(lbTrangThai = new JLabel());
		footer.add(new JLabel("Ngày trả: "));				footer.add(lbNgayTra = new JLabel());
		footer.add(new JLabel("Tiền cọc: "));				footer.add(lbSoTienCoc = new JLabel());
		footer.add(new JLabel("Số tiền phạt: "));			footer.add(lbSoTienPhat = new JLabel());
		footer.add(new JLabel("Tiền khuyến mại: "));		footer.add(lbSoKhuyenMai = new JLabel());
		footer.add(new JLabel("Tổng tiền phạt: "));   		footer.add(lbTongTienPhat = new JLabel("0"));
		footer.add(new JLabel("Tổng tiền thuê: "));   		footer.add(lbTongTienThue = new JLabel("0"));
		footer.add(new JLabel("Tổng tiền khuyến mại: "));	footer.add(lbTongKhuyenMai = new JLabel("0"));
		return footer;
	}
}
