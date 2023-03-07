package com.GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.GUI.MoneyTransfer.ButtonListener6;
import com.GUI.MoneyTransfer.ButtonListener7;
import com.util.AtmUtility;

// GUI Deposit Class
public class Deposit extends JFrame {

	JLabel headlabel, l1, l2, l4, resultlabel;
	JTextField tf1, tf3;
	JButton b6, b7;
	ButtonListener6 buttonListener6;
	ButtonListener7 buttonListener7;

//	Get Account Number Function
	public void my_update(int accno) {
		tf3.setText(accno + "");
	}

//	Deposit Function
	public Deposit() {

		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.WHITE);

		headlabel = new JLabel("D. B. Bank");
		headlabel.setForeground(Color.BLUE);
		headlabel.setFont(new Font("times new roman", Font.BOLD, 20));
		headlabel.setSize(330, 20);
		headlabel.setLocation(80, 20);

		l1 = new JLabel("Choose a Transaction");
		l1.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));
		l1.setForeground(Color.BLACK);
		l1.setSize(270, 20);
		l1.setLocation(40, 60);

		l4 = new JLabel("Your Account Number");
		l4.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		l4.setForeground(Color.BLACK);
		l4.setSize(270, 20);
		l4.setLocation(200, 20);

		tf3 = new JTextField();
		tf3.setSize(40, 20);
		tf3.setLocation(370, 20);

		l2 = new JLabel("Amount");
		l2.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		l2.setSize(270, 30);
		l2.setLocation(40, 100);

		tf1 = new JTextField();
		tf1.setSize(70, 20);
		tf1.setLocation(40, 130);

		b6 = new JButton("Submit");
		b6.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		b6.setSize(130, 30);
		b6.setLocation(40, 180);
		b6.setBackground(Color.RED);
		b6.setForeground(Color.WHITE);
		buttonListener6 = new ButtonListener6();
		b6.addActionListener(buttonListener6);

		resultlabel = new JLabel("");
		resultlabel.setForeground(Color.BLUE);
		resultlabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		resultlabel.setSize(330, 20);
		resultlabel.setLocation(40, 220);

		b7 = new JButton("Back");
		b7.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		b7.setSize(130, 30);
		b7.setLocation(200, 180);
		b7.setBackground(Color.RED);
		b7.setForeground(Color.WHITE);
		buttonListener7 = new ButtonListener7();
		b7.addActionListener(buttonListener7);

		c.add(headlabel);
		c.add(resultlabel);
		c.add(l1);
		c.add(l4);
		c.add(tf3);
		c.add(l2);
		c.add(tf1);
		c.add(b6);
		c.add(b7);

		tf3.setEditable(false);
		setTitle("Deposit");
		setSize(500, 300);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

//	Submit Button
	public class ButtonListener6 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			try {
				int sender_ac = Integer.parseInt(tf3.getText());
				int amount = Integer.parseInt(tf1.getText());

				if (tf3.getText().isEmpty() || tf1.getText().isEmpty()) {
					resultlabel.setText("Please fill all required details !!");
					resultlabel.setForeground(Color.RED);
				} else {

					boolean dep = AtmUtility.deposit(amount, sender_ac);

					if (dep == true) {
//					System.out.println("Process was Successful!");
						Transact obj = new Transact();
						obj.my_update(Integer.parseInt(tf3.getText()));
						obj.setVisible(true);
					} else {
						System.out.println("Something Went Wrong!");
						new Transact();
					}
				}

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

//	Back Button
	public class ButtonListener7 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			new Transact();
		}
	}

//	public static void main(String[] args) {
//		new Deposit();
//	}

}
