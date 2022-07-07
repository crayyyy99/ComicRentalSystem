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

public class CuViewReceiptDialog extends JDialog {
	private final int MAX_INT = 2147483467;
	private User userData = new User();
	private Rental rental = new Rental();
	private ArrayList<ComicCopy> comicCopyList = new ArrayList<ComicCopy>();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	private JTextField txtCusName;
	private JTextField txtRentDate;
	private JTable tableComicCopy;
	private JTextField txtExpectedDate;
	private JTextField txtRentFee;
	private JTextField txtRentalId;
	
	public CuViewReceiptDialog(int rentalId, User user) {		
		
		rental.setId(rentalId);
		userData = user;
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("C:\\Users\\user\\eclipse-workspace\\"
				+ "ComicRentalSystem\\images\\crs logo.png").getImage().getScaledInstance(100, 120, Image.SCALE_DEFAULT));
		
		getContentPane().setBackground(new Color(255, 255, 204));
		
		txtCusName = new JTextField();
		txtCusName.setEditable(false);
		txtCusName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtCusName.setColumns(10);
		
		JButton btnClose = new JButton("Close");
		btnClose.setVerticalAlignment(SwingConstants.BOTTOM);
		btnClose.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		btnClose.addActionListener(new CloseButtonListener());
		
		JLabel lblNewLabel_1 = new JLabel("Rental ID:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_2 = new JLabel("Rent Date:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_3 = new JLabel("Expected Return Date:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_5 = new JLabel("Total Payment (RM):");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtRentDate = new JTextField();
		txtRentDate.setEditable(false);
		txtRentDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtRentDate.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		
		txtExpectedDate = new JTextField();
		txtExpectedDate.setEditable(false);
		txtExpectedDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtExpectedDate.setColumns(10);
		
		txtRentFee = new JTextField();
		txtRentFee.setEditable(false);
		txtRentFee.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtRentFee.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Customer Name:");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtRentalId = new JTextField();
		txtRentalId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtRentalId.setEditable(false);
		txtRentalId.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Note: Late return will have a penalty of RM1.00 per comic book.");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JLabel lblRabbitComicsWorld = new JLabel("Rabbit Comics World Rental");
		lblRabbitComicsWorld.setFont(new Font("Sitka Subheading", Font.PLAIN, 26));
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(imageIcon);
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(60)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(115)
									.addComponent(lblNewLabel_5)
									.addGap(18)
									.addComponent(txtRentFee, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 108, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(43)
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 381, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 42, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(187)
									.addComponent(btnClose, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 186, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(63)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_3)
										.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
									.addGap(31)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(txtRentalId, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
										.addComponent(txtExpectedDate, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
										.addComponent(txtRentDate)
										.addComponent(txtCusName))
									.addGap(62))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(129)
							.addComponent(lblRabbitComicsWorld))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(243)
							.addComponent(lblNewLabel_4))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(67)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(67, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblNewLabel_4)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblRabbitComicsWorld, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtRentalId, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtCusName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtRentDate, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtExpectedDate, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtRentFee, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblNewLabel)
					.addGap(27)
					.addComponent(btnClose, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addGap(25))
		);
		
		tableComicCopy = new JTable();
		tableComicCopy.setFont(new Font("Arial", Font.PLAIN, 13));
		tableComicCopy.setModel(new comicCopyTableModel());
		tableComicCopy.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(tableComicCopy);
		getContentPane().setLayout(groupLayout);
		setTitle("Receipt");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 730);
		setLocationRelativeTo(null);
			
		loadRentalData(rentalId);
		loadRentalCopyList(String.format("rental_copy.rental_id = %d", rentalId));
	}

	public void loadRentalData(int rentalId) {

		RentalController rController = new RentalController();
		String condition = String.format("rental.id = %d", rentalId);
		try {
			rental = rController.selectWhere(condition, 0, 1).get(0);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rental.getUserId());
		txtRentalId.setText(String.valueOf(rentalId));
		txtCusName.setText(userData.getName());
		txtRentDate.setText(rental.getRentDate().format(formatter));
		txtExpectedDate.setText(rental.getExpectedDate().format(formatter));
		txtRentFee.setText(String.valueOf(rental.getRentFee()));
		
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
	
	private class comicCopyTableModel implements TableModel 
	{   
		String header[] = new String[] { "Comic ID", "Copy ID", "Title", "Genre", "Author", "Price"};
	    
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
			   	case 5: return cp.getPrice();
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
	
	private class CloseButtonListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{
	    	CuViewReceiptDialog.this.dispose();
	    	PopOutMsgBox.infoBox("Please refresh your table to see your rental record.", "Notification");
		}
	}
}
