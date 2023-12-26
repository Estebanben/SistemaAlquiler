package proyecto1Consola;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import proyecto1BackEnd.*;
import proyecto1Consola.*;

public class VentanaInicio extends JFrame {

    private String TipoUsuario;
    private JPanel currentPanel;
    private Empresa empresa;
    private App app;
    private String ArchivoAdmins;
    private String ArchivoAlquileres;
    private String ArchivoCarros;
    private String ArchivoCategorias;
    private String ArchivoClientes;
    private String ArchivoConductoresAd;
    private String ArchivoEmpleados;
    private String ArchivoReservas;
    private String ArchivoSedes;
    private String ArchivoSeguros;

    public VentanaInicio(Empresa empresa, App app,String ArchivoAdmins,String ArchivoAlquileres,String ArchivoCarros,String ArchivoCategorias,String ArchivoClientes,String ArchivoConductoresAd,String ArchivoEmpleados,String ArchivoReservas,String ArchivoSedes,String ArchivoSeguros) {
        this.empresa=empresa;
        this.app=app;
        this.ArchivoAdmins = ArchivoAdmins;
        this.ArchivoAlquileres = ArchivoAlquileres;
        this.ArchivoCarros = ArchivoCarros;
        this.ArchivoCategorias = ArchivoCategorias;
        this.ArchivoClientes = ArchivoClientes;
        this.ArchivoConductoresAd = ArchivoConductoresAd;
        this.ArchivoEmpleados = ArchivoConductoresAd;
        this.ArchivoReservas = ArchivoReservas;
        this.ArchivoSedes = ArchivoSedes;
        this.ArchivoSeguros = ArchivoSeguros;
        
        setTitle("Ventana Inicio");

       
        setLayout(new BorderLayout());

        getContentPane().setBackground(Color.BLACK);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));

        
        JButton botonCliente = new JButton("Cliente");
        JButton botonAdminLocal = new JButton("AdminiLocal");
        JButton botonAdmin = new JButton("Admin");
        JButton botonEmpleado = new JButton("Empleado");


        botonCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TipoUsuario = "Cliente";
                if (currentPanel != null) {
                    getContentPane().remove(currentPanel);
                }

                // Create a new panel and add it to the center of the JFrame
                currentPanel = new PanelSesionCliente(empresa,app,VentanaInicio.this,ArchivoClientes,ArchivoAlquileres,ArchivoReservas,ArchivoConductoresAd,ArchivoSeguros);
                currentPanel.setBackground(Color.BLACK);
                add(currentPanel, BorderLayout.CENTER);
                revalidate();
                repaint();
                
            }
        });

        botonAdminLocal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TipoUsuario = "AdminLocal";
                if (currentPanel != null) {
                    getContentPane().remove(currentPanel);
                }

                // Create a new panel and add it to the center of the JFrame
                currentPanel = new PanelSesion(empresa,TipoUsuario,app,VentanaInicio.this,ArchivoAdmins,ArchivoAlquileres,ArchivoCarros, ArchivoCategorias,ArchivoClientes, ArchivoConductoresAd, ArchivoEmpleados, ArchivoReservas, ArchivoSedes, ArchivoSeguros);
                currentPanel.setBackground(Color.BLACK);
                add(currentPanel, BorderLayout.CENTER);

                revalidate();
                repaint();
            }
        });

        botonAdmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TipoUsuario = "Admin";
                if (currentPanel != null) {
                    getContentPane().remove(currentPanel);
                }
                // Create a new panel and add it to the center of the JFrame
                currentPanel = new PanelSesion(empresa,TipoUsuario,app,VentanaInicio.this,ArchivoAdmins,ArchivoAlquileres,ArchivoCarros, ArchivoCategorias,ArchivoClientes, ArchivoConductoresAd, ArchivoEmpleados, ArchivoReservas, ArchivoSedes, ArchivoSeguros);
                currentPanel.setBackground(Color.BLACK);
                add(currentPanel, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });

        botonEmpleado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TipoUsuario = "Empleado";
                if (currentPanel != null) {
                    getContentPane().remove(currentPanel);
                }

                // Create a new panel and add it to the center of the JFrame
                currentPanel = new PanelSesion(empresa,TipoUsuario,app,VentanaInicio.this,ArchivoAdmins,ArchivoAlquileres,ArchivoCarros, ArchivoCategorias,ArchivoClientes, ArchivoConductoresAd, ArchivoEmpleados, ArchivoReservas, ArchivoSedes, ArchivoSeguros);
                currentPanel.setBackground(Color.BLACK);
                add(currentPanel, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });

        buttonPanel.add(botonCliente);
        buttonPanel.add(botonAdminLocal);
        buttonPanel.add(botonAdmin);
        buttonPanel.add(botonEmpleado);

       
        add(buttonPanel, BorderLayout.NORTH);
        

        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setVisible(true);
    }
    


    public String getTipoUsuario() {
        return TipoUsuario;
    }



    public JPanel getCurrentPanel() {
        return currentPanel;
    }



    public Empresa getEmpresa() {
        return empresa;
    }



    public App getApp() {
        return app;
    }



    public void setCurrentPanel(JPanel currentPanel) {
        this.currentPanel = currentPanel;
    }



    public static void main(String[] args) {
        Administrador Admin = new Administrador("Administrador", "Administrador");
        Empresa empresa = new Empresa(Admin);
        App app = new App();
        UIManager.put("Label.foreground", Color.WHITE);
        String path = Paths.get("Data").toAbsolutePath().toString();
        String ArchivoSedes = path + "/Sedes.csv";
        String ArchivoSeguros = path + "/Seguros.csv";
        String ArchivoCategorias = path + "/Categorias.csv";
        String ArchivoClientes = path +"/Clientes.csv";
        String ArchivoEmpleados = path + "/Empleados.csv";
        String ArchivoCarros = path + "/Carros.csv";
        String ArchivoAlquileres = path + "/Alquileres.csv";
        String ArchivoAdmins = path + "/AdminsLocales.csv";
        String ArchivoReservas = path +"/Reservas.csv";
        String ArchivoConductoresAd = path +"/ConductoresAdicionales.csv";
        
        //Procesa las sedes
        HashMap<String,Sede> sedes = app.processSedes(ArchivoSedes);
        empresa.setSedes(sedes);
        //Procesa los seguros
        HashMap<String,Seguro> seguros = app.processSeguros(ArchivoSeguros);
        empresa.setSeguros(seguros);
        //Procesa las categorias
        HashMap<String,Categoria> categorias = app.processCategorias(ArchivoCategorias);
        empresa.setCategorias(categorias);
        //Procesa los clientes
        HashMap<String,Cliente> clientes = app.processClientes(ArchivoClientes);
        empresa.setClientes(clientes);
        //Procesa los empleados
        HashMap<String,Empleado> empleados = app.processEmpleados(ArchivoEmpleados, empresa);
        empresa.setEmpleados(empleados);
        //Procesa los Administradores locales
        HashMap<String, AdministradorLocal> administradores = app.processAdmins(ArchivoAdmins, empresa);
        empresa.setAdminsLocales(administradores);
        //Procesa los carros
        HashMap<String,Carro> carros = app.processCarros(ArchivoCarros, empresa);
        empresa.setInventario(carros);
        //Procesa los conductores adicionales
        HashMap<String,ConductorAdicional> conductoresAd = app.processConductoresAdicionales(ArchivoConductoresAd);
        empresa.setConductoresAdicionales(conductoresAd);
        //Procesa los alquileres
        ArrayList<Alquiler> alquileres = app.processAlquileres(ArchivoAlquileres, empresa);
        empresa.setAlquileres(alquileres);
        //Procesa las reservas
        HashMap<String,Reserva> reservas = app.processReservas(ArchivoReservas,empresa);
        empresa.setReservas(reservas);
       
        System.out.println("Se cargaron los archivos (en caso de obtener mensajes de error revise el contenido de los archivos y vuelva a intentar)");

        VentanaInicio ventana = new VentanaInicio(empresa,app,ArchivoAdmins,ArchivoAlquileres,ArchivoCarros,ArchivoCategorias,ArchivoClientes,ArchivoConductoresAd,ArchivoEmpleados,ArchivoReservas,ArchivoSedes,ArchivoSeguros);

        //lee el input del usuario
        Scanner scanner = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
                System.out.println("Bienvenido al sistema de alquileres, Seleccione una opcion:");
                System.out.println("1.Iniciar sesion como cliente");
                System.out.println("2.Iniciar sesion como administradorLocal");
                System.out.println("3.Iniciar sesion como Administrador");
                System.out.println("4.Iniciar sesion como empleado");
                System.out.println("5.Registrarse como cliente");
                System.out.println("6.Salir");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                    Boolean ExisteUsuario = false;
                    Boolean CoincideClave = false;
                    System.out.print("Usuario: ");
        
                    String username = reader.readLine();
                   
                    System.out.print("Clave: ");
                   
                    String password = reader.readLine();
                    Cliente cliente = empresa.BuscarClienteUsuario(username);
                    if (cliente != null){ExisteUsuario = true;if (cliente.getClave().equals(password)){CoincideClave = true;}}
                    if (ExisteUsuario && CoincideClave){

                        System.out.println("Inicio de sesion exitoso");
                        boolean done = false;
                        while (!done) {
                            try{
                                System.out.println("1. Reservar carro");
                            System.out.println("2. Alquilar Carro");
                            System.out.println("3. Logout");
                            System.out.print("Enter your choice: ");
                            int userChoice = scanner.nextInt();
                            scanner.nextLine();  // Consume newline left-over
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                            switch (userChoice) {
                                case 1:
                                	System.out.print("Ingrese el nombre de la categoria que desea reservar: ");
                                    System.out.flush();  // Flush the output buffer
                                    String nombreCategoria = reader.readLine();

                                    System.out.print("Ingrese el nombre de la sede donde desea recoger el vehiculo: ");
                                    System.out.flush();  // Flush the output buffer
                                    String nombreSedeRec = reader.readLine();

                                    System.out.print("Ingrese el nombre de la sede donde se entregara el vehiculo: ");
                                    System.out.flush();  // Flush the output buffer
                                    String nombreSedeEnt = reader.readLine();

                                    System.out.print("Ingrese la fecha y hora en la que va a recoger el vehiculo (YYYY-MM-DD HH:MM): ");
                                    System.out.flush();  // Flush the output buffer
                                    String FechaHoraR = reader.readLine();

                                    System.out.print("Ingrese la fecha y hora en la que va a entregar el vehiculo (YYYY-MM-DD HH:MM): ");
                                    System.out.flush();  // Flush the output buffer
                                    String FechaHoraE = reader.readLine();
                                    Categoria categoriaReserva = empresa.BuscarCategoriaNombre(nombreCategoria);
                                    Sede sedeRec = empresa.BuscarSedeNombre(nombreSedeRec);
                                    Sede sedeEnt = empresa.BuscarSedeNombre(nombreSedeEnt);
                                    LocalDateTime FechaHoraRec = LocalDateTime.parse(FechaHoraR, formatter);
                                    LocalDateTime FechaHoraEnt = LocalDateTime.parse(FechaHoraE, formatter);
                                    cliente.ReservarCarro(categoriaReserva, sedeRec, sedeEnt, FechaHoraRec, FechaHoraEnt, cliente, empresa,ArchivoReservas);
                                    break;
                                case 2:
                                    ArrayList<ConductorAdicional> conductoresAdicionales = new ArrayList<>();
                                    ArrayList<Seguro> segurosAlquiler = new ArrayList<>();
                                    Carro carroAlquiler = null;
                                    String placaCarroAlquiler = null;
                                    System.out.print("Tiene una reserva previa? (true/false)");
                                    System.out.flush();
                                    String tieneReserva = reader.readLine();
                                    if (tieneReserva.equals("false")){ System.out.print("Ingrese la placa del carro que desea alquilar: ");System.out.flush();  // Flush the output buffer
                                    placaCarroAlquiler = reader.readLine();}
                                    
                                    System.out.print("Ingrese el nombre de la sede donde desea recoger el vehiculo: ");
                                    System.out.flush();  // Flush the output buffer
                                    String nombreSedeRecAlquiler = reader.readLine();
                                    System.out.print("Ingrese el nombre de la sede donde se entregara el vehiculo: ");
                                    System.out.flush();  // Flush the output buffer
                                    String nombreSedeEntAlquiler = reader.readLine();
                                    System.out.print("Ingrese la fecha y hora en la que va a recoger el vehiculo (YYYY-MM-DD HH:MM): ");
                                    System.out.flush();  // Flush the output buffer
                                    String FechaHoraRalquiler = reader.readLine();
                                    System.out.print("Ingrese la fecha y hora en la que va a entregar el vehiculo (YYYY-MM-DD HH:MM): ");
                                    System.out.flush();  // Flush the output buffer
                                    String FechaHoraEalquiler = reader.readLine();
                                    System.out.print("Desea tener conductores adicionales para su alquiler? (true/false): ");
                                    System.out.flush();  // Flush the output buffer
                                    String tieneConductoresAd = reader.readLine();
                                    if (tieneConductoresAd.equalsIgnoreCase("true")){
                                    	System.out.print("Cuantos conductores adicionales va a tener?: ");
                                        System.out.flush();  // Flush the output buffer
                                        String numeroConductoresAd = reader.readLine();
                                        int numConductores = Integer.parseInt(numeroConductoresAd);

                                        for (int i = 1; i <= numConductores; i++) {
                                        	System.out.print("Ingrese el nombre del conductor " + i + ": ");
                                            System.out.flush();  // Flush the output buffer
                                            String NombreConductor = reader.readLine();

                                            System.out.print("Ingrese el telefono del conductor " + i + ": ");
                                            System.out.flush();  // Flush the output buffer
                                            String TelefonoConductor = reader.readLine();

                                            System.out.print("Ingrese el numero de la licencia del conductor " + i + ": ");
                                            System.out.flush();  // Flush the output buffer
                                            String NumLicenciaCond = reader.readLine();

                                            System.out.print("Ingrese el pais de expedicion de la licencia del conductor " + i + ": ");
                                            System.out.flush();  // Flush the output buffer
                                            String PaisExpLicenciaCond = reader.readLine();

                                            System.out.print("Ingrese la fecha de vencimiento de la licencia del conductor " + i + "(yyyy-MM-dd): ");
                                            System.out.flush();  // Flush the output buffer
                                            String FechaVencLicenciaCond = reader.readLine();
                                            LocalDate FechaLic = LocalDate.parse(FechaVencLicenciaCond);
                                            Licencia licenciaCondAd = new Licencia(NumLicenciaCond, PaisExpLicenciaCond, FechaLic);
                                            ConductorAdicional conductorAd = new ConductorAdicional(NombreConductor, TelefonoConductor, licenciaCondAd);
                                            conductoresAdicionales.add(conductorAd);
                                            conductorAd.AniadirConductorAd(conductorAd, ArchivoConductoresAd, empresa);
                                        }
                                    }
                                    HashMap<String,Seguro> segurosEmpresa = empresa.getSeguros();
                                    System.out.print("Desea tener seguros para su alquiler? (true/false): ");
                                    System.out.flush();  // Flush the output buffer
                                    String tieneSeguros = reader.readLine();
                                    if (tieneSeguros.equalsIgnoreCase("true")){
                                    	System.out.print("Cuantos seguros va a tener?: ");
                                        System.out.flush();  // Flush the output buffer
                                        String numeroSeguros = reader.readLine();
                                        int numSeguros = Integer.parseInt(numeroSeguros);
                                        for (int i = 1; i <= numSeguros; i++) {
                                            System.out.print("Ingrese el nombre del seguro que desea comprar: ");
                                            System.out.flush();  // Flush the output buffer
                                            String NombreSeguro = reader.readLine();
                                            if (segurosEmpresa.get(NombreSeguro)!=null){
                                                if (segurosAlquiler.contains(segurosEmpresa.get(NombreSeguro))){
                                                    System.out.println("El seguro elegido ya se encuentra en los seguros del alquiler");
                                                }
                                                else{
                                                    segurosAlquiler.add(segurosEmpresa.get(NombreSeguro));
                                                }
                                            }
                                            else{
                                                System.out.println("El seguro dado no existe");
                                            }
                                        }
                                    }
                                    Sede sedeRecAlquiler = empresa.BuscarSedeNombre(nombreSedeRecAlquiler);
                                    Sede sedeEntAlquiler = empresa.BuscarSedeNombre(nombreSedeEntAlquiler);
                                    LocalDateTime FechaHoraRecAlquiler = LocalDateTime.parse(FechaHoraRalquiler, formatter);
                                    LocalDateTime FechaHoraEntAlquiler = LocalDateTime.parse(FechaHoraEalquiler, formatter);
                                    if (tieneReserva.equals("false")){
                                        carroAlquiler = sedeRecAlquiler.getCarros().get(placaCarroAlquiler);
                                        cliente.AlquilarCarroSinR(carroAlquiler, sedeRecAlquiler, sedeEntAlquiler, FechaHoraRecAlquiler, FechaHoraEntAlquiler, cliente,Boolean.parseBoolean(tieneReserva) , conductoresAdicionales, segurosAlquiler, empresa, ArchivoAlquileres);
                                    }
                                    else{
                                        carroAlquiler = cliente.RetornarCarroAlquiler(empresa, cliente);
                                        cliente.AlquilarCarroSinR(carroAlquiler, sedeRecAlquiler, sedeEntAlquiler, FechaHoraRecAlquiler, FechaHoraEntAlquiler, cliente,true , conductoresAdicionales, segurosAlquiler, empresa, ArchivoAlquileres);
                                    }
                                    
                                    
                                    
                                    break;
                                case 3:
                                    done = true;
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                                
                            }

                                
                            }
                            catch (Exception e) {
                                System.out.println("An error occurred: " + e.getMessage());
                            }
                        }

                    }
                    else{System.out.println("Los datos de inicio de sesion son incorrectos");}
                        break;
                    case 2:
                        // Handle option 2
                        Boolean ExisteUsuarioAdminLocal = false;
                        Boolean CoincideClaveAdminLocal = false;
                        System.out.print("Usuario: ");
                        System.out.flush();  // Flush the output buffer
                        String usernameAdminLocal = reader.readLine();

                        System.out.print("Clave: ");
                        System.out.flush();  // Flush the output buffer
                        String passwordAdminLocal = reader.readLine();
                        AdministradorLocal adminLocal = empresa.BuscarAdminLocalUsuario(usernameAdminLocal);
                        if (adminLocal != null){ExisteUsuarioAdminLocal = true;if(adminLocal.getClave().equals(passwordAdminLocal)){CoincideClaveAdminLocal = true;}}
                        if (ExisteUsuarioAdminLocal && CoincideClaveAdminLocal){
                            
                        boolean done = false;
                        while (!done) {
                            System.out.println("1. Crear nuevo empleado");
                            System.out.println("2. Eliminar Empleado");
                            System.out.println("3. Logout");
                            System.out.print("Enter your choice: ");
                            int userChoice = scanner.nextInt();
                            scanner.nextLine();  // Consume newline left-over
                            switch (userChoice) {
                                case 1:
                                	System.out.print("Ingrese el usuario del empleado a crear: ");
                                    System.out.flush();  // Flush the output buffer
                                    String UsuarioEmpleado = reader.readLine();

                                    System.out.print("Ingrese la clave del empleado a crear: ");
                                    System.out.flush();  // Flush the output buffer
                                    String ClaveEmpleado = reader.readLine();

                                    adminLocal.CrearEmpleado(UsuarioEmpleado, ClaveEmpleado, adminLocal.getSede(), empresa, ArchivoEmpleados);
                                    break;
                                case 2:
                                	System.out.print("Ingrese el usuario del empleado a eliminar: ");
                                    System.out.flush();  // Flush the output buffer
                                    String UsuarioEmpleadoEliminar = reader.readLine();
                                    adminLocal.EliminarEmpleado(UsuarioEmpleadoEliminar, empresa, adminLocal.getSede());
                                    break;
                                case 3:
                                    done = true;
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                            }
                        }

                        }
                        break;
                    case 3:
                        //Handle option 3
                    	System.out.print("Usuario: ");
                        System.out.flush();  // Flush the output buffer
                        String usernameAdmin = reader.readLine();

                        System.out.print("Clave: ");
                        System.out.flush();  // Flush the output buffer
                        String passwordAdmin = reader.readLine();
                        String UsuarioAdmin = empresa.getAdmin().getUsuario();
                        String ClaveAdmin = empresa.getAdmin().getClave();
                        Administrador admin = empresa.getAdmin();
                        if (usernameAdmin.equals(UsuarioAdmin) && passwordAdmin.equals(ClaveAdmin)){
                            System.out.println("Inicio de sesion exitoso");
                            boolean done = false;
                        while (!done) {
                            System.out.println("1. Registrar nuevo carro");
                            System.out.println("2. Dar de baja un carro");
                            System.out.println("3. Transladar vehiculo");
                            System.out.println("4. Crear una nueva sede");
                            System.out.println("5. Crear administrador local");
                            System.out.println("6. Modificar info sede");
                            System.out.println("7. Crear categoria");
                            System.out.println("8. Crear Seguro");
                            System.out.println("9. Logout");
                            System.out.print("Enter your choice: ");
                            int userChoice = scanner.nextInt();
                            scanner.nextLine();  // Consume newline left-over
                            switch (userChoice) {
                                case 1:
                                	System.out.print("Ingrese la placa del vehiculo a registrar: ");
                                    System.out.flush();  // Flush the output buffer
                                    String Placa = reader.readLine();

                                    System.out.print("Ingrese la transmision del vehiculo a registrar: ");
                                    System.out.flush();  // Flush the output buffer
                                    String Transmision = reader.readLine();

                                    System.out.print("Ingrese el color del vehiculo a registrar: ");
                                    System.out.flush();  // Flush the output buffer
                                    String Color = reader.readLine();

                                    System.out.print("Ingrese la marca del vehiculo a registrar: ");
                                    System.out.flush();  // Flush the output buffer
                                    String Marca = reader.readLine();

                                    System.out.print("Ingrese el modelo del vehiculo a registrar: ");
                                    System.out.flush();  // Flush the output buffer
                                    String Modelo = reader.readLine();

                                    System.out.print("Ingrese la capacidad del vehiculo a registrar: ");
                                    System.out.flush();  // Flush the output buffer
                                    String Capacidad = reader.readLine();

                                    System.out.print("Ingrese el nombre de la categoria del vehiculo a registrar: ");
                                    System.out.flush();  // Flush the output buffer
                                    String NombreCat = reader.readLine();
                                    
                                    Estado estadoCarro = new Estado("Disponible");
                                    
                                    System.out.print("Ingrese el nombre de la sede del vehiculo a registrar: ");
                                    System.out.flush();  // Flush the output buffer
                                    String NombreSede = reader.readLine();
                                    Categoria categoriaCarro = empresa.BuscarCategoriaNombre(NombreCat);
                                    Sede sedeCarro = empresa.BuscarSedeNombre(NombreSede);
                                    admin.RegistrarCompraCarro(Placa, Transmision, Color, Marca, Modelo, Integer.parseInt(Capacidad), categoriaCarro, estadoCarro, sedeCarro, empresa, ArchivoCarros);
                                    break;
                                case 2:
                                	System.out.print("Ingrese la placa del carro que se desea dar de baja:  ");
                                    System.out.flush();  // Flush the output buffer
                                    String placa = reader.readLine();
                                    admin.DarDeBajaCaro(placa, empresa);
                                    break;
                                case 3:
                                	System.out.print("Ingrese la placa del vehiculo a transladar: ");
                                    System.out.flush();  // Flush the output buffer
                                    String PlacaCarro = reader.readLine();

                                    System.out.print("Ingrese la sede inicial del vehiculo a transladar: ");
                                    System.out.flush();  // Flush the output buffer
                                    String NombreSedeI = reader.readLine();

                                    System.out.print("Ingrese la sede final del vehiculo a transladar: ");
                                    System.out.flush();  // Flush the output buffer
                                    String NombreSedeF = reader.readLine();
                                    Carro carroTranslado = empresa.BuscarCarroPlaca(PlacaCarro);
                                    Sede SedeInicioTranslado = empresa.BuscarSedeNombre(NombreSedeI);
                                    Sede SedeFinalTranslado = empresa.BuscarSedeNombre(NombreSedeF);
                                    admin.GenerarReservaEspecial(carroTranslado,SedeInicioTranslado,SedeFinalTranslado);
                                    break;
                                case 4:
                                	System.out.print("Ingrese el nombre de la sede a crear: ");
                                    System.out.flush();  // Flush the output buffer
                                    String NombreSedeCrear = reader.readLine();

                                    System.out.print("Ingrese la direccion de la sede a crear: ");
                                    System.out.flush();  // Flush the output buffer
                                    String DireccionSede = reader.readLine();

                                    System.out.print("Ingrese el horario de apertura de la sede a crear (HH:mm): ");
                                    System.out.flush();  // Flush the output buffer
                                    String HorarioAp = reader.readLine();

                                    System.out.print("Ingrese el horario de cierre de la sede a crear (HH:mm): ");
                                    System.out.flush();  // Flush the output buffer
                                    String NombreCi = reader.readLine();
                                    admin.CrearSede(NombreSedeCrear,DireccionSede,LocalTime.parse(HorarioAp),LocalTime.parse(NombreCi),empresa, ArchivoSedes);
                                    break;
                                case 5:
                                	System.out.print("Ingrese el usuario del administrador local a crear: ");
                                    System.out.flush();  // Flush the output buffer
                                    String UsuarioAdminLocal = reader.readLine();

                                    System.out.print("Ingrese la clave del administrador local a crear: ");
                                    System.out.flush();  // Flush the output buffer
                                    String ClaveAdminLocal = reader.readLine();

                                    System.out.print("Ingrese el nombre de la sede a la cual se desea asignar el administrador local: ");
                                    System.out.flush();  // Flush the output buffer
                                    String NombreSedeAd = reader.readLine();
                                    Sede sedeAdminLocal = empresa.BuscarSedeNombre(NombreSedeAd);
                                    admin.CrearAdminLocal(UsuarioAdminLocal, ClaveAdminLocal, sedeAdminLocal, empresa, ArchivoAdmins);
                                    break;
                                case 6:
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                                    System.out.print("Ingrese el nombre de la sede a la cual se le quiere modificar la informacion: ");
                                    System.out.flush();  // Flush the output buffer
                                    String NombreSedeMod = reader.readLine();

                                    System.out.print("Ingrese el nuevo nombre de la sede: ");
                                    System.out.flush();  // Flush the output buffer
                                    String NuevoNombreSede = reader.readLine();

                                    System.out.print("Ingrese la nueva direccion de la sede: ");
                                    System.out.flush();  // Flush the output buffer
                                    String NuevaDirSede = reader.readLine();

                                    System.out.print("Ingrese el nuevo horario de apertura de la sede(HH:mm): ");
                                    System.out.flush();  // Flush the output buffer
                                    String NuevoHorarioap = reader.readLine();

                                    System.out.print("Ingrese el nuevo horario de cierre de la sede(HH:mm): ");
                                    System.out.flush();  // Flush the output buffer
                                    String NuevoHoracrioci = reader.readLine();
                                    Sede sedeMod = empresa.BuscarSedeNombre(NombreSedeMod);
                                    LocalTime NuevoHorarioAp = LocalTime.parse(NuevoHorarioap,formatter);
                                    LocalTime NuevoHorarioCi = LocalTime.parse(NuevoHoracrioci,formatter);
                                    admin.ModificarSede(sedeMod, NuevoNombreSede, NuevaDirSede, NuevoHorarioAp, NuevoHorarioCi);

                                    break;
                                case 7:
                                	System.out.print("Ingrese el nombre de la categoria a crear: ");
                                    System.out.flush();  // Flush the output buffer
                                    String NombreCategoria = reader.readLine();

                                    System.out.print("Ingrese la tarifa de temporada alta de la categoria: ");
                                    System.out.flush();  // Flush the output buffer
                                    String TarifaTempAlta = reader.readLine();

                                    System.out.print("Ingrese la tarifa de temporada baja de la categoria: ");
                                    System.out.flush();  // Flush the output buffer
                                    String TarifaTempBaja = reader.readLine();
                                    long valorTempAlta = Long.parseLong(TarifaTempAlta);
                                    long valorTempBaja = Long.parseLong(TarifaTempBaja);
                                    admin.CrearCategoria(NombreCategoria, valorTempAlta, valorTempBaja, empresa,ArchivoCategorias);
                                    break;
                                case 8:
                                    System.out.print("Ingrese el nombre del seguro a crear");
                                    System.out.flush();  // Flush the output buffer
                                    String NombreSeguro = reader.readLine();

                                    System.out.print("Ingrese la descripcion del seguro a crear");
                                    System.out.flush();  // Flush the output buffer
                                    String DescripcionSeguro = reader.readLine();

                                    System.out.print("Ingrese la tarifa del seguro a crear");
                                    System.out.flush();  // Flush the output buffer
                                    String PrecioSeguro = reader.readLine();
                                    Integer precioEntero = Integer.parseInt(PrecioSeguro);
                                    admin.CrearSeguro(NombreSeguro, DescripcionSeguro, precioEntero, ArchivoSeguros, empresa);
                                    break;
                                case 9:
                                    done = true;
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                            }
                        }
                        }
                        break;
                    case 4:
                        Boolean ExisteUsuarioEmpleado = false;
                        Boolean CoincideClaveEmpleado = false;
                        System.out.print("Usuario: ");
                        System.out.flush();  // Flush the output buffer
                        String usernameEmpleado = reader.readLine();

                        System.out.print("Clave: ");
                        System.out.flush();  // Flush the output buffer
                        String passwordEmpleado = reader.readLine();
                        Empleado empleado = empresa.BuscarEmpleadoUsuario(usernameEmpleado);
                        if (empleado != null){ExisteUsuarioEmpleado = true;if(empleado.getClave().equals(passwordEmpleado)){CoincideClaveEmpleado = true;}}
                        if(ExisteUsuarioEmpleado && CoincideClaveEmpleado){
                            System.out.println("Inicio de sesion exitoso");
                            boolean done = false;
                        while (!done) {
                            System.out.println("1. Cambiar el estado de un carro");
                            System.out.println("2. Recibir Carro");
                            System.out.println("3. Logout");
                            System.out.print("Enter your choice: ");
                            int userChoice = scanner.nextInt();
                            scanner.nextLine();  // Consume newline left-over
                            switch (userChoice) {
                                case 1:
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                                    Estado estadoNuevo = null;
                                    System.out.print("Ingrese la placa del carro al cual se le desea cambiar el estado: ");
                                    System.out.flush();  // Flush the output buffer
                                    String PlacaCarro = reader.readLine();
                                    Carro carro = empresa.BuscarCarroPlaca(PlacaCarro);
                                    System.out.print("Ingrese el nombre del nuevo estado del carro (Disponible/Alquilado/Reservado/Limpieza/Mantenimiento): ");
                                    System.out.flush();  // Flush the output buffer
                                    String NombreEstado = reader.readLine();
                                    if (carro != null){
                                        if (NombreEstado.equals("Disponible")){
                                            estadoNuevo = new Estado(NombreEstado);
                                        }
                                        else{
                                        	System.out.print("Ingrese la fecha inicial del estado(yyyy-mm-dd HH:mm): ");
                                            System.out.flush();  // Flush the output buffer
                                            String FechaInicio = reader.readLine();

                                            System.out.print("Ingrese la fecha final del estado(yyyy-mm-dd HH:mm): ");
                                            System.out.flush();  // Flush the output buffer
                                            String FechaFinal = reader.readLine();
                                            estadoNuevo = new Estado(NombreEstado);
                                            estadoNuevo.setFechaInicio(LocalDateTime.parse(FechaInicio, formatter));
                                            estadoNuevo.setFechaDisponibilidad(LocalDateTime.parse(FechaFinal, formatter));
                                        }
                                        empleado.ActualizarEstadoCarro(carro, estadoNuevo);
                                    }
                                    else{System.out.println("No existe un carro con la placa dada");}
                                    break;
                                case 2:
                                    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                                    Estado estadoNuevoR = null;
                                    System.out.print("Ingrese la placa del carro a recibir: ");
                                    System.out.flush();  // Flush the output buffer
                                    String PlacaCarroR = reader.readLine();
                                    Carro carroR = empresa.BuscarCarroPlaca(PlacaCarroR);
                                    System.out.print("Ingrese el nombre del nuevo estado del carro (Disponible/Alquilado/Reservado/Limpieza/Mantenimiento): ");
                                    System.out.flush();  // Flush the output buffer
                                    String NombreEstadoN = reader.readLine();
                                    if (carroR != null){
                                        if (NombreEstadoN.equals("Disponible")){
                                            estadoNuevoR = new Estado(NombreEstadoN);
                                        }
                                        else{
                                        	System.out.print("Ingrese la fecha inicial del estado(yyyy-mm-dd HH:mm): ");
                                            System.out.flush();  // Flush the output buffer
                                            String FechaInicio = reader.readLine();

                                            System.out.print("Ingrese la fecha final del estado(yyyy-mm-dd HH:mm): ");
                                            System.out.flush();  // Flush the output buffer
                                            String FechaFinal = reader.readLine();
                                            estadoNuevo = new Estado(NombreEstadoN);
                                            estadoNuevo.setFechaInicio(LocalDateTime.parse(FechaInicio, formatter1));
                                            estadoNuevo.setFechaDisponibilidad(LocalDateTime.parse(FechaFinal, formatter1));
                                        }
                                        empleado.RecibirCarro(carroR, empleado.getSede(), estadoNuevoR);
                                    }
                                    else{System.out.println("No existe un carro con la placa dada");}
                                    break;
                                case 3:
                                    done = true;
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                            }
                        }
                        }
                   
                        break;
                    case 5:
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        System.out.print("Ingrese el usuario: ");
                        System.out.flush();  // Flush the output buffer
                        String Usuario = reader.readLine();

                        System.out.print("Ingrese la clave: ");
                        System.out.flush();  // Flush the output buffer
                        String Clave = reader.readLine();

                        System.out.print("Ingrese su nombre: ");
                        System.out.flush();  // Flush the output buffer
                        String Nombre = reader.readLine();

                        System.out.print("Ingrese su numero de telefono: ");
                        System.out.flush();  // Flush the output buffer
                        String Telefono = reader.readLine();

                        System.out.print("Ingrese su fecha de nacimiento (yyyy-mm-dd): ");
                        System.out.flush();  // Flush the output buffer
                        String FechaNacimiento = reader.readLine();

                        System.out.print("Ingrese su nacionalidad: ");
                        System.out.flush();  // Flush the output buffer
                        String Nacionalidad = reader.readLine();

                        System.out.print("Ingrese su numero de documento: ");
                        System.out.flush();  // Flush the output buffer
                        String NumeroDoc = reader.readLine();

                        System.out.print("Ingrese el numero de su tarjeta de credito: ");
                        System.out.flush();  // Flush the output buffer
                        String NumeroTarjeta = reader.readLine();

                        System.out.print("Ingrese la fecha de vencimiento de su tarjeta de credito (yyyy-mm-dd): ");
                        System.out.flush();  // Flush the output buffer
                        String FechaVEncTarjeta = reader.readLine();

                        System.out.print("Ingrese el numero de su licencia de conduccion: ");
                        System.out.flush();  // Flush the output buffer
                        String NumeroLicencia = reader.readLine();

                        System.out.print("Ingrese el pais de expedicion de su licencia: ");
                        System.out.flush();  // Flush the output buffer
                        String PaisExpLicencia = reader.readLine();

                        System.out.print("Ingrese la fecha de vencimiento de su licencia de conduccion (yyyy-mm-dd): ");
                        System.out.flush();  // Flush the output buffer
                        String FechaVencLicencia = reader.readLine();
                        TarjetaCred tarjeta = new TarjetaCred(NumeroTarjeta, LocalDate.parse(FechaVEncTarjeta,formatter));
                        Licencia licencia = new Licencia(NumeroLicencia, PaisExpLicencia, LocalDate.parse(FechaVencLicencia,formatter));
                        empresa.RegistrarCliente(Nombre, Telefono, LocalDate.parse(FechaNacimiento,formatter), Nacionalidad, NumeroDoc, tarjeta, licencia, Usuario, Clave, empresa, ArchivoClientes);
                        break;
                    case 6:
                    System.out.println("Saliendo...");
                    return;

                    default:
                        System.out.println("Opcion invalida, por favor seleccione una de las opciones que se muestran");
                }
            } catch (Exception e) {
                System.out.println("An error ocurred" + e.getMessage()); 
                scanner.nextLine();  // Clear the input buffer
            }
        }
    }
}
