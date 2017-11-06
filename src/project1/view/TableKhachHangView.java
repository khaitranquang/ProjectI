package project1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import project1.model.KhachHang;

public class TableKhachHangView extends JPanel{
	public static final int TABLE_KH_WIDTH  = 1000;
	public static final int TABLE_KH_HEIGHT = 350;
	private JTable table;
	private String titleCols[] = {"Mã khách hàng", "Tên khách hàng", "Số CMND", "Ngày Sinh", "Địa chỉ", 
			"Giới tính", "Số ĐT", "Email"};
	
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	
	
	public TableKhachHangView() {
		setLayout(new BorderLayout());
		add(createTableXePanel(), BorderLayout.CENTER);
	}
	
	private JPanel createTableXePanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(5, 40, 5, 40));
		table = createTable();
				
		loadData(table);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(TABLE_KH_WIDTH, TABLE_KH_HEIGHT));
		panel.add(scroll);
		
		return panel;
		
	}
	private JTable createTable() {
		JTable table = new JTable();
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		return table;
	}
	
	// Load Data 
	private void loadData(JTable table) {
		SwingUtilities.invokeLater(new Runnable(){public void run(){
			String data[][] = null;
		    //Update the model here
			DefaultTableModel tableModel = new DefaultTableModel(data, titleCols) {
				@Override
				public boolean isCellEditable(int row, int column) {
					// TODO Auto-generated method stub
					return false;
				}
			};
			table.setModel(tableModel);		
		}});
	}
	
	// Update Model of table
	public void updateTable(ArrayList<KhachHang> list) {
		SwingUtilities.invokeLater(new Runnable(){public void run(){
		    //Update the model here
			String data[][] = convertData(list);
			DefaultTableModel tableModel = new DefaultTableModel(data, titleCols) {
				@Override
				public boolean isCellEditable(int row, int column) {
					// TODO Auto-generated method stub
					return false;
				}
			};
			table.setModel(tableModel);	
		}});
	}
	
	// Convert list of Book => Array 2D
	private String[][] convertData(ArrayList<KhachHang> list) {
		int size = list.size();
		String data[][] = new String[size][titleCols.length];
		for (int i = 0; i < size; i++) {
			KhachHang kh = list.get(i);
			data[i][0] = kh.getIdKhachHang();
			data[i][1] = kh.getTenKhachHang();
			data[i][2] = kh.getSoCMND();
			data[i][3] = kh.getNgaySinh();
			data[i][4] = kh.getDiaChi();
			data[i][5] = kh.getGioiTinh();
			data[i][6] = kh.getSDT();
			data[i][7] = kh.getEmail();
		}
		return data;
	}
}
