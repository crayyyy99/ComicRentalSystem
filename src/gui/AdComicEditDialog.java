package gui;

import controller.ComicController;
import controller.ComicCopyController;
import controller.ComicGenreController;
import controller.UserController;
import model.Comic;
import model.ComicCopy;
import model.ComicGenre;
import model.User;

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

public class AdComicEditDialog extends JDialog {
	private final int MAX_INT = 2147483467;
	private boolean selected = false;
	private ComicCopy selectedCC = new ComicCopy();
	private ArrayList<String> genreList = new ArrayList<String>();
	private ArrayList<ComicCopy> comicCopyList = new ArrayList<ComicCopy>();
	private JTextField txtcomicid;
	private JComboBox cmbgenre;
	private JTextField txtauthor;
	private JSpinner spinnerprice;
	private JButton btnAddGenre;
	private JComboBox cmbcondition;
	private JButton btnSaveCopy;
	private JTextField txttitle;
	private JTable tableComicCopy;
	private JTextArea txtdescription;
	
	public AdComicEditDialog(ComicCopy comicCopy) {		
		
		selectedCC = comicCopy;
		
		getContentPane().setBackground(new Color(255, 255, 204));
		
		txtcomicid = new JTextField();
		txtcomicid.setEditable(false);
		txtcomicid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtcomicid.setColumns(10);
		
		cmbgenre = new JComboBox();
		cmbgenre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		spinnerprice = new JSpinner();
		spinnerprice.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		spinnerprice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtauthor = new JTextField();
		txtauthor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtauthor.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.setVerticalAlignment(SwingConstants.BOTTOM);
		btnSave.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		btnSave.addActionListener(new SaveButtonListener());
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setVerticalAlignment(SwingConstants.BOTTOM);
		btnCancel.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		btnCancel.addActionListener(new CancelButtonListener());
		
		btnAddGenre = new JButton("Add");
		btnAddGenre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmbgenre.setEditable(true);
			}
		});
		btnAddGenre.setVerticalAlignment(SwingConstants.BOTTOM);
		btnAddGenre.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		
		JLabel lblNewLabel_1 = new JLabel("Comic ID:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_2 = new JLabel("Title:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_3 = new JLabel("Genre:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_4 = new JLabel("Author:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_5 = new JLabel("Price:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_6 = new JLabel("Description:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txttitle = new JTextField();
		txttitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txttitle.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 204, 153));
		
		JButton btnSelectCopy = new JButton("Select ");
		btnSelectCopy.setVerticalAlignment(SwingConstants.BOTTOM);
		btnSelectCopy.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		btnSelectCopy.addActionListener(new SelectButtonListener());
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(46)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
							.addGap(4)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_1)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(cmbgenre, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnAddGenre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addComponent(spinnerprice, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
									.addComponent(txtauthor, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txttitle, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtcomicid, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(panel, 0, 0, Short.MAX_VALUE)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnSelectCopy)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)))
					.addGap(47))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(75)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtcomicid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(txttitle, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGap(24)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(cmbgenre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAddGenre))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtauthor, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(spinnerprice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 162, Short.MAX_VALUE))
								.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnSelectCopy)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))))
					.addGap(53))
		);
		
		txtdescription = new JTextArea();
		scrollPane_1.setViewportView(txtdescription);
		
		tableComicCopy = new JTable();
		tableComicCopy.setFont(new Font("Arial", Font.PLAIN, 13));
		tableComicCopy.setModel(new ComicCopyTableModel());
		tableComicCopy.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tableComicCopy);
		
		JLabel lblNewLabel_6_1 = new JLabel("Condition:");
		lblNewLabel_6_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		cmbcondition = new JComboBox();
		cmbcondition.setEnabled(false);
		cmbcondition.setModel(new DefaultComboBoxModel(new String[] {"Available", "Rented", "Broken"}));
		cmbcondition.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cmbcondition.setSelectedIndex(-1);
		
		btnSaveCopy = new JButton("Save Copy");
		btnSaveCopy.setEnabled(false);
		btnSaveCopy.setVerticalAlignment(SwingConstants.BOTTOM);
		btnSaveCopy.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		btnSaveCopy.addActionListener(new SaveCopyButtonListener());
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_6_1, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cmbcondition, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(86, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(189, Short.MAX_VALUE)
					.addComponent(btnSaveCopy)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_6_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbcondition, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSaveCopy, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		setTitle("Edit Comic");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 574);
		setLocationRelativeTo(null);
			
		loadGenre();
		loadComicCopyList("comic_id = "+ selectedCC.getComicId());
		
		if(selectedCC!= null)
			loadComicData(selectedCC);
		
		
	}

	public void loadComicData(ComicCopy cc) {
		txtcomicid.setText(String.valueOf(cc.getComicId()));
		txttitle.setText(cc.getTitle());
		cmbgenre.setSelectedItem(cc.getGenre());
		txtauthor.setText(cc.getAuthor());
		spinnerprice.setValue(cc.getPrice());
		txtdescription.setText(cc.getDescription());	
	}
	
	public void loadComicCopyCondition(ComicCopy cc) {
		cmbcondition.setSelectedItem(cc.getCondition());
	}
	
	public void loadComicCopyList(String condition)
	{
		ComicCopyController ccController = new ComicCopyController();
		comicCopyList.clear();
		try {
			if(condition == "")
				comicCopyList = ccController.selectAllList(0, MAX_INT);
			else
				comicCopyList = ccController.selectWhereList(condition, 0, MAX_INT);
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
		String header[] = new String[] { "Copy ID", "Condition"};
    
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
			  	case 0: return cc.getCopyId();
			   	case 1: return cc.getCondition();
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
	
	public void loadGenre() {
		
		ComicGenreController comicGenreController = new ComicGenreController();
		try {
			genreList = comicGenreController.selectAllGenre(0, MAX_INT);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String[] array = genreList.toArray(String[]::new);
		cmbgenre.setModel(new DefaultComboBoxModel(array));
	}
	
	private class SaveButtonListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{
			ComicGenreController cgController = new ComicGenreController();
			ComicController cController = new ComicController();
			ComicGenre cg = new ComicGenre();
			Comic c = new Comic();
			
				c.setId(selectedCC.getComicId());
				c.setTitle(txttitle.getText());
						
				if(btnAddGenre.isSelected())
					cg.setGenre((String)cmbgenre.getEditor().getItem());
				else
					cg.setGenre((String)cmbgenre.getSelectedItem());
					
				try {
					boolean	found = cgController.genreExist(cg.getGenre());
					
						if(!found)
						{
							int result = -1;
					    	try {
								if(cg.getId() == 0)
									result = cgController.insert(cg);
								else
									result = cgController.update(cg);
								
								if (result >= 1)
								{
									c.setGenreId(result);
								}
					    	}
					    	catch (InputException e1) {
								e1.displayMessage();
							}
							catch (ClassNotFoundException | SQLException e1) {
								e1.printStackTrace();
							}

						}
						else
						{
							try {
								cg = cgController.selectWhere(" genre = '"+ cg.getGenre() + "'" , 0, 1).get(0);
								c.setGenreId(cg.getId());
							} catch (ClassNotFoundException | SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						
				} catch (ClassNotFoundException | SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
							
				c.setAuthor(txtauthor.getText());
				c.setPrice((double)spinnerprice.getValue());
				c.setDescription(txtdescription.getText());
				
				int result = -1;
		    	try {
					if(c.getId() == 0)
						result = cController.insert(c);
					else
						result = cController.update(c);
					
					if (result >= 1)
					{
						PopOutMsgBox.infoBox("Record has been updated. Please refresh your table.", "Updated Successful");
						AdComicEditDialog.this.dispose();
					}
				} catch (InputException e3) {
					e3.displayMessage();
				}
				catch (ClassNotFoundException | SQLException e3) {
					e3.printStackTrace();
				}
		    	
			}
			
		}
	
	private class SaveCopyButtonListener implements ActionListener {		
		public void actionPerformed(ActionEvent e)
		{		
			selectedCC.setCondition((String)cmbcondition.getSelectedItem());
			ComicCopyController ccController = new ComicCopyController();
	    	int result = -1;
	    	try {
				result = ccController.update(selectedCC);
				if (result >= 1)
				{
					PopOutMsgBox.infoBox("Copy has been updated", "Update Successful");
					cmbcondition.setSelectedItem("");
					cmbcondition.setEnabled(false);
					btnSaveCopy.setEnabled(false);
					selected = false;
					cmbcondition.setSelectedIndex(-1);
					cmbcondition.setEnabled(false);
					btnSaveCopy.setEnabled(false);
					loadComicCopyList("comic_id = "+ selectedCC.getComicId());
				}
				
			} catch (InputException e1) {
				e1.displayMessage();
			}
			catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		}		
	}

	private class SelectButtonListener implements ActionListener {		
		public void actionPerformed(ActionEvent e)
		{
			int index = tableComicCopy.getSelectedRow();
	    	if(index < 0)
	    		return;
    		int id = (int) tableComicCopy.getValueAt(index, 0);	 
    		String condition = String.format("comic_copy.copy_id = %d AND comic_copy.comic_id = %d ", id, selectedCC.getComicId());
    		ComicCopyController ccController = new ComicCopyController();
    		try {
    			selectedCC = ccController.selectWhereList(condition, 0, 1).get(0);
    			selected = true;
    			cmbcondition.setEnabled(true);
				btnSaveCopy.setEnabled(true);
				loadComicCopyCondition(selectedCC);
				    			
    		} catch (ClassNotFoundException | SQLException e1) {
    			e1.printStackTrace();
    		}	    
		}
	}
	
	private class CancelButtonListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{
	    	AdComicEditDialog.this.dispose();
		}
	}
}
