import java.util.*;

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
					System.out.println("ingrese nombre de producto a crear");
					String prod = in.nextLine();
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
					 }
				     catch (Exception ex){
				    	 ex.printStackTrace();
				     }
					 TiendaDeProductos.Product nuevo_prod = new TiendaDeProductos.Product(211, prod, 23, 'A');
					 T.storeProduct(nuevo_prod);
					break;
				case("B"):		
					System.out.println("ingrese nombre nuevo vendedor");
					String vend = in.nextLine();
					System.out.println(";");
					System.out.println("ingrese remuneracion");
					String incom = in.nextLine();
					try{
			            int income = Integer.parseInt(incom);				  
					}
				    catch (NumberFormatException ex){
				    	ex.printStackTrace();
				    }
					TiendaDeProductos.Seller nuevo_vend = new TiendaDeProductos.Seller(211, vend, 1234);					
					break;
				case("C"):
					System.out.println("registar nueva venta - seleccione a continuacion articulos a registrar (max 5)");
					System.out.println("buscar articulo (codigo, nombre)");
					String articulo = in.nextLine();
					try {
						int codigo = Integer.parseInt(articulo);
						T.productSearch(codigo);
						
					} catch (NumberFormatException ex) {
						T.productSearch(articulo);
					}
					break;
				case("D"):
					System.out.println("ha elegido opcion D");
					break;
				case("E"):
					System.out.println("ha elegido opcion E");
					break;
				case("S"):
					System.out.println("cerrando...");
					in.close();
					System.exit(0);
					break;
				default:
					in.close();
					throw new RuntimeException("opcion invalida"); // termina la aplicacion
					
			}
		}
	}
}	
	

