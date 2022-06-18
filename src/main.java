import java.util.*;

import TiendaDeProductos;

public class main{

	public static void main(String[] args) {

		TiendaDeProductos T = new TiendaDeProductos();
		while(true) {
			System.out.println("Tienda de productos APP");
			System.out.println(";");
			System.out.println("menu de opciones");
			System.out.println(";");
			System.out.println("nuevo producto (A)");
			System.out.println(";");
			System.out.println("nuevo vendedor (B)");
			System.out.println(";");
			System.out.println("nueva venta (C)");
			System.out.println(";");
			System.out.println("buscar producto (D)");
			System.out.println(";");
			System.out.println("calcular comision por vendedor (E)");
			System.out.println(";");
			System.out.println("salir (S)");
			System.out.println(";");
			System.out.println(";");
			System.out.println("seleccione opcion");
			Scanner in = new Scanner(System.in);
			String op = in.nextLine();
			op = op.toUpperCase();
			//System.out.println(op);
			switch(op) {
				case("A"):
					System.out.println("ha elegido opcion A");
					System.out.println("ingrese nombre de producto a crear");
					String prod = in.nextLine();
					System.out.println(prod);
					System.out.println(";");
					System.out.println("ingrese precio");
					String precio = in.nextLine();
					 try{
				            int price = Integer.parseInt(precio);				  
				            System.out.println(price);
					 }
				     catch (NumberFormatException ex){
				    	 ex.printStackTrace();
				     }
					System.out.println(";");
					System.out.println("ingrese categoria");
					String cat = in.nextLine();
					 try{
				            char ch = cat.charAt(0);
				            System.out.println(ch);
					 }
				     catch (Exception ex){
				    	 ex.printStackTrace();
				     }
					 TiendaDeProductos.Product nuevo = new TiendaDeProductos.Product(211, prod, 23, 'A');
					 T.storeProduct(nuevo);
					 System.out.println(T.productSearch(prod).name);
					break;
				case("B"):
					System.out.println("ha elegido opcion B");
					break;
				case("C"):
					System.out.println("ha elegido opcion C");
					break;
				case("D"):
					System.out.println("ha elegido opcion D");
					break;
				case("E"):
					System.out.println("ha elegido opcion E");
					break;
				case("S"):
					System.out.println("cerrando...");
					System.exit(0);
					break;
				default:
					throw new RuntimeException("opcion invalida"); // termina la aplicacion
			}
		}
	}
}	
	

