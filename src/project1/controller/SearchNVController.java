package project1.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import project1.model.NhanVien;
import project1.model.NhanVienDAO;
import project1.model.NhanVienDB;
import project1.view.MainUI;
import project1.view.TableNhanVienView;

public class SearchNVController {
	private MainUI mainUI;
	private NhanVienDAO nhanVienDB;
	
	private ArrayList<NhanVien> resultSearch = new ArrayList<NhanVien>();
	
	private JTextField tfSearch;
	private JComboBox<String> cbSearchType;
	
	private TableNhanVienView tableNhanVienView;
	private int typeSearch;
	
	private final Color COLOR_DEFAULT   = Color.WHITE;
	private final Color COLOR_NOT_FOUND = Color.PINK;
	
	public ArrayList<NhanVien> getResultSearch() {
		return resultSearch;
	}
	
	public SearchNVController(MainUI mainUI) {
		this.mainUI = mainUI;
		nhanVienDB = new NhanVienDB();
		tableNhanVienView = mainUI.getQlNV().getTableNV();
		tfSearch    = mainUI.getQlNV().getBtnNV().getTfTimKiem();
		cbSearchType = mainUI.getQlNV().getBtnNV().getTimKiemCB();
		
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
				tableNhanVienView.updateTable(search(typeSearch));
				
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
	private ArrayList<NhanVien> search(int typeSearch) {
		ArrayList<NhanVien> allNV = nhanVienDB.getAllNhanVien();
		int size = allNV.size();
		resultSearch.clear();
		String textFind = tfSearch.getText().trim().toLowerCase();
		
		if (textFind.length() == 0) {
			return nhanVienDB.getAllNhanVien();
		}
		
		// Find tenNV
		if (typeSearch == 0) {
			for (int i = 0; i < size; i++) {
				String tenNV = allNV.get(i).getTenNhanVien().trim().toLowerCase();
				if (tenNV.indexOf(textFind) >= 0) {
					resultSearch.add(allNV.get(i));
				}
			}
		}
		
		// Find maNV
		if (typeSearch == 1) {
			for (int i = 0; i < size; i++) {
				String tenKH = allNV.get(i).getIdNhanVien().trim().toLowerCase();
				if (tenKH.indexOf(textFind) >= 0) {
					resultSearch.add(allNV.get(i));
				}
			}
		}
		
		// Find soCMND
		if (typeSearch == 2) {
			for (int i = 0; i < size; i++) {
				String cmnd = allNV.get(i).getSoCMND().trim().toLowerCase();
				if (cmnd.indexOf(textFind) >= 0) {
					resultSearch.add(allNV.get(i));
				}
			}
		}
		
		// Find ngaySinh
		if (typeSearch == 3) {
			for (int i = 0; i < size; i++) {
				String ngaySinh = allNV.get(i).getNgaySinh().trim().toLowerCase();
				if (ngaySinh.indexOf(textFind) >= 0) {
					resultSearch.add(allNV.get(i));
				}
			}
		}
		
		// Find diaChi
		if (typeSearch == 4) {
			for (int i = 0; i < size; i++) {
				String diaChi = allNV.get(i).getDiaChi().trim().toLowerCase();
				if (diaChi.indexOf(textFind) >= 0) {
					resultSearch.add(allNV.get(i));
				}
			}
		}
		
		// Find gioiTinh
		if (typeSearch == 5) {
			for (int i = 0; i < size; i++) {
				String gioiTinh = allNV.get(i).getGioiTinh().trim().toLowerCase();
				if (gioiTinh.indexOf(textFind) >= 0) {
					resultSearch.add(allNV.get(i));
				}
			}
		}
		
		// Find sDT
		if (typeSearch == 6) {
			for (int i = 0; i < size; i++) {
				String sdt = allNV.get(i).getSDT().trim().toLowerCase();
				if (sdt.indexOf(textFind) >= 0) {
					resultSearch.add(allNV.get(i));
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
			tableNhanVienView.updateTable(search(typeSearch));
		}
		else {
			tableNhanVienView.updateTable(nhanVienDB.getAllNhanVien());
		}
	}
}
