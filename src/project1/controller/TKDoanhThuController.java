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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

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

import project1.model.ChiTiet;
import project1.model.ChiTietDAO;
import project1.model.ChiTietDB;
import project1.model.MuonXe;
import project1.model.MuonXeDAO;
import project1.model.MuonXeDB;
import project1.model.NhanVienDAO;
import project1.model.NhanVienDB;
import project1.view.MainUI;
import project1.view.PanelTKDoanhThu;

public class TKDoanhThuController {
	public static final int PHAT_MOT_NGAY_MUON = 50000;
	private MainUI mainUI;
	private ChiTietDAO chiTietDB;
	private MuonXeDAO muonXeDB;
	private NhanVienDAO nhanVienDB;
	private PanelTKDoanhThu panelTKDoanhThu;
	
	private JButton btnTkNgay;
	private JButton btnTkThang;
	private JButton btnTkQuy;
	private JButton btnPrintNgay;
	private JButton btnPrintThang;
	private JButton btnPrintQuy;
	private JButton btnTinhDoanhSo;
	private JButton btnInDoanhSo;
	private JButton btnEdit;
	
	public TKDoanhThuController(MainUI mainUI) {
		this.mainUI = mainUI;
		muonXeDB = new MuonXeDB();
		chiTietDB = new ChiTietDB();
		nhanVienDB = new NhanVienDB();
		panelTKDoanhThu = mainUI.getPanelTKDoanhThu();
		initView();
		setAction();
	}
	
	private void initView() {
		btnTkNgay = panelTKDoanhThu.getBtnTkNgay();
		btnTkThang = panelTKDoanhThu.getBtnTkThang();
		btnTkQuy = panelTKDoanhThu.getBtnTkQuy();
		btnPrintNgay = panelTKDoanhThu.getBtnPrintNgay();
		btnPrintThang = panelTKDoanhThu.getBtnPrintThang();
		btnPrintQuy = panelTKDoanhThu.getBtnPrintQuy();
		btnPrintNgay.setEnabled(false);
		btnPrintQuy.setEnabled(false);
		btnPrintThang.setEnabled(false);
		btnTinhDoanhSo = panelTKDoanhThu.getBtnTinhDoanhSo();
		btnEdit = panelTKDoanhThu.getBtnEdit();
		btnInDoanhSo = panelTKDoanhThu.getBtnInDoanhSo();
		btnInDoanhSo.setEnabled(false);
		
		panelTKDoanhThu.getTfSoNV().setText(nhanVienDB.getAllNhanVien().size() + "");
		panelTKDoanhThu.getTfSoNV().setEditable(false);
		
		setEnableTfDoanhSo(false);
	}
	
	private void setEnableTfDoanhSo (boolean isEnable) {
		panelTKDoanhThu.getTfThueMatBang().setEditable(isEnable);
		panelTKDoanhThu.getTfLuongCoBan().setEditable(isEnable);
		panelTKDoanhThu.getTfPhiBaoTri().setEditable(isEnable);
		panelTKDoanhThu.getTfPhiVeSinh().setEditable(isEnable);
		panelTKDoanhThu.getTfPhiKhac().setEditable(isEnable);
		panelTKDoanhThu.getTfTongChi().setEditable(isEnable);
		panelTKDoanhThu.getTfTongDoanhThu().setEditable(isEnable);
		panelTKDoanhThu.getTfDoanhSo().setEditable(isEnable);
	}
	
