package project1.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import project1.model.Xe;
import project1.model.XeDB;
import project1.view.ButtonXeView;
import project1.view.MainUI;
import project1.view.PanelQuanLyXeView;
import project1.view.TableXeView;

public class DeleteXeController {
	private MainUI mainUI;
	private Xe xe;
	private XeDB xeDB;
	
	private	PanelQuanLyXeView panelQuanLyXeView;
	private ButtonXeView buttonXeView;
	private TableXeView tableXeView;
	
	public DeleteXeController(MainUI mainUI) {
		this.mainUI = mainUI;
		xeDB = new XeDB();
		
		panelQuanLyXeView = mainUI.getQlXe();
		buttonXeView      = panelQuanLyXeView.getBtnXe();
		tableXeView       = panelQuanLyXeView.getTableXe();
		
		setActions();
	}
	
	/*--------------Set Action----------*/
	private void setActions() {
		JButton btnXoa = buttonXeView.getBtnXoa();
		
		btnXoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = findIndexOfData();
				if (index >= 0) {
					int select = JOptionPane.showOptionDialog(null, "Bạn có muốn xoá xe này không?",
							 "Xoá", JOptionPane.YES_NO_OPTION,
							 JOptionPane.QUESTION_MESSAGE, null, null, null);
					if(select == 0) {
						String id = getId(index, 0);
						xe = xeDB.getXe(id);
						xeDB.deleteXe(xe);
						tableXeView.updateTable(xeDB.getAllXe());
					}
				}
				else {
					JOptionPane.showMessageDialog(mainUI, "Hãy chọn 1 xe để xóa");
				}
			}
		});
	}
	
	/* Get index of Row */
	private int findIndexOfData() {
		int index = tableXeView.getTable().getSelectedRow();
		return index;
	}
	
	/* Get id's Book */
	private String getId(int indexRow, int indexCol) {
		JTable table = tableXeView.getTable();
		String id = table.getModel().getValueAt(indexRow, indexCol).toString();
		return id;
	}
}
