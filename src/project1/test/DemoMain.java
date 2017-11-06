package project1.test;

import project1.controller.AddNhanVienController;
import project1.controller.AddXeController;
import project1.controller.DeleteNhanVienController;
import project1.controller.DeleteXeController;
import project1.controller.EditNhanVienController;
import project1.controller.EditXeController;
import project1.view.MainUI;

public class DemoMain {
	public static void main(String[] args) {
		MainUI mainUI = new MainUI();
		new AddXeController(mainUI);
		new EditXeController(mainUI);
		new DeleteXeController(mainUI);
		new AddNhanVienController(mainUI);
		new EditNhanVienController(mainUI);
		new DeleteNhanVienController(mainUI);
	}
}
