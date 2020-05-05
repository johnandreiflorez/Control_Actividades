package gestioninformacionproyectos.Controller;

import gestioninformacionproyectos.Configuration.Conexion;
import gestioninformacionproyectos.Model.Activity;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author COMPUFACIL03
 */
public class ActivityController {
    
    Activity activity;
    Conexion conexion;
    String table = "actividad";

    public ActivityController(Activity objActivity) {
        this.activity = objActivity;
    }
    
    public boolean validateActivity(int code){
        boolean validate = false;
        String SQL = "SELECT * FROM " + this.table + " WHERE codigo = " + code;
        
        this.conexion = new Conexion();
        this.conexion.abriBD();
        ResultSet resultSet = this.conexion.ejecutarSelect(SQL);
        try {
            if(resultSet.next()){
                System.out.println("Actividad existe");
                validate = true;
            }else{
                System.out.println("Actividad no existe");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActivityController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.conexion.cerrarBd();
        return validate;
    }
    
    public void createActivity(){
        String SQL = "INSERT INTO " + this.table + " (codigo, descripcion, fechaini, fechafin) " +
                    "VALUES('" + this.activity.getCode() + "', '" + this.activity.getDescription() + 
                    "', '" + this.activity.getStartDate() + "', '" + this.activity.getEndDate() + "')";
        this.conexion = new Conexion();
        this.conexion.abriBD();
        this.conexion.ejecutarUpdate(SQL);
        this.conexion.cerrarBd();
    }
    
    public void updateActivity(int code) {
        String SQL = "UPDATE " + this.table + " SET descripcion = '" + this.activity.getDescription() + "', " +
                    "fechaini = '" + this.activity.getStartDate() + "', " +
                    "fechafin = '" + this.activity.getEndDate() + "' WHERE codigo = " + code;
        this.conexion = new Conexion();
        this.conexion.abriBD();
        this.conexion.ejecutarUpdate(SQL);
        this.conexion.cerrarBd();
    }
    
    public void deleteActivity(int code) {
        String SQL = "DELETE FROM " + this.table + " WHERE codigo = " + code;
        this.conexion = new Conexion();
        this.conexion.abriBD();
        this.conexion.ejecutarUpdate(SQL);
        this.conexion.cerrarBd();
    }

    public Activity consultActivity(){
        String SQL = "SELECT * FROM " + this.table + " WHERE codigo = " + this.activity.getCode();
        this.conexion = new Conexion();
        this.conexion.abriBD();
        ResultSet resultSet = this.conexion.ejecutarSelect(SQL);
        try {
            if(resultSet.next()) {
                this.activity.setDescription(resultSet.getString("descripcion"));
                this.activity.setStartDate(resultSet.getString("fechaini"));
                this.activity.setEndDate(resultSet.getString("fechafin"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActivityController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.conexion.cerrarBd();
        return this.activity;
    }
    
    public String[][] consultAllActivities() {
        String[][] matriz = null;
        String SQL = "SELECT * FROM " + this.table;
        this.conexion = new Conexion();
        this.conexion.abriBD();
        ResultSet resultSet = this.conexion.ejecutarSelect(SQL);
        try {
            int i = 0;
            matriz = new String[countDataActivities()][4];
            while (resultSet.next()) {                
                matriz[i][0] = Integer.toString(resultSet.getInt("codigo"));
                matriz[i][1] = resultSet.getString("descripcion");
                matriz[i][2] = resultSet.getString("fechaini");
                matriz[i][3] = resultSet.getString("fechafin"); 
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActivityController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.conexion.cerrarBd();
        return matriz;
    }
    
    public int countDataActivities() {
        int count = 0;
        String SQL = "SELECT COUNT(*) FROM " + this.table;
        this.conexion = new Conexion();
        this.conexion.abriBD();
        ResultSet resultSet = this.conexion.ejecutarSelect(SQL);
        try {
            if(resultSet.next()) count = resultSet.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(ActivityController.class.getName()).log(Level.SEVERE, null, ex);
        }  
        this.conexion.cerrarBd();
        return count;
    }
    
}
