package proyecto1Consola;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import proyecto1BackEnd.*;

public class App {

    public App(){}

    public String PrepararCrearSeguro (String NombreSeguro,String DescripcionSeguro,String PrecioSeguro,Empresa empresa, Administrador admin,String ArchivoSeguros){
        Integer precioEntero = Integer.parseInt(PrecioSeguro);
        String x = admin.CrearSeguro(NombreSeguro, DescripcionSeguro, precioEntero, ArchivoSeguros, empresa);
        return x;
    }

    public String PrepararCrearCategoria (String NombreCategoria,String TarifaTempAlta,String TarifaTempBaja,Empresa empresa, Administrador admin,String ArchivoCategorias){
        long valorTempAlta = Long.parseLong(TarifaTempAlta);
        long valorTempBaja = Long.parseLong(TarifaTempBaja);
        String x = admin.CrearCategoria(NombreCategoria, valorTempAlta, valorTempBaja, empresa,ArchivoCategorias);
        return x;
    }

    public void PrepararModSede(String NombreSedeMod,String NuevoNombreSede,String NuevaDirSede,String NuevoHorarioap,String NuevoHoracrioci,Empresa empresa,Administrador admin){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        Sede sedeMod = empresa.BuscarSedeNombre(NombreSedeMod);
        LocalTime NuevoHorarioAp = LocalTime.parse(NuevoHorarioap,formatter);
        LocalTime NuevoHorarioCi = LocalTime.parse(NuevoHoracrioci,formatter);
        admin.ModificarSede(sedeMod, NuevoNombreSede, NuevaDirSede, NuevoHorarioAp, NuevoHorarioCi);
    }

    public String PrepararCrearAdminLocal (String UsuarioAdminLocal,String ClaveAdminLocal,String NombreSedeAd,Empresa empresa,String ArchivoAdmins,Administrador admin){
        Sede sedeAdminLocal = empresa.BuscarSedeNombre(NombreSedeAd);
        String x = admin.CrearAdminLocal(UsuarioAdminLocal, ClaveAdminLocal, sedeAdminLocal, empresa, ArchivoAdmins);
        return x;
        }

    public String traductorRegistroEmpleado(String UsuarioEmpleado , String ClaveEmpleado, AdministradorLocal al, Empresa empresa ,String ArchivoEmpleados  ) {

        String x = al.CrearEmpleado(UsuarioEmpleado, ClaveEmpleado, al.getSede(), empresa, ArchivoEmpleados);
        return x;
    	
    	}

    public String PrepararCrearSede (String NombreSedeCrear,String DireccionSede,String HorarioAp,String NombreCi,Empresa empresa,Administrador admin,String ArchivoSedes){
        String x = admin.CrearSede(NombreSedeCrear,DireccionSede,LocalTime.parse(HorarioAp),LocalTime.parse(NombreCi),empresa, ArchivoSedes);
        return x;
    }

    public String PrepararTransladoCarro (String PlacaCarro,String NombreSedeI,String NombreSedeF,Empresa empresa,Administrador admin){

        Carro carroTranslado = empresa.BuscarCarroPlaca(PlacaCarro);
        Sede SedeInicioTranslado = empresa.BuscarSedeNombre(NombreSedeI);
        Sede SedeFinalTranslado = empresa.BuscarSedeNombre(NombreSedeF);
        String x = admin.GenerarReservaEspecial(carroTranslado,SedeInicioTranslado,SedeFinalTranslado);    
        return x;
    }

    public String PrepararDarDeBajaCarro (String placa,Empresa empresa,Administrador admin){

        String x = admin.DarDeBajaCaro(placa, empresa);   
        return x; 
    }

    public String PreparaCompraCarro(String Placa,String Transmision,String Color,String Marca,String Modelo,String Capacidad,String NombreCat,String NombreSede,Empresa empresa,Administrador admin,String ArchivoCarros){

        Estado estadoCarro = new Estado("Disponible");
        Categoria categoriaCarro = empresa.BuscarCategoriaNombre(NombreCat);
        Sede sedeCarro = empresa.BuscarSedeNombre(NombreSede);
        String x = admin.RegistrarCompraCarro(Placa, Transmision, Color, Marca, Modelo, Integer.parseInt(Capacidad), categoriaCarro, estadoCarro, sedeCarro, empresa, ArchivoCarros); 
        return x;   
    }

