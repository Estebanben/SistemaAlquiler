package proyecto1BackEnd;

public class Empleado {
    
    //Atributes
    private String Usuario;
    private String Clave;
    private Sede sede;
    //Constructor
    public Empleado(String Usuario, String Clave, Sede sede) {
        this.Usuario = Usuario;
        this.Clave = Clave;
        this.sede = sede;
    }
    //Metodos
    public void ActualizarEstadoCarro(Carro carro, Estado estado){
        carro.CambiarEstado(carro, estado);
        System.out.println("El cambio de estado fue exitoso");
    }
    public void RecibirCarro(Carro carro, Sede sede,Estado estado){
        carro.CambiarEstado(carro, estado);
        carro.CambiarSede(carro,sede);
    }
    public String getUsuario() {
        return Usuario;
    }
    public String getClave() {
        return Clave;
    }
    public Sede getSede() {
        return sede;
    }
    
    
}
