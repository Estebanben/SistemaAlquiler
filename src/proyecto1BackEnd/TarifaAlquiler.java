package proyecto1BackEnd;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class TarifaAlquiler {

    //Atributes
    private Categoria CategoriaCarro;
    private LocalDateTime FechaInicio;
    private LocalDateTime FechaFinal;
    private Boolean TempAlta;
    private Boolean EntregaMismaSede;
    private ArrayList<ConductorAdicional> ConductoresAdicionales;
    private ArrayList<Seguro> Seguros;
    //Constructor
    public TarifaAlquiler(Categoria CategoriaCarro, LocalDateTime FechaInicio, LocalDateTime FechaFinal, Boolean TempAlta,Boolean EntregaMismaSede, ArrayList<ConductorAdicional> ConductoresAdicionales, ArrayList<Seguro> Seguros) {
        this.CategoriaCarro = CategoriaCarro;
        this.FechaInicio = FechaInicio;
        this.FechaFinal = FechaFinal;
        this.TempAlta = TempAlta;
        this.EntregaMismaSede = EntregaMismaSede;
        this.ConductoresAdicionales = ConductoresAdicionales;
        this.Seguros = Seguros;
    }
    public TarifaAlquiler(Categoria CategoriaCarro, LocalDateTime FechaInicio, LocalDateTime FechaFinal, Boolean TempAlta,Boolean EntregaMismaSede){
        this.CategoriaCarro = CategoriaCarro;
        this.FechaInicio = FechaInicio;
        this.FechaFinal = FechaFinal;
        this.TempAlta = TempAlta;
        this.EntregaMismaSede = EntregaMismaSede;
        this.ConductoresAdicionales = new ArrayList<>();
        this.Seguros = new ArrayList<>();

    }
    //Metodos
    public long CalcularTarifa(TarifaAlquiler Tarifa){
        Categoria categoria = Tarifa.getCategoriaCarro();
        LocalDateTime FechaI = Tarifa.getFechaInicio();
        LocalDateTime FechaF = Tarifa.getFechaFinal();
        Boolean tempAlta = Tarifa.getTempAlta();
        Boolean entregaMismaSede = Tarifa.getEntregaMismaSede();
        ArrayList<ConductorAdicional> ConductoresAd = Tarifa.getConductoresAdicionales();
        ArrayList<Seguro> seguros = Tarifa.getSeguros();
        long TarifaFinal = 0;
        int entregaMismas = 1;

        if (entregaMismaSede){entregaMismas = 0;}
        int numConductoresAd = ConductoresAd.size();
        int precioSeguros = 0;
        for (Seguro seguro:seguros){
            int precioSeguro = seguro.getPrecio();
            precioSeguros = precioSeguro + precioSeguros;
        }
        long dias = ChronoUnit.DAYS.between(FechaI, FechaF);
        if(tempAlta){
            long precioCategoria = categoria.getTarifaTempAlta();
            TarifaFinal = (precioSeguros + precioCategoria*dias) + (numConductoresAd*50000) + (entregaMismas*50000);
        }
        else{
            long precioCategoria = categoria.getTarifaTempBaja();
            TarifaFinal = (precioSeguros + precioCategoria*dias) + (numConductoresAd*50000) + (entregaMismas*50000);
        }
        return TarifaFinal;

    }
    public Categoria getCategoriaCarro() {
        return CategoriaCarro;
    }
    public LocalDateTime getFechaInicio() {
        return FechaInicio;
    }
    public LocalDateTime getFechaFinal() {
        return FechaFinal;
    }
    public Boolean getTempAlta() {
        return TempAlta;
    }
    public Boolean getEntregaMismaSede() {
        return EntregaMismaSede;
    }
    public ArrayList<ConductorAdicional> getConductoresAdicionales() {
        return ConductoresAdicionales;
    }
    public ArrayList<Seguro> getSeguros() {
        return Seguros;
    }
    
}