    public String PrepararEliminacionEmpleado(String UsuarioEmpleadoEliminar,Empresa empresa,AdministradorLocal adminLocal){

        String x = adminLocal.EliminarEmpleado(UsuarioEmpleadoEliminar, empresa, adminLocal.getSede());    
        return x;
    }

    public ArrayList<Object> IniciarSesion(String Usuario,String Clave,String tipo,Empresa empresa){
        Boolean ExisteUsuario = false;
        Boolean CoincideClave = false;
        ArrayList<Object> ArrayListReturn = new ArrayList<>();
        if (tipo.equals("Cliente")){
            Cliente cliente = empresa.BuscarClienteUsuario(Usuario);
            if (cliente != null){ExisteUsuario = true;if (cliente.getClave().equals(Clave)){CoincideClave = true;}}
            ArrayListReturn.add((ExisteUsuario&&CoincideClave));
            ArrayListReturn.add(cliente);
            return ArrayListReturn;
        }
        else if (tipo.equals("AdminLocal")){
            AdministradorLocal adminLocal = empresa.BuscarAdminLocalUsuario(Usuario);
            if (adminLocal != null){ExisteUsuario = true;if(adminLocal.getClave().equals(Clave)){CoincideClave = true;}}
            ArrayListReturn.add((ExisteUsuario&&CoincideClave));
            ArrayListReturn.add(adminLocal);
            return ArrayListReturn;
        }
        else if(tipo.equals("Admin")){
            Administrador admin = empresa.getAdmin();
            String UsuarioAdmin = empresa.getAdmin().getUsuario();
            String ClaveAdmin = empresa.getAdmin().getClave();
            if (Usuario.equals(UsuarioAdmin) && Clave.equals(ClaveAdmin)){
                ExisteUsuario = true;
                CoincideClave = true;
                ArrayListReturn.add((ExisteUsuario&&CoincideClave));
                ArrayListReturn.add(admin);
                return ArrayListReturn;
            }
        }
        else if(tipo.equals("Empleado")){
            Empleado empleado = empresa.BuscarEmpleadoUsuario(Usuario);
            if (empleado != null){ExisteUsuario = true;if(empleado.getClave().equals(Clave)){CoincideClave = true;}}
            ArrayListReturn.add((ExisteUsuario&&CoincideClave));
            ArrayListReturn.add(empleado);
            return ArrayListReturn;
        }
        return ArrayListReturn;
    }

    public Boolean RegistrarNuevoCliente(String Usuario,String Clave,String Nombre,String Telefono,String FechaNacimiento,String Nacionalidad,String NumeroDoc,String NumeroTarjeta, String FechaVEncTarjeta,String NumeroLicencia,String PaisExpLicencia,String FechaVencLicencia,Empresa empresa,String ArchivoClientes){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                        TarjetaCred tarjeta = new TarjetaCred(NumeroTarjeta, LocalDate.parse(FechaVEncTarjeta,formatter));
                        Licencia licencia = new Licencia(NumeroLicencia, PaisExpLicencia, LocalDate.parse(FechaVencLicencia,formatter));
                        String x = empresa.RegistrarCliente(Nombre, Telefono, LocalDate.parse(FechaNacimiento,formatter), Nacionalidad, NumeroDoc, tarjeta, licencia, Usuario, Clave, empresa, ArchivoClientes);
                        if (x.equals("Cliente creado exitosamente")){return true;}
                        return false;
    }

