package project1.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import project1.model.ChiTiet;
import project1.model.ChiTietDB;
import project1.model.MuonXe;
import project1.model.MuonXeDB;
import project1.view.MainUI;
import project1.view.TKRentInformation;
import project1.view.TKRentView;

public class TKRentController {
	private MainUI mainUI;
	private MuonXeDB muonXeDB;
	private ChiTietDB chiTietDB;
	
	private TKRentView tkRentView;
	private TKRentInformation tkRentInformation;
	
	private JButton btnTKRent;
	private JComboBox<String> cbTKRent;
	
	public TKRentController(MainUI mainUI) {
		this.mainUI = mainUI;
		muonXeDB = new MuonXeDB();
		chiTietDB = new ChiTietDB();
		btnTKRent = mainUI.getQlMT().getBtn().getBtnThongKe();
		cbTKRent = mainUI.getQlMT().getBtn().getCbThongKe();
		
		btnThongKeThueEvent();
	}
	
	private void btnThongKeThueEvent() {
		btnTKRent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cbTKRent.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(mainUI, "Hãy chọn 1 kiểu thống kê");
					return;
				}
				else {
					tkRentView = new TKRentView(mainUI);
					tkRentInformation = tkRentView.getTkRentInformation();
					tkRentView.setVisible(true);
					
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
					LocalDate localDate   = LocalDate.now();
					String ngayHT     = Integer.toString(localDate.getDayOfMonth());
					String thangHT    = Integer.toString(localDate.getMonthValue());
					String namHT      = Integer.toString(localDate.getYear());
					String now = ngayHT + "-" + thangHT + "-" + namHT;
					tkRentInformation.getLbCurrentDate().setText(now);
					
					int indexOfCombo = cbTKRent.getSelectedIndex();
					String title = cbTKRent.getSelectedItem().toString();
					tkRentInformation.getLbTitle().setText("THỐNG KÊ THUÊ XE THEO " + title);
				
					String data[][] = getDataTKMTFromDB(indexOfCombo);

					tkRentInformation.getTableTKRentView().updateTable(data, indexOfCombo);
					
					setActionForThongKeMTView(data);
				}
			}
		});
	}
	
	private String[][] getDataTKMTFromDB(int indexOfCb) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		if (indexOfCb == 1) {
			result = muonXeDB.thongKeMuonTra("idKhachHang");
		}
		else if (indexOfCb == 2) {
			result = muonXeDB.thongKeMuonTra("idNhanVien");
			
		}
		else if (indexOfCb == 3) {
			result = muonXeDB.thongKeMuonTra("ngayMuon");
		}
		else if (indexOfCb == 4) {
			result = muonXeDB.thongKeMuonTra("ngayHenTra");
		}
		else if (indexOfCb == 5) {
			result = muonXeDB.thongKeViPhamKH();
		}
		else if (indexOfCb == 6) {
			result = muonXeDB.thongKeTongTienPhat();
		}
		else if (indexOfCb == 7){
			result = muonXeDB.thongKeTongTienKM();
		}
		else if (indexOfCb == 8) {
			result = muonXeDB.thongKeXeChuaTra();
		}
		else if (indexOfCb == 9) {
			result = muonXeDB.thongKeTongXeNVChoThue();
		}
		else if (indexOfCb == 10) {
			result = tinhTienChiKH();
		}
		
		String[][] data = convertToString(result);
		return data;
		
	}
	
	/* Convert ArrayList<ArrayList<String>> to String[][] */
	private String[][] convertToString (ArrayList<ArrayList<String>> arr) {
		String[][] convertResult = new String[arr.size()][3];
		for (int i = 0; i < arr.size(); i ++) {
			convertResult[i][0] = Integer.toString(i+1);
			convertResult[i][1] = arr.get(i).get(0);
			convertResult[i][2] = arr.get(i).get(1);
		}
		
		return convertResult;
	}
	
	private ArrayList<ArrayList<String>> tinhTienChiKH() {
		ArrayList<ArrayList<String>> arrResult = new ArrayList<ArrayList<String>>();
		ArrayList<MuonXe> listMuonXe = muonXeDB.getAllMuonXe();
		ArrayList<String> listMaKH = muonXeDB.getListIdKhachHang();
		
		for (int i = 0; i < listMaKH.size(); i ++) {
			ArrayList<String> record = new ArrayList<String>();
			int tongTienChi = 0;
			for (int j = 0; j < listMuonXe.size(); j++) {
				String maKH = listMaKH.get(i);
				if (listMuonXe.get(j).getMaKH().equals(maKH)) {
					String maMT = listMuonXe.get(j).getMaMT();
					int tienChi = tinhTongTienThue(maMT) + (int)chiTietDB.tinhTongPhat(maMT);
					tongTienChi += tienChi;
				}
				else {
					continue;
				}
			}
			
			record.add(listMaKH.get(i));
			record.add(tongTienChi + "");
			arrResult.add(record);
		}
		
		return arrResult;
	}
	
	
	/* Calculating rent free */
	public int tienThue (String ngayTra, String ngayMuon, int tienThueMotNgay) {
		int tongTienThue = 0;
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try{
			Date dateStart = format.parse(ngayMuon);
			Date dateEnd   = format.parse(ngayTra);
			long diffDay = (dateEnd.getTime() - dateStart.getTime()) / (24 * 60 * 60 * 1000);
			System.out.println("So ngay thue: " + diffDay);
			if (diffDay > 0) {
				tongTienThue = (int) (diffDay * tienThueMotNgay);
			}
			else {
				tongTienThue = 0;
			}
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("Loi dinh dang ngay");
		}
		
		return tongTienThue;
	}
	
	public int tinhTongTienThue (String maMT) {
		ArrayList<ChiTiet> listChiTiet = chiTietDB.getAllChiTietWithID(maMT);
		MuonXe muonXe = muonXeDB.getMuonXe(maMT);
		String ngayMuon = muonXe.getNgayMuon();
		int tongTienThue = 0;		
		for (int i = 0; i < listChiTiet.size(); i++) {
			if (listChiTiet.get(i).getNgayTra().equals("")) continue;
			else {
				String ngayTra = listChiTiet.get(i).getNgayTra();
				int tienThueMotNgay = listChiTiet.get(i).getTienThue();
				tongTienThue += tienThue(ngayTra, ngayMuon, tienThueMotNgay);
			}
		}
		return tongTienThue;
	}
	
	
	
	
	
	
	/* Set Action for thongKeSachView - Print & Cancel */
	private void setActionForThongKeMTView(String[][] data) {
		JButton btnPrint  = tkRentView.getBtnPrint();
		JButton btnCancel = tkRentView.getBtnCancel();
		
		btnPrint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				inThongKe(data);
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				huy();
			}
		});		
	}
	
	/* In Thong Ke */
	private void inThongKe(String[][] data) {
		JFileChooser fileChooser = new JFileChooser();
		int select = fileChooser.showSaveDialog(this.tkRentView);
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
			saveThongKeExcelTo(saveFilePath, data);
		}
	}
	
	private void saveThongKeExcelTo(String path, String data[][]) {
		try {
			FileOutputStream fos = new FileOutputStream(new File(path));
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("ThongKeThueXe");
			sheet.setColumnWidth(0, 8400);
			sheet.setColumnWidth(1, 5600);
			sheet.setColumnWidth(2, 8400);
			
			Row row = sheet.createRow(0);
			Cell cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("TRƯỜNG ĐẠI HỌC BÁCH KHOA HÀ NỘI");
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Cộng hòa xã hội chủ nghĩa Việt Nam");
			cell.setCellStyle(createStyleDefault(workbook));
			
			row = sheet.createRow(1);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Cửa hàng xe Bách Khoa");
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Độc lập - Tự do - Hạnh phúc");
			cell.setCellStyle(createStyleDefault(workbook));
			
			row = sheet.createRow(2);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Nhóm 14");
			cell.setCellStyle(createStyleDefault(workbook));
			
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			LocalDate localDate   = LocalDate.now();
			String ngayHT     = Integer.toString(localDate.getDayOfMonth());
			String thangHT    = Integer.toString(localDate.getMonthValue());
			String namHT      = Integer.toString(localDate.getYear());
			String now = "Ngày " + ngayHT + "-" + thangHT + "-" + namHT;
			row = sheet.createRow(3);
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue(now);
			cell.setCellStyle(createStyleForDate(workbook));
			
			row = sheet.createRow(5);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue(tkRentInformation.getLbTitle().getText().toString().toUpperCase());
			cell.setCellStyle(createStyleForTitle(workbook));
			sheet.addMergedRegion(new CellRangeAddress(5, 5, 0, 2));
			
			row = sheet.createRow(7);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("STT");
			cell.setCellStyle(createStyleForTableTitle(workbook));
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue(cbTKRent.getSelectedItem().toString().toUpperCase());
			cell.setCellStyle(createStyleForTableTitle(workbook));
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("SỐ LƯỢNG");
			cell.setCellStyle(createStyleForTableTitle(workbook));
			
			int rowNum = 8;
			for (int i = 0; i < data.length; i++) {
				row = sheet.createRow(rowNum);
				for (int j = 0; j < 3; j++) {
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
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Chữ kí thủ thư");
			cell.setCellStyle(createStyleDefault(workbook));
			
			row = sheet.createRow(rowNum + 3);
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Admin");
			cell.setCellStyle(createStyleDefault(workbook));
			
			workbook.write(fos);
			System.out.println("Create file: " + path);
			fos.close();
		} 
		catch (IOException e) {
			JOptionPane.showMessageDialog(new JDialog(), "Lỗi File");
			e.printStackTrace();
		}
	}
	
	private XSSFCellStyle createStyleForDate (XSSFWorkbook workbook) {
		XSSFCellStyle styleDate = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setItalic(true);
		styleDate.setFont(font);
		styleDate.setAlignment(HorizontalAlignment.CENTER);
		return styleDate;
	}
	
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
	
	private XSSFCellStyle createStyleDefault(XSSFWorkbook workbook) {
		XSSFCellStyle styleDefault = workbook.createCellStyle();
		styleDefault.setAlignment(HorizontalAlignment.CENTER);
		return styleDefault;
	}
	
	private XSSFCellStyle createStyleDataTable(XSSFWorkbook workbook) {
		XSSFCellStyle styleData = workbook.createCellStyle();
		styleData.setAlignment(HorizontalAlignment.CENTER);
		styleData.setBorderBottom(BorderStyle.MEDIUM);
		styleData.setBorderTop(BorderStyle.MEDIUM);
		styleData.setBorderRight(BorderStyle.MEDIUM);
		styleData.setBorderLeft(BorderStyle.MEDIUM);
		return styleData;
	}

	private void huy() {
		this.tkRentView.setVisible(false);
	}
	
	
	
	
	
	
	
	
	
}
