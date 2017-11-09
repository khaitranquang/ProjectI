package project1.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TKXeInformation extends JPanel{
	private JLabel lbCurrentDate;
	private JLabel lbTitle;
	private TableTKXeView tableTKXeView;
	
	public JLabel getLbCurrentDate() {
		return lbCurrentDate;
	}
	public JLabel getLbTitle() {
		return lbTitle;
	}
	public TableTKXeView getTableTKXeView() {
		return tableTKXeView;
	}
	
	public TKXeInformation() {
		setLayout(new BorderLayout());
		add(createDatePanel (), BorderLayout.PAGE_START);
		add(createTablePanel(), BorderLayout.CENTER);
	}
	
	private JPanel createDatePanel() {
		JPanel datePanel = new JPanel(new GridLayout(1, 2, 10, 10));
		datePanel.add(new JLabel(""));
		lbCurrentDate = new JLabel();
		datePanel.add(lbCurrentDate);
		return datePanel;
	}
	
	private JPanel createTablePanel() {
		JPanel panel = new JPanel(new BorderLayout());
		lbTitle = new JLabel ("THỐNG KÊ");
		tableTKXeView = new TableTKXeView();
		panel.add(lbTitle, BorderLayout.PAGE_START);
		panel.add(tableTKXeView, BorderLayout.CENTER);
		return panel;
	}
}
