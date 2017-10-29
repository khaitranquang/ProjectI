package project1.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PanelQuanLyMT_CTMTView extends JPanel{
	private InputMT_CTMTView input;
	private ButtonMT_CTMTView btn;
	private TableMuonTraView tableMT;
	private TableChiTietMuonTraView tableCTMT;
	
	public PanelQuanLyMT_CTMTView() {
		setLayout(new BorderLayout());
		add(createTitlePanel("Quản lí mượn trả"), BorderLayout.PAGE_START);
		add(createMainPanel(), BorderLayout.CENTER);
	}
	
	private JPanel createTitlePanel(String title) {
		JPanel panel = new JPanel();
		JLabel label = new JLabel(title);
		label.setFont(new Font("Caribli", Font.BOLD, 18));
		label.setForeground(Color.BLUE);;
		panel.add(label);
		
		return panel;
	}
	
	private JPanel createMainPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 1, 5, 5));
		panel.add(tableMT = new TableMuonTraView());
		panel.add(createBottomPanel());
		
		return panel;
	}
	
	private JPanel createBottomPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(5, 40, 0, 40));
		panel.add(createCTMTInputPanel(), BorderLayout.CENTER);
		panel.add(btn = new ButtonMT_CTMTView(), BorderLayout.EAST);
		
		return panel;
	}
	
	private JPanel createCTMTInputPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 1));
		panel.setBorder(new EmptyBorder(0, 0, 0, 40));
		panel.add(createCTMTPanel());
		panel.add(input = new InputMT_CTMTView());
		
		return panel;
	}
	
	private JPanel createCTMTPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(createTitlePanel("Chi tiết mượn trả"), BorderLayout.PAGE_START);
		panel.add(tableCTMT = new TableChiTietMuonTraView(), BorderLayout.CENTER);
		
		return panel;
	}

	public InputMT_CTMTView getInput() {
		return input;
	}

	public ButtonMT_CTMTView getBtn() {
		return btn;
	}

	public TableMuonTraView getTableMT() {
		return tableMT;
	}

	public TableChiTietMuonTraView getTableCTMT() {
		return tableCTMT;
	}
	
	

}