    public Boolean PrepararReserva(String nombreCategoria,String nombreSedeRec,String nombreSedeEnt,String FechaHoraR,String FechaHoraE,Empresa empresa,Cliente cliente,String ArchivoReservas){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Categoria categoriaReserva = empresa.BuscarCategoriaNombre(nombreCategoria);
        Sede sedeRec = empresa.BuscarSedeNombre(nombreSedeRec);
        Sede sedeEnt = empresa.BuscarSedeNombre(nombreSedeEnt);
        LocalDateTime FechaHoraRec = LocalDateTime.parse(FechaHoraR, formatter);
        LocalDateTime FechaHoraEnt = LocalDateTime.parse(FechaHoraE, formatter);
        String x = cliente.ReservarCarro(categoriaReserva, sedeRec, sedeEnt, FechaHoraRec, FechaHoraEnt, cliente, empresa,ArchivoReservas);
        if (x.equals("Su reserva fue creada de manera exitosa")){
            return true;
        }
        return false;
    }

    public Boolean PreparaAlquiler(ArrayList<ConductorAdicional> conductoresAdicionales,String placaCarroAlquiler,String tieneReserva,String nombreSedeRecAlquiler,String nombreSedeEntAlquiler,String FechaHoraRalquiler,String FechaHoraEalquiler,String NombreSeguro,Cliente cliente,Empresa empresa,String ArchivoAlquileres){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
        Carro carroAlquiler = null;
        ArrayList<Seguro> segurosAlquiler = new ArrayList<>();
        HashMap<String,Seguro> segurosEmpresa = empresa.getSeguros();
        if ((segurosEmpresa.get(NombreSeguro)!=null)||(NombreSeguro.equals(""))){
            if(segurosEmpresa.get(NombreSeguro)!=null){
                segurosAlquiler.add(segurosEmpresa.get(NombreSeguro));
            }
            else if(NombreSeguro.equals("")){

            }
        }
        else{
            return false;
        }
    
        Sede sedeRecAlquiler = empresa.BuscarSedeNombre(nombreSedeRecAlquiler);
        Sede sedeEntAlquiler = empresa.BuscarSedeNombre(nombreSedeEntAlquiler);
        LocalDateTime FechaHoraRecAlquiler = LocalDateTime.parse(FechaHoraRalquiler, formatter);
        LocalDateTime FechaHoraEntAlquiler = LocalDateTime.parse(FechaHoraEalquiler, formatter);
        if (tieneReserva.equals("false")){
            carroAlquiler = sedeRecAlquiler.getCarros().get(placaCarroAlquiler);
            String x = cliente.AlquilarCarroSinR(carroAlquiler, sedeRecAlquiler, sedeEntAlquiler, FechaHoraRecAlquiler, FechaHoraEntAlquiler, cliente,Boolean.parseBoolean(tieneReserva) , conductoresAdicionales, segurosAlquiler, empresa, ArchivoAlquileres);
            if (x.equals("Su alquiler fue creado de manera exitosa")){
                return true;
            }
            return false;
            
        }
        else{
            carroAlquiler = cliente.RetornarCarroAlquiler(empresa, cliente);
            String x = cliente.AlquilarCarroSinR(carroAlquiler, sedeRecAlquiler, sedeEntAlquiler, FechaHoraRecAlquiler, FechaHoraEntAlquiler, cliente,true , conductoresAdicionales, segurosAlquiler, empresa, ArchivoAlquileres);
            if (x.equals("Su alquiler fue creado de manera exitosa")){
                return true;
            }
            return false;
        }        
    }
    public ConductorAdicional RegistrarConductorAdicional(String NombreConductor,String TelefonoConductor,String NumLicenciaCond,String PaisExpLicenciaCond,String FechaVencLicenciaCond,Empresa empresa,String ArchivoConductoresAd){
        LocalDate FechaLic = LocalDate.parse(FechaVencLicenciaCond);
        Licencia licenciaCondAd = new Licencia(NumLicenciaCond, PaisExpLicenciaCond, FechaLic);
        ConductorAdicional conductorAd = new ConductorAdicional(NombreConductor, TelefonoConductor, licenciaCondAd);
        conductorAd.AniadirConductorAd(conductorAd, ArchivoConductoresAd, empresa);
        return conductorAd;
    }

