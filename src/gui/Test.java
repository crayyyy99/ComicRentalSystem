package gui;

import java.awt.*;


import javax.swing.*;


public class Test extends JFrame {
	
	private JLabel lblTitle;
	
	private JLabel lblForm;
	private String lblForms[] = {"Name", "Email", "Phone", "Bajet", "Bateri > than", "Weight < than", "Remark"};	
	
	private JLabel lblDot;
	
	private JTextField [] textfields = new JTextField[lblForms.length];
	private JTextArea txtAreaRemark;
	
	private String lblButtons[] = {"Submit", "Reset", "Back"};
	private JButton button;
	
	public static void main(String[] args) {
		try {
			Test view = new Test();			
			view.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void init() {
		
		
		setSize(500,500);
		setLayout (null);
		
		lblTitle = new JLabel ("Customer Phone Assist");
		lblTitle.setBounds(0,0,200,20);
		add(lblTitle);
		
		int size = lblForms.length;
		int x = 100, y = 70, width = 100, height = 20;
		
		JLabel lbl1 = new JLabel("AA");
		//ButtonGroup group = new ButtonGroup();
		JRadioButton rb1 = new JRadioButton("a");
		rb1.setBounds(0, y+30, 200, height);
		JRadioButton rb2 = new JRadioButton("b");
		rb2.setBounds(0, 420, width, height);
		//group.add(rb1);
		//group.add(rb2);
		
		
		add(rb1);
		add(rb2);
		
		
//		for(int i = 0; i< size ; i++)
//		{
//			lblForm = new JLabel(lblForms[i]);
//			lblForm.setBounds(x, y, width, height);
//			add(lblForm);
//			
//			lblDot = new JLabel(":");
//			lblDot.setBounds(x + 100, y, 5, height);
//			add(lblDot);
//			if(i != size -1)
//			{
//				textfields[i] = new JTextField();
//				textfields[i].setBounds(x + 105, y, width + 50, height);
//				add(textfields[i]);	
//			}
//			else
//			{
//				txtAreaRemark = new JTextArea();
//				txtAreaRemark.setBounds(x + 105, y, width + 50, height);
//				add(txtAreaRemark);
//			}
//			
//			y += 40;
//			
//		}
//		int x_btn = 100, y_btn = 400;
//		
//		for(String lblButton : lblButtons)
//		{
//			button = new JButton(lblButton);
//			button.setBounds(x_btn, y_btn, width, height);
//			add(button);
//			x_btn += 105;
//		}

		
		
	}
	
	public Test() {		
		
		super();
		init();
	}
//		
//		getContentPane().setBackground(new Color(255, 255, 204));
//		
//		JLabel lblNewLabel = new JLabel("User ID:");
//		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 15));
//		
//		idtxt1 = new JTextField();
//		idtxt1.setEditable(false);
//		idtxt1.setColumns(10);
//		
//		JLabel lblNewLabel_3 = new JLabel("Contact No:");
//		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 15));
//		
//		contacttxt1 = new JTextField();
//		contacttxt1.setColumns(20);
//		
//		JLabel lblNewLabel_4 = new JLabel("E-mail:");
//		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 15));
//		
//		emailtxt1 = new JTextField();
//		emailtxt1.setColumns(100);
//		
//		JLabel lblNewLabel_5 = new JLabel("IC No:");
//		lblNewLabel_5.setFont(new Font("Arial", Font.PLAIN, 15));
//		
//		ictxt1 = new JTextField();
//		ictxt1.setColumns(12);
//		
//		JLabel lblNewLabel_6 = new JLabel("Name:");
//		lblNewLabel_6.setFont(new Font("Arial", Font.PLAIN, 15));
//		
//		nametxt1 = new JTextField();
//		nametxt1.setColumns(100);
//		
//		JButton btnSave = new JButton("Save");
//		btnSave.addActionListener(new SaveButtonListener());
//		btnSave.setVerticalAlignment(SwingConstants.BOTTOM);
//		btnSave.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
//		
//		JButton btnCancel = new JButton("Cancel");
//		btnCancel.addActionListener(new CancelButtonListener());
//		btnCancel.setVerticalAlignment(SwingConstants.BOTTOM);
//		btnCancel.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
//		
//		JLabel lblNewLabel_7 = new JLabel("Username:");
//		lblNewLabel_7.setFont(new Font("Arial", Font.PLAIN, 15));
//		
//		usernametxt1 = new JTextField();
//		usernametxt1.setEditable(false);
//		usernametxt1.setColumns(25);
//		
//		JLabel lblNewLabel_8 = new JLabel("Password");
//		lblNewLabel_8.setFont(new Font("Arial", Font.PLAIN, 15));
//		
//		JButton btnResetPassword = new JButton("Reset Password");
//		btnResetPassword.addActionListener(new ResetPwdButtonListener());
//		btnResetPassword.setVerticalAlignment(SwingConstants.BOTTOM);
//		btnResetPassword.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));
//		
//		passwordtxt1 = new JPasswordField();
//		passwordtxt1.setEditable(false);
//		GroupLayout groupLayout = new GroupLayout(getContentPane());
//		groupLayout.setHorizontalGroup(
//			groupLayout.createParallelGroup(Alignment.LEADING)
//				.addGroup(groupLayout.createSequentialGroup()
//					.addGap(37)
//					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
//						.addGroup(groupLayout.createSequentialGroup()
//							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
//								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
//								.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
//								.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
//								.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
//								.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
//							.addGap(18)
//							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
//								.addComponent(contacttxt1)
//								.addComponent(emailtxt1, 0, 0, Short.MAX_VALUE)
//								.addComponent(ictxt1)
//								.addComponent(nametxt1, 0, 0, Short.MAX_VALUE)
//								.addComponent(idtxt1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)))
//						.addGroup(Alignment.TRAILING, groupLayout.createParallelGroup(Alignment.LEADING)
//							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
//								.addComponent(btnResetPassword)
//								.addPreferredGap(ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
//								.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
//								.addGap(18)
//								.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
//							.addGroup(groupLayout.createSequentialGroup()
//								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
//									.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
//									.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
//								.addGap(18)
//								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
//									.addComponent(passwordtxt1, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
//									.addComponent(usernametxt1, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)))))
//					.addGap(37))
//		);
//		groupLayout.setVerticalGroup(
//			groupLayout.createParallelGroup(Alignment.LEADING)
//				.addGroup(groupLayout.createSequentialGroup()
//					.addGap(58)
//					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
//						.addComponent(lblNewLabel)
//						.addComponent(idtxt1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
//					.addGap(28)
//					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
//						.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
//						.addComponent(nametxt1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
//					.addGap(30)
//					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
//						.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
//						.addComponent(ictxt1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
//					.addGap(31)
//					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
//						.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
//						.addComponent(emailtxt1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
//					.addGap(30)
//					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
//						.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
//						.addComponent(contacttxt1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
//					.addGap(28)
//					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
//						.addComponent(lblNewLabel_7, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
//						.addComponent(usernametxt1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
//					.addGap(27)
//					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
//						.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
//						.addComponent(passwordtxt1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
//					.addGap(57)
//					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
//						.addComponent(btnSave)
//						.addComponent(btnCancel, GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
//						.addComponent(btnResetPassword, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
//					.addGap(58))
//		);
//		getContentPane().setLayout(groupLayout);
//		setTitle("Account Details");
//		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//		setBounds(100, 100, 510, 550);
//		setLocationRelativeTo(null);
//		
//		if(user!= null)
//			loadUserData(user);
//				
	}
	
	