package project1.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class AccountDB {
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
	
	public String getPassAdmin() {
		connection = getConnection();
		Statement statement = null;
		String passAdmin = "";
	
		try {
			String sql = "SELECT * FROM admin";
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery(sql);
			
			while (result.next()) {
				passAdmin = result.getString("pass");
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
		
		return passAdmin;
	}
	
	public String getPassEmpl (String idNhanVien) {
		String passEmpl = "";
		connection = getConnection();
		PreparedStatement preStatement = null;
		String sql = "SELECT * FROM taikhoannhanvien WHERE idNhanVien = ?";
		try {
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, idNhanVien);
			ResultSet result = preStatement.executeQuery();
			
			while (result.next()) {
				passEmpl = result.getString("pass");
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
		
		return passEmpl;
	}
	
	/* Change Password from DB */
	public void changePassAdminFromDB(String newPass) {
		connection = getConnection();
		PreparedStatement preStatement = null;
		
		try {
			String sql = "UPDATE admin SET pass=?";
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, newPass);
			
			int rows = preStatement.executeUpdate();
			if (rows > 0) System.out.println("This pass has been changed");
			
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
	
	public void changePassEmplFromDB(String accountEmpl, String newPass) {
		connection = getConnection();
		PreparedStatement preStatement = null;
		
		try {
			String sql = "UPDATE taikhoannhanvien SET pass=? where idNhanVien=?";
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, newPass);
			preStatement.setString(2, accountEmpl);
			
			int rows = preStatement.executeUpdate();
			if (rows > 0) System.out.println("This pass has been changed");
			
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
	
	/* insert account for employment */
	public void insertAccEmpl (String maNV) {
		connection = getConnection();
		PreparedStatement preStatement = null;
		
		try {
			String sql = "INSERT INTO taikhoannhanvien (idNhanVien, pass)"
                    + "VALUE (?, ?)";
			preStatement = connection.prepareStatement(sql);
			preStatement.setString(1, maNV);
			preStatement.setString(2, maNV.toLowerCase());
			
			int rows = preStatement.executeUpdate();
			if (rows > 0) System.out.println("This accountEmpl has been inserted");
			
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
	
	/* Delete account of Employment */
	public void deleteAccEmpl(String maNV) {
		connection = getConnection();
		PreparedStatement preStatement = null;
		
		try {
			String sql = "DELETE FROM taikhoannhanvien WHERE idNhanVien = ?";
			preStatement = (PreparedStatement) connection.prepareStatement(sql);
			preStatement.setString(1, maNV);
			
			int rows = preStatement.executeUpdate();
			if(rows > 0) System.out.println("This accountEmpl has been deleted");
			
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
	
	/* Initial admin */
	public void initAdmin() {
		Connection conn = getConnection();
		PreparedStatement preStatement = null;
		
		try {
			String sql = "INSERT INTO admin (admin, pass) VALUES (?, ?)";
			preStatement = conn.prepareStatement(sql);
			preStatement.setString(1, "admin");
			preStatement.setString(2, "1234");
			
			int rows = preStatement.executeUpdate();
			if (rows > 0) System.out.println("Init admin");
			
			preStatement.close();
			conn.close();
		} catch (Exception e) {
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
}
