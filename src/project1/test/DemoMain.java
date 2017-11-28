package project1.test;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import project1.controller.AddKhachHangController;
import project1.controller.AddNhanVienController;
import project1.controller.AddThueXeController;
import project1.controller.AddXeController;
import project1.controller.DeleteKhachHangController;
import project1.controller.DeleteNhanVienController;
import project1.controller.DeleteXeController;
import project1.controller.EditKhachHangController;
import project1.controller.EditNhanVienController;
import project1.controller.EditRentController;
import project1.controller.EditXeController;
import project1.controller.PrintSearchKHController;
import project1.controller.PrintSearchNVController;
import project1.controller.PrintSearchXeController;
import project1.controller.SearchKHController;
import project1.controller.SearchNVController;
import project1.controller.SearchXeController;
import project1.controller.ShowDetailInformation;
import project1.controller.TKKHController;
import project1.controller.TKNVController;
import project1.controller.TKRentController;
import project1.controller.TKXeController;
import project1.view.MainUI;

public class DemoMain {
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				 try {
					 UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					 MainUI mainUI = new  MainUI();
					 
						new AddXeController(mainUI);
						new EditXeController(mainUI);
						new DeleteXeController(mainUI);
						new AddNhanVienController(mainUI);
						new EditNhanVienController(mainUI);
						new DeleteNhanVienController(mainUI);
						new AddKhachHangController(mainUI);
						new EditKhachHangController(mainUI);
						new DeleteKhachHangController(mainUI);
						new SearchXeController(mainUI);
						new TKXeController(mainUI);
						new PrintSearchXeController(mainUI);
						new SearchKHController(mainUI);
						new PrintSearchKHController(mainUI);
						new TKKHController(mainUI);
						new SearchNVController(mainUI);
						new PrintSearchNVController(mainUI);
						new TKNVController(mainUI);
						new AddThueXeController(mainUI);
						new EditRentController(mainUI);
						new ShowDetailInformation(mainUI);
						new TKRentController(mainUI);
				 } 
				 catch (ClassNotFoundException | InstantiationException | 
						IllegalAccessException | UnsupportedLookAndFeelException ex) {
					 System.out.println("Failed");
					 MainUI mainUI = new  MainUI();
					 new AddXeController(mainUI);
					 new EditXeController(mainUI);
					 new DeleteXeController(mainUI);
					 new AddNhanVienController(mainUI);
					 new EditNhanVienController(mainUI);
					 new DeleteNhanVienController(mainUI);
					 new AddKhachHangController(mainUI);
					 new EditKhachHangController(mainUI);
					 new DeleteKhachHangController(mainUI);
				 }
			}
		});
	}
}
