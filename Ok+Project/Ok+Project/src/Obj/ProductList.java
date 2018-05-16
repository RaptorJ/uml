package Obj;

import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import user.Customer;

public class ProductList extends JList<Product> {
	
	JList<Product> list;
	private Double prix;
	private String name;
	private String description;
	
	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public ProductList() {
		Scanner sc;
		
		Customer s = null;

		
		try {
			sc = new Scanner(new File("test.txt"));
			while(sc.hasNextLine()) {
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
		
		
		
		ListModel<Product> listModel;
		listModel = new DefaultListModel<Product>();
		for(Product item : ProductContainer.getInstance()) {
			Product p = new Product(item.getPrix(),item.getName(),item.getDescription(),null);
			((DefaultListModel<Product>) listModel).addElement(p);
		}
		
		list = new JList<Product>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(11);
		
	}
	
	private static ProductList INSTANCE = new ProductList();
	 
	public static ProductList getInstance()
	{   
		return INSTANCE;
	}
	
	
	

	public JList<Product> getList(){
		return list;
	}
}
