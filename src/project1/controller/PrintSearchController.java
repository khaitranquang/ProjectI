package project1.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import project1.model.KhachHang;
import project1.model.MuonXe;
import project1.model.NhanVien;
import project1.model.Xe;
import project1.view.MainUI;

public class PrintSearchController {
	private MainUI mainUI;
	
	private ArrayList<Xe> resultSearchXe;
	private ArrayList<KhachHang> resultSearchKH;
	private ArrayList<NhanVien> resultSearchNV;
	private ArrayList<MuonXe> resultSearchMT;
	
	private JTextField tfSearchXe;
	private JTextField tfSearchKH;
	private JTextField tfSearchNV;
	private JTextField tfSearchMT;
	
	private JComboBox<String> cbSearchXe;
	private JComboBox<String> cbSearchKH;
	private JComboBox<String> cbSearchNV;
	private JComboBox<String> cbSearchMT;
	
	public PrintSearchController(MainUI mainUI) {
		this.mainUI = mainUI;
		resultSearchXe = new SearchXeController(mainUI).getResultSearch();
		resultSearchKH = new SearchKHController(mainUI).getResultSearch();
		resultSearchNV = new SearchNVController(mainUI).getResultSearch();
		resultSearchMT = new SearchDetailController(mainUI).getResultSearch();
		
		cbSearchXe = mainUI.getQlXe().getBtnXe().getTimKiemCB();
		cbSearchKH = mainUI.getQlKH().getBtnKH().getTimKiemCB();
		cbSearchNV = mainUI.getQlNV().getBtnNV().getTimKiemCB();
		cbSearchMT = mainUI.getQlMT().getBtn().getCbTimKiem();
		
		tfSearchXe = mainUI.getQlXe().getBtnXe().getTfTimKiem();
		tfSearchKH = mainUI.getQlKH().getBtnKH().getTfTimKiem();
		tfSearchNV = mainUI.getQlNV().getBtnNV().getTfTimKiem();
		tfSearchMT = mainUI.getQlMT().getBtn().getTfTimKiem();
		
		JButton btnPrintXe = mainUI.getQlXe().getBtnXe().getBtnXuatFile();
		JButton btnPrintKH = mainUI.getQlKH().getBtnKH().getBtnXuatFile();
		JButton btnPrintNV = mainUI.getQlNV().getBtnNV().getBtnXuatFile();
		JButton btnPrintMT = mainUI.getQlMT().getBtn().getBtnXuatFile();
		
		btnPrintXe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String[][] data = convertDataXe(resultSearchXe);
				setAction(data, 1);
			}
		});
		
		btnPrintKH.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[][] data = convertDataKH(resultSearchKH);
				setAction(data, 2);
			}
		});
		
		btnPrintNV.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[][] data = convertDataNV(resultSearchNV);
				setAction(data, 3);
			}
		});
		
		btnPrintMT.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[][] data = convertDataMT(resultSearchMT);
				setAction(data, 4);
			}
		});
		
	}
	
	/* Convert Data */
	private String[][] convertDataXe(ArrayList<Xe> resultSearch) {
		String data[][] = new String[resultSearch.size()][10];
		
		for (int i = 0; i < resultSearch.size(); i++) {
			Xe xe = resultSearch.get(i);
			data[i][0] = xe.getIdXe();
			data[i][1] = xe.getBienXe();
			data[i][2] = xe.getTenXe();
			data[i][3] = xe.getLoaiXe();
			data[i][4] = xe.getHangSanXuat();
			data[i][5] = xe.getNamSanXuat();
			data[i][6] = xe.getNgayBaoTri();
			data[i][7] = xe.getNhienLieu();
			data[i][8] = xe.getTrangThai() + "";
			data[i][9] = xe.getGiaThue() + "";
		}
		return data;
	}
	private String[][] convertDataKH(ArrayList<KhachHang> resultSearch) {
		String data[][] = new String[resultSearch.size()][8];
		
		for (int i = 0; i < resultSearch.size(); i++) {
			KhachHang kh = resultSearch.get(i);
			data[i][0] = kh.getIdKhachHang();
			data[i][1] = kh.getTenKhachHang();
			data[i][2] = kh.getSoCMND();
			data[i][3] = kh.getNgaySinh();
			data[i][4] = kh.getDiaChi();
			data[i][5] = kh.getGioiTinh();
			data[i][6] = kh.getSDT();
			data[i][7] = kh.getEmail();
			
		}
		return data;
	}
	private String[][] convertDataNV(ArrayList<NhanVien> resultSearch) {
		String data[][] = new String[resultSearch.size()][7];
		
		for (int i = 0; i < resultSearch.size(); i++) {
			NhanVien nv = resultSearch.get(i);
			data[i][0] = nv.getIdNhanVien();
			data[i][1] = nv.getTenNhanVien();
			data[i][2] = nv.getSoCMND();
			data[i][3] = nv.getNgaySinh();
			data[i][4] = nv.getDiaChi();
			data[i][5] = nv.getGioiTinh();
			data[i][6] = nv.getSDT();
			
		}
		return data;
	}
	private String[][] convertDataMT(ArrayList<MuonXe> resultSearch) {
		String data[][] = new String[resultSearch.size()][6];
		
		for (int i = 0; i < resultSearch.size(); i++) {
			MuonXe mx = resultSearch.get(i);
			data[i][0] = mx.getMaMT();
			data[i][1] = mx.getMaKH();
			data[i][2] = mx.getMaNV();
			data[i][3] = mx.getNgayMuon();
			data[i][4] = mx.getNgayHenTra();
			data[i][5] = mx.getTienCoc() + "";
			
		}
		return data;
	}
	
	/* ---------- Set Action --------------*/
	private void setAction(String[][] data, int modeSave) {
		JFileChooser fileChooser = new JFileChooser();
		int select = fileChooser.showSaveDialog(this.mainUI);
		String saveFilePath = "";
		
		if (select == JFileChooser.APPROVE_OPTION) {
			String path = fileChooser.getCurrentDirectory().toString() 
			       	   + "\\" + fileChooser.getSelectedFile().getName();
			if(path.indexOf(".xlsx") >= 0) {
				saveFilePath = path;
			}
			else {
				saveFilePath = path + ".xlsx";
			}
			System.out.println("Save file to: " + saveFilePath);
			saveSearchExcelTo(saveFilePath, data, modeSave);
		}
	}
	
	private void saveSearchExcelTo(String path, String[][] data, int modeSave) {
		try {
			FileOutputStream fos = new FileOutputStream(new File(path));
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("PheuTimKiem");
			
			if (modeSave == 1) {
				sheet.setColumnWidth(0, 4000);
				sheet.setColumnWidth(1, 4000);
				sheet.setColumnWidth(2, 4000);
				sheet.setColumnWidth(3, 4000);
				sheet.setColumnWidth(4, 4000);
				sheet.setColumnWidth(5, 4000);
				sheet.setColumnWidth(6, 4000);
				sheet.setColumnWidth(7, 4000);
				sheet.setColumnWidth(8, 4000);
				sheet.setColumnWidth(9, 4000);
				
				Row row = sheet.createRow(0);
				Cell cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("TRƯỜNG ĐẠI HỌC BÁCH KHOA HÀ NỘI");
				cell.setCellStyle(createStyleDefault(workbook));
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
				cell = row.createCell(6, CellType.STRING);
				cell.setCellValue("Cộng hòa xã hội chủ nghĩa Việt Nam");
				cell.setCellStyle(createStyleDefault(workbook));
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 6, 9));
				
				row = sheet.createRow(1);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("Cửa hàng xe Bách Khoa");
				cell.setCellStyle(createStyleDefault(workbook));
				sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 3));
				cell = row.createCell(6, CellType.STRING);
				cell.setCellValue("Độc lập - Tự do - Hạnh phúc");
				cell.setCellStyle(createStyleDefault(workbook));
				sheet.addMergedRegion(new CellRangeAddress(1, 1, 6, 9));
				
				row = sheet.createRow(2);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("Nhóm 14");
				cell.setCellStyle(createStyleDefault(workbook));
				sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 3));
				
				printImage(workbook, sheet);
				
				LocalDate localDate   = LocalDate.now();
				String ngayHT         = Integer.toString(localDate.getDayOfMonth());
				String thangHT        = Integer.toString(localDate.getMonthValue());
				String namHT          = Integer.toString(localDate.getYear());
				String now            = "Ngày " + ngayHT + "-" + thangHT + "-" + namHT;
				
				row = sheet.createRow(3);  
				cell = row.createCell(4, CellType.STRING);
				cell.setCellValue(now);
				cell.setCellStyle(createStyleForDate(workbook));
				sheet.addMergedRegion(new CellRangeAddress(3, 3, 6, 9));
				
				row = sheet.createRow(7);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("TÌM KIẾM XE THEO " + cbSearchXe.getSelectedItem().toString().toUpperCase() +": " 
								   + tfSearchXe.getText().toString());
				cell.setCellStyle(createStyleForTitle(workbook));
				sheet.addMergedRegion(new CellRangeAddress(7, 7, 0, 9));
				
				row = sheet.createRow(9);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("Mã xe");
				cell.setCellStyle(createStyleForTableTitle(workbook));
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue("Biển xe");
				cell.setCellStyle(createStyleForTableTitle(workbook));
				cell = row.createCell(2, CellType.STRING);
				cell.setCellValue("Tên xe");
				cell.setCellStyle(createStyleForTableTitle(workbook));
				cell = row.createCell(3, CellType.STRING);
				cell.setCellValue("Loại xe");
				cell.setCellStyle(createStyleForTableTitle(workbook));
				cell = row.createCell(4, CellType.STRING);
				cell.setCellValue("Hãng sản xuất");
				cell.setCellStyle(createStyleForTableTitle(workbook));
				cell = row.createCell(5, CellType.STRING);
				cell.setCellValue("Năm sản xuất");
				cell.setCellStyle(createStyleForTableTitle(workbook));
				cell = row.createCell(6, CellType.STRING);
				cell.setCellValue("Ngày bảo trì");
				cell.setCellStyle(createStyleForTableTitle(workbook));
				cell = row.createCell(7, CellType.STRING);
				cell.setCellValue("Nhiên liệu");
				cell.setCellStyle(createStyleForTableTitle(workbook));
				cell = row.createCell(8, CellType.STRING);
				cell.setCellValue("Trạng Thái");
				cell.setCellStyle(createStyleForTableTitle(workbook));
				cell = row.createCell(9, CellType.STRING);
				cell.setCellValue("Giá thuê");
				cell.setCellStyle(createStyleForTableTitle(workbook));
			
				int rowNum = 10;
				for (int i = 0; i < data.length; i++) {
					row = sheet.createRow(rowNum);
					for (int j = 0; j < 10; j++) {
						cell = row.createCell(j, CellType.STRING);
						cell.setCellValue(data[i][j]);
						cell.setCellStyle(createStyleDataTable(workbook));
					}
					rowNum++;
				}
				
				row = sheet.createRow(rowNum + 2);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("Người lập");
				cell.setCellStyle(createStyleDefault(workbook));
				sheet.addMergedRegion(new CellRangeAddress(rowNum + 2, rowNum + 2, 0, 3));
				cell = row.createCell(6, CellType.STRING);
				cell.setCellValue("Chữ kí quản lí");
				cell.setCellStyle(createStyleDefault(workbook));
				sheet.addMergedRegion(new CellRangeAddress(rowNum + 2, rowNum + 2, 6, 9));
				
				row = sheet.createRow(rowNum + 3);
				cell = row.createCell(6, CellType.STRING);
				cell.setCellValue("Admin");
				cell.setCellStyle(createStyleDefault(workbook));
				sheet.addMergedRegion(new CellRangeAddress(rowNum + 3, rowNum + 3, 6, 9));
			}
			
			else if (modeSave == 2) {
				sheet.setColumnWidth(0, 4000);
				sheet.setColumnWidth(1, 4000);
				sheet.setColumnWidth(2, 4000);
				sheet.setColumnWidth(3, 4000);
				sheet.setColumnWidth(4, 4000);
				sheet.setColumnWidth(5, 4000);
				sheet.setColumnWidth(6, 4000);
				sheet.setColumnWidth(7, 4000);
				
				Row row = sheet.createRow(0);
				Cell cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("TRƯỜNG ĐẠI HỌC BÁCH KHOA HÀ NỘI");
				cell.setCellStyle(createStyleDefault(workbook));
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
				cell = row.createCell(5, CellType.STRING);
				cell.setCellValue("Cộng hòa xã hội chủ nghĩa Việt Nam");
				cell.setCellStyle(createStyleDefault(workbook));
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 5, 7));
				
				row = sheet.createRow(1);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("Cửa hàng xe Bách Khoa");
				cell.setCellStyle(createStyleDefault(workbook));
				sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 2));
				cell = row.createCell(5, CellType.STRING);
				cell.setCellValue("Độc lập - Tự do - Hạnh phúc");
				cell.setCellStyle(createStyleDefault(workbook));
				sheet.addMergedRegion(new CellRangeAddress(1, 1, 5, 7));
				
				row = sheet.createRow(2);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("Nhóm 14");
				cell.setCellStyle(createStyleDefault(workbook));
				sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 2));
				
				printImage(workbook, sheet);
				
				LocalDate localDate   = LocalDate.now();
				String ngayHT         = Integer.toString(localDate.getDayOfMonth());
				String thangHT        = Integer.toString(localDate.getMonthValue());
				String namHT          = Integer.toString(localDate.getYear());
				String now            = "Ngày " + ngayHT + "-" + thangHT + "-" + namHT;
				
				row = sheet.createRow(3);  
				cell = row.createCell(4, CellType.STRING);
				cell.setCellValue(now);
				cell.setCellStyle(createStyleForDate(workbook));
				sheet.addMergedRegion(new CellRangeAddress(3, 3, 5, 7));
				
				row = sheet.createRow(7);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("TÌM KIẾM KHÁCH HÀNG THEO " + cbSearchKH.getSelectedItem().toString().toUpperCase() +": " 
								   + tfSearchKH.getText().toString());
				cell.setCellStyle(createStyleForTitle(workbook));
				sheet.addMergedRegion(new CellRangeAddress(7, 7, 0, 7));
				
				row = sheet.createRow(9);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("Mã khách hàng");
				cell.setCellStyle(createStyleForTableTitle(workbook));
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue("Tên khách hàng");
				cell.setCellStyle(createStyleForTableTitle(workbook));
				cell = row.createCell(2, CellType.STRING);
				cell.setCellValue("Số CMND");
				cell.setCellStyle(createStyleForTableTitle(workbook));
				cell = row.createCell(3, CellType.STRING);
				cell.setCellValue("Ngày sinh");
				cell.setCellStyle(createStyleForTableTitle(workbook));
				cell = row.createCell(4, CellType.STRING);
				cell.setCellValue("Địa chỉ");
				cell.setCellStyle(createStyleForTableTitle(workbook));
				cell = row.createCell(5, CellType.STRING);
				cell.setCellValue("Giới tính");
				cell.setCellStyle(createStyleForTableTitle(workbook));
				cell = row.createCell(6, CellType.STRING);
				cell.setCellValue("Số ĐT");
				cell.setCellStyle(createStyleForTableTitle(workbook));
				cell = row.createCell(7, CellType.STRING);
				cell.setCellValue("Email");
				cell.setCellStyle(createStyleForTableTitle(workbook));
			
				int rowNum = 10;
				for (int i = 0; i < data.length; i++) {
					row = sheet.createRow(rowNum);
					for (int j = 0; j < 8; j++) {
						cell = row.createCell(j, CellType.STRING);
						cell.setCellValue(data[i][j]);
						cell.setCellStyle(createStyleDataTable(workbook));
					}
					rowNum++;
				}
				
				row = sheet.createRow(rowNum + 2);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("Người lập");
				cell.setCellStyle(createStyleDefault(workbook));
				sheet.addMergedRegion(new CellRangeAddress(rowNum + 2, rowNum + 2, 0, 2));
				cell = row.createCell(5, CellType.STRING);
				cell.setCellValue("Chữ kí quản lí");
				cell.setCellStyle(createStyleDefault(workbook));
				sheet.addMergedRegion(new CellRangeAddress(rowNum + 2, rowNum + 2, 5, 7));
				
				row = sheet.createRow(rowNum + 3);
				cell = row.createCell(5, CellType.STRING);
				cell.setCellValue("Admin");
				cell.setCellStyle(createStyleDefault(workbook));
				sheet.addMergedRegion(new CellRangeAddress(rowNum + 3, rowNum + 3, 5, 7));
			}
			
			else if (modeSave == 3) {
				sheet.setColumnWidth(0, 4000);
				sheet.setColumnWidth(1, 4000);
				sheet.setColumnWidth(2, 4000);
				sheet.setColumnWidth(3, 4000);
				sheet.setColumnWidth(4, 4000);
				sheet.setColumnWidth(5, 4000);
				sheet.setColumnWidth(6, 4000);
				
				Row row = sheet.createRow(0);
				Cell cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("TRƯỜNG ĐẠI HỌC BÁCH KHOA HÀ NỘI");
				cell.setCellStyle(createStyleDefault(workbook));
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
				cell = row.createCell(4, CellType.STRING);
				cell.setCellValue("Cộng hòa xã hội chủ nghĩa Việt Nam");
				cell.setCellStyle(createStyleDefault(workbook));
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 4, 6));
				
				row = sheet.createRow(1);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("Cửa hàng xe Bách Khoa");
				cell.setCellStyle(createStyleDefault(workbook));
				sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 2));
				cell = row.createCell(4, CellType.STRING);
				cell.setCellValue("Độc lập - Tự do - Hạnh phúc");
				cell.setCellStyle(createStyleDefault(workbook));
				sheet.addMergedRegion(new CellRangeAddress(1, 1, 4, 6));
				
				row = sheet.createRow(2);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("Nhóm 14");
				cell.setCellStyle(createStyleDefault(workbook));
				sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 2));
				
				printImage(workbook, sheet);
				
				LocalDate localDate   = LocalDate.now();
				String ngayHT         = Integer.toString(localDate.getDayOfMonth());
				String thangHT        = Integer.toString(localDate.getMonthValue());
				String namHT          = Integer.toString(localDate.getYear());
				String now            = "Ngày " + ngayHT + "-" + thangHT + "-" + namHT;
				
				row = sheet.createRow(3);  
				cell = row.createCell(4, CellType.STRING);
				cell.setCellValue(now);
				cell.setCellStyle(createStyleForDate(workbook));
				sheet.addMergedRegion(new CellRangeAddress(3, 3, 4, 6));
				
				row = sheet.createRow(7);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("TÌM KIẾM NHÂN VIÊN THEO " + cbSearchNV.getSelectedItem().toString().toUpperCase() +": " 
								   + tfSearchNV.getText().toString());
				cell.setCellStyle(createStyleForTitle(workbook));
				sheet.addMergedRegion(new CellRangeAddress(7, 7, 0, 6));
				
				row = sheet.createRow(9);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("Mã nhân viên");
				cell.setCellStyle(createStyleForTableTitle(workbook));
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue("Tên nhân viên");
				cell.setCellStyle(createStyleForTableTitle(workbook));
				cell = row.createCell(2, CellType.STRING);
				cell.setCellValue("Số CMND");
				cell.setCellStyle(createStyleForTableTitle(workbook));
				cell = row.createCell(3, CellType.STRING);
				cell.setCellValue("Ngày sinh");
				cell.setCellStyle(createStyleForTableTitle(workbook));
				cell = row.createCell(4, CellType.STRING);
				cell.setCellValue("Địa chỉ");
				cell.setCellStyle(createStyleForTableTitle(workbook));
				cell = row.createCell(5, CellType.STRING);
				cell.setCellValue("Giới tính");
				cell.setCellStyle(createStyleForTableTitle(workbook));
				cell = row.createCell(6, CellType.STRING);
				cell.setCellValue("Số ĐT");
				cell.setCellStyle(createStyleForTableTitle(workbook));

				int rowNum = 10;
				for (int i = 0; i < data.length; i++) {
					row = sheet.createRow(rowNum);
					for (int j = 0; j < 7; j++) {
						cell = row.createCell(j, CellType.STRING);
						cell.setCellValue(data[i][j]);
						cell.setCellStyle(createStyleDataTable(workbook));
					}
					rowNum++;
				}
				
				row = sheet.createRow(rowNum + 2);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("Người lập");
				cell.setCellStyle(createStyleDefault(workbook));
				sheet.addMergedRegion(new CellRangeAddress(rowNum + 2, rowNum + 2, 0, 2));
				cell = row.createCell(4, CellType.STRING);
				cell.setCellValue("Chữ kí quản lí");
				cell.setCellStyle(createStyleDefault(workbook));
				sheet.addMergedRegion(new CellRangeAddress(rowNum + 2, rowNum + 2, 4, 6));
				
				row = sheet.createRow(rowNum + 3);
				cell = row.createCell(4, CellType.STRING);
				cell.setCellValue("Admin");
				cell.setCellStyle(createStyleDefault(workbook));
				sheet.addMergedRegion(new CellRangeAddress(rowNum + 3, rowNum + 3, 4, 6));
			}
			
			else if (modeSave == 4) {
				sheet.setColumnWidth(0, 4000);
				sheet.setColumnWidth(1, 4000);
				sheet.setColumnWidth(2, 4000);
				sheet.setColumnWidth(3, 4000);
				sheet.setColumnWidth(4, 4000);
				sheet.setColumnWidth(5, 4000);
		
				Row row = sheet.createRow(0);
				Cell cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("TRƯỜNG ĐẠI HỌC BÁCH KHOA HÀ NỘI");
				cell.setCellStyle(createStyleDefault(workbook));
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 2));
				cell = row.createCell(3, CellType.STRING);
				cell.setCellValue("Cộng hòa xã hội chủ nghĩa Việt Nam");
				cell.setCellStyle(createStyleDefault(workbook));
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 3, 5));
				
				row = sheet.createRow(1);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("Cửa hàng xe Bách Khoa");
				cell.setCellStyle(createStyleDefault(workbook));
				sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 2));
				cell = row.createCell(3, CellType.STRING);
				cell.setCellValue("Độc lập - Tự do - Hạnh phúc");
				cell.setCellStyle(createStyleDefault(workbook));
				sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, 5));
				
				row = sheet.createRow(2);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("Nhóm 14");
				cell.setCellStyle(createStyleDefault(workbook));
				sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 2));
				
				printImage(workbook, sheet);
				
				LocalDate localDate   = LocalDate.now();
				String ngayHT         = Integer.toString(localDate.getDayOfMonth());
				String thangHT        = Integer.toString(localDate.getMonthValue());
				String namHT          = Integer.toString(localDate.getYear());
				String now            = "Ngày " + ngayHT + "-" + thangHT + "-" + namHT;
				
				row = sheet.createRow(3);  
				cell = row.createCell(4, CellType.STRING);
				cell.setCellValue(now);
				cell.setCellStyle(createStyleForDate(workbook));
				sheet.addMergedRegion(new CellRangeAddress(3, 3, 3, 5));
				
				row = sheet.createRow(7);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("TÌM KIẾM THUÊ XE THEO " + cbSearchMT.getSelectedItem().toString().toUpperCase() +": " 
								   + tfSearchMT.getText().toString());
				cell.setCellStyle(createStyleForTitle(workbook));
				sheet.addMergedRegion(new CellRangeAddress(7, 7, 0, 5));
				
				row = sheet.createRow(9);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("Mã mượn trả");
				cell.setCellStyle(createStyleForTableTitle(workbook));
				cell = row.createCell(1, CellType.STRING);
				cell.setCellValue("Mã khách hàng");
				cell.setCellStyle(createStyleForTableTitle(workbook));
				cell = row.createCell(2, CellType.STRING);
				cell.setCellValue("Mã nhân viên");
				cell.setCellStyle(createStyleForTableTitle(workbook));
				cell = row.createCell(3, CellType.STRING);
				cell.setCellValue("Ngày mượn");
				cell.setCellStyle(createStyleForTableTitle(workbook));
				cell = row.createCell(4, CellType.STRING);
				cell.setCellValue("Ngày hẹn trả");
				cell.setCellStyle(createStyleForTableTitle(workbook));
				cell = row.createCell(5, CellType.STRING);
				cell.setCellValue("Tiền cọc");
				cell.setCellStyle(createStyleForTableTitle(workbook));
				
			
				int rowNum = 10;
				for (int i = 0; i < data.length; i++) {
					row = sheet.createRow(rowNum);
					for (int j = 0; j < 6; j++) {
						cell = row.createCell(j, CellType.STRING);
						cell.setCellValue(data[i][j]);
						cell.setCellStyle(createStyleDataTable(workbook));
					}
					rowNum++;
				}
				
				row = sheet.createRow(rowNum + 2);
				cell = row.createCell(0, CellType.STRING);
				cell.setCellValue("Người lập");
				cell.setCellStyle(createStyleDefault(workbook));
				sheet.addMergedRegion(new CellRangeAddress(rowNum + 2, rowNum + 2, 0, 2));
				cell = row.createCell(3, CellType.STRING);
				cell.setCellValue("Chữ kí quản lí");
				cell.setCellStyle(createStyleDefault(workbook));
				sheet.addMergedRegion(new CellRangeAddress(rowNum + 2, rowNum + 2, 3, 5));
				
				row = sheet.createRow(rowNum + 3);
				cell = row.createCell(3, CellType.STRING);
				cell.setCellValue("Admin");
				cell.setCellStyle(createStyleDefault(workbook));
				sheet.addMergedRegion(new CellRangeAddress(rowNum + 3, rowNum + 3, 3, 5));
			}

			workbook.write(fos);
			System.out.println("Create file: " + path);
			fos.close();
		}
		catch (IOException e) {
			JOptionPane.showMessageDialog(new JDialog(), "Lỗi File");
			e.printStackTrace();
		}
	}
	
	/* Style for date in excel file */
	private XSSFCellStyle createStyleForDate (XSSFWorkbook workbook) {
		XSSFCellStyle styleDate = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setItalic(true);
		styleDate.setFont(font);
		styleDate.setAlignment(HorizontalAlignment.CENTER);
		return styleDate;
	}
	
	/* Style for title */
	private XSSFCellStyle createStyleForTitle (XSSFWorkbook workbook) {
		XSSFCellStyle styleTitle = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setColor(IndexedColors.BLUE.index);
		font.setFontHeight(18);
		styleTitle.setFont(font);
		styleTitle.setAlignment(HorizontalAlignment.CENTER);
		return styleTitle;
	}
	
	/* Style for table title */
	private XSSFCellStyle createStyleForTableTitle (XSSFWorkbook workbook) {
		XSSFCellStyle styleTableTitle = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(12);
		styleTableTitle.setFont(font);
		styleTableTitle.setAlignment(HorizontalAlignment.CENTER);
		styleTableTitle.setAlignment(HorizontalAlignment.CENTER);
		styleTableTitle.setBorderBottom(BorderStyle.MEDIUM);
		styleTableTitle.setBorderTop(BorderStyle.MEDIUM);
		styleTableTitle.setBorderRight(BorderStyle.MEDIUM);
		styleTableTitle.setBorderLeft(BorderStyle.MEDIUM);
		return styleTableTitle;
	}
	
	/* Default style */
	private XSSFCellStyle createStyleDefault(XSSFWorkbook workbook) {
		XSSFCellStyle styleDefault = workbook.createCellStyle();
		styleDefault.setAlignment(HorizontalAlignment.CENTER);
		return styleDefault;
	}
	
	/* Style for cell in table */
	private XSSFCellStyle createStyleDataTable(XSSFWorkbook workbook) {
		XSSFCellStyle styleData = workbook.createCellStyle();
		styleData.setAlignment(HorizontalAlignment.CENTER);
		styleData.setBorderBottom(BorderStyle.MEDIUM);
		styleData.setBorderTop(BorderStyle.MEDIUM);
		styleData.setBorderRight(BorderStyle.MEDIUM);
		styleData.setBorderLeft(BorderStyle.MEDIUM);
		return styleData;
	}
	
	/*Print image */
	private void printImage (Workbook wb, Sheet sheet) {
		 try {
			 Path imagePath = Paths.get(ClassLoader.getSystemResource("bachkhoa.png").toURI()); 
			 //FileInputStream obtains input bytes from the image file
			 InputStream inputStream = Files.newInputStream(imagePath);
			 //Get the contents of an InputStream as a byte[].
			 byte[] bytes = IOUtils.toByteArray(inputStream);
			 //Adds a picture to the workbook
			 int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			 //close the input stream
			 inputStream.close();
			 //Returns an object that handles instantiating concrete classes
			 CreationHelper helper = wb.getCreationHelper();
			 //Creates the top-level drawing patriarch.
			 Drawing drawing = sheet.createDrawingPatriarch();
			 //Create an anchor that is attached to the worksheet
			 ClientAnchor anchor = helper.createClientAnchor();
			 //set top-left corner for the image
			 anchor.setCol1(1);
			 anchor.setRow1(3);
			 //Creates a picture
			 Picture pict = drawing.createPicture(anchor, pictureIdx);
			 //Reset the image to the original size
			 pict.resize();	 
		 }
		 catch (Exception e) {
			 System.out.println(e);
		 }
	}
}
