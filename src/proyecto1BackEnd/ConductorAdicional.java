package proyecto1BackEnd;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class ConductorAdicional {
    
    //Atributes
    private String Nombre;
    private String Telefono;
    private Licencia licencia;
    //Constructor
    public ConductorAdicional(String Nombre, String Telefono, Licencia licencia) {
        this.Nombre = Nombre;
        this.Telefono = Telefono;
        this.licencia = licencia;
    }
    //Metodos
    public String getNombre() {
        return Nombre;
    }
    public String getTelefono() {
        return Telefono;
    }
    public Licencia getLicencia() {
        return licencia;
    }
    public void AniadirConductorAd (ConductorAdicional conductorAd,String filePath,Empresa empresa){
        HashMap<String,ConductorAdicional> conductoresAdEmpresa = empresa.getConductoresAdicionales();
        conductoresAdEmpresa.put(conductorAd.getLicencia().getNumeroLicencia(),conductorAd);
        empresa.setConductoresAdicionales(conductoresAdEmpresa);
        try (FileWriter fw = new FileWriter(filePath, true);PrintWriter pw = new PrintWriter(fw)) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    pw.print( "\n" + conductorAd.getNombre() + "," + conductorAd.getTelefono() + "," + conductorAd.getLicencia().getNumeroLicencia() + "," + conductorAd.getLicencia().getPaisExpLicencia() + "," + conductorAd.getLicencia().getFechaVencLicencia().format(formatter));
                }
                catch (IOException e) {
                    System.out.println("An error occurred: " + e.getMessage());
                }
    }

}
