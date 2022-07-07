package gui;

import controller.UserController;
import model.User;
import other.CheckInput;

import java.sql.SQLException;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GeLoginView extends JFrame {
	
	private User user;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JComboBox cmbUserType;
	
	//1245aaa654643
	public GeLoginView() {
		setResizable(false);	
		setBounds(100, 100, 829, 574);
		setName("frame\r\n");
		getContentPane().setBackground(new Color(255, 255, 204));
		setLocationRelativeTo(null);
		
		JLabel loginTitle = new JLabel("LOG IN");
		loginTitle.setVerticalAlignment(SwingConstants.BOTTOM);
		loginTitle.setHorizontalAlignment(SwingConstants.CENTER);
		loginTitle.setFont(new Font("Sitka Subheading", Font.BOLD, 27));
		
		JLabel lblusername = new JLabel("Username");
		lblusername.setVerticalAlignment(SwingConstants.BOTTOM);
		lblusername.setFont(new Font("Sitka Subheading", Font.PLAIN, 21));
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setVerticalAlignment(SwingConstants.BOTTOM);
		lblPassword.setFont(new Font("Sitka Subheading", Font.PLAIN, 21));
		
		JLabel lblUserType = new JLabel("User Type");
		lblUserType.setVerticalAlignment(SwingConstants.BOTTOM);
		lblUserType.setFont(new Font("Sitka Subheading", Font.PLAIN, 21));
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Arial", Font.PLAIN, 19));
		usernameField.setColumns(25);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Arial", Font.PLAIN, 19));
		passwordField.setColumns(64);
		
		cmbUserType = new JComboBox();
		cmbUserType.setModel(new DefaultComboBoxModel(new String[] {"Customer", "Admin"}));
		cmbUserType.setFont(new Font("Arial", Font.PLAIN, 19));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeMainMenuView frame = new GeMainMenuView();
				frame.setVisible(true);
				dispose();
			}
		});
		btnCancel.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		
		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new OKButtonListener());
		btnOk.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(201)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(82)
							.addComponent(loginTitle, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 91, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 188, Short.MAX_VALUE)
								.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(passwordField, 0, 0, Short.MAX_VALUE))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblusername, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(usernameField, GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblUserType, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(cmbUserType, 0, 275, Short.MAX_VALUE))))
					.addGap(202))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(82)
					.addComponent(loginTitle, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addGap(52)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblusername, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblPassword, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(8)
							.addComponent(usernameField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addGap(37)
							.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUserType, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(cmbUserType, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
					.addGap(58)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(92, Short.MAX_VALUE))
		);
		loginTitle.setBackground(new Color(245, 222, 179));
		setTitle("Rabbit Comics Rental System");
		loginTitle.setOpaque(true);
		getContentPane().setLayout(groupLayout);
		
	}
	
	private class OKButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			UserController controller = new UserController();
			try {
				
				CheckInput check = new CheckInput();				
				if (check.isNull(usernameField.getText()) || check.isNull(new String(passwordField.getPassword())))
					PopOutMsgBox.infoBox("Empty inputs are not available!", "Error: Empty Field");
				else
				{
					String username = usernameField.getText();
					String pwd = new String(passwordField.getPassword());
					
					boolean isUserExist = controller.validateUserLogin(username, pwd );
					if (isUserExist)
					{				
						if(cmbUserType.getSelectedItem().toString() == "Admin")
						{
							boolean isAdminExist = controller.validateAdminLogin(username,  pwd);
							if(isAdminExist)
							{
								User admin = controller.selectWhere(String.format("username = '%s'", usernameField.getText()), 0, 1).get(0);
								AdminPanelView frame = new AdminPanelView();
								frame.setVisible(true);
								GeLoginView.this.dispose();
							}
							else
							{
								PopOutMsgBox.infoBox("You do not have a permission to log in.", "Login Error");
							}		
						}
						else if(cmbUserType.getSelectedItem().toString() == "Customer")
						{
							User customer = controller.selectWhere(String.format("username = '%s'", usernameField.getText()), 0, 1).get(0);
							CustomerPanelView frame = new CustomerPanelView(customer);
							frame.setVisible(true);
							GeLoginView.this.dispose();		
						}			
					}
					else
						PopOutMsgBox.infoBox("Incorrect Username or Password!", "Login Error");
				}
				
				
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
		}
	}
	
}
