package project1.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class TableMuonTraView extends JPanel{
	private JTable table;
	private String titleCols[] = {"Mã Mượn Trả", "Mã Khách Hàng", "Mã Nhân Viên", "Ngày Mượn", "Ngày Hẹn Trả", "Tiền Cọc"};
	
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
