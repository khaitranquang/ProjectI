package project1.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import project1.model.KhachHang;
import project1.model.KhachHangDB;
import project1.model.MuonXe;
import project1.model.MuonXeDB;
import project1.model.NhanVien;
import project1.model.NhanVienDB;
import project1.view.EditRentView;
import project1.view.MainUI;
import project1.view.TableMuonTraView;

public class EditRentController {
	private MainUI mainUI;
	private MuonXe muonXe;
	private MuonXeDB muonXeDB;
	
	private EditRentView editRentView;
	private JButton btnEditRent;
	private TableMuonTraView tableMuonTraView;
	
	private ArrayList<String> listMaNV = new ArrayList<String>();
	private ArrayList<String> listMaKH = new ArrayList<String>();
	
	private String[] date = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", 
			 "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
			 "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
	private ArrayList<String> arrDate = new ArrayList<String>(Arrays.asList(date));
	private String[] month = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", 
			  "11", "12"};
	private ArrayList<String> arrMonth = new ArrayList<String>(Arrays.asList(month));
	private String[] year = {"2017", "2018", "2019", "2020"};
	private ArrayList<String> arrYear = new ArrayList<String>(Arrays.asList(year));
	
	public EditRentController(MainUI mainUI) {
		this.mainUI = mainUI;
		muonXeDB = new MuonXeDB();
		btnEditRent = mainUI.getQlMT().getBtn().getBtnSua();
		tableMuonTraView = mainUI.getQlMT().getTableMT();
		
		btnEditEvent();
	}
	
	private int findIndexOfData() {
		int index = tableMuonTraView.getTable().getSelectedRow();
		return index;
	}
	
	private String getMaMT(int indexRow, int indexCol) {
		JTable table = tableMuonTraView.getTable();
		String id = table.getModel().getValueAt(indexRow, indexCol).toString();
		return id;
	}
	
	/*-------------Event - Action--------------- */
	private void btnEditEvent() {
		btnEditRent.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent arg0) {
				editRentView = new EditRentView(mainUI);
				int index = findIndexOfData();
				if (index >= 0) {
					editRentView.setVisible(true);
					String maMT = getMaMT(index, 0);
					editRentView.getCbMaKH().addItem("-- Chon ma DG --");
					KhachHangDB khachHangDB = new KhachHangDB();
					ArrayList<KhachHang> listKH = khachHangDB.getAllKhachHang();
					for (int i = 0; i < listKH.size(); i++) {
						editRentView.getCbMaKH().addItem(listKH.get(i).getIdKhachHang());
						listMaKH.add(listKH.get(i).getIdKhachHang());
					}
					editRentView.getCbMaNV().addItem("-- Chon ma NV --");
					NhanVienDB nhanVienDB = new NhanVienDB();
					ArrayList<NhanVien> listNV = nhanVienDB.getAllNhanVien();
					for (int i = 0; i < listNV.size(); i++) {
						editRentView.getCbMaNV().addItem(listNV.get(i).getIdNhanVien());
						listMaNV.add(listNV.get(i).getIdNhanVien());
					}
					
					loadInfor(maMT);
					
					setActions();
				}
				else {
					JOptionPane.showMessageDialog(mainUI, "Chọn 1 mượn trả để sửa");
				}
			}
		});
	}
	
	private void loadInfor(String maMT) {
		muonXe = muonXeDB.getMuonXe(maMT);
		editRentView.getTfMaMT().setText(maMT);
		editRentView.getTfMaMT().setEditable(false);
		String maKH = muonXe.getMaKH();
		String maNV = muonXe.getMaNV();
		String ngayMuon = muonXe.getNgayMuon();
		String ngayHenTra = muonXe.getNgayHenTra();
		String dateDB = ngayHenTra.substring(0, 2);
		String monthDB = ngayHenTra.substring(3, 5);
		String yearDB = ngayHenTra.substring(6);
		editRentView.getCbMaKH().setSelectedIndex(listMaKH.indexOf(maKH) + 1);
		editRentView.getCbMaNV().setSelectedIndex(listMaNV.indexOf(maNV) + 1);
		editRentView.getCbNgayHenTra().setSelectedIndex(arrDate.indexOf(dateDB));
		editRentView.getCbThangHenTra().setSelectedIndex(arrMonth.indexOf(monthDB));
		editRentView.getCbNamHenTra().setSelectedIndex(arrYear.indexOf(yearDB));
		editRentView.getLbHoTenKH().setText(new KhachHangDB().getKhachHang(maKH).getTenKhachHang());
		editRentView.getLbHoTenNV().setText(new NhanVienDB().getNhanVien(maNV).getTenNhanVien());
		editRentView.getTfTienCoc().setText(muonXe.getTienCoc() + "");
		editRentView.getTfNgayMuon().setText(ngayMuon);
		editRentView.getTfNgayMuon().setEditable(false);
	}
	
	/*--------------------  Set Actions ----------------- */
	private void setActions() {
		JButton btnEdit = editRentView.getBtnEdit();
		JButton btnCancel = editRentView.getBtnCancel();
		JComboBox<String> cbMaKH = editRentView.getCbMaKH();
		JComboBox<String> cbMaNV = editRentView.getCbMaNV();
		
		cbMaKH.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(cbMaKH.getSelectedIndex() == 0) {
					editRentView.getLbHoTenKH().setText("");
					return;
				}
				String hotenKH = new KhachHangDB().getKhachHang(cbMaKH.getSelectedItem().toString()).getTenKhachHang();
				editRentView.getLbHoTenKH().setText(hotenKH);
			}
		});
		
		cbMaNV.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cbMaNV.getSelectedIndex() == 0) {
					editRentView.getLbHoTenNV().setText("");
					return;
				}
				String hotenNV = new NhanVienDB().getNhanVien(cbMaNV.getSelectedItem().toString()).getTenNhanVien();
				editRentView.getLbHoTenNV().setText(hotenNV);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				suaMT();
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				huy();
			}
		});
	}
	
	private void suaMT() {
		if (checkInfor(editRentView)) {
			String maKHMoi       = editRentView.getCbMaKH().getSelectedItem().toString();
			String maNVMoi       = editRentView.getCbMaNV().getSelectedItem().toString();
			String ngayHenTraMoi = editRentView.getCbNgayHenTra().getSelectedItem().toString() + "-"
					   		     + editRentView.getCbThangHenTra().getSelectedItem().toString() + "-"
					             + editRentView.getCbNamHenTra().getSelectedItem().toString();
			int tienCocMoi       = Integer.parseInt(editRentView.getTfTienCoc().getText().trim().toString());
		
			muonXeDB.editMuonXe(muonXe, maKHMoi, maNVMoi, ngayHenTraMoi, tienCocMoi);
			tableMuonTraView.updateTable(muonXeDB.getAllMuonXe());
			
			this.editRentView.setVisible(false);
		}
		else {
			System.out.println("Edit Fail !!!");
		}
	}
	
	private void huy() {
		this.editRentView.setVisible(false);
	}
	
	private boolean checkInfor(EditRentView editRentView) {
		// Check empty?
		if (editRentView.getTfTienCoc().getText().toString().trim().equals("") ||
			editRentView.getCbMaKH().getSelectedIndex() == 0 || 
			editRentView.getCbMaNV().getSelectedIndex() == 0) {
			System.out.println("Khong de trong cac truong du lieu");
			JOptionPane.showMessageDialog(this.editRentView, "Các trường dữ liệu không được để trống");
			return false;
		}
		
		// Check number field
		try {
			String tienCoc = editRentView.getTfTienCoc().getText().trim().toString();
			int money = Integer.parseInt(tienCoc);
			
			// Test < 0 ????
			if(money < 0) {
				JOptionPane.showMessageDialog(this.editRentView, "Nhập đúng định dạng các trường số !!!!");
				return false;
			}	
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this.editRentView, "Tiền cọc phải là số nguyên");
			System.out.println(e.toString());
			return false;
		}
		return true;
	}
}
