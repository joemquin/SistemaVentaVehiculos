/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package ec.edu.espol.sistemaventavehiculos;

import ec.edu.espol.Util.Util;
import ec.edu.espol.classes.Comprador;
import ec.edu.espol.classes.Oferta;
import ec.edu.espol.classes.Vehiculo;
import ec.edu.espol.classes.Vendedor;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author PC
 */
public class SistemaVentaVehiculos {

    public static void main(String[] args) {
                Scanner sc = new Scanner(System.in);
        //Vendedor.registrarVendedor(sc, "Vendedores3.txt"); //(1)
        
        
        /*//Registro de Vehiculos
        ArrayList<Vendedor> vendedores = Vendedor.readFile("Vendedores3.txt");
        
        Vendedor v1 = Vendedor.searchByCorreo(vendedores, "joemquin@gmail.com");
        v1.registrarVehiculo(sc, "Vehiculos4.txt", "Vendedores3.txt");*/
    
        
        //ArrayList<Vehiculo> vehiculos = Vehiculo.readFile("Vehiculos3.txt");
        //Vehiculo vh1 = Vehiculo.searchByPlaca(vehiculos, "20042004");
        
        //System.out.println(vh1.getAnio());
        
        
        //Comprador.registrarComprador(sc, "Compradores2.txt");
        
        /* //Registrar oferta
        ArrayList<Vehiculo> vehiculos = Vehiculo.readFile("Vehiculos4.txt");
        ArrayList<Comprador> compradores = Comprador.readFile("Compradores2.txt");
        Comprador c1 = Comprador.searchByCorreo(compradores, "cerdo@gmai.com");
        c1.ofertarVehiculo(sc, vehiculos);*/
         
         /*// ACEPTAR OFERTA 
        ArrayList<Vendedor> vendedores = Vendedor.readFile("Vendedores3.txt");
        ArrayList<Vehiculo> vehiculos = Vehiculo.readFile("Vehiculos4.txt");
        ArrayList<Comprador> compradores = Comprador.readFile("Compradores2.txt");
        ArrayList<Oferta> ofertas = Oferta.readFile("Ofertas4.txt");
        Comprador c1 = Comprador.searchByCorreo(compradores, "joemqui@gmail.com");
        Vehiculo vh1 = Vehiculo.searchByPlaca(vehiculos, "231403");
        */
        
      
        //Oferta.link(compradores, ofertas, vehiculos);
        
        Util.mostrarMenu(sc);
     ;
        
        
        
        
       
        
        
        
        
        
    }
}
