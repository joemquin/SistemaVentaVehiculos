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
public class Vendedor extends Usuario {
    private ArrayList<Vehiculo> vehiculos;
   
    public Vendedor(int id, String n, String a, String o, String correo, String clave)
    {
    super(id, n,a,o,correo,clave);
    this.vehiculos = new ArrayList<>();
   
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }
    
    public static Vendedor searchByCorreo(ArrayList<Vendedor> vendedores, String correo)
    {
        for (Vendedor v : vendedores)
        {
            if(v.getCorreoElectronico().equals(correo))
                return v;
        }
        return null;
    }
    
    public static Vendedor searchByID(ArrayList<Vendedor> vendedores, int id)
    {
        for (Vendedor v : vendedores)
        {
            if(v.getId()==id)
                return v;
        }
        return null;
    }
    
    public static void registrarVendedor(Scanner sc, String nomFile)
    {
        
        System.out.println("\nRegistro de nuevo vendedor");
        System.out.println("Nombres: ");
        String n= sc.nextLine();
        System.out.println("Apellidos: ");
        String a = sc.nextLine();
        System.out.println("Organización a la que pertenece: ");
        String o = sc.nextLine();
        System.out.println("Correo: ");
        String correo = sc.nextLine();
        System.out.println("Clave: ");
        String clave = sc.nextLine();
        
        /*for (Vendedor v : vendedores)
        {
            if (v.getCorreo().equals(correo))
            {
                correoExistente=true;
                
            }
        }^*/
        int id = Util.nextID(nomFile);
        
        Vendedor vendedor = new Vendedor(id, n,a,o,correo,clave);
        vendedor.saveFile(nomFile);
    }
    
      public static void registrarVehiculo(Scanner sc, String nomFileVehiculo, String nomFileVendedor)
    {
        
        Vendedor vendedor = Vendedor.ingresoSistema(sc, nomFileVendedor);
        if(vendedor != null){
        System.out.println("Tipo de vehiculo (auto/camioneta/moto): ");
        String tipoVehiculo = sc.next();
        
        int id_vendedor = vendedor.getId();
        
        
        if(tipoVehiculo.equals("auto")){
            Auto.nextAuto(sc, nomFileVehiculo, id_vendedor, tipoVehiculo);}
        else if(tipoVehiculo.equals("camioneta")){
            Camioneta.nextCamioneta(sc, nomFileVehiculo, id_vendedor, tipoVehiculo);}
        else{
            Vehiculo.nextVehiculo(sc, nomFileVehiculo, id_vendedor, tipoVehiculo);}
        
        }
        else
            System.out.println("Las credenciales ingresadas no fueron las correctas");
        
        }
    
      
    public static void aceptarOferta(Scanner sc, String nomFileVendedores, String nomFileVehiculos, String nomFileCompradores, String nomFileOfertas)
    {
        
        Vendedor vendedor = Vendedor.ingresoSistema(sc, nomFileVendedores);
        
        ArrayList<Vehiculo> listaVehiculos =  Vehiculo.readFile(nomFileVehiculos);
        ArrayList<Comprador> listaCompradores =  Comprador.readFile(nomFileCompradores);
        ArrayList<Oferta> listaOfertas =  Oferta.readFile(nomFileOfertas);
        
        Oferta.link(listaCompradores, listaOfertas, listaVehiculos);
        if (vendedor != null) 
        {
            System.out.println("\nIngrese la placa del vehículo: ");
            String placa = sc.next();
            
            Vehiculo vehiculo = Vehiculo.searchByPlaca(listaVehiculos, placa);
            
            if (vehiculo != null)
            {
                ArrayList<Oferta> ofertas = vehiculo.getOfertas();
                int totalOfertas = ofertas.size();
                vehiculo.mostrarInformacion();
                System.out.println("Se han realizado "+ totalOfertas+" ofertas");
                
                
                if (ofertas.isEmpty()) 
                {
                System.out.println("No hay ofertas para este vehículo.");
                }
                else{
                int i = 0;
                while(i>=0 && i<totalOfertas){
                Oferta oferta = ofertas.get(i);
                oferta.mostrarOferta(i);
              
                i = Util.menúAceptarOferta(sc, i, totalOfertas, oferta, nomFileVehiculos, nomFileOfertas, listaVehiculos,
                        listaOfertas, vehiculo.getTipoVehiculo(), vehiculo, oferta);

                    }//while
                }//else       
                

            }else {
            System.out.println("No se encontró un vehículo con la placa especificada.");
        }
           }else {
        System.out.println("Credenciales incorrectas. Intente nuevamente.");}
        }
        
    public static Vendedor ingresoSistema(Scanner sc, String nomFileVendedor)
    {
        System.out.println("Verificación de credenciales");
        System.out.println("Introduzca su correo: ");
        String correo = sc.next();
        System.out.println("Ingrese su clave: ");
        String clave = sc.next();
        ArrayList<Vendedor> vendedores = Vendedor.readFile(nomFileVendedor);
        Vendedor vendedor = Vendedor.searchByCorreo(vendedores, correo);
        
        if(vendedor!= null)
        {
            return vendedor;
        }
        else
            return null; 
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
    
    public void saveFile(String nomFile, ArrayList<Vendedor> vendedores)
    {
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), true)))
        {
            for (Vendedor v:vendedores){
            pw.println(v.getId()+"|"+v.getNombres()+"|"+v.getApellidos()+"|"+v.getOrganizacion()+
                    "|"+v.getCorreoElectronico()+"|"+v.getClave());
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<Vendedor> readFile(String nomFile)
    {
        ArrayList<Vendedor> vendedores = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomFile)))
        {
            while(sc.hasNextLine())
            {
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                Vendedor v = new Vendedor(Integer.parseInt(tokens[0]),tokens[1],tokens[2], tokens[3],tokens[4],
                        tokens[5]);
                vendedores.add(v);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return vendedores;
    }

   public static void fill(ArrayList<Vendedor> vendedores, ArrayList<Vehiculo> vehiculos)
   {
       for(Vendedor vendedor : vendedores)
       {
           for(Vehiculo vehiculo: vehiculos)
           {
               if(vendedor.getId()== vehiculo.getId_vendedor())
               {
                   vendedor.getVehiculos().add(vehiculo);
                   vehiculo.setVendedor(vendedor);
               }
           }
       }
   }
   
    
}
