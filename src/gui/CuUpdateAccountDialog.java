package gui;

import controller.UserController;
import model.User;
import other.CheckInput;

import java.util.ArrayList;
import java.sql.SQLException;
import exception.InputException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class CuUpdateAccountDialog extends JDialog {
	private User userData = new User();
	private JTextField idtxt1;
	private JTextField contacttxt1;
	private JTextField emailtxt1;
	private JTextField ictxt1;
	private JTextField nametxt1;
	private JTextField usernametxt1;
	private JPasswordField passwordtxt1;
	
	public CuUpdateAccountDialog(User user) {		
		userData = user;
		
		getContentPane().setBackground(new Color(255, 255, 204));
		
		JLabel lblNewLabel = new JLabel("User ID:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		
		idtxt1 = new JTextField();
		idtxt1.setEditable(false);
		idtxt1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Contact No:");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 15));
		
		contacttxt1 = new JTextField();
		contacttxt1.setColumns(20);
		
		JLabel lblNewLabel_4 = new JLabel("E-mail:");
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 15));
		
		emailtxt1 = new JTextField();
		emailtxt1.setColumns(100);
		
		JLabel lblNewLabel_5 = new JLabel("IC No:");
		lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 15));
		
		ictxt1 = new JTextField();
		ictxt1.setColumns(12);
		
		JLabel lblNewLabel_6 = new JLabel("Name:");
		lblNewLabel_6.setFont(new Font("Arial", Font.PLAIN, 15));
		
		nametxt1 = new JTextField();
		nametxt1.setColumns(100);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new SaveButtonListener());
		btnSave.setVerticalAlignment(SwingConstants.BOTTOM);
		btnSave.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new CancelButtonListener());
		btnCancel.setVerticalAlignment(SwingConstants.BOTTOM);
		btnCancel.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		
		JLabel lblNewLabel_7 = new JLabel("Username:");
		lblNewLabel_7.setFont(new Font("Arial", Font.PLAIN, 15));
		
		usernametxt1 = new JTextField();
		usernametxt1.setEditable(false);
		usernametxt1.setColumns(25);
		
		JLabel lblNewLabel_8 = new JLabel("Password");
		lblNewLabel_8.setFont(new Font("Arial", Font.PLAIN, 15));
		
		JButton btnResetPassword = new JButton("Reset Password");
		btnResetPassword.addActionListener(new ResetPwdButtonListener());
		btnResetPassword.setVerticalAlignment(SwingConstants.BOTTOM);
		btnResetPassword.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
		
		passwordtxt1 = new JPasswordField();
		passwordtxt1.setEditable(false);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(37)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(contacttxt1)
								.addComponent(emailtxt1, 0, 0, Short.MAX_VALUE)
								.addComponent(ictxt1)
								.addComponent(nametxt1, 0, 0, Short.MAX_VALUE)
								.addComponent(idtxt1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)))
						.addGroup(Alignment.TRAILING, groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addComponent(btnResetPassword)
								.addPreferredGap(ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
								.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(passwordtxt1, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
									.addComponent(usernametxt1, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)))))
					.addGap(37))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(58)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(idtxt1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(nametxt1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(ictxt1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(emailtxt1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(contacttxt1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(usernametxt1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordtxt1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(57)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSave)
						.addComponent(btnCancel, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
						.addComponent(btnResetPassword, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(58))
		);
		getContentPane().setLayout(groupLayout);
		setTitle("Account Details");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 510, 550);
		setLocationRelativeTo(null);
		
		if(user!= null)
			loadUserData(user);
				
	}
	
	public void loadUserData(User user) {
		idtxt1.setText(String.valueOf(user.getId()));
		contacttxt1.setText(user.getPhoneNo());
		emailtxt1.setText(user.getEmail());
		ictxt1.setText(user.getIcNo());
		nametxt1.setText(user.getName());
		usernametxt1.setText(user.getUsername());
		passwordtxt1.setText(user.getPassword());
	}
	
	private class ResetPwdButtonListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{
			UserController userController = new UserController();
			JPasswordField oldpwdtxt = new JPasswordField();
			JPasswordField newpwdtxt = new JPasswordField();
			JPasswordField repwdtxt = new JPasswordField();
			Object[] oldpwdmsg = {"Enter Old Password:", oldpwdtxt};
			Object[] message = {"New Password", newpwdtxt, "Confirm New Password", repwdtxt};
			
			int option1 = JOptionPane.showConfirmDialog(null, oldpwdmsg, "Old Password", JOptionPane.OK_CANCEL_OPTION);
			
			String oldpassword = UserController.getSHA(new String((oldpwdtxt.getPassword())));
	        boolean reEquals = false;
	        
			try {
	        	if(option1 == JOptionPane.OK_OPTION)
	        	{
	        		if(oldpassword.equals(userController.getOldPassword(userData.getId()))){
	  				  
						int option = JOptionPane.showConfirmDialog(null, message, "New Password", JOptionPane.OK_CANCEL_OPTION);
						if(option == JOptionPane.OK_OPTION)
						{
							reEquals = new String(newpwdtxt.getPassword()).equals(new String(repwdtxt.getPassword()));						
							if(reEquals)
							{
								userData.setPassword(new String(newpwdtxt.getPassword()));

				    	    	int result = -1;
								
								if(userData.getId() == 0)
			    					result = userController.insert(userData);
			    				else
			    					result = userController.update(userData);
			    				
			    				if (result == 1)
			    					PopOutMsgBox.infoBox("Password has been reset.", "Reset Successful");
							}
							else
								PopOutMsgBox.infoBox("Password not same. Please try again.", "Wrong Password");
						}								
					}
	        		else 
						PopOutMsgBox.infoBox("Wrong Old Password. Please try again.", "Wrong Password");
	        	}else {}
				
			} catch (InputException e1) {
				e1.displayMessage();
			}
			catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	       
		}
	}
	
	private class SaveButtonListener implements ActionListener
	{		
		public void actionPerformed(ActionEvent e)
		{
			userData.setPhoneNo(contacttxt1.getText());
			userData.setEmail(emailtxt1.getText());
			userData.setIcNo(ictxt1.getText());
			userData.setName(nametxt1.getText());
			userData.setPassword("");
			
			UserController userController = new UserController();
	    	int result = -1;
	    	try {
				if(userData.getId() == 0)
					result = userController.insert(userData);
				else
					result = userController.update(userData);
				
				if (result == 1)
				{
					PopOutMsgBox.infoBox("Account details has been updated.", "Update Successful");
					CuUpdateAccountDialog.this.dispose();
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
	    	CuUpdateAccountDialog.this.dispose();
		}
	}
	
	
}
