package proyecto1BackEnd;

import java.util.HashMap;

public class Categoria {
    
    //Atributes
    private String Nombre;
    private long TarifaTempAlta;
    private long TarifaTempBaja;
    private HashMap<String,Carro> CarrosCategoria;
    //Constructor
    public Categoria(String Nombre, long TarifaTempAlta, long TarifaTempBaja) {
        this.Nombre = Nombre;
        this.TarifaTempAlta = TarifaTempAlta;
        this.TarifaTempBaja = TarifaTempBaja;
        this.CarrosCategoria = new HashMap<>();
    }
    //Metodos
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    public long getTarifaTempAlta() {
        return TarifaTempAlta;
    }
    public void setTarifaTempAlta(Integer tarifaTempAlta) {
        TarifaTempAlta = tarifaTempAlta;
    }
    public long getTarifaTempBaja() {
        return TarifaTempBaja;
    }
    public void setTarifaTempBaja(Integer tarifaTempBaja) {
        TarifaTempBaja = tarifaTempBaja;
    }
    public HashMap<String, Carro> getCarrosCategoria() {
        return CarrosCategoria;
    }
    public void setCarrosCategoria(HashMap<String, Carro> carrosCategoria) {
        CarrosCategoria = carrosCategoria;
    }
   
    @Override
    public String toString() {
        return "Sede{" +
                "nombre='" + Nombre + '\'' +
                ", TarifaTempAlta='" + String.valueOf(TarifaTempAlta) + '\'' +
                ", TarifaTempBaja='" + String.valueOf(TarifaTempBaja) +
                '}';
    }
}
