package project1.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import project1.model.Xe;
import project1.model.XeDB;
import project1.view.ButtonXeView;
import project1.view.InputXeView;
import project1.view.MainUI;
import project1.view.PanelQuanLyXeView;
import project1.view.TableXeView;

public class AddXeController {
	private MainUI mainUI;
	private Xe xe;
	private XeDB xeDB;
	
	private	PanelQuanLyXeView panelQuanLyXeView;
	private ButtonXeView buttonXeView;
	private InputXeView inputXeView;
	private TableXeView tableXeView;
	
	public AddXeController(MainUI mainUI) {
		this.mainUI = mainUI;
		xeDB = new XeDB();
		
		panelQuanLyXeView = mainUI.getQlXe();
		buttonXeView      = panelQuanLyXeView.getBtnXe();
		inputXeView       = panelQuanLyXeView.getInputXe();
		tableXeView       = panelQuanLyXeView.getTableXe();
		tableXeView.updateTable(xeDB.getAllXe());
		
		setActions();
	}
	
	/*
	 * Check information of input
	 */
	private boolean checkInfor( InputXeView inputXeView) {
		// Are text fields empty?
		if (inputXeView.getTfIdXe().getText().toString().trim().equals("")        ||
			inputXeView.getTfBienXe().getText().toString().trim().equals("")      ||
			inputXeView.getTfTenXe().getText().toString().trim().equals("")       ||
			inputXeView.getTfLoaiXe().getText().toString().trim().equals("")      ||
			inputXeView.getTfHangSanXuat().getText().toString().trim().equals("") ||
			inputXeView.getTfNamSanXuat().getText().toString().trim().equals("")  ||
			inputXeView.getTfNhienLieu().getText().toString().trim().equals("")   ||
			inputXeView.getTfNgayBaoTri().getText().toString().trim().equals("")  ||
			inputXeView.getTfTrangThai().getText().toString().trim().equals("")   ||
			inputXeView.getTfGiaThue().getText().toString().trim().equals(""))  {
			System.out.println("Text Fields are not empty !!!");
			JOptionPane.showMessageDialog(new JDialog(), "Các trường dữ liệu không được để trống");
			return false;
		}
		
		// Check integer?
		try {
			int namsanXuat = Integer.parseInt(inputXeView.getTfNamSanXuat().getText().toString().trim());
			int trangThai  = Integer.parseInt(inputXeView.getTfTrangThai().getText().toString().trim());
			int giaThue    = Integer.parseInt(inputXeView.getTfGiaThue().getText().toString().trim());
			// Test < 0 ????
			if(namsanXuat < 0 || trangThai <0 || giaThue < 0 || namsanXuat > 9999 || namsanXuat < 1000) {
				JOptionPane.showMessageDialog(new JDialog(), "Nhập lại đúng định dạng các trường số !!!");
				return false;
			}	
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(new JDialog(), "Năm sản xuất, trạng thái, giá thuê phải là số");
			System.out.println(e.toString());
			return false;
		}
		
		/* Check if maSach is exist*/
		if (!checkID(inputXeView.getTfIdXe().getText().toString().trim())) {
			JOptionPane.showMessageDialog(new JDialog(), "Mã xe đã tồn tại - Hãy nhập lại");
			return false;
		}
		
		return true;
	}
	
	private boolean checkID(String id) {
		ArrayList<Xe> listXe = xeDB.getAllXe();
		for (int i = 0; i < listXe.size(); i++) {
			String idXeFromDB = listXe.get(i).getIdXe();
			if (id.equals(idXeFromDB)) return false;
		}
		return true;
	}
	
	/*
	 * Set Action for button
	 */
	private void setActions() {
		JButton btnThem = buttonXeView.getBtnThem();
		JButton btnHuy  = buttonXeView.getBtnHuy();
		
		btnThem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				themXe();
			}
		});
		
		btnHuy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				huy();
			}
		});
	}
	
	private void themXe() {
		if (checkInfor(inputXeView)) {
			String idXe = inputXeView.getTfIdXe().getText().toString();
			String bienXe = inputXeView.getTfBienXe().getText().toString();
			String tenXe = inputXeView.getTfTenXe().getText().toString();
			String loaiXe = inputXeView.getTfLoaiXe().getText().toString();
			String hangSanXuat = inputXeView.getTfHangSanXuat().getText().toString();
			String namSanXuat = inputXeView.getTfNamSanXuat().getText().toString();
			String ngayBaoTri = inputXeView.getTfNgayBaoTri().getText().toString();
			String nhienLieu = inputXeView.getTfNhienLieu().getText().toString();
			int trangThai = Integer.parseInt(inputXeView.getTfTrangThai().getText().toString());
			int giaThue = Integer.parseInt(inputXeView.getTfGiaThue().getText().toString());
			
			xe = new Xe(idXe, bienXe, tenXe, loaiXe, hangSanXuat, namSanXuat, ngayBaoTri, nhienLieu, trangThai, giaThue);
			xeDB.insertXe(xe);
			
			tableXeView.updateTable(xeDB.getAllXe());
			clearInput();
		}
		else {
			System.out.println("Insert this xe false!!!");
		}
	}
	
	private void huy() {
		clearInput();
	}
	
	private void clearInput() {
		inputXeView.getTfIdXe().setText("");
		inputXeView.getTfNamSanXuat().setText("");
		inputXeView.getTfBienXe().setText("");
		inputXeView.getTfTenXe().setText("");
		inputXeView.getTfLoaiXe().setText("");
		inputXeView.getTfHangSanXuat().setText("");
		inputXeView.getTfNhienLieu().setText("");
		inputXeView.getTfNgayBaoTri().setText("");
		inputXeView.getTfTrangThai().setText("");
		inputXeView.getTfGiaThue().setText("");
	}
	
	
	
	
	
	
	
	
}
