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


public class XeDB implements XeDAO{
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
	public ArrayList<Xe> getAllXe() {
		connection = getConnection();
		Statement statement  = null;
		ArrayList<Xe> listXe = new ArrayList<Xe>();
		
		try {
			String sql = "SELECT * FROM xe";
			statement  = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while (result.next()) {
				String idXe        = result.getString("idXe");
				String bienXe      = result.getString("bienXe");
				String tenXe       = result.getString("tenXe");
				String loaiXe      = result.getString("loaiXe");
				String hangSanXuat = result.getString("hangSanXuat");
				String namSanXuat  = result.getString("namSanXuat");
				String ngayBaoTri  = result.getString("ngayBaoTri");
				String nhienLieu   = result.getString("nhienLieu");
				int trangThai      = result.getInt("trangThai");
				int giaThue        = result.getInt("GiaThue");
				
				Xe xe = new Xe(idXe, bienXe, tenXe, loaiXe, hangSanXuat, namSanXuat, ngayBaoTri, nhienLieu, trangThai, giaThue);
				listXe.add(xe);
			}
			// Close connection
			result.close();
			statement.close();
			connection.close();
		} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(new JDialog(), "Can't connect to database...");
			e.printStackTrace();
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

	@Override
	public Xe getXe(String idXe) {
		Xe xe = null;
		connection = getConnection();
		PreparedStatement preStatement = null;
		String sql = "SELECT * FROM xe WHERE idXe = ?";
		
		try {
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, idXe);
			ResultSet result = preStatement.executeQuery();
			
			while (result.next()) {
				String bienXe      = result.getString("bienXe");
				String tenXe       = result.getString("tenXe");
				String loaiXe      = result.getString("loaiXe");
				String hangSanXuat = result.getString("hangSanXuat");
				String namSanXuat  = result.getString("namSanXuat");
				String ngayBaoTri  = result.getString("ngayBaoTri");
				String nhienLieu   = result.getString("nhienLieu");
				int trangThai      = result.getInt("trangThai");
				int giaThue        = result.getInt("GiaThue");
				
				xe = new Xe(idXe, bienXe, tenXe, loaiXe, hangSanXuat, 
							namSanXuat, ngayBaoTri, nhienLieu, trangThai, giaThue);
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
		
		return xe;
	}

	@Override
	public void updateXe(Xe xe, String idXeMoi, String bienXeMoi, String tenXeMoi, String loaiXeMoi,
			String hangSanXuatMoi, String namSanXuatMoi, String ngayBaoTriMoi, String nhienLieuMoi, int trangThaiMoi,
			int giaThueMoi) {
		String idXe = xe.getIdXe();
		connection = getConnection();
		PreparedStatement preStatement = null;
		try {
			String sql = "UPDATE xe SET idXe=?, bienXe=?, tenXe=?, loaiXe=?, hangSanXuat=?, "
					+ "namSanXuat=?, ngayBaoTri=?, nhienLieu=?, trangThai=?, GiaThue=? WHERE idXe=?";
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, idXeMoi);
			preStatement.setString(2, bienXeMoi);
			preStatement.setString(3, tenXeMoi);
			preStatement.setString(4, loaiXeMoi);
			preStatement.setString(5, hangSanXuatMoi);
			preStatement.setString(6, namSanXuatMoi);
			preStatement.setString(7, ngayBaoTriMoi);
			preStatement.setString(8, nhienLieuMoi);
			preStatement.setInt(9, trangThaiMoi);
			preStatement.setInt(10, giaThueMoi);
			preStatement.setString(11, idXe);
			
			int rows = preStatement.executeUpdate();
			if (rows > 0) System.out.println("This Xe has been update");
			
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
				e.printStackTrace();
			}
		}
	}

	@Override
	public void insertXe(Xe xe) {
		String idXe        = xe.getIdXe();
		String bienXe      = xe.getBienXe();
		String tenXe       = xe.getTenXe();
		String loaiXe      = xe.getLoaiXe();
		String hangSanXuat = xe.getHangSanXuat();
		String namSanXuat  = xe.getNamSanXuat();
		String ngayBaoTri  = xe.getNgayBaoTri();
		String nhienLieu   = xe.getNhienLieu();
		int trangThai      = xe.getTrangThai();
		int giaThue        = xe.getGiaThue();
		connection = getConnection();
		PreparedStatement preStatement = null;
		
		try {
			String sql = "INSERT INTO xe (idXe, bienXe, tenXe, loaiXe, hangSanXuat,"
					+ " namSanXuat, ngayBaoTri, nhienLieu, trangThai, GiaThue)"
                    + "VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, idXe);
			preStatement.setString(2, bienXe);
			preStatement.setString(3, tenXe);
			preStatement.setString(4, loaiXe);
			preStatement.setString(5, hangSanXuat);
			preStatement.setString(6, namSanXuat);
			preStatement.setString(7, ngayBaoTri);
			preStatement.setString(8, nhienLieu);
			preStatement.setInt(9, trangThai);
			preStatement.setInt(10, giaThue);
			
			int rows = preStatement.executeUpdate();
			if (rows > 0) System.out.println("This xe has been inserted");
			
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
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteXe(Xe xe) {
		String idXe = xe.getIdXe();
		connection = getConnection();
		PreparedStatement preStatement = null;
		
		try {
			String sql = "DELETE FROM xe WHERE idXe = ?";
			preStatement = (PreparedStatement) connection.prepareStatement(sql);
			preStatement.setString(1, idXe);
			
			int rows = preStatement.executeUpdate();
			if(rows > 0) System.out.println("This xe has been deleted");
			
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
				e.printStackTrace();
			}
		}
	}

	@Override
	public ArrayList<ArrayList<String>> thongKeXe(String colName) {
		connection = getConnection();
		Statement statement = null;
		ArrayList<ArrayList<String>> arrResult = new ArrayList<ArrayList<String>>();
		
		try {
			String sql = "SELECT " + colName + ", count(" + colName +")" + " FROM xe group by " + colName;
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

	@Override
	public void updateXe(Xe xe, int trangThaiMoi) {
		String maXe = xe.getIdXe();
		connection = getConnection();
		PreparedStatement preStatement = null;
		try {
			String sql = "UPDATE xe SET trangThai=? WHERE idXe=?";
			preStatement = connection.prepareStatement(sql);
			preStatement.setInt(1, trangThaiMoi);
			preStatement.setString(2, maXe);
			
			
			int rows = preStatement.executeUpdate();
			if (rows > 0) System.out.println("Xe vua duoc cap nhat trang thai");
			
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
}
