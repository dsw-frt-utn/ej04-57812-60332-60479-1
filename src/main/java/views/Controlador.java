package views;

import data.Persistencia;
import domain.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

public class Controlador {
    
    public static ArrayList<VehiculoViewModel> getVehiculos(){
        ArrayList<VehiculoViewModel> vehiculos = new ArrayList<>();
        for(Vehiculo vehiculo : Persistencia.getVehiculos()) {
            vehiculos.add(new VehiculoViewModel(vehiculo));
        }
        return vehiculos;
    }   
    
    public static void agregarVehiculoCombustible(String patente, String nombreMarca, String paisMarca, String modelo, int anio, double capCarga, Sucursal sucursal, double kmLitro, double litrosExtra) {
    
        Marca marca = new Marca(nombreMarca, paisMarca);
        Vehiculo vehiculo = new VehiculoCombustible(patente, marca, modelo, anio, capCarga, sucursal, kmLitro, litrosExtra);
        Persistencia.agregarVehiculo(vehiculo);
    }

    public static void agregarVehiculoElectrico(String patente, String nombreMarca, String paisMarca, String modelo, int anio, double capCarga, Sucursal sucursal, double kwhBase) {

        Marca marca = new Marca(nombreMarca, paisMarca);
        Vehiculo vehiculo = new VehiculoElectrico(patente, marca, modelo, anio, capCarga, sucursal, kwhBase);
        Persistencia.agregarVehiculo(vehiculo);
    }


    public static double[] calcularConsumos(Map<String, Double> vehiculos){
        double consumoElectricos = 0;
        double consumoCombustible= 0;
        for(Map.Entry<String, Double> entry : vehiculos.entrySet()){
           double consumo = 0;
           Optional<Vehiculo> vehiculo = Persistencia.getVehiculo(entry.getKey());
           if(vehiculo.isPresent()){
               consumo = vehiculo.get().calcularConsumo(entry.getValue());
               consumoElectricos += vehiculo.get().esDe(VehiculoTipo.ELECTRICO) ? consumo : 0;
               consumoCombustible += vehiculo.get().esDe(VehiculoTipo.COMBUSTIBLE) ? consumo : 0;
           }
        }
        return new double[] {consumoElectricos, consumoCombustible};
    }
}

