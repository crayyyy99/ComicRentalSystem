package gui;

import controller.*;
import model.*;

import java.util.ArrayList;
import java.sql.SQLException;
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

public class CuViewRentalDetailsDialog extends JDialog {
	private final int MAX_INT = 2147483467;
	private boolean selected = false;
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
	
	public CuViewRentalDetailsDialog(RentalCopy rentalCopy) {		
		
		selectedRCData = rentalCopy;
		
		int rentalId = selectedRCData.getRentalId();
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
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnClose, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(38)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblNewLabel_5, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
										.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
										.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
										.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
										.addComponent(lblNewLabel_5_1, Alignment.LEADING))
									.addGap(18))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_4_1, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtOverdueFee, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(txtExpectedDate, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
									.addComponent(txtRentDate, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
									.addComponent(txtRentalId, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
									.addComponent(txtRentFee, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtReturnDate, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
									.addComponent(txtStatus, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)))
							.addGap(50)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 335, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnViewComicDetails))))
					.addGap(42))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(43)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnViewComicDetails)
					.addPreferredGap(ComponentPlacement.RELATED, 107, Short.MAX_VALUE)
					.addComponent(btnClose, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addGap(47))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(49)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtRentalId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtRentDate, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtExpectedDate, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtReturnDate, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtRentFee, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtOverdueFee, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtStatus, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(105, Short.MAX_VALUE))
		);
		
		tableComicCopy = new JTable();
		tableComicCopy.setFont(new Font("Arial", Font.PLAIN, 13));
		tableComicCopy.setModel(new ComicCopyTableModel());
		tableComicCopy.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tableComicCopy);
		getContentPane().setLayout(groupLayout);
		setTitle("View Rental Details");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 820, 500);
		setLocationRelativeTo(null);
			
		loadRentalCopyList(String.format("rental_id = %d", rentalId));
		
		if(selectedRCData!= null)
			loadRentalData(selectedRCData);
		
	}

	public void loadRentalData(RentalCopy rc) {
		 	 
		txtRentalId.setText(String.valueOf(rc.getRentalId()));
		txtRentDate.setText(rc.getRentDate().format(formatter));
		txtExpectedDate.setText(rc.getExpectedDate().format(formatter));
		txtReturnDate.setText(rc.getReturnDate().format(formatter));
		txtRentFee.setText(String.valueOf(rc.getRentFee()));
		txtOverdueFee.setText(String.valueOf(rc.getOverdueFee()));
		txtStatus.setText(rc.getStatus());
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
		String header[] = new String[] { "Comic ID", "Copy ID"};
    
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
				CuComicViewDetailsDialog dialog = new CuComicViewDetailsDialog(selectedCC);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		    	dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
			}
			
		}
	}
	
	private class CloseButtonListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{
	    	CuViewRentalDetailsDialog.this.dispose();   	
		}
	}
}
