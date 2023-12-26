package proyecto1BackEnd;

import java.time.LocalDateTime;

public class Reserva {
    
    //Atributes
    private Sede SedeRec;
    private Sede SedeEnt;
    private LocalDateTime FechaHoraRec;
    private LocalDateTime FechaHoraEnt;
    private Categoria categoria;
    private long TarifaEstimada;
    private Cliente ClienteReserva;
    //Constructor
    public Reserva(Sede SedeRec, Sede SedeEnt, LocalDateTime FechaHoraRec, LocalDateTime FechaHoraEnt,Categoria categoria, long TarifaEstimada, Cliente ClienteReserva) {
        this.SedeRec = SedeRec;
        this.SedeEnt = SedeEnt;
        this.FechaHoraRec = FechaHoraRec;
        this.FechaHoraEnt = FechaHoraEnt;
        this.categoria = categoria;
        this.TarifaEstimada = TarifaEstimada;
        this.ClienteReserva = ClienteReserva;
    }
    //Metodos
    public Sede getSedeRec() {
        return SedeRec;
    }
    public Sede getSedeEnt() {
        return SedeEnt;
    }
    public LocalDateTime getFechaHoraRec() {
        return FechaHoraRec;
    }
    public LocalDateTime getFechaHoraEnt() {
        return FechaHoraEnt;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public long getTarifaEstimada() {
        return TarifaEstimada;
    }
    public Cliente getClienteReserva() {
        return ClienteReserva;
    }
    
    
}
