package project1.model;

public class KhachHang {
	private String idKhachHang;
	private String tenKhachHang;
	private String soCMND;
	private String ngaySinh;
	private String diaChi;
	private String gioiTinh;
	private String SDT;
	private String email;
	
	public KhachHang(String idKhachHang, String tenKhachHang, String soCMND, String ngaySinh, String diaChi,
			String gioiTinh, String sDT, String email) {
		super();
		this.idKhachHang = idKhachHang;
		this.tenKhachHang = tenKhachHang;
		this.soCMND = soCMND;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.gioiTinh = gioiTinh;
		this.SDT = sDT;
		this.email = email;
	}

	public String getIdKhachHang() {
		return idKhachHang;
	}
	public String getTenKhachHang() {
		return tenKhachHang;
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
	public String getEmail() {
		return email;
	}
}
