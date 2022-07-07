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
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class CuRentAddDialog extends JDialog {
	
	private final int MAX_INT = 2147483467;
	private boolean selectedComic = false;
	private boolean selectedRental = false;
	private User userData = new User();
	private ComicCopy selectedCCData = new ComicCopy();
	private ComicCopy selectedRCData = new ComicCopy();
	private ArrayList<ComicCopy> comicCopyList = new ArrayList<ComicCopy>();
	private ArrayList<ComicCopy> rentalCopyList = new ArrayList<ComicCopy>();
	private JTextField searchtxt;
	private JComboBox cmbSearchType;
	private JTable tableAddRental;
	private JTable tableComic;
	private JTextField txttotalprice;
	private double totalPrice =0.0;
	
	
	public CuRentAddDialog(User user) {		
		
		userData = user;
		getContentPane().setBackground(new Color(255, 255, 204));
		
		ImageIcon searchIcon = new ImageIcon(new ImageIcon("C:\\Users\\user\\eclipse-workspace\\ComicRentalSystem\\"
				+ "images\\search.png").getImage().getScaledInstance(18, 17, Image.SCALE_DEFAULT));
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new SubmitButtonListener());
		btnSubmit.setVerticalAlignment(SwingConstants.BOTTOM);
		btnSubmit.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new CancelButtonListener());
		btnCancel.setVerticalAlignment(SwingConstants.BOTTOM);
		btnCancel.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblNewLabel_1 = new JLabel("Search By");
		lblNewLabel_1.setFont(new Font("Sitka Subheading", Font.PLAIN, 15));
		
		cmbSearchType = new JComboBox();
		cmbSearchType.setFont(new Font("Arial", Font.PLAIN, 14));
		cmbSearchType.setModel(new DefaultComboBoxModel(new String[] {"Comic ID", "Title", "Author", "Genre"}));
		
		searchtxt = new JTextField();
		searchtxt.setFont(new Font("Arial", Font.PLAIN, 14));
		searchtxt.setColumns(100);
		
		JButton btnSearch = new JButton("");
		btnSearch.setIcon(searchIcon);
		btnSearch.addActionListener(new SearchButtonListener());
		
		JButton btnViewDetails = new JButton("View Details");
		btnViewDetails.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		btnViewDetails.addActionListener(new ViewComicDetailsButtonListener());
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JLabel lblSearchComics = new JLabel("Search Comics");
		lblSearchComics.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSearchComics.setOpaque(true);
		lblSearchComics.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearchComics.setFont(new Font("Sitka Subheading", Font.BOLD, 19));
		lblSearchComics.setBackground(new Color(255, 222, 179));
		
		JLabel lblAddRental = new JLabel("Add Rental");
		lblAddRental.setVerticalAlignment(SwingConstants.BOTTOM);
		lblAddRental.setOpaque(true);
		lblAddRental.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddRental.setFont(new Font("Sitka Subheading", Font.BOLD, 19));
		lblAddRental.setBackground(new Color(255, 222, 179));
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setVerticalAlignment(SwingConstants.BOTTOM);
		btnAdd.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		btnAdd.addActionListener(new AddRentalCopyListener());
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setVerticalAlignment(SwingConstants.BOTTOM);
		btnDelete.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		btnDelete.addActionListener(new DeleteRentalCopyListener());
		
		JButton btnRefreshTable = new JButton("Refresh Table");
		btnRefreshTable.addActionListener(new RefreshButtonListener());
		btnRefreshTable.setVerticalAlignment(SwingConstants.BOTTOM);
		btnRefreshTable.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		
		JLabel lblNewLabel = new JLabel("Total Price (RM):");
		lblNewLabel.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		
		txttotalprice = new JTextField();
		txttotalprice.setEditable(false);
		txttotalprice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txttotalprice.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(44)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(cmbSearchType, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(searchtxt, 0, 0, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblSearchComics, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 481, GroupLayout.PREFERRED_SIZE))
							.addGap(52))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(9)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnRefreshTable, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 206, Short.MAX_VALUE)
									.addComponent(btnViewDetails))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 462, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 62, Short.MAX_VALUE)))
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblAddRental, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(9)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 443, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(txttotalprice, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)))))
							.addGap(3))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnSubmit)
							.addGap(18)
							.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)))
					.addGap(34))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSearchComics, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
									.addComponent(cmbSearchType, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
									.addComponent(searchtxt, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblAddRental, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnViewDetails, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRefreshTable)
						.addComponent(lblNewLabel)
						.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(txttotalprice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSubmit))
					.addGap(31))
		);
		
		tableAddRental = new JTable();
		scrollPane_1.setViewportView(tableAddRental);
		tableAddRental.setModel(new RentalTableModel());
		tableAddRental.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableAddRental.setFont(new Font("Arial", Font.PLAIN, 13));	
		
		tableComic = new JTable();
		scrollPane.setViewportView(tableComic);
		tableComic.setModel(new ComicTableModel());
		tableComic.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableComic.setFont(new Font("Arial", Font.PLAIN, 13));
		
		getContentPane().setLayout(groupLayout);
		setTitle("Add Rental");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1080, 574);
		setLocationRelativeTo(null);
		
		
		loadAddRentalList();
		loadComicList("");
	
				
	}
	
	private class RentalTableModel implements TableModel 
	{   
		String header[] = new String[] { "Comic ID", "Copy ID", "Title", "Genre", "Author", "Price"};
    
		 public int getColumnCount() 
		 {
		     return header.length;
		 }
		   
		 public int getRowCount() 
		 {
		     return rentalCopyList.size();
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
			 ComicCopy rc = rentalCopyList.get(rowIndex);
			 switch(columnIndex)
			 {
			  	case 0: return rc.getComicId();
			  	case 1: return rc.getCopyId();
			  	case 2: return rc.getTitle();
			   	case 3: return rc.getGenre();
			   	case 4: return rc.getAuthor();
			   	case 5: return rc.getPrice();
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
	
	public void loadAddRentalList()
	{
		tableAddRental.repaint();
		tableAddRental.revalidate();	
	}

	private class ComicTableModel implements TableModel 
	{   
		String header[] = new String[] { "Comic ID", "Copy ID", "Title", "Genre", "Author", "Condition", "Price"};
    
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
			 ComicCopy cp = comicCopyList.get(rowIndex);
			 switch(columnIndex)
			 {
			  	case 0: return cp.getComicId();
			  	case 1: return cp.getCopyId();
			  	case 2: return cp.getTitle();
			   	case 3: return cp.getGenre();
			   	case 4: return cp.getAuthor();
			   	case 5: return cp.getCondition();
			   	case 6: return cp.getPrice();
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
	
	private class AddRentalCopyListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e) {
			int row = tableComic.getSelectedRow();
			selectedCCData = comicCopyList.get(row);
	    	
			if(selectedCCData.getCondition().contains("Available"))
			{
				selectedComic = true;
		    	
		    	totalPrice += selectedCCData.getPrice();
		    	
		    	txttotalprice.setText(Double.toString(totalPrice));
				ComicCopyController comicCopyController = new ComicCopyController();
				ComicCopy data = new ComicCopy();
				String condition = "comic_copy.id = " + (String.valueOf(selectedCCData.getId()));
				try {
					data = comicCopyController.selectWhereList(condition, 0, 1).get(0);
					
	
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				data.setCondition("Rented");
	
		    	int result = -1;
		    	try {
					if(data.getId() == 0)
						result = comicCopyController.insert(data);
					else
						result = comicCopyController.update(data);
					
					loadAddRentalList();
					loadComicList("");
					rentalCopyList.add(data);
				} catch (InputException e1) {
					e1.displayMessage();
				}
				catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
			else
				PopOutMsgBox.infoBox("This copy is not available!","Comic Not Available");
			
		}
	}

	public void loadComicList(String condition)
	{
		ComicCopyController comicCopyController = new ComicCopyController();
		comicCopyList.clear();
		try {
			if(condition == "")
				comicCopyList = comicCopyController.selectAllList(0, MAX_INT);
			else
				comicCopyList = comicCopyController.selectWhereList(condition, 0, MAX_INT);
			tableComic.repaint();
			tableComic.revalidate();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}	
		
	}

	
	private class DeleteRentalCopyListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e) {

			int row = tableAddRental.getSelectedRow();
			selectedRCData = comicCopyList.get(row); 
			ComicCopyController comicCopyController = new ComicCopyController();
			
    		int x = JOptionPane.showConfirmDialog(null, "Are you confirm?", "Delete Confirmation", JOptionPane.YES_NO_OPTION);

        	if(x == JOptionPane.YES_OPTION)
        	{      
        		totalPrice -= selectedRCData.getPrice();
    	    	txttotalprice.setText(Double.toString(totalPrice));
        		selectedRCData = rentalCopyList.remove(row);  
        		
        		selectedRCData.setCondition("Available");

    	    	int result = -1;
    	    	try {
    				if(selectedRCData.getId() == 0)
    					result = comicCopyController.insert(selectedRCData);
    				else
    					result = comicCopyController.update(selectedRCData);
    				
    				loadAddRentalList();
    				loadComicList("");
    			} catch (InputException e1) {
    				e1.displayMessage();
    			}
    			catch (ClassNotFoundException | SQLException e1) {
    				e1.printStackTrace();
    			}   	    	
        	}
        	else
        	{}
  	
		}
	}
	
	
	private class SubmitButtonListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{
			RentalController rController = new RentalController();
			RentalCopyController rcController = new RentalCopyController();
			Rental r = new Rental();
			RentalCopy rc = new RentalCopy();
			
			r.setRentFee(totalPrice);
			r.setOverdueFee(0.0);
			r.setUserId(userData.getId());
			int rentalId =-1;
			int result = -1;
	    	try {
				if(r.getId() == 0)
					result = rController.insert(r);
				else
					result = rController.update(r);
				
				if (result >= 1)
				{
					rentalId = result;
				}
				
			} catch (InputException e1) {
				e1.displayMessage();
			}
			catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
	    	
	    	for(int i=0; i < rentalCopyList.size(); i++)
	    	{
	    		rc.setRentalId(rentalId);
	    		rc.setComicCopyId(rentalCopyList.get(i).getId());
	    		result = -1;
		    	try {
					if(rc.getId() == 0)
						result = rcController.insert(rc);
					else
						result = rcController.update(rc);
					
				} catch (InputException e1) {
					e1.displayMessage();
				}
				catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
	    	}
	    	
	    	PopOutMsgBox.infoBox("This is your receipt.", "Rental Receipt");
	    	rentalCopyList.clear();
	    	
	    	try {
				CuViewReceiptDialog dialog = new CuViewReceiptDialog(rentalId, userData);
		    	dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		    	dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
	    	CuRentAddDialog.this.dispose();
	    	
	
		}
	}
	
	private class CancelButtonListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{
			if(rentalCopyList.size()==0)
			{
				rentalCopyList.clear();
		    	CuRentAddDialog.this.dispose();
			}
			else
			{
				int x = JOptionPane.showConfirmDialog(null, "Are you confirm?", "Cancel Confirmation", JOptionPane.YES_NO_OPTION);

	        	if(x == JOptionPane.YES_OPTION)
	        	{      
	        		ComicCopyController ccController = new ComicCopyController();
	        		
	        		for(int i=0;i<rentalCopyList.size();i++)
	        		{
	        			rentalCopyList.get(i).setCondition("Available");
	        			try {
							ccController.update(rentalCopyList.get(i));
						} catch (ClassNotFoundException | SQLException | InputException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	        			
	        		}
	        		rentalCopyList.clear();
			    	CuRentAddDialog.this.dispose();
	        	}
	        	else
	        	{}
			}			
		}
	}
	
	private class ViewComicDetailsButtonListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{
			int row = tableComic.getSelectedRow();
    		selectedCCData = comicCopyList.get(row);
	    	selectedComic = true;
	    	
	    	try {
				CuComicViewDetailsDialog dialog = new CuComicViewDetailsDialog(selectedCCData);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		    	dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
			}
		}
	}
	
	private class SearchButtonListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{
			String condition = "%s LIKE '%s'", searchType = "", strCond = "";
	    	String searchStr = searchtxt.getText();
	    	if((String)cmbSearchType.getSelectedItem() == "Comic ID")
	    	{
	    		searchType = "comic_copy.comic_id";
	    		strCond = searchStr;
	    	}
	    	else if((String)cmbSearchType.getSelectedItem() == "Title")
	    	{
	    		searchType = "title";
	    		strCond = "%" + searchStr + "%";
	    	}
	    	else if((String)cmbSearchType.getSelectedItem() == "Author")
	    	{
	    		searchType = "author";
	    		strCond = "%" + searchStr + "%";
	    	}
	    	else if((String)cmbSearchType.getSelectedItem() == "Genre")
	    	{
	    		searchType = "genre";
	    		strCond = "%" + searchStr + "%";
	    	}    	
	    	
	    	condition = String.format(condition,  searchType, strCond);
	    	loadComicList(condition);
		}
	}
	
	private class RefreshButtonListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{
	    	loadComicList("");
	    	searchtxt.setText("");
	    	selectedComic = false;
		}
	}
	
	
	
}
