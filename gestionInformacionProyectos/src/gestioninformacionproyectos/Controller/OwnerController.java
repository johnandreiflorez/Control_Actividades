package gestioninformacionproyectos.Controller;

import gestioninformacionproyectos.Configuration.Conexion;
import gestioninformacionproyectos.Model.Owner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author COMPUFACIL03
 */
public class OwnerController {
    
    Owner owner;
    Conexion conexion;
    String table = "responsable";

    public OwnerController(Owner objOwner) {
        this.owner = objOwner;
    }
    
    public boolean validateOwner(String code){
        boolean validate = false;
        String SQL = "SELECT * FROM " + this.table + " WHERE codigo = " + code;
        
        this.conexion = new Conexion();
        this.conexion.abriBD();
        ResultSet resultSet = this.conexion.ejecutarSelect(SQL);
        try {
            if(resultSet.next()){
                System.out.println("Responsable ya existe");
                validate = true;
            }else{
                System.out.println("Responsable no existe");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OwnerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.conexion.cerrarBd();
        return validate;
    }
    
    public void createOwner(){
        String SQL = "INSERT INTO " + this.table + " (codigo, nombre, telfijo, telmovil) " +
                    "VALUES('" + this.owner.getCode() + "', '" + this.owner.getName()+
                    "', '" + this.owner.getNumberPhone()+ "', '" + this.owner.getNumberCelPhone() + "')";
        this.conexion = new Conexion();
        this.conexion.abriBD();
        this.conexion.ejecutarUpdate(SQL);
        this.conexion.cerrarBd();
    }
    
    public void updateOwner(String code) {
        String SQL = "UPDATE " + this.table + " SET nombre = '" + this.owner.getName()+ "', " +
                    "telfijo = '" + this.owner.getNumberPhone()+ "', " +
                    "telmovil = '" + this.owner.getNumberCelPhone()+ "' WHERE codigo = '" + code + "'";
        this.conexion = new Conexion();
        this.conexion.abriBD();
        this.conexion.ejecutarUpdate(SQL);
        this.conexion.cerrarBd();
    }
    
    public void deleteOwner(String code) {
        String SQL = "DELETE FROM " + this.table + " WHERE codigo = '" + code + "'";
        this.conexion = new Conexion();
        this.conexion.abriBD();
        this.conexion.ejecutarUpdate(SQL);
        this.conexion.cerrarBd();
    }
    
    public Owner consultOwner(){
        String SQL = "SELECT * FROM " + this.table + " WHERE codigo = '" + this.owner.getCode() + "'";
        this.conexion = new Conexion();
        this.conexion.abriBD();
        ResultSet resultSet = this.conexion.ejecutarSelect(SQL);
        try {
            if(resultSet.next()) {
                this.owner.setName(resultSet.getString("nombre"));
                this.owner.setNumberPhone(resultSet.getString("telfijo"));
                this.owner.setNumberCelPhone(resultSet.getString("telmovil"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(OwnerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.conexion.cerrarBd();
        return this.owner;
    }
    
    public String[][] consultAllOwners() {
        String[][] matriz = null;
        String SQL = "SELECT * FROM " + this.table;
        this.conexion = new Conexion();
        this.conexion.abriBD();
        ResultSet resultSet = this.conexion.ejecutarSelect(SQL);
        try {
            int i = 0;
            matriz = new String[countDataOwner()][4];
            while (resultSet.next()) {                
                matriz[i][0] = resultSet.getString("codigo");
                matriz[i][1] = resultSet.getString("nombre");
                matriz[i][2] = resultSet.getString("telfijo");
                matriz[i][3] = resultSet.getString("telmovil"); 
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OwnerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.conexion.cerrarBd();
        return matriz;
    }
    
    public int countDataOwner() {
        int count = 0;
        String SQL = "SELECT COUNT(*) FROM " + this.table;
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
