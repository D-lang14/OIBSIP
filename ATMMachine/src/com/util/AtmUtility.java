package com.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.Scanner;

public class AtmUtility {

	private static final int NULL = 0;
	private static Connection con = null;
	static String sql = "";

	static {
		try {
			con = DBConnection.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public static boolean createAccount(String name, int balance, int passCode) // create account function
	{
		try {
			if (name == "" || passCode == NULL) {
				System.out.println("All Field Required!");
				return false;
			}

			sql = "INSERT INTO customer(cname,balance,pass_code) values(?,?,?)";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, name);
			st.setInt(2, balance);
			st.setInt(3, passCode);

			if (st.executeUpdate() == 1) {
				System.out.println(name + ", Now You Login!");
				return true;
			}

		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Username Not Available!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

//	Login Function
	public static boolean loginAccount(String name, int passCode) // login method
	{
		try {
			if (name == "" || passCode == NULL) {
				System.out.println("All Field Required!");
				return false;
			}
			sql = "select * from customer where cname='" + name + "' and pass_code=" + passCode;
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			Scanner sc = new Scanner(System.in);

			if (rs.next()) {
				
//				Menu Driven Program
				
				int ch = 5;
				int amt = 0;
				int senderAc = rs.getInt("accno");
				int receiveAc;
				boolean flag = true;
				while (flag == true) {
					try {
						System.out.println("\n\n           Hello, " + rs.getString("cname"));
						System.out.println("\n1)Transfer Money");
						System.out.println("2)View Balance");
						System.out.println("3)Withdraw");
						System.out.println("4)Deposit");
						System.out.println("5)LogOut");

						System.out.print("\nEnter Choice:");
						ch = sc.nextInt();

						switch (ch) {
						case 1:
							System.out.print("Enter Receiver Account No:");
							receiveAc = sc.nextInt();
							System.out.print("Enter Amount:");
							amt = sc.nextInt();

							if (AtmUtility.transferMoney(senderAc, receiveAc, amt)) {
								System.out.println("");
								System.out.println("Money Sent Successfully!\n");
							} else {
								System.out.println("ERR :  Failed!\n");
							}
							break;
						case 2:
							AtmUtility.getBalance(senderAc);
							break;
						case 3:
							System.out.print("Enter Amount:");
							amt = sc.nextInt();
							AtmUtility.withdraw(amt, senderAc);
							System.out.println("");
							break;
						case 4:
							System.out.print("Enter Amount:");
							amt = sc.nextInt();
							AtmUtility.deposit(amt, senderAc);
							System.out.println("");
							break;
						case 5:
							flag = false;
							break;
						default:
							System.out.println("Err : Enter Valid input!\n");

						}

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				return false;
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Username Not Available!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

//	Withdraw Function
	public static void withdraw(int amount, int accno) throws SQLException {
		Statement st = con.createStatement();

		String sql = "update customer set balance=balance-" + amount + " where accno=" + accno;
		if (st.executeUpdate(sql) == 1) {
			System.out.println("Amount Debited!");
		} else {
			System.out.println("Insufficient Balance");
		}
	}

//	Deposit Function
	public static void deposit(int amount, int accno) throws SQLException {
		Statement st = con.createStatement();

		String sql = "update customer set balance=balance+" + amount + " where accno=" + accno;
		st.executeUpdate(sql);
		System.out.println("Your Money has been successfully depsited");
		System.out.println("");
	}

//	Fetch details of User
	public static void getBalance(int acNo)
	{
		try {

			sql = "select * from customer where accno=" + acNo;
			PreparedStatement st = con.prepareStatement(sql);

			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				System.out.println("Balance : " + rs.getInt("balance"));
			}
			System.out.println("\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	Money Transfer Function
	public static boolean transferMoney(int sender_ac, int reveiver_ac, int amount) throws SQLException // transfer
																										// money method
	{
		if (reveiver_ac == NULL || amount == NULL) {
			System.out.println("All Field Required!");
			return false;
		}
		try {
			con.setAutoCommit(false);
			sql = "select * from customer where accno=" + sender_ac;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				if (rs.getInt("balance") < amount) {
					System.out.println("Insufficient Balance!");
					return false;
				}
			}

			Statement st = con.createStatement();

			con.setSavepoint();

			sql = "update customer set balance=balance-" + amount + " where accno=" + sender_ac;
			if (st.executeUpdate(sql) == 1) {
				System.out.println("Amount Debited!");
			}

			sql = "update customer set balance=balance+" + amount + " where accno=" + reveiver_ac;
			st.executeUpdate(sql);

			con.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			con.rollback();
		}
		return false;
	}
}