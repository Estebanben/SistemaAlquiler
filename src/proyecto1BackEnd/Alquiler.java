package proyecto1BackEnd;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Alquiler {

    //Atributes
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFinal;
    private Sede sedeEntrega;
    private Sede sedeInicio;
    private Boolean reserva;
    private ArrayList<ConductorAdicional> conductoresAdicionales;
    private Carro carro;
    private long tarifa;
    private Cliente cliente;
    //Constructor
    public Alquiler(LocalDateTime fechaInicio, LocalDateTime fechaFinal, Sede sedeEntrega, Sede sedeInicio, Boolean reserva,ArrayList<ConductorAdicional> conductoresAdicionales, Carro carro, long tarifa, Cliente cliente) {
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.sedeEntrega = sedeEntrega;
        this.sedeInicio = sedeInicio;
        this.reserva = reserva;
        this.conductoresAdicionales = conductoresAdicionales;
        this.carro = carro;
        this.tarifa = tarifa;
        this.cliente = cliente;
    }
    //Metodos
    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }
    public LocalDateTime getFechaFinal() {
        return fechaFinal;
    }
    public Sede getSedeEntrega() {
        return sedeEntrega;
    }
    public Sede getSedeInicio() {
        return sedeInicio;
    }
    public Boolean getReserva() {
        return reserva;
    }
    public ArrayList<ConductorAdicional> getConductoresAdicionales() {
        return conductoresAdicionales;
    }
    public Carro getCarro() {
        return carro;
    }
    public long getTarifa() {
        return tarifa;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public void setFechaFinal(LocalDateTime fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
    public void setSedeEntrega(Sede sedeEntrega) {
        this.sedeEntrega = sedeEntrega;
    }
    public void setSedeInicio(Sede sedeInicio) {
        this.sedeInicio = sedeInicio;
    }
    public void setReserva(Boolean reserva) {
        this.reserva = reserva;
    }
    public void setConductoresAdicionales(ArrayList<ConductorAdicional> conductoresAdicionales) {
        this.conductoresAdicionales = conductoresAdicionales;
    }
    public void setCarro(Carro carro) {
        this.carro = carro;
    }
    public void setTarifa(long tarifa) {
        this.tarifa = tarifa;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
}
