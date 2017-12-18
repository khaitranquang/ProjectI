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
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
import project1.model.KhachHangDB;
import project1.model.MuonXe;
import project1.model.MuonXeDAO;
import project1.model.MuonXeDB;
import project1.model.NhanVienDB;
import project1.model.Xe;
import project1.model.XeDB;
import project1.view.ChiTietInformation;
import project1.view.ChiTietView;
import project1.view.MainUI;
import project1.view.TableMuonTraView;


public class ShowDetailInformation {
	private static final int PHAT_MOT_NGAY_MUON = 50000;
	private static final double PHAN_TRAM_KHUYEN_MAI = 0.1;
	
	private MainUI mainUI;
	private ChiTietDAO chiTietDB;
	private MuonXeDAO muonXeDB;
	
	private ChiTietView chiTietView;
	private JButton btnShowDetail;
	
	private ChiTietInformation chiTietInformation;
	private TableMuonTraView tableMuonTraView;
	
	/* Initialize */
	public ShowDetailInformation (MainUI mainUI) {
		this.mainUI = mainUI;
		chiTietDB   = new ChiTietDB();
		muonXeDB    = new MuonXeDB();
		
		tableMuonTraView = mainUI.getQlMT().getTableMT();	
		btnShowDetail   = mainUI.getQlMT().getBtn().getBtnChiTiet();
		
		btnShowDetailEvent();
	}
	
