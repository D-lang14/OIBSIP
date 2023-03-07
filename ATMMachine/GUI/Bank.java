package com.GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

// GUI Bank using Swing 
public class Bank extends JFrame {

//	Initialized Label and Button
	JLabel headlabel, l1, resultlabel;
	ButtonListener1 buttonListener1;
	ButtonListener2 buttonListener2;
	ButtonListener3 buttonListener3;
	ButtonListener4 buttonListener4;

//	Bank Function 
	public Bank() {
		
//		Container of JFrame
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.WHITE);

		headlabel = new JLabel("D. B. Bank");
		headlabel.setForeground(Color.BLUE);
		headlabel.setFont(new Font("times new roman", Font.BOLD, 18));
		headlabel.setSize(330, 20);
		headlabel.setLocation(80, 10);

		l1 = new JLabel("Welcome To D. B. Bank");
		l1.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		l1.setForeground(Color.BLACK);
		l1.setSize(270, 20);
		l1.setLocation(40, 50);

		resultlabel = new JLabel("");
		resultlabel.setForeground(Color.BLUE);
		resultlabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		resultlabel.setSize(330, 20);
		resultlabel.setLocation(40, 70);

		JButton b1 = new JButton("Create");
		b1.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		b1.setBorderPainted(false);
		b1.setSize(100, 30);
		b1.setLocation(260, 60);
		b1.setBackground(Color.LIGHT_GRAY);
		buttonListener1 = new ButtonListener1();
		b1.addActionListener(buttonListener1);

		JButton b2 = new JButton("Login");
		b2.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		b2.setSize(100, 30);
		b2.setLocation(260, 100);
		b2.setBackground(Color.LIGHT_GRAY);
		buttonListener2 = new ButtonListener2();
		b2.addActionListener(buttonListener2);

		JButton b3 = new JButton("Logout");
		b3.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		b3.setSize(100, 30);
		b3.setLocation(260, 140);
		b3.setBackground(Color.LIGHT_GRAY);
		buttonListener3 = new ButtonListener3();
		b3.addActionListener(buttonListener3);

		JButton b4 = new JButton("Cancel");
		b4.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		b4.setSize(100, 30);
		b4.setLocation(40, 180);
		b4.setBackground(Color.RED);
		b4.setForeground(Color.WHITE);
		buttonListener4 = new ButtonListener4();
		b4.addActionListener(buttonListener4);

//		Container adds all Labels and Buttons
		c.add(headlabel);
		c.add(l1);
		c.add(resultlabel);
		c.add(b1);
		c.add(b2);
		c.add(b3);
		c.add(b4);

		setTitle("ATM Interface");
		setSize(500, 300);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

//	Create Button
	public class ButtonListener1 implements ActionListener {
		@SuppressWarnings("unused")
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			if (new Create() != null) {
				Create c = new Create();
				String name = c.tf1.getText();
				resultlabel.setText(name + "Now You can Login!");
				resultlabel.setForeground(Color.GREEN);
			} else {
				resultlabel.setText("Please Enter Coorect Credentials!");
				resultlabel.setForeground(Color.RED);
			}


		}
	}

//	Login Button
	public class ButtonListener2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			new Login();

		}
	}

//	Logout Button
	public class ButtonListener3 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
		}
	}

//	Cancel Button
	public class ButtonListener4 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.exit(0);
		}
	}

	public static void main(String[] args) {
		new Bank();
	}

}
