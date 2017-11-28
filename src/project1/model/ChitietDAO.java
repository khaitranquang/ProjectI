package project1.model;

import java.util.ArrayList;

public interface ChitietDAO {
	public ArrayList<ChiTiet> getAllChiTietWithID(String maMT);
	
	public ChiTiet getChiTiet(String maMT, String maXeMuon);
	
	public void insertChiTiet(ChiTiet chiTiet);
	
	public void updateChiTiet(ChiTiet chiTiet, String ngayTraMoi, int tienPhatMoi, int khuyenMaiMoi);
	
	public void deleteChiTiet(String maMT);
	
	public double tinhTongPhat(String maMT);
	
	public double tinhTongKhuyenMai(String maMT);
}
