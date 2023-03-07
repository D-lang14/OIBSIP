package com.GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.GUI.Create.ButtonListener1;
import com.util.AtmUtility;
import com.util.DBConnection;

//GUI Login Class
public class Login extends JFrame {

	private static final int NULL = 0;
	
	private static Connection con = null;

//	Database Connection
	static {
		try {
			con = DBConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	JTextField tf1;
	JPasswordField pf3;
	JLabel headlabel, l1, l3, resultlabel;
	JButton btn1;
	ButtonListener buttonListener;
	ButtonListener1 buttonListener1;

//	Login Function
	public Login() {
		Container c = getContentPane();
		c.setLayout(null);
		c.setBackground(Color.WHITE);

		headlabel = new JLabel("Login To Your Account");
		headlabel.setForeground(Color.BLUE);
		headlabel.setFont(new Font("times new roman", Font.BOLD, 18));
		headlabel.setSize(330, 20);
		headlabel.setLocation(140, 10);

		l1 = new JLabel("Username");
		l1.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		l1.setSize(270, 30);
		l1.setLocation(40, 50);

		tf1 = new JTextField();
		tf1.setSize(120, 20);
		tf1.setLocation(40, 80);

		l3 = new JLabel("Pin");
		l3.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		l3.setSize(270, 30);
		l3.setLocation(40, 100);

		pf3 = new JPasswordField();
		pf3.setSize(120, 20);
		pf3.setLocation(40, 130);

		JButton btn = new JButton("Sign In");
		btn.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btn.setBorderPainted(false);
		btn.setSize(100, 30);
		btn.setLocation(120, 180);
		btn.setBackground(Color.LIGHT_GRAY);
		buttonListener = new ButtonListener();
		btn.addActionListener(buttonListener);

		resultlabel = new JLabel("");
		resultlabel.setForeground(Color.BLUE);
		resultlabel.setFont(new Font("Comic Sans MS", Font.BOLD, 14));
		resultlabel.setSize(330, 20);
		resultlabel.setLocation(150, 220);

		btn1 = new JButton("Back");
		btn1.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
		btn1.setBorderPainted(false);
		btn1.setSize(100, 30);
		btn1.setLocation(250, 180);
		btn1.setBackground(Color.LIGHT_GRAY);
		buttonListener1 = new ButtonListener1();
		btn1.addActionListener(buttonListener1);

		c.add(headlabel);
		c.add(l1);
		c.add(l3);
		c.add(tf1);
		c.add(pf3);
		c.add(btn);
		c.add(btn1);
		c.add(resultlabel);

		setTitle("Login Account");
		setSize(500, 300);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

//	Sign In Button
	public class ButtonListener implements ActionListener {

		@SuppressWarnings("unused")
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			String name = tf1.getText();
			@SuppressWarnings("deprecation")
			int passCode = Integer.valueOf(pf3.getText());

			if (name.equals("")) {
				resultlabel.setText("Please fill all required details !!");
				resultlabel.setForeground(Color.RED);
			} else if (name != "" || pf3 != null) {

				boolean in = AtmUtility.loginAccount(name, passCode);

				if (in == true) {

					try {

						String sql = "select accno from customer where pass_code=" + passCode;
						PreparedStatement st = con.prepareStatement(sql);
						ResultSet rs = st.executeQuery();
						if (rs.next()) {
							Transact obj = new Transact();
							obj.my_update(rs.getInt("accno"));
							obj.setVisible(true);
						}
						dispose();

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					resultlabel.setText("Username or Pin Not Available!");
					resultlabel.setForeground(Color.RED);
				}
			} else {
				resultlabel.setText("Username or Pin Not Available!");
				resultlabel.setForeground(Color.RED);
			}
		}
	}

//	Back Button
	public class ButtonListener1 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			new Bank();

		}
	}
}
