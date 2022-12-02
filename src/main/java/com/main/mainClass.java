package com.main;

import java.util.List;
import java.util.Scanner;

import com.conn.DBConnect;
import com.dao.contactDAO;
import com.entity.contact;

public class mainClass {
	public static void main(String args[]) {

		boolean f = true;

		while (f) {
			System.out.println("-------------------");
			System.out.println("1.Create Contact");
			System.out.println("2.Edit Contact");
			System.out.println("3.Delete Contact");
			System.out.println("4.View Contact");
			System.out.println("5.Exit");
			System.out.println("------------------");

			Scanner sc = new Scanner(System.in);
			System.out.println("Enter the number");
			int number = sc.nextInt();

			contactDAO cDao = new contactDAO(DBConnect.getConnection());

			switch (number) {
			case 1:
				System.out.println("Enter name");
				String name = sc.next();

				System.out.println("Enter phone number");
				String phone = sc.next();

				contact c = new contact();
				c.setName(name);
				c.setPhone(phone);

				boolean s1 = cDao.saveContact(c);
				if (s1) {
					System.out.println("Contact saved");
				} else {
					System.out.println("Not saved");
				}

				break;
			case 2:
				System.out.println("Enter id");
				int id = sc.nextInt();
				System.out.println("Enter name");
				String name2 = sc.next();
				System.out.println("Enter phone");
				String phone2 = sc.next();
				contact c2 = new contact();
				c2.setId(id);
				c2.setName(name2);
				c2.setPhone(phone2);

				boolean s2 = cDao.editContact(c2);
				if (s2) {
					System.out.println("Edit successful");
				} else {
					System.out.println("Not Successful");
				}

				break;
			case 3:
				System.out.println("Enter id");
				int id2 = sc.nextInt();

				boolean s3 = cDao.deleteContact(id2);
				if (s3) {
					System.out.println("Delete successful");
				} else {
					System.out.println("Not Successful");
				}

				break;
			case 4:
				List<contact> con = cDao.getAllContact();
				if (con.isEmpty()) {
					System.out.println("no number available");
				} else {
					for (contact contact : con) {
						System.out.println("Id" + contact.getId());
						System.out.println("name" + contact.getName());
						System.out.println("phone" + contact.getPhone());
					}
				}
				break;
			case 5:
				f = false;
				break;

			default:
				System.out.println("Please enter correct number");
				break;
			}

		}

	}
}
