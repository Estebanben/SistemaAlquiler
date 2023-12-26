package proyecto1BackEnd;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Sede {
    
    //Atributes
    private String Nombre;
    private String Direccion;
    private LocalTime HoraApertura;
    private LocalTime HoraCierre;
    private HashMap<String,Reserva> Reservas;
    private HashMap<String,Empleado> Empleados;
    private HashMap<String,Carro> Carros;
    private AdministradorLocal AdminLocal;
    private HashMap<String,Alquiler> Alquileres;
    
    //Constructor
    public Sede(String Nombre, String Direccion, LocalTime HoraApertura, LocalTime HoraCierre) {
        this.Nombre = Nombre;
        this.Direccion = Direccion;
        this.HoraApertura = HoraApertura;
        this.HoraCierre = HoraCierre;
        this.Empleados = new HashMap<>();
        this.Carros = new HashMap<>();
    }
    //Metodos
    public Boolean VerificarDisponibilidad(Categoria categoria, LocalDateTime FechaI, LocalDateTime FechaF){
        HashMap<String,Carro> mapaCarros = Carros;
        for (Map.Entry<String,Carro> entry:mapaCarros.entrySet()){
            Carro carro = entry.getValue();
            if (carro.getCategoria() == categoria && carro.getEstado().getNombre().equals("Disponible")){
                Estado estadoReserva = new Estado("Reservado");
                estadoReserva.setFechaInicio(FechaI);
                estadoReserva.setFechaDisponibilidad(FechaF);
                carro.CambiarEstado(carro, estadoReserva);
                carro.setEstado(estadoReserva);
                //Modifies the car state in the file
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        return "Sede{" +
                "nombre='" + Nombre + '\'' +
                ", direccion='" + Direccion + '\'' +
                ", horaApertura=" + HoraApertura +
                ", horaCierre=" + HoraCierre +
                '}';
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    public String getDireccion() {
        return Direccion;
    }
    public void setDireccion(String direccion) {
        Direccion = direccion;
    }
    public LocalTime getHoraApertura() {
        return HoraApertura;
    }
    public void setHoraApertura(LocalTime horaApertura) {
        HoraApertura = horaApertura;
    }
    public LocalTime getHoraCierre() {
        return HoraCierre;
    }
    public void setHoraCierre(LocalTime horaCierre) {
        HoraCierre = horaCierre;
    }
    public HashMap<String, Reserva> getReservas() {
        return Reservas;
    }
    public void setReservas(HashMap<String, Reserva> reservas) {
        Reservas = reservas;
    }
    public HashMap<String, Empleado> getEmpleados() {
        return Empleados;
    }
    public void setEmpleados(HashMap<String, Empleado> empleados) {
        Empleados = empleados;
    }
    public HashMap<String, Carro> getCarros() {
        return Carros;
    }
    public void setCarros(HashMap<String, Carro> carros) {
        Carros = carros;
    }
    public AdministradorLocal getAdminLocal() {
        return AdminLocal;
    }
    public void setAdminLocal(AdministradorLocal adminLocal) {
        AdminLocal = adminLocal;
    }
    public HashMap<String, Alquiler> getAlquileres() {
        return Alquileres;
    }
    public void setAlquileres(HashMap<String, Alquiler> alquileres) {
        Alquileres = alquileres;
    }

}
