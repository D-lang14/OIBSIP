package com.GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.util.AtmUtility;

//Transactions Class
public class Transact extends JFrame {

	JLabel headlabel, l1, l2, l3, l4, resultlabel;
	JTextField tf1, tf2, tf3;
	ButtonListener1 buttonListener1;
	ButtonListener2 buttonListener2;
	ButtonListener3 buttonListener3;
	ButtonListener4 buttonListener4;
	ButtonListener5 buttonListener5;

//	Get Account Number Function
	public void my_update(int accno) {
		tf3.setText(accno + "");
	}

//	Transact function
	public Transact() {
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.WHITE);

		headlabel = new JLabel("D. B. Bank");
		headlabel.setForeground(Color.BLUE);
		headlabel.setFont(new Font("times new roman", Font.BOLD, 18));
		headlabel.setSize(330, 20);
		headlabel.setLocation(80, 10);

		l1 = new JLabel("Choose a Transaction");
		l1.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		l1.setForeground(Color.BLACK);
		l1.setSize(270, 20);
		l1.setLocation(40, 50);

		l4 = new JLabel("Your Account Number");
		l4.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		l4.setForeground(Color.BLACK);
		l4.setSize(270, 20);
		l4.setLocation(200, 10);

		tf3 = new JTextField();
		tf3.setSize(40, 20);
		tf3.setLocation(370, 10);

		resultlabel = new JLabel("");
		resultlabel.setForeground(Color.BLUE);
		resultlabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		resultlabel.setSize(330, 20);
		resultlabel.setLocation(40, 80);

		JButton b1 = new JButton("Transfer Money");
		b1.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		b1.setBorderPainted(false);
		b1.setSize(200, 30);
		b1.setLocation(235, 60);
		b1.setBackground(Color.LIGHT_GRAY);
		buttonListener1 = new ButtonListener1();
		b1.addActionListener(buttonListener1);

		JButton b2 = new JButton("Deposit");
		b2.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		b2.setSize(150, 30);
		b2.setLocation(260, 100);
		b2.setBackground(Color.LIGHT_GRAY);
		buttonListener2 = new ButtonListener2();
		b2.addActionListener(buttonListener2);

		JButton b3 = new JButton("Withdraw");
		b3.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		b3.setSize(150, 30);
		b3.setLocation(260, 140);
		b3.setBackground(Color.LIGHT_GRAY);
		buttonListener3 = new ButtonListener3();
		b3.addActionListener(buttonListener3);

		JButton b4 = new JButton("View Balance");
		b4.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		b4.setSize(150, 30);
		b4.setLocation(260, 180);
		b4.setBackground(Color.LIGHT_GRAY);
		buttonListener4 = new ButtonListener4();
		b4.addActionListener(buttonListener4);

		JButton b5 = new JButton("Logout");
		b5.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		b5.setSize(150, 30);
		b5.setLocation(60, 200);
		b5.setBackground(Color.RED);
		b5.setForeground(Color.WHITE);
		buttonListener5 = new ButtonListener5();
		b5.addActionListener(buttonListener5);

		c.add(headlabel);
		c.add(l1);
		c.add(l4);
		c.add(tf3);
		c.add(resultlabel);
		c.add(b1);
		c.add(b2);
		c.add(b3);
		c.add(b4);
		c.add(b5);

		tf3.setEditable(false);
		setTitle("Transaction");
		setSize(500, 300);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

//	Transfer Money Button
	public class ButtonListener1 implements ActionListener {

		@SuppressWarnings("unused")
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			MoneyTransfer mt = new MoneyTransfer();
			mt.my_update(Integer.parseInt(tf3.getText()));
			mt.setVisible(true);
			
			if (mt != null) {
				resultlabel.setText("Process was Successful!");
				resultlabel.setForeground(Color.GREEN);
			} else {
				resultlabel.setText("Something Went Wrong!");
				resultlabel.setForeground(Color.RED);
			}
			
		}
	}

//	Deposit Button
	public class ButtonListener2 implements ActionListener {
		@SuppressWarnings("unused")
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			Deposit dep = new Deposit();
			dep.my_update(Integer.parseInt(tf3.getText()));
			dep.setVisible(true);
			
			if (dep != null) {
				resultlabel.setText("Process was Successful!");
				resultlabel.setForeground(Color.GREEN);
			} else {
				resultlabel.setText("Something Went Wrong!");
				resultlabel.setForeground(Color.RED);
			}

		}
	}

//	Withdraw Button
	public class ButtonListener3 implements ActionListener {
		@SuppressWarnings("unused")
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			Withdraw wd = new Withdraw();
			wd.my_update(Integer.parseInt(tf3.getText()));
			wd.setVisible(true);

			if (wd != null) {
				resultlabel.setText("Process was Successful!");
				resultlabel.setForeground(Color.GREEN);
			} else {
				resultlabel.setText("Something Went Wrong!");
				resultlabel.setForeground(Color.RED);
			}

		}
	}

//	View Balance Button
	public class ButtonListener4 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			int sender_ac = Integer.parseInt(tf3.getText());
			int bal = AtmUtility.getBalance(sender_ac);

			if (bal != 0) {
				resultlabel.setText("Balance : " + bal);
				resultlabel.setForeground(Color.GREEN);
//				System.out.println("Process was Successful!");
			} else {
				resultlabel.setText("Something Went Wrong!");
				resultlabel.setForeground(Color.RED);
//				System.out.println("Something Went Wrong!");
			}

		}
	}

//	LogOut Button
	public class ButtonListener5 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			new Bank();
			
		}
	}
	
//	public static void main ( String [] args) {
//		new Transact();
//	}
}
