package project1.model;

import java.util.ArrayList;

public interface MuonXeDAO {
	public ArrayList<MuonXe> getAllMuonXe();
	public MuonXe getMuonXe(String maMT);
	
	public void insertMuonXe(MuonXe muonXe);
	
	public void editMuonXe(MuonXe muonXe, String maKHMoi, String maNVMoi, String ngayHenTraMoi, int tienCocMoi);
	
	public ArrayList<ArrayList<String>> thongKeMuonTra(String colName);
}
