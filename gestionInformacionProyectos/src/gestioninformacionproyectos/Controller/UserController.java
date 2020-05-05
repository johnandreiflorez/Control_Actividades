package gestioninformacionproyectos.Controller;

import gestioninformacionproyectos.Configuration.Conexion;
import gestioninformacionproyectos.Model.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author COMPUFACIL03
 */
public class UserController {
    
    User user;
    Conexion conexion;
    String table = "usuario";
    
    public UserController(User objUser) {
        this.user = objUser;
    }
    
    public boolean consultUser(String id) {
        boolean validate = false;
        String SQL = "SELECT * FROM " + this.table + " WHERE cedula = '" + id + "'";
        
        this.conexion = new Conexion();
        this.conexion.abriBD();
        ResultSet resultSet = this.conexion.ejecutarSelect(SQL);
        try {
            if(resultSet.next()){
                System.out.println("Usuario existe");
                validate = true;
            }else{
                System.out.println("Usuario no existe");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.conexion.cerrarBd();
        return validate;
    }
    
    public void createUser(){
        String SQL = "INSERT INTO " + this.table + " (cedula, nombre, apellido, correo, contraseña)" +
                    " VALUES('" + this.user.getId() + "', '" + this.user.getName() + "', '" + this.user.getLastName() + 
                    "', '" + this.user.getEmail() + "', '" + this.user.getPassword() + "')";
        this.conexion = new Conexion();
        this.conexion.abriBD();
        this.conexion.ejecutarUpdate(SQL);
        System.out.println("Se guardo al usuario " + this.user.getName() + " en la tabla 'usuarios'");
        this.conexion.cerrarBd();
    } 

}
