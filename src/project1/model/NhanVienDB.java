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



public class NhanVienDB implements NhanVienDAO{
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
	public ArrayList<NhanVien> getAllNhanVien() {
		connection = getConnection();
		Statement statement = null;
		ArrayList<NhanVien> listNhanVien= new ArrayList<NhanVien>();
		
		try {
			String sql = "SELECT * FROM nhanvien";
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while (result.next()) {
				String idNhanVien 	= result.getString("idNhanVien");
				String tenNhanVien  = result.getString("tenNhanVien");
				String soCMND 		= result.getString("soCMND");
				String ngaySinh 	= result.getString("ngaySinh");
				String diaChi 		= result.getString("diaChi");
				String gioiTinh 	= result.getString("gioiTinh");
				String SDT 			= result.getString("sDT");
				
				NhanVien nhanhvien= new NhanVien(idNhanVien, tenNhanVien, soCMND, ngaySinh,
													diaChi, gioiTinh, SDT);
				listNhanVien.add(nhanhvien);
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
		
		return listNhanVien;
	}
	
	@Override
	public NhanVien getNhanVien(String idNhanVien) {
		NhanVien nhanvien = null;
		connection = getConnection();
		PreparedStatement preStatement = null;
		String sql = "SELECT * FROM nhanvien WHERE idNhanVien = ?";
		
		try {
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, idNhanVien);
			ResultSet result = preStatement.executeQuery();
			
			while (result.next()) {
				String tenNhanVien 	= result.getString("tenNhanVien");
				String soCMND 		= result.getString("soCMND");
				String diaChi 		= result.getString("diaChi");
				String ngaySinh 	= result.getString("ngaySinh");
				String gioiTinh 	= result.getString("gioiTinh");
				String SDT 			= result.getString("sDT");
				
				nhanvien= new NhanVien(idNhanVien, tenNhanVien, soCMND, ngaySinh,
										diaChi, gioiTinh, SDT);
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return nhanvien;
		}
	}
	
	@Override
	public void updateNhanVien (NhanVien nhanvien, String idNhanVienMoi, String tenNhanVienMoi, String soCMNDMoi,
			String ngaySinhMoi, String diaChiMoi, String gioiTinhMoi, String sDTMoi) {
		String idNhanVien = nhanvien.getIdNhanVien();
		connection = getConnection();
		PreparedStatement preStatement = null;
		try {
			String sql = "UPDATE nhanvien SET idNhanVien=?, tenNhanVien=?, soCMND=?, ngaySinh=?, "
					+ "							diaChi=?, gioiTinh=?, SDT=? WHERE idNhanVien=?";
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, idNhanVienMoi);
			preStatement.setString(2, tenNhanVienMoi);
			preStatement.setString(3, soCMNDMoi);
			preStatement.setString(4, ngaySinhMoi);
			preStatement.setString(5, diaChiMoi);
			preStatement.setString(6, gioiTinhMoi);
			preStatement.setString(7, sDTMoi);
			preStatement.setString(8, idNhanVien);
			
			int rows = preStatement.executeUpdate();
			if (rows > 0) System.out.println("This NhanVien has been update");
			
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
	public void insertNhanVien(NhanVien nhanvien) {
		String idNhanVien 	= nhanvien.getIdNhanVien();
		String tenNhanVien 	= nhanvien.getTenNhanVien();
		String soCMND 		= nhanvien.getSoCMND();
		String ngaySinh 	= nhanvien.getNgaySinh();
		String diaChi 		= nhanvien.getDiaChi();
		String gioiTinh 	= nhanvien.getGioiTinh();
		String SDT 			= nhanvien.getSDT();
		connection = getConnection();
		PreparedStatement preStatement = null;
		
		try {
			String sql = "INSERT INTO nhanvien (idNhanVien, tenNhanVien, soCMND, ngaySinh"
					+ "diaChi, gioiTinh, SDT)"
					+ "VALUE (?,?,?,?,?,?,?)";
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, idNhanVien);
			preStatement.setString(2, tenNhanVien);
			preStatement.setString(3, soCMND);
			preStatement.setString(4, ngaySinh);
			preStatement.setString(5, diaChi);
			preStatement.setString(6, gioiTinh);
			preStatement.setString(7, SDT);
			
			int rows = preStatement.executeUpdate();
			if (rows > 0) System.out.println("This nhanvien has been inserted");
			
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
	public void deleteNhanVien(NhanVien nhanvien) {
		String idNhanVien = nhanvien.getIdNhanVien();
		connection = getConnection();
		PreparedStatement preStatement = null;
		
		try {
			String sql = "DELETE FROM nhanvien WHERE idNhanVien = ?";
			preStatement = (PreparedStatement) connection.prepareStatement(sql);
			preStatement.setString(1, idNhanVien);
			
			int rows = preStatement.executeUpdate();
			if(rows > 0) System.out.println("This nhanvien has been deleted");
			
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