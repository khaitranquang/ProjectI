package project1.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PanelQuanLyKhachHangView extends JPanel{
	private InputKhachHangView inputKH;
	private ButtonKhachHangView btnKH;
	private TableKhachHangView tableKH;
	
	public PanelQuanLyKhachHangView() {
		setLayout(new BorderLayout());
		add(createTitlePanel(), BorderLayout.PAGE_START);
		add(createMainPanel(), BorderLayout.CENTER);
	}
	
	
	private JPanel createTitlePanel() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Quản lí khách hàng");
		label.setFont(new Font("Caribli", Font.BOLD, 18));
		label.setForeground(Color.BLUE);;
		panel.add(label);
		
		return panel;
	}
	
	private JPanel createMainPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 1, 5, 5));
		panel.add(tableKH = new TableKhachHangView());
		panel.add(createBottomPanel());
		
		return panel;
	}
	
	private JPanel createBottomPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 2, 5, 5));
		panel.setBorder(new EmptyBorder(15, 40, 0, 40));
		panel.add(inputKH = new InputKhachHangView());
		panel.add(btnKH = new ButtonKhachHangView());
		
		return panel;
	}


	public InputKhachHangView getInputKH() {
		return inputKH;
	}


	public ButtonKhachHangView getBtnKH() {
		return btnKH;
	}


	public TableKhachHangView getTableKH() {
		return tableKH;
	}
	
	
	
}
