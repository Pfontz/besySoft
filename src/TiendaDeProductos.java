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
	
	public void newSale(List<Product> prod, Seller sel) {
		Sale nSale = new Sale(sel,prod);
		this.sales.add(nSale);
	}
	
	public double commision(Seller ven) {
		double comm;
		double five_percent = 0.05;
		double ten_percent = 0.10;
		int impVentas=0;
		int prodsVendidos=0;
		for(int i=0;i<this.sales.size();++i) {
			if(this.sales.get(i).sel==ven) {
				for(int j=0; j<this.sales.get(i).prods.size(); ++j) {
					impVentas+=this.sales.get(i).prods.get(j).price;
					prodsVendidos++;
				}
			}
		}
		if(prodsVendidos>2) {
			comm = impVentas * ten_percent;
		}else {
			comm = impVentas * five_percent;
		}
		return comm;
	}
	
	//search products by category
	public ArrayList<Product> productSearchC(String catg) {
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
			if(this.prods.get(i).name.equals(art)) {
				resProd = this.prods.get(i);
			}
		}
		return resProd;
	}
	
	//aux:
	public List<Seller> showSellers(){
		return this.sellers;
	}
	
	public boolean isSeller(int cod_v) {
		List<Seller> sellers = this.showSellers();
		for(int i=0;i<sellers.size();++i) {
			if(sellers.get(i).code==cod_v) {
				return true;
			}
		}
		return false;
	}
	
	public boolean emptyProducts() {
		if(this.prods.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public boolean emptySales() {
		if(this.sales.isEmpty()) {
			return true;
		}
		return false;
	}
	
	public List<Product> showProducts(){
		return this.prods;
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
		String category;
		
		//class constructor
		Product(){};
		
		Product(int cod, String nam, int pr, String cat){
			this.code = cod;
			this.name = nam;
			this.price = pr;
			this.category = cat;
		}
	}
	
	static class Sale{//class to define a sale
		Seller sel;
		List<Product> prods;
		
		//class constructor
		Sale(){};
		
		Sale(Seller sell, List<Product> prs){
			this.sel = sell; // copia
			this.prods = prs; //copia
		}
	}
	
	
	private List<Seller> sellers; //sellers data structure
	private List<Product> prods; //products data structure
	private List<Sale> sales; //sales data structure 
	
	
	
	
}








