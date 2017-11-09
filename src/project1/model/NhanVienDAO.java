package project1.model;

import java.util.ArrayList;

public interface NhanVienDAO {
	// Get all NhanVien from database
	public ArrayList<NhanVien> getAllNhanVien();
	
	// Get one NhanVien with idNhanVien
	public NhanVien getNhanVien(String idNhanVien);
	
	// Update one NhanVien
	public void updateNhanVien (NhanVien nhanvien, String idNhanVienMoi, String tenNhanVienMoi, String soCMNDMoi,
								String ngaySinhMoi, String diaChiMoi, String gioiTinhMoi, String sDTMoi);
	
	/* Insert one nhanvien into database */
	public void insertNhanVien(NhanVien nhanvien);
	
	/* Delete one nhanvien */
	public void deleteNhanVien(NhanVien nhanvien);
	
	/* Thong ke nhanVien */
	public ArrayList<ArrayList<String>> thongKeNV(String colName);
}
