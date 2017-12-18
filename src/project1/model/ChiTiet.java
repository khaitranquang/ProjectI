package project1.model;

public class ChiTiet {
	private String maMT;
	private String maXe;
	private int tienThue;
	private String ngayTra;
	private int tienPhat;
	private int tienKhuyenMai;
	private String trangThai;
	
	public String getMaMT() {
		return maMT;
	}
	public String getMaXe() {
		return maXe;
	}
	public int getTienThue() {
		return tienThue;
	}
	public String getNgayTra() {
		return ngayTra;
	}
	public int getTienPhat() {
		return tienPhat;
	}
	public int getTienKhuyenMai() {
		return tienKhuyenMai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}	
	public String getTrangThai() {
		return trangThai;
	}
	public ChiTiet(String maMT, String maXe, int tienThue, String ngayTra, int tienPhat, int tienKhuyenMai) {
		super();
		this.maMT = maMT;
		this.maXe = maXe;
		this.tienThue = tienThue;
		this.ngayTra = ngayTra;
		this.tienPhat = tienPhat;
		this.tienKhuyenMai = tienKhuyenMai;
	}
	
	@Override
	public String toString() {
		return maMT + "-" + maXe + "-" + ngayTra + "-" + tienThue + "-" + tienPhat + "-" + tienKhuyenMai;
	}
}
