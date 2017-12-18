package project1.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import project1.model.Xe;
import project1.model.XeDAO;
import project1.model.XeDB;
import project1.view.EditXeView;
import project1.view.MainUI;
import project1.view.TableXeView;
import project1.view.XeInformation;

public class EditXeController {
	private static final Pattern DATE_PATTERN = Pattern.compile(
			"^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|"
			+ "-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|"
			+ "^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468]"
			+ "[048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|"
			+ "^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4("
			+ "?:(?:1[6-9]|[2-9]\\d)?\\d{2})$");

	private MainUI mainUI;
	private Xe xe;
	private XeDAO xeDB;
	private String oldID = "";
	
	private JButton btnEdit;
	private EditXeView editXeView;
	private XeInformation xeInformation;
	private TableXeView tableXeView;
	
	public EditXeController(MainUI mainUI) {
		this.mainUI = mainUI;
		xeDB = new XeDB();
		btnEdit = mainUI.getQlXe().getBtnXe().getBtnSua();
		tableXeView = mainUI.getQlXe().getTableXe();
		tableXeView.updateTable(xeDB.getAllXe());
		
		//...................
		
		
		btnEditEvent();
	}
	
	/* Event - Action */
	private void btnEditEvent() {
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//.........
				editXeView = new EditXeView(mainUI);
				xeInformation = editXeView.getXeInformation();
				
				int index = findIndexOfData();
				if (index >= 0) {
					editXeView.setVisible(true);
					oldID = getID(index, 0);
					loadInfor(oldID);
					
					setActions();
				}
				else {
					JOptionPane.showMessageDialog(mainUI, "Chọn 1 xe để sửa");
				}
			}
		});
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
	
	private void loadInfor(String id) {
		xe = xeDB.getXe(id);
		xeInformation.getTfIdXe().setText(id);
		xeInformation.getTfIdXe().setEditable(false);
		xeInformation.getTfBienXe().setText(xe.getBienXe());
		xeInformation.getTfTenXe().setText(xe.getTenXe());
		xeInformation.getTfLoaiXe().setText(xe.getLoaiXe());
		xeInformation.getTfHangSanXuat().setText(xe.getHangSanXuat());
		xeInformation.getTfNamSanXuat().setText(xe.getNamSanXuat());
		xeInformation.getTfNgayBaoTri().setText(xe.getNgayBaoTri());
		xeInformation.getTfNhienLieu().setText(xe.getNhienLieu());
		xeInformation.getTfTrangThai().setText(xe.getTrangThai() + "");
		xeInformation.getTfGiaThue().setText(xe.getGiaThue() + "");
		xeInformation.getLbAvatarUrl().setText(xeDB.getAvatarUrl(id));
	}
	
	private void setActions() {
		JButton btnSelectUrl = xeInformation.getBtnSelectImg();
		JButton btnEdit = editXeView.getBtnEdit();
		JButton btnCancel = editXeView.getBtnCancel();
		
		btnSelectUrl.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				int select = fileChooser.showOpenDialog(null);
				String openFilePath = "";
				
				if (select == JFileChooser.APPROVE_OPTION) {
					String path = fileChooser.getCurrentDirectory().toString() 
					       	   + "\\" + fileChooser.getSelectedFile().getName();
					if(path.indexOf(".jpg") >= 0 || path.indexOf(".JPG")>= 0 ||
					   path.indexOf(".png") >= 0 || path.indexOf(".PNG") >= 0) {
						openFilePath = path;
					}
					else {
						openFilePath = path + ".jpg";
					}
					xeInformation.getLbAvatarUrl().setText(openFilePath);
					System.out.println(openFilePath);
				}
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				suaXe();
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				huy();
			}
		});
	}
	
	/*
	 * Check information of input
	 */
	private boolean checkInfor(XeInformation xeInformation) {
		// Are text fields empty?
		if (xeInformation.getTfIdXe().getText().toString().trim().equals("")        ||
			xeInformation.getTfBienXe().getText().toString().trim().equals("")      ||
			xeInformation.getTfTenXe().getText().toString().trim().equals("")       ||
			xeInformation.getTfLoaiXe().getText().toString().trim().equals("")      ||
			xeInformation.getTfHangSanXuat().getText().toString().trim().equals("") ||
			xeInformation.getTfNamSanXuat().getText().toString().trim().equals("")  ||
			xeInformation.getTfNhienLieu().getText().toString().trim().equals("")   ||
			xeInformation.getTfNgayBaoTri().getText().toString().trim().equals("")  ||
			xeInformation.getTfTrangThai().getText().toString().trim().equals("")   ||
			xeInformation.getTfGiaThue().getText().toString().trim().equals(""))  {
			System.out.println("Text Fields are not empty !!!");
			JOptionPane.showMessageDialog(new JDialog(), "Các trường dữ liệu không được để trống");
			return false;
		}
		
		// Check integer?
		try {
			int namsanXuat = Integer.parseInt(xeInformation.getTfNamSanXuat().getText().toString().trim());
			int trangThai  = Integer.parseInt(xeInformation.getTfTrangThai().getText().toString().trim());
			int giaThue    = Integer.parseInt(xeInformation.getTfGiaThue().getText().toString().trim());
			// Test < 0 ????
			if(namsanXuat < 0 || trangThai <0 || giaThue < 0 || namsanXuat > 9999 || namsanXuat < 1000) {
				JOptionPane.showMessageDialog(new JDialog(), "Nhập lại đúng định dạng các trường số !!!");
				return false;
			}	
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(new JDialog(), "Năm sản xuất, trạng thái, giá thuê phải là số");
			System.out.println(e.toString());
			return false;
		}
		
		/* Check ngayBaoTri */
		if (checkFormatOfDate(xeInformation.getTfNgayBaoTri().getText().toString().trim()) == false) {
			JOptionPane.showMessageDialog(new JDialog(), "Hã nhập đúng định dạng ngày bảo trì dd/mm/yyyy");
			return false;
		}
		
		/* Check if maSach is exist*/
		if (!checkID(xeInformation.getTfIdXe().getText().toString().trim())) {
			JOptionPane.showMessageDialog(new JDialog(), "Mã xe đã tồn tại - Hãy nhập lại");
			return false;
		}
		
		return true;
	}
	
	private boolean checkID(String id) {
		ArrayList<Xe> listXe = xeDB.getAllXe();
		for (int i = 0; i < listXe.size(); i++) {
			String idXeFromDB = listXe.get(i).getIdXe();
			if (id.equals(idXeFromDB) && (id.equals(oldID) == false)) return false;
		}
		return true;
	}
	
	private boolean checkFormatOfDate(String date) {
		return DATE_PATTERN.matcher(date).matches();
	}
	
	private void suaXe() {
		String url ="";
		if (checkInfor(xeInformation)) {
			String maXeMoi        = xeInformation.getTfIdXe().getText().toString();
			String bienXeMoi      = xeInformation.getTfBienXe().getText().toString();
			String tenXeMoi       = xeInformation.getTfTenXe().getText().toString();
			String loaiXeMoi      = xeInformation.getTfLoaiXe().getText().toString();
			String hangSanXuatMoi = xeInformation.getTfHangSanXuat().getText().toString();
			String namSanXuatMoi  = xeInformation.getTfNamSanXuat().getText().toString();
			String ngayBaoTriMoi  = xeInformation.getTfNgayBaoTri().getText().toString();
			String nhienLieuMoi   = xeInformation.getTfNhienLieu().getText().toString();
			int trangThaiMoi      = Integer.parseInt(xeInformation.getTfTrangThai().getText().toString());
			int giaThueMoi        = Integer.parseInt(xeInformation.getTfGiaThue().getText().toString());
			url 			      = xeInformation.getLbAvatarUrl().getText().toString();
			xeDB.updateXe(this.xe, maXeMoi, bienXeMoi, tenXeMoi, loaiXeMoi, hangSanXuatMoi, namSanXuatMoi, ngayBaoTriMoi, nhienLieuMoi, trangThaiMoi, giaThueMoi);
			xeDB.updateUrl(maXeMoi, url);
			tableXeView.updateTable(xeDB.getAllXe());
			
			this.editXeView.dispose();
		}
		else {
			System.out.println("Edit fail !!!");
		}
	}
	
	private void huy() {
		this.editXeView.dispose();
	}	
}
