package proyecto1BackEnd;

import java.time.LocalDateTime;

public class Estado {

    //Atributes
    private String Nombre;
    private LocalDateTime FechaInicio;
    private LocalDateTime FechaDisponibilidad;
    //Constructor
    public Estado(String Nombre) {
        this.Nombre = Nombre;
    }
    //Metodos
    public String getNombre() {
        return Nombre;
    }
    public LocalDateTime getFechaInicio() {
        return FechaInicio;
    }
    public LocalDateTime getFechaDisponibilidad() {
        return FechaDisponibilidad;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    public void setFechaInicio(LocalDateTime fechaInicio) {
        FechaInicio = fechaInicio;
    }
    public void setFechaDisponibilidad(LocalDateTime fechaDisponibilidad) {
        FechaDisponibilidad = fechaDisponibilidad;
    }
    
}
