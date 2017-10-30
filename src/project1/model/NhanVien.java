package project1.model;

public class NhanVien {
	private String idNhanVien;
	private String tenNhanVien;
	private String soCMND;
	private String ngaySinh;
	private String diaChi;
	private String gioiTinh;
	private String SDT;
	
	public NhanVien() {
		
	}

	public NhanVien(String idNhanVien, String tenNhanVien, String soCMND, String ngaySinh, String diaChi,
			String gioiTinh, String sDT) {
		super();
		this.idNhanVien = idNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.soCMND = soCMND;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.gioiTinh = gioiTinh;
		SDT = sDT;
	}

	public String getIdNhanVien() {
		return idNhanVien;
	}

	public void setIdNhanVien(String idNhanVien) {
		this.idNhanVien = idNhanVien;
	}

	public String getTenNhanVien() {
		return tenNhanVien;
	}

	public void setTenNhanVien(String tenNhanVien) {
		this.tenNhanVien = tenNhanVien;
	}

	public String getSoCMND() {
		return soCMND;
	}

	public void setSoCMND(String soCMND) {
		this.soCMND = soCMND;
	}

	public String getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getSDT() {
		return SDT;
	}

	public void setSDT(String sDT) {
		SDT = sDT;
	}
	
}
