package gestioninformacionproyectos.Controller;

import gestioninformacionproyectos.Model.Project;
import gestioninformacionproyectos.Configuration.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author COMPUFACIL03
 */
public class ProjectController {
    
    Project project;
    Conexion conexion;
    String table = "proyecto";

    public ProjectController(Project objProject) {
        this.project = objProject;
    }
    
    public boolean validateProject(int code){
        boolean validate = false;
        String SQL = "SELECT * FROM " + this.table + " WHERE codigo = " + code;
        
        this.conexion = new Conexion();
        this.conexion.abriBD();
        ResultSet resultSet = this.conexion.ejecutarSelect(SQL);
        try {
            if(resultSet.next()){
                System.out.println("Proyecto existe");
                validate = true;
            }else{
                System.out.println("Proyecto no existe");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.conexion.cerrarBd();
        return validate;
    }
    
    public void createProyect(){
        String SQL = "INSERT INTO " + this.table + " (codigo, titulo, fechaini, fechafin, costo) " +
                    "VALUES(" + this.project.getCode() + ", '" + this.project.getTitle() +
                    "', '" + this.project.getStartDate() + "', '" + this.project.getEndDate() + 
                    "', " + this.project.getCost() + ")";
        this.conexion = new Conexion();
        this.conexion.abriBD();
        this.conexion.ejecutarUpdate(SQL);
        this.conexion.cerrarBd();
    }
    
    public void updateProject(int code) {
        String SQL = "UPDATE " + this.table + " SET titulo = '" + this.project.getTitle()+ "', " +
                    "fechaini = '" + this.project.getStartDate() + "', " +
                    "fechafin = '" + this.project.getEndDate() + "', " +
                    "costo = " + this.project.getCost() + " WHERE codigo = " + code;
        this.conexion = new Conexion();
        this.conexion.abriBD();
        this.conexion.ejecutarUpdate(SQL);
        this.conexion.cerrarBd();
    }
    
    public void deleteProject(int code) {
        String SQL = "DELETE FROM " + this.table + " WHERE codigo = " + code;
        this.conexion = new Conexion();
        this.conexion.abriBD();
        this.conexion.ejecutarUpdate(SQL);
        this.conexion.cerrarBd();
    }
    
    public Project consultProject(){
        String SQL = "SELECT * FROM " + this.table + " WHERE codigo = " + this.project.getCode();
        this.conexion = new Conexion();
        this.conexion.abriBD();
        ResultSet resultSet = this.conexion.ejecutarSelect(SQL);
        try {
            if(resultSet.next()) {
                this.project.setTitle(resultSet.getString("titulo"));
                this.project.setStartDate(resultSet.getString("fechaini"));
                this.project.setEndDate(resultSet.getString("fechafin"));
                this.project.setCost(resultSet.getDouble("costo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.conexion.cerrarBd();
        return this.project;
    }
    
    public String[][] consultAllProjects() {
        String[][] matriz = null;
        String SQL = "SELECT * FROM " + this.table;
        this.conexion = new Conexion();
        this.conexion.abriBD();
        ResultSet resultSet = this.conexion.ejecutarSelect(SQL);
        try {
            int i = 0;
            matriz = new String[countDataProject()][5];
            while (resultSet.next()) {                
                matriz[i][0] = Integer.toString(resultSet.getInt("codigo"));
                matriz[i][1] = resultSet.getString("titulo");
                matriz[i][2] = resultSet.getString("fechaini");
                matriz[i][3] = resultSet.getString("fechafin"); 
                matriz[i][4] = String.format("%.0f", resultSet.getDouble("costo"));
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.conexion.cerrarBd();
        return matriz;
    }
    
    public int countDataProject() {
        int count = 0;
        String SQL = "SELECT COUNT(*) FROM " + this.table;
        this.conexion = new Conexion();
        this.conexion.abriBD();
        ResultSet resultSet = this.conexion.ejecutarSelect(SQL);
        try {
            if(resultSet.next()) count = resultSet.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
        }  
        this.conexion.cerrarBd();
        return count;
    }
    
}
