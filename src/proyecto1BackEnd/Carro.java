package proyecto1BackEnd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Carro {

    //Atributes
    private String Placa;
    private String Transmision;
    private String Color;
    private String Marca;
    private String Modelo;
    private ArrayList<String> log;
    private int Capacidad;
    private Estado estado;
    private Categoria categoria;
    private Sede sede;
    //Constructor
    public Carro(String Placa, String Transmision, String Color, String Marca, String Modelo, int Capacidad,Estado estado, Categoria categoria, Sede sede) {
        this.Placa = Placa;
        this.Transmision = Transmision;
        this.Color = Color;
        this.Marca = Marca;
        this.Modelo = Modelo;
        this.Capacidad = Capacidad;
        this.estado = estado;
        this.categoria = categoria;
        this.sede = sede;
    }
    //Metodos
    public void CambiarEstado (Carro carro,Estado estado){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String path = Paths.get("Data").toAbsolutePath().toString();
        String newLine = "";
                String InputFile = path + "/Carros.csv";
                String OutputFile = path + "/temp.csv";
                //gets the line to replace
                String placa = carro.getPlaca();
                String transmision = carro.getTransmision();
                String color = carro.getColor();
                String marca = carro.getMarca();
                String modelo = carro.getModelo();
                String capacidad = Integer.toString(carro.getCapacidad());
                String nombreEstado = carro.getEstado().getNombre();
                LocalDateTime fechaInicio = carro.getEstado().getFechaInicio();
                LocalDateTime fechaFinal = carro.getEstado().getFechaDisponibilidad();
                String nombreCategoria = carro.getCategoria().getNombre();
                String nombreSede = carro.getSede().getNombre();
                StringBuilder sb = new StringBuilder(placa);
                sb.append(",");
                sb.append(transmision);
                sb.append(",");
                sb.append(color);
                sb.append(",");
                sb.append(marca);
                sb.append(",");
                sb.append(modelo);
                sb.append(",");
                sb.append(capacidad);
                sb.append(",");
                sb.append(nombreEstado);
                sb.append(",");
                if (fechaInicio != null){sb.append(fechaInicio.format(formatter));}
                sb.append(",");
                if(fechaFinal!=null){sb.append(fechaFinal.format(formatter));}
                sb.append(",");
                sb.append(nombreCategoria);
                sb.append(",");
                sb.append(nombreSede);
                String lineToModify = sb.toString();
                if(estado.getFechaInicio() == null){newLine = (placa + "," + transmision + "," + color + "," + marca + "," + modelo + "," + capacidad + "," + estado.getNombre() + ","  + "," + "," + nombreCategoria + "," + nombreSede);
}
                else{
                    newLine = (placa + "," + transmision + "," + color + "," + marca + "," + modelo + "," + capacidad + "," + estado.getNombre() + "," + estado.getFechaInicio().format(formatter) + "," + estado.getFechaDisponibilidad().format(formatter) + "," + nombreCategoria + "," + nombreSede);
                }
                try {
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
                carro.setEstado(estado);
            }catch (IOException e) {
                e.printStackTrace();
            }
    }
    public void CambiarSede (Carro carro, Sede sede){
        String newLine = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String path = Paths.get("Data").toAbsolutePath().toString();
                String InputFile = path + "/Carros.csv";
                String OutputFile = path + "/temp.csv";
                //gets the line to replace
                String placa = carro.getPlaca();
                String transmision = carro.getTransmision();
                String color = carro.getColor();
                String marca = carro.getMarca();
                String modelo = carro.getModelo();
                String capacidad = Integer.toString(carro.getCapacidad());
                String nombreEstado = carro.getEstado().getNombre();
                LocalDateTime fechaInicio = carro.getEstado().getFechaInicio();
                LocalDateTime fechaFinal = carro.getEstado().getFechaDisponibilidad();
                String nombreCategoria = carro.getCategoria().getNombre();
                String nombreSede = carro.getSede().getNombre();
                StringBuilder sb = new StringBuilder(placa);
                sb.append(",");
                sb.append(transmision);
                sb.append(",");
                sb.append(color);
                sb.append(",");
                sb.append(marca);
                sb.append(",");
                sb.append(modelo);
                sb.append(",");
                sb.append(capacidad);
                sb.append(",");
                sb.append(nombreEstado);
                sb.append(",");
                if (fechaInicio != null){sb.append(fechaInicio.format(formatter));}
                sb.append(",");
                if(fechaFinal!=null){sb.append(fechaFinal.format(formatter));}
                sb.append(",");
                sb.append(nombreCategoria);
                sb.append(",");
                sb.append(nombreSede);
                String lineToModify = sb.toString();
                if (nombreEstado.equals("Disponible")){
                    newLine = (placa + "," + transmision + "," + color + "," + marca + "," + modelo + "," + capacidad + "," + nombreEstado + ","  + "," + "," + nombreCategoria + "," + sede.getNombre());
                }
                else{
                    newLine = (placa + "," + transmision + "," + color + "," + marca + "," + modelo + "," + capacidad + "," + nombreEstado + "," + fechaInicio.format(formatter) + "," + fechaFinal.format(formatter) + "," + nombreCategoria + "," + sede.getNombre());
                }
                
                try {
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
                carro.setSede(sede);
            }catch (IOException e) {
                e.printStackTrace();
            }
    }
    public String getPlaca() {
        return Placa;
    }
    public String getTransmision() {
        return Transmision;
    }
    public String getColor() {
        return Color;
    }
    public String getMarca() {
        return Marca;
    }
    public String getModelo() {
        return Modelo;
    }
    public ArrayList<String> getLog() {
        return log;
    }
    public int getCapacidad() {
        return Capacidad;
    }
    public Estado getEstado() {
        return estado;
    }
    public Categoria getCategoria() {
        return categoria;
    }
    public Sede getSede() {
        return sede;
    }
    public void setPlaca(String placa) {
        Placa = placa;
    }
    public void setTransmision(String transmision) {
        Transmision = transmision;
    }
    public void setColor(String color) {
        Color = color;
    }
    public void setMarca(String marca) {
        Marca = marca;
    }
    public void setModelo(String modelo) {
        Modelo = modelo;
    }
    public void setLog(ArrayList<String> log) {
        this.log = log;
    }
    public void setCapacidad(int capacidad) {
        Capacidad = capacidad;
    }
    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public void setSede(Sede sede) {
        this.sede = sede;
    }
    
    
}
