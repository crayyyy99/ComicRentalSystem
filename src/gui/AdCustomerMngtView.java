package gui;

import controller.UserController;
import model.User;
import java.util.ArrayList;
import java.sql.SQLException;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdCustomerMngtView extends JFrame {
	
	private final int MAX_INT = 2147483467;
	private boolean selected = false;
	private User selectedUser = new User();
	private ArrayList<User> userList = new ArrayList<User>();
	private JTextField searchtxt;
	private JComboBox cmbSearchType;
	private JTable tableUser;
		
	public static void main(String[] args) {
		try {
			AdCustomerMngtView view = new AdCustomerMngtView();			
			view.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public AdCustomerMngtView() {
		setTitle("Rabbit Comics Rental System");
		setResizable(true);	
		//setBounds(100, 100, 829, 574);
		//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//setSize(screenSize.width-50, screenSize.height-50);
		setSize(1080, 720);
		
		setName("frame\r\n");
		getContentPane().setBackground(new Color(255, 255, 204));
		
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("C:\\Users\\user\\eclipse-workspace\\ComicRentalSystem\\"
				+ "images\\user.png").getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
		
		ImageIcon searchIcon = new ImageIcon(new ImageIcon("C:\\Users\\user\\eclipse-workspace\\ComicRentalSystem\\"
				+ "images\\search.png").getImage().getScaledInstance(18, 17, Image.SCALE_DEFAULT));
		
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(10, 7));
		panel.setBorder(null);
		panel.setBackground(new Color(255, 255, 204));
		JLabel image = new JLabel("");
		image.setHorizontalAlignment(SwingConstants.CENTER);
		image.setIcon(imageIcon);
		
		JLabel AdminPanelTitle = new JLabel("CUSTOMER MANAGEMENT");
		AdminPanelTitle.setVerticalAlignment(SwingConstants.BOTTOM);
		AdminPanelTitle.setOpaque(true);
		AdminPanelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		AdminPanelTitle.setFont(new Font("Sitka Subheading", Font.BOLD, 27));
		AdminPanelTitle.setBackground(new Color(255, 222, 179));
		
		JLabel lblNewLabel = new JLabel("Search By");
		lblNewLabel.setFont(new Font("Sitka Subheading", Font.PLAIN, 19));
		
		cmbSearchType = new JComboBox();
		cmbSearchType.setFont(new Font("Arial", Font.PLAIN, 17));
		cmbSearchType.setModel(new DefaultComboBoxModel(new String[] {"User ID", "Name"}));
		
		searchtxt = new JTextField();
		searchtxt.setFont(new Font("Arial", Font.PLAIN, 17));
		searchtxt.setColumns(100);
		
		JButton btnSearch = new JButton("");
		btnSearch.setIcon(searchIcon);
		btnSearch.addActionListener(new SearchButtonListener());
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(201)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(cmbSearchType, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(searchtxt, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(339)
							.addComponent(AdminPanelTitle, GroupLayout.PREFERRED_SIZE, 394, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(480)
							.addComponent(image, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(653, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(image, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(AdminPanelTitle, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(1)
							.addComponent(cmbSearchType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(searchtxt, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnRefreshTable = new JButton("Refresh Table");
		btnRefreshTable.setVerticalAlignment(SwingConstants.BOTTOM);
		btnRefreshTable.setFont(new Font("Sitka Subheading", Font.PLAIN, 17));
		btnRefreshTable.addActionListener(new RefreshButtonListener());
		
		JButton btnUpdate = new JButton("Update Access");
		btnUpdate.setFont(new Font("Sitka Subheading", Font.PLAIN, 17));
		btnUpdate.addActionListener(new UpdateUserDetailsButtonListener());
		
		JButton btnViewDetails = new JButton("View Details");
		btnViewDetails.setFont(new Font("Sitka Subheading", Font.PLAIN, 17));
		btnViewDetails.addActionListener(new ViewUserDetailsButtonListener());
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminPanelView frame = new AdminPanelView();
				frame.setVisible(true);
				dispose();
			}
		});
		btnBack.setVerticalAlignment(SwingConstants.BOTTOM);
		btnBack.setFont(new Font("Sitka Subheading", Font.PLAIN, 17));

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(197)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnRefreshTable)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 672, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnUpdate)
						.addComponent(btnViewDetails, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnUpdate, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addGap(29)
							.addComponent(btnViewDetails, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 332, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRefreshTable, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(40, Short.MAX_VALUE))
		);
		
		tableUser = new JTable();
		scrollPane.setViewportView(tableUser);
		tableUser.setModel(new UserTableModel());
		tableUser.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableUser.setFont(new Font("Arial", Font.PLAIN, 13));
		getContentPane().setLayout(groupLayout);
		
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		loadUserList("");
	}
	
	private class UserTableModel implements TableModel 
	{   
		String header[] = new String[] { "User ID", "Name", "IC No", "Permission", "Username"};
    
		 public int getColumnCount() 
		 {
		     return header.length;
		 }
		   
		 public int getRowCount() 
		 {
		     return userList.size();
		 }    
		    
		 public String getColumnName(int column) 
		 {
		     return header[column];
		 }
		        
		 public Class getColumnClass(int column) 
		 {
		     return String.class;
		 }
		   
		 public Object getValueAt(int rowIndex, int columnIndex) 
		 {
			 User user = userList.get(rowIndex);
			 switch(columnIndex)
			 {
			  	case 0: return user.getId();
			  	case 1: return user.getName();
			  	case 2: return user.getIcNo();
			   	case 3: return user.getPermission();
			   	case 4: return user.getUsername();
			 }
		     return "";
		 }    
		    
		 public boolean isCellEditable(int rowIndex, int columnIndex) 
		 {
		     return false;
		 }
		    
		 public void setValueAt(Object value, int row, int column) {}

		 public void addTableModelListener(TableModelListener l) {}

		 public void removeTableModelListener(TableModelListener l) {}  
	
	}

	public void loadUserList(String condition)
	{
		UserController userController = new UserController();
		userList.clear();
		try {
			if(condition == "")
				userList = userController.selectAll(0, MAX_INT);
			else
				userList = userController.selectWhere(condition, 0, MAX_INT);
			tableUser.repaint();
			tableUser.revalidate();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}	
		
	}
	
//	private class DeleteButtonListener implements ActionListener
//	{		
//		public void actionPerformed(ActionEvent e)
//		{   		
//			UserController userController = new UserController();
//    		
//			int index = tableUser.getSelectedRow();
//	    	if(index < 0)
//	    		return;
//    		int id = (int) tableUser.getValueAt(index, 0);	 
//    		String condition = String.format("id = %d", id);
//
//    		try {
//    			selectedUser = userController.selectWhere(condition, 0, 1).get(0);
//    			selected = true;
//    		} catch (ClassNotFoundException | SQLException e1) {
//    			e1.printStackTrace();
//    		}	    
//    		
//    		int x = JOptionPane.showConfirmDialog(null, "Are you confirm?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);
//
//        	if(x == JOptionPane.YES_OPTION)
//        	{
//        		PopOutMsgBox.infoBox("User record cannot be deleted.", "Unable to Delete");
//        		
////        		try {		
////    			int success = userController.delete(selectedUser);
////    			if(success == 1)
////    				PopOutMsgBox.infoBox("Selected row has been deleted.", "Delete Successful");
////
////        		} catch (ClassNotFoundException | SQLException e1) {
////        			e1.printStackTrace();
////        		}	
//        	}
//        	else {}
//		}
//		
//	}
	
	private class ViewUserDetailsButtonListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{
			int index = tableUser.getSelectedRow();
	    	if(index < 0)
	    		return;
    		int id = (int) tableUser.getValueAt(index, 0);	 
    		String condition = String.format("id = %d", id);
    		UserController userController = new UserController();
    		try {
    			selectedUser = userController.selectWhere(condition, 0, 1).get(0);
    			selected = true;
    		} catch (ClassNotFoundException | SQLException e1) {
    			e1.printStackTrace();
    		}	    
			
			try {
				AdViewUserDetailsDialog dialog = new AdViewUserDetailsDialog(selectedUser);
		    	dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		    	dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			
		}
	}
	
	private class UpdateUserDetailsButtonListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{
			int index = tableUser.getSelectedRow();
	    	if(index < 0)
	    		return;
    		int id = (int) tableUser.getValueAt(index, 0);	 
    		String condition = String.format("id = %d", id);
    		UserController userController = new UserController();
    		try {
    			selectedUser = userController.selectWhere(condition, 0, 1).get(0);
    			selected = true;
    		} catch (ClassNotFoundException | SQLException e1) {
    			e1.printStackTrace();
    		}	    
			
			try {
				AdEditUserDetailsDialog dialog = new AdEditUserDetailsDialog(selectedUser);
		    	dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		    	dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			
		}
	}
	
	private class RefreshButtonListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{
	    	loadUserList("");
	    	searchtxt.setText("");
	    	selected = false;
		}
	}
	
	
	
	private class SearchButtonListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{
			String condition = "%s LIKE '%s'", searchType = "", strCond = "";
	    	String searchStr = searchtxt.getText();
	    	if((String)cmbSearchType.getSelectedItem() == "User ID")
	    	{
	    		searchType = "id";
	    		strCond = searchStr;
	    	}
	    	else
	    	{
	    		searchType = "name";
	    		strCond = "%" + searchStr + "%";
	    	}
	    	
	    	condition = String.format(condition,  searchType, strCond);
	    	loadUserList(condition);
		}
	}


}
	

