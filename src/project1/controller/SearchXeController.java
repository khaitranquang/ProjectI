package project1.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import project1.model.Xe;
import project1.model.XeDAO;
import project1.model.XeDB;
import project1.view.MainUI;
import project1.view.TableXeView;

public class SearchXeController {
	private MainUI mainUI;
	private XeDAO xeDB;
	
	private ArrayList<Xe> resultSearch = new ArrayList<Xe>();
	
	private JTextField tfSearch;
	private JComboBox<String> cbSearchType;
	private TableXeView tableXeView;
	private int typeSearch;
	
	private final Color COLOR_DEFAULT   = Color.WHITE;
	private final Color COLOR_NOT_FOUND = Color.PINK;
	
	public ArrayList<Xe> getResultSearch() {
		return resultSearch;
	}
	
	public SearchXeController(MainUI mainUI) {
		this.mainUI = mainUI;
		xeDB = new XeDB();
		tableXeView = mainUI.getQlXe().getTableXe();
		tfSearch    = mainUI.getQlXe().getBtnXe().getTfTimKiem();
		cbSearchType = mainUI.getQlXe().getBtnXe().getTimKiemCB();
		
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
				tableXeView.updateTable(search(typeSearch));
				
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
	private ArrayList<Xe> search(int typeSearch) {
		ArrayList<Xe> allXe = xeDB.getAllXe();
		int size = allXe.size();
		resultSearch.clear();
		String textFind = tfSearch.getText().trim().toLowerCase();
		
		if (textFind.length() == 0) {
			return xeDB.getAllXe();
		}
		
		//Find tenXe
		if (typeSearch == 0) {
			for (int i = 0; i < size; i++) {
				String tenXe = allXe.get(i).getTenXe().trim().toLowerCase();
				if (tenXe.indexOf(textFind) >= 0) {
					resultSearch.add(allXe.get(i));
				}
			}
		}
		
		// Find maXe;
		if (typeSearch == 1) {
			for (int i = 0; i < size; i++) {
				String maXe = allXe.get(i).getIdXe().trim().toLowerCase();
				if (maXe.indexOf(textFind) >= 0) {
					resultSearch.add(allXe.get(i));
				}
			}
		}
		
		// Find bienXe
		if (typeSearch == 2) {
			for (int i = 0; i < size; i++) {
				String bienXe = allXe.get(i).getBienXe().trim().toLowerCase();
				if (bienXe.indexOf(textFind) >= 0) {
					resultSearch.add(allXe.get(i));
				}
			}
		}
		
		// Find loaiXe
		if (typeSearch == 3) {
			for (int i = 0; i < size; i++) {
				String loaiXe = allXe.get(i).getLoaiXe().trim().toLowerCase();
				if (loaiXe.indexOf(textFind) >= 0) {
					resultSearch.add(allXe.get(i));
				}
			}
		}
		
		// Find hangSanXuat
		if (typeSearch == 4) {
			for (int i = 0; i < size; i++) {
				String hangSX = allXe.get(i).getHangSanXuat().trim().toLowerCase();
				if (hangSX.indexOf(textFind) >= 0) {
					resultSearch.add(allXe.get(i));
				}
			}
		}
		
		// Find namSX
		if (typeSearch == 5) {
			for (int i = 0; i < size; i++) {
				String namSX = allXe.get(i).getNamSanXuat().trim().toLowerCase();
				if (namSX.indexOf(textFind) >= 0) {
					resultSearch.add(allXe.get(i));
				}
			}
		}
		
		// Find ngayBaoTri
		if (typeSearch == 6) {
			for (int i = 0; i < size; i++) {
				String ngayBT = allXe.get(i).getNgayBaoTri().trim().toLowerCase();
				if (ngayBT.indexOf(textFind) >= 0) {
					resultSearch.add(allXe.get(i));
				}
			}
		}
		
		// Find nhienLieu
		if (typeSearch == 7) {
			for (int i = 0; i < size; i++) {
				String nhienLieu = allXe.get(i).getNhienLieu().trim().toLowerCase();
				if (nhienLieu.indexOf(textFind) >= 0) {
					resultSearch.add(allXe.get(i));
				}
			}
		}
		
		// Find trangThai
		if (typeSearch == 8) {
			for (int i = 0; i < size; i++) {
				String trangThai = (allXe.get(i).getTrangThai() + "").trim().toLowerCase();
				if (trangThai.indexOf(textFind) >= 0) {
					resultSearch.add(allXe.get(i));
				}
			}
		}
		
		// Find giaThue
		if (typeSearch == 9) {
			for (int i = 0; i < size; i++) {
				String giaThue = (allXe.get(i).getGiaThue() + "").trim().toLowerCase();
				if (giaThue.indexOf(textFind) >= 0) {
					resultSearch.add(allXe.get(i));
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
			tableXeView.updateTable(search(typeSearch));
		}
		else {
			tableXeView.updateTable(xeDB.getAllXe());
		}
	}
}
