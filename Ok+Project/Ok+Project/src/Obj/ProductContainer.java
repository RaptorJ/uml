package Obj;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;




public class ProductContainer implements Iterable<Product>{
	private  List<Product> listProducts;

	public ProductContainer(){
		this.listProducts = new ArrayList<Product>();
	}
	
	private static ProductContainer INSTANCE = new ProductContainer();
	 
	public static ProductContainer getInstance()
	{   return INSTANCE;
	}

	public void addProduct(Product p) {
		listProducts.add(p);
	}
	
	public void supprimerProduct(Product p) {
		listProducts.remove(p);
	}
	
	public void clearList() {
		listProducts.clear();
	}
	
	@Override
	public Iterator<Product> iterator() {
		return new Iterator<Product> () {
			private final Iterator<Product> iter = listProducts.iterator();

			@Override
			public boolean hasNext() {
				return iter.hasNext();
			}

			@Override
			public Product next() {
				return iter.next();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException("no changes allowed");
			}
		};
	}
	
	public Object[]  toArray() {
		return listProducts.toArray();
	}
	
	public Product getProductById(int id){
		return listProducts.get(id);
	}
	
	public Product searchProduct(String nom) {
		
		
		for(Product prod : listProducts) {
			if( prod.getName().equals(nom)){
				return prod;
			}
		}
		return null;
		
	}
	
	public ArrayList<Product> getList(){
		return getInstance().getList();
	}
	
}
