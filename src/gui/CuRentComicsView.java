package gui;

import controller.*;
import model.*;
import java.util.ArrayList;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CuRentComicsView extends JFrame {
	
	private final int MAX_INT = 2147483467;
	private boolean selected = false;
	private RentalCopy selectedRental = new RentalCopy();
	private User userData = new User();
	private ArrayList<RentalCopy> rentalList = new ArrayList<RentalCopy>();
	private JTable tableRental;
	JCheckBox chckbxYear;
	JCheckBox chckbxMonth;
	JCheckBox chckbxDay;
	JSpinner spinneryear;
	JSpinner spinnermonth;
	JSpinner spinnerday;
	private boolean yearSelected =false;
	private boolean monthSelected =false;
	private boolean daySelected =false;
		
	public static void main(String[] args) {
		try {
			CuRentComicsView view = new CuRentComicsView(null);			
			view.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public CuRentComicsView(User user) {
		userData = user;
		setTitle("Rabbit Comics Rental System");
		setResizable(true);	
		//setBounds(100, 100, 829, 574);
		//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//setSize(screenSize.width-50, screenSize.height-50);
		setSize(1080, 720);
		
		setName("frame\r\n");
		getContentPane().setBackground(new Color(255, 255, 204));
		
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("C:\\Users\\user\\eclipse-workspace\\ComicRentalSystem\\"
				+ "images\\book.png").getImage().getScaledInstance(100, 91, Image.SCALE_DEFAULT));
		
		ImageIcon searchIcon = new ImageIcon(new ImageIcon("C:\\Users\\user\\eclipse-workspace\\ComicRentalSystem\\"
				+ "images\\search.png").getImage().getScaledInstance(18, 17, Image.SCALE_DEFAULT));
		
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(10, 7));
		panel.setBorder(null);
		panel.setBackground(new Color(255, 255, 204));
		JLabel image = new JLabel("");
		image.setHorizontalAlignment(SwingConstants.CENTER);
		image.setIcon(imageIcon);
		
		JLabel AdminPanelTitle = new JLabel("RENT COMICS");
		AdminPanelTitle.setVerticalAlignment(SwingConstants.BOTTOM);
		AdminPanelTitle.setOpaque(true);
		AdminPanelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		AdminPanelTitle.setFont(new Font("Sitka Subheading", Font.BOLD, 27));
		AdminPanelTitle.setBackground(new Color(255, 222, 179));
		
		JButton btnSearch = new JButton("");
		btnSearch.setEnabled(false);
		btnSearch.setIcon(searchIcon);
		btnSearch.addActionListener(new SearchButtonListener());
		
		spinneryear = new JSpinner();
		spinneryear.setEnabled(false);
		spinneryear.setModel(new SpinnerNumberModel(new Integer(2021), null, null, new Integer(1)));
		spinneryear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		spinnermonth = new JSpinner();
		spinnermonth.setEnabled(false);
		spinnermonth.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		spinnermonth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		spinnerday = new JSpinner();
		spinnerday.setEnabled(false);
		spinnerday.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		spinnerday.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		chckbxYear = new JCheckBox("Year:");
		chckbxYear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxYear.isSelected())
				{
					yearSelected = true;
					spinneryear.setEnabled(true);
					btnSearch.setEnabled(true);
				}			
				else if(!chckbxYear.isSelected() && !monthSelected && !daySelected)
				{
					yearSelected = false;
					spinneryear.setEnabled(false);
					btnSearch.setEnabled(false);
				}
				else
				{
					yearSelected = false;
					spinneryear.setEnabled(false);
				}
			}
		});
		chckbxYear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxYear.setBackground(new Color(255, 255, 204));
		
		chckbxMonth = new JCheckBox("Month:");
		chckbxMonth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxMonth.setBackground(new Color(255, 255, 204));
		chckbxMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxMonth.isSelected())
				{
					monthSelected = true;
					spinnermonth.setEnabled(true);
					btnSearch.setEnabled(true);
				}	
				else if(!chckbxMonth.isSelected() && !yearSelected && !daySelected)
				{
					monthSelected = false;
					spinnermonth.setEnabled(false);
					btnSearch.setEnabled(false);
				}
				else if(!chckbxMonth.isSelected() && yearSelected && daySelected)
				{
					monthSelected = false;
					spinnermonth.setEnabled(false);
					btnSearch.setEnabled(false);
				}
				else
				{
					monthSelected = false;
					spinnermonth.setEnabled(false);
				}
			}
		});
		
		chckbxDay = new JCheckBox("Day:");
		chckbxDay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxDay.setBackground(new Color(255, 255, 204));
		chckbxDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxDay.isSelected())
				{
					daySelected = true;
					spinnerday.setEnabled(true);
					btnSearch.setEnabled(true);
				}
				else if(!chckbxDay.isSelected() && !yearSelected && !monthSelected)
				{
					daySelected = false;
					spinnerday.setEnabled(false);
					btnSearch.setEnabled(false);
				}		
				else
				{
					daySelected = false;
					spinnerday.setEnabled(false);
				}
			}
		});
		
		JLabel lblNewLabel = new JLabel("Search By:");
		lblNewLabel.setFont(new Font("Sitka Subheading", Font.PLAIN, 19));
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(484)
							.addComponent(image, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(394)
							.addComponent(AdminPanelTitle, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(196)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addGap(22)
							.addComponent(chckbxYear, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinneryear, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addGap(34)
							.addComponent(chckbxMonth, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinnermonth, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
							.addGap(34)
							.addComponent(chckbxDay, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinnerday, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addGap(28)
							.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(814, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(image, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(AdminPanelTitle, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(spinnerday, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addComponent(spinnermonth, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addComponent(spinneryear, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel)
							.addComponent(chckbxMonth, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addComponent(chckbxYear)
							.addComponent(chckbxDay, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnRefreshTable = new JButton("Refresh Table");
		btnRefreshTable.setVerticalAlignment(SwingConstants.BOTTOM);
		btnRefreshTable.setFont(new Font("Sitka Subheading", Font.PLAIN, 17));
		btnRefreshTable.addActionListener(new RefreshButtonListener());
		
		JButton btnAddRental = new JButton("Add Rental");
		btnAddRental.setFont(new Font("Sitka Subheading", Font.PLAIN, 17));
		btnAddRental.addActionListener(new AddRentalButtonListener());
		
		JButton btnViewDetails = new JButton("View Details");
		btnViewDetails.setFont(new Font("Sitka Subheading", Font.PLAIN, 17));
		btnViewDetails.addActionListener(new ViewRentalDetailsButtonListener());
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerPanelView frame = new CustomerPanelView(user);
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
					.addGap(196)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(btnRefreshTable)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 672, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnViewDetails, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddRental, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnAddRental, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addGap(54)
							.addComponent(btnViewDetails, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addGap(191))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 332, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRefreshTable, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(34, Short.MAX_VALUE))
		);
		
		tableRental = new JTable();
		scrollPane.setViewportView(tableRental);
		tableRental.setModel(new RentalTableModel());
		tableRental.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableRental.setFont(new Font("Arial", Font.PLAIN, 13));
		getContentPane().setLayout(groupLayout);
		
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		loadRentalList(String.format("user_id = %d", user.getId()));
	}
	
	private class RentalTableModel implements TableModel 
	{   
		String header[] = new String[] { "No", "Rental ID", "Rent Date", "Expected Return Date", "Quantity", "Rent Fee", "Status"};
    
		 public int getColumnCount()
		 {
		     return header.length;
		 }
		   
		 public int getRowCount()
		 {
		     return rentalList.size();
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
			 RentalCopy rp = rentalList.get(rowIndex);
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			 switch(columnIndex)
			 {						
			 	case 0: return rowIndex+1;
			  	case 1: return rp.getRentalId();
			  	case 2: return (rp.getRentDate().format(formatter));
			   	case 3: return (rp.getExpectedDate().format(formatter));
			   	case 4: return rp.getQuantity();
			   	case 5: return rp.getRentFee();
				case 6: return rp.getStatus();
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
	
	public void loadRentalList(String condition)
	{
		RentalCopyController rentalCopyController = new RentalCopyController();
		rentalList.clear();
		try {
				rentalList = rentalCopyController.selectWhereCusRental(condition, 0, MAX_INT);
			tableRental.repaint();
			tableRental.revalidate();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}	
	}	
	
	private class AddRentalButtonListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{
			try {
				CuRentAddDialog dialog = new CuRentAddDialog(userData);
		    	dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		    	dialog.setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	private class ViewRentalDetailsButtonListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{
			int row = tableRental.getSelectedRow();
	    	if(row < 0)
	    		return;
    		int rental_id = (int) tableRental.getValueAt(row, 1);
    		String condition = String.format("user_id = %d AND rental_id = %d", userData.getId(), rental_id);
    		RentalCopyController rentalCopyController = new RentalCopyController();
    		try {
    			selectedRental = rentalCopyController.selectWhereCusRental(condition, 0, 1).get(0);
    			selected = true;
    		} catch (ClassNotFoundException | SQLException e1) {
    			e1.printStackTrace();
    		}
    		
    		try {
				CuViewRentalDetailsDialog dialog = new CuViewRentalDetailsDialog(selectedRental);
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
			loadRentalList(String.format("user_id = %d", userData.getId()));
	    	selected = false;
		}
	}
	
	private class SearchButtonListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{
			String condition = "user_id = %d AND rent_date LIKE '%s%s%s'", userId, strYr = "", strMth = "", strDay = "" ;
			
			if(yearSelected)
			{
				strYr = spinneryear.getValue().toString();
				strYr.replace(",", "");
			}
			else if(!yearSelected&& !monthSelected)
				strYr = "%";
			else if(!yearSelected && monthSelected)
				strYr = "%";
			
			if(monthSelected)
			{
				int month = (int)spinnermonth.getValue();
				if(month<10)
					strMth = String.format("-0" + month);
				else
					strMth = String.format("-" + month);
			}
			else if(!yearSelected && !monthSelected)
				strMth = "";
			else if(!yearSelected && monthSelected)
				strMth = "%";
			
			if(daySelected)
			{
				int day = (int)spinnerday.getValue();
				if(day<10)
				{
					strDay = String.format("-0" + day);
					strDay = strDay + "%";
				}
					
				else
				{
					strDay = String.format("-" + day);
					strDay = strDay + "%";
				}
					
			}
			else if(!daySelected)
				strDay = "%";

			condition = String.format(condition, userData.getId(), strYr, strMth, strDay);		
	    	
	    	loadRentalList(condition);

		}
	}
}
	


