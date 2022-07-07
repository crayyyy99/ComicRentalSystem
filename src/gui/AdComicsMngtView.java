package gui;

import controller.ComicCopyController;
import model.*;
import java.util.ArrayList;
import java.sql.SQLException;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdComicsMngtView extends JFrame {
	
	private final int MAX_INT = 2147483467;
	private boolean selected = false;
	private ComicCopy selectedComicCopy = new ComicCopy();
	private ArrayList<ComicCopy> comicCopyList = new ArrayList<ComicCopy>();
	private JTextField searchtxt;
	private JComboBox cmbSearchType;
	private JTable tableComic;
		
	public static void main(String[] args) {
		try {
			AdComicsMngtView view = new AdComicsMngtView();			
			view.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public AdComicsMngtView() {
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
		
		JLabel AdminPanelTitle = new JLabel("COMICS MANAGEMENT");
		AdminPanelTitle.setVerticalAlignment(SwingConstants.BOTTOM);
		AdminPanelTitle.setOpaque(true);
		AdminPanelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		AdminPanelTitle.setFont(new Font("Sitka Subheading", Font.BOLD, 27));
		AdminPanelTitle.setBackground(new Color(255, 222, 179));
		
		JLabel lblNewLabel = new JLabel("Search By");
		lblNewLabel.setFont(new Font("Sitka Subheading", Font.PLAIN, 19));
		
		cmbSearchType = new JComboBox();
		cmbSearchType.setFont(new Font("Arial", Font.PLAIN, 17));
		cmbSearchType.setModel(new DefaultComboBoxModel(new String[] {"Comic ID", "Title", "Author", "Genre"}));
		
		searchtxt = new JTextField();
		searchtxt.setFont(new Font("Arial", Font.PLAIN, 17));
		searchtxt.setColumns(100);
		
		JButton btnSearch = new JButton("");
		btnSearch.setIcon(searchIcon);
		btnSearch.addActionListener(new SearchButtonListener());
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(201)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(cmbSearchType, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(searchtxt, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(338)
							.addComponent(AdminPanelTitle, GroupLayout.PREFERRED_SIZE, 394, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(484)
							.addComponent(image, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(653, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(image, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(AdminPanelTitle, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(5)
							.addComponent(lblNewLabel))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(1)
							.addComponent(cmbSearchType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(searchtxt, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JButton btnRefreshTable = new JButton("Refresh Table");
		btnRefreshTable.setVerticalAlignment(SwingConstants.BOTTOM);
		btnRefreshTable.setFont(new Font("Sitka Subheading", Font.PLAIN, 17));
		btnRefreshTable.addActionListener(new RefreshButtonListener());
		
		JButton btnAddComic = new JButton("Add Comic");
		btnAddComic.setFont(new Font("Sitka Subheading", Font.PLAIN, 17));
		btnAddComic.addActionListener(new AddComicButtonListener());
		
		JButton btnViewDetails = new JButton("View Details");
		btnViewDetails.setFont(new Font("Sitka Subheading", Font.PLAIN, 17));
		btnViewDetails.addActionListener(new ViewComicDetailsButtonListener());
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminPanelView frame = new AdminPanelView();
				frame.setVisible(true);
				dispose();
			}
		});
		btnBack.setVerticalAlignment(SwingConstants.BOTTOM);
		btnBack.setFont(new Font("Sitka Subheading", Font.PLAIN, 17));
		
		JButton btnEditGenre = new JButton("Edit Genre");
		btnEditGenre.addActionListener(new EditGenreButtonListener());
		btnEditGenre.setFont(new Font("Sitka Subheading", Font.PLAIN, 17));
			
		JButton btnEditComic = new JButton("Edit Comic");
		btnEditComic.addActionListener(new EditComicButtonListener());
		btnEditComic.setFont(new Font("Sitka Subheading", Font.PLAIN, 17));
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(35)
					.addComponent(btnEditGenre, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnRefreshTable)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 672, GroupLayout.PREFERRED_SIZE))
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAddComic, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEditComic, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnViewDetails, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(btnAddComic, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnEditComic, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnViewDetails, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
								.addGap(166))
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 332, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnEditGenre, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRefreshTable, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(26))
		);
		
		tableComic = new JTable();
		scrollPane.setViewportView(tableComic);
		tableComic.setModel(new ComicTableModel());
		tableComic.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableComic.setFont(new Font("Arial", Font.PLAIN, 13));
		getContentPane().setLayout(groupLayout);
		
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		loadComicList("");
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
	
	private class AddComicButtonListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{
			try {
				AdComicAddDialog dialog = new AdComicAddDialog();
		    	dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		    	dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		}
	}
	
	private class ViewComicDetailsButtonListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{
			int index = tableComic.getSelectedRow();
	    	if(index < 0)
	    		return;
    		int id = (int) tableComic.getValueAt(index, 0);	 
    		String condition = String.format("comic_id = %d", id);
    		ComicCopyController comicCopyController = new ComicCopyController();
    		try {
    			selectedComicCopy = comicCopyController.selectWhereList(condition, 0, 1).get(0);
    			selected = true;
    		} catch (ClassNotFoundException | SQLException e1) {
    			e1.printStackTrace();
    		}	  
    		
    		try {
				AdComicViewDetailsDialog dialog = new AdComicViewDetailsDialog(selectedComicCopy);
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
	    	loadComicList("");
	    	searchtxt.setText("");
	    	selected = false;
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
	
	private class EditGenreButtonListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{
			try {
				AdGenreEditDialog dialog = new AdGenreEditDialog();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		    	dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
			}
		}
	}
	
	private class EditComicButtonListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{
			int index = tableComic.getSelectedRow();
	    	if(index < 0)
	    		return;
    		int id = (int) tableComic.getValueAt(index, 0);	 
    		String condition = String.format("comic_id = %d", id);
    		ComicCopyController comicCopyController = new ComicCopyController();
    		try {
    			selectedComicCopy = comicCopyController.selectWhereList(condition, 0, 1).get(0);
    			selected = true;
    		} catch (ClassNotFoundException | SQLException e1) {
    			e1.printStackTrace();
    		}	  
    		
    		try {
				AdComicEditDialog dialog = new AdComicEditDialog(selectedComicCopy);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		    	dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
			}
		}
	}
	

	
	
	
		
}
	


