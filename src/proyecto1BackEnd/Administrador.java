package proyecto1BackEnd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Administrador {

    //Atributes
    private String Usuario;
    private String Clave;
    //Constructor
    public Administrador(String Usuario, String Clave) {
        this.Usuario = Usuario;
        this.Clave = Clave;
    }
    //Metodos
    /**
     * Registra un nuevo carro <br>
     * <b>pre: </b>El hashmap de carros ya esta inicializado<br>
     * <b>pre: </b> Ya se reviso que la sede existe<br>
     * <b>pre: </b> Ya se reviso que la categoria existe<br>
     * <b>post :</b>Se crea un nuevo carro en la empresa con los datos dados
     * @param Placa Placa del carro a crear. Placa != null && Placa != ""
     * @param Transmision Transmision del carro a crear. Transmision != null && Transmision != ""
     * @param Color Color del carro a crear. Color != null && Color != ""
     * @param Marca Marca del carro a crear. Marca != null && Marca != ""
     * @param Modelo Modelo del carro a crear. Modelo != null && Modelo != ""
     * @param Capacidad Capacidad del carro a crear. Capacidad != null && Capacidad != ""
     * @param categoria categoria del carro a crear. categoria != null
     * @param estado estado del carro a crear. estado != null
     * @param sede sede del carro a crear. sede != null
     * @param empresa empresa en la cual se va a guardar el carro, empresa != null
     * @param filePath direccion del archivo donde se guarda la informacion de los carros
     * @throws Exception <br>
    */
    public String RegistrarCompraCarro(String Placa, String Transmision, String Color, String Marca,String Modelo,Integer Capacidad,Categoria categoria,Estado estado,Sede sede,Empresa empresa,String filePath){
        Carro ExisteCarro = empresa.BuscarCarroPlaca(Placa);
        if (ExisteCarro == null){
            HashMap<String,Carro> carrosEmpresa = empresa.getInventario();
            Carro carro = new Carro(Placa, Transmision, Color, Marca, Modelo, Capacidad, estado, categoria, sede);
            carrosEmpresa.put(Placa, carro);
            empresa.setInventario(carrosEmpresa);
            HashMap<String,Carro> carrosCat = categoria.getCarrosCategoria();
            carrosCat.put(Placa,carro);
            categoria.setCarrosCategoria(carrosCat);
            HashMap<String,Carro> carrosSede = sede.getCarros();
            carrosSede.put(Placa,carro);
            sede.setCarros(carrosSede);
            try (FileWriter fw = new FileWriter(filePath, true);PrintWriter pw = new PrintWriter(fw)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    String StringToWrite = ("\n" + Placa + "," + Transmision + "," + Color + "," + Marca + "," + Modelo + "," +String.valueOf(Capacidad) + "," + estado.getNombre());
                    if (estado.getFechaInicio() != null || estado.getFechaDisponibilidad() != null){
                        StringToWrite = StringToWrite + "," + estado.getFechaInicio().format(formatter) + "," + estado.getFechaDisponibilidad().format(formatter);
                    }
                    StringToWrite = StringToWrite + "," +","+","+ carro.getCategoria().getNombre() + "," + carro.getSede().getNombre();
                    pw.print(StringToWrite);
                    return("Exitosos");
                }
                catch (IOException e) {
                    return("An error occurred: " + e.getMessage());
                }
        }
        else {return("Ya existe un carro con la placa dada");}
    }
    /**
     * Elimina un carro <br>
     * <b>pre: </b>El hashmap de carros ya esta inicializado<br>
     * <b>post: </b>El carro dado es eliminado de los carros de la empresa
     * @param Placa placa del carro que se va a eliminar. Placa != null && Placa != ""
     * @param empresa empresa de la cual se quiere eliminar el carro. empresa != null
     * @throws Exception <br>
     */
    public String DarDeBajaCaro(String Placa, Empresa empresa){
        Carro existeCarro = empresa.BuscarCarroPlaca(Placa);
        if (existeCarro == null){return("no existe un carro con la placa dada");}
        else{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            HashMap<String,Carro> carrosEmpresa = empresa.getInventario();
            carrosEmpresa.remove(Placa);
            empresa.setInventario(carrosEmpresa);
            HashMap<String,Carro> carrosSede = existeCarro.getSede().getCarros();
            carrosSede.remove(Placa);
            existeCarro.getSede().setCarros(carrosSede);
            HashMap<String,Carro> carrosCategoria = existeCarro.getCategoria().getCarrosCategoria();
            carrosCategoria.remove(Placa);
            existeCarro.getCategoria().setCarrosCategoria(carrosCategoria);

            String lineToRemove = existeCarro.getPlaca() + "," + existeCarro.getTransmision() + "," +existeCarro.getColor() + "," + existeCarro.getMarca() + "," + existeCarro.getModelo() + "," + String.valueOf(existeCarro.getCapacidad());
            if (existeCarro.getEstado().getNombre().equals("Disponible")){
                lineToRemove = lineToRemove + "," + "Disponible" + "," + ",";
            }
            else{
                lineToRemove = lineToRemove + "," + existeCarro.getEstado().getNombre() + "," + existeCarro.getEstado().getFechaInicio().format(formatter) + "," + existeCarro.getEstado().getFechaDisponibilidad().format(formatter ) + ",";
            }
            lineToRemove = lineToRemove + existeCarro.getCategoria().getNombre() + "," + existeCarro.getSede().getNombre();
            String path = Paths.get("Data").toAbsolutePath().toString();
            String inputFile = path + "/Carros.csv";
            String outputFile = path + "/temp.csv";
            try {
            File inFile = new File(inputFile);
            File tempFile = new File(outputFile);

            BufferedReader reader = new BufferedReader(new FileReader(inFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;
            String previousLine = null;
        
            while((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if(trimmedLine.equals(lineToRemove)) continue;
                if (previousLine != null) {
                    writer.write(System.getProperty("line.separator") + currentLine);
                } else {
                    writer.write(currentLine);
                }
                previousLine = currentLine;
            }
            writer.close(); 
            reader.close(); 
            if (!inFile.delete()) {
                return("Could not delete original file");
            } else {
                // Rename the new file to the original filename
                if (!tempFile.renameTo(inFile)) {
                    return("Could not rename temp file");
                }
            }
            return("Se dio de baja el carro correctamente");
        } catch(IOException e) {
            return("Error");
        }
        }
    }
    /**
     * Crear un seguro <br>
     * <b>pre: </b>El hashmap de seguros ya esta inicializado<br>
     * <b>post: </b>El seguro es creado con la informacion dada
     * @param Nombre Nombre del seguro. Nombre != null && Nombre != ""
     * @param Descripcion Descripcion del seguro. Descripcion != null && Descripcion != ""
     * @param Precio Precio por dia del seguro. Precio != null
     * @throws Exception <br>
     *      1.Si ya existe un seguro con el mismo nombre
     */
    public String CrearSeguro(String Nombre,String Descripcion, Integer Precio,String filePath,Empresa empresa){
        HashMap<String,Seguro> segurosEmpresa = empresa.getSeguros();
        if (segurosEmpresa.get(Nombre)==null){
            Seguro seguro = new Seguro(Nombre,Descripcion,Precio);
        segurosEmpresa.put(Nombre, seguro);
        empresa.setSeguros(segurosEmpresa);
        try (FileWriter fw = new FileWriter(filePath, true);PrintWriter pw = new PrintWriter(fw)) {
                    String StringToWrite = ("\n" + Nombre + "," + Descripcion + "," + String.valueOf(Precio));
                    pw.print(StringToWrite);
                    return("Exitoso");
                }
                catch (IOException e) {
                    return("An error occurred: " + e.getMessage());
                }
        }
        else{return("Ya existe un seguro con el nombre dado");}

    }
    /**
     * Genera una reserva especial para un cliente interno (translado de carro)<br>
     * <b>pre: </b> Ya se reviso que la sede de inicio existe<br>
     * * <b>pre: </b> Ya se reviso que la sede de destino existe<br>
     * <b>post: </b> El carro dado es transladado de sede
     * @param carro carro a transladar de sede. carro != null
     * @param sedeInicial sede desde la cual se va a transladar el carro. sedeInicial != null
     * @param sedeFinal sede a la cual se va a transladar el carro. sedeFinal != null
     * @throws Exception <br>
     *      1.Si el carro dado no se encuentra en la sede dada
     */
    public String GenerarReservaEspecial(Carro carro,Sede sedeInicial, Sede sedeFinal){
        HashMap<String,Carro> carrosSede = sedeInicial.getCarros();
        Carro ExisteCarro = carrosSede.get(carro.getPlaca());
        if (ExisteCarro == null){
            return("No existe el carro dado en la sede de inicio");
        }
        else{
            carrosSede.remove(carro.getPlaca());
            sedeInicial.setCarros(carrosSede);
            HashMap<String,Carro> carrosSedeFinal = sedeFinal.getCarros();
            carrosSedeFinal.put(ExisteCarro.getPlaca(),ExisteCarro);
            sedeFinal.setCarros(carrosSedeFinal);
            ExisteCarro.CambiarSede(ExisteCarro,sedeFinal);
            return("Reserva exitosa");
        }
    }
    /**
     * Crear una nueva sede<br>
     * <b>pre: </b> El hashmap de sedes ya esta inicializado<br>
     * <b>post: </b> Se crea la nueva sede con la informacion dada
     * @param Nombre Nombre de la sede a crear. Nombre != null && Nombre != "" 
     * @param Direccion Direccion de la sede a crear. Direccion != null $$ != ""
     * @param Horario_ap Horario de apertura de la sede a crear. Horario != null
     * @param Horario_ci Horario de cierre de la sede a crear. Horario != null
     * @param admin Administrador local de la sede a crear. admin != null
     * @throws Exception <br>
     *      1.Ya existe una sede con el mismo nombre
     */
    public String CrearSede(String Nombre, String Direccion,LocalTime Horario_ap,LocalTime Horario_ci,Empresa empresa,String fileName){
        Sede ExisteSede = empresa.BuscarSedeNombre(Nombre);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        if (ExisteSede != null){
            return("Ya existe una sede con el nombre dado");
        }
        else{
            Sede sede = new Sede(Nombre, Direccion, Horario_ap, Horario_ci);
            HashMap<String,Sede> sedesEmpresa = empresa.getSedes();
            sedesEmpresa.put(Nombre, sede);
            empresa.setSedes(sedesEmpresa);
            try (FileWriter fw = new FileWriter(fileName, true);PrintWriter pw = new PrintWriter(fw)) {
                String FechaHoraAp = Horario_ap.format(formatter);
                String FechaHoraCi = Horario_ci.format(formatter);
                String StringToPrint = ("\n" + Nombre + "," + Direccion + "," + FechaHoraAp + "," + FechaHoraCi);
                pw.print(StringToPrint );
                return("La sede se creo exitosamente");
            }
            catch (IOException e) {
                return("An error occurred: " + e.getMessage());
            }
        }
    }
    /**
     * Modifica la informacion de una sede<br>
     * <b>pre: </b> El hashmap de sedes ya esta inicializado<br>
     * * <b>pre: </br> Ya se reviso que la sede existe <br>
     * <b>post:</b> Se modifica la informacion de la sede dada
     * @param sede sede a la cual se le va a modificar la informacion. sede != null
     * @param NuevoNombre nuevo nombre de la sede. NuevoNombre != null && NuevoNombre != ""
     * @param NuevaDir nueva direccion de la sede. NuevaDir != null && NuevaDir != ""
     * @param NuevoHorarioap Nuevo horario de apertura de la sede. NuevoHorarioap != null
     * @param NuevoHorarioCi Nuevo horario de cierre de la sede. NuevoHorarioCi != null
     * @throws Exception <br>
     */
    public void ModificarSede(Sede sede, String NuevoNombre,String NuevaDir, LocalTime NuevoHorarioap, LocalTime NuevoHorarioCi){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String lineToModify = sede.getNombre() + "," + sede.getDireccion() + "," + sede.getHoraApertura().format(formatter) + "," + sede.getHoraCierre().format(formatter);
        String newLine = NuevoNombre + "," + NuevaDir + "," + NuevoHorarioap.format(formatter) + "," + NuevoHorarioCi.format(formatter);
        Sede sedeTemp = new Sede(NuevoNombre,NuevaDir,NuevoHorarioap,NuevoHorarioCi);
        HashMap<String,Carro> carrosSede = sede.getCarros();
            for (Carro carro:carrosSede.values()){
                carro.CambiarSede(carro, sedeTemp);
            }
        sede.setNombre(NuevoNombre);
        sede.setDireccion(NuevaDir);
        sede.setHoraApertura(NuevoHorarioap);
        sede.setHoraCierre(NuevoHorarioCi);
        String path = Paths.get("Data").toAbsolutePath().toString();
        String InputFile = path + "/Sedes.csv";
        String OutputFile = path + "/temp.csv";
        
        try{
            File inFile = new File(InputFile);
            File tempFile = new File(OutputFile);
                
            BufferedReader reader = new BufferedReader(new FileReader(inFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
                
            String currentLine;
            String previousLine = null;
            while((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if(trimmedLine.equals(lineToModify)) {
                    currentLine = newLine;
                }
                if (previousLine != null) {
                    writer.write(System.getProperty("line.separator") + currentLine);
                } else {
                    writer.write(currentLine);
                }
                previousLine = currentLine;
            }
            writer.close(); 
            reader.close();
            if (!inFile.delete()) {
                System.out.println("Could not delete original file");
            } else {
                // Rename the new file to the original filename
                if (!tempFile.renameTo(inFile)) {
                    System.out.println("Could not rename temp file");
                }
            }
            
            System.out.println("Se modifico la informacion de la sede de manera correcta");
        }catch(IOException e){e.printStackTrace();}

    }
    /**
     * Genera el log de un carro<br>
     * <b>pre: </br> El atributo de Log esta inicializado para el carro <br>
     * <b>pre: </br> Ya se reviso que el carro existe <br>
     * <b>post: </b> Se genera un arhivo con el log (historial) del carro dado
     * @param carro carro del cual se quiere generar el log. carro != null
     * @throws Exception <br>
     */
    public File GenerarLogCarro(Carro carro){
        File file = new File("/usuarios");
        return file;
    }
    /**
     * Crea un administrador local para una sede<br>
     * <b>pre: </b> Ya se verifico que la sede existe <br>
     * <b>post: </b> Se crea un administrador local para la sede dada
     * @param Usuario Usuario del administrador local. Usuario != null && Usuario != ""
     * @param Clave Clave del administrador local. Clave != null && Clave != ""
     * @param sede sede del administrador local. sede != null
     */
    public String CrearAdminLocal(String Usuario, String Clave, Sede sede,Empresa empresa,String fileName){
        AdministradorLocal ExisteAdminLocal = sede.getAdminLocal();
        HashMap<String, AdministradorLocal> adminsEmpresa = empresa.getAdminsLocales();
        if(ExisteAdminLocal != null){
            return("Ya existe un administrador local para la sede dada");
        }
        else{
            if (adminsEmpresa.containsKey(Usuario)){
                return("Ya existe un administrador con el usuario dado");
            }
            else{
                AdministradorLocal administradorLocal = new AdministradorLocal(Usuario, Clave, sede);
                sede.setAdminLocal(administradorLocal);
                adminsEmpresa.put(Usuario, administradorLocal);
                try (FileWriter fw = new FileWriter(fileName, true);PrintWriter pw = new PrintWriter(fw)) {
                String StringToPrint = ("\n" + Usuario + "," + Clave + "," + sede.getNombre());
                pw.print(StringToPrint );
                return("El administrador local fue creado con exito");
            }
            catch (IOException e) {
                return("An error occurred: " + e.getMessage());
            }

            }
        }
        
    }
    public String CrearCategoria(String nombreCategoria,long tarifaTempAlta, long tarifaTempBaja,Empresa empresa,String fileName){
        if (empresa.BuscarCategoriaNombre(nombreCategoria) != null){
            return("Ya existe una categoria con el nombre dado");

        }
        else{
            Categoria categoria = new Categoria(nombreCategoria, tarifaTempAlta, tarifaTempBaja);
            HashMap<String,Categoria> categoriasEmpresa = empresa.getCategorias();
            categoriasEmpresa.put(nombreCategoria, categoria);
            empresa.setCategorias(categoriasEmpresa);
            try (FileWriter fw = new FileWriter(fileName, true);PrintWriter pw = new PrintWriter(fw)) {
                String StringToPrint = ("\n" + nombreCategoria + "," + String.valueOf(tarifaTempAlta) + "," + String.valueOf(tarifaTempBaja));
                pw.print(StringToPrint );
                return("La categoria fue creada con exito");
            }
            catch (IOException e) {
                return("An error occurred: " + e.getMessage());
            }

        }
    }

    public String getUsuario() {
        return Usuario;
    }
    public String getClave() {
        return Clave;
    }
    
}
