package gui;

import controller.UserController;
import exception.InputException;
import model.User;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class GeRegisterView extends JFrame {
	
	private JTextField usernametxt;
	private JPasswordField passwordtxt1;
	private JPasswordField passwordtxt2;
	private JTextField nametxt;
	private JTextField ictxt;
	private JTextField emailtxt;
	private JTextField phonenotxt;
	
	public GeRegisterView() {
		setTitle("Rabbit Comics Rental System");
		setResizable(false);	
		setBounds(100, 100, 829, 574);
		setName("frame\r\n");
		getContentPane().setBackground(new Color(255, 255, 204));
		
		JLabel RegisterTitle = new JLabel("REGISTER");
		RegisterTitle.setVerticalAlignment(SwingConstants.BOTTOM);
		RegisterTitle.setOpaque(true);
		RegisterTitle.setHorizontalAlignment(SwingConstants.CENTER);
		RegisterTitle.setFont(new Font("Sitka Subheading", Font.BOLD, 27));
		RegisterTitle.setBackground(new Color(245, 222, 179));
		
		JLabel lblusername = new JLabel("Username");
		lblusername.setVerticalAlignment(SwingConstants.BOTTOM);
		lblusername.setFont(new Font("Sitka Subheading", Font.PLAIN, 19));
		
		JLabel lblusername_1 = new JLabel("Password");
		lblusername_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblusername_1.setFont(new Font("Sitka Subheading", Font.PLAIN, 19));
		
		JLabel lblusername_2 = new JLabel("Confirm Password");
		lblusername_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblusername_2.setFont(new Font("Sitka Subheading", Font.PLAIN, 19));
		
		usernametxt = new JTextField();
		usernametxt.setFont(new Font("Arial", Font.PLAIN, 19));
		usernametxt.setColumns(25);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new RegisterBtnListener());	
		btnRegister.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeMainMenuView frame = new GeMainMenuView();
				frame.setVisible(true);
				dispose();
			}
		});
		btnCancel.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		
		passwordtxt1 = new JPasswordField();
		passwordtxt1.setFont(new Font("Arial", Font.PLAIN, 19));
		passwordtxt1.setColumns(64);
		
		passwordtxt2 = new JPasswordField();
		passwordtxt2.setFont(new Font("Arial", Font.PLAIN, 19));
		passwordtxt2.setColumns(64);
		
		JLabel lblusername_2_1 = new JLabel("Name");
		lblusername_2_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblusername_2_1.setFont(new Font("Sitka Subheading", Font.PLAIN, 19));
		
		JLabel lblusername_2_2 = new JLabel("IC Number");
		lblusername_2_2.setVerticalAlignment(SwingConstants.BOTTOM);
		lblusername_2_2.setFont(new Font("Sitka Subheading", Font.PLAIN, 19));
		
		JLabel lblusername_2_2_1 = new JLabel("Email");
		lblusername_2_2_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblusername_2_2_1.setFont(new Font("Sitka Subheading", Font.PLAIN, 19));
		
		JLabel lblusername_2_2_1_1 = new JLabel("Contact Number");
		lblusername_2_2_1_1.setVerticalAlignment(SwingConstants.BOTTOM);
		lblusername_2_2_1_1.setFont(new Font("Sitka Subheading", Font.PLAIN, 19));
		
		nametxt = new JTextField();
		nametxt.setFont(new Font("Arial", Font.PLAIN, 19));
		nametxt.setColumns(25);
		
		ictxt = new JTextField();
		ictxt.setFont(new Font("Arial", Font.PLAIN, 19));
		ictxt.setColumns(25);
		
		emailtxt = new JTextField();
		emailtxt.setFont(new Font("Arial", Font.PLAIN, 19));
		emailtxt.setColumns(25);
		
		phonenotxt = new JTextField();
		phonenotxt.setFont(new Font("Arial", Font.PLAIN, 19));
		phonenotxt.setColumns(25);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(183)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(btnRegister, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 213, Short.MAX_VALUE)
							.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(100)
							.addComponent(RegisterTitle, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 99, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblusername_2_2_1_1, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(lblusername_2_2_1, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblusername_2_2, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblusername)
												.addGap(92))
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblusername_1, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
												.addGap(14))
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblusername_2, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
												.addComponent(lblusername_2_1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE))))))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(passwordtxt2, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
								.addComponent(ictxt, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
								.addComponent(emailtxt, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
								.addComponent(nametxt, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
								.addComponent(usernametxt, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
								.addComponent(phonenotxt, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
								.addComponent(passwordtxt1, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE))))
					.addGap(184))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(26)
					.addComponent(RegisterTitle, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblusername, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(usernametxt, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(2)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblusername_1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordtxt1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblusername_2, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordtxt2, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblusername_2_1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(nametxt, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(ictxt, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblusername_2_2, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(emailtxt, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblusername_2_2_1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblusername_2_2_1_1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(phonenotxt, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRegister, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addGap(26))
		);
		getContentPane().setLayout(groupLayout);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	private class RegisterBtnListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			User newUser = new User();
			UserController controller = new UserController();
	
			newUser.setUsername(usernametxt.getText());
			newUser.setName(nametxt.getText());
			newUser.setIcNo(ictxt.getText());
			newUser.setEmail(emailtxt.getText());
			newUser.setPhoneNo(phonenotxt.getText());
			
			String password = new String(passwordtxt1.getPassword());
			String repassword = new String(passwordtxt2.getPassword());
	
			if (!password.equals(repassword))
			{
				PopOutMsgBox.infoBox("Password not same. Please try again.", "Error: Re-attempt Password");
				return;
			}
					
			newUser.setPassword(new String(passwordtxt1.getPassword()));
							
			int result = -1;
			try {	
				if(newUser.getId() == 0)
					result = controller.insert(newUser);
				if (result == 1)
				{
					PopOutMsgBox.infoBox("Registration successful. Please login", "Register Successful");
					GeMainMenuView frame = new GeMainMenuView();
					frame.setVisible(true);
					dispose();
				}
				
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
			
}
