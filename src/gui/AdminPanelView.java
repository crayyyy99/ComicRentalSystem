package gui;

import model.*;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminPanelView extends JFrame {
	
	public AdminPanelView() {
		setTitle("Rabbit Comics Rental System");
		setResizable(true);	
		setBounds(100, 100, 829, 574);
		setName("frame\r\n");
		getContentPane().setBackground(new Color(255, 255, 204));
		
		JLabel AdminPanelTitle = new JLabel("ADMIN MENU");
		AdminPanelTitle.setVerticalAlignment(SwingConstants.BOTTOM);
		AdminPanelTitle.setOpaque(true);
		AdminPanelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		AdminPanelTitle.setFont(new Font("Sitka Subheading", Font.BOLD, 27));
		AdminPanelTitle.setBackground(new Color(245, 222, 179));
		
		JButton btnComicsManagement = new JButton("Comics Management");
		btnComicsManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdComicsMngtView frame = new AdComicsMngtView();
				frame.setVisible(true);
				dispose();
			}
		});
		btnComicsManagement.setFont(new Font("Sitka Subheading", Font.PLAIN, 21));
		
		JButton btnRentalManagement = new JButton("Rental Management");
		btnRentalManagement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdRentalMngtView frame = new AdRentalMngtView();
				frame.setVisible(true);
				dispose();
			}
		});
		btnRentalManagement.setFont(new Font("Sitka Subheading", Font.PLAIN, 21));
		
		JButton btnViewRentalReport = new JButton("View Rental Report");
		btnViewRentalReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdRentalReportView frame = new AdRentalReportView();
				frame.setVisible(true);
				dispose();
			}
		});
		btnViewRentalReport.setFont(new Font("Sitka Subheading", Font.PLAIN, 21));
		
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
		
		JButton btnViewRentalReport_2 = new JButton("Customer Management");
		btnViewRentalReport_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdCustomerMngtView frame = new AdCustomerMngtView();
				frame.setVisible(true);
				dispose();
			}
		});
		btnViewRentalReport_2.setFont(new Font("Sitka Subheading", Font.PLAIN, 21));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(99)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnComicsManagement, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnRentalManagement, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnViewRentalReport, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnViewRentalReport_2, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE))
					.addGap(99))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(283)
					.addComponent(AdminPanelTitle, GroupLayout.PREFERRED_SIZE, 249, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(283, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(336)
					.addComponent(btnLogOut, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(336, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(87)
					.addComponent(AdminPanelTitle, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addGap(49)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnComicsManagement, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnViewRentalReport_2, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
					.addGap(49)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRentalManagement, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnViewRentalReport, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
					.addGap(51)
					.addComponent(btnLogOut, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(91, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


}
