package project1.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import project1.model.NhanVien;
import project1.model.NhanVienDB;
import project1.view.ButtonNhanVienView;
import project1.view.MainUI;
import project1.view.PanelQuanLyNhanVienView;
import project1.view.TableNhanVienView;

public class DeleteNhanVienController {
	private MainUI mainUI;
	private NhanVien nhanVien;
	private NhanVienDB nhanVienDB;
	
	private PanelQuanLyNhanVienView panelQuanLyNhanVienView;
	private ButtonNhanVienView buttonNhanVienView;
	private TableNhanVienView tableNhanVienView;
	
	public DeleteNhanVienController(MainUI mainUI) {
		this.mainUI = mainUI;
		nhanVienDB  = new NhanVienDB();
		
		panelQuanLyNhanVienView = mainUI.getQlNV();
		buttonNhanVienView      = panelQuanLyNhanVienView.getBtnNV();
		tableNhanVienView       = panelQuanLyNhanVienView.getTableNV();
		
		setActions();
	}
	
	/* Get index of Row */
	private int findIndexOfData() {
		int index = tableNhanVienView.getTable().getSelectedRow();
		return index;
	}
	
	/* Get id's Book */
	private String getId(int indexRow, int indexCol) {
		JTable table = tableNhanVienView.getTable();
		String id = table.getModel().getValueAt(indexRow, indexCol).toString();
		return id;
	}
	/*--------------Set Action----------*/
	private void setActions() {
		JButton btnXoa = buttonNhanVienView.getBtnXoa();
		
		btnXoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = findIndexOfData();
				if (index >= 0) {
					int select = JOptionPane.showOptionDialog(null, "Bạn có muốn xoá nhân viên này không?",
							 "Xoá", JOptionPane.YES_NO_OPTION,
							 JOptionPane.QUESTION_MESSAGE, null, null, null);
					if(select == 0) {
						String id = getId(index, 0);
						nhanVien = nhanVienDB.getNhanVien(id);
						nhanVienDB.deleteNhanVien(nhanVien);
						tableNhanVienView.updateTable(nhanVienDB.getAllNhanVien());
					}
				}
				else {
					JOptionPane.showMessageDialog(mainUI, "Hãy chọn 1 xe để xóa");
				}
			}
		});
	}
	
}
