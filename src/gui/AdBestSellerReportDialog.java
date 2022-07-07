package gui;

import controller.ComicCopyController;
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

public class AdBestSellerReportDialog extends JDialog {
	private final int MAX_INT = 2147483467;
	private ArrayList<ComicCopy> comicCopyList = new ArrayList<ComicCopy>();
	private JTable tableBestSeller;
	
	public AdBestSellerReportDialog() {		
		
		getContentPane().setBackground(new Color(255, 255, 204));		
		
		ImageIcon searchIcon = new ImageIcon(new ImageIcon("C:\\Users\\user\\eclipse-workspace\\ComicRentalSystem\\"
				+ "images\\search.png").getImage().getScaledInstance(18, 17, Image.SCALE_DEFAULT));
		
		JButton btnCancel = new JButton("Close");
		btnCancel.setVerticalAlignment(SwingConstants.BOTTOM);
		btnCancel.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		btnCancel.addActionListener(new CancelButtonListener());
		
		JLabel AdminPanelTitle = new JLabel("ALL TIME BEST SELLER");
		AdminPanelTitle.setVerticalAlignment(SwingConstants.BOTTOM);
		AdminPanelTitle.setOpaque(true);
		AdminPanelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		AdminPanelTitle.setFont(new Font("Sitka Subheading", Font.BOLD, 27));
		AdminPanelTitle.setBackground(new Color(255, 222, 179));
		
		JScrollPane scrollPane = new JScrollPane();
		
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
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 619, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(98, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(33)
					.addComponent(AdminPanelTitle, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addGap(38)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
					.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addGap(44))
		);
		
		tableBestSeller = new JTable();
		scrollPane.setViewportView(tableBestSeller);
		tableBestSeller.setModel(new BestSellerTableModel());
		tableBestSeller.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableBestSeller.setFont(new Font("Arial", Font.PLAIN, 13));

		getContentPane().setLayout(groupLayout);
		setTitle("Best Seller Report");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 829, 574);
		setLocationRelativeTo(null);
		
		loadBestSellerList("");
		
	}

	private class BestSellerTableModel implements TableModel 
	{   
		String header[] = new String[] { "Comic ID", "Title", "Author", "Genre", "Rent Count"};
	    
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
			 ComicCopy rp = comicCopyList.get(rowIndex);
			 switch(columnIndex)
			 {
			  	case 0: return rp.getComicId();
			  	case 1: return rp.getTitle();
			  	case 2: return rp.getAuthor();
			  	case 3: return rp.getGenre();	  	
			   	case 4: return rp.getId();
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
	
	public void loadBestSellerList(String condition1)
	{
		ComicCopyController comicCopyController = new ComicCopyController();
		comicCopyList.clear();
		try {
			comicCopyList = comicCopyController.selectBestSeller(0, MAX_INT);
			
			tableBestSeller.repaint();
			tableBestSeller.revalidate();
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
	    	AdBestSellerReportDialog.this.dispose();
		}
	}
}
