package project1.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import project1.model.NhanVien;
import project1.model.NhanVienDB;
import project1.model.Xe;
import project1.view.ButtonNhanVienView;
import project1.view.InputNhanVienView;
import project1.view.MainUI;
import project1.view.PanelQuanLyNhanVienView;
import project1.view.TableNhanVienView;

public class AddNhanVienController {
	private static final Pattern DATE_PATTERN = Pattern.compile(
			"^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|"
			+ "-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|"
			+ "^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468]"
			+ "[048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|"
			+ "^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4("
			+ "?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");
	
	
	private MainUI mainUI;
	private NhanVien nhanVien;
	private NhanVienDB nhanVienDB;
	
	private PanelQuanLyNhanVienView panelQuanLyNhanVienView;
	private ButtonNhanVienView buttonNhanVienView;
	private InputNhanVienView inputNhanVienView;
	private TableNhanVienView tableNhanVienView;
	
	public AddNhanVienController (MainUI mainUI) {
		this.mainUI = mainUI;
		nhanVienDB = new NhanVienDB();
		
		panelQuanLyNhanVienView = mainUI.getQlNV();
		buttonNhanVienView      = panelQuanLyNhanVienView.getBtnNV();
		inputNhanVienView       = panelQuanLyNhanVienView.getInputNV();
		tableNhanVienView       = panelQuanLyNhanVienView.getTableNV();
		tableNhanVienView.updateTable(nhanVienDB.getAllNhanVien());
		
		setActions();
	}
	
