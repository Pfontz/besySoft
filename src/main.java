import java.util.*;

public class main{

	public static void main(String[] args) {

		TiendaDeProductos T = new TiendaDeProductos();
		List<TiendaDeProductos.Seller> vendedores = T.showSellers(); // TODO:la lista puede ser vacia si no hay vendedores cargados
		while(true) {
			System.out.println("Tienda de productos APP");
			System.out.println();
			System.out.println("menu de opciones");
			System.out.println();
			System.out.println("nuevo producto (A)");
			System.out.println();
			System.out.println("nuevo vendedor (B)");
			System.out.println();
			System.out.println("nueva venta (C)");
			System.out.println();
			System.out.println("buscar producto (D)");
			System.out.println();
			System.out.println("calcular comision por vendedor (E)");
			System.out.println();
			System.out.println("salir (S)");
			System.out.println();
			Scanner in = new Scanner(System.in);
			String op = in.nextLine();
			op = op.toUpperCase();
			//SyQstem.out.println(op);
			switch(op) {
				case("A"):
					System.out.println("ingrese codigo de producto a crear");
					String cod_p = in.nextLine();
					int cod_n=0;
					while(true) {
						try{
							cod_n = Integer.parseInt(cod_p);				  					
							break;
						}
						catch (NumberFormatException ex){
							System.out.println("Error tipo dato, introduzca un numero valido");
							cod_p = in.nextLine();
						}
					}				
					System.out.println("ingrese nombre de producto a crear"); // pueden repetir nombres
					String prod = in.nextLine();			
					System.out.println("ingrese precio");
					String precio = in.nextLine();
					int price=0;
					while(true) {
						try{
							price = Integer.parseInt(precio);				  
							break;
						}
						catch (NumberFormatException ex){
							System.out.println("Error tipo dato, introduzca un numero valido");
							precio = in.nextLine();
						}
					}
					System.out.println("ingrese categoria");
					String cat = in.nextLine();
					char ch='x';
					try{
						ch = cat.charAt(0);
					}
					catch (Exception ex){
						ex.printStackTrace();
					}
					TiendaDeProductos.Product nuevo_prod = new TiendaDeProductos.Product(cod_n, prod, price, ch); //TODO:generar codigo aut
					T.storeProduct(nuevo_prod);
					System.out.println("producto creado");
					break;
				case("B"):		
					System.out.println("ingrese nombre nuevo vendedor");
					String vend = in.nextLine();
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
						listaProds.add(T.productSearch(articulo));
						T.newSale(listaProds, vendedores.get(Integer.parseInt(ven)));
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
					System.out.println("seleccione vendedor: ");
					List<TiendaDeProductos.Seller> vends = T.showSellers(); // TODO:la lista puede ser vacia si no hay vendedores cargados
					for(int i=0; i<vends.size(); ++i) {
						System.out.println(vends.get(0).name + "(" + (i+1) + ")"); 
						//TODO:corregir lista vacia
					}
					String vended = in.nextLine(); // vendedor a asociar venta TODO:corregir valor vacio
					TiendaDeProductos.Seller sell = vendedores.get(Integer.parseInt(vended));
					double comision = T.commision(sell);
					System.out.println(comision);					
				break;
				case("S"):
					System.out.println("cerrando...");
					in.close();
					System.exit(0);
				break;
				default:
					System.out.println("opcion invalida - eliga opcion correcta");
					System.out.println();
			}
		}
	}
}	


