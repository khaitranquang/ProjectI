package project1.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class TableXeView extends JPanel{
	
	private JTable table;
	private String[] titleCols = {"Mã Xe", "Biển Xe", "Tên Xe", "Loại Xe", "Hãng Sản Xuất", 
			"Năm Sản Xuất", "Ngày Bảo Trì", "Nhiên Liệu", "Trạng Thái", "Giá Thuê"};
	
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	
	
	public TableXeView() {
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
