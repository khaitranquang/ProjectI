package project1.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PanelQuanLyNhanVienView extends JPanel{
	private InputNhanVienView inputNV;
	private TableNhanVienView tableNV;
	private ButtonNhanVienView btnNV;
	
	public PanelQuanLyNhanVienView() {
		setLayout(new BorderLayout());
		add(createTitlePanel(), BorderLayout.PAGE_START);
		add(createMainPanel(), BorderLayout.CENTER);
	}
	
	private JPanel createTitlePanel() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Quản lí nhân viên");
		label.setFont(new Font("Caribli", Font.BOLD, 18));
		label.setForeground(Color.BLUE);;
		panel.add(label);
		
		return panel;
	}
	
	private JPanel createMainPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 1, 5, 5));
		panel.add(tableNV = new TableNhanVienView());
		panel.add(createBottomPanel());
		
		return panel;
	}
	
	private JPanel createBottomPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new EmptyBorder(15, 40, 0, 40));
		panel.add(inputNV = new InputNhanVienView(), BorderLayout.CENTER);
		panel.add(btnNV = new ButtonNhanVienView(), BorderLayout.EAST);
		
		return panel;
	}

	public InputNhanVienView getInputNV() {
		return inputNV;
	}

	public TableNhanVienView getTableNV() {
		return tableNV;
	}

	public ButtonNhanVienView getBtnNV() {
		return btnNV;
	}

}
