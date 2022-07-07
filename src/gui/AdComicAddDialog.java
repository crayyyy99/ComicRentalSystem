package gui;

import controller.ComicController;
import controller.ComicCopyController;
import controller.ComicGenreController;
import model.Comic;
import model.ComicCopy;
import model.ComicGenre;
import model.User;

import java.util.ArrayList;
import java.sql.SQLException;

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

public class AdComicAddDialog extends JDialog {
	private final int MAX_INT = 2147483467;
	private ArrayList<String> genreList = new ArrayList<String>();
	private JTextField titletxt;
	private JComboBox cmbgenre;
	private JTextField authortxt;
	private JSpinner pricespinner;
	private JSpinner quantityspinner;
	private JSpinner comicIDspinner;
	private JSpinner cpyQuantityspinner;
	private JRadioButton rdbtnAddNewComic;
	private JRadioButton rdbtnAddNewCopy;
	private JButton btnAddGenre;
	private JTextArea textArea;
	
	public AdComicAddDialog() {		
		
		getContentPane().setBackground(new Color(255, 255, 204));
		
		titletxt = new JTextField();
		titletxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		titletxt.setColumns(10);
		
		cmbgenre = new JComboBox();
		cmbgenre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_1 = new JLabel("Title:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		rdbtnAddNewComic = new JRadioButton("Add New Comic");
		rdbtnAddNewComic.setSelected(true);
		rdbtnAddNewComic.setBackground(new Color(255, 255, 204));
		rdbtnAddNewComic.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		rdbtnAddNewCopy = new JRadioButton("Add New Copy");
		rdbtnAddNewCopy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		rdbtnAddNewCopy.setBackground(new Color(255, 255, 204));
		
		JLabel lblNewLabel_6 = new JLabel("Description:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		pricespinner = new JSpinner();
		pricespinner.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		pricespinner.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_5 = new JLabel("Quantity:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		authortxt = new JTextField();
		authortxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		authortxt.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Genre:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_3 = new JLabel("Author:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_4 = new JLabel("Price:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		quantityspinner = new JSpinner();
		quantityspinner.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_7 = new JLabel("Comic ID:");
		lblNewLabel_7.setEnabled(false);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		comicIDspinner = new JSpinner();
		comicIDspinner.setEnabled(false);
		comicIDspinner.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_8 = new JLabel("Quantity:");
		lblNewLabel_8.setEnabled(false);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		cpyQuantityspinner = new JSpinner();
		cpyQuantityspinner.setEnabled(false);
		cpyQuantityspinner.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		rdbtnAddNewComic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnAddNewComic.isSelected()) {
					titletxt.setEnabled(true);
					cmbgenre.setEnabled(true);
					authortxt.setEnabled(true);
					lblNewLabel_1.setEnabled(true);
					lblNewLabel_2.setEnabled(true);
					lblNewLabel_3.setEnabled(true);
					lblNewLabel_4.setEnabled(true);
					lblNewLabel_5.setEnabled(true);
					lblNewLabel_6.setEnabled(true);
					pricespinner.setEnabled(true);
					textArea.setEnabled(true);			
					quantityspinner.setEnabled(true);				
					lblNewLabel_7.setEnabled(false);
					lblNewLabel_8.setEnabled(false);
					comicIDspinner.setEnabled(false);			
					cpyQuantityspinner.setEnabled(false);
				}
			}
		});
		
		rdbtnAddNewCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnAddNewCopy.isSelected()) {
					lblNewLabel_7.setEnabled(true);
					lblNewLabel_8.setEnabled(true);
					comicIDspinner.setEnabled(true);			
					cpyQuantityspinner.setEnabled(true);
					titletxt.setEnabled(false);
					cmbgenre.setEnabled(false);
					authortxt.setEnabled(false);
					lblNewLabel_1.setEnabled(false);
					lblNewLabel_2.setEnabled(false);
					lblNewLabel_3.setEnabled(false);
					lblNewLabel_4.setEnabled(false);
					lblNewLabel_5.setEnabled(false);
					lblNewLabel_6.setEnabled(false);
					pricespinner.setEnabled(false);
					textArea.setEnabled(false);			
					quantityspinner.setEnabled(false);
				}
			}
		});
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnAddNewComic);
		group.add(rdbtnAddNewCopy);
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(rdbtnAddNewCopy, GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED))
									.addComponent(rdbtnAddNewComic, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE))
								.addGap(517))
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(30)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
										.addGap(26)
										.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
											.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(cmbgenre, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(btnAddGenre, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(titletxt, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
												.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
													.addGroup(groupLayout.createSequentialGroup()
														.addComponent(pricespinner, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(quantityspinner, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
													.addComponent(authortxt, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE))))
										.addGap(18)
										.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)))
								.addGap(30)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(31)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(cpyQuantityspinner)
								.addComponent(comicIDspinner, GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, 514, GroupLayout.PREFERRED_SIZE)))
					.addGap(29))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(51)
							.addComponent(rdbtnAddNewComic)
							.addGap(38)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(titletxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
							.addGap(14)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
									.addComponent(cmbgenre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnAddGenre, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(authortxt, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGap(24)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(quantityspinner, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addComponent(pricespinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
							.addGap(34)
							.addComponent(rdbtnAddNewCopy, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(comicIDspinner, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
								.addComponent(cpyQuantityspinner, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(116)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)))
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(52, Short.MAX_VALUE))
		);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		getContentPane().setLayout(groupLayout);
		setTitle("Add Comic");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 574);
		setLocationRelativeTo(null);
		
		loadGenre();
		
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
		cmbgenre.setSelectedIndex(-1);
	}
	
	private class SaveButtonListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{
			ComicCopyController ccController = new ComicCopyController();
			ComicGenreController cgController = new ComicGenreController();
			ComicController cController = new ComicController();
			ComicCopy cc = new ComicCopy();
			ComicGenre cg = new ComicGenre();
			Comic c = new Comic();
			
			if(rdbtnAddNewComic.isSelected()) {
				c.setTitle(titletxt.getText());
				
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
							
				c.setAuthor(authortxt.getText());
				c.setPrice((double)pricespinner.getValue());
				c.setDescription(textArea.getText());
				
				int result = -1;
		    	try {
					if(c.getId() == 0)
						result = cController.insert(c);
					else
						result = cController.update(c);
					
					if (result >= 1)
					{
						cc.setComicId(result);
					}
				} catch (InputException e3) {
					e3.displayMessage();
				}
				catch (ClassNotFoundException | SQLException e3) {
					e3.printStackTrace();
				}
		    	
		    	int number = (int)quantityspinner.getValue();
		    	
		    	for(int i=0; i<number; i++) {
		    		result = -1;
		    		cc.setCopyId(i+1);
		    		try {
						if(cc.getId() == 0)
							result = ccController.insert(cc);
						else
							result = ccController.update(cc);
						
						
					} catch (InputException e4) {
						e4.displayMessage();
					}
					catch (ClassNotFoundException | SQLException e4) {
						e4.printStackTrace();
					}	
		    	}	
		    	if (result >= 1)
				{
					PopOutMsgBox.infoBox("Record has been added. Please refresh your table.", "Added Successful");
					cc.setCopyId(0);
					AdComicAddDialog.this.dispose();
				}
			}
			
			else if(rdbtnAddNewCopy.isSelected())
			{		
				c.setId((int)comicIDspinner.getValue());
				try {
					
					boolean found = cController.idExist(c.getId());
					if(!found)
					{
						PopOutMsgBox.infoBox("Comic ID not exist.", "Error");
					}
					else {
						c = cController.selectWhere("id = "+ c.getId(), 0, 1).get(0);
						
						int x = JOptionPane.showConfirmDialog(null, "Are you confirm to add copy for " + c.getTitle() + "?", "Add Confirmation", JOptionPane.YES_NO_OPTION);

		            	if(x == JOptionPane.YES_OPTION)
		            	{
		            		int currentCopyNo = ccController.getCopyCount(c.getId());
		            		int number = (int)cpyQuantityspinner.getValue();
		            		int result = -1;
		            		
		    		    	for(int i=0; i<number; i++) {
		    		    		cc.setComicId(c.getId());
		    		    		cc.setCopyId(currentCopyNo+i+1);
		    		    		try {
		    						if(cc.getId() == 0)
		    							result = ccController.insert(cc);
		    						else
		    							result = ccController.update(cc);
		    							    						
		    					} catch (InputException e1) {
		    						e1.displayMessage();
		    					}
		    					catch (ClassNotFoundException | SQLException e1) {
		    						e1.printStackTrace();
		    					}
		    		    	}	
		    		    	
		    		    	if (result == 1)
    						{
    							PopOutMsgBox.infoBox("Record has been added.", "Added Successful");
    							AdComicAddDialog.this.dispose();
    						}
		            	}
		            	else {}
					}
					
					
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		
		}
	}
	
	private class CancelButtonListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{
	    	AdComicAddDialog.this.dispose();
		}
	}
}
