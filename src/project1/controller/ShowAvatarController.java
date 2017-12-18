package project1.controller;

import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import project1.model.XeDAO;
import project1.model.XeDB;
import project1.view.MainUI;
import project1.view.TableXeView;

public class ShowAvatarController {
	public static final int WIDTH_IMAGE = 450;
	public static final int HEIGHT_IMAGE = 300;
	private MainUI mainUI;
	private XeDAO xeDB;
	private TableXeView tableXeView;
	
	public ShowAvatarController(MainUI mainUI) {
		this.mainUI = mainUI;
		xeDB = new XeDB();
		tableXeView = mainUI.getQlXe().getTableXe();
		
		setAction();
	}
	
	// Find index
	private int findIndexOfData(){
		int index = tableXeView.getTable().getSelectedRow();
		return index;
	}
		
	private String getID(int indexRow, int indexCol) {
		JTable table = tableXeView.getTable();
		String id = table.getModel().getValueAt(indexRow, indexCol).toString();
		return id;
	}
	
	private void setAction() {
		JTable tableXe = tableXeView.getTable();
		tableXe.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		        JTable table =(JTable) mouseEvent.getSource();
		        Point point = mouseEvent.getPoint();
		        int row = table.rowAtPoint(point);
		        if (mouseEvent.getClickCount() == 2) {
		        	String maXe = getID(row, 0);
			        String url = xeDB.getAvatarUrl(maXe);
					ImageIcon img = new ImageIcon(url);
					
					Image imageTransform = img.getImage(); // transform it 
					Image newImg = imageTransform.getScaledInstance(WIDTH_IMAGE, HEIGHT_IMAGE,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
					img = new ImageIcon(newImg);  // transform it back

					JLabel lbl = new JLabel(img);
					JOptionPane.showMessageDialog(null, lbl, "Hình ảnh thực tế mã xe " + maXe, 
						                                 JOptionPane.PLAIN_MESSAGE, null);
		        }
		    }
		});
	}
}