	/*
	 * Check information of input
	 */
	private boolean checkInfor (InputNhanVienView inputNhanVienView) {
		// Are text fields empty?
		if (inputNhanVienView.getTfIdNhanVien().getText().toString().trim().equals("") ||
			inputNhanVienView.getTfTenNhanVien().getText().toString().trim().equals("") ||
			inputNhanVienView.getTfNgaySinh().getText().toString().trim().equals("") ||
			inputNhanVienView.getTfSoCMND().getText().toString().trim().equals("") ||
			inputNhanVienView.getTfDiaChi().getText().toString().trim().equals("") ||
			inputNhanVienView.getTfSoDT().getText().toString().trim().equals("") ||
			inputNhanVienView.getTfGioiTinh().getText().toString().trim().equals("")) {
			System.out.println("Text Fields are not empty !!!");
			JOptionPane.showMessageDialog(new JDialog(), "Các trường dữ liệu không được để trống");
			return false;
		}
		
		// Check number?
		try {
			int soCMND = Integer.parseInt(inputNhanVienView.getTfSoCMND().getText().toString());
			int   sdt  = Integer.parseInt(inputNhanVienView.getTfSoDT().getText().toString());
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
		if (checkFormatOfDate(inputNhanVienView.getTfNgaySinh().getText().toString().trim()) == false) {
			JOptionPane.showMessageDialog(new JDialog(), "Hã nhập đúng định dạng ngày sinh dd/mm/yyyy");
			return false;
		}
		
		/* Check if maNV is exist*/
		if (!checkID(inputNhanVienView.getTfIdNhanVien().getText().toString().trim())) {
			JOptionPane.showMessageDialog(new JDialog(), "Mã nhân viên đã tồn tại - Hãy nhập lại");
			return false;
		}
		
		return true;
	}
	
	private boolean checkID(String id) {
		ArrayList<NhanVien> listNV = nhanVienDB.getAllNhanVien();
		for (int i = 0; i < listNV.size(); i++) {
			String idNVFromDB = listNV.get(i).getIdNhanVien();
			if (id.equals(idNVFromDB)) return false;
		}
		return true;
	}
	
	private boolean checkFormatOfDate(String date) {
		return DATE_PATTERN.matcher(date).matches();
	}
	
	/*
	 * Set action for button
	 */
	private void setActions() {
		JButton btnAdd            = buttonNhanVienView.getBtnThem();
		JButton btnCancel         = buttonNhanVienView.getBtnHuy();
		JButton btnInputFromExcel = buttonNhanVienView.getBtnNhapFile();
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				themNV();
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				huy();
			}
		});
		
		btnInputFromExcel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addFromExcel();
			}
		});
	}
	
	private void themNV() {
		if(checkInfor(inputNhanVienView)) {
			String idNV     = inputNhanVienView.getTfIdNhanVien().getText().toString();
			String tenNV    = inputNhanVienView.getTfTenNhanVien().getText().toString();
			String soCMND   = inputNhanVienView.getTfSoCMND().getText().toString();
			String ngaySinh = inputNhanVienView.getTfNgaySinh().getText().toString();
			String diaChi   = inputNhanVienView.getTfDiaChi().getText().toString();
			String gioiTinh = inputNhanVienView.getTfGioiTinh().getText().toString();
			String sdt      = inputNhanVienView.getTfSoDT().getText().toString();
			
			nhanVien = new NhanVien(idNV, tenNV, soCMND, ngaySinh, diaChi, gioiTinh, sdt);
			nhanVienDB.insertNhanVien(nhanVien);
			
			tableNhanVienView.updateTable(nhanVienDB.getAllNhanVien());
			clearInput();
		}
		else {
			System.out.println("Insert this employee false!!!");
		}
	}
	
	private void huy() {
		clearInput();
	}
	
	private void clearInput() {
		inputNhanVienView.getTfIdNhanVien().setText("");
		inputNhanVienView.getTfTenNhanVien().setText("");
		inputNhanVienView.getTfGioiTinh().setText("");
		inputNhanVienView.getTfDiaChi().setText("");
		inputNhanVienView.getTfSoDT().setText("");
		inputNhanVienView.getTfNgaySinh().setText("");
		inputNhanVienView.getTfIdNhanVien().setText("");
		inputNhanVienView.getTfSoCMND().setText("");

	}
	
	private void addFromExcel() {
		JFileChooser fileChooser = new JFileChooser();
		int select = fileChooser.showOpenDialog(this.mainUI);
		String openFilePath = "";
		
		if (select == JFileChooser.APPROVE_OPTION) {
			String path = fileChooser.getCurrentDirectory().toString() 
			       	   + "\\" + fileChooser.getSelectedFile().getName();
			if(path.indexOf(".xlsx") >= 0) {
				openFilePath = path;
			}
			else {
				openFilePath = path + ".xlsx";
			}
			System.out.println(openFilePath);
			addNVFromExcelFile(openFilePath);
		}
		
	}
	
	/* Add data from excel */
	private void addNVFromExcelFile(String path) {
		try {
			FileInputStream fis = new FileInputStream(new File(path));
			// Create Workbook Object
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			// Get the first sheet from workbook
			XSSFSheet sheet = workbook.getSheetAt(2);
			// Get all row of the current sheet
			Iterator<Row> rowIterator = sheet.iterator();
			List<Row> rowList = IteratorUtils.toList(rowIterator);
			
			for (int i = 2; i < rowList.size(); i++) {
				XSSFRow row = (XSSFRow) rowList.get(i);
				Iterator<Cell> cellIterator = row.cellIterator();
				List<Cell> cellList = IteratorUtils.toList(cellIterator);
				
				// ArrayList save data of current row
				ArrayList<String> dataOfRow = new ArrayList<String>();
				for (int j = 0; j < cellList.size(); j++) {
					Cell cell = cellList.get(j);
					CellType cellType = cell.getCellTypeEnum();
					String data = "";
					switch(cellType) {
						case STRING:
							data = cell.getStringCellValue();
							break;
						case NUMERIC:
							data = Double.toString(cell.getNumericCellValue());
							break;
						default:
							data = "";
							break;
					}
					dataOfRow.add(data);
				}
				
				if (checkInfor(dataOfRow)) {
					String idNV      = dataOfRow.get(0);
					String tenNV     = dataOfRow.get(1);
					double soCMND    = Double.parseDouble(dataOfRow.get(2));
					int soCMNDInt    = (int) soCMND;
					String soCMNDStr = Integer.toString(soCMNDInt);
					String ngaySinh  = dataOfRow.get(3);
					String diaChi    = dataOfRow.get(4);
					String gioiTinh  = dataOfRow.get(5);
					String sdt       = dataOfRow.get(6);
					
					NhanVien nv = new NhanVien(idNV, tenNV, soCMNDStr, ngaySinh, diaChi, gioiTinh, sdt);
					nhanVienDB.insertNhanVien(nv);
					tableNhanVienView.updateTable(nhanVienDB.getAllNhanVien());
	
					dataOfRow.clear();
				}
			
				else return;
			}
		}
		
		catch (IOException e) {
			JOptionPane.showMessageDialog(new JDialog(), "Lỗi định dạng File");
			e.printStackTrace();
		}	
	}
	
	private boolean checkInfor(ArrayList<String> dataOfRow) {
		// Check if empty
		for (int i = 0; i < dataOfRow.size(); i++) {
			if (dataOfRow.get(i).equals("")) return false;
		}
		
		try {
			double soCMND = Double.parseDouble(dataOfRow.get(2));
			int soCMNDInt = (int) soCMND;
			if (soCMNDInt != soCMND) return false;
			
			String sdt = dataOfRow.get(6);
			int sdtInt = Integer.parseInt(sdt);
			
			// Test < 0 ????
			if(soCMNDInt < 0 || sdtInt <0) {
				JOptionPane.showMessageDialog(this.mainUI, "Nhập lại đúng định dạng các trường số !!!");
				return false;
			}	
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this.mainUI, "Số CMND, số điện thoại phải là số");
			System.out.println(e.toString());
			return false;
		}
		
		/* Check date */
		if (checkFormatOfDate(dataOfRow.get(3)) == false) {
			JOptionPane.showMessageDialog(new JDialog(), "Hãy kiểm tra định dạng ngày sinh dd/mm/yyyy");
			return false;
		}
		
		/* Check if maSach is exist*/
		if (!checkID(dataOfRow.get(0))) {
			JOptionPane.showMessageDialog(new JDialog(), "Mã nhân viên đã tồn tại - Hãy nhập lại");
			return false;
		}
		
		return true;
	}
	
	
}
