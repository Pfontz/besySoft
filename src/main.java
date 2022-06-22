import java.util.*;

public class main{

	public static void main(String[] args) {

		TiendaDeProductos T = new TiendaDeProductos();
		List<TiendaDeProductos.Seller> vendedores = T.showSellers(); 
		List<TiendaDeProductos.Product> productos = T.showProducts(); 
		while(true) {
			System.out.println("Tienda de productos APP");
			System.out.println();
			System.out.println("menu de opciones");
			System.out.println();
			System.out.println("nuevo producto (1)");
			System.out.println();
			System.out.println("nuevo vendedor (2)");
			System.out.println();
			System.out.println("nueva venta (3)");
			System.out.println();
			System.out.println("buscar producto (4)");
			System.out.println();
			System.out.println("calcular comision por vendedor (5)");
			System.out.println();
			System.out.println("salir (S)");
			System.out.println();
			Scanner in = new Scanner(System.in);
			String op = in.nextLine();
			op = op.toUpperCase();
			switch(op) {
				case("1"):
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
				case("2"):
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
				case("3"):
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
									System.out.println("tipo codigo numerico no valido, ingrese codigo numerico");
									ven = in.nextLine();
								}
							}
							while(!T.isSeller(ven_n)) {
								System.out.println("codigo vendedor incorrecto");
								ven = in.nextLine();
								try {
									ven_n = Integer.parseInt(ven);
								} catch (NumberFormatException ex){
									System.out.println("codigo vendedor incorrecto");									
								}
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
										listaProds.add(T.productSearch(articulo));// TODO: BUG, while(no encuentra el articulo: print codig inexistente; entrada por teclado ...)
									}
									//T.newSale(listaProds, T.findSeller(ven_n)); // TODO: BUG
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
									//T.newSale(listaProds, T.findSeller(ven_n)); // TODO: BUG
									break;
								default:
									System.out.println("opcion incorrecta");
									j=j-1;
								}
								if(j>-1 && j<5) {
									System.out.println("desea seguir agregando articulos?(SI/NO)");
									String resp = in.nextLine().toUpperCase();
									while(true) {
										switch(resp) {
										case("SI"):
										break;
										case("NO"):
										j=5;
										break;
										default:
											while(!((resp.equals("SI")) || (resp.equals("NO")))) {							
												System.out.println("opciones validas: SI/NO");
												resp = in.nextLine().toUpperCase();
											}										
										}
										break;
									}
								}
							}
							for(int p=0;p<listaProds.size();++p) {
								System.out.println(listaProds.get(p).name + "" + listaProds.get(p).code);
							}
						}else {
							System.out.println("no existen productos");
						}
					}else {
						System.out.println("no existen vendedores");
					}
				break;
				case("4"):
					System.out.println("buscar producto D");
					System.out.println("buscar producto por codigo (a)");
					System.out.println("buscar producto por nombre (b)");
					System.out.println("buscar producto categoria (c)");
					String opc = in.nextLine().toLowerCase();
					switch(opc) {
						case("a"):
							System.out.println("ingrese codigo de producto");
							int code;
							try {
								code = Integer.parseInt(in.nextLine());
								if(T.productSearch(code).code==code) {
									TiendaDeProductos.Product aProducto = T.productSearch(code);
									System.out.println("codigo: " + aProducto.code);
									System.out.println("nombre: " + aProducto.name);
									System.out.println("precio: " + aProducto.price);
									System.out.println("categoria: " + aProducto.category);
								} else {
									System.out.print("producto no encontrado");
								}
							} catch (NumberFormatException ex) {
								System.out.println("dato invalido");
							}
						break;
						case("b"):
							System.out.println("ingrese nombre de producto");
							String nomProd = in.nextLine();
							try {
								if(T.productSearch(nomProd).name.equals(nomProd)) {
									TiendaDeProductos.Product bProducto = T.productSearch(nomProd);
									System.out.println("codigo: " + bProducto.code);
									System.out.println("nombre: " + bProducto.name);
									System.out.println("precio: " + bProducto.price);
									System.out.println("categoria: " + bProducto.category);
								}	
							}catch (NullPointerException ex) {
								System.out.println("producto no encontrado");
							}
						break;
						case("c"):
							System.out.println("ingrese categoria de producto");
							String catProd = in.nextLine();
							List<TiendaDeProductos.Product> cProducto = T.productSearchC(catProd);
							if(cProducto.size()>0) {
								for(int i=0; i<cProducto.size();++i) {
									System.out.println("codigo: " + cProducto.get(i).code); 
									System.out.println("nombre: " + cProducto.get(i).name);
									System.out.println("precio: " + cProducto.get(i).price);
									System.out.println("categoria: " + cProducto.get(i).category);
									System.out.println();
								}
							}else {
								System.out.println("no existe categoria");
							}
						break;
						default:
							System.out.println("opcion invalida"); // vuelve al menu principal
							System.out.println();
					}
				break;
				case("5"):
					if(!T.emptySales()) {
						System.out.println("seleccione vendedor: ");
						for(int i=0; i<vendedores.size(); ++i) {
							System.out.println(vendedores.get(i).name + "(cod:" + vendedores.get(i).code + ")"); 
						}
						String vended = in.nextLine();
						try {
							int v = Integer.parseInt(vended);
							if(T.isSeller(v)) {
								TiendaDeProductos.Seller sell = T.findSeller(v);
								double comision = T.commision(sell);
								System.out.println(comision);
							}else {
								System.out.print("vendedor no encontrado");
							}
						} catch (NumberFormatException ex) {
							System.out.println("tipo codigo numerico ingresado incorrecto");
						}
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


