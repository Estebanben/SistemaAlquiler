package proyecto1BackEnd;

import java.time.LocalDate;

public class TarjetaCred {
    
    //Atributes
    private String NumeroTarjeta;
    private LocalDate FechaVencCredito;
    //Constructor
    public TarjetaCred(String NumeroTarjeta, LocalDate FechaVencCredito) {
        this.NumeroTarjeta = NumeroTarjeta;
        this.FechaVencCredito = FechaVencCredito;
    }
    //Metodos
    public String getNumeroTarjeta() {
        return NumeroTarjeta;
    }
    public LocalDate getFechaVencCredito() {
        return FechaVencCredito;
    }
    

}
