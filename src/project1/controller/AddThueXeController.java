package project1.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import project1.model.ChiTiet;
import project1.model.ChiTietDB;
import project1.model.KhachHang;
import project1.model.KhachHangDB;
import project1.model.MuonXe;
import project1.model.MuonXeDB;
import project1.model.NhanVien;
import project1.model.NhanVienDB;
import project1.model.Xe;
import project1.model.XeDB;
import project1.view.AddRentView;
import project1.view.MainUI;
import project1.view.TableMuonTraView;
import project1.view.TableXeView;
import project1.view.ThueXeInformation;
import project1.view.XeDuocMuonView;

public class AddThueXeController {
	private static final double PERCENT_KM = 0.5;
	
	private MainUI mainUI;
	private MuonXe muonXe;
	private MuonXeDB muonXeDB;
	
	private AddRentView addRentView;
	private ThueXeInformation thueXeInformation;
	private TableMuonTraView tableMuonTraView;
	
	private ArrayList<Xe> listXeIsRent = new ArrayList<Xe>();
	private JPanel rightPanel;
	
	private ArrayList<XeDuocMuonView> arrXeDuocThueView = new ArrayList<XeDuocMuonView>();

	/* Constructor */
	public AddThueXeController(MainUI mainUI) {
		this.mainUI = mainUI;
		muonXeDB = new MuonXeDB();
		JButton btnAddMuonXe = mainUI.getQlMT().getBtn().getBtnThem();
		tableMuonTraView = mainUI.getQlMT().getTableMT();
		tableMuonTraView.updateTable(muonXeDB.getAllMuonXe());
		
		btnAddMuonXe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addRentView = new AddRentView(mainUI);
				thueXeInformation = addRentView.getThueXeInformation();
				rightPanel = thueXeInformation.getRightPanel();
				
				thueXeInformation.getCbMaKH().addItem("-- Chọn mã khách hàng --");
				KhachHangDB khachHangDB = new KhachHangDB();
				ArrayList<KhachHang> listKH = khachHangDB.getAllKhachHang();
				for (int i = 0; i < listKH.size(); i++) {
					thueXeInformation.getCbMaKH().addItem(listKH.get(i).getIdKhachHang());;
				}
				
				thueXeInformation.getCbMaNV().addItem("-- Chọn mã nhân viên --");
				NhanVienDB nhanVienDB = new NhanVienDB();
				ArrayList<NhanVien> listNV = nhanVienDB.getAllNhanVien();
				for (int i = 0; i < listKH.size(); i++) {
					thueXeInformation.getCbMaNV().addItem(listNV.get(i).getIdNhanVien());;
				}
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
				LocalDate localDate   = LocalDate.now();
				String ngayMuonHT     = Integer.toString(localDate.getDayOfMonth());
				String thangMuonHT    = Integer.toString(localDate.getMonthValue());
				String namMuonHT      = Integer.toString(localDate.getYear());
				thueXeInformation.getTfNgayMuon().setText(ngayMuonHT + "-" + thangMuonHT + "-" + namMuonHT);
				thueXeInformation.getTfNgayMuon().setEditable(false);
				
				JButton btnThemXeDuocThue = thueXeInformation.getBtnThemXe();
				btnThemXeDuocThue.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						setActionForBtnThemXeThue();
						setActionOnRightPanel();
					}
				});
				
				JComboBox<String> cbMaKH = thueXeInformation.getCbMaKH();
				cbMaKH.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(cbMaKH.getSelectedIndex() == 0) {
							thueXeInformation.getLbHoTenKH().setText("");
							return;
						}
						String hotenKH = new KhachHangDB().getKhachHang( cbMaKH.getSelectedItem().toString()).getTenKhachHang();
						thueXeInformation.getLbHoTenKH().setText(hotenKH);
					}
				});
				
				JComboBox<String> cbMaNV = thueXeInformation.getCbMaNV();
				cbMaNV.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if(cbMaNV.getSelectedIndex() == 0) {
							thueXeInformation.getLbHoTenNV().setText("");
							return;
						}
						String hotenNV = new NhanVienDB().getNhanVien(cbMaNV.getSelectedItem().toString()).getTenNhanVien();
						thueXeInformation.getLbHoTenNV().setText(hotenNV);
					}
				});
				
				addRentView.setVisible(true);
				setMainActions();
			}
		});
	}

	// Set Actions for btnThemXeDuocThue
	private void setActionForBtnThemXeThue() {
		if (!checkXe(thueXeInformation)) {
			return;
		}
		else if (new XeDB().getXe(thueXeInformation.getTfMaXe().getText().toString()).getTrangThai() == 0) {
			JOptionPane.showMessageDialog(new JDialog(), "Xe này đã được thuê - Hiện không có sẵn" + "\n"
					   + "Hãy nhập lại mã xe khác");
			return;
		}
		else {
			String maXe = thueXeInformation.getTfMaXe().getText().toString();
			Xe xe = new XeDB().getXe(maXe);
			String giaThue = xe.getGiaThue() + "";
			String tenXe   = xe.getTenXe();
			listXeIsRent.add(xe);
			
			XeDuocMuonView xeDuocMuonView = new XeDuocMuonView(maXe, tenXe, giaThue);
			arrXeDuocThueView.add(xeDuocMuonView);
			rightPanel.add(xeDuocMuonView);
			
			rightPanel.revalidate();
			rightPanel.repaint();
			rightPanel.setVisible(true);
			thueXeInformation.getTfMaXe().setText("");
		}
		
	}
	
	/* Check id of xe and check text field "Add xe" to rent */
	/* Maximum of xe is allowed rent is 3*/
	private boolean checkXe(ThueXeInformation thueXeInformation) {
		if (thueXeInformation.getTfMaXe().getText().toString().trim().equals("")) {
			JOptionPane.showMessageDialog(new JDialog(), "Không để trống mã xe");
			return false;
		}
		// Check maXe is exist?
		if (!checkMaXe(thueXeInformation.getTfMaXe().getText().toString())) {
			JOptionPane.showMessageDialog(new JDialog(), "Mã xe này không tồn tại" + "\n" +
					 "Chú ý mã xe phân biệt in hoa thường");
			return false;
		}
		// Check maximum xe is allowed rent
		if (listXeIsRent.size() > 3) {
			JOptionPane.showMessageDialog(new JDialog(), "Không được mượn quá 3 loại xe");
			return false;
		}
		
		// Check if xe is rent
		for (int i = 0; i < listXeIsRent.size(); i++) {
			if (thueXeInformation.getTfMaXe().getText().toString().equals(listXeIsRent.get(i).getIdXe())) {
				JOptionPane.showMessageDialog(new JDialog(), "Mã xe này đã được chọn" + "\n" + 
						 "Vui lòng thêm xe khác");
				return false;
			}
		}

		return true;
	}
	
	private boolean checkMaXe(String maXe) {
		XeDB xeDB = new XeDB();
		ArrayList<Xe> listXe = xeDB.getAllXe();
		for (int i = 0; i < listXe.size(); i++) {
			if (maXe.equals(listXe.get(i).getIdXe())) return true;
		}
		return false;
	}

	/* Action on right Panel */
	private void setActionOnRightPanel() {
		System.out.println(arrXeDuocThueView.size());
		for (int i = 0; i < arrXeDuocThueView.size(); i++) {
			XeDuocMuonView xeDuocMuonView = arrXeDuocThueView.get(i);
			JMenuItem menuItem    = new JMenuItem("Remove");
		    JPopupMenu menuPopup  = new JPopupMenu();
		    menuPopup.add(menuItem);
		    xeDuocMuonView.setComponentPopupMenu(menuPopup);
		   
		    String maXe = xeDuocMuonView.getLbMaXe().getText().toString();
		    
		    menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for (int j = 0; j < listXeIsRent.size(); j++) {
						if (listXeIsRent.get(j).getIdXe().equals(maXe)) 
							listXeIsRent.remove(j);
					}
					arrXeDuocThueView.remove(xeDuocMuonView);
					rightPanel.remove(xeDuocMuonView);
					rightPanel.revalidate();
					rightPanel.repaint();
				}
			});
			
		}
	}
	
	/*------------------------------Actions----------------------*/
	// Check format of text field
	private boolean checkInfor(ThueXeInformation thueXeInformation) {
		// Are text fields empty?
		if (thueXeInformation.getTfMaMT().getText().toString().trim().equals("")        ||
			thueXeInformation.getTfNgayMuon().getText().toString().trim().equals("")    ||
			thueXeInformation.getCbMaKH().getSelectedIndex() == 0 ||
			thueXeInformation.getCbMaNV().getSelectedIndex() == 0 ||
			thueXeInformation.getTfTienCoc().getText().toString().trim().equals("")     ||
			listXeIsRent.size() == 0) {
				System.out.println("Khong de trong cac truong du lieu");
				JOptionPane.showMessageDialog(this.addRentView, "Các trường dữ liệu không được để trống");
				return false;
			}
		/* Check if maMT is exist */
		if (!checkMaMT(thueXeInformation.getTfMaMT().getText().toString())) {
			JOptionPane.showMessageDialog(new JDialog(), "Mã mượn trả đã tồn tại - Hãy nhập lại");
			return false;
		}
		/* Check ngayHenTra*/
		// Check format of ngayHenTra
		String month = thueXeInformation.getCbThangHenTra().getSelectedItem().toString();
		String date = thueXeInformation.getCbNgayHenTra().getSelectedItem().toString();
		if ((month.equals("4") || month.equals("6") || month.equals("9") || month.equals("11")) && date.equals("31")) {
			JOptionPane.showMessageDialog(new JDialog(), "Tháng 4, 6, 9, 11 không có 31 ngày");
			return false;
		}
		if (month.equals("2") && (date.equals("30") || date.equals("31"))) {
			JOptionPane.showMessageDialog(new JDialog(), "Tháng 2 không có quá 29 ngày");
			return false;
		}
		
		// Check tienCoc is integer?
		try {
			int tienCoc = Integer.parseInt(thueXeInformation.getTfTienCoc().getText().toString());
			// Test < 0 ????
			if(tienCoc <= 0) {
				JOptionPane.showMessageDialog(this.addRentView, "Nhập lại đúng định dạng các trường số !!!");
				return false;
			}	
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this.thueXeInformation, "Tiền cọc phải là số nguyên");
			System.out.println(e.toString());
		}	
		
		return true;
	}
	
	private boolean checkMaMT (String maMT) {
		ArrayList<MuonXe> listThueXe = muonXeDB.getAllMuonXe();
		for (int i = 0; i < listThueXe.size(); i ++) {
			String maMTFromDB = listThueXe.get(i).getMaMT();
			if (maMT.equals(maMTFromDB)) return false;
		}
		return true;
	}
	
	/*----------------------------- SET MAIN ACTIONS _---------------------------*/
	private void setMainActions() {
		JButton btnThemMT   = addRentView.getBtnAdd();
		JButton btnTaoLaiMT = addRentView.getBtnReset();
		JButton btnHuyMT    = addRentView.getBtnCancel();
		
		// Set actions for each button
		btnThemMT.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				themMT();
			}
		});
		
		btnTaoLaiMT.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				taoLaiMT();
			}
		});
		
		btnHuyMT.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				huyMT();
			}
		});
	}
	
	private void taoLaiMT() {
		clearInput();
	}

	private void huyMT() {
		clearInput();
		this.addRentView.setVisible(false);
	}
	
	private void clearInput() {
		thueXeInformation.getTfMaMT().setText("");
		thueXeInformation.getTfMaXe().setText("");
		thueXeInformation.getTfTienCoc().setText("");
		thueXeInformation.getCbMaKH().setSelectedIndex(0);
		thueXeInformation.getCbMaNV().setSelectedIndex(0);
		thueXeInformation.getCbNgayHenTra().setSelectedIndex(0);
		thueXeInformation.getCbThangHenTra().setSelectedIndex(0);
		thueXeInformation.getCbNamHenTra().setSelectedIndex(0);
		arrXeDuocThueView.clear();
		listXeIsRent.clear();
	}
	
	private void themMT() {
		if (checkInfor(thueXeInformation)) {
			String maMT       = thueXeInformation.getTfMaMT().getText().toString();
			String maKH       = thueXeInformation.getCbMaKH().getSelectedItem().toString();
			String maNV       = thueXeInformation.getCbMaNV().getSelectedItem().toString();
			String ngayMuon   = thueXeInformation.getTfNgayMuon().getText().toString();
			String ngayHenTra = thueXeInformation.getCbNgayHenTra().getSelectedItem().toString() + "-"
							   + thueXeInformation.getCbThangHenTra().getSelectedItem().toString() + "-"
							   + thueXeInformation.getCbNamHenTra().getSelectedItem().toString();
			int tienCoc       = Integer.parseInt(thueXeInformation.getTfTienCoc().getText().trim().toString());
			
			muonXe = new MuonXe(maMT, maKH, maNV, ngayMuon, ngayHenTra, tienCoc);
			muonXeDB.insertMuonXe(muonXe);

			
			for (int i = 0; i < arrXeDuocThueView.size(); i++) {
				String maXe  = arrXeDuocThueView.get(i).getLbMaXe().getText().trim().toString();
				String tenXe = arrXeDuocThueView.get(i).getLbTenXe().getText().trim().toString();
				int tienThue   = Integer.parseInt(arrXeDuocThueView.get(i).getLbTienThue().getText().trim().toString());
//				int tienKhuyenMai = (int) (tienThue * PERCENT_KM);
				int tienKhuyenMai = 0;
			
				ChiTiet chiTiet = new ChiTiet(maMT, maXe, tienThue, "", 0, tienKhuyenMai);
				ChiTietDB chiTietDB = new ChiTietDB();
				chiTietDB.insertChiTiet(chiTiet);
			}
			
			
			// Recreate status of Xe is Loan
			for (int i = 0; i < listXeIsRent.size(); i++) {
				int trangThaiMoi = 0;
				XeDB xeDB = new XeDB();
				xeDB.updateXe(listXeIsRent.get(i), trangThaiMoi);
			}
			TableXeView tableXeView = mainUI.getQlXe().getTableXe();
			tableXeView.updateTable(new XeDB().getAllXe());
			
			
			tableMuonTraView.updateTable(muonXeDB.getAllMuonXe());
			clearInput();
			this.addRentView.setVisible(false);
		}
		else {
			System.out.println("Insert rentCar is fail !!!");
		}
	}
	
	
	
	
	
	
	
	


}
