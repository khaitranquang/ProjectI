package project1.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TableChiTietMuonTraView extends JPanel{
	private JTable table;
	private String titleCols[] = {"Mã mượn trả", "Mã xe", "Ngày trả", "Tiền thuê", "Tiền phạt", "Tiền khuyến mại"};
	
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	
	
	public TableChiTietMuonTraView() {
		setLayout(new BorderLayout());
		add(createTableXePanel(), BorderLayout.CENTER);
	}
	
	private JPanel createTableXePanel() {
		JPanel panel = new JPanel(new BorderLayout());
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
