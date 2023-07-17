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
public class Oferta {
    private int id;
    private int id_comprador;
    private int id_vehiculo;
    private Comprador comprador;
    private Vehiculo vehiculo;
    private double precioOfertado;

    public Oferta(int id, int id_vehiculo, int id_comprador, double precioOfertado)
    {
        this.id=id;
        this.id_comprador=id_comprador;
        this.id_vehiculo=id_vehiculo;
        this.precioOfertado = precioOfertado; 
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_comprador() {
        return id_comprador;
    }

    public void setId_comprador(int id_comprador) {
        this.id_comprador = id_comprador;
    }

    public int getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(int id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public double getPrecioOfertado() {
        return precioOfertado;
    }

    public void setPrecioOfertado(double precioOfertado) {
        this.precioOfertado = precioOfertado;
    }
    
    
        public void saveFile(String nomFile)
    {
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), true)))
        {
            pw.println(this.id+"|"+this.id_vehiculo+"|"+this.id_comprador+"|"+this.precioOfertado);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
        
     public static void saveFile(String nomFile, ArrayList<Oferta> ofertas)
    {
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile))))
        {   int identificador = Util.nextID(nomFile);
            for(Oferta o : ofertas){
            pw.println(identificador+"|"+o.id_vehiculo+"|"+o.id_comprador+"|"+o.precioOfertado);}
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
        
    public static ArrayList<Oferta> readFile(String nomFile)
    {
        ArrayList<Oferta> ofertas = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomFile)))
        {
            while(sc.hasNextLine())
            {
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                Oferta o = new Oferta(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]),
                Integer.parseInt(tokens[2]),Double.parseDouble(tokens[3]));
                ofertas.add(o);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return ofertas;
    }
    public static void link(ArrayList<Comprador> compradores,ArrayList<Oferta> ofertas,ArrayList<Vehiculo> vehiculos)
    {
        for(Oferta o: ofertas)
        {
            Comprador c = Comprador.searchByID(compradores, o.getId_comprador());
            Vehiculo v = Vehiculo.searchByID(vehiculos, o.getId_vehiculo());
            c.getOfertas().add(o);
            v.getOfertas().add(o);
            o.setComprador(c);
            o.setVehiculo(v);
        }
    }
    
    
    public void mostrarOferta(int index)
    {
        
        System.out.println("Oferta " + (index+1));
        System.out.println("Correo: " + this.getComprador().getCorreoElectronico());
        System.out.println("Precio ofertado: "+ this.getPrecioOfertado());
                    
                   
    }
}
