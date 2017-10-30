package project1.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class TableThongKeView extends JPanel{
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
		add(createTableTkeXePanel(), BorderLayout.CENTER);
	}
	
	private JPanel createTableTkeXePanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(5, 40, 5, 40));
		JScrollPane scroll = new JScrollPane(table = createTable());
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(titleCols);
		table.setModel(model);
		panel.add(scroll);
		
		return panel;
	}
	
	private JTable createTable() {
		JTable table = new JTable();
		return table;
	}
	
}
