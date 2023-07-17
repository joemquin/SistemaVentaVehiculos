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
public class Vehiculo {
    private int id;
    private String tipoVehiculo;
    private String placa;
    private String marca;
    private String modelo;
    private String tipoMotor;
    private int anio;
    private double recorrido;
    private String color;
    private String tipoCombustible;
    private double precio;
    private int id_vendedor;
    private Vendedor vendedor;
    private ArrayList<Oferta> ofertas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }
    

    public int getId_vendedor() {
        return id_vendedor;
    }

    public void setId_vendedor(int id_vendedor) {
        this.id_vendedor = id_vendedor;
    }

    
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipoMotor() {
        return tipoMotor;
    }

    public void setTipoMotor(String tipoMotor) {
        this.tipoMotor = tipoMotor;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public double getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(double recorrido) {
        this.recorrido = recorrido;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }
    
    public Vehiculo(int id, int id_vendedor, String tipoVehiculo, String placa, String marca, String modelo, 
            String tipoMotor, int anio, double recorrido,String color, String tipoCombustible, double precio)
    {
        this.id=id;
        this.id_vendedor=id_vendedor;
        this.tipoVehiculo=tipoVehiculo;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipoMotor = tipoMotor;
        this.anio = anio;
        this.recorrido = recorrido;
        this.color = color;
        this.tipoCombustible = tipoCombustible;
        this.precio = precio;
        this.ofertas= new ArrayList<>();
    }
      
    
      
    public static Vehiculo searchByPlaca(ArrayList<Vehiculo> vehiculos, String placa)
    {
        for (Vehiculo v : vehiculos)
        {
            if(v.getPlaca().equals(placa))
            {       
                if(v instanceof Auto)
                {
                    Auto a = (Auto) v;
                    return a;
                }
                else if (v instanceof Camioneta)
                {
                    Camioneta c = (Camioneta) v;
                    return c;
                }
                else 
                    return v;
            }
        }
        /*Vehiculo v2 = new Vehiculo("2", "1", "c", "q","2", 2.2, "z", "x", 1.2, 1);
        return v2;*/
        return null;
    }    
    
        public static Vehiculo searchByID(ArrayList<Vehiculo> vehiculos, int id)
    {
        for (Vehiculo v : vehiculos)
        {
            if(v.getId()==id)
            {       
                if(v instanceof Auto)
                {
                    Auto a = (Auto) v;
                    return a;
                }
                else if (v instanceof Camioneta)
                {
                    Camioneta c = (Camioneta) v;
                    return c;
                }
                else 
                    return v;
            }
        }
        /*Vehiculo v2 = new Vehiculo("2", "1", "c", "q","2", 2.2, "z", "x", 1.2, 1);
        return v2;*/
        return null;
    }    
    
    public static ArrayList<Vehiculo> readFile(String nomFile)
    {
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomFile)))
        {
            while(sc.hasNextLine())
            {
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                Vehiculo v = new Vehiculo(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),tokens[2],tokens[3],
                        tokens[4], tokens[5],tokens[6], Integer.parseInt(tokens[7]),Double.parseDouble(tokens[8]), tokens[9], tokens[10],
                        Double.parseDouble(tokens[11]));
                vehiculos.add(v);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        return vehiculos;
    }
    
    public static Vehiculo crearVehiculo(String[] tokens)
    {
        String tipoVehiculo = tokens[1];
        if(tipoVehiculo.equals("auto"))
        {
            Auto auto = new Auto(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),tokens[2],tokens[3],
                        tokens[4], tokens[5],tokens[6], Integer.parseInt(tokens[7]),Double.parseDouble(tokens[8]), tokens[9], tokens[10],
                        Double.parseDouble(tokens[11]), tokens[12], tokens[13]);
            return auto;
        
        }
        else if(tipoVehiculo.equals("camioneta"))
        {    
            Camioneta camioneta = new Camioneta(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),tokens[2],tokens[3],
                        tokens[4], tokens[5],tokens[6], Integer.parseInt(tokens[7]),Double.parseDouble(tokens[8]), tokens[9], tokens[10],
                        Double.parseDouble(tokens[11]), tokens[12], tokens[13], tokens[14]);
            return camioneta;
        }
        else if(tipoVehiculo.equals("moto"))
        {
            Vehiculo vehiculo = new Vehiculo(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),tokens[2],
                    tokens[3],tokens[4], tokens[5],tokens[6], Integer.parseInt(tokens[7]),Double.parseDouble(tokens[8]), 
                    tokens[9], tokens[10],Double.parseDouble(tokens[11]));
            return vehiculo;
        }
        return null;
    }
    
    
    
     public void saveFile(String nomFile, String tipoVehiculo)
    {
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile),true)))
        {
            
            pw.println(this.id +"|"+this.id_vendedor+"|"+tipoVehiculo+"|"+this.placa+"|"+this.marca+"|"+this.modelo+"|"+this.tipoMotor+"|"+
                    this.anio+"|"+this.recorrido+"|"+this.color+"|"+this.tipoCombustible+"|"+this.precio);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }   
    }
     
    public static void saveFile(String nomFile, String tipoVehiculo, ArrayList<Vehiculo> vehiculos)
    {
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile))))
        {
            int identificador = Util.nextID(nomFile);
            for(Vehiculo v: vehiculos){
                if(v instanceof Auto)
                {
                    
                    Auto auto = (Auto) v;
                    pw.println(identificador +"|"+v.id_vendedor+"|"+v.getTipoVehiculo()+"|"+v.placa+"|"+v.marca+"|"+v.modelo+"|"+v.tipoMotor+"|"+
                    v.anio+"|"+v.recorrido+"|"+v.color+"|"+v.tipoCombustible+"|"+v.precio +"|" +auto.getVidrios()+"|"+
                    auto.getTransmision());
                }
                else if(v instanceof Camioneta)
                {
                    Camioneta camioneta = (Camioneta) v;
                    pw.println(identificador +"|"+v.id_vendedor+"|"+v.getTipoVehiculo()+"|"+v.placa+"|"+v.marca+"|"+v.modelo+"|"+v.tipoMotor+"|"+
                    v.anio+"|"+v.recorrido+"|"+v.color+"|"+v.tipoCombustible+"|"+v.precio +"|" +camioneta.getVidrios()+"|"+
                    camioneta.getTransmision()+"|"+camioneta.getTraccion());
                }
                else{
            pw.println(identificador +"|"+v.id_vendedor+"|"+v.getTipoVehiculo()+"|"+v.placa+"|"+v.marca+"|"+v.modelo+"|"+v.tipoMotor+"|"+
                    v.anio+"|"+v.recorrido+"|"+v.color+"|"+v.tipoCombustible+"|"+v.precio);}
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }   
    }
          
    public static void nextVehiculo(Scanner sc, String nomFileVehiculo, int id_vendedor, String tipoVehiculo)
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
        int id = Util.nextID(nomFileVehiculo);
        
        
        Vehiculo vehiculo = new Vehiculo(id,id_vendedor,tipoVehiculo, placa, marca, modelo, tipoMotor, anio, recorrido,
                        color, tipoCombustible, precio);
        vehiculo.saveFile(nomFileVehiculo, tipoVehiculo);
        
        
        
               
    }
    

    @Override
    public String toString()
    {             
        return ("Vehiculo( "+"id: "+this.id+" Tipo de vehículo: "+ this.tipoVehiculo+" Placa: "+this.placa+
                " Marca: "+this.marca+" Modelo: "+this.modelo+" Tipo de motor: "+this.tipoMotor+" Año: "
                + this.anio + " Recorrido: "+ this.recorrido + " Color: "+ this.color+ " Tipo de combustible: "
                + this.tipoCombustible + " Precio: "+ this.precio+")");
    }
    
    public void mostrarInformacion()
    {
        System.out.println(this.marca+" " + this.modelo+ " Precio: " +this.precio);
    }
    
    
}
