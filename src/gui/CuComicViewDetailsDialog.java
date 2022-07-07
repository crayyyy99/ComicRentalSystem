package gui;

import controller.ComicController;
import controller.ComicCopyController;
import controller.ComicGenreController;
import controller.UserController;
import model.Comic;
import model.ComicCopy;
import model.ComicGenre;
import model.User;

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

public class CuComicViewDetailsDialog extends JDialog {
	private final int MAX_INT = 2147483467;
	private boolean selected = false;
	private ComicCopy selectedCC = new ComicCopy();
	private ArrayList<String> genreList = new ArrayList<String>();
	private ArrayList<ComicCopy> comicCopyList = new ArrayList<ComicCopy>();
	private JTextField txtcomicid;
	private JTextField txtauthor;
	private JTextField txttitle;
	private JTextField txtgenre;
	private JTextField txtprice;
	private JTextArea txtdescription;
	
	public CuComicViewDetailsDialog(ComicCopy comicCopy) {		
		
		selectedCC = comicCopy;
		
		getContentPane().setBackground(new Color(255, 255, 204));
		
		txtcomicid = new JTextField();
		txtcomicid.setEditable(false);
		txtcomicid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtcomicid.setColumns(10);
		
		txtauthor = new JTextField();
		txtauthor.setEditable(false);
		txtauthor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtauthor.setColumns(10);
		
		JButton btnBack = new JButton("Close");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CuComicViewDetailsDialog.this.dispose();
			}
		});
		btnBack.setVerticalAlignment(SwingConstants.BOTTOM);
		btnBack.setFont(new Font("Sitka Subheading", Font.PLAIN, 16));

		
		JLabel lblNewLabel_1 = new JLabel("Comic ID:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_2 = new JLabel("Title:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_3 = new JLabel("Genre:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_4 = new JLabel("Author:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_5 = new JLabel("Price (RM):");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_6 = new JLabel("Description:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txttitle = new JTextField();
		txttitle.setEditable(false);
		txttitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txttitle.setColumns(10);
		
		txtgenre = new JTextField();
		txtgenre.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtgenre.setEditable(false);
		txtgenre.setColumns(10);
		
		txtprice = new JTextField();
		txtprice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtprice.setEditable(false);
		txtprice.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(63)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
								.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
								.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
								.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
								.addComponent(lblNewLabel_6, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(txtgenre, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
									.addComponent(txtauthor, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
									.addComponent(txttitle, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
									.addComponent(txtcomicid, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(txtprice, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, 207, Short.MAX_VALUE))))))
					.addGap(62))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(49)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtcomicid, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txttitle, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtgenre, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtauthor, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(txtprice, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
					.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addGap(39))
		);
		
		txtdescription = new JTextArea();
		txtdescription.setEditable(false);
		scrollPane.setViewportView(txtdescription);
		getContentPane().setLayout(groupLayout);
		setTitle("View Comic Details");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 530, 574);
		setLocationRelativeTo(null);
				
		if(selectedCC!= null)
			loadComicData(selectedCC);
		
		
	}

	public void loadComicData(ComicCopy cc) {
		txtcomicid.setText(String.valueOf(cc.getComicId()));
		txttitle.setText(cc.getTitle());
		txtgenre.setText(cc.getGenre());
		txtauthor.setText(cc.getAuthor());
		txtprice.setText(String.valueOf(cc.getPrice()));
		txtdescription.setText(cc.getDescription());	
	}
	
}
