package project1.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import project1.model.Xe;
import project1.model.XeDB;
import project1.view.ButtonXeView;
import project1.view.InputXeView;
import project1.view.MainUI;
import project1.view.PanelQuanLyXeView;
import project1.view.TableXeView;

public class EditXeController {
	private MainUI mainUI;
	private Xe xe;
	private XeDB xeDB;
	private String oldID = "";
	private int indexOfRow = -1;
	
	private	PanelQuanLyXeView panelQuanLyXeView;
	private ButtonXeView buttonXeView;
	private InputXeView inputXeView;
	private TableXeView tableXeView;
	
	JButton btnSua;
	
	public EditXeController(MainUI mainUI) {
		this.mainUI = mainUI;
		xeDB = new XeDB();
		
		panelQuanLyXeView = mainUI.getQlXe();
		buttonXeView      = panelQuanLyXeView.getBtnXe();
		inputXeView       = panelQuanLyXeView.getInputXe();
		tableXeView       = panelQuanLyXeView.getTableXe();
		btnSua            = buttonXeView.getBtnSua();
		setActions();
	}
	
	/*
	 * Set Actions for button(
	 */
	private void setActions() {
		btnSua.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = findIndexOfData();
				if (index >= 0) {
					oldID = getID(index, 0);
					loadInfor(oldID);
					updateXe();
					return;
				}
				else {
					JOptionPane.showMessageDialog(mainUI, "Chọn 1 xe để sửa");
					return;
				}
			}
		});
		
	}
	
	// Find index
	private int findIndexOfData(){
		int index = tableXeView.getTable().getSelectedRow();
		return index;
	}
	
	private String getID(int indexRow, int indexCol) {
		JTable table = tableXeView.getTable();
		String id = table.getModel().getValueAt(indexRow, indexCol).toString();
		return id;
	}
	
	private void loadInfor(String id) {
		xe = xeDB.getXe(id);
		inputXeView.getTfIdXe().setText(id);
		inputXeView.getTfBienXe().setText(xe.getBienXe());
		inputXeView.getTfTenXe().setText(xe.getTenXe());
		inputXeView.getTfLoaiXe().setText(xe.getLoaiXe());
		inputXeView.getTfHangSanXuat().setText(xe.getHangSanXuat());
		inputXeView.getTfNamSanXuat().setText(xe.getNamSanXuat());
		inputXeView.getTfTrangThai().setText(xe.getTrangThai()+"");
		inputXeView.getTfNhienLieu().setText(xe.getNhienLieu());;
		inputXeView.getTfGiaThue().setText(xe.getGiaThue() + "");
		inputXeView.getTfNgayBaoTri().setText(xe.getNgayBaoTri());
	}
	
	/*----Action Update Xe -----*/
	private void updateXe() {
		JButton btnUpdate = inputXeView.getBtnUpdate();
		btnUpdate.setVisible(true);
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				suaXe();
				return;
			}
		});
	}

	private void suaXe() {
		boolean check = checkInfor(inputXeView);
		if (check) {
			String maXeMoi        = inputXeView.getTfIdXe().getText().toString();
			String bienXeMoi      = inputXeView.getTfBienXe().getText().toString();
			String tenXeMoi       = inputXeView.getTfTenXe().getText().toString();
			String loaiXeMoi      = inputXeView.getTfLoaiXe().getText().toString();
			String hangSanXuatMoi = inputXeView.getTfHangSanXuat().getText().toString();
			String namSanXuatMoi  = inputXeView.getTfNamSanXuat().getText().toString();
			String ngayBaoTriMoi  = inputXeView.getTfNgayBaoTri().getText().toString();
			String nhienLieuMoi   = inputXeView.getTfNhienLieu().getText().toString();
			int trangThaiMoi      = Integer.parseInt(inputXeView.getTfTrangThai().getText().toString());
			int giaThueMoi        = Integer.parseInt(inputXeView.getTfGiaThue().getText().toString());
		
			xeDB.updateXe(this.xe, maXeMoi, bienXeMoi, tenXeMoi, loaiXeMoi, hangSanXuatMoi, namSanXuatMoi, ngayBaoTriMoi, nhienLieuMoi, trangThaiMoi, giaThueMoi);
//			JOptionPane.showMessageDialog(new JDialog(), "Cập nhật thông tin xe thành công");
//			tableXeView.getTable().getSelectionModel().clearSelection();
			//tableXeView.getTable().getSelectionModel().isSelectionEmpty();
			tableXeView.updateTable(xeDB.getAllXe());
			//clearInput();
			inputXeView.getBtnUpdate().setVisible(false);
			return;
		}
		else {
			System.out.println("Edit fail !!!");
			return;
		}
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
			if (id.equals(idXeFromDB) && (id.equals(oldID) == false)) return false;
		}
		return true;
	}
	
	/* Clear input */
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


