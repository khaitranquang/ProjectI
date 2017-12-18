package project1.model;

import java.util.ArrayList;

public interface MuonXeDAO {
	public ArrayList<MuonXe> getAllMuonXe();
	public MuonXe getMuonXe(String maMT);
	
	public void insertMuonXe(MuonXe muonXe);
	
	public void editMuonXe(MuonXe muonXe, String maKHMoi, String maNVMoi, String ngayHenTraMoi, int tienCocMoi);
	
	public void deleteRentXe(String id);
	
	public ArrayList<String> getListIdKhachHang();
	
	// Thong ke thue xe
	public ArrayList<ArrayList<String>> thongKeMuonTra(String colName);
	
	public ArrayList<ArrayList<String>> thongKeViPhamKH();
	
	public ArrayList<ArrayList<String>> thongKeTongTienPhat();
	
	public ArrayList<ArrayList<String>> thongKeTongTienKM();
	
	public ArrayList<ArrayList<String>> thongKeTongXeNVChoThue();
	
	public ArrayList<ArrayList<String>> thongKeXeChuaTra();
}
