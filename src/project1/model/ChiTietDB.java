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

public class ChiTietDB implements ChitietDAO{
	private final String dbURL = "jdbc:mysql://localhost:3306/quanlythuexe";
	private String user = "root";
	private String password = "1234";
	private Connection connection;
	
	/* Get a connection - Connect to Database (MySQL) */
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(dbURL, user, password);
			if(conn != null) {
				System.out.println("Connected!");
			}	
		} 
		catch (SQLException e) {
			System.out.println("Connecting Failed!");
			e.printStackTrace();
		}
		
		return conn;
	}
	
	@Override
	public ArrayList<ChiTiet> getAllChiTietWithID(String maMT) {
		connection = getConnection();
		ArrayList<ChiTiet> listDetail = new ArrayList<ChiTiet>();
		PreparedStatement preStatement = null;
		String sql = "SELECT * FROM chitietmuontra WHERE idMuonTra = ?";
		
		
		try {
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, maMT);
			ResultSet result = preStatement.executeQuery();
			
			while (result.next()) {
				String maXe       = result.getString("idXe");
				int tienThue      = result.getInt("tienThue");
				String ngayTra    = result.getString("ngayTra");
				int soTienPhat    = result.getInt("tienPhat");
				int tienKhuyenMai = result.getInt("tienKhuyenMai");
				
				ChiTiet detail = new ChiTiet(maMT, maXe, tienThue, ngayTra, soTienPhat, tienKhuyenMai);
				listDetail.add(detail);
			}
			// Close connection
			result.close();
			preStatement.close();
			connection.close();			
		} 
		catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JDialog(), "Can't connect to database...");
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
		
		return listDetail;
	}

	@Override
	public ChiTiet getChiTiet(String maMT, String maXeMuon) {
		ChiTiet detail = null;
		connection = getConnection();
		PreparedStatement preStatement = null;
		String sql = "SELECT * FROM chitietmuontra WHERE (idMuonTra=? AND idXe=?)";
		try {
			preStatement = (PreparedStatement) connection.prepareStatement(sql);
			preStatement.setString(1, maMT);
			preStatement.setString(2, maXeMuon);
			ResultSet result = preStatement.executeQuery();
			
			while (result.next()) {
				int tienThue      = result.getInt("tienThue");
				String ngayTra    = result.getString("ngayTra");
				int soTienPhat    = result.getInt("tienPhat");
				int tienKhuyenMai = result.getInt("tienKhuyenMai");
				
				detail = new ChiTiet(maMT, maXeMuon, tienThue, ngayTra, soTienPhat, tienKhuyenMai);
			}
			// Close connection
			result.close();
			preStatement.close();
			connection.close();			
		} 
		catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JDialog(), "Can't connect to database...");
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
		
		return detail;
	}

	@Override
	public void insertChiTiet(ChiTiet chiTiet) {
		String maMT       = chiTiet.getMaMT();
		String maXe       = chiTiet.getMaXe();
		int tienThue      = chiTiet.getTienThue();
		String ngayTra    = chiTiet.getNgayTra();
		int soTienPhat    = chiTiet.getTienPhat();
		int tienKhuyenMai = chiTiet.getTienKhuyenMai();
		
		connection = getConnection();
		java.sql.PreparedStatement preStatement = null;
		try {
			String sql = "INSERT INTO chitietmuontra (idMuonTra, idXe, ngayTra, tienThue, tienPhat, tienKhuyenMai)"
                    + "VALUE (?, ?, ?, ?, ?, ?)";
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, maMT);
			preStatement.setString(2, maXe);
			preStatement.setString(3, ngayTra);
			preStatement.setInt(4, tienThue);
			preStatement.setInt(5, soTienPhat);
			preStatement.setInt(6, tienKhuyenMai);
						
			int rows = preStatement.executeUpdate();
			if (rows > 0) System.out.println("Chi tiet da duoc them");
			
			// CLose connections
			preStatement.close();
			connection.close();
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(new JDialog(), "Can't connect to database...");
			e.printStackTrace();
		}
		finally {
			try {
				if(preStatement != null) preStatement.close();
				if(connection != null) connection.close();	
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void updateChiTiet(ChiTiet chiTiet, String ngayTraMoi, int tienPhatMoi, int khuyenMaiMoi) {
		String maMT = chiTiet.getMaMT();
		String maXe = chiTiet.getMaXe();
		connection = getConnection();
		PreparedStatement preStatement = null;
		try {
			String sql = "UPDATE chitietmuontra SET ngayTra=?, tienPhat=?, tienKhuyenMai=? WHERE (idMuonTra=? AND idXe=?)";
			preStatement = (PreparedStatement) connection.prepareStatement(sql);
			preStatement.setString(1, ngayTraMoi);
			preStatement.setDouble(2, tienPhatMoi);
			preStatement.setInt(3, khuyenMaiMoi);
			preStatement.setString(4, maMT);
			preStatement.setString(5, maXe);
			
			
			int rows = preStatement.executeUpdate();
			if (rows > 0) System.out.println("Chi tiet Thue Xe duoc update");
			
			// Close connection
			preStatement.close();
			connection.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JDialog(), "Can't connect to database...");
		}
		finally {
			try {
				if(preStatement != null) preStatement.close();
				if(connection != null) connection.close();	
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public double tinhTongPhat(String maMT) {
		double tongTienPhat = 0.0;
		connection = getConnection();
		PreparedStatement preStatement = null;
		String sql = "SELECT sum(tienPhat) FROM chitietmuontra WHERE idMuonTra=?";
		try {
			preStatement = (PreparedStatement) connection.prepareStatement(sql);
			preStatement.setString(1, maMT);
			
			ResultSet result = preStatement.executeQuery();
			
			while (result.next()) {
				
				tongTienPhat = result.getDouble("sum(tienPhat)");
			}
			// Close connection
			result.close();
			preStatement.close();
			connection.close();			
		} 
		catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JDialog(), "Can't connect to database...");
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
		
		return tongTienPhat;
	}

	@Override
	public void deleteChiTiet(String maMT) {
		connection = getConnection();
		PreparedStatement preStatement = null;
		
		try {
			String sql = "DELETE FROM chitietmuontra WHERE idMuonTra = ?";
			preStatement = (PreparedStatement) connection.prepareStatement(sql);
			preStatement.setString(1, maMT);
			
			int rows = preStatement.executeUpdate();
			if(rows > 0) System.out.println("This detail has been deleted");
			
			//Close connection
			preStatement.close();
			connection.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JDialog(), "Can't connect to database... \n Check your internet...");
		}
		finally {
			try {
				if(preStatement != null) preStatement.close();
				if(connection != null) connection.close();	
			} 
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public double tinhTongKhuyenMai(String maMT) {
		double tongKhuyenMai = 0.0;
		connection = getConnection();
		PreparedStatement preStatement = null;
		String sql = "SELECT sum(tienKhuyenMai) FROM chitietmuontra WHERE idMuonTra=?";
		try {
			preStatement = (PreparedStatement) connection.prepareStatement(sql);
			preStatement.setString(1, maMT);
			
			ResultSet result = preStatement.executeQuery();
			
			while (result.next()) {
				
				tongKhuyenMai = result.getDouble("sum(tienKhuyenMai)");
			}
			// Close connection
			result.close();
			preStatement.close();
			connection.close();			
		} 
		catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JDialog(), "Can't connect to database...");
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
		
		return tongKhuyenMai;
	}
	
	public ArrayList<String> getListXeIsLoan() {
		connection = getConnection();
		Statement statement = null;
		ArrayList<String> listXe = new ArrayList<String>();
		
		try {
			String sql = "select distinct idXe from chitietmuontra";
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while (result.next()) {
				String maSach  = result.getString("idXe");
				listXe.add(maSach);
			}
			// Close connection
			result.close();
			statement.close();
			connection.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(new JDialog(), "Can't connect to database...");
			
		}
		finally {
			try {
				if(statement  != null) statement.close();
				if(connection != null) connection.close();	
			} 
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return listXe;
	}

}
