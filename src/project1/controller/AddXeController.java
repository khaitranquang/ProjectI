package project1.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
		JButton btnThem        = buttonXeView.getBtnThem();
		JButton btnHuy         = buttonXeView.getBtnHuy();
		JButton btnInFromExcel = buttonXeView.getBtnNhapFile();
		
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
		
		btnInFromExcel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				addFromExcel();
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
	
	/* Add Xe From Excel */
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
		addXeFromExcelFile(openFilePath);
	}
	
	private void addXeFromExcelFile(String path) {
		try {
			FileInputStream fis = new FileInputStream(new File(path));
			// Create Workbook Object
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			// Get the first sheet from workbook
			XSSFSheet sheet = workbook.getSheetAt(0);
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
					String idXe        = dataOfRow.get(0);
					String bienXe      = dataOfRow.get(1);
					String tenXe       = dataOfRow.get(2);
					String loaiXe      = dataOfRow.get(3);
					String hangSanXuat = dataOfRow.get(4);
					double namSanXuat  = Double.parseDouble(dataOfRow.get(5));
					int    namSXInt    = (int) namSanXuat;
					String namSXStr    = Integer.toString(namSXInt);
					String ngayBaoTri  = dataOfRow.get(6);
					String nhienLieu   = dataOfRow.get(7);
					double ttDouble    = Double.parseDouble(dataOfRow.get(8));
					int trangThai      = (int) ttDouble;
					double gtDouble    = Double.parseDouble(dataOfRow.get(9));
					int giaThue        = (int) gtDouble;
					
					Xe xe = new Xe(idXe, bienXe, tenXe, loaiXe, hangSanXuat, namSXStr, ngayBaoTri, nhienLieu, trangThai, giaThue);
					xeDB.insertXe(xe);
					tableXeView.updateTable(xeDB.getAllXe());
	
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
		
		// Check integer?
		try {
			double namSX = Double.parseDouble(dataOfRow.get(5));
			int namSXInt = (int) namSX;
			if (namSXInt != namSX) return false;
			double trangThai = Double.parseDouble(dataOfRow.get(8));
			int trangThaiInt = (int) trangThai;
			if (trangThaiInt != trangThai) return false;
			double giaThue = Double.parseDouble(dataOfRow.get(9));
			int giaThueInt = (int) giaThue;
			if (giaThueInt != giaThue) return false;
			
			// Test < 0 ????
			if(namSXInt < 0 || trangThaiInt <0 || giaThueInt < 0 || namSXInt > 9999 || namSXInt < 1000 || trangThaiInt > 1) {
				JOptionPane.showMessageDialog(this.mainUI, "Nhập lại đúng định dạng các trường số !!!");
				return false;
			}	
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this.mainUI, "Năm sản xuất, trạng thái, giá thuê phải là số");
			System.out.println(e.toString());
			return false;
		}
		
		/* Check if maSach is exist*/
		if (!checkID(dataOfRow.get(0))) {
			JOptionPane.showMessageDialog(new JDialog(), "Mã sách đã tồn tại - Hãy nhập lại");
			return false;
		}
		
		return true;
	}
}
