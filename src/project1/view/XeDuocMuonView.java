package project1.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class XeDuocMuonView  extends JPanel{
	private JLabel lbMaXe;
	private JLabel lbTenXe;
	private JLabel lbTienThue;
	
	public JLabel getLbMaXe() {
		return lbMaXe;
	}
	public void setLbMaXe(JLabel lbMaXe) {
		this.lbMaXe = lbMaXe;
	}
	public JLabel getLbTenXe() {
		return lbTenXe;
	}
	public void setLbTenXe(JLabel lbTenXe) {
		this.lbTenXe = lbTenXe;
	}
	public JLabel getLbTienThue() {
		return lbTienThue;
	}
	public void setLbTienThue(JLabel lbTienThue) {
		this.lbTienThue = lbTienThue;
	}
	
	public XeDuocMuonView(String maXe, String tenXe, String tienThue) {
		setBorder(new TitledBorder(""));
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipadx = 40;
		gbc.ipady = 20;
		add(createMaXePanel(maXe), gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.ipadx = 200;
		add(createTenXePanel(tenXe), gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.ipadx = 25;
		add(createTienThuePanel(tienThue), gbc);
	}
	
	private JPanel createMaXePanel(String maXe) {
		JPanel maXePanel = new JPanel (new FlowLayout());
		maXePanel.setPreferredSize(new Dimension(15, 20));
		maXePanel.add(lbMaXe = new JLabel(maXe), FlowLayout.LEFT);
		return maXePanel;
	}
	
	private JPanel createTenXePanel(String tenXe) {
		JPanel tenSachPanel = new JPanel (new FlowLayout());
		tenSachPanel.setPreferredSize(new Dimension(140, 20));
		tenSachPanel.add(lbTenXe = new JLabel(tenXe), FlowLayout.LEFT);
		return tenSachPanel;
	}
	
	private JPanel createTienThuePanel(String tienThue) {
		JPanel soLuongPanel = new JPanel (new FlowLayout());
		soLuongPanel.setPreferredSize(new Dimension(15, 20));
		soLuongPanel.add(lbTienThue = new JLabel(tienThue), FlowLayout.LEFT);
		return soLuongPanel;
	}
}