	private void btnShowDetailEvent() {
		btnShowDetail.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				chiTietView        = new ChiTietView(mainUI);
				chiTietInformation = chiTietView.getChiTietInformation();
				
				int index = findIndexOfData();
				if (index >= 0) {
					chiTietView.setVisible(true);
					String maMT = getMaMT(index, 0);
					loadInfor(maMT);
					setActions();
				}
				else {
					JOptionPane.showMessageDialog(mainUI, "Chọn 1 mượn trả để xem chi tiết");
				}
			}
		});
	}
	
	/* Find index of row is selected */
	private int findIndexOfData() {
		int index = tableMuonTraView.getTable().getSelectedRow();
		return index;
	}
	
	/* Get maMT of row is selected */
	private String getMaMT (int indexRow, int indexCol) {
		JTable table = tableMuonTraView.getTable();
		String maMT  = table.getModel().getValueAt(indexRow, indexCol).toString();
		return maMT;
	}
	
	/* Load detail information */
	private void loadInfor(String maMT) {
		MuonXe muonXe = muonXeDB.getMuonXe(maMT);
		ArrayList<ChiTiet> listDetail = chiTietDB.getAllChiTietWithID(maMT);
		
		chiTietInformation.getLbMaMT().setText(maMT);
		chiTietInformation.getLbMaKH().setText(muonXe.getMaKH());
		chiTietInformation.getLbMaNV().setText(muonXe.getMaNV());
		chiTietInformation.getLbHoTenKH().setText(new KhachHangDB().getKhachHang(muonXe.getMaKH()).getTenKhachHang());
		chiTietInformation.getLbHoTenNV().setText(new NhanVienDB().getNhanVien(muonXe.getMaNV()).getTenNhanVien());
		chiTietInformation.getLbNgayMuon().setText(muonXe.getNgayMuon());
		chiTietInformation.getLbNgayHenTra().setText(muonXe.getNgayHenTra());
		chiTietInformation.getLbSoTienCoc().setText(muonXe.getTienCoc() + "");
		chiTietInformation.getLbNgayTra().setText("");
		chiTietInformation.getLbTrangThai().setText("");
		chiTietInformation.getLbSoTienPhat().setText("");
		
		chiTietInformation.getLbTongTienPhat().setText(chiTietDB.tinhTongPhat(maMT) + "");
		chiTietInformation.getLbTongTienThue().setText(tinhTongTienThue(maMT) + "");
		chiTietInformation.getLbTongKhuyenMai().setText(chiTietDB.tinhTongKhuyenMai(maMT) + "");
		
		/* Update table list book is loan */
		String[][] listXe = new String[listDetail.size()][3];
		for (int i = 0; i < listDetail.size(); i++) {
			listXe[i][0] = listDetail.get(i).getMaXe();
			listXe[i][1] = new XeDB().getXe(listDetail.get(i).getMaXe()).getTenXe();
			listXe[i][2] = listDetail.get(i).getTienThue() + "";
		}
		chiTietInformation.getTableChiTietView().updateTable(listXe);
	}
	
	/* Set actions for all buttons in DetailInformatio View*/
	
	private void setActions() {
		JTable tableDetail = chiTietInformation.getTableChiTietView().getTable();
		JButton btnPay     = chiTietView.getBtnPay();
		btnPay.setEnabled(false);
		JButton btnPayAll  = chiTietView.getBtnPayAll();
		JButton btnPrint   = chiTietView.getBtnPrint();
		JButton btnCancel  = chiTietView.getBtnCancel();
		
		/* When a row in table (list Book is loan) is selected */
		tableDetail.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String maMT     = chiTietInformation.getLbMaMT().getText().trim().toString();
				String maXeMuon = tableDetail.getModel().getValueAt(tableDetail.getSelectedRow(), 0).toString();
				ChiTiet detail  = chiTietDB.getChiTiet(maMT, maXeMuon);
				
				if (detail.getNgayTra().equals("") == true) {
					btnPay.setEnabled(true);
				}
				else {
					btnPay.setEnabled(false);
				}
				
				chiTietInformation.getLbNgayTra().setText(detail.getNgayTra());
				chiTietInformation.getLbSoTienPhat().setText(detail.getTienPhat() + "");
				chiTietInformation.getLbSoKhuyenMai().setText(detail.getTienKhuyenMai() + "");
				
				if (chiTietInformation.getLbNgayTra().getText().trim().toString().equals("")) {
					chiTietInformation.getLbTrangThai().setText("Chưa trả");
				}
				else {
					chiTietInformation.getLbTrangThai().setText("Đã trả");
				}
			}
		});
		
		/* Pay a book */
		btnPay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int indexOfRow = tableDetail.getSelectedRow();
				String maMT = chiTietInformation.getLbMaMT().getText().trim().toString();
				String maXeMuon = tableDetail.getModel().getValueAt(indexOfRow, 0).toString();
				
				LocalDate localDate   = LocalDate.now();
				String ngayTraHT      = Integer.toString(localDate.getDayOfMonth());
				String thangTraHT     = Integer.toString(localDate.getMonthValue());
				String namTraHT       = Integer.toString(localDate.getYear());
				String ngayTra        = ngayTraHT + "-" + thangTraHT + "-" + namTraHT;
				
				ChiTiet detail = chiTietDB.getChiTiet(maMT, maXeMuon);
				int tienPhat       = tinhTienPhat(ngayTra, chiTietInformation.getLbNgayHenTra().getText().toString());
				int tienKhuyenMai  = 0;
				if (tienPhat == 0 && (!ngayTra.equals(chiTietInformation.getLbNgayMuon().getText().toString()))) {
					tienKhuyenMai = (int) (PHAN_TRAM_KHUYEN_MAI * 
									tienThue(ngayTra, chiTietInformation.getLbNgayMuon().getText().toString(), detail.getTienThue()));
				}
				
				// Update ngayTra, tienPhat 
				chiTietDB.updateChiTiet(detail, ngayTra, tienPhat, tienKhuyenMai);
				
				// Update status of xe
				Xe xeDuocMuon = new XeDB().getXe(maXeMuon);
				new XeDB().updateXe(xeDuocMuon, 1);
				mainUI.getQlXe().getTableXe().updateTable(new XeDB().getAllXe());
				
				// Update View
				chiTietInformation.getLbTrangThai().setText("Đã trả");
				chiTietInformation.getLbNgayTra().setText(ngayTra);
				chiTietInformation.getLbSoTienPhat().setText(tienPhat + "");
				chiTietInformation.getLbSoKhuyenMai().setText(tienKhuyenMai + "");
				chiTietInformation.getLbTongTienPhat().setText(chiTietDB.tinhTongPhat(maMT) + "");
				chiTietInformation.getLbTongTienThue().setText(tinhTongTienThue(maMT) + "");
				chiTietInformation.getLbTongKhuyenMai().setText(chiTietDB.tinhTongKhuyenMai(maMT) + "");
				
				// Disable this btnPay of this book
				btnPay.setEnabled(false);
				
			}
		});
		
		/* Pay all books */
		btnPayAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String            maMT       = chiTietInformation.getLbMaMT().getText().trim().toString();
				ArrayList<ChiTiet> listDetail = chiTietDB.getAllChiTietWithID(maMT);
				for (int i = 0; i < listDetail.size(); i++) {
					ChiTiet aDetail = listDetail.get(i);
					if (aDetail.getNgayTra().equals("") == false) continue;
					else {
						String maXeMuon = aDetail.getMaXe();
						
						LocalDate localDate   = LocalDate.now();
						String ngayTraHT      = Integer.toString(localDate.getDayOfMonth());
						String thangTraHT     = Integer.toString(localDate.getMonthValue());
						String namTraHT       = Integer.toString(localDate.getYear());
						String ngayTra        = ngayTraHT + "-" + thangTraHT + "-" + namTraHT;
						
						int tienPhat       = tinhTienPhat(ngayTra, chiTietInformation.getLbNgayHenTra().getText().toString());
						int tienKhuyenMai  = 0;
						if (tienPhat == 0 && (!ngayTra.equals(chiTietInformation.getLbNgayMuon().getText().toString()))) {
							tienKhuyenMai = (int) (PHAN_TRAM_KHUYEN_MAI * 
											tienThue(ngayTra, chiTietInformation.getLbNgayMuon().getText().toString(), 
											aDetail.getTienThue()));
						}
						
						
						chiTietDB.updateChiTiet(aDetail, ngayTra, tienPhat, tienKhuyenMai);
						
						// Update status of xe
						Xe XeDuocThue = new XeDB().getXe(maXeMuon);
						new XeDB().updateXe(XeDuocThue, 1);
						mainUI.getQlXe().getTableXe().updateTable(new XeDB().getAllXe());
					}
				}
				
				chiTietInformation.getLbTongTienPhat().setText(chiTietDB.tinhTongPhat(maMT) + "");
				chiTietInformation.getLbTongTienThue().setText(tinhTongTienThue(maMT) + "");
				chiTietInformation.getLbTongKhuyenMai().setText(chiTietDB.tinhTongKhuyenMai(maMT) + "");
				JOptionPane.showMessageDialog(new JDialog(), "Tất cả xe còn lại đã được trả");
				btnPayAll.setEnabled(false);
			}
		});
		
		/* Print bill */
		btnPrint.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String [][] data = convertData();
				printBill(data);
			}
		});
		
		/* Close this dialog */
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				huy();
			}
		});
	}
	
	private void huy() {
		this.chiTietView.setVisible(false);
	}
	
	/* Calculating the different day(s) between ngayTra and ngayHenTra */
	/* Then calculating forfeit */
	public int tinhTienPhat (String ngayTra, String ngayHenTra) {
		int tienPhat = 0;
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		try{
			Date dateStart = format.parse(ngayHenTra);
			Date dateEnd   = format.parse(ngayTra);
			long diffDay = (dateEnd.getTime() - dateStart.getTime()) / (24 * 60 * 60 * 1000);
			System.out.println("diffDay: " + diffDay);
			if (diffDay > 0) {
				tienPhat =(int)(diffDay * PHAT_MOT_NGAY_MUON);
			}
			else {
				tienPhat = 0;
			}
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("Loi dinh dang ngay");
		}
		return tienPhat;
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
//			else if (diffDay == 0){
//				tongTienThue = (double) (tienThueMotNgay * 0.5);
//			}
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
	
	
	/* Print bill with data */
	private void printBill(String[][] data) {
		JFileChooser fileChooser = new JFileChooser();
		int select = fileChooser.showSaveDialog(this.chiTietView);
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
			saveBillExcelTo(saveFilePath, data);
		}
	}
	
	/* Convert table to data */
	private String[][] convertData() {
		String            maMT       = chiTietInformation.getLbMaMT().getText().trim().toString();
		ArrayList<ChiTiet> listDetail = chiTietDB.getAllChiTietWithID(maMT);
		String data[][] = new String[listDetail.size()][5];
		
		for (int i = 0; i < listDetail.size(); i++) {
			ChiTiet aDetail = listDetail.get(i);
			data[i][0] = Integer.toString(i+1);
			data[i][1] = aDetail.getMaXe();
			data[i][2] = chiTietInformation.getTableChiTietView().getTable().getModel().getValueAt(i, 1).toString();
			data[i][3] = aDetail.getNgayTra();
			data[i][4] = aDetail.getTienPhat() + "";
		}
		return data;
	}
	
	private void saveBillExcelTo (String path, String data[][]) {
		try {
			FileOutputStream fos = new FileOutputStream(new File(path));
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("PheuMuonTra");
			sheet.setColumnWidth(0, 7000);
			sheet.setColumnWidth(1, 7000);
			sheet.setColumnWidth(2, 7000);
			sheet.setColumnWidth(3, 7000);
			sheet.setColumnWidth(4, 7000);
			
			Row row = sheet.createRow(0);
			Cell cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("TRƯỜNG ĐẠI HỌC BÁCH KHOA HÀ NỘI");
			cell.setCellStyle(createStyleDefault(workbook));
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 1));
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Cộng hòa xã hội chủ nghĩa Việt Nam");
			cell.setCellStyle(createStyleDefault(workbook));
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 3, 4));
			
			row = sheet.createRow(1);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Thư viện Tạ Quang Bửu");
			cell.setCellStyle(createStyleDefault(workbook));
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 1));
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Độc lập - Tự do - Hạnh phúc");
			cell.setCellStyle(createStyleDefault(workbook));
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 3, 4));
			
			row = sheet.createRow(2);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Nhóm 14");
			cell.setCellStyle(createStyleDefault(workbook));
			sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 1));
			
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
			sheet.addMergedRegion(new CellRangeAddress(3, 3, 3, 4));
			
			row = sheet.createRow(7);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("PHIẾU THUÊ TRẢ");
			cell.setCellStyle(createStyleForTitle(workbook));
			sheet.addMergedRegion(new CellRangeAddress(7, 7, 0, 4));
			
			row = sheet.createRow(9);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Mã mượn trả");
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue(chiTietInformation.getLbMaMT().getText().toString());
			cell.setCellStyle(createStyleDefault(workbook));
			
			row = sheet.createRow(10);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Mã khách hàng");
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue(chiTietInformation.getLbMaKH().getText().toString());
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Tên khách hàng");
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue(chiTietInformation.getLbHoTenKH().getText().toString());
			cell.setCellStyle(createStyleDefault(workbook));
			
			row = sheet.createRow(11);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Mã nhân viên");
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue(chiTietInformation.getLbMaNV().getText().toString());
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Tên nhân viên");
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue(chiTietInformation.getLbHoTenNV().getText().toString());
			cell.setCellStyle(createStyleDefault(workbook));
			
			row = sheet.createRow(12);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Ngày mượn");
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue(chiTietInformation.getLbNgayMuon().getText().toString());
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Ngày hẹn trả");
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue(chiTietInformation.getLbNgayHenTra().getText().toString());
			cell.setCellStyle(createStyleDefault(workbook));
			
			
			row = sheet.createRow(14);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("STT");
			cell.setCellStyle(createStyleForTableTitle(workbook));
			cell = row.createCell(1, CellType.STRING);
			cell.setCellValue("MÃ XE MƯỢN");
			cell.setCellStyle(createStyleForTableTitle(workbook));
			cell = row.createCell(2, CellType.STRING);
			cell.setCellValue("TÊN XE");
			cell.setCellStyle(createStyleForTableTitle(workbook));
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("NGÀY TRẢ");
			cell.setCellStyle(createStyleForTableTitle(workbook));
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue("TIỀN PHẠT");
			cell.setCellStyle(createStyleForTableTitle(workbook));
			
			int rowNum = 15;
			for (int i = 0; i < data.length; i++) {
				row = sheet.createRow(rowNum);
				for (int j = 0; j < 5; j++) {
					cell = row.createCell(j, CellType.STRING);
					cell.setCellValue(data[i][j]);
					cell.setCellStyle(createStyleDataTable(workbook));
				}
				rowNum++;
			}
			
			
			row = sheet.createRow(rowNum + 1);
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Tổng số tiền phạt");
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue(chiTietInformation.getLbTongTienPhat().getText().toString());
			cell.setCellStyle(createStyleDefault(workbook));
			
			row = sheet.createRow(rowNum + 2);
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Tổng số tiền thuê");
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue(chiTietInformation.getLbTongTienThue().getText().toString());
			cell.setCellStyle(createStyleDefault(workbook));
			
			row = sheet.createRow(rowNum + 3);
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Tổng số tiền khuyễn mãi");
			cell.setCellStyle(createStyleDefault(workbook));
			cell = row.createCell(4, CellType.STRING);
			cell.setCellValue(chiTietInformation.getLbTongKhuyenMai().getText().toString());
			cell.setCellStyle(createStyleDefault(workbook));
			
			
			
			row = sheet.createRow(rowNum + 5);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue("Người thuê");
			cell.setCellStyle(createStyleDefault(workbook));
			sheet.addMergedRegion(new CellRangeAddress(rowNum + 5, rowNum + 5, 0, 1));
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue("Nhân viên cho thuê");
			cell.setCellStyle(createStyleDefault(workbook));
			sheet.addMergedRegion(new CellRangeAddress(rowNum + 5, rowNum + 5, 3, 4));
			
			row = sheet.createRow(rowNum + 6);
			cell = row.createCell(0, CellType.STRING);
			cell.setCellValue(chiTietInformation.getLbHoTenKH().getText().toString());
			cell.setCellStyle(createStyleDefault(workbook));
			sheet.addMergedRegion(new CellRangeAddress(rowNum + 6, rowNum + 6, 0, 1));
			cell = row.createCell(3, CellType.STRING);
			cell.setCellValue(chiTietInformation.getLbHoTenNV().getText().toString());
			cell.setCellStyle(createStyleDefault(workbook));
			sheet.addMergedRegion(new CellRangeAddress(rowNum + 6, rowNum + 6, 3, 4));
			
			
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
			   pict.resize();
			   
			  }
			  catch (Exception e) {
			   System.out.println(e);
			  }
	}

}
