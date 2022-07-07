package gui;

import controller.*;
import model.*;

import java.util.ArrayList;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import exception.InputException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class AdViewRentalDetailsDialog extends JDialog {
	private final int MAX_INT = 2147483467;
	private boolean selected = false;
	private User selectedUser = new User();
	private RentalCopy selectedRCData = new RentalCopy();
	private ComicCopy selectedCC = new ComicCopy();
	private ArrayList<ComicCopy> comicCopyList = new ArrayList<ComicCopy>();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private JTextField txtRentalId;
	private JTextField txtReturnDate;
	private JTextField txtRentDate;
	private JTable tableComicCopy;
	private JTextField txtExpectedDate;
	private JTextField txtRentFee;
	private JTextField txtOverdueFee;
	private JTextField txtStatus;
	private JTextField txtUserId;
	private JButton btnUpdateStatus;
	private Rental r = new Rental();
	private RentalController rController = new RentalController();
	private RentalCopyController rcController = new RentalCopyController();	
	private int count =0;
	
	public AdViewRentalDetailsDialog(RentalCopy rentalCopy, boolean isCompleted) {		
		
		String condition = String.format("id = %d", rentalCopy.getRentalId());
		
			try {
				r = rController.selectWhere(condition, 0, 1).get(0);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		getContentPane().setBackground(new Color(255, 255, 204));
		
		txtRentalId = new JTextField();
		txtRentalId.setEditable(false);
		txtRentalId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtRentalId.setColumns(10);
		
		txtReturnDate = new JTextField();
		txtReturnDate.setEditable(false);
		txtReturnDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtReturnDate.setColumns(10);
		
		JButton btnClose = new JButton("Close");
		btnClose.setVerticalAlignment(SwingConstants.BOTTOM);
		btnClose.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		btnClose.addActionListener(new CloseButtonListener());
		
		JLabel lblNewLabel_1 = new JLabel("Rental ID:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_2 = new JLabel("Rent Date:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_3 = new JLabel("Expected Date:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_4 = new JLabel("Return Date:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_5 = new JLabel("Rent Fee (RM):");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtRentDate = new JTextField();
		txtRentDate.setEditable(false);
		txtRentDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtRentDate.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnViewComicDetails = new JButton("View Comic Details");
		btnViewComicDetails.setVerticalAlignment(SwingConstants.BOTTOM);
		btnViewComicDetails.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		btnViewComicDetails.addActionListener(new ViewDetailsButtonListener());
		
		txtExpectedDate = new JTextField();
		txtExpectedDate.setEditable(false);
		txtExpectedDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtExpectedDate.setColumns(10);
		
		txtRentFee = new JTextField();
		txtRentFee.setEditable(false);
		txtRentFee.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtRentFee.setColumns(10);
		
		JLabel lblNewLabel_5_1 = new JLabel("Overdue Fee (RM):");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtOverdueFee = new JTextField();
		txtOverdueFee.setEditable(false);
		txtOverdueFee.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtOverdueFee.setColumns(10);
		
		JLabel lblNewLabel_4_1 = new JLabel("Status:");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtStatus = new JTextField();
		txtStatus.setEditable(false);
		txtStatus.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtStatus.setColumns(10);
		
		JButton btnViewCustomerDetails = new JButton("View Customer Details");
		btnViewCustomerDetails.setVerticalAlignment(SwingConstants.BOTTOM);
		btnViewCustomerDetails.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		btnViewCustomerDetails.addActionListener(new ViewCusDetailsButtonListener());
		
		txtUserId = new JTextField();
		txtUserId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUserId.setEditable(false);
		txtUserId.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Customer ID:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		btnUpdateStatus = new JButton("Update Status");
		btnUpdateStatus.setEnabled(false);
		btnUpdateStatus.setVerticalAlignment(SwingConstants.BOTTOM);
		btnUpdateStatus.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		btnUpdateStatus.addActionListener(new UpdateStatusButtonListener());
		
		if(isCompleted)
			btnUpdateStatus.setEnabled(false);
		else
			btnUpdateStatus.setEnabled(true);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 435, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnUpdateStatus, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addComponent(btnClose, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
											.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
													.addGroup(groupLayout.createSequentialGroup()
														.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
														.addGap(22))
													.addComponent(lblNewLabel_4_1, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
													.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
												.addGap(4))
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblNewLabel_5_1)
												.addPreferredGap(ComponentPlacement.UNRELATED)))
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(lblNewLabel_1_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lblNewLabel_3, Alignment.LEADING))
											.addPreferredGap(ComponentPlacement.RELATED)))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED))))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtStatus, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(txtReturnDate, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
									.addComponent(txtRentFee, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtExpectedDate, 232, 232, 232)
									.addComponent(txtRentDate, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
									.addComponent(txtUserId, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtRentalId, GroupLayout.PREFERRED_SIZE, 229, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtOverdueFee, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(btnViewComicDetails, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnViewCustomerDetails, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)))))
					.addGap(43))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(70)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtRentalId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtUserId, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtRentDate, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtExpectedDate, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtReturnDate, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(txtRentFee, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtOverdueFee, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_5_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
									.addGap(24)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(txtStatus, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_4_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnViewComicDetails)
							.addGap(18)
							.addComponent(btnViewCustomerDetails, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnClose, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnUpdateStatus, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(70))
		);
		
		tableComicCopy = new JTable();
		tableComicCopy.setFont(new Font("Arial", Font.PLAIN, 13));
		tableComicCopy.setModel(new ComicCopyTableModel());
		tableComicCopy.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tableComicCopy);
		getContentPane().setLayout(groupLayout);
		setTitle("View Rental Details");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 574);
		setLocationRelativeTo(null);
			
		loadRentalCopyList(String.format("rental_id = %d", r.getId()));
		loadRentalData(r);
	}

	public void loadRentalData(Rental r) {
		 	 
		txtRentalId.setText(String.valueOf(r.getId()));
		txtUserId.setText(String.valueOf(r.getUserId()));
		txtRentDate.setText(r.getRentDate().format(formatter));
		txtExpectedDate.setText(r.getExpectedDate().format(formatter));
		txtReturnDate.setText(r.getReturnDate().format(formatter));
		txtRentFee.setText(String.valueOf(r.getRentFee()));
		txtOverdueFee.setText(String.valueOf(r.getOverdueFee()));
		txtStatus.setText(r.getStatus());
	}
	
	public void loadRentalCopyList(String condition)
	{
		ComicCopyController comicCopyController = new ComicCopyController();
		comicCopyList.clear();
		try {
			comicCopyList = comicCopyController.selectWhereFindRentalList(condition, 0, MAX_INT);
			tableComicCopy.repaint();
			tableComicCopy.revalidate();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}			
	}
	
	private class ComicCopyTableModel implements TableModel 
	{   
		String header[] = new String[] { "Comic ID", "Copy ID", "Condition"};
    
		 public int getColumnCount() 
		 {
		     return header.length;
		 }
		   
		 public int getRowCount() 
		 {
		     return comicCopyList.size();
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
			 ComicCopy cc = comicCopyList.get(rowIndex);
			 switch(columnIndex)
			 {
			  	case 0: return cc.getComicId();
			   	case 1: return cc.getCopyId();
			   	case 2: return cc.getCondition();
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
	
	private class ViewDetailsButtonListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{
			int index = tableComicCopy.getSelectedRow();
	    	if(index < 0)
	    		return;
    		int comicId = (int) tableComicCopy.getValueAt(index, 0);	 
    		String condition = String.format("comic_copy.comic_id = %d ", comicId);
    		ComicCopyController ccController = new ComicCopyController();
    		try {
    			selectedCC = ccController.selectWhereFindRentalList(condition, 0, 1).get(0);
    			selected = true;
    		} catch (ClassNotFoundException | SQLException e1) {
    			e1.printStackTrace();
    		}
    		
    		try {
				AdComicViewDetailsDialog dialog = new AdComicViewDetailsDialog(selectedCC);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		    	dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
			}
			
		}
	}
	
	private class ViewCusDetailsButtonListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{
			int id = r.getUserId();
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
	
	private class UpdateStatusButtonListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{								
			ArrayList <RentalCopy> rentalCopyList = new ArrayList <RentalCopy>();
			
			String condition = String.format("rental_id = %d", r.getId());
	    	try {
				rentalCopyList = rcController.selectWhere(condition, 0, MAX_INT);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	
			int x = JOptionPane.showConfirmDialog(null, "Are you confirm this customer has returned the comics?", "Update Confirmation", JOptionPane.YES_NO_OPTION);

        	if(x == JOptionPane.YES_OPTION)
        	{
        		LocalDateTime currentDateTime = LocalDateTime.now();
        		boolean isAfter = currentDateTime.isAfter(r.getExpectedDate());

        		if(isAfter)
        		{
        			double fine = 0.0;
        			for(int i = 0; i< rentalCopyList.size();i++) {
        				fine += 1.0;
        			}
        			r.setOverdueFee(fine);
        			selectedRCData.setOverdueFee(fine);
        		}
        		else
        			r.setOverdueFee(0.0);
        		
        		int result = -1;
		    	try {
					if(r.getId() == 0)
						result = rController.insert(r);
					else
						result = rController.update(r);					
					
    			} catch (InputException e1) {
    				e1.displayMessage();
    			}
    			catch (ClassNotFoundException | SQLException e1) {
    				e1.printStackTrace();
    			}
    	    	

		    	ComicCopyController ccController = new ComicCopyController();
		    	ArrayList<ComicCopy> ccList = new ArrayList<ComicCopy>();
    	    	ComicCopy cc = new ComicCopy();
    	    	
    	    	for(int i=0;i<rentalCopyList.size(); i++)
    	    	{
    	    		condition = String.format("id = %d", rentalCopyList.get(i).getComicCopyId());
    	    		try {
						cc = ccController.selectWhere(condition, 0, 1).get(0);
							
						ccList.add(cc);
						ccList.get(i).setCondition("Available");			
						
						try {
							int success = ccController.update(ccList.get(i));
							
							if(success ==1)
							{
								btnUpdateStatus.setEnabled(false);
							}
							
							loadRentalData(r);
			    	    	loadRentalCopyList(String.format("rental_id = %d", r.getId()));
						} catch (InputException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
    	    	}
    	    	
    	    	PopOutMsgBox.infoBox("This rental record is set to \"completed\".", "Update Successful");
    	    	count++;
           	} else {}
        		
		}
     	
	}
	
	private class CloseButtonListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{
			if(count >=1)
				PopOutMsgBox.infoBox("Please refresh the table to see the record.", "Notification");
	    	AdViewRentalDetailsDialog.this.dispose();
		}
	}

	
	
}
