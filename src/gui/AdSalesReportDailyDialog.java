package gui;

import controller.RentalCopyController;
import controller.UserController;
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

public class AdSalesReportDailyDialog extends JDialog {
	private final int MAX_INT = 2147483467;
	private ArrayList<String> genreList = new ArrayList<String>();
	private RentalCopyController rentalCopyController = new RentalCopyController();
	private ArrayList<RentalCopy> rentalCopyList = new ArrayList<RentalCopy>();
	private JComboBox cmbDay;
	private JComboBox cmbMonth;
	private JComboBox cmbYear;
	private JTable tableDailySales;
	private JTextField TotalSalestxt;
	private JTextField salescounttxt;
	
	public AdSalesReportDailyDialog() {		
		
		getContentPane().setBackground(new Color(255, 255, 204));		
		
		ImageIcon searchIcon = new ImageIcon(new ImageIcon("C:\\Users\\user\\eclipse-workspace\\ComicRentalSystem\\"
				+ "images\\search.png").getImage().getScaledInstance(18, 17, Image.SCALE_DEFAULT));
		
		JButton btnCancel = new JButton("Close");
		btnCancel.setVerticalAlignment(SwingConstants.BOTTOM);
		btnCancel.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		btnCancel.addActionListener(new CancelButtonListener());
		
		JLabel AdminPanelTitle = new JLabel("DAILY SALES REPORT");
		AdminPanelTitle.setVerticalAlignment(SwingConstants.BOTTOM);
		AdminPanelTitle.setOpaque(true);
		AdminPanelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		AdminPanelTitle.setFont(new Font("Sitka Subheading", Font.BOLD, 27));
		AdminPanelTitle.setBackground(new Color(255, 222, 179));
		
		JLabel lblTotalSales = new JLabel("Search By:");
		lblTotalSales.setFont(new Font("Sitka Subheading", Font.PLAIN, 17));
		
		cmbMonth = new JComboBox();
		cmbMonth.setFont(new Font("Arial", Font.PLAIN, 15));
		cmbMonth.setModel(new DefaultComboBoxModel(new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}));
		cmbMonth.setSelectedIndex(-1);
		
		JButton btnSearch = new JButton("");
		btnSearch.setIcon(searchIcon);
		btnSearch.addActionListener(new SearchButtonListener());
		
		JScrollPane scrollPane = new JScrollPane();
		
		cmbYear = new JComboBox();
		cmbYear.setModel(new DefaultComboBoxModel(new String[] {"2021"}));
		cmbYear.setEditable(true);
		cmbYear.setFont(new Font("Arial", Font.PLAIN, 15));
		cmbYear.setSelectedIndex(-1);
		
		JLabel lblNewLabel = new JLabel("Total Sales of the Day (RM):");
		lblNewLabel.setFont(new Font("Sitka Subheading", Font.PLAIN, 15));
		
		TotalSalestxt = new JTextField();
		TotalSalestxt.setEditable(false);
		TotalSalestxt.setFont(new Font("Arial", Font.PLAIN, 14));
		TotalSalestxt.setColumns(10);
		
		cmbDay = new JComboBox();
		cmbDay.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));	
		cmbDay.setFont(new Font("Arial", Font.PLAIN, 15));
		cmbDay.setSelectedIndex(-1);
		
		JLabel lblSalesCount = new JLabel("Sales Count Per Day:");
		lblSalesCount.setFont(new Font("Sitka Subheading", Font.PLAIN, 15));
		
		salescounttxt = new JTextField();
		salescounttxt.setText("0");
		salescounttxt.setFont(new Font("Arial", Font.PLAIN, 14));
		salescounttxt.setEditable(false);
		salescounttxt.setColumns(10);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(198)
							.addComponent(AdminPanelTitle, GroupLayout.PREFERRED_SIZE, 418, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(98)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(TotalSalestxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 619, GroupLayout.PREFERRED_SIZE)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblSalesCount, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(salescounttxt, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(204)
							.addComponent(lblTotalSales, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cmbDay, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cmbMonth, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cmbYear, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(98, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addComponent(AdminPanelTitle, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblTotalSales, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addComponent(cmbDay, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addComponent(cmbMonth, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addComponent(cmbYear, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSalesCount, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(salescounttxt, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(TotalSalestxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(28, Short.MAX_VALUE))
		);
		
		tableDailySales = new JTable();
		scrollPane.setViewportView(tableDailySales);
		tableDailySales.setModel(new DailySalesTableModel());
		tableDailySales.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableDailySales.setFont(new Font("Arial", Font.PLAIN, 13));

		getContentPane().setLayout(groupLayout);
		setTitle("Daily Sales Report");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 829, 574);
		setLocationRelativeTo(null);
		
		loadDailySalesList("", "", "");
		
	}

	private class DailySalesTableModel implements TableModel 
	{   
		String header[] = new String[] {"No.", "Date", "Sales (RM)"};
    
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
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
			 RentalCopy rp = rentalCopyList.get(rowIndex);
			 switch(columnIndex)
			 {
			 	case 0: return rowIndex+1;
			    case 1: return (rp.getRentDate().format(formatter));
			  	case 2: return rp.getRentFee();
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
	
	private class SearchButtonListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{
			String year = (String)cmbYear.getEditor().getItem();
			String month = null;
			String day = (String)cmbDay.getSelectedItem();
			
	    	if((String)cmbMonth.getSelectedItem() == "Jan")
	    	{
	    		month = "01";
	    	}
	    	else if((String)cmbMonth.getSelectedItem() == "Feb")
	    	{
	    		month = "02";
	    	}
	    	else if((String)cmbMonth.getSelectedItem() == "Mar")
	    	{
	    		month = "03";
	    	}
	    	else if((String)cmbMonth.getSelectedItem() == "Apr")
	    	{
	    		month = "04";
	    	}
	    	else if((String)cmbMonth.getSelectedItem() == "May")
	    	{
	    		month = "05";
	    	}
	    	else if((String)cmbMonth.getSelectedItem() == "Jun")
	    	{
	    		month = "06";
	    	}
	    	else if((String)cmbMonth.getSelectedItem() == "Jul")
	    	{
	    		month = "07";
	    	}
	    	else if((String)cmbMonth.getSelectedItem() == "Aug")
	    	{
	    		month = "08";
	    	}
	    	else if((String)cmbMonth.getSelectedItem() == "Sep")
	    	{
	    		month = "09";
	    	}
	    	else if((String)cmbMonth.getSelectedItem() == "Oct")
	    	{
	    		month = "10";
	    	}
	    	else if((String)cmbMonth.getSelectedItem() == "Nov")
	    	{
	    		month = "11";
	    	}
	    	else if((String)cmbMonth.getSelectedItem() == "Dec")
	    	{
	    		month = "12";
	    	}
	    	
	    	loadDailySalesList(year, month, day);    		    	
		}
	}
	
	public void loadDailySalesList(String condition1, String condition2, String condition3)
	{
		rentalCopyList.clear();
		try {
			rentalCopyList = rentalCopyController.selectDailySales(0, MAX_INT, condition1, condition2, condition3);
			TotalSalestxt.setText(String.valueOf(rentalCopyController.getTotalDailySales(0, MAX_INT, condition1, condition2, condition3)));
			salescounttxt.setText(String.valueOf(rentalCopyList.size()));
			tableDailySales.repaint();
			tableDailySales.revalidate();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	private class CancelButtonListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{
	    	AdSalesReportDailyDialog.this.dispose();
		}
	}
}

