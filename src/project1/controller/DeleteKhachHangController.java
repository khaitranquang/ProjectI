package project1.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import project1.model.KhachHang;
import project1.model.KhachHangDB;
import project1.view.ButtonKhachHangView;
import project1.view.MainUI;
import project1.view.PanelQuanLyKhachHangView;
import project1.view.TableKhachHangView;

public class DeleteKhachHangController {
	private MainUI mainUI;
	private KhachHang khachHang;
	private KhachHangDB khachHangDB;
	
	private PanelQuanLyKhachHangView panelQuanLyKhachHangView;
	private ButtonKhachHangView buttonKhachHangView;
	private TableKhachHangView tableKhachHangView;
	
	public DeleteKhachHangController(MainUI mainUI) {
		this.mainUI = mainUI;
		khachHangDB = new KhachHangDB();
		
		panelQuanLyKhachHangView = mainUI.getQlKH();
		buttonKhachHangView      = panelQuanLyKhachHangView.getBtnKH();
		tableKhachHangView       = panelQuanLyKhachHangView.getTableKH();
		
		setActions();
	}
	
	/* Get index of Row */
	private int findIndexOfData() {
		int index = tableKhachHangView.getTable().getSelectedRow();
		return index;
	}
	
	/* Get id's KhachHang */
	private String getId(int indexRow, int indexCol) {
		JTable table = tableKhachHangView.getTable();
		String id = table.getModel().getValueAt(indexRow, indexCol).toString();
		return id;
	}
	
	/*--------------Set Action----------*/
	private void setActions() {
		JButton btnXoa = buttonKhachHangView.getBtnXoa();
		
		btnXoa.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = findIndexOfData();
				if (index >= 0) {
					int select = JOptionPane.showOptionDialog(null, "Bạn có muốn xoá khách hàng này không?",
							 "Xoá", JOptionPane.YES_NO_OPTION,
							 JOptionPane.QUESTION_MESSAGE, null, null, null);
					if(select == 0) {
						String id = getId(index, 0);
						khachHang = khachHangDB.getKhachHang(id);
						khachHangDB.deleteKhachHang(khachHang);
						tableKhachHangView.updateTable(khachHangDB.getAllKhachHang());
					}
				}
				else {
					JOptionPane.showMessageDialog(mainUI, "Hãy chọn 1 khách hàng để xóa");
				}
			}
		});
	}
	
	
	
}
