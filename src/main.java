import java.util.*;

public class main{

	public static void main(String[] args) {

		TiendaDeProductos T = new TiendaDeProductos();
		List<TiendaDeProductos.Seller> vendedores = T.showSellers(); // TODO:la lista puede ser vacia si no hay vendedores cargados
		List<TiendaDeProductos.Product> productos = T.showProducts(); // TODO:la lista puede ser vacia si no hay productos cargados
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
			switch(op) {
				case("A"):
					System.out.println("ingrese codigo de producto a crear");
					String cod_p = in.nextLine();
					int cod_n;
					while(true) {
						try{
							cod_n = Integer.parseInt(cod_p);
							while(T.productSearch(cod_n).code==cod_n) { //TODO:que devuelve productsearch si el producto con ese codigo no existe?
								System.out.println("codigo existente, elija otro codigo");
								cod_p = in.nextLine();
								cod_n = Integer.parseInt(cod_p);
							}
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
					int price;
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
					String cat = in.nextLine(); //puede ser cualquier string para este caso practico
					TiendaDeProductos.Product nuevo_prod = new TiendaDeProductos.Product(cod_n, prod, price, cat);
					T.storeProduct(nuevo_prod);
					System.out.println("producto creado");
					break;
				case("B"):
					System.out.println("ingrese codigo de nuevo vendedor");
					String codv = in.nextLine();
					int cod_v;
					while(true) {
						try{
							cod_v = Integer.parseInt(codv);
							while(T.isSeller(cod_v)) { 
								System.out.println("codigo existente, elija otro codigo");
								codv = in.nextLine();
								cod_v = Integer.parseInt(codv); 
							}
							break;
						}
						catch (NumberFormatException ex){
							System.out.println("Error tipo dato, introduzca un numero valido");
							codv = in.nextLine();
						}
					}								
					System.out.println("ingrese nombre nuevo vendedor");
					String vend = in.nextLine();
					System.out.println("ingrese remuneracion");
					String incom = in.nextLine();
					int income;
					while(true) {
						try{
							income = Integer.parseInt(incom);
							break;
						}
						catch (NumberFormatException ex){
							System.out.println("Error tipo dato, introduzca un numero valido");
							incom = in.nextLine();
						}
					}
					TiendaDeProductos.Seller nuevo_vend = new TiendaDeProductos.Seller(cod_v, vend, income);
					T.storeSeller(nuevo_vend);
				break;
				case("C"):
					if(!vendedores.isEmpty()) {
						if(!T.emptyProducts()) {
							System.out.println("seleccione vendedor: ");
							for(int i=0; i<vendedores.size(); ++i) {
								System.out.println(vendedores.get(i).name + "(cod: " + vendedores.get(i).code + ")"); 
							}
							String ven = in.nextLine();
							int ven_n;
							while(true) {
								try {
									ven_n = Integer.parseInt(ven);
									break;
								}catch (NumberFormatException ex){
									System.out.println("codigo vendedor incorrecto");
									ven = in.nextLine();
								}
							}
							while(!T.isSeller(ven_n)) {
								System.out.println("codigo vendedor incorrecto");
								ven = in.nextLine();
								ven_n = Integer.parseInt(ven);
							}
							System.out.println("registar nueva venta - seleccione a continuacion articulos a registrar (max 5)");						
							List<TiendaDeProductos.Product> listaProds = new ArrayList<TiendaDeProductos.Product>();
							for(int j=0;j<5;++j) {
								System.out.println("escriba 'listar' para ver todos los articulos o 'seleccionar' para seleccionar articulo (por codigo o nombre)");
								String opcion = in.nextLine().toLowerCase();
								switch(opcion) {
								case("seleccionar"):						
									System.out.println("indique articulo (codigo o nombre)");
									String articulo = in.nextLine();
									try {
										int codigo = Integer.parseInt(articulo);
										while(!(T.productSearch(codigo).code==codigo)) { //no existe el articulo a buscar
											System.out.println("codigo inexistente");
											articulo = in.nextLine();
											codigo = Integer.parseInt(articulo);
										}
										listaProds.add(T.productSearch(codigo));
										System.out.println(listaProds.get(0).code);
									} catch (NumberFormatException ex) {
										listaProds.add(T.productSearch(articulo));
									}
									T.newSale(listaProds, T.findSeller(ven_n));
									break;
								case("listar"):
									for(int i=0;i<productos.size();++i) { // listar productos
										System.out.println(productos.get(i).name + "(cod:" + productos.get(i).code + ")");
									}
									while(true) {
										String artic = in.nextLine();
										try {
											int art_n = Integer.parseInt(artic);
											if(!(T.productSearch(art_n).code==art_n)) {
												System.out.println("codigo inexistente, introduzca codigo correcto:");
											}else {
												listaProds.add(T.productSearch(art_n));
												break;
											}
										} catch (NumberFormatException ex) {
											System.out.println("dato incorrecto, introduzca codigo correcto:");	
										}	
									}
									T.newSale(listaProds, T.findSeller(ven_n));
									break;
								default:
									System.out.println("opcion incorrecta");
									j=j-1;
								}
								if(j>-1 && j<5) {
									System.out.println("desea seguir agregando articulos?(s/n)");
									String resp = in.nextLine().toLowerCase();
									while(true) {
										switch(resp) {
										case("s"):
										break;
										case("n"):
										j=5;
										break;
										default:
											System.out.println("opciones validas: s/n");
											resp = in.nextLine().toLowerCase();
										}
										break;
									}
								}
							}	
						}else {
							System.out.println("no existen productos");
						}
					}else {
						System.out.println("no existen vendedores");
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
							String catProd = in.nextLine();
							List<TiendaDeProductos.Product> cProducto = T.productSearchC(catProd);
							for(int i=0; i<cProducto.size();++i) {
								System.out.println("codigo: " + cProducto.get(i).code); 
								System.out.println("nombre: " + cProducto.get(i).name);
								System.out.println("precio: " + cProducto.get(i).price);
								System.out.println("categoria: " + cProducto.get(i).category);
								System.out.println();
							}
						break;
						default:
							System.out.println("opcion invalida"); // vuelve al menu principal
							System.out.println();
					}
				break;
				case("E"):
					if(!T.emptySales()) {
						System.out.println("seleccione vendedor: ");
						List<TiendaDeProductos.Seller> vends = T.showSellers(); //si existen ventas almacenadas => existen vendedores (lista no puede ser vacia)
						for(int i=0; i<vends.size(); ++i) {
							System.out.println(vends.get(i).name + "(" + (i) + ")"); 
						}
						String vended = in.nextLine(); // vendedor a asociar venta TODO:corregir valor vacio
						TiendaDeProductos.Seller sell = vendedores.get(Integer.parseInt(vended));
						double comision = T.commision(sell);
						System.out.println(comision);
					}else {
						System.out.println("no existen ventas registradas");
					}
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


