/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.Util;

import ec.edu.espol.classes.Comprador;
import ec.edu.espol.classes.Oferta;
import ec.edu.espol.classes.Vehiculo;
import ec.edu.espol.classes.Vendedor;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class Util {
    private Util(){}
    
    public static int nextID(String nomFile)
    {
        int id = 0;
        try(Scanner sc = new Scanner (new File(nomFile)))
        {
            while(sc.hasNextLine())
            {
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                id =  Integer.parseInt(tokens[0]);
            }
        }
        catch(Exception e){}
        return id+1;
    }
    
     public static void mostrarMenu(Scanner scanner) {
        int opcion;
        do {
            System.out.println("Menú de Opciones:");
            System.out.println("1. Vendedor");
            System.out.println("2. Comprador");
            System.out.println("3. Salir");
            System.out.print("Ingrese la opción deseada: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    menuVendedor(scanner);
                    break;
                case 2:
                    menuComprador(scanner);
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
        } while (opcion != 3);
    }
     
        public static void menuVendedor(Scanner scanner) {
        int opcion;
        String nomFileVendedores = "Vendedores.txt";
        String nomFileCompradores= "Compradores.txt";
        String nomFileVehiculos = "Vehiculos.txt";
        String nomFileOfertas = "Ofertas.txt";
        do {
            System.out.println("\nOpciones del Vendedor:");
            System.out.println("1. Registrar un nuevo vendedor");
            System.out.println("2. Registrar un nuevo vehículo");
            System.out.println("3. Aceptar oferta");
            System.out.println("4. Regresar");
            System.out.print("Ingrese la opción deseada: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    Vendedor.registrarVendedor(scanner, nomFileVendedores);
                    break;
                case 2:
                    Vendedor.registrarVehiculo( scanner, nomFileVehiculos, nomFileVendedores);
                    break;
                case 3:
                    Vendedor.aceptarOferta(scanner, nomFileVendedores, nomFileVehiculos, nomFileCompradores, nomFileOfertas);
                    break;
                case 4:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
        } while (opcion != 4);
    }

    public static void menuComprador(Scanner scanner) {
        int opcion;
        String nomFileVendedores = "Vendedores.txt";
        String nomFileCompradores= "Compradores.txt";
        String nomFileVehiculos = "Vehiculos.txt";
        String nomFileOfertas = "Ofertas.txt";
        do {
            System.out.println("\nOpciones del Comprador:");
            System.out.println("1. Registrar un nuevo comprador");
            System.out.println("2. Ofertar por un vehículo");
            System.out.println("3. Regresar");
            System.out.print("Ingrese la opción deseada: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    Comprador.registrarComprador(scanner, nomFileCompradores);
                    break;
                case 2:
                    Comprador.ofertarVehiculo(scanner, nomFileVehiculos, nomFileCompradores, nomFileOfertas);
                    break;
                case 3:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
        } while (opcion != 3);
    }
    
    public static int menúAceptarOferta(Scanner sc, int i, int totalOfertas, Oferta o, String nomFileVehiculos, 
            String nomFileOfertas, ArrayList<Vehiculo> vehiculos, ArrayList<Oferta> ofertas, String tipoVehiculo, 
            Vehiculo vehiculo, Oferta oferta)
    {
        System.out.println("1. Siguiente oferta");
                    
        if(i >0)
        {
            System.out.println("2. Anterior oferta  ");
        }
                    
        if (i==0)
        {
        System.out.println("2. Aceptar oferta");
        }
        else{
            System.out.println("3. Aceptar oferta");
            }
        int opcion = sc.nextInt();
        sc.nextLine();
        switch (opcion) {
                    case 1:
                        i++;
                        if (i >= totalOfertas) {
                            System.out.println("No hay más ofertas para este vehiculo");
                            i = (totalOfertas) - 1;
                        }
                        break;
                    case 2:
                        
                        if (i == 0) { 
                            //eliminarVehiculo( nomFileVehiculos,  tipoVehiculo,  vehiculos, vehiculo);
                           // eliminarOferta(nomFileOfertas, ofertas,  oferta);
                            System.out.println("Oferta "+(i+1)+ " aceptada. Email enviado a "+o.getComprador().getCorreoElectronico());
                            i=totalOfertas;
                            break;
                            }
                        else{
                            i--;
                            break;
                        }
                        
                       
                    case 3:
                        if(i==0){
                            System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                        break;}
                        else
                        {
                            //eliminarVehiculo( nomFileVehiculos,  tipoVehiculo,  vehiculos, vehiculo);
                            //eliminarOferta(nomFileOfertas, ofertas,  oferta);
                            System.out.println("Oferta "+(i+1)+ " aceptada. Email enviado a "+o.getComprador().getCorreoElectronico());
                            i=totalOfertas;
                           
                            break;}
                        
                    default:
                        System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                        break; 
            }
        return i;
    }
    
    public static void eliminarVehiculo(String nomFileVehiculo, String tipoVehiculo, ArrayList<Vehiculo> vehiculos, 
            Vehiculo vehiculo, String nomFileOferta, ArrayList<Oferta> ofertas,Oferta o, 
            ArrayList<Comprador> compradores)
    {
        int id_vehiculo = vehiculo.getId();
        ArrayList<Vehiculo> vehiculosNuevos =  vehiculos_nuevos(vehiculos, vehiculo);
        Oferta ofertasNuevas = ofertasNuevas(ofertas, o, id_vehiculo);
        if (ofertasNuevas != null){
        for (Comprador c: compradores){
        for (Oferta of : c.getOfertas())
        {
            int id_vehiculo_antes = o.getId_vehiculo();
            Vehiculo vehiculo_antes = Vehiculo.searchByID(vehiculos, id_vehiculo_antes);
            String placa_antes= vehiculo_antes.getPlaca();
            
            Vehiculo vehiculo_nuevo = Vehiculo.searchByPlaca(vehiculosNuevos, placa_antes);
            ofertasNuevas.setId_vehiculo(vehiculo_nuevo.getId());
        }
        }
        }
        else
        {
            System.out.println("No hay ofertas asociadas a ese vehiculo");
        }
       
    }
    
    public static ArrayList<Vehiculo> vehiculos_nuevos(ArrayList<Vehiculo> vehiculos, Vehiculo vehiculo)
    {
        vehiculos.remove(vehiculo.getId());
        ArrayList<Vehiculo> vehiculosNuevos = vehiculos;  
        return vehiculosNuevos;
    }
    
        public static Oferta ofertasNuevas(ArrayList<Oferta> ofertas, Oferta oferta, int id_vehiculo)
    {
        
            for (Oferta ofert: ofertas)
        {
            
            if(ofert.getId_vehiculo() != id_vehiculo)
            {
                return ofert; 
            }
            else
                return null;
        }
            return null;
    }
    
    public static void eliminarOferta(String nomFileOferta, ArrayList<Oferta> ofertas,Oferta oferta)
    {
        
        ofertas.remove(oferta.getId());
        Oferta.saveFile(nomFileOferta, ofertas);
    }
    

}
