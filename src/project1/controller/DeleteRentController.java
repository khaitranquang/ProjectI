package project1.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import project1.model.ChiTiet;
import project1.model.ChiTietDAO;
import project1.model.ChiTietDB;
import project1.model.MuonXeDAO;
import project1.model.MuonXeDB;
import project1.view.MainUI;
import project1.view.TableMuonTraView;

public class DeleteRentController {
	private MainUI mainUI;
	private ChiTietDAO chiTietDB;
	private MuonXeDAO muonXeDB;
	
	private JButton btnDelete;
	private TableMuonTraView tableMuonTraView;
	
	public DeleteRentController(MainUI mainUI) {
		this.mainUI = mainUI;
		muonXeDB = new MuonXeDB();
		chiTietDB = new ChiTietDB();
		
		btnDelete = mainUI.getQlMT().getBtn().getBtnXoa();
		tableMuonTraView = mainUI.getQlMT().getTableMT();
		setAction();
	}
	
	private void setAction() {
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int index = findIndexOfData();
				if (index >= 0) {
					int select = JOptionPane.showOptionDialog(null, "Bạn có muốn xoá sách này không?",
							 "Xoá", JOptionPane.YES_NO_OPTION,
							 JOptionPane.QUESTION_MESSAGE, null, null, null);
					if(select == 0) {
						String id = getId(index, 0);
						if (!checkDetail(id)) {
							JOptionPane.showMessageDialog(new JDialog(), "Mượn trả này còn có xe chưa trả nên không thể xóa");
							return;
						}
						else {
							chiTietDB.deleteChiTiet(id);
							muonXeDB.deleteRentXe(id);
							tableMuonTraView.updateTable(muonXeDB.getAllMuonXe());
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(mainUI, "Chọn 1 mượn trả để xóa");	
				}
			}
		});
	}
	
	/* Get index of Row */
	private int findIndexOfData() {
		int index = tableMuonTraView.getTable().getSelectedRow();
		return index;
	}
	
	/* Get id's loanBook */
	private String getId(int indexRow, int indexCol) {
		JTable table = tableMuonTraView.getTable();
		String id = table.getModel().getValueAt(indexRow, indexCol).toString();
		return id;
	}
	
	/* Kiem tra chi tiet muon tra con sach chua tra khong? */
	private boolean checkDetail(String maMT) {
		ArrayList<ChiTiet> listDetail = chiTietDB.getAllChiTietWithID(maMT);
		for (int i = 0; i < listDetail.size(); i++) {
			if (listDetail.get(i).getNgayTra().equals("")) return false;
		}
		return true;
	}
}
