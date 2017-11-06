package project1.model;

public class NhanVien {
	private String idNhanVien;
	private String tenNhanVien;
	private String soCMND;
	private String ngaySinh;
	private String diaChi;
	private String gioiTinh;
	private String SDT;
	
	public NhanVien(String idNhanVien, String tenNhanVien, String soCMND, String ngaySinh, String diaChi,
			String gioiTinh, String SDT) {
		super();
		this.idNhanVien = idNhanVien;
		this.tenNhanVien = tenNhanVien;
		this.soCMND = soCMND;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.gioiTinh = gioiTinh;
		this.SDT = SDT;
	}

	public String getIdNhanVien() {
		return idNhanVien;
	}
	public String getTenNhanVien() {
		return tenNhanVien;
	}
	public String getSoCMND() {
		return soCMND;
	}
	public String getNgaySinh() {
		return ngaySinh;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public String getGioiTinh() {	
		return gioiTinh;
	}
	public String getSDT() {
		return SDT;
	}
}
