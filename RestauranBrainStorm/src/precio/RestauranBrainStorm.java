package precio;
//import leer.*;

import java.util.Scanner;

public class RestauranBrainStorm {

    public static void main(String[] args) throws InterruptedException {
        Scanner tec = new Scanner(System.in);
        int formapago;
        int cantPersonas = 0;
        double descuento;
        double total = 0;
        int cont;
        int hora = 0;
        int M = 0;
        String nombrePersona[] = new String[cantPersonas];
       
        
        /*
     * Se pedirá la cantidad de artículos que elija el cliente y se dará el precio a pagar según esa lo elegido. 
         */

        System.out.println(" ");
        System.out.println("\tBienvenidos al restaurante BrainStorm");
        System.out.println(" ");
        System.out.println("\t--------------------------------------");
        System.out.println("\t-Le recordamos que tiene que reservar-");
        System.out.println("\t-con antipacion para mesas con mas de-");
        System.out.println("\t------------seis personas-------------");
        System.out.println("\t-------------!GRACIAS¡----------------");
        System.out.println("\t--------------------------------------");
        System.out.println(" ");

        boolean continuar = true;
        int lecturaProducto, lecturaCantidad; //Variables para seleccionar el producto y la cantidad que se quiere comprar

        //Se instancian y cargan los productos
        Producto plato1 = new Combos("Milanesas con pure + Gasosa 500ml ----------------$580", 580.00, true);
        Producto plato2 = new Combos("Ravioles con salsa blanca + Gaseosa 500ml --------$600", 600.00, true);
        Producto plato3 = new Combos("Pizza de panceta para 6 personas + Andes 1L ------$800", 800.00, true);
        Producto plato4 = new Combos("Lomo simple + Gaseosa 500ml ----------------------$700", 700.00, true);
        Producto plato5 = new Combos("Parrilla libre + Bebida a eleccion ---------------$1500", 1500.00, false);
        

        //Se crea el array "catálogo" para contener los productos. Su dimensión viene del número de veces que se
        //instancia el contructor de Producto
        Producto catalogo[] = new Producto[Producto.dimesionArray];
        //Se crea el objeto gestion para trabajar (mostrar, vender, y mostrar caja)
        Gestion gestion = new Gestion();

        //Se rellena el array catálogo
        catalogo[0] = plato1;
        catalogo[1] = plato2;
        catalogo[2] = plato3;
        catalogo[3] = plato4;
        catalogo[4] = plato5;
        

        do {
            System.out.println(" ");
            System.out.println("\tIntroduzca la opción que desea realizar: ");
            System.out.println("\t1. Mostrar productos");
            System.out.println("\t2. Ordenar combos");
            System.out.println("\t3. Mostrar total");
           // System.out.println("\t4. Reserva\n");
            System.out.println("SALIR --> Pulse cualquier otro número");
            System.out.println("--------------------------------------------");
            System.out.println("Opcion: ");

            switch (Leer.datoInt()) {
                case 1:
                    gestion.mostrarProductos(catalogo);
                    Thread.sleep(1 * 1000);
                    break;
                case 2:
                    do {
                        System.out.println(" ");
                        System.out.println("Ingresar cantidad de personas: ");
                        cantPersonas = tec.nextInt();
                        if (cantPersonas > 6) {
                            System.out.println("Ingrese cantidad permitida\n");
                        }
                    } while (cantPersonas > 6);

                    for (int i = 0; i < cantPersonas; i++) {

                        nombrePersona = new String[cantPersonas];

                        System.out.println("-> Persona " + (i + 1));
                        System.out.println("-> Nombre: ");
                        nombrePersona[i] = Leer.dato();
                        //tec.nextLine();
                        do {
                            System.out.println(" ");
                            System.out.println(nombrePersona[i] + " ¿Que combos desea ordenar?\n");
                            gestion.mostrarNombreProductos(catalogo);
                            Thread.sleep(1 * 1000);
                            System.out.println("Opcion: ");
                            lecturaProducto = Leer.datoInt();
                            if (lecturaProducto > 5) {
                                System.out.println("\nPor favor ingrese un combo disponible");
                            }
                        } while (lecturaProducto > 5);
                        System.out.println("¿Cuántas unidades desea ordenar?");
                        lecturaCantidad = Leer.datoInt();
                        //Se carga el producto y la cantidad solicitada por el usuario
                        gestion.comprarProducto(catalogo, lecturaProducto, lecturaCantidad);
                    }
                    break;
                case 3:
                    if (cantPersonas == 0) {
                        System.out.println(" ");
                        System.out.println("Por favor primero ingrese su nombre y pedido");
                        System.out.println(" ");
                        Thread.sleep(1 * 1000);
                    } else {
                        System.out.println("$ " + gestion.mostrarCaja());
                        System.out.println(" ");
                        System.out.println("[1]Desea pagar \n[2]Seguir continuar con los pedidos?");
                        cont = tec.nextInt();
                        if (cont == 1) {
                            System.out.println("Como desea abonar: ");
                            System.out.println("Elija un metodo de pago(1-3)\n");
                            // Mostramos las opciones de pago al usuario
                            System.out.println("(1).Efectivo 15% de descuento");
                            System.out.println("(2).Tarjeta 10% de descuento");
                            System.out.println("(3).Billetera virtual 8% de descuento\n");
                            do {
                                System.out.println("Digite su opcion");
                                //Solicitamos el metodo elegido
                                formapago = tec.nextInt();
                                if (formapago > 3) {
                                    System.out.println("Opcion incorrecta");
                                }
                            } while (formapago > 3);
                            descuento = 0;
                            switch (formapago) {
                                case 1:
                                    descuento = gestion.mostrarCaja() * 0.15;
                                    total = gestion.mostrarCaja() - descuento;
                                    // Segun la opcion seleccionada aplicar el descuento correspondiente
                                    break;
                                case 2:
                                    descuento = gestion.mostrarCaja() * 0.10;
                                    total = gestion.mostrarCaja() - descuento;
                                    break;
                                case 3:
                                    descuento = gestion.mostrarCaja() * 0.08;
                                    total = gestion.mostrarCaja() - descuento;
                                    break;

                            }
                            // Mostrar total a pagar
                            System.out.println("El total a pagar es de $" + total);
                            Thread.sleep(2 * 1000);
                            System.out.println(""); // no hay forma directa de borrar la consola en Java
                            // Animacion
                            System.out.println("\t...Procesando el pago...");
                            Thread.sleep(2 * 1000);
                            System.out.println(""); // no hay forma directa de borrar la consola en Java
                            // Mensaje de agradecimiento
                            System.out.println("Pago realizado. Muchas gracias por su visita, lo esperamos en otra ocasión.");
                        }
                    }
                    break;
                
                default:
                    //Se sale del programa
                    continuar = false;
            }

        } while (continuar);

        System.out.println("---- Gracias por usar la aplicación. ----");

    }
}
