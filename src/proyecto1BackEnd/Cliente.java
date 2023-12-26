package proyecto1BackEnd;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Cliente {

    //Atributes
    private String Nombres;
    private String Telefono;
    private LocalDate FechaNacimiento;
    private String Nacionalidad;
    private String NumeroDoc;
    private String Usuario;
    private String Clave;
    private Licencia licencia;
    private TarjetaCred tarjetaCred;
    //Constructor
    public Cliente(String Nombres, String Telefono, LocalDate FechaNacimiento, String Nacionalidad, String NumeroDoc,String Usuario, String Clave, Licencia licencia, TarjetaCred tarjetaCred) {
        this.Nombres = Nombres;
        this.Telefono = Telefono;
        this.FechaNacimiento = FechaNacimiento;
        this.Nacionalidad = Nacionalidad;
        this.NumeroDoc = NumeroDoc;
        this.Usuario = Usuario;
        this.Clave = Clave;
        this.licencia = licencia;
        this.tarjetaCred = tarjetaCred;
    }
    //Metodos
    public String ReservarCarro(Categoria categoriaR,Sede sedeRec, Sede sedeEnt,LocalDateTime FechaHoraRec, LocalDateTime FechaHoraEnt,Cliente cliente,Empresa empresa,String fileName){
        Boolean hayDisponibilidad = sedeRec.VerificarDisponibilidad(categoriaR, FechaHoraRec, FechaHoraEnt);
        if(hayDisponibilidad){
            
            //Create tarifa
            Boolean tempAlta = false;
            Boolean EntregaMismaS = false;
            int MesInicio = FechaHoraRec.getMonthValue();
            HashSet<Integer> MesesTempAlta = new HashSet<>();
            MesesTempAlta.add(12);
            MesesTempAlta.add(1);
            MesesTempAlta.add(2);
            MesesTempAlta.add(3);;
            if (MesesTempAlta.contains(MesInicio)){
                tempAlta = true;
            }
            if (sedeRec == sedeEnt){
                EntregaMismaS = true;
            }
            TarifaAlquiler tarifaEstimada = new TarifaAlquiler(categoriaR, FechaHoraRec, FechaHoraEnt,tempAlta, EntregaMismaS);
            long valorTarifaEstimada = tarifaEstimada.CalcularTarifa(tarifaEstimada);
            //Checks if the given dates are valid
            if (FechaHoraEnt.isAfter(FechaHoraRec)){
                Reserva reserva = new Reserva(sedeRec, sedeEnt, FechaHoraRec, FechaHoraEnt, categoriaR, valorTarifaEstimada, cliente);
                HashMap<String, Reserva> reservasEmpresa = empresa.getReservas();
                reservasEmpresa.put(cliente.getUsuario(), reserva);
                empresa.setReservas(reservasEmpresa);
                try (FileWriter fw = new FileWriter(fileName, true);PrintWriter pw = new PrintWriter(fw)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    String FechaHoraRecString = FechaHoraRec.format(formatter);
                    String FechaHoraEntString = FechaHoraEnt.format(formatter);
                    pw.print( "\n" + sedeRec.getNombre() + "," + sedeEnt.getNombre() + "," + FechaHoraRecString + "," + FechaHoraEntString + "," + categoriaR.getNombre() + "," +String.valueOf(valorTarifaEstimada) + "," + cliente.getUsuario());
                }
                catch (IOException e) {
                    return("An error occurred: " + e.getMessage());
                }
            return("Su reserva fue creada de manera exitosa");
            }
            else{return("Algunos de los datos de la reserva son erroneos, por favor verifique y vuelva a intentar");}
            
        }
        else{return("No hay disponibilidad para la categoria dada");}
    }
    public String AlquilarCarroSinR(Carro carroA ,Sede sedeRec, Sede sedeEnt,LocalDateTime FechaHoraRec, LocalDateTime FechaHoraEnt,Cliente cliente,Boolean Reserva,ArrayList<ConductorAdicional> conductoresAd,ArrayList<Seguro> segurosAlquiler,Empresa empresa, String fileName ){
        String NombreEstado = carroA.getEstado().getNombre();
        if (!Reserva){
        if (NombreEstado.equals("Disponible")){
            Estado estadoCarro = new Estado("Alquilado");
            estadoCarro.setFechaInicio(FechaHoraRec);
            estadoCarro.setFechaDisponibilidad(FechaHoraEnt);
            Categoria categoriaCarro = carroA.getCategoria();
            Boolean tempAlta = false;
            Boolean EntregaMismaS = false;
            int MesInicio = FechaHoraRec.getMonthValue();
            HashSet<Integer> MesesTempAlta = new HashSet<>();
            MesesTempAlta.add(12);
            MesesTempAlta.add(1);
            MesesTempAlta.add(2);
            MesesTempAlta.add(3);;
            if (MesesTempAlta.contains(MesInicio)){
                tempAlta = true;
            }
            if (sedeRec == sedeEnt){
                EntregaMismaS = true;
            }
            TarifaAlquiler tarifaAlquiler = new TarifaAlquiler(categoriaCarro, FechaHoraRec, FechaHoraEnt,tempAlta, EntregaMismaS,conductoresAd,segurosAlquiler);
            long valorTarifaAlquiler = tarifaAlquiler.CalcularTarifa(tarifaAlquiler);
            if (FechaHoraEnt.isAfter(FechaHoraRec)){
                Alquiler alquiler = new Alquiler(FechaHoraRec, FechaHoraEnt, sedeEnt, sedeRec, Reserva, conductoresAd, carroA,valorTarifaAlquiler,cliente);
                ArrayList<Alquiler> alquileresEmpresa = empresa.getAlquileres();
                alquileresEmpresa.add(alquiler);
                empresa.setAlquileres(alquileresEmpresa);
                try (FileWriter fw = new FileWriter(fileName, true);PrintWriter pw = new PrintWriter(fw)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    String FechaHoraRecString = FechaHoraRec.format(formatter);
                    String FechaHoraEntString = FechaHoraEnt.format(formatter);
                    String StringToPrint = ("\n" + FechaHoraRecString + "," + FechaHoraEntString + "," + sedeRec.getNombre() + "," + sedeEnt.getNombre() + "," + Boolean.toString(Reserva));
                    int index = 0;
                    for (ConductorAdicional cond:conductoresAd){
                        index += 1;
                        if (index == 1){StringToPrint = (StringToPrint + "," + cond.getLicencia().getNumeroLicencia() + ";");}
                        else if (index == conductoresAd.size()){StringToPrint = (StringToPrint + cond.getLicencia().getNumeroLicencia());}
                        else {StringToPrint = (StringToPrint + cond.getLicencia().getNumeroLicencia() + ";");}
                    }
                    if (conductoresAd.size() == 0){
                        StringToPrint = StringToPrint + ",";
                    }
                    StringToPrint = (StringToPrint + "," + carroA.getPlaca() + "," + String.valueOf(valorTarifaAlquiler) + "," + cliente.getUsuario());
                    pw.print(StringToPrint );
                }
                catch (IOException e) {
                    return("An error occurred: " + e.getMessage());
                }
            carroA.CambiarEstado(carroA, estadoCarro);
            carroA.setEstado(estadoCarro);
            return("Su alquiler fue creado de manera exitosa");
            }
            else{return("Algunos de los datos del alquiler son erroneos, por favor verifique y vuelva a intentar");}
            
        }
        else {return("El carro seleccionado no se encuentra disponible");}
    }
    else{
        Estado estadoCarro = new Estado("Alquilado");
            estadoCarro.setFechaInicio(FechaHoraRec);
            estadoCarro.setFechaDisponibilidad(FechaHoraEnt);
            Categoria categoriaCarro = carroA.getCategoria();
            Boolean tempAlta = false;
            Boolean EntregaMismaS = false;
            int MesInicio = FechaHoraRec.getMonthValue();
            HashSet<Integer> MesesTempAlta = new HashSet<>();
            MesesTempAlta.add(12);
            MesesTempAlta.add(1);
            MesesTempAlta.add(2);
            MesesTempAlta.add(3);;
            if (MesesTempAlta.contains(MesInicio)){
                tempAlta = true;
            }
            if (sedeRec == sedeEnt){
                EntregaMismaS = true;
            }
            TarifaAlquiler tarifaAlquiler = new TarifaAlquiler(categoriaCarro, FechaHoraRec, FechaHoraEnt,tempAlta, EntregaMismaS,conductoresAd,segurosAlquiler);
            long valorTarifaAlquiler = tarifaAlquiler.CalcularTarifa(tarifaAlquiler);
            if (FechaHoraEnt.isAfter(FechaHoraRec)){
                Alquiler alquiler = new Alquiler(FechaHoraRec, FechaHoraEnt, sedeEnt, sedeRec, Reserva, conductoresAd, carroA,valorTarifaAlquiler,cliente);
                ArrayList<Alquiler> alquileresEmpresa = empresa.getAlquileres();
                alquileresEmpresa.add(alquiler);
                empresa.setAlquileres(alquileresEmpresa);
                try (FileWriter fw = new FileWriter(fileName, true);PrintWriter pw = new PrintWriter(fw)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    String FechaHoraRecString = FechaHoraRec.format(formatter);
                    String FechaHoraEntString = FechaHoraEnt.format(formatter);
                    String StringToPrint = ("\n" + FechaHoraRecString + "," + FechaHoraEntString + "," + sedeRec.getNombre() + "," + sedeEnt.getNombre() + "," + Boolean.toString(Reserva));
                    int index = 0;
                    for (ConductorAdicional cond:conductoresAd){
                        index += 1;
                        if (index == 1){StringToPrint = (StringToPrint + "," + cond.getLicencia().getNumeroLicencia() + ";");}
                        else if (index == conductoresAd.size()){StringToPrint = (StringToPrint + cond.getLicencia().getNumeroLicencia());}
                        else {StringToPrint = (StringToPrint + cond.getLicencia().getNumeroLicencia() + ";");}
                    }
                    if (conductoresAd.size() == 0){
                        StringToPrint = StringToPrint + ",";
                    }
                    StringToPrint = (StringToPrint + "," + carroA.getPlaca() + "," + String.valueOf(valorTarifaAlquiler) + "," + cliente.getUsuario());
                    pw.print(StringToPrint );
                }
                catch (IOException e) {
                    return("An error occurred: " + e.getMessage());
                }
            carroA.CambiarEstado(carroA, estadoCarro);
            carroA.setEstado(estadoCarro);
            return("Su alquiler fue creado de manera exitosa");
            }
            else{return("Algunos de los datos del alquiler son erroneos, por favor verifique y vuelva a intentar");}
            

    }
    }
    public Carro RetornarCarroAlquiler(Empresa empresa, Cliente cliente){
        HashMap<String,Reserva> reservasEmpresa = empresa.getReservas();
        Reserva reservaCliente = reservasEmpresa.get(cliente.getUsuario());
        if (reservaCliente == null){return null;}
        else{
            LocalDateTime fechaHoraRecReserva = reservaCliente.getFechaHoraRec();
            LocalDateTime fechaHoraEntReserva = reservaCliente.getFechaHoraRec();
            HashMap<String,Carro> carrosEmpresa = empresa.getInventario();

            for (Carro carro:carrosEmpresa.values()){

                String NombreEstado = carro.getEstado().getNombre();
                if (NombreEstado.equals("Reservado")){
                    if (fechaHoraRecReserva.equals(carro.getEstado().getFechaInicio()) ||fechaHoraEntReserva.equals(carro.getEstado().getFechaDisponibilidad()) ){
                        return carro;
                    }
                }
            }
        }
        return null;

    }
    public String ConsultarInfoSede(Sede sede){
        return "x";
    }
    public String getNombres() {
        return Nombres;
    }
    public String getTelefono() {
        return Telefono;
    }
    public LocalDate getFechaNacimiento() {
        return FechaNacimiento;
    }
    public String getNacionalidad() {
        return Nacionalidad;
    }
    public String getNumeroDoc() {
        return NumeroDoc;
    }
    public String getUsuario() {
        return Usuario;
    }
    public String getClave() {
        return Clave;
    }
    public Licencia getLicencia() {
        return licencia;
    }
    public TarjetaCred getTarjetaCred() {
        return tarjetaCred;
    }
    @Override
    public String toString() {
        return "Sede{" +
                "nombres='" + Nombres + '\'' +
                ", Telefono='" + Telefono + '\'' +
                ", FechaNacimiento=" + FechaNacimiento +
                ", Nacionalidad=" + Nacionalidad +
                ", NumeroDoc=" + NumeroDoc +
                ", Usuario=" + Usuario +
                ", Clave=" + Clave +
                ", Licencia=" + licencia +
                ", tarjetaCred=" + tarjetaCred +
                '}';
    }
    
}
