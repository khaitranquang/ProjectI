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
//					 MainUI mainUI = new  MainUI();
//					 
//						new AddXeController(mainUI);
//						new EditXeController(mainUI);
//						new DeleteXeController(mainUI);
//						new AddNhanVienController(mainUI);
//						new EditNhanVienController(mainUI);
//						new DeleteNhanVienController(mainUI);
//						new AddKhachHangController(mainUI);
//						new EditKhachHangController(mainUI);
//						new DeleteKhachHangController(mainUI);
//						new SearchXeController(mainUI);
//						new TKXeController(mainUI);
//						//new PrintSearchXeController(mainUI);
//						new SearchKHController(mainUI);
//						//new PrintSearchKHController(mainUI);
//						new TKKHController(mainUI);
//						new SearchNVController(mainUI);
//						//new PrintSearchNVController(mainUI);
//						new TKNVController(mainUI);
//						new AddThueXeController(mainUI);
//						new EditRentController(mainUI);
//						new ShowDetailInformation(mainUI);
//						new TKRentController(mainUI);
//						new SearchDetailController(mainUI);
//						new PrintSearchController(mainUI);
					 MainUI mainUI = new  MainUI();
					 LoginController loginController = new LoginController(mainUI);
				 } 
				 catch (ClassNotFoundException | InstantiationException | 
						IllegalAccessException | UnsupportedLookAndFeelException ex) {
					 System.out.println("Failed");
//					 MainUI mainUI = new  MainUI();
//					 new AddXeController(mainUI);
//					 new EditXeController(mainUI);
//					 new DeleteXeController(mainUI);
//					 new AddNhanVienController(mainUI);
//					 new EditNhanVienController(mainUI);
//					 new DeleteNhanVienController(mainUI);
//					 new AddKhachHangController(mainUI);
//					 new EditKhachHangController(mainUI);
//					 new DeleteKhachHangController(mainUI);
					 MainUI mainUI = new  MainUI();
					 LoginController loginController = new LoginController(mainUI);
				 }
			}
		});
	}
}
