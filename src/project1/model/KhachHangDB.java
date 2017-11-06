package project1.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class KhachHangDB implements KhachHangDAO{
	private final String dbURL = "jdbc:mysql://localhost:3306/quanlythuexe";
	private String user = "root";
	private String password = "1234";
	private Connection connection;
	
	//Get a connection - Connection to Database (MySQL)
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn= DriverManager.getConnection(dbURL, user, password);
			if(conn != null) {
				System.out.println("Connected!");
			}
		} catch (SQLException e) {
			System.out.println("Connectiong Failed");
			e.printStackTrace();
		}
		
		return conn;
	}
	
	@Override
	public ArrayList<KhachHang> getAllKhachHang() {
		connection = getConnection();
		Statement statement = null;
		ArrayList<KhachHang> listKH = new ArrayList<KhachHang>();
		
		try {
			String sql = "SELECT * FROM khachhang";
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while (result.next()) {
				String idKH    	= result.getString("idKhachHang");
				String tenKH    = result.getString("tenKhachHang");
				String soCMND 	= result.getString("SoCMND");
				String ngaySinh = result.getString("ngaySinh");
				String diaChi 	= result.getString("diaChi");
				String gioiTinh = result.getString("gioiTinh");
				String SDT 		= result.getString("SDT");
				String email    = result.getString("email");
				
				KhachHang kh= new KhachHang(idKH, tenKH, soCMND, ngaySinh,
													diaChi, gioiTinh, SDT, email);
				listKH.add(kh);
			}
			// Close connection
			result.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(new JDialog(), "Cant connect to database");
			e.printStackTrace();
		}
		finally {
			try {
				if(statement != null) statement.close();
				if(connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return listKH;
	}

	@Override
	public KhachHang getKhachHang(String idKhachHang) {
		KhachHang kh = null;
		connection = getConnection();
		PreparedStatement preStatement = null;
		String sql = "SELECT * FROM khachhang WHERE idKhachHang = ?";
		
		try {
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, idKhachHang);
			ResultSet result = preStatement.executeQuery();
			
			while (result.next()) {
				String tenKH    = result.getString("tenKhachHang");
				String soCMND 	= result.getString("SoCMND");
				String ngaySinh = result.getString("ngaySinh");
				String diaChi 	= result.getString("diaChi");
				String gioiTinh = result.getString("gioiTinh");
				String SDT 		= result.getString("SDT");
				String email    = result.getString("email");
				kh = new KhachHang(idKhachHang, tenKH, soCMND, ngaySinh, diaChi, gioiTinh, SDT, email);
			}
			// Close connection
			result.close();
			preStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JDialog(), "Can't connect to Database...");
		}
		finally {
			try {
				if(preStatement != null) preStatement.close();
				if(connection != null) connection.close();
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return kh;
	}

	@Override
	public void updateKhachHang(KhachHang khachHang, String idKHMoi, String tenKHMoi, String soCMNDMoi,
			String ngaySinhMoi, String diaChiMoi, String gioiTinhMoi, String SDTMoi, String emailMoi) {
		String idKH = khachHang.getIdKhachHang();
		connection = getConnection();
		PreparedStatement preStatement = null;
		try {
			String sql = "UPDATE khachhang SET idKhachHang=?, tenKhachHang=?, SoCMND=?, ngaySinh=?, "
					+ "diaChi=?, gioiTinh=?, SDT=?, email=? WHERE idKhachHang=?";
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, idKHMoi);
			preStatement.setString(2, tenKHMoi);
			preStatement.setString(3, soCMNDMoi);
			preStatement.setString(4, ngaySinhMoi);
			preStatement.setString(5, diaChiMoi);
			preStatement.setString(6, gioiTinhMoi);
			preStatement.setString(7, SDTMoi);
			preStatement.setString(8, emailMoi);
			preStatement.setString(9, idKH);
			
			int rows = preStatement.executeUpdate();
			if (rows > 0) System.out.println("This Customer has been update");
			
			// Close connection
			preStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JDialog(), "Can't connect to Database...");
		}
		finally {
			try {
				if(preStatement != null) preStatement.close();
				if(connection != null) connection.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void insertKhachHang(KhachHang khachHang) {
		String idKH 	= khachHang.getIdKhachHang();
		String tenKH	= khachHang.getTenKhachHang();
		String soCMND 	= khachHang.getSoCMND();
		String ngaySinh = khachHang.getNgaySinh();
		String diaChi 	= khachHang.getDiaChi();
		String gioiTinh = khachHang.getGioiTinh();
		String SDT 		= khachHang.getSDT();
		String email    = khachHang.getEmail();
		connection = getConnection();
		PreparedStatement preStatement = null;
		
		try {
			String sql = "INSERT INTO khachhang (idKhachHang, tenKhachHang, SoCMND, ngaySinh, "
					+ "diaChi, gioiTinh, SDT, email)"
					+ "VALUE (?,?,?,?,?,?,?, ?)";
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, idKH);
			preStatement.setString(2, tenKH);
			preStatement.setString(3, soCMND);
			preStatement.setString(4, ngaySinh);
			preStatement.setString(5, diaChi);
			preStatement.setString(6, gioiTinh);
			preStatement.setString(7, SDT);
			preStatement.setString(8, email);
			
			int rows = preStatement.executeUpdate();
			if (rows > 0) System.out.println("This customer has been inserted");
			
			// Close connections
			preStatement.close();
			connection.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(new JDialog(), "Can't connect to Database");
			e.printStackTrace();
		}
		finally {
			try {
				if(preStatement != null) preStatement.close();
				if(connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteKhachHang(KhachHang khachHang) {
		String idKhachHang = khachHang.getIdKhachHang();
		connection = getConnection();
		PreparedStatement preStatement = null;
		
		try {
			String sql = "DELETE FROM khachhang WHERE idKhachHang = ?";
			preStatement = (PreparedStatement) connection.prepareStatement(sql);
			preStatement.setString(1, idKhachHang);
			
			int rows = preStatement.executeUpdate();
			if(rows > 0) System.out.println("This customer has been deleted");
			
			// Close connection
			preStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JDialog(), "Can't connect to Database... \nCheck your internet...");
		}
		finally {
			try {
				if(preStatement != null) preStatement.close();
				if(connection != null) connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
