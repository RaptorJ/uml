package user;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;



public class CustomerContainer {
	
	static ArrayList<Customer> list;
	
	CustomerContainer(){
		list = new ArrayList<Customer>();
		addCustomer("admin", "admin", "admin@ad.com", "admin");
	}
	
	CustomerContainer(ArrayList<Customer> list){
		this.list.addAll(list);		
	}
	
	private static CustomerContainer INSTANCE = new CustomerContainer();
	 
	public static CustomerContainer getInstance()
	{   return INSTANCE;
	}
	
	public static void load(){
		try{
		File file=new File("ContainerCustomer.txt"); 
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while ((line = br.readLine()) != null) {
			String str[] =line.split(";");
		}
		br.close();
		}catch (Exception e) {}
		int a =0;
		for(int i = 0; i<list.size();i++){
			if(list.get(i).getId()>a)a=list.get(i).getId();
		}	
		Customer.setCounterID(a);
	}
	
	public static void Save(){
		try{
			File ff=new File("customer.txt"); // définir l'arborescence
			ff.createNewFile();
			FileWriter ffw=new FileWriter(ff);
			for(int i = 0; i<list.size();i++){
				ffw.write(list.get(i).toString()); 
				ffw.write("\n"); // forcer le passage à la ligne
			}			
			ffw.close(); // fermer le 
		} catch (Exception e) {}
	}

	public void addCustomer(String firstN, String lastN, String email, String pas)
	{
		Customer cus = new Customer(firstN,lastN,0,email,pas);
		list.add(cus);
	}
	public void add(Customer c)
	{		
		list.add(c);
	}
	
	public Customer searchCustomer(String email, String pas) {
		
		
		for(Customer cus : list) {
			if( (cus.getEmail().equals(email)) && (cus.getPassword().equals(pas)) ){
				
				return cus;
			}
		}
		return null;
		
	}
		
}
