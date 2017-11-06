package project1.model;

import java.util.ArrayList;

public interface KhachHangDAO {
	// Get all KhachHang from database
	public ArrayList<KhachHang> getAllKhachHang();
		
	// Get one KhachHang with idNhanVien
	public KhachHang getKhachHang(String idKhachHang);
		
	// Update one KhachHang
	public void updateKhachHang (KhachHang khachHang, String idKHMoi, String tenKHMoi, String soCMNDMoi,
									String ngaySinhMoi, String diaChiMoi, String gioiTinhMoi, String SDTMoi, String emailMoi);
		
	/* Insert one KhachHang into database */
	public void insertKhachHang(KhachHang khachHang);
		
	/* Delete one KhachHang */
	public void deleteKhachHang(KhachHang khachHang);
}
