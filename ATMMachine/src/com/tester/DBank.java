package com.tester;

import java.io.IOException;
import java.util.Scanner;

import com.util.AtmUtility;

public class DBank {
	public static void main(String args[]) // main class of bank
			throws IOException {

		Scanner sc = new Scanner(System.in);
		String name = "";
		int pass_code;
		int balance;
		int ch;
		boolean flag = true;

		while (flag == true) {
			System.out.println("\n         Welcome to DBank         \n");
			System.out.println("1)Create Account");
			System.out.println("2)Login Account");
			System.out.println("3)Logout");

			try {
				System.out.print("\nEnter Input:"); // user input
				ch = sc.nextInt();

				switch (ch) {
				case 1:
					try {
						System.out.print("Enter Unique UserName:");
						name = sc.next();
						System.out.print("Enter Balance:");
						balance = sc.nextInt();
						System.out.print("Enter New Password:");
						pass_code = sc.nextInt();

						if (AtmUtility.createAccount(name, balance, pass_code)) {
							System.out.println("Account Created Successfully!\n");
						} else {
							System.out.println("ERR : Account Creation Failed!\n");
						}
					} catch (Exception e) {
						System.out.println(" ERR : Enter Valid Data::Insertion Failed!\n");
					}
					break;

				case 2:
					try {
						System.out.print("Enter  UserName:");
						name = sc.next();
						System.out.print("Enter  Password:");
						pass_code = sc.nextInt();

						if (AtmUtility.loginAccount(name, pass_code)) {
							System.out.println("Logout Successfully!\n");
						} else {
							System.out.println("ERR : login Failed!\n");
						}
					} catch (Exception e) {
						System.out.println(" ERR : Enter Valid Data::Login Failed!\n");
					}

					break;

				case 3:
					System.out.println("\n       Exited Successfully!\n\n           Thank You :)");
					flag = false;
					break;
				default:
					System.out.println(" ERR : Invalid Entry!\n");
				}
			} catch (Exception e) {
				System.out.println("Enter Valid Entry!");
			}
		}
		sc.close();
	}
}
