package gui;

import controller.*;
import model.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerPanelView extends JFrame {
	
	public CustomerPanelView(User user) {
		
		setTitle("Rabbit Comics Rental System");
		setResizable(true);	
		setBounds(100, 100, 829, 574);
		setName("frame\r\n");
		getContentPane().setBackground(new Color(255, 255, 204));
		
		JLabel lblCustomerMenu = new JLabel("CUSTOMER MENU");
		lblCustomerMenu.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCustomerMenu.setOpaque(true);
		lblCustomerMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomerMenu.setFont(new Font("Sitka Subheading", Font.BOLD, 27));
		lblCustomerMenu.setBackground(new Color(245, 222, 179));
		
		JButton btnRentComics = new JButton("Rent Comics");
		btnRentComics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CuRentComicsView frame = new CuRentComicsView(user);
				frame.setVisible(true);
				dispose();
			}
		});
		btnRentComics.setFont(new Font("Sitka Subheading", Font.PLAIN, 21));
		
		JButton btnUpdateAccount = new JButton("Update Account");
		btnUpdateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					CuUpdateAccountDialog dialog = new CuUpdateAccountDialog(user);
			    	dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			    	dialog.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
			}
		});
		btnUpdateAccount.setFont(new Font("Sitka Subheading", Font.PLAIN, 21));
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int x = JOptionPane.showConfirmDialog(null, "Are you confirm?", "Log Out Confirmation", JOptionPane.YES_NO_OPTION);

            	if(x == JOptionPane.YES_OPTION)
            	{
            		GeMainMenuView frame = new GeMainMenuView();
    				frame.setVisible(true);
    				dispose();
            	}
			}
		});
		btnLogOut.setFont(new Font("Sitka Subheading", Font.PLAIN, 21));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(260)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCustomerMenu, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnLogOut, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnUpdateAccount, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRentComics, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(261, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(114)
					.addComponent(lblCustomerMenu, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addGap(37)
					.addComponent(btnRentComics, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addGap(33)
					.addComponent(btnUpdateAccount, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addComponent(btnLogOut, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(115, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


}
