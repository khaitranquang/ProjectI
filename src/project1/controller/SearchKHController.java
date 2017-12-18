package project1.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import project1.model.KhachHang;
import project1.model.KhachHangDAO;
import project1.model.KhachHangDB;
import project1.view.MainUI;
import project1.view.TableKhachHangView;

public class SearchKHController {
	private MainUI mainUI;
	private KhachHangDAO khachHangDB;
	
	private ArrayList<KhachHang> resultSearch = new ArrayList<KhachHang>();
	
	private JTextField tfSearch;
	private JComboBox<String> cbSearchType;
	
	private TableKhachHangView tableKhachHangView;
	private int typeSearch;
	
	private final Color COLOR_DEFAULT   = Color.WHITE;
	private final Color COLOR_NOT_FOUND = Color.PINK;
	
	public ArrayList<KhachHang> getResultSearch() {
		return resultSearch;
	}
	
	public SearchKHController(MainUI mainUI) {
		this.mainUI = mainUI;
		khachHangDB = new KhachHangDB();
		tableKhachHangView = mainUI.getQlKH().getTableKH();
		tfSearch    = mainUI.getQlKH().getBtnKH().getTfTimKiem();
		cbSearchType = mainUI.getQlKH().getBtnKH().getTimKiemCB();
		
		setActions();
	}
	
	/* -----------------------SET ACTIONS -------------------------- */
	private void setActions() {
		tfSearch.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				typeSearch = cbSearchType.getSelectedIndex();
				tableKhachHangView.updateTable(search(typeSearch));
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				
			}
		});
		
		/* ConmboBox's Event */
		cbSearchType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				resetSearch();
				return;
			}
		});
	}
	
	// Search Function
	private ArrayList<KhachHang> search(int typeSearch) {
		ArrayList<KhachHang> allKH = khachHangDB.getAllKhachHang();
		int size = allKH.size();
		resultSearch.clear();
		String textFind = tfSearch.getText().trim().toLowerCase();
		
		if (textFind.length() == 0) {
			return khachHangDB.getAllKhachHang();
		}
		
		// Find maKH
		if (typeSearch == 0) {
			for (int i = 0; i < size; i++) {
				String maKH = allKH.get(i).getIdKhachHang().trim().toLowerCase();
				if (maKH.indexOf(textFind) >= 0) {
					resultSearch.add(allKH.get(i));
				}
			}
		}
		
		// Find tenKH
		if (typeSearch == 1) {
			for (int i = 0; i < size; i++) {
				String tenKH = allKH.get(i).getTenKhachHang().trim().toLowerCase();
				if (tenKH.indexOf(textFind) >= 0) {
					resultSearch.add(allKH.get(i));
				}
			}
		}
		
		// Find soCMND
		if (typeSearch == 2) {
			for (int i = 0; i < size; i++) {
				String cmnd = allKH.get(i).getSoCMND().trim().toLowerCase();
				if (cmnd.indexOf(textFind) >= 0) {
					resultSearch.add(allKH.get(i));
				}
			}
		}
		
		// Find ngaySinh
		if (typeSearch == 3) {
			for (int i = 0; i < size; i++) {
				String ngaySinh = allKH.get(i).getNgaySinh().trim().toLowerCase();
				if (ngaySinh.indexOf(textFind) >= 0) {
					resultSearch.add(allKH.get(i));
				}
			}
		}
		
		// Find diaChi
		if (typeSearch == 4) {
			for (int i = 0; i < size; i++) {
				String diaChi = allKH.get(i).getDiaChi().trim().toLowerCase();
				if (diaChi.indexOf(textFind) >= 0) {
					resultSearch.add(allKH.get(i));
				}
			}
		}
		
		// Find gioiTinh
		if (typeSearch == 5) {
			for (int i = 0; i < size; i++) {
				String gioiTinh = allKH.get(i).getGioiTinh().trim().toLowerCase();
				if (gioiTinh.indexOf(textFind) >= 0) {
					resultSearch.add(allKH.get(i));
				}
			}
		}
		
		// Find sDT
		if (typeSearch == 6) {
			for (int i = 0; i < size; i++) {
				String sdt = allKH.get(i).getSDT().trim().toLowerCase();
				if (sdt.indexOf(textFind) >= 0) {
					resultSearch.add(allKH.get(i));
				}
			}
		}
		
		// Find email
		if (typeSearch == 7) {
			for (int i = 0; i < size; i++) {
				String email = allKH.get(i).getEmail().trim().toLowerCase();
				if (email.indexOf(textFind) >= 0) {
					resultSearch.add(allKH.get(i));
				}
			}
		}
		
		// Set color
		if (resultSearch.size() == 0) {
			tfSearch.setBackground(COLOR_NOT_FOUND);
		}
		else {
			tfSearch.setBackground(COLOR_DEFAULT);
		}
		return resultSearch;
	}
	
	// Reset Search when cbSearch is changed
	private void resetSearch() {
		typeSearch = cbSearchType.getSelectedIndex();
		tfSearch.setText("");
		tfSearch.requestFocus();
		tfSearch.setBackground(COLOR_DEFAULT);
		updateData();
	}
	
	private void updateData() {
		if (resultSearch.size() > 0) {
			tableKhachHangView.updateTable(search(typeSearch));
		}
		else {
			tableKhachHangView.updateTable(khachHangDB.getAllKhachHang());
		}
	}
}
