package proyecto1BackEnd;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Seguro {
    
    //Atributes
    private String Nombre;
    private String Descripcion;
    private Integer Precio;
    //Constructor
    public Seguro(String Nombre,String Descripcion, Integer Precio) {
        this.Nombre = Nombre;
        this.Descripcion = Descripcion;
        this.Precio = Precio;
    }
    //Metodos
    public String getDescripcion() {
        return Descripcion;
    }
    public Integer getPrecio() {
        return Precio;
    }
    public String getNombre(){
        return Nombre;
    }

    @Override
    public String toString() {
        return "Seguro{" +
                "nombre='" + Nombre + '\'' +
                ", Descripcion='" + Descripcion + '\'' +
                ", Precio=" + Integer.toString(Precio) +
                '}';
    }
    

}
