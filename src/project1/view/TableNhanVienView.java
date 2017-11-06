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

import project1.model.NhanVien;

public class TableNhanVienView extends JPanel{
	public static final int TABLE_NV_WIDTH  = 1000;
	public static final int TABLE_NV_HEIGHT = 350;
	private JTable table;
	private String titleCols[] = {"Mã nhân viên", "Tên nhân viên", "Số CMND", "Ngày sinh", "Địa chỉ", 
			"Giới tính", "Số ĐT"};
	
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}

	public TableNhanVienView() {
		setLayout(new BorderLayout());
		add(createTableXePanel(), BorderLayout.CENTER);
	}
	
	private JPanel createTableXePanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(5, 40, 5, 40));
		table = createTable();
		
		loadData(table);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(TABLE_NV_WIDTH, TABLE_NV_HEIGHT));
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
	public void updateTable(ArrayList<NhanVien> list) {
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
	private String[][] convertData(ArrayList<NhanVien> list) {
		int size = list.size();
		String data[][] = new String[size][titleCols.length];
		for (int i = 0; i < size; i++) {
			NhanVien nv = list.get(i);
			data[i][0] = nv.getIdNhanVien();
			data[i][1] = nv.getTenNhanVien();
			data[i][2] = nv.getSoCMND();
			data[i][3] = nv.getNgaySinh();
			data[i][4] = nv.getDiaChi();
			data[i][5] = nv.getGioiTinh();
			data[i][6] = nv.getSDT();
		}
		return data;
	}
}
