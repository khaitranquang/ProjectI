package project1.test;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import project1.controller.LoginController;
import project1.view.MainUI;

public class DemoMain {
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				 try {
					 UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					 MainUI mainUI = new  MainUI();
					 LoginController loginController = new LoginController(mainUI);
				 } 
				 catch (ClassNotFoundException | InstantiationException | 
						IllegalAccessException | UnsupportedLookAndFeelException ex) {
					 System.out.println("Failed");
					 MainUI mainUI = new  MainUI();
					 LoginController loginController = new LoginController(mainUI);
				 }
			}
		});
	}
}
