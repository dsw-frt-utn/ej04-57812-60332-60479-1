package app;

import data.Persistencia;
import java.util.InvalidPropertiesFormatException;
import views.*;

public class Program {
    public static void main(String[] args) throws IllegalArgumentException, InvalidPropertiesFormatException {
        Persistencia.inicializar();
        
        ListarVehiculosView viewListar = new ListarVehiculosView();
        viewListar.setVisible(true);
        
        AgregarVehiculosView viewAgregar = new AgregarVehiculosView();
        viewAgregar.setVisible(true);
    }
}
