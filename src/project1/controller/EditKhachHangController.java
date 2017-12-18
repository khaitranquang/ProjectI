package project1.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import project1.model.KhachHang;
import project1.model.KhachHangDAO;
import project1.model.KhachHangDB;
import project1.view.EditKhachHangView;
import project1.view.KhachHangInformation;
import project1.view.MainUI;
import project1.view.TableKhachHangView;

public class EditKhachHangController {
	private static final Pattern DATE_PATTERN = Pattern.compile(
			"^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|"
			+ "-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|"
			+ "^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468]"
			+ "[048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|"
			+ "^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4("
			+ "?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
	
	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile(
			"^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	private MainUI mainUI;
	private KhachHang khachHang;
	private KhachHangDAO khachHangDB;
	private String oldID = "" ;
	
	private EditKhachHangView editKhachHangView;
	private JButton btnEdit;
	private KhachHangInformation khachHangInformation;
	private TableKhachHangView tableKhachHangView;
	
	public EditKhachHangController(MainUI mainUI) {
		this.mainUI = mainUI;
		khachHangDB = new KhachHangDB();
		btnEdit = mainUI.getQlKH().getBtnKH().getBtnSua();
		tableKhachHangView = mainUI.getQlKH().getTableKH();
		tableKhachHangView.updateTable(khachHangDB.getAllKhachHang());
		btnEditEvent();
	}
	
	private void btnEditEvent() {
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				editKhachHangView = new EditKhachHangView(mainUI);
				khachHangInformation = editKhachHangView.getKhachHangInformation();
				
				int index = findIndexOfData();
				if (index >= 0) {
					editKhachHangView.setVisible(true);
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
		int index = tableKhachHangView.getTable().getSelectedRow();
		return index;
	}
	
	private String getID(int indexRow, int indexCol) {
		JTable table = tableKhachHangView.getTable();
		String id = table.getModel().getValueAt(indexRow, indexCol).toString();
		return id;
	}
	
	private void loadInfor(String id) {
		khachHang = khachHangDB.getKhachHang(id);
		khachHangInformation.getTfIdKH().setText(id);
		khachHangInformation.getTfTenKH().setText(khachHang.getTenKhachHang());
		khachHangInformation.getTfSoCMND().setText(khachHang.getSoCMND());
		khachHangInformation.getTfNgaySinh().setText(khachHang.getNgaySinh());
		khachHangInformation.getTfDiaChi().setText(khachHang.getDiaChi());
		if(khachHang.getGioiTinh().equals("Nam")) khachHangInformation.getRadNam().setSelected(true);
		else khachHangInformation.getRadNu().setSelected(true);
		khachHangInformation.getTfSDT().setText(khachHang.getSDT());
		khachHangInformation.getTfEmail().setText(khachHang.getEmail());
	}
	
	private void setActions() {
		JButton btnEdit = editKhachHangView.getBtnEdit();
		JButton btnCancel = editKhachHangView.getBtnCancel();
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				suaKH();
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
		this.editKhachHangView.dispose();
	}
	
	/*
	 * Check information of input
	 */
	private boolean checkInfor (KhachHangInformation khachHangInformation) {
		// Are text fields empty?
		if (khachHangInformation.getTfIdKH().getText().toString().trim().equals("") ||
			khachHangInformation.getTfTenKH().getText().toString().trim().equals("") ||
			khachHangInformation.getTfNgaySinh().getText().toString().trim().equals("") ||
			khachHangInformation.getTfSoCMND().getText().toString().trim().equals("") ||
			khachHangInformation.getTfDiaChi().getText().toString().trim().equals("") ||
			khachHangInformation.getTfSDT().getText().toString().trim().equals("") ||
			(!khachHangInformation.getRadNam().isSelected()&&!khachHangInformation.getRadNu().isSelected())||
			khachHangInformation.getTfEmail().getText().toString().trim().equals("")) {
			System.out.println("Text Fields are not empty !!!");
			JOptionPane.showMessageDialog(new JDialog(), "Các trường dữ liệu không được để trống");
			return false;
		}
		
		// Check number?
		try {
			int soCMND = Integer.parseInt(khachHangInformation.getTfSoCMND().getText().toString());
			int   sdt  = Integer.parseInt(khachHangInformation.getTfSDT().getText().toString());
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
		if (checkFormatOfDate(khachHangInformation.getTfNgaySinh().getText().toString().trim()) == false) {
			JOptionPane.showMessageDialog(new JDialog(), "Hã nhập đúng định dạng ngày sinh dd/mm/yyyy");
			return false;
		}
		
		/* Check email */
		if (validateEmail(khachHangInformation.getTfEmail().getText().toString().trim()) == false) {
			JOptionPane.showMessageDialog(new JDialog(), "Hãy kiểm tra định dạng email");
			return false;
		}
		
		/* Check if maNV is exist*/
		if (!checkID(khachHangInformation.getTfIdKH().getText().toString().trim())) {
			JOptionPane.showMessageDialog(new JDialog(), "Mã nhân viên đã tồn tại - Hãy nhập lại");
			return false;
		}
		
		return true;
	}
	
	private boolean checkID(String id) {
		ArrayList<KhachHang> listKH = khachHangDB.getAllKhachHang();
		for (int i = 0; i < listKH.size(); i++) {
			String idKHFromDB = listKH.get(i).getIdKhachHang();
			if (id.equals(idKHFromDB) && (id.equals(oldID) == false)) return false;
		}
		return true;
	}
	
	private boolean checkFormatOfDate(String date) {
		return DATE_PATTERN.matcher(date).matches();
	}
	
	public boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
	}
	
	private void suaKH() {
		if (checkInfor(khachHangInformation)) {
			String idKH = khachHangInformation.getTfIdKH().getText().toString();
			String tenKH = khachHangInformation.getTfTenKH().getText().toString();
			String soCMND = khachHangInformation.getTfSoCMND().getText().toString();
			String ngaySinh = khachHangInformation.getTfNgaySinh().getText().toString();
			String diaChi = khachHangInformation.getTfDiaChi().getText().toString();
			String gioiTinh = khachHangInformation.getRadNam().isSelected()?"Nam":"Nữ";
			String sdt= khachHangInformation.getTfSDT().getText().toString();
			String email = khachHangInformation.getTfEmail().getText().toString();
			
			khachHangDB.updateKhachHang(this.khachHang, idKH, tenKH, soCMND, ngaySinh, diaChi, gioiTinh, sdt, email);
			tableKhachHangView.updateTable(khachHangDB.getAllKhachHang());
			this.editKhachHangView.dispose();
		}
		else {
			System.out.println("Edit fail !!!");
		}
	}
}
