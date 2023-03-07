package com.GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.util.AtmUtility;

//GUI Create Class
public class Create extends JFrame {

	JTextField tf1, tf2;
	JPasswordField pf3;
	JLabel headlabel, l1, l2, l3, resultlabel;
	JButton btn1, btn2;
	ButtonListener buttonListener;
	ButtonListener1 buttonListener1;

	public Create() {
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.WHITE);

		headlabel = new JLabel("Create Your Account");
		headlabel.setForeground(Color.BLUE);
		headlabel.setFont(new Font("times new roman", Font.BOLD, 18));
		headlabel.setSize(330, 20);
		headlabel.setLocation(160, 10);

		l1 = new JLabel("Username");
		l1.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		l1.setSize(270, 30);
		l1.setLocation(40, 50);

		tf1 = new JTextField();
		tf1.setSize(120, 20);
		tf1.setLocation(40, 80);

		l2 = new JLabel("Balance");
		l2.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		l2.setSize(270, 30);
		l2.setLocation(40, 100);

		tf2 = new JTextField();
		tf2.setSize(120, 20);
		tf2.setLocation(40, 130);

		l3 = new JLabel("Pin");
		l3.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		l3.setSize(270, 30);
		l3.setLocation(40, 150);

		pf3 = new JPasswordField();
		pf3.setSize(120, 20);
		pf3.setLocation(40, 180);

		JButton btn = new JButton("Submit");
		btn.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btn.setBorderPainted(false);
		btn.setSize(100, 30);
		btn.setLocation(140, 220);
		btn.setBackground(Color.PINK);
		buttonListener = new ButtonListener();
		btn.addActionListener(buttonListener);

		resultlabel = new JLabel("");
		resultlabel.setForeground(Color.BLUE);
		resultlabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		resultlabel.setSize(330, 20);
		resultlabel.setLocation(140, 200);

		btn1 = new JButton("Back");
		btn1.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btn1.setBorderPainted(false);
		btn1.setSize(100, 30);
		btn1.setLocation(280, 220);
		btn1.setBackground(Color.PINK);
		buttonListener1 = new ButtonListener1();
		btn1.addActionListener(buttonListener1);

		c.add(headlabel);
		c.add(l1);
		c.add(l2);
		c.add(l3);
		c.add(tf1);
		c.add(tf2);
		c.add(pf3);
		c.add(btn);
		c.add(btn1);
		c.add(resultlabel);

		setTitle("Create Account");
		setSize(500, 300);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

//	Submit Button
	public class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			String name = tf1.getText();
			int balance = Integer.parseInt(tf2.getText());
			@SuppressWarnings("deprecation")
			int passCode = Integer.parseInt(pf3.getText());

			if (name != null || balance != 0 || passCode != 0) {
				
				boolean in = AtmUtility.createAccount(name, balance, passCode);

				if (in == true) {
					Bank b = new Bank();
					b.buttonListener2.actionPerformed(e);
					resultlabel.setText("Succesfully Created The Account!");
					resultlabel.setForeground(Color.GREEN);
					setLocation(810, 0);

				} else {
					resultlabel.setText("Username Not Available!");
					resultlabel.setForeground(Color.RED);
				}

			} else {
				resultlabel.setText("Please fill all required details !!");
				resultlabel.setForeground(Color.RED);
			}
		}
	}

//	Back Button
	public class ButtonListener1 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			new Bank();

		}
	}

//	public static void main ( String [] args) {
//		new Create();
//	}

}
