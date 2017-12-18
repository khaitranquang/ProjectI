package project1.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import project1.model.NhanVien;
import project1.model.NhanVienDAO;
import project1.model.NhanVienDB;
import project1.view.EditNhanVienView;
import project1.view.MainUI;
import project1.view.NhanVienInformation;
import project1.view.TableNhanVienView;

public class EditNhanVienController {
	private static final Pattern DATE_PATTERN = Pattern.compile(
			"^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|"
			+ "-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|"
			+ "^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468]"
			+ "[048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|"
			+ "^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4("
			+ "?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
	
	private MainUI mainUI;
	private NhanVien nhanVien;
	private NhanVienDAO nhanVienDB;
	private String oldID = "";
	
	private EditNhanVienView editNhanVienView;
	private JButton btnEdit;
	private NhanVienInformation nhanVienInformation;
	private TableNhanVienView tableNhanVienView;
	
	public EditNhanVienController(MainUI mainUI) {
		this.mainUI = mainUI;
		nhanVienDB = new NhanVienDB();
		btnEdit = mainUI.getQlNV().getBtnNV().getBtnSua();
		tableNhanVienView = mainUI.getQlNV().getTableNV();
		tableNhanVienView.updateTable(nhanVienDB.getAllNhanVien());
		btnEditEvent();
	}
	
	/* Event - Action */
	private void btnEditEvent() {
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editNhanVienView = new EditNhanVienView(mainUI);
				nhanVienInformation = editNhanVienView.getNhanVienInformation();
				
				int index = findIndexOfData();
				if (index >= 0) {
					editNhanVienView.setVisible(true);
					oldID = getID(index, 0);
					loadInfor(oldID);
					
					setActions();
				}
				else {
					JOptionPane.showMessageDialog(mainUI, "Chọn 1 nhân viên để sửa");
				}
			}
		});
	}
	
	// Find index
	private int findIndexOfData(){
		int index = tableNhanVienView.getTable().getSelectedRow();
		return index;
	}
	
	private String getID(int indexRow, int indexCol) {
		JTable table = tableNhanVienView.getTable();
		String id = table.getModel().getValueAt(indexRow, indexCol).toString();
		return id;
	}
	
	private void loadInfor(String id) {
		nhanVien = nhanVienDB.getNhanVien(id);
		nhanVienInformation.getTfIdNhanVien().setText(id);
		nhanVienInformation.getTfTenNhanVien().setText(nhanVien.getTenNhanVien());
		nhanVienInformation.getTfSoCMND().setText(nhanVien.getSoCMND());
		nhanVienInformation.getTfNgaySinh().setText(nhanVien.getNgaySinh());
		nhanVienInformation.getTfDiaChi().setText(nhanVien.getDiaChi());
		nhanVienInformation.getTfGioiTinh().setText(nhanVien.getGioiTinh());
		nhanVienInformation.getTfSDT().setText(nhanVien.getSDT());
	}
	
	private void setActions() {
		JButton btnEdit = editNhanVienView.getBtnEdit();
		JButton btnCancel = editNhanVienView.getBtnCancel();
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				suaNV();
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				huy();
			}
		});
	}
	
	private void huy() {
		this.editNhanVienView.setVisible(false);
	}
	
	/*
	 * Check information of input
	 */
	private boolean checkInfor (NhanVienInformation nhanVienInformation) {
		// Are text fields empty?
		if (nhanVienInformation.getTfIdNhanVien().getText().toString().trim().equals("") ||
			nhanVienInformation.getTfTenNhanVien().getText().toString().trim().equals("") ||
			nhanVienInformation.getTfNgaySinh().getText().toString().trim().equals("") ||
			nhanVienInformation.getTfSoCMND().getText().toString().trim().equals("") ||
			nhanVienInformation.getTfDiaChi().getText().toString().trim().equals("") ||
			nhanVienInformation.getTfSDT().getText().toString().trim().equals("") ||
			nhanVienInformation.getTfGioiTinh().getText().toString().trim().equals("")) {
			System.out.println("Text Fields are not empty !!!");
			JOptionPane.showMessageDialog(new JDialog(), "Các trường dữ liệu không được để trống");
			return false;
		}
		
		// Check number?
		try {
			int soCMND = Integer.parseInt(nhanVienInformation.getTfSoCMND().getText().toString());
			int   sdt  = Integer.parseInt(nhanVienInformation.getTfSDT().getText().toString());
			// Test < 0 ????
			if(soCMND < 0 || sdt <0) {
				JOptionPane.showMessageDialog(new JDialog(), "Nhập lại đúng định dạng các trường số !!!");
					return false;
			}	
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(new JDialog(), "Số CMND và số điện thoại phải là số nguyên");
			System.out.println(e.toString());
			return false;
		}
		/* Check ngaySinh */
		if (checkFormatOfDate(nhanVienInformation.getTfNgaySinh().getText().toString().trim()) == false) {
			JOptionPane.showMessageDialog(new JDialog(), "Hã nhập đúng định dạng ngày sinh dd/mm/yyyy");
			return false;
		}
		
		/* Check if maNV is exist*/
		if (!checkID(nhanVienInformation.getTfIdNhanVien().getText().toString().trim())) {
			JOptionPane.showMessageDialog(new JDialog(), "Mã nhân viên đã tồn tại - Hãy nhập lại");
			return false;
		}
		
		return true;
	}
	
	private boolean checkID(String id) {
		ArrayList<NhanVien> listNV = nhanVienDB.getAllNhanVien();
		for (int i = 0; i < listNV.size(); i++) {
			String idNVFromDB = listNV.get(i).getIdNhanVien();
			if (id.equals(idNVFromDB) && (id.equals(oldID) == false)) return false;
		}
		return true;
	}
	
	private boolean checkFormatOfDate(String date) {
		return DATE_PATTERN.matcher(date).matches();
	}
	
	private void suaNV() {
		if (checkInfor(nhanVienInformation)) {
			String idNV = nhanVienInformation.getTfIdNhanVien().getText().toString();
			String tenNV = nhanVienInformation.getTfTenNhanVien().getText().toString();
			String soCMND = nhanVienInformation.getTfSoCMND().getText().toString();
			String ngaySinh = nhanVienInformation.getTfNgaySinh().getText().toString();
			String diaChi = nhanVienInformation.getTfDiaChi().getText().toString();
			String gioiTinh = nhanVienInformation.getTfGioiTinh().getText().toString();
			String sdt= nhanVienInformation.getTfSDT().getText().toString();
			
			nhanVienDB.updateNhanVien(this.nhanVien, idNV, tenNV, soCMND, ngaySinh, diaChi, gioiTinh, sdt);
			tableNhanVienView.updateTable(nhanVienDB.getAllNhanVien());
			this.editNhanVienView.setVisible(false);
		}
		else {
			System.out.println("Edit fail !!!");
		}
	}
}
