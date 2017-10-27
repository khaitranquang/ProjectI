package project1.model;

public class Xe {
	private String idXe;
	private String bienXe;
	private String tenXe;
	private String loaiXe;
	private String hangSanXuat;
	private String namSanXuat;
	private String ngayBaoTri;
	private String nhienLieu;
	private	int trangThai;
	private int giaThue;
	
	// Getter
	public String getIdXe() {
		return idXe;
	}
	public String getBienXe() {
		return bienXe;
	}
	public String getTenXe() {
		return tenXe;
	}
	public String getLoaiXe() {
		return loaiXe;
	}
	public String getHangSanXuat() {
		return hangSanXuat;
	}
	public String getNamSanXuat() {
		return namSanXuat;
	}
	public String getNgayBaoTri() {
		return ngayBaoTri;
	}
	public String getNhienLieu() {
		return nhienLieu;
	}
	public int getTrangThai() {
		return trangThai;
	}
	public int getGiaThue() {
		return giaThue;
	}
	
	// Constructor
	public Xe(String idXe, String bienXe, String tenXe, String loaiXe, String hangSanXuat, String namSanXuat,
			String ngayBaoTri, String nhienLieu, int trangThai, int giaThue) {
		this.idXe = idXe;
		this.bienXe = bienXe;
		this.tenXe = tenXe;
		this.loaiXe = loaiXe;
		this.hangSanXuat = hangSanXuat;
		this.namSanXuat = namSanXuat;
		this.ngayBaoTri = ngayBaoTri;
		this.nhienLieu = nhienLieu;
		this.trangThai = trangThai;
		this.giaThue = giaThue;
	}
}
