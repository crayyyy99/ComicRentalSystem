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

public class AdSalesReportMonthlyDialog extends JDialog {
	private final int MAX_INT = 2147483467;
	private ArrayList<String> genreList = new ArrayList<String>();
	private RentalCopyController rentalCopyController = new RentalCopyController();
	private ArrayList<RentalCopy> rentalCopyList = new ArrayList<RentalCopy>();
	private JComboBox cmbMonth;
	private JComboBox cmbYear;
	private JTable tableMonthlySales;
	private JTextField TotalSalestxt;
	private JTextField salesCounttxt;
	
	public AdSalesReportMonthlyDialog() {		
		
		getContentPane().setBackground(new Color(255, 255, 204));		
		
		ImageIcon searchIcon = new ImageIcon(new ImageIcon("C:\\Users\\user\\eclipse-workspace\\ComicRentalSystem\\"
				+ "images\\search.png").getImage().getScaledInstance(18, 17, Image.SCALE_DEFAULT));
		
		JButton btnCancel = new JButton("Close");
		btnCancel.setVerticalAlignment(SwingConstants.BOTTOM);
		btnCancel.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		btnCancel.addActionListener(new CancelButtonListener());
		
		JLabel AdminPanelTitle = new JLabel("MONTHLY SALES REPORT");
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
		
		JLabel lblNewLabel = new JLabel("Total Sales of the Month (RM):");
		lblNewLabel.setFont(new Font("Sitka Subheading", Font.PLAIN, 15));
		
		TotalSalestxt = new JTextField();
		TotalSalestxt.setEditable(false);
		TotalSalestxt.setFont(new Font("Arial", Font.PLAIN, 14));
		TotalSalestxt.setColumns(10);
		
		JLabel lblSalesCount = new JLabel("Sales Count Per Month:");
		lblSalesCount.setFont(new Font("Sitka Subheading", Font.PLAIN, 15));
		
		salesCounttxt = new JTextField();
		salesCounttxt.setText("0");
		salesCounttxt.setFont(new Font("Arial", Font.PLAIN, 14));
		salesCounttxt.setEditable(false);
		salesCounttxt.setColumns(10);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(98)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblSalesCount, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(TotalSalestxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(salesCounttxt, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, 284, Short.MAX_VALUE))))
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 619, GroupLayout.PREFERRED_SIZE)))
							.addGap(10))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(228)
							.addComponent(lblTotalSales, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cmbMonth, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(cmbYear, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(198)
							.addComponent(AdminPanelTitle, GroupLayout.PREFERRED_SIZE, 418, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(88, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addComponent(AdminPanelTitle, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblTotalSales, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addComponent(cmbMonth, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addComponent(cmbYear, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 258, GroupLayout.PREFERRED_SIZE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSalesCount, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(salesCounttxt, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(TotalSalestxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel))
							.addGap(53))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addGap(30))))
		);
		
		tableMonthlySales = new JTable();
		scrollPane.setViewportView(tableMonthlySales);
		tableMonthlySales.setModel(new MonthlySalesTableModel());
		tableMonthlySales.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableMonthlySales.setFont(new Font("Arial", Font.PLAIN, 13));

		getContentPane().setLayout(groupLayout);
		setTitle("Monthy Sales Report");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 829, 574);
		setLocationRelativeTo(null);
		
		loadMonthlySalesList("", "");
		
	}

	private class MonthlySalesTableModel implements TableModel 
	{   
		String header[] = new String[] {"No.", "Date", "Sales (RM)", "Sales Count"};
	    
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
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
			 RentalCopy rp = rentalCopyList.get(rowIndex);
			 switch(columnIndex)
			 {
			 	case 0: return rowIndex+1;
			    case 1: return (rp.getRentDate().format(formatter));
			  	case 2: return rp.getRentFee();
			  	case 3: return rp.getQuantity();
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
			String condition1 = (String)cmbYear.getEditor().getItem();
			String condition2 = null;
			
	    	if((String)cmbMonth.getSelectedItem() == "Jan")
	    	{
	    		condition2 = "01";
	    	}
	    	else if((String)cmbMonth.getSelectedItem() == "Feb")
	    	{
	    		condition2 = "02";
	    	}
	    	else if((String)cmbMonth.getSelectedItem() == "Mar")
	    	{
	    		condition2 = "03";
	    	}
	    	else if((String)cmbMonth.getSelectedItem() == "Apr")
	    	{
	    		condition2 = "04";
	    	}
	    	else if((String)cmbMonth.getSelectedItem() == "May")
	    	{
	    		condition2 = "05";
	    	}
	    	else if((String)cmbMonth.getSelectedItem() == "Jun")
	    	{
	    		condition2 = "06";
	    	}
	    	else if((String)cmbMonth.getSelectedItem() == "Jul")
	    	{
	    		condition2 = "07";
	    	}
	    	else if((String)cmbMonth.getSelectedItem() == "Aug")
	    	{
	    		condition2 = "08";
	    	}
	    	else if((String)cmbMonth.getSelectedItem() == "Sep")
	    	{
	    		condition2 = "09";
	    	}
	    	else if((String)cmbMonth.getSelectedItem() == "Oct")
	    	{
	    		condition2 = "10";
	    	}
	    	else if((String)cmbMonth.getSelectedItem() == "Nov")
	    	{
	    		condition2 = "11";
	    	}
	    	else if((String)cmbMonth.getSelectedItem() == "Dec")
	    	{
	    		condition2 = "12";
	    	}
	    	
	    	loadMonthlySalesList(condition1, condition2);	    	    	
		}
	}
	
	public void loadMonthlySalesList(String condition1, String condition2)
	{
		rentalCopyList.clear();
		try {			
			rentalCopyList = rentalCopyController.selectMonthlySales(0, MAX_INT, condition1, condition2);
			TotalSalestxt.setText(String.valueOf(rentalCopyController.getTotalMonthlySales(0, MAX_INT, condition1, condition2)));
			salesCounttxt.setText(String.valueOf(rentalCopyController.getMonthlySalesCount(condition1, condition2)));
			tableMonthlySales.repaint();
			tableMonthlySales.revalidate();
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
	    	AdSalesReportMonthlyDialog.this.dispose();
		}
	}
}
