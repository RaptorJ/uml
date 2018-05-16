package repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

import Obj.Product;
import Obj.ProductContainer;
import user.Customer;
import user.CustomerContainer;

public class FlieRepository implements Repository {

	

	@Override
	public void load() {
		// TODO Auto-generated method stub
		Scanner sc;
		Customer s = null;
		
		int id;
		String firstN;
		String lastN;
		double balance;
		String email = null;
		String password;

		// Customer
		try {
			sc = new Scanner(new File("Customer.txt"));
			while (sc.hasNextLine()) {
				id = Integer.parseInt(sc.next());
				firstN= sc.next();
				lastN =sc.next();
				balance = Double.parseDouble(sc.next());
				lastN =sc.next();
				password = sc.next();

				s = new Customer(id,firstN,lastN,balance,email,password);
				System.out.println("emp created.");
				//ProductContainer.getInstance().addProduct(p);
				CustomerContainer.getInstance().add(s);
				System.out.println("emp added.");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		
		double prix;
		String name;
		String description;
		
		
		// Product
		try {
			sc = new Scanner(new File("Product.txt"));
			while (sc.hasNextLine()) {
				prix = Double.parseDouble(sc.next());
				name = sc.next();
				description = sc.next();

				Product p = new Product(prix, name, description, s);
				System.out.println("emp created.");
				ProductContainer.getInstance().addProduct(p);
				System.out.println("emp added.");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void save() {
		// TODO Auto-generated method stub

	}

}
