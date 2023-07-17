/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.classes;

import ec.edu.espol.Util.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class Comprador extends  Usuario{
    private ArrayList<Oferta> ofertas;  
    
    public Comprador(int id, String n, String a, String o, String correo, String clave)
    {
    super(id, n,a,o,correo,clave);
    this.ofertas = new ArrayList<>();
    }

    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }


    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }
    
    public static void registrarComprador(Scanner sc, String nomFile)
    {
        
        System.out.println("Registro de nuevo comprador");
        System.out.println("Nombres: ");
        String n = sc.nextLine();
        System.out.println("Apellidos: ");
        String a = sc.nextLine();
        System.out.println("Organización a la que pertenece: ");
        String o = sc.nextLine();
        System.out.println("Correo: ");
        String correo = sc.nextLine();
        System.out.println("Clave: ");
        String clave = sc.nextLine();
        int id = Util.nextID(nomFile);
        //verificar que el correo no esté registrado
        boolean correoExistente = verificarCorreo(nomFile, correo);
        if (!correoExistente){
        Comprador comprador = new Comprador(id,n,a,o,correo,clave);
        comprador.saveFile(nomFile);}
        else
            System.out.println("\nEl correo ya se encuentra registrado");
        
    }
        public static Comprador searchByCorreo(ArrayList<Comprador> compradores, String correo)
    {
        for (Comprador c : compradores)
        {
            if(c.getCorreoElectronico().equals(correo))
                return c;
        }
        return null;
    }

        public static Comprador searchByID(ArrayList<Comprador> compradores, int id)
    {
        for (Comprador c : compradores)
        {
            if(c.getId()==id)
                return c;
        }
        return null;
    }
        
    public void registrarOferta(Vehiculo v, String nomFileOferta, double precioOfertado)
    {
        
        int id = Util.nextID(nomFileOferta);
        Oferta o = new Oferta(id, v.getId(), super.getId(), precioOfertado);
        o.saveFile(nomFileOferta);
    }
    
        public void saveFile(String nomFile)
    {
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), true)))
        {
            pw.println(super.getId()+"|"+super.getNombres()+"|"+super.getApellidos()+"|"+super.getOrganizacion()+
                    "|"+super.getCorreoElectronico()+"|"+super.getClave());
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static ArrayList<Comprador> readFile(String nomFile)
    {
        boolean correoExistente=false;
        ArrayList<Comprador> compradores = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomFile)))
        {
            while(sc.hasNextLine())
            {
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                Comprador c = new Comprador(Integer.parseInt(tokens[0]), tokens[1], tokens[2],tokens[3],
                        tokens[4],tokens[5]);
                compradores.add(c);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return compradores;
    }
    public static void ofertarVehiculo(Scanner sc, String nomFileVehiculos, String nomFileCompradores, String nomFileOfertas)
    {
        ArrayList<Vehiculo> vehiculos = Vehiculo.readFile(nomFileVehiculos);
        Comprador comprador = Comprador.ingresoSistema(sc, nomFileCompradores);
        if(comprador!=null){
      
        ArrayList<Vehiculo> vehiculosFiltrados = ingresarOfertasTeclado(sc, vehiculos);
        System.out.println("\nResultados de la búsqueda: ");
        if(vehiculosFiltrados.isEmpty())
        {
            System.out.println("No se encontraron vehículos con las características especificadas: ");
        }
        else
        {
           
            int i=0;
     
            while((i>=0 && i<vehiculosFiltrados.size()))
            {
                
                Vehiculo v = vehiculosFiltrados.get(i); 
                System.out.println(v.toString());
                
                System.out.println("1. Siguiente Vehiculo");
                    
                    if(i >0)
                    {
                    System.out.println("2. Anterior Vehiculo");
                    }
                    
                    if (i==0){
                    System.out.println("2. Ofertar precio");
                        System.out.println("3. Salir");}
                    else{
                        System.out.println("3. Ofertar precio");
                        System.out.println("4. Salir");
                        }
                    
                    int opcion = sc.nextInt();
                    sc.nextLine();
                    
                    
                    switch (opcion) {
                    case 1:
                        i++;
                        if (i >= vehiculosFiltrados.size()) {
                            System.out.println("No hay más carros disponibles");
                            i = (vehiculosFiltrados.size()) - 1;
                        }
                        break;
                    case 2:
                        
                        if (i == 0) {
                            System.out.println("Indique el precio a ofertar: ");
                            double precioOfertado = sc.nextDouble();
                            sc.nextLine();
                            comprador.registrarOferta(v, nomFileOfertas, precioOfertado);
                            i=0;
                            vehiculosFiltrados.remove(i);
                            break;
                            }
                        else{
                            i--;
                            break;
                        }
                        
                       
                    case 3:
                        if(i==0){
                            i=vehiculosFiltrados.size();
                            
                        break;}
                        else
                        {System.out.println("Indique el precio a ofertar: ");
                            double precioOfertado = sc.nextDouble();
                            sc.nextLine();
                            comprador.registrarOferta(v, nomFileOfertas, precioOfertado);
                            vehiculosFiltrados.remove(i);
                            
                            i=0;
                            break;}
                        
                    case 4:
                        if (i==0)
                        {System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                        break;
                        }
                        else{
                            i=vehiculosFiltrados.size();
                            break;
                        }
                    
                    
                    default:
                        System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                        break; 
            }
            
        }
        }
        }
        else{System.out.println("Las credenciales no fueron las correctas");}
    }
    
    
    public static ArrayList<Vehiculo> filtrarVehiculos(ArrayList<Vehiculo> vehiculos,String tipoVehiculo,String recorridoMin,
            String recorridoMax, String anioMin, String anioMax, String precioMin, String precioMax)
    {
        ArrayList<Vehiculo> vehiculosFiltrados = new ArrayList<>();
       
        for (Vehiculo vehiculo : vehiculos) 
        {
            if (tipoVehiculo.isEmpty() || vehiculo.getTipoVehiculo().equalsIgnoreCase(tipoVehiculo))
            {
                double recorrido = vehiculo.getRecorrido();
                int anio = vehiculo.getAnio();
                double precio = vehiculo.getPrecio();
                
                boolean cumpleRecorrido = true;
                boolean cumpleAnio = true;
                boolean cumplePrecio = true;
                
                if (!recorridoMin.isEmpty() && recorrido < Double.parseDouble(recorridoMin)) {
                cumpleRecorrido = false;
            }
            
            if (!recorridoMax.isEmpty() && recorrido > Double.parseDouble(recorridoMax)) {
                cumpleRecorrido = false;
            }
            
            if (!anioMin.isEmpty() && anio < Integer.parseInt(anioMin)) {
                cumpleAnio = false;
            }
            
            if (!anioMax.isEmpty() && anio > Integer.parseInt(anioMax)) {
                cumpleAnio = false;
            }
            
            if (!precioMin.isEmpty() && precio < Double.parseDouble(precioMin)) {
                cumplePrecio = false;
            }
            
            if (!precioMax.isEmpty() && precio > Double.parseDouble(precioMax)) {
                cumplePrecio = false;
            }
            
            if (cumpleRecorrido && cumpleAnio && cumplePrecio) {
                vehiculosFiltrados.add(vehiculo);
            }
            }
        
        }
        return vehiculosFiltrados;
    }
        public static Comprador ingresoSistema(Scanner sc, String nomFile)
    {
        System.out.println("Verificación de credenciales");
        System.out.println("Introduzca su correo: ");
        String correo = sc.next();
        System.out.println("Ingrese su clave: ");
        String clave = sc.next();
        ArrayList<Comprador> compradores = Comprador.readFile(nomFile);
        Comprador comprador = Comprador.searchByCorreo(compradores, correo);
        
        if(comprador != null){
        return comprador;}
        
        else
            return null;
        
    }
        
    public static boolean verificarCorreo(String nomFile, String correo)
    {
        boolean correoExistente=false;
        
        try(Scanner sc = new Scanner(new File(nomFile)))
        {
            while(sc.hasNextLine())
            {
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                if(tokens[4].equals(correo)){
                    correoExistente=true;
                    break;
                }       
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return correoExistente;
    }
    
    public static ArrayList<Vehiculo> ingresarOfertasTeclado(Scanner sc, ArrayList<Vehiculo> vehiculos)
    {
      System.out.println("\n<Busqueda de vehículos>");
        System.out.println("Ingrese los criterios de búsqueda (deje en blanco si no quiere especificar)");
        
        System.out.print("\nTipo de vehiculo (auto/camioneta/moto): ");
        String tipoVehiculo = sc.nextLine().trim().toLowerCase();
        sc.nextLine();
        
        System.out.print("\nRecorrido (min-max): ");
        String recorridoInput = sc.nextLine().trim();
        double recorridoMin = Double.MIN_VALUE;
        double recorridoMax = Double.MAX_VALUE;
        if (!recorridoInput.isEmpty()) {
            String[] recorridoRange = recorridoInput.split("-");
            if (recorridoRange.length == 2) {
                recorridoMin = Double.parseDouble(recorridoRange[0]);
                recorridoMax = Double.parseDouble(recorridoRange[1]);
            }
        }
        
        System.out.print("\nAño (min-max): ");
        String anioInput = sc.nextLine().trim();
        int anioMin = Integer.MIN_VALUE;
        int anioMax = Integer.MAX_VALUE;
        if (!anioInput.isEmpty()) {
            String[] anioRange = anioInput.split("-");
            if (anioRange.length == 2) {
                anioMin = Integer.parseInt(anioRange[0]);
                anioMax = Integer.parseInt(anioRange[1]);
            }
        }
        
        System.out.print("\nPrecio (min-max): ");
        String precioInput = sc.nextLine().trim();
        double precioMin = Double.MIN_VALUE;
        double precioMax = Double.MAX_VALUE;
        if (!precioInput.isEmpty()) {
            String[] precioRange = precioInput.split("-");
            if (precioRange.length == 2) {
                precioMin = Double.parseDouble(precioRange[0]);
                precioMax = Double.parseDouble(precioRange[1]);
            }
        }
     ArrayList<Vehiculo> vehiculosFiltrados = filtrarVehiculos(vehiculos, tipoVehiculo, String.valueOf(recorridoMin),
            String.valueOf(recorridoMax), String.valueOf(anioMin), String.valueOf(anioMax),
            String.valueOf(precioMin), String.valueOf(precioMax));

    return vehiculosFiltrados;
        
    }
}
