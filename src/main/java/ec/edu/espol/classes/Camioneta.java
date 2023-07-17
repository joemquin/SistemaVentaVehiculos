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
public class Camioneta extends Auto{
    private String traccion;

    public Camioneta(int id, int id_vendedor,String tipoVehiculo, String placa, String marca, String modelo,
            String tipoMotor, int anio, double recorrido, String color, String tipoCombustible, double precio,
            String vidrios, String transmision, 
            String traccion) 
    {
        super(id, id_vendedor, tipoVehiculo, placa, marca, modelo, tipoMotor, anio,
                recorrido, color, tipoCombustible, precio,vidrios, transmision);
        this.traccion = traccion;
    }

    public String getTraccion() {
        return traccion;
    }

    public void setTraccion(String traccion) {
        this.traccion = traccion;
    }
    
    
    
    @Override
         public void saveFile(String nomFile, String tipoVehiculo)
    {   
        
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), true)))
        {
            pw.println(super.getId()+"|"+super.getId_vendedor()+"|"+tipoVehiculo+"|"+super.getPlaca()+"|"+super.getMarca()+"|"+super.getModelo()+"|"+
                    super.getTipoMotor()+"|"+super.getAnio()+"|"+super.getRecorrido()+"|"+super.getColor()+"|"+
                    super.getTipoCombustible()+"|"+super.getPrecio()+"|"+super.getVidrios()+"|"+
                    super.getTransmision()+"|"+this.traccion);
         
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
  public static void nextCamioneta(Scanner sc, String nomFileVehiculo, int id_vendedor, String tipoVehiculo)
    {
        System.out.println("\nRegistro de nuevo vehiculo");
        System.out.println("Placa: ");
        String placa= sc.next();
        System.out.println("Marca: ");
        String marca = sc.next();
        System.out.println("Modelo: ");
        String modelo = sc.next();
        System.out.println("Tipo del motor: ");
        String tipoMotor= sc.next();
        System.out.println("Año del vehiculo: ");
        int anio = sc.nextInt();
        System.out.println("Recorrido en km del vehiculo: ");
        double recorrido = sc.nextDouble();
        System.out.println("Color: ");
        String color = sc.next();
        System.out.println("Tipo de combustible que usa: ");
        String tipoCombustible = sc.next();
        System.out.println("Precio: ");
        double precio = sc.nextDouble();
        System.out.println("Vidrios: ");
        String vidrios = sc.next();
        System.out.println("Transmisión: ");
        String transmision = sc.next();
        System.out.println("Traccion: ");
        String traccion = sc.next();
        int id = Util.nextID(nomFileVehiculo);
        Camioneta camioneta = new Camioneta(id,id_vendedor,tipoVehiculo, placa, marca, modelo, tipoMotor,
                            anio, recorrido, color, tipoCombustible, precio, vidrios, transmision,traccion);
        camioneta.saveFile(nomFileVehiculo, tipoVehiculo);
        

      
    }
    @Override
    public String toString()
    {             
        return ("Vehiculo( "+"id: "+super.getId()+" Tipo de vehículo: "+ super.getTipoVehiculo()+" Placa: "+
               super.getPlaca()+" Marca: "+super.getMarca()+" Modelo: "+super.getModelo()+" Tipo de motor: "+super.getTipoMotor()
                +" Año: "+super.getAnio() + " Recorrido: "+super.getRecorrido()+ " Color: "+super.getColor()+ 
                " Tipo de combustible: "+ super.getTipoCombustible() + " Precio: "+ super.getPrecio() + " Vidrios: "+
                super.getVidrios()+" Transmision: "+super.getTransmision()+" Traccion: "+traccion+")");
    }
}