package project1.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
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

import project1.model.KhachHang;
import project1.model.KhachHangDB;
import project1.model.NhanVien;
import project1.view.ButtonKhachHangView;
import project1.view.InputKhachHangView;
import project1.view.MainUI;
import project1.view.PanelQuanLyKhachHangView;
import project1.view.TableKhachHangView;

public class AddKhachHangController {
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
	private KhachHangDB khachHangDB;
	
	private PanelQuanLyKhachHangView panelQuanLyKhachHangView;
	private ButtonKhachHangView buttonKhachHangView;
	private InputKhachHangView inputKhachHangView;
	private TableKhachHangView tableKhachHangView;
	
	public AddKhachHangController(MainUI mainUI) {
		this.mainUI = mainUI;
		khachHangDB = new KhachHangDB();
		
		panelQuanLyKhachHangView = mainUI.getQlKH();
		buttonKhachHangView      = panelQuanLyKhachHangView.getBtnKH();
		inputKhachHangView       = panelQuanLyKhachHangView.getInputKH();
		tableKhachHangView       = panelQuanLyKhachHangView.getTableKH();
		tableKhachHangView.updateTable(khachHangDB.getAllKhachHang());
		
		setActions();
	}
	
	/*
	 * Check information of input
	 */
	private boolean checkInfor(InputKhachHangView inputKhachHangView) {
		// Are text fields empty?
		if (inputKhachHangView.getTfIdKhachHang().getText().toString().trim().equals("") ||
			inputKhachHangView.getTfTenKhachHang().getText().toString().trim().equals("") ||
			inputKhachHangView.getTfNgaySinh().getText().toString().trim().equals("") ||
			inputKhachHangView.getTfSoCMND().getText().toString().trim().equals("") ||
			inputKhachHangView.getTfDiaChi().getText().toString().trim().equals("") ||
			inputKhachHangView.getTfSoDT().getText().toString().trim().equals("") ||
			inputKhachHangView.getTfGioiTinh().getText().toString().trim().equals("") ||
			inputKhachHangView.getTfEmail().getText().toString().trim().equals("")) {
				System.out.println("Text Fields are not empty !!!");
				JOptionPane.showMessageDialog(new JDialog(), "Các trường dữ liệu không được để trống");
				return false;
		}
		
		// Check number?
		try {
			int soCMND = Integer.parseInt(inputKhachHangView.getTfSoCMND().getText().toString());
			int   sdt  = Integer.parseInt(inputKhachHangView.getTfSoDT().getText().toString());
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
		if (checkFormatOfDate(inputKhachHangView.getTfNgaySinh().getText().toString().trim()) == false) {
			JOptionPane.showMessageDialog(new JDialog(), "Hãy nhập đúng định dạng ngày sinh dd/mm/yyyy");
			return false;
		}
		
		/* Check format of email */
		if (validateEmail(inputKhachHangView.getTfEmail().getText().trim().toString())  == false) {
			JOptionPane.showMessageDialog(new JDialog(), "Hãy nhập đúng định dạng email");
			return false;
		}
		
		
		/* Check if maNV is exist*/
		if (!checkID(inputKhachHangView.getTfIdKhachHang().getText().toString().trim())) {
			JOptionPane.showMessageDialog(new JDialog(), "Mã nhân viên đã tồn tại - Hãy nhập lại");
			return false;
		}
		
		
		return true;
	}
	
	private boolean checkID(String id) {
		ArrayList<KhachHang> listKH = khachHangDB.getAllKhachHang();
		for (int i = 0; i < listKH.size(); i++) {
			String idKHFromDB = listKH.get(i).getIdKhachHang();
			if (id.equals(idKHFromDB)) return false;
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
	
	/*
	 * Set action for button
	 */
	private void setActions() {
		JButton btnAdd            = buttonKhachHangView.getBtnThem();
		JButton btnCancel         = buttonKhachHangView.getBtnHuy();
		JButton btnInputFromExcel = buttonKhachHangView.getBtnNhapFile();
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				themKH();
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
	
	private void themKH() {
		if(checkInfor(inputKhachHangView)) {
			String idKH     = inputKhachHangView.getTfIdKhachHang().getText().toString();
			String tenKH    = inputKhachHangView.getTfTenKhachHang().getText().toString();
			String soCMND   = inputKhachHangView.getTfSoCMND().getText().toString();
			String ngaySinh = inputKhachHangView.getTfNgaySinh().getText().toString();
			String diaChi   = inputKhachHangView.getTfDiaChi().getText().toString();
			String gioiTinh = inputKhachHangView.getTfGioiTinh().getText().toString();
			String sdt      = inputKhachHangView.getTfSoDT().getText().toString();
			String email    = inputKhachHangView.getTfEmail().getText().toString();
			
			khachHang = new KhachHang(idKH, tenKH, soCMND, ngaySinh, diaChi, gioiTinh, sdt, email);
			khachHangDB.insertKhachHang(khachHang);
			
			tableKhachHangView.updateTable(khachHangDB.getAllKhachHang());
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
		inputKhachHangView.getTfIdKhachHang().setText("");
		inputKhachHangView.getTfTenKhachHang().setText("");
		inputKhachHangView.getTfGioiTinh().setText("");
		inputKhachHangView.getTfDiaChi().setText("");
		inputKhachHangView.getTfSoDT().setText("");
		inputKhachHangView.getTfNgaySinh().setText("");
		inputKhachHangView.getTfSoCMND().setText("");
		inputKhachHangView.getTfEmail().setText("");
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
		}
		System.out.println(openFilePath);
		addKHFromExcelFile(openFilePath);
	}
	
	/* Add data from excel */
	private void addKHFromExcelFile(String path) {
		try {
			FileInputStream fis = new FileInputStream(new File(path));
			// Create Workbook Object
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			// Get the first sheet from workbook
			XSSFSheet sheet = workbook.getSheetAt(1);
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
					String idKH      = dataOfRow.get(0);
					String tenKH     = dataOfRow.get(1);
					double soCMND    = Double.parseDouble(dataOfRow.get(2));
					int soCMNDInt    = (int) soCMND;
					String soCMNDStr = Integer.toString(soCMNDInt);
					String ngaySinh  = dataOfRow.get(3);
					String diaChi    = dataOfRow.get(4);
					String gioiTinh  = dataOfRow.get(5);
					String sdt       = dataOfRow.get(6);
					String email     = dataOfRow.get(7);
					
					KhachHang kh = new KhachHang(idKH, tenKH, soCMNDStr, ngaySinh, diaChi, gioiTinh, sdt, email);
					khachHangDB.insertKhachHang(kh);
					tableKhachHangView.updateTable(khachHangDB.getAllKhachHang());
	
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
		
		/* Check format of email */
		if (validateEmail(dataOfRow.get(7)) == false) {
			JOptionPane.showMessageDialog(new JDialog(), "Hãy kiểm tra định dạng email");
			return false;
		}
		
		/* Check if maSach is exist*/
		if (!checkID(dataOfRow.get(0))) {
			JOptionPane.showMessageDialog(new JDialog(), "Mã khách hàng đã tồn tại - Hãy nhập lại");
			return false;
		}
		
		return true;
	}
	
	
	
	
	
	
	
	
}
