package proyecto1BackEnd;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class Empresa {

    //Atributes
    private HashMap<String,Cliente> Clientes;
    private Administrador Admin;
    private HashMap<String,Empleado> Empleados;
    private HashMap<String,Sede> Sedes;
    private HashMap<String,Carro> Inventario;
    private ArrayList<Alquiler> Alquileres;
    private HashMap<String,Seguro> Seguros;
    private HashMap<String,Categoria> Categorias;
    private HashMap<String,AdministradorLocal> AdminsLocales;
    private HashMap<String,ConductorAdicional> ConductoresAdicionales;
    private HashMap<String,Reserva> Reservas;
    //Constructor
    public Empresa(Administrador Admin) {
        this.Admin = Admin;
        this.Clientes = new HashMap<>();
        this.Empleados = new HashMap<>();
        this.Sedes = new HashMap<>();
        this.Inventario = new HashMap<>();
        this.Alquileres = new ArrayList<>();
        this.ConductoresAdicionales = new HashMap<>();
    }
    //Metodos
    public String RegistrarCliente(String Nombre, String Telefono, LocalDate FechaNacimiento, String Nacionalidad,String NumeroDoc, TarjetaCred Tarjeta, Licencia licencia, String Usuario , String Clave,Empresa empresa,String fileName){

        HashMap<String,Cliente> clientesEmpresa = empresa.getClientes();
        if(clientesEmpresa.get(Usuario)==null){
            Cliente cliente = new Cliente(Nombre, Telefono, FechaNacimiento, Nacionalidad, NumeroDoc, Usuario, Clave, licencia, Tarjeta);
            try (FileWriter fw = new FileWriter(fileName, true);PrintWriter pw = new PrintWriter(fw)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    pw.print( "\n" + Nombre + "," + Telefono + "," + FechaNacimiento.format(formatter) + "," + Nacionalidad + "," + NumeroDoc + "," + Usuario + "," + Clave + "," + licencia.getNumeroLicencia() + "," + licencia.getPaisExpLicencia() + "," + licencia.getFechaVencLicencia().format(formatter) + "," + Tarjeta.getNumeroTarjeta() + "," + Tarjeta.getFechaVencCredito().format(formatter));
                    clientesEmpresa.put(Usuario, cliente);
                    empresa.setClientes(clientesEmpresa);
                }
                catch (IOException e) {
                    return "An error occurred: " + e.getMessage();
                }
        }
        else{return("Ya existe un cliente registrado con el usuario dado");}
        return("Cliente creado exitosamente");
    }
    public Carro BuscarCarroPlaca(String Placa){
        return Inventario.get(Placa);
    }
    public Categoria BuscarCategoriaNombre(String Nombre){
        return Categorias.get(Nombre);
    }
    public Sede BuscarSedeNombre(String Nombre){
        return Sedes.get(Nombre);
    }
    public Empleado BuscarEmpleadoUsuario(String Usuario){
        return Empleados.get(Usuario);
    }
    public Cliente BuscarClienteUsuario(String Usuario){
        return Clientes.get(Usuario);
    }
    public AdministradorLocal BuscarAdminLocalUsuario (String Usuario){
        return AdminsLocales.get(Usuario);
    }
    public Seguro BuscarSeguroNombre(String Nombre){
        return Seguros.get(Nombre);
    }
    public HashMap<String, Cliente> getClientes() {
        return Clientes;
    }
    public void setClientes(HashMap<String, Cliente> clientes) {
        Clientes = clientes;
    }
    public Administrador getAdmin() {
        return Admin;
    }
    public void setAdmin(Administrador admin) {
        Admin = admin;
    }
    public HashMap<String, Empleado> getEmpleados() {
        return Empleados;
    }
    public void setEmpleados(HashMap<String, Empleado> empleados) {
        Empleados = empleados;
    }
    public HashMap<String, Sede> getSedes() {
        return Sedes;
    }
    public void setSedes(HashMap<String, Sede> sedes) {
        Sedes = sedes;
    }
    public HashMap<String, Carro> getInventario() {
        return Inventario;
    }
    public void setInventario(HashMap<String, Carro> inventario) {
        Inventario = inventario;
    }
    public ArrayList<Alquiler> getAlquileres() {
        return Alquileres;
    }
    public void setAlquileres(ArrayList<Alquiler> alquileres) {
        Alquileres = alquileres;
    }
    public HashMap<String, Seguro> getSeguros() {
        return Seguros;
    }
    public void setSeguros(HashMap<String, Seguro> seguros) {
        Seguros = seguros;
    }
    public HashMap<String, Categoria> getCategorias() {
        return Categorias;
    }
    public void setCategorias(HashMap<String, Categoria> categorias) {
        Categorias = categorias;
    }
    public HashMap<String, AdministradorLocal> getAdminsLocales() {
        return AdminsLocales;
    }
    public void setAdminsLocales(HashMap<String, AdministradorLocal> adminsLocales) {
        AdminsLocales = adminsLocales;
    }
    public HashMap<String, ConductorAdicional> getConductoresAdicionales() {
        return ConductoresAdicionales;
    }
    public void setConductoresAdicionales(HashMap<String, ConductorAdicional> conductoresAdicionales) {
        ConductoresAdicionales = conductoresAdicionales;
    }
    public HashMap<String, Reserva> getReservas() {
        return Reservas;
    }
    public void setReservas(HashMap<String, Reserva> reservas) {
        Reservas = reservas;
    }
    
    
    
   
    
    
}