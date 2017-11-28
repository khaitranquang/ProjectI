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
import project1.model.MuonXe;
import project1.model.MuonXeDB;
import project1.view.MainUI;
import project1.view.TableMuonTraView;

public class SearchDetailController {
	private MainUI mainUI;
	private MuonXe muonXe;
	private MuonXeDB muonXeDB;
	
	private ArrayList<MuonXe> resultSearch = new ArrayList<MuonXe>();
	
	private JTextField tfSearch;
	private JComboBox<String> cbSearchType;
	
	private TableMuonTraView tableMuonTraView;
	private int typeSearch;
	
	private final Color COLOR_DEFAULT   = Color.WHITE;
	private final Color COLOR_NOT_FOUND = Color.PINK;
	
	public ArrayList<MuonXe> getResultSearch() {
		return resultSearch;
	}
	
	public SearchDetailController(MainUI mainUI) {
		this.mainUI = mainUI;
		muonXeDB = new MuonXeDB();
		tableMuonTraView = mainUI.getQlMT().getTableMT();
		tfSearch = mainUI.getQlMT().getBtn().getTfTimKiem();
		cbSearchType = mainUI.getQlKH().getBtnKH().getTimKiemCB();
		
		setActions();
	}
	
	private void setActions() {
		tfSearch.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				typeSearch = cbSearchType.getSelectedIndex();
				tableMuonTraView.updateTable(search(typeSearch));
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		cbSearchType.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				resetSearch();
				return;
			}
		});
	}
	
	// Search Function
	private ArrayList<MuonXe> search(int typeSearch) {
		ArrayList<MuonXe> allMuonXe = muonXeDB.getAllMuonXe();
		int size = allMuonXe.size();
		resultSearch.clear();
		String textFind = tfSearch.getText().trim().toLowerCase();
		
		if (textFind.length() == 0) {
			return allMuonXe;
		}
		
		// Find maMuon
		if (typeSearch == 0) {
			for (int i = 0; i < size; i++) {
				String maMuon = allMuonXe.get(i).getMaMT().trim().toLowerCase();
				if (maMuon.indexOf(textFind) >= 0) {
					resultSearch.add(allMuonXe.get(i));
				}
			}
		}
		
		// Find maKH
		if (typeSearch == 1) {
			for (int i = 0; i < size; i++) {
				String maKH = allMuonXe.get(i).getMaKH().trim().toLowerCase();
				if (maKH.indexOf(textFind) >= 0) {
					resultSearch.add(allMuonXe.get(i));
				}
			}
		}
		
		// Find maNV
		if (typeSearch == 2) {
			for (int i = 0; i < size; i++) {
				String maNV = allMuonXe.get(i).getMaNV().trim().toLowerCase();
				if (maNV.indexOf(textFind) >= 0) {
					resultSearch.add(allMuonXe.get(i));
				}
			}
		}
		
		// Find ngayMuon
		if (typeSearch == 3) {
			for (int i = 0; i < size; i++) {
				String ngayMuon = allMuonXe.get(i).getNgayMuon().trim().toLowerCase();
				if (ngayMuon.indexOf(textFind) >= 0) {
					resultSearch.add(allMuonXe.get(i));
				}
			}
		}
		
		// Find ngayHenTra
		if (typeSearch == 4) {
			for (int i = 0; i < size; i++) {
				String ngayHenTra = allMuonXe.get(i).getNgayHenTra().trim().toLowerCase();
				if (ngayHenTra.indexOf(textFind) >= 0) {
					resultSearch.add(allMuonXe.get(i));
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
			tableMuonTraView.updateTable(search(typeSearch));
		}
		else {
			tableMuonTraView.updateTable(muonXeDB.getAllMuonXe());
		}
	}
	
}
