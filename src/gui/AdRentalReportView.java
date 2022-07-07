package gui;

import controller.*;
import model.*;
import java.util.ArrayList;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdRentalReportView extends JFrame {
			
	public static void main(String[] args) {
		try {
			AdRentalReportView view = new AdRentalReportView();			
			view.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public AdRentalReportView() {

		setTitle("Rabbit Comics Rental System");
		setResizable(true);	
		//setBounds(100, 100, 829, 574);
		//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		//setSize(screenSize.width-50, screenSize.height-50);
		setSize(1080, 720);
		
		setName("frame\r\n");
		getContentPane().setBackground(new Color(255, 255, 204));
		
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("C:\\Users\\user\\eclipse-workspace\\ComicRentalSystem\\"
				+ "images\\report.png").getImage().getScaledInstance(100, 114, Image.SCALE_DEFAULT));
		
		JPanel panel = new JPanel();
		panel.setMinimumSize(new Dimension(10, 7));
		panel.setBorder(null);
		panel.setBackground(new Color(255, 255, 204));
		JLabel image = new JLabel("");
		image.setHorizontalAlignment(SwingConstants.CENTER);
		image.setIcon(imageIcon);
		
		JLabel AdminPanelTitle = new JLabel("VIEW REPORTS");
		AdminPanelTitle.setVerticalAlignment(SwingConstants.BOTTOM);
		AdminPanelTitle.setOpaque(true);
		AdminPanelTitle.setHorizontalAlignment(SwingConstants.CENTER);
		AdminPanelTitle.setFont(new Font("Sitka Subheading", Font.BOLD, 27));
		AdminPanelTitle.setBackground(new Color(255, 222, 179));			
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(480)
							.addComponent(image, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(324)
							.addComponent(AdminPanelTitle, GroupLayout.PREFERRED_SIZE, 418, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(324, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(image, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(AdminPanelTitle, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(27, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
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
		
		JButton btnDailySales = new JButton("Daily Sales");
		btnDailySales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AdSalesReportDailyDialog dialog = new AdSalesReportDailyDialog();
			    	dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			    	dialog.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
			}
			
		});
		btnDailySales.setFont(new Font("Sitka Subheading", Font.PLAIN, 21));
		
		JButton btnMonthlySales = new JButton("Monthly Sales");
		btnMonthlySales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AdSalesReportMonthlyDialog dialog = new AdSalesReportMonthlyDialog();
			    	dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			    	dialog.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
			}
			
		});
		btnMonthlySales.setFont(new Font("Sitka Subheading", Font.PLAIN, 21));
		
		JButton btnTotalSales = new JButton("Annual Sales");
		btnTotalSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					AdSalesReportAnnuallyDialog dialog = new AdSalesReportAnnuallyDialog();
			    	dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			    	dialog.setVisible(true);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
			}
			
		});
		btnTotalSales.setFont(new Font("Sitka Subheading", Font.PLAIN, 21));
		
		JButton btnBestSeller = new JButton("Best Seller");
		btnBestSeller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				AdBestSellerReportDialog dialog = new AdBestSellerReportDialog();
		    	dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		    	dialog.setVisible(true);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		});
		btnBestSeller.setFont(new Font("Sitka Subheading", Font.PLAIN, 21));
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1066, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(488)
					.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(485, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(384)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnDailySales, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
						.addComponent(btnMonthlySales, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnTotalSales, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnBestSeller, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(384, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnDailySales, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addGap(34)
					.addComponent(btnMonthlySales, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addGap(36)
					.addComponent(btnTotalSales, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addGap(40)
					.addComponent(btnBestSeller, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
					.addGap(47)
					.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(73, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
				
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
	