	private void setAction() {
		btnTkNgay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String startDay = panelTKDoanhThu.getCbNgayBatDau().getSelectedItem().toString() + "-" +
								  panelTKDoanhThu.getCbThangBatDau().getSelectedItem().toString() + "-" +
								  panelTKDoanhThu.getCbNamBatDau().getSelectedItem().toString();
				String endDay = panelTKDoanhThu.getCbNgayKetThuc().getSelectedItem().toString() + "-" +
								panelTKDoanhThu.getCbThangKetThuc().getSelectedItem().toString() + "-" +
								panelTKDoanhThu.getCbNamKetThuc().getSelectedItem().toString();
				ArrayList arrDoanhThu = tinhDoanhThu(startDay, endDay);
				String ketQuaThongKe = "Doanh thu: " + arrDoanhThu.get(0) +
						  			   " --- Số lượng giao dịch trả: "  +arrDoanhThu.get(1);
				panelTKDoanhThu.getTfResultNgay().setText(ketQuaThongKe);
				
				/* In tkDoanhThu */
				ArrayList<ChiTiet> listDetailThisTime = (ArrayList<ChiTiet>) arrDoanhThu.get(2);
				String[][] data = convertData(listDetailThisTime);
				btnPrintNgay.setEnabled(true);
				btnPrintNgay.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						inThongKe(data, "THỐNG KÊ THUÊ XE TỪ NGÀY " + startDay + " ĐẾN " + endDay, ketQuaThongKe);
					}
				});
			}		
		});
		
		btnTkThang.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String thang = panelTKDoanhThu.getCbTkThang().getSelectedItem().toString();
				String nam = panelTKDoanhThu.getCbTkThangNam().getSelectedItem().toString();
				String startDay = "01-" + thang + "-" + nam;
				String endDay   = "31-" + thang + "-" + nam;
				
				ArrayList arrDoanhThu = tinhDoanhThu(startDay, endDay);
				String ketQuaThongKe = "Doanh thu: " + arrDoanhThu.get(0) +
									   " --- Số lượng giao dịch trả: "  +arrDoanhThu.get(1);
				panelTKDoanhThu.getTfResultThang().setText(ketQuaThongKe);
				/* In tkDoanhThu */
				ArrayList<ChiTiet> listDetailThisTime = (ArrayList<ChiTiet>) arrDoanhThu.get(2);
				String[][] data = convertData(listDetailThisTime);
				btnPrintThang.setEnabled(true);
				btnPrintThang.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						inThongKe(data, "THỐNG KÊ THUÊ XE THÁNG " + thang + " NĂM " + nam, ketQuaThongKe);
					}
				});
			}
		});
		
		btnTkQuy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String quy = panelTKDoanhThu.getCbTkQuy().getSelectedItem().toString();
				String nam = panelTKDoanhThu.getCbTkQuyNam().getSelectedItem().toString();
				String thangBatDau = "", thangKetThuc = "";
				
				if (quy.equals("1")) {
					thangBatDau = "1"; thangKetThuc = "3";
				}
				else if (quy.equals("2")) {
					thangBatDau = "4"; thangKetThuc = "6";
				}
				else if (quy.equals("3")) {
					thangBatDau = "7"; thangKetThuc = "9";
				}
				else if (quy.equals("4")) {
					thangBatDau = "10"; thangKetThuc = "12";
				}
				
				String startDay = "01-" + thangBatDau+ "-" + nam;
				String endDay   = "31-" + thangKetThuc + "-" + nam;
				
				ArrayList arrDoanhThu = tinhDoanhThu(startDay, endDay);
				String ketQuaThongKe = "Doanh thu: " + arrDoanhThu.get(0) +
						  			   " --- Số lượng giao dịch trả: "  +arrDoanhThu.get(1);
				panelTKDoanhThu.getTfResultQuy().setText(ketQuaThongKe);
				/* In tkDoanhThu */
				ArrayList<ChiTiet> listDetailThisTime = (ArrayList<ChiTiet>) arrDoanhThu.get(2);
				String[][] data = convertData(listDetailThisTime);
				btnPrintQuy.setEnabled(true);
				btnPrintQuy.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						inThongKe(data, "THỐNG KÊ THUÊ XE QUÝ " + quy + " NĂM " + nam, ketQuaThongKe);
					}
				});
			}
		});
		
		btnTinhDoanhSo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkTf()) {
					int phiMatBang = Integer.parseInt(panelTKDoanhThu.getTfThueMatBang().getText().toString().trim());
					int soNhanVien = Integer.parseInt(panelTKDoanhThu.getTfSoNV().getText().toString().trim());
					int luongCoBan = Integer.parseInt(panelTKDoanhThu.getTfLuongCoBan().getText().toString().trim());
					int phiBaoTri  = Integer.parseInt(panelTKDoanhThu.getTfPhiBaoTri().getText().toString().trim());
					int phiKhac    = Integer.parseInt(panelTKDoanhThu.getTfPhiKhac().getText().toString().trim());
					int phiVeSinh  = Integer.parseInt(panelTKDoanhThu.getTfPhiVeSinh().getText().toString().trim());
					int tongChi = phiMatBang + soNhanVien * luongCoBan + phiBaoTri + phiVeSinh + phiKhac;
					
					String thang = panelTKDoanhThu.getCbDoanhSoThang().getSelectedItem().toString();
					String nam = panelTKDoanhThu.getCbDoanhSoNam().getSelectedItem().toString();
					String startDay = "01-" + thang + "-" + nam;
					String endDay   = "31-" + thang + "-" + nam;
					ArrayList arrDoanhThu = tinhDoanhThu(startDay, endDay);
					int tongThu = (int) arrDoanhThu.get(0);
					
					int doanhSo = tongThu - tongChi;
					panelTKDoanhThu.getTfTongChi().setText(tongChi + "");
					panelTKDoanhThu.getTfTongDoanhThu().setText(tongThu + "");
					panelTKDoanhThu.getTfDoanhSo().setText(doanhSo + "");
					setEnableTfDoanhSo(false);
					
					btnInDoanhSo.setEnabled(true);
					btnInDoanhSo.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							inDoanhSo("DOANH SỐ DỰ TRÙ THÁNG " + thang + " NĂM " + nam);
						}
					});
				}
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setEnableTfDoanhSo(true);
			}
		});
		
	}

	private boolean checkTf() {
		if (panelTKDoanhThu.getTfThueMatBang().getText().toString().equals("") ||
			panelTKDoanhThu.getTfLuongCoBan().getText().toString().equals("") ||
			panelTKDoanhThu.getTfPhiBaoTri().getText().toString().equals("") ||
			panelTKDoanhThu.getTfPhiKhac().getText().toString().equals("") ||
			panelTKDoanhThu.getTfPhiVeSinh().getText().toString().equals("")) {
			JOptionPane.showMessageDialog(new JDialog(), "Các trường dữ liệu không được để trống");
			return false;
		}
		
		try {
			int phiMatBang = Integer.parseInt(panelTKDoanhThu.getTfThueMatBang().getText().toString().trim());
			int luongCoBan = Integer.parseInt(panelTKDoanhThu.getTfLuongCoBan().getText().toString().trim());
			int phiBaoTri  = Integer.parseInt(panelTKDoanhThu.getTfPhiBaoTri().getText().toString().trim());
			int phiKhac    = Integer.parseInt(panelTKDoanhThu.getTfPhiKhac().getText().toString().trim());
			int phiVeSinh  = Integer.parseInt(panelTKDoanhThu.getTfPhiVeSinh().getText().toString().trim());
			// Test < 0 ????
			if(phiMatBang < 0 || luongCoBan <0 || phiBaoTri < 0 || phiVeSinh < 0 || phiKhac < 0) {
				JOptionPane.showMessageDialog(new JDialog(), "Nhập lại đúng định dạng các trường số !!!");
					return false;
			}	
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(new JDialog(), "Các trường số phải là số");
			System.out.println(e.toString());
			return false;
		}
		return true;
	}
	
	private ArrayList tinhDoanhThu (String startDay, String endDay) {
		ArrayList<ChiTiet> listChiTiet = chiTietDB.getAllChiTiet();
		ArrayList<MuonXe> listMuonXe = muonXeDB.getAllMuonXe();
		ArrayList arrResult = new ArrayList();
		int doanhThu = 0;
		int soGiaoDichTra = 0;
		ArrayList<ChiTiet> listDetailThisTime = new ArrayList<ChiTiet>();
		
		for (int i = 0; i < listMuonXe.size(); i ++) {
			for (int j = 0; j < listChiTiet.size(); j++) {
				if (listMuonXe.get(i).getMaMT().equals(listChiTiet.get(j).getMaMT())) {
					String ngayMuon = listMuonXe.get(i).getNgayMuon();
					String ngayTra = listChiTiet.get(j).getNgayTra();
					if (checkNgayTra(ngayTra, startDay, endDay)) {
						int tienThue = tienThue(ngayTra, ngayMuon, listChiTiet.get(j).getTienThue());
						int tienPhat = listChiTiet.get(j).getTienPhat();
						int tienKM = listChiTiet.get(j).getTienKhuyenMai();
						int doanhThuMT = tienThue + tienPhat - tienKM;
						doanhThu += doanhThuMT;
						soGiaoDichTra ++;
						listDetailThisTime.add(listChiTiet.get(j));
					}
				}
			}
		}
		arrResult.add(doanhThu);
		arrResult.add(soGiaoDichTra);
		arrResult.add(listDetailThisTime);
		return arrResult;
	}
	
	/* Kiem tra xem ngayTra co thuoc vung thoi gian khong? */
	private boolean checkNgayTra(String ngayTra, String startDay, String endDay) {
		if(ngayTra.equals("") || startDay.equals("") || endDay.equals("")) return false;
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		
		try{
			Date dateStart = format.parse(startDay);
			Date dateEnd   = format.parse(endDay);
			Date datePay   = format.parse(ngayTra);
			long diffDay1 = (dateEnd.getTime() - datePay.getTime()) / (24 * 60 * 60 * 1000);
			long diffDay2 = (datePay.getTime() - dateStart.getTime()) / (24 * 60 * 60 * 1000);
			if (diffDay1 >=0 && diffDay2 >= 0) return true;
			else return false;
			
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("Loi dinh dang ngay");
			return false;
		}
	}
	
	/* Calculating rent free */
	private int tienThue (String ngayTra, String ngayMuon, int tienThueMotNgay) {
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
	
	/* Convert Data */
	private String[][] convertData(ArrayList<ChiTiet> listDetail) {
		String[][] data = new String[listDetail.size()][6];
		for (int i = 0; i < listDetail.size(); i++) {
			data[i][0] = listDetail.get(i).getMaMT();
			data[i][1] = listDetail.get(i).getMaXe();
			data[i][2] = listDetail.get(i).getNgayTra();
			data[i][3] = listDetail.get(i).getTienThue() + "";
			data[i][4] = listDetail.get(i).getTienPhat() + "";
			data[i][5] = listDetail.get(i).getTienKhuyenMai() + "";
		}
		return data;
	}
 	
	/* In Thong Ke */
	private void inThongKe(String[][] listDetailThisTime, String title, String ketQuaThongKe) {
		JFileChooser fileChooser = new JFileChooser();
		int select = fileChooser.showSaveDialog(this.panelTKDoanhThu);
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
			saveThongKeExcelTo(saveFilePath, listDetailThisTime, title, ketQuaThongKe);
			btnPrintNgay.setEnabled(false);
			btnPrintThang.setEnabled(false);
			btnPrintQuy.setEnabled(false);
		}
	}
	
	private void saveThongKeExcelTo(String path, String[][] listDetailThisTime, String title, String ketQuaThongKe) {
		try {
			FileOutputStream fos = new FileOutputStream(new File(path));
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("ThongKeDoanhThu");
			sheet.setColumnWidth(0, 5000);
			sheet.setColumnWidth(1, 5000);
			sheet.setColumnWidth(2, 5000);
			sheet.setColumnWidth(3, 5000);
			sheet.setColumnWidth(4, 5000);
			sheet.setColumnWidth(5, 5000);
			
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
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue(now);
			cell.setCellStyle(createStyleForDate(workbook));
			sheet.addMergedRegion(new CellRangeAddress(3, 3, 3, 5));
			
			row = sheet.createRow(7);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue(title);
			cell.setCellStyle(createStyleForTitle(workbook));
			sheet.addMergedRegion(new CellRangeAddress(7, 7, 0, 5));
			
			row = sheet.createRow(9);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Mã mượn trả");
			cell.setCellStyle(createStyleForTableTitle(workbook));
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Mã xe");
			cell.setCellStyle(createStyleForTableTitle(workbook));
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("Ngày trả");
			cell.setCellStyle(createStyleForTableTitle(workbook));
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Tiền thuê một ngày");
			cell.setCellStyle(createStyleForTableTitle(workbook));
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue("Tiền phạt");
			cell.setCellStyle(createStyleForTableTitle(workbook));
			cell = row.createCell(5, CellType.STRING);
			cell.setCellValue("Tiền khuyến mại");
			cell.setCellStyle(createStyleForTableTitle(workbook));
			
			int rowNum = 10;
			for (int i = 0; i < listDetailThisTime.length; i++) {
				row = sheet.createRow(rowNum);
				for (int j = 0; j < 6; j++) {
					cell = row.createCell(j, CellType.STRING);
					cell.setCellValue(listDetailThisTime[i][j]);
					cell.setCellStyle(createStyleDataTable(workbook));
				}
				rowNum++;
			}
			
			row = sheet.createRow(rowNum + 2);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue(ketQuaThongKe);
			cell.setCellStyle(createStyleDefault(workbook));
			sheet.addMergedRegion(new CellRangeAddress(rowNum + 2, rowNum + 2, 0, 5));
			
			row = sheet.createRow(rowNum + 3);
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Chữ kí quản lí");
			cell.setCellStyle(createStyleDefault(workbook));
			sheet.addMergedRegion(new CellRangeAddress(rowNum + 3, rowNum + 3, 3, 5));
			
			row = sheet.createRow(rowNum + 4);
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Admin");
			cell.setCellStyle(createStyleDefault(workbook));
			sheet.addMergedRegion(new CellRangeAddress(rowNum + 4, rowNum + 4, 3, 5));
			
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
	
	private void printImage (Workbook wb, Sheet sheet) {
		 try {
			 Path imagePath = Paths.get(ClassLoader.getSystemResource("bachkhoa.png").toURI()); 
			 InputStream inputStream = Files.newInputStream(imagePath);
			 byte[] bytes = IOUtils.toByteArray(inputStream);
			 int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
			 inputStream.close();
			 CreationHelper helper = wb.getCreationHelper();
			 Drawing drawing = sheet.createDrawingPatriarch();
			 ClientAnchor anchor = helper.createClientAnchor();
			 anchor.setCol1(1);
			 anchor.setRow1(3);
			 Picture pict = drawing.createPicture(anchor, pictureIdx);
			 pict.resize();	 
		 }
		 catch (Exception e) {
			 System.out.println(e);
		 }
	}
	
	/* In Thong Ke */
	private void inDoanhSo(String title) {
		JFileChooser fileChooser = new JFileChooser();
		int select = fileChooser.showSaveDialog(this.panelTKDoanhThu);
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
			saveDoanhSoExcelTo(saveFilePath, title);
			btnInDoanhSo.setEnabled(false);
		}
	}
	
	private void saveDoanhSoExcelTo(String path, String title) {
		try {
			FileOutputStream fos = new FileOutputStream(new File(path));
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("DoanhSoDuTinh");
			sheet.setColumnWidth(0, 15000);
			sheet.setColumnWidth(1, 15000);
			
			
			Row row = sheet.createRow(0);
			Cell cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("TRƯỜNG ĐẠI HỌC BÁCH KHOA HÀ NỘI");
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Cộng hòa xã hội chủ nghĩa Việt Nam");
			cell.setCellStyle(createStyleDefault(workbook));
			
			row = sheet.createRow(1);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Cửa hàng xe Bách Khoa");
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Độc lập - Tự do - Hạnh phúc");
			cell.setCellStyle(createStyleDefault(workbook));
			
			row = sheet.createRow(2);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Nhóm 14");
			cell.setCellStyle(createStyleDefault(workbook));
			
			LocalDate localDate   = LocalDate.now();
			String ngayHT         = Integer.toString(localDate.getDayOfMonth());
			String thangHT        = Integer.toString(localDate.getMonthValue());
			String namHT          = Integer.toString(localDate.getYear());
			String now            = "Ngày " + ngayHT + "-" + thangHT + "-" + namHT;
			
			row = sheet.createRow(3);  
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue(now);
			cell.setCellStyle(createStyleForDate(workbook));
			
			row = sheet.createRow(5);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue(title);
			cell.setCellStyle(createStyleForTitle(workbook));
			sheet.addMergedRegion(new CellRangeAddress(5, 6, 0, 1));
			
			row = sheet.createRow(8);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Chi phí mặt bằng hàng tháng");
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue(panelTKDoanhThu.getTfThueMatBang().getText().toString());
			cell.setCellStyle(createStyleDefault(workbook));
			
			row = sheet.createRow(9);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Số nhân viên");
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue(panelTKDoanhThu.getTfSoNV().getText().toString());
			cell.setCellStyle(createStyleDefault(workbook));
			
			row = sheet.createRow(10);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Lương cơ bản mỗi nhân viên");
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue(panelTKDoanhThu.getTfLuongCoBan().getText().toString());
			cell.setCellStyle(createStyleDefault(workbook));
			
			row = sheet.createRow(11);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Chi phí bảo trì xe");
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue(panelTKDoanhThu.getTfPhiBaoTri().getText().toString());
			cell.setCellStyle(createStyleDefault(workbook));
			
			row = sheet.createRow(12);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Chi phí vệ sinh hàng tháng");
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue(panelTKDoanhThu.getTfPhiVeSinh().getText().toString());
			cell.setCellStyle(createStyleDefault(workbook));
			
			row = sheet.createRow(13);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Chi phí khác");
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue(panelTKDoanhThu.getTfPhiKhac().getText().toString());
			cell.setCellStyle(createStyleDefault(workbook));
			
			row = sheet.createRow(14);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Tổng chi");
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue(panelTKDoanhThu.getTfTongChi().getText().toString());
			cell.setCellStyle(createStyleDefault(workbook));
			
			row = sheet.createRow(15);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Tổng thu");
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue(panelTKDoanhThu.getTfTongDoanhThu().getText().toString());
			cell.setCellStyle(createStyleDefault(workbook));
			
			row = sheet.createRow(16);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Lợi nhuận dự tính");
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue(panelTKDoanhThu.getTfDoanhSo().getText().toString());
			cell.setCellStyle(createStyleDefault(workbook));
			
			row = sheet.createRow(18);
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("Chữ kí quản lí");
			cell.setCellStyle(createStyleDefault(workbook));
			
			row = sheet.createRow(19);
			cell = row.createCell(1, CellType.STRING);
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
	
}
