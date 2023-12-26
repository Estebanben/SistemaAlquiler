package proyecto1BackEnd;


public class ReservaEspecial {
    
    //Atributes
    private Carro carro;
    private Sede SedeInicio;
    private Sede SedeFinal;
    //Constructor
    public ReservaEspecial(Carro carro, Sede SedeInicio, Sede SedeFinal) {
        this.carro = carro;
        this.SedeInicio = SedeInicio;
        this.SedeFinal = SedeFinal;
    }
    //Metodos
    public Carro getCarro() {
        return carro;
    }
    public Sede getSedeInicio() {
        return SedeInicio;
    }
    public Sede getSedeFinal() {
        return SedeFinal;
    }
    
}
