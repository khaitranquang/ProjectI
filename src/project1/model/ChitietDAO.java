package project1.model;

import java.util.ArrayList;

public interface ChitietDAO {
	public ArrayList<ChiTiet> getAllChiTietWithID(String maMT);
	
	public ChiTiet getChiTiet(String maMT, String maXeMuon);
	
	public void insertChiTiet(ChiTiet chiTiet);
	
	public void updateChiTiet(ChiTiet chiTiet, String ngayTraMoi, int tienPhatMoi);
}
