package project1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import project1.model.MuonXe;

public class TableMuonTraView extends JPanel{
	private JTable table;
	public static final int TABLE_RENT_WIDTH = 1095;
	public static final int TABLE_RENT_HEIGHT = 300;
	private String titleItem[] = {"Mã mượn trả", "Mã khách hàng", "Mã nhân viên", "Ngày mượn", "Ngày hẹn trả", "Tiền cọc"};
	
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}
	
	public TableMuonTraView() {
		setLayout(new BorderLayout());
		add(createTableXePanel(), BorderLayout.CENTER);
	}
	
	private JPanel createTableXePanel() {
		JPanel panel = new JPanel();
		panel.add(createTablePanel());
		
		return panel;
		
	}
	private JPanel createTablePanel() {
		JPanel panel = new JPanel(new BorderLayout());
		table = createTable();
		//Load data...?????????????
		loadData(table);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(TABLE_RENT_WIDTH, TABLE_RENT_HEIGHT));
		panel.add(scroll, BorderLayout.CENTER);
		
		return panel;
	}
	
	private JTable createTable() {
		JTable table = new JTable();
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		return table;
	}
	
	// Load data
	private void loadData(JTable table) {
		SwingUtilities.invokeLater(new Runnable(){public void run(){
			String data[][] = null;
		    //Update the model here
			DefaultTableModel tableModel = new DefaultTableModel(data, titleItem) {
				@Override
				public boolean isCellEditable(int row, int column) {
					// TODO Auto-generated method stub
					return false;
				}
			};
			table.setModel(tableModel);		
		}});
	}
	
	// Update Model of Table
	public void updateTable(ArrayList<MuonXe> list) {
		SwingUtilities.invokeLater(new Runnable(){public void run(){
		    //Update the model here
			String data[][] = convertData(list);
			DefaultTableModel tableModel = new DefaultTableModel(data, titleItem) {
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
	private String[][] convertData(ArrayList<MuonXe> list) {
		int size = list.size();
		String data[][] = new String[size][titleItem.length];
		for (int i = 0; i < size; i++) {
			MuonXe muonXe = list.get(i);
			data[i][0] = muonXe.getMaMT();
			data[i][1] = muonXe.getMaKH();
			data[i][2] = muonXe.getMaNV();
			data[i][3] = muonXe.getNgayMuon();
			data[i][4] = muonXe.getNgayHenTra();
			data[i][5] = muonXe.getTienCoc() + "";
		}
		return data;
	}
}
