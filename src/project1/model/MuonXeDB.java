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

public class MuonXeDB implements MuonXeDAO{
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
	public ArrayList<MuonXe> getAllMuonXe() {
		connection = getConnection();
		Statement statement = null;
		ArrayList<MuonXe> listMuonXe = new ArrayList<MuonXe>();
		
		try {
			String sql = "SELECT * FROM muontra";
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while (result.next()) {
				String maMT       = result.getString("idMuonTra");
				String maKH       = result.getString("idKhachHang");
				String maNV       = result.getString("idNhanVien");
				String ngayMuon   = result.getString("ngayMuon");
				String ngayHenTra = result.getString("ngayHenTra");
				int tienCoc       = result.getInt("tienCoc");
				
				MuonXe muonXe = new MuonXe(maMT, maKH, maNV, ngayMuon, ngayHenTra, tienCoc);
				listMuonXe.add(muonXe);
			}
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
		
		return listMuonXe;
	}

	@Override
	public MuonXe getMuonXe(String maMT) {
		MuonXe muonXe = null;
		connection = getConnection();
		PreparedStatement preStatement = null;
		String sql = "SELECT * FROM muontra WHERE idMuonTra = ?";
		
		try {
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, maMT);
			ResultSet result = preStatement.executeQuery();
			
			while (result.next()) {
				String maKH       = result.getString("idKhachHang");
				String maNV       = result.getString("idNhanVien");
				String ngayMuon   = result.getString("ngayMuon");
				String ngayHenTra = result.getString("ngayHenTra");
				int tienCoc       = result.getInt("tienCoc");
				muonXe = new MuonXe(maMT, maKH, maNV, ngayMuon, ngayHenTra, tienCoc);
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
		
		return muonXe;
	}

	@Override
	public void insertMuonXe(MuonXe muonXe) {
		String maMT       = muonXe.getMaMT();
		String maKH       = muonXe.getMaKH();
		String maNV       = muonXe.getMaNV();
		String ngayMuon   = muonXe.getNgayMuon();
		String ngayHenTra = muonXe.getNgayHenTra();
		int    tienCoc    = muonXe.getTienCoc();
		
		connection = getConnection();
		PreparedStatement preStatement = null;
		
		try {
			String sql = "INSERT INTO muontra (idMuonTra, idKhachHang, idNhanVien, ngayMuon, ngayHenTra, tienCoc)"
                    + "VALUE (?, ?, ?, ?, ?, ?)";
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, maMT);
			preStatement.setString(2, maKH);
			preStatement.setString(3, maNV);
			preStatement.setString(4, ngayMuon);
			preStatement.setString(5, ngayHenTra);
			preStatement.setInt(6, tienCoc);
			
			int rows = preStatement.executeUpdate();
			if (rows > 0) System.out.println("Muon tra da duoc them");
			
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
	public void editMuonXe(MuonXe muonXe, String maKHMoi, String maNVMoi, String ngayHenTraMoi, int tienCocMoi) {
		String maMT = muonXe.getMaMT();
		connection = getConnection();
		PreparedStatement preStatement = null;
		
		try {
			String sql = "UPDATE muontra SET idKhachHang=?, idNhanVien=?, ngayHenTra=?, tienCoc=? WHERE idMuonTra=?";
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, maKHMoi);
			preStatement.setString(2, maNVMoi);
			preStatement.setString(3, ngayHenTraMoi);
			preStatement.setInt(4, tienCocMoi);
			preStatement.setString(5, maMT);
			
			int rows = preStatement.executeUpdate();
			if (rows > 0) System.out.println("Muon tra duoc cap nhat");
			
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
	public ArrayList<ArrayList<String>> thongKeMuonTra(String colName) {
		connection = getConnection();
		Statement statement = null;
		ArrayList<ArrayList<String>> arrResult = new ArrayList<ArrayList<String>>();
		
		try {
			String sql = "SELECT " + colName + ", count(" + colName +")" + " FROM muontra group by " + colName;
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while (result.next()) {
				String column = result.getString(colName);
				int   soLuong = result.getInt("count(" + colName +")");
				
				ArrayList<String> record = new ArrayList<String>();
				record.add(column);
				record.add(Integer.toString(soLuong));
				arrResult.add(record);
				//record.clear();
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
		
		return arrResult;
	}

}
