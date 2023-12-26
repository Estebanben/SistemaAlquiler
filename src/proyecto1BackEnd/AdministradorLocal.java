package proyecto1BackEnd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.HashMap;

public class AdministradorLocal {
    
    //Atributes
    private String Usuario;
    private String Clave;
    private Sede sede;
    //Constructor
    public AdministradorLocal(String Usuario, String Clave, Sede sede) {
        this.Usuario = Usuario;
        this.Clave = Clave;
        this.sede = sede;
    }
    //Metodos
    /**
     * Crea un nuevo empleado para la sede del Administrador local
     * <b>pre: </b> Ya se reviso que la sede exista<br>
     * <b>post: </b> Se crea un empleado con los datos dados para la sede dada
     * @param Usuario Usuario del empleado. Usuario != null && Usuario != ""
     * @param Clave Clave del empleado. Clave != null && Clave != ""
     * @param sede sede del empleado. sede != null
     * @throws Exception <br>
     *      1.Si existe un empleado con el mismo usuario
     */
    public String CrearEmpleado(String Usuario, String Clave, Sede sede, Empresa empresa, String filePath){
        HashMap<String,Empleado> empleadosEmpresa = empresa.getEmpleados();
        if (empleadosEmpresa.get(Usuario) != null){return("Ya existe un empleado con el usuario dado");}
        else{
        Empleado empleado = new Empleado(Usuario, Clave, sede);
        HashMap<String,Empleado> empleadosSede = sede.getEmpleados();
        empleadosEmpresa.put(Usuario, empleado);
        empleadosSede.put(Usuario, empleado);
        sede.setEmpleados(empleadosSede);
        empresa.setEmpleados(empleadosSede);
        try (FileWriter fw = new FileWriter(filePath, true);PrintWriter pw = new PrintWriter(fw)) {
                    String StringToWrite = ("\n" + Usuario + "," + Clave + "," + sede.getNombre());
                    pw.print(StringToWrite);
                    return("Empleado creado correctamente");
                }
        catch (IOException e) {
            return("An error occurred: " + e.getMessage());
            }
        }

        
    }
    /**
     * Elimina un empleado de la empresa
     * <b>pre: </b> El HashMap de empleados esta inicilizado <br>
     * <b>post: </b> Se elimina el empleado dado
     * @param Usuario usuario del empleado a eliminar. Usuario != null && Usuario != ""
     * @param empresa empresa de donde se desea eliminar el empleado. empresa != null
     * @throws Exception <br>
     */
    public String EliminarEmpleado(String Usuario, Empresa empresa,Sede sede){
        Empleado existeEmpleado = sede.getEmpleados().get(Usuario);
            String path = Paths.get("Data").toAbsolutePath().toString();
        String inputFile = path + "/Empleados.csv";
        String outputFile = path + "/temp.csv";
        if (existeEmpleado == null){return("No existe ningun empleado con usuario dado");}
        else{
            HashMap<String,Empleado> empleadosEmpresa = empresa.getEmpleados();
            empleadosEmpresa.remove(Usuario);
            empresa.setEmpleados(empleadosEmpresa);
            HashMap<String,Empleado> empleadosSede = sede.getEmpleados();
            empleadosSede.remove(Usuario);
            sede.setEmpleados(empleadosSede);
            String lineToRemove = Usuario + "," + existeEmpleado.getClave() + "," + sede.getNombre();
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
            return("Se elimino el empleado de manera correcta");
        } catch(IOException e) {
            return("Error");
        }
        }
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
