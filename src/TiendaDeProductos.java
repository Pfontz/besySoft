import java.util.*;


public class TiendaDeProductos {
	
	//class constructor
	TiendaDeProductos() {
		this.sellers = new ArrayList<Seller>();
		this.prods = new ArrayList<Product>();
		this.sales = new ArrayList<Sale>();
	}
		
	//public methods 
	public void storeProduct(Product producto) {
		if(!this.prods.contains(producto)) {
			this.prods.add(producto);
		} else {
			return;
		}
	
	}		
	
	public void storeSeller(Seller vendedor) {
		if(!this.sellers.contains(vendedor)) {
			this.sellers.add(vendedor);
		}
		return;
	}
	
	public void newSale(Product[] prod, Seller sel) {
		Sale nSale = new Sale(sel,prod);
		this.sales.add(nSale);
	}
	
	public double commision(Seller ven) {
		double comm;
		int impVentas=0;
		int prodsVendidos=0;
		for(int i=0;i<this.sales.size();++i) {
			if(this.sales.get(i).sel==ven) {
				for(int j=0;j<this.sales.get(i).prods.length;++j) {
					impVentas+=this.sales.get(i).prods[j].price;
					prodsVendidos++;
				}
			}
		}
		if(prodsVendidos>2) {
			comm = impVentas * 0.10;
		}else {
			comm = impVentas * 0.05;
		}
		return comm;
	}
	
	//search products by category
	public ArrayList<Product> productSearch(char catg) {
		ArrayList<Product> resProducts = new ArrayList<Product>(); 
		for(int i = 0;i<this.prods.size();++i) {
			if(this.prods.get(i).category==catg) {
				resProducts.add(this.prods.get(i));
			}
		}
		return resProducts;
	}
	
	//search product by code
	public Product productSearch(int cod) {
		Product resProd = new Product();
		for(int i=0; i<this.prods.size();++i) {
			if(this.prods.get(i).code==cod) {
				resProd = this.prods.get(i);
			}
		}
		return resProd;
	}
	
	//search product by name
	public Product productSearch(String art) {
		Product resProd = new Product();
		for(int i=0; i<this.prods.size();++i) {
			if(this.prods.get(i).name==art) {
				resProd = this.prods.get(i);
			}
		}
		return resProd;
	}
	
	
	//included data types:
	static class Seller{//class to define Seller data type
			int code;
			String name;
			int income;
			
			//class constructor
			Seller(){};
			
			Seller(int cod, String nam, int inc){
				this.code = cod;
				this.name = nam;
				this.income = inc;
			}
	}
	
	static class Product{//class to define Product data type
		int code;
		String name;
		int price;
		char category;
		
		//class constructor
		Product(){};
		
		Product(int cod, String nam, int pr, char cat){
			this.code = cod;
			this.name = nam;
			this.price = pr;
			this.category = cat;
		}
	}
	
	static class Sale{//class to define a sale
		Seller sel;
		Product[] prods;
		
		//class constructor
		Sale(){};
		
		Sale(Seller sell, Product[] prs){
			this.sel = sell; // copia
			this.prods = prs; //copia
		}
	}
	
	
	private ArrayList<Seller> sellers; //sellers data structure
	private ArrayList<Product> prods; //products data structure
	private ArrayList<Sale> sales; //sales data structure 
	
	
}








