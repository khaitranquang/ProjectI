package project1.test;

import javax.swing.JFrame;

import project1.view.ButtonXeView;
import project1.view.TableXeView;

public class TestUI extends JFrame{
	private ButtonXeView buttonXeView;
	
	private TableXeView tableXeView = new TableXeView();
	public TestUI() {
		buttonXeView = new ButtonXeView();
		add(buttonXeView);
		setSize(500, 500);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new TestUI();
	}
}
