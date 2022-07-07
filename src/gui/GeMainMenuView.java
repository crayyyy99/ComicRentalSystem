package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

import controller.UserController;

public class GeMainMenuView extends JFrame {
		
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GeMainMenuView view = new GeMainMenuView();			
					view.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public GeMainMenuView() {
		setResizable(false);
		setName("frame\r\n");
		getContentPane().setBackground(new Color(255, 255, 204));
		setTitle("Rabbit Comics Rental System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 829, 574);
		setLocationRelativeTo(null);
		
		JLabel welcomeText = new JLabel("Welcome to Rabbit Comics World");
		welcomeText.setFont(new Font("Sitka Subheading", Font.PLAIN, 26));
			
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("C:\\Users\\user\\eclipse-workspace\\"
				+ "ComicRentalSystem\\images\\crs logo.png").getImage().getScaledInstance(165, 198, Image.SCALE_DEFAULT));
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(imageIcon);
		
		JButton btnLogin = new JButton("Log In");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GeLoginView frame = new GeLoginView();
				frame.setVisible(true);
				dispose();
			}
		});
		btnLogin.setHorizontalTextPosition(SwingConstants.CENTER);
		btnLogin.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserController controller = new UserController();
				
				GeRegisterView frame = new GeRegisterView();
				frame.setVisible(true);
				dispose();
				
				try {	
					if(controller.getCount() == 0)
						PopOutMsgBox.infoBox("Your account is the first account registered into the system"
								+ " and will be set as Admin account.", "Notification");
				}
				catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			
			}
		});
		btnRegister.setFont(new Font("Sitka Subheading", Font.PLAIN, 18));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(330)
							.addComponent(lblNewLabel))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(218)
							.addComponent(welcomeText, GroupLayout.PREFERRED_SIZE, 389, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(246)
							.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
							.addGap(103)
							.addComponent(btnRegister, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(218, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(69)
					.addComponent(lblNewLabel)
					.addGap(10)
					.addComponent(welcomeText)
					.addGap(41)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRegister, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(148, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
	}
	
}

