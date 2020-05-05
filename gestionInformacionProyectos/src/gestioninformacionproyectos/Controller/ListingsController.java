package gestioninformacionproyectos.Controller;

import gestioninformacionproyectos.Configuration.Conexion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListingsController {
    
    Conexion conexion;
    String table = "resp_act_proy";
    
    public String[][] consultProyectWithActivity(){
        String[][] matriz = null;
        String SQL = "CALL listadoProyectosConActividades()";
        this.conexion = new Conexion();
        this.conexion.abriBD();
        ResultSet resultSet = this.conexion.ejecutarSelect(SQL);
        try {
            int i = 0;
            matriz = new String[countData(this.table)][9];
            while (resultSet.next()) {                
                matriz[i][0] = resultSet.getString("COD_PROY");
                matriz[i][1] = resultSet.getString("NOMBRE_PROY");
                matriz[i][2] = resultSet.getString("FI_PROY");
                matriz[i][3] = resultSet.getString("FF_PROY");
                matriz[i][4] = String.format("%.0f", resultSet.getDouble("COSTO"));
                matriz[i][5] = Integer.toString(resultSet.getInt("COD_ACTIV"));
                matriz[i][6] = resultSet.getString("NOMBRE_ACTIV"); 
                matriz[i][7] = resultSet.getString("FI_ACTIV"); 
                matriz[i][8] = resultSet.getString("FF_ACTIV"); 
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.conexion.cerrarBd();
        return matriz;
    }
    
    public String[][] consultOwnerWithActivity() {
        String[][] matriz = null;
        String SQL = "CALL listadoResponsablesConActividades()";
        this.conexion = new Conexion();
        this.conexion.abriBD();
        ResultSet resultSet = this.conexion.ejecutarSelect(SQL);
        try {
            int i = 0;
            matriz = new String[countData(this.table)][8];
            while (resultSet.next()) {                
                matriz[i][0] = resultSet.getString("COD_RESP");
                matriz[i][1] = resultSet.getString("NOMBRE_RESP");
                matriz[i][2] = resultSet.getString("TELFIJO");
                matriz[i][3] = resultSet.getString("TELMOVIL");
                matriz[i][4] = Integer.toString(resultSet.getInt("COD_ACTIV"));
                matriz[i][5] = resultSet.getString("NOMBRE_ACTIV"); 
                matriz[i][6] = resultSet.getString("FI_ACTIV"); 
                matriz[i][7] = resultSet.getString("FF_ACTIV"); 
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ListingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.conexion.cerrarBd();
        return matriz;
    }
    
    public int countData(String tableName) {
        int count = 0;
        String SQL = "SELECT COUNT(*) FROM " + tableName;
        this.conexion = new Conexion();
        this.conexion.abriBD();
        ResultSet resultSet = this.conexion.ejecutarSelect(SQL);
        try {
            if(resultSet.next()) count = resultSet.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(OwnerController.class.getName()).log(Level.SEVERE, null, ex);
        }  
        this.conexion.cerrarBd();
        return count;
    }

}
