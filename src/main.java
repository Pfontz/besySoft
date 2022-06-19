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
			//SyQstem.out.println(op);
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
					char ch='x';
					try{
						ch = cat.charAt(0);
					}
					catch (Exception ex){
						ex.printStackTrace();
					}
					TiendaDeProductos.Product nuevo_prod = new TiendaDeProductos.Product(211, prod, 23, ch);
					T.storeProduct(nuevo_prod);
					//System.out.println("la categoria es: ");
					//System.out.println(T.productSearch(211).category);
					break;
				case("B"):		
					System.out.println("ingrese nombre nuevo vendedor");
					String vend = in.nextLine();
					System.out.println(";");
					System.out.println("ingrese remuneracion");
					String incom = in.nextLine();
					int income=0;
					try{
						income = Integer.parseInt(incom);				  
					}
					catch (NumberFormatException ex){
						ex.printStackTrace();
					}
					TiendaDeProductos.Seller nuevo_vend = new TiendaDeProductos.Seller(211, vend, income);
					T.storeSeller(nuevo_vend);
				break;
				case("C"):
					System.out.println("seleccione vendedor: ");
					List<TiendaDeProductos.Seller> vendedores = T.showSellers(); // TODO:la lista puede ser vacia si no hay vendedores cargados
					for(int i=0; i<vendedores.size(); ++i) {
						System.out.println(vendedores.get(0).name + "(" + (i+1) + ")"); 
						//TODO:corregir lista vacia
					}
					String ven = in.nextLine(); // vendedor a asociar venta TODO:corregir valor vacio
					System.out.println(ven);
					System.out.println("registar nueva venta - seleccione a continuacion articulos a registrar (max 5)");
					System.out.println("buscar articulo (codigo, nombre)");
					String articulo = in.nextLine();
					List<TiendaDeProductos.Product> listaProds = new ArrayList<TiendaDeProductos.Product>(5);
					try {
						int codigo = Integer.parseInt(articulo);
						listaProds.add(T.productSearch(codigo));						
						T.newSale(listaProds, vendedores.get(Integer.parseInt(ven)));
					} catch (NumberFormatException ex) {
						T.productSearch(articulo);
					}
				break;
				case("D"):
					System.out.println("buscar producto D");
					System.out.println("buscar producto por codigo (a)");
					System.out.println("buscar producto por nombre (b)");
					System.out.println("buscar producto categoria (c)");
					char opc = in.nextLine().toLowerCase().charAt(0);
					switch(opc) {
						case('a'):
							System.out.println("ingrese codigo de producto");
							int code = Integer.parseInt(in.nextLine());
							TiendaDeProductos.Product aProducto = T.productSearch(code);
							System.out.println("codigo: " + aProducto.code);
							System.out.println("nombre: " + aProducto.name);
							System.out.println("precio: " + aProducto.price);
							System.out.println("categoria: " + aProducto.category);							
						break;
						case('b'):
							System.out.println("ingrese nombre de producto");
							String nomProd = in.nextLine();
							TiendaDeProductos.Product bProducto = T.productSearch(nomProd);
							System.out.println("codigo: " + bProducto.code);
							System.out.println("nombre: " + bProducto.name);
							System.out.println("precio: " + bProducto.price);
							System.out.println("categoria: " + bProducto.category);	
						break;
						case('c'):
							System.out.println("ingrese categoria de producto");
							char catProd = in.nextLine().toUpperCase().charAt(0);
							List<TiendaDeProductos.Product> cProducto = T.productSearch(catProd);
							for(int i=0; i<cProducto.size();++i) {
								System.out.println("codigo: " + cProducto.get(i).code); 
								System.out.println("nombre: " + cProducto.get(i).name);
								System.out.println("precio: " + cProducto.get(i).price);
								System.out.println("categoria: " + cProducto.get(i).category);
								System.out.println();
							}
						break;
						default:
							in.close();
							throw new RuntimeException("opcion invalida"); // termina la aplicacion							
					}
				break;
				case("E"):
					System.out.println("calcular comision E");
					
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


