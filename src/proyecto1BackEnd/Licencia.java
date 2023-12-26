package proyecto1BackEnd;

import java.time.LocalDate;

public class Licencia {
    
    //Atributes
    private String NumeroLicencia;
    private String PaisExpLicencia;
    private LocalDate FechaVencLicencia;
    //Constructor
    public Licencia(String NumeroLicencia, String PaisExpLicencia, LocalDate FechaVencLicencia) {
        this.NumeroLicencia = NumeroLicencia;
        this.PaisExpLicencia = PaisExpLicencia;
        this.FechaVencLicencia = FechaVencLicencia;
    }
    //Metodos
    public String getNumeroLicencia() {
        return NumeroLicencia;
    }
    public String getPaisExpLicencia() {
        return PaisExpLicencia;
    }
    public LocalDate getFechaVencLicencia() {
        return FechaVencLicencia;
    }
    
}
