package gui;

import controller.ComicGenreController;
import model.ComicGenre;
import java.util.ArrayList;
import java.sql.SQLException;
import exception.InputException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class AdGenreEditDialog extends JDialog {
	private final int MAX_INT = 2147483467;
	private boolean selected = false;
	private ComicGenre selectedComicGenre = new ComicGenre();
	private ArrayList<ComicGenre> comicGenreList = new ArrayList<ComicGenre>();
	private JTextField txtgenre;
	private JTable tableComicGenre;
	private JButton btnEdit;
	private JButton btnAdd;
	private boolean editSelected = false;
	private boolean addSelected = false;
	
	public AdGenreEditDialog() {		
		
		getContentPane().setBackground(new Color(255, 255, 204));
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new SaveButtonListener());
		btnSave.setVerticalAlignment(SwingConstants.BOTTOM);
		btnSave.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new CancelButtonListener());
		btnCancel.setVerticalAlignment(SwingConstants.BOTTOM);
		btnCancel.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		
		JScrollPane scrollPane = new JScrollPane();
		
		txtgenre = new JTextField();
		txtgenre.setEditable(false);
		txtgenre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtgenre.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("New Genre:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new EditButtonListener());
		btnEdit.setVerticalAlignment(SwingConstants.BOTTOM);
		btnEdit.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		
		btnAdd = new JButton("Add");
		btnAdd.setVerticalAlignment(SwingConstants.BOTTOM);
		btnAdd.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		btnAdd.addActionListener(new AddButtonListener());
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(47)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txtgenre, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 402, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(47, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(52)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(txtgenre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSave))
					.addGap(31))
		);
		
		tableComicGenre = new JTable();
		tableComicGenre.setFont(new Font("Arial", Font.PLAIN, 13));
		tableComicGenre.setModel(new ComicGenreTableModel());
		tableComicGenre.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tableComicGenre);
		getContentPane().setLayout(groupLayout);
		setTitle("Edit Genre");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 510, 600);
		setLocationRelativeTo(null);
		
		loadComicGenreList("");
				
	}
	
	private class ComicGenreTableModel implements TableModel 
	{   
		String header[] = new String[] { "Genre ID", "Genre"};
    
		 public int getColumnCount() 
		 {
		     return header.length;
		 }
		   
		 public int getRowCount() 
		 {
		     return comicGenreList.size();
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
			 ComicGenre cg = comicGenreList.get(rowIndex);
			 switch(columnIndex)
			 {
			  	case 0: return cg.getId();
			   	case 1: return cg.getGenre();
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

	
	public void loadComicGenreList(String condition)
	{
		ComicGenreController comicGenreController = new ComicGenreController();
		comicGenreList.clear();
		try {
			if(condition == "")
				comicGenreList = comicGenreController.selectAll(0, MAX_INT);
			else
				comicGenreList = comicGenreController.selectWhere(condition, 0, MAX_INT);
			tableComicGenre.repaint();
			tableComicGenre.revalidate();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}	
		
	}

	private class SaveButtonListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{
			
				ComicGenre cg = new ComicGenre();
				if(editSelected)
					selectedComicGenre.setGenre(txtgenre.getText());
				if(addSelected)
					cg.setGenre(txtgenre.getText());
					
				ComicGenreController comicGenreController = new ComicGenreController();
		    	int result = -1;
		    	try {
					if(addSelected)
						result = comicGenreController.insert(cg);
					else if(editSelected)
						result = comicGenreController.update(selectedComicGenre);
					
					if (result >= 1)
					{
						if(editSelected)
							PopOutMsgBox.infoBox("Record has been updated.", "Update Successful");
						else if(addSelected)
							PopOutMsgBox.infoBox("New genre has been added.", "Add Successful");
						txtgenre.setText("");
						txtgenre.setEditable(false);
						selected = false;
						btnAdd.setEnabled(true);
						btnEdit.setEnabled(true);
						loadComicGenreList("");
						
					}
				} catch (InputException e1) {
					e1.displayMessage();
				}
				catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}

		}
	}
	
	private class CancelButtonListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{
	    	AdGenreEditDialog.this.dispose();
		}
	}

	private class AddButtonListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{
			txtgenre.setEditable(true);
			addSelected = true;
			editSelected = false;
			btnEdit.setEnabled(false);
		}
	}
	
	private class EditButtonListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{
			addSelected = false;
			editSelected = true;
			btnAdd.setEnabled(false);
			
	    	int index = tableComicGenre.getSelectedRow();
	    	if(index < 0)
	    		return;
    		int id = (int) tableComicGenre.getValueAt(index, 0);	 
    		String condition = String.format("id = %d", id);
    		ComicGenreController comicGenreController = new ComicGenreController();
    		try {
    			selectedComicGenre = comicGenreController.selectWhere(condition, 0, 1).get(0);
    			selected = true;
    			if(selected)
    				txtgenre.setEditable(true);
    		} catch (ClassNotFoundException | SQLException e1) {
    			e1.printStackTrace();
    		}	    
		}
		
	}
}
