package project1.model;

public class MuonXe {
	private String maMT;
	private String maKH;
	private String maNV;
	private String ngayMuon;
	private String ngayHenTra;
	private int tienCoc;
	
	public String getMaMT() {
		return maMT;
	}
	public String getMaKH() {
		return maKH;
	}
	public String getNgayMuon() {
		return ngayMuon;
	}
	public String getNgayHenTra() {
		return ngayHenTra;
	}
	public int getTienCoc() {
		return tienCoc;
	}
	public String getMaNV() {
		return maNV;
	}
	
	public MuonXe(String maMT, String maKH, String maNV, String ngayMuon, String ngayHenTra, int tienCoc) {
		super();
		this.maMT = maMT;
		this.maKH = maKH;
		this.maNV = maNV;
		this.ngayMuon = ngayMuon;
		this.ngayHenTra = ngayHenTra;
		this.tienCoc = tienCoc;
	}
}
