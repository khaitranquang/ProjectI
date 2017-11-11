package project1.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class TableThongKeView extends JPanel{
	public static final int TABLE_TK_WIDTH = 500;
	public static final int TABLE_TK_HEIGHT = 250;
	private JTable table;
	private String valThongKe = "";
	private String titleCols[] = {"TT", valThongKe, "Số lượng"};
	
	public JTable getTable() {
		return table;
	}
	public void setTable(JTable table) {
		this.table = table;
	}
	public String getValThongKe() {
		return valThongKe;
	}
	public void setValThongKe(String valThongKe) {
		this.valThongKe = valThongKe;
	}
	
	
	public TableThongKeView() {
		setLayout(new BorderLayout());
		add(createThongKePanel(), BorderLayout.CENTER);
	}
	
	private JPanel createThongKePanel() {
		JPanel thongKePanel = new JPanel();
		thongKePanel.add(createTablePanel());
		return thongKePanel;
	}
	
	// Create table Panel
	private JPanel createTablePanel() {
		JPanel panel = new JPanel(new BorderLayout());
		table = createTable();
		//Load data...?????????????
		loadData(table);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(TABLE_TK_WIDTH, TABLE_TK_HEIGHT));
		panel.add(scroll, BorderLayout.CENTER);
		
		return panel;
	}
	
	// Create table
	private JTable createTable() {
		JTable table = new JTable();
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);
		table.getTableHeader().setReorderingAllowed(false);
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
	
	// Update Model of Table
	public void updateTable(String[][] listResult, int mode) {
		SwingUtilities.invokeLater(new Runnable(){public void run(){
		    //Update the model here
			String data[][] = listResult;
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
}
