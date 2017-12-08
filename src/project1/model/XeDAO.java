package project1.model;

import java.util.ArrayList;

public interface XeDAO {
	/* Get all Xe from database */
	public ArrayList<Xe> getAllXe();
	
	/* Get one Xe with idXe */
	public Xe getXe(String idXe);
	
	/* Update one Xe */
	public void updateXe (Xe xe, String idXeMoi, String bienXeMoi, String tenXeMoi, String loaiXeMoi,
						 String hangSanXuatMoi, String namSanXuatMoi, String ngayBaoTriMoi,
						 String nhienLieuMoi, int trangThaiMoi, int giaThueMoi);
	/* Update status of xe */
	public void updateXe (Xe xe, int trangThaiMoi);
	
	/* Get avatarUrl */
	public String getAvatarUrl(String idXe);
	
	/* Update avatarUrl */
	public void update(String idXe, String newUrl);
	
	/* Insert one xe into database */
	public void insertXe(Xe xe);
	
	/* Delete one xe */
	public void deleteXe(Xe xe);
	
	/* Thong Ke Xe */
	public ArrayList<ArrayList<String>> thongKeXe(String colName);
}
