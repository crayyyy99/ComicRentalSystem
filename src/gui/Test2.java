package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.*;

public class Test2 {
	
	public Test2() {		
		JFrame frame = new JFrame("Main Frame");
		frame.setSize(500,500);
		
		BorderLayout borderLayout = new BorderLayout();
		frame.setLayout(borderLayout);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		Test panel = new Test();
		frame.add(panel, BorderLayout.CENTER);
		
		frame.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		try {
			Test2 view = new Test2();			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