    public Boolean PrepararCambioDeEstado(Empresa empresa,Empleado empleado,String PlacaCarro,String NombreEstado,String FechaInicio,String FechaFinal){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Estado estadoNuevo = null;
        Carro carro = empresa.BuscarCarroPlaca(PlacaCarro);
        if (carro != null){
            if (NombreEstado.equals("Disponible")){
                estadoNuevo = new Estado(NombreEstado);
            }
            else{
                estadoNuevo = new Estado(NombreEstado);
                estadoNuevo.setFechaInicio(LocalDateTime.parse(FechaInicio, formatter));
                estadoNuevo.setFechaDisponibilidad(LocalDateTime.parse(FechaFinal, formatter));
            }
            empleado.ActualizarEstadoCarro(carro, estadoNuevo);
            return true;
        }
        else{return false;}    }

    public static HashMap<String,Sede> processSedes(String filePath){
        String line;
        String SplitBy = ",";
        HashMap<String, Sede> sedes = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip the first line (header)
            br.readLine();

            while ((line = br.readLine()) != null) {
                // Use comma as separator
                String[] values = line.split(SplitBy);

                // Pass values to Sede method
                Sede sede = new Sede(values[0], values[1], LocalTime.parse(values[2]), LocalTime.parse(values[3]));
                sedes.put(values[0],sede);
            }
            
                
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return sedes;
    }
    public static HashMap<String,Seguro> processSeguros(String filePath){
        String line;
        String SplitBy = ",";
        HashMap<String, Seguro> seguros = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip the first line (header)
            br.readLine();

            while ((line = br.readLine()) != null) {
                // Use comma as separator
                String[] values = line.split(SplitBy);

                // Pass values to Seguro method
                Seguro seguro = new Seguro(values[0], values[1],Integer.parseInt(values[2]));
                seguros.put(values[0],seguro);
            }
            
                
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return seguros;
    }
    public static HashMap<String,Categoria> processCategorias(String filePath){
        String line;
        String SplitBy = ",";
        HashMap<String, Categoria> categorias = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip the first line (header)
            br.readLine();

            while ((line = br.readLine()) != null) {
                // Use comma as separator
                String[] values = line.split(SplitBy);

                // Pass values to Sede method
                Categoria categoria = new Categoria(values[0],Integer.parseInt(values[1]),Integer.parseInt(values[2]));
                categorias.put(values[0],categoria);
            }
            
                
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return categorias;
    }
    public static HashMap<String,Cliente> processClientes(String filePath){
        String line;
        String SplitBy = ",";
        HashMap<String, Cliente> clientes = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip the first line (header)
            br.readLine();

            while ((line = br.readLine()) != null) {
                // Use comma as separator
                String[] values = line.split(SplitBy);

                // Create the licencia and tarjeta objects
                Licencia licencia = new Licencia(values[7], values[8], LocalDate.parse(values[9]));
                TarjetaCred tarjeta = new TarjetaCred(values[10],LocalDate.parse(values[11]));
                //Create the cliente object
                Cliente cliente = new Cliente(values[0],values[1],LocalDate.parse(values[2]), values[3], values[4], values[5], values[6], licencia, tarjeta);
                clientes.put(values[5],cliente);
            }
                 
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return clientes;

    }
    public static HashMap<String,Empleado> processEmpleados (String filePath,Empresa empresa){
        String line;
        String SplitBy = ",";
        HashMap<String, Empleado> empleados = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip the first line (header)
            br.readLine();

            while ((line = br.readLine()) != null) {
                // Use comma as separator
                String[] values = line.split(SplitBy);
                // Gets the Sede of the empleado
                String NombreSede = values[2];
                HashMap<String, Sede> sedesEmpresa = empresa.getSedes();
                HashMap<String,Empleado> EmpleadosSede = new HashMap<>();
                Sede sedeEmpleado = sedesEmpresa.get(NombreSede);
                if (sedeEmpleado == null){System.out.println("No existe la sede de alguno de los empleados en el archivo");}
                else{
                    EmpleadosSede = sedeEmpleado.getEmpleados();
                    Empleado empleado = new Empleado(values[0],values[1],sedeEmpleado);
                    empleados.put(values[0],empleado);
                    EmpleadosSede.put(values[0],empleado);
                    sedeEmpleado.setEmpleados(EmpleadosSede);
                }
            }
        }
         catch (IOException e) {
            e.printStackTrace();
        }
        return empleados;
    }
    public static HashMap<String,AdministradorLocal> processAdmins (String filePath,Empresa empresa){
        String line;
        String SplitBy = ",";
        HashMap<String, AdministradorLocal> admins = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip the first line (header)
            br.readLine();

            while ((line = br.readLine()) != null) {
                // Use comma as separator
                String[] values = line.split(SplitBy);

                String NombreSede = values[2];
                HashMap<String, Sede> sedesEmpresa = empresa.getSedes();
                Sede sedeAdmin = sedesEmpresa.get(NombreSede);
                if (sedeAdmin == null){System.out.println("No existe la sede de alguno de los Aministradores en el archivo");}
                else{
                    AdministradorLocal Admin = new AdministradorLocal(values[0], values[1], sedeAdmin);
                    sedeAdmin.setAdminLocal(Admin);
                    admins.put(values[0],Admin);
                }
            }
                 
        }
         catch (IOException e) {
            e.printStackTrace();
        }
        return admins;
    }
    public static HashMap<String,Carro> processCarros(String filePath, Empresa empresa){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String line;
        String SplitBy = ",";
        HashMap<String, Carro> carros = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip the first line (header)
            br.readLine();

            while ((line = br.readLine()) != null) {
                // Use comma as separator
                String[] values = line.split(SplitBy);
                //Creates Estado for the car
                Estado estadoCarro = new Estado("Null");
                if (values[6].equals("Disponible")){
                    estadoCarro.setNombre("Disponible");
                }
                else if (values[6].equals("Alquilado")|| values[6].equals("Reservado")|| values[6].equals("Limpieza")|| values[6].equals("Mantenimiento")){
                    if (!values[7].isEmpty() && !values[8].isEmpty()){
                        estadoCarro.setNombre(values[6]);
                        estadoCarro.setFechaInicio(LocalDateTime.parse(values[7],formatter));
                        estadoCarro.setFechaDisponibilidad(LocalDateTime.parse(values[8],formatter));
                    }
                    else{System.out.println("La informacion del estado es incorrecta (verifique que sea un estado valido y que las fechas sean consistentes)");}
                }
                //Busca la categoria del carro
                HashMap<String,Categoria> categoriasEmpresa = empresa.getCategorias();
                Categoria categoriaCarro = categoriasEmpresa.get(values[9]);
                if (categoriaCarro == null){System.out.println("No existe la categoria dada");}
                //Busca la sede del carro
                HashMap<String,Sede> sedesEmpresa = empresa.getSedes();
                Sede sedeCarro = sedesEmpresa.get(values[10]);
                if (sedeCarro == null){System.out.println("No existe la sede dada");}
                if (categoriaCarro == null || sedeCarro == null){System.out.println("No es posible crear el carro, la informacion es incorrecta");}
                else{
                    Carro carro = new Carro(values[0], values[1], values[2], values[3], values[4], Integer.parseInt(values[5]), estadoCarro, categoriaCarro, sedeCarro);
                    carros.put(values[0],carro);
                    HashMap<String,Carro> CarrosCat = categoriaCarro.getCarrosCategoria();
                    CarrosCat.put(values[0],carro);
                    categoriaCarro.setCarrosCategoria(CarrosCat);
                    HashMap<String,Carro> CarrosSede = sedeCarro.getCarros();
                    CarrosSede.put(values[0],carro);
                    sedeCarro.setCarros(CarrosSede);

                }
            }
                 
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return carros;
    }
    public static HashMap<String,ConductorAdicional> processConductoresAdicionales(String filePath){
        String line;
        String SplitBy = ",";
        HashMap<String, ConductorAdicional> conductoresAd = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip the first line (header)
            br.readLine();

            while ((line = br.readLine()) != null) {
                // Use comma as separator
                String[] values = line.split(SplitBy);
                //Create the Licencia for conductor
                Licencia licencia = new Licencia(values[2], values[3], LocalDate.parse(values[4]));
                //Create the Conductor adicional
                ConductorAdicional conductorAd = new ConductorAdicional(values[0], values[1], licencia);
                conductoresAd.put(values[2],conductorAd);

            }
            
                
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return conductoresAd;
    }
    public static ArrayList<Alquiler> processAlquileres(String filePath, Empresa empresa){
        String line;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String SplitBy = ",";
        ArrayList<Alquiler> Alquileres = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip the first line (header)
            br.readLine();

            while ((line = br.readLine()) != null) {
                // Use comma as separator
                String[] values = line.split(SplitBy);
                HashMap<String,Sede> sedesEmpresa = empresa.getSedes();
                Sede SedeInicio = sedesEmpresa.get(values[2]);
                Sede SedeFinal = sedesEmpresa.get(values[3]);
                if (SedeFinal == null || SedeInicio == null){System.out.println("Una o ambas de las sedes dadas no existe");}
                HashMap<String,ConductorAdicional> conductoresAd = empresa.getConductoresAdicionales();
                String[] conductoresAdArray = values[5].split(";");
                ArrayList<ConductorAdicional> conductoresAdicionales = new ArrayList<>();
                for(String conductorAd : conductoresAdArray){
                    ConductorAdicional conductor = conductoresAd.get(conductorAd);
                    conductoresAdicionales.add(conductor);

                }
                HashMap<String,Carro> carrosEmpresa  = empresa.getInventario();
                Carro carroAlquiler = carrosEmpresa.get(values[6]);
                if (carroAlquiler == null) {System.out.println("El carro dado no existe");}
                HashMap<String,Cliente> clientesEmpresa = empresa.getClientes();
                Cliente ClienteAlquiler = clientesEmpresa.get(values[8]);
                if(ClienteAlquiler == null||SedeInicio == null || SedeFinal == null){System.out.println("No se puede crear el alquiler, verifique la informacion del archivo");}
                else{
                    Alquiler alquiler = new Alquiler(LocalDateTime.parse(values[0],formatter),LocalDateTime.parse(values[1],formatter),SedeFinal, SedeInicio,Boolean.parseBoolean(values[4]), conductoresAdicionales, carroAlquiler, Long.parseLong(values[7]), ClienteAlquiler);
                    Alquileres.add(alquiler);
                }
            }     
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return Alquileres;
    }
    public static HashMap<String,Reserva> processReservas (String filePath, Empresa empresa){
        String line;
        String SplitBy = ",";
        HashMap<String, Reserva> reservas = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip the first line (header)
            br.readLine();

            while ((line = br.readLine()) != null) {
                // Use comma as separator
                String[] values = line.split(SplitBy);
                HashMap<String,Sede> sedesEmpresa = empresa.getSedes();
                Sede sedeRecogida = sedesEmpresa.get(values[0]);
                Sede sedeEntrega = sedesEmpresa.get(values[1]);
                HashMap<String,Categoria> categoriasEmpresa = empresa.getCategorias();
                Categoria categoriaReserva = categoriasEmpresa.get(values[4]);
                HashMap<String,Cliente> clientesEmpresa = empresa.getClientes();
                Cliente clienteReserva = clientesEmpresa.get(values[6]);
                if (sedeRecogida == null || sedeEntrega == null||categoriaReserva == null||clienteReserva == null){
                    System.out.println("La informacion de la reserva es incorrecta");
                }
                else{
                    //Creates the object of class Reserva
                    Reserva reserva = new Reserva(sedeRecogida, sedeEntrega, LocalDateTime.parse(values[2],formatter), LocalDateTime.parse(values[3],formatter), categoriaReserva, Integer.parseInt(values[5]), clienteReserva);
                    reservas.put(values[6],reserva);
                }
            }
            
                
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return reservas;
    }
    
}