package gestioninformacionproyectos.Controller;

import gestioninformacionproyectos.Configuration.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author srestrepo
 */
public class LoginController {
    
    Conexion conexion;
    String table = "usuario";
    SessionData sessionService = new SessionData();
    
    public boolean logIn(String email, String password) {
        boolean validate = false;
        String SQL = "SELECT * FROM " + this.table + " WHERE correo = '" + email + 
                "' AND contraseña = '" + password + "'";
        this.conexion = new Conexion();
        this.conexion.abriBD();
        ResultSet resultSet = this.conexion.ejecutarSelect(SQL);
        try {
            if(resultSet.next()){
                validate = true;
                this.sessionService.setId(resultSet.getString("cedula"));
                this.sessionService.setName(resultSet.getString("nombre"));
                this.sessionService.setLastName(resultSet.getString("apellido"));
                this.sessionService.setEmail(resultSet.getString("correo"));
                this.sessionService.setOpenSession(validate);
                System.out.println("Usuario existe, Logeo correcto");
            }else{
                System.out.println("Usuario y/o contraseña invalidos");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.conexion.cerrarBd();
        return validate;
    }

    public SessionData getSessionService() {
        return sessionService;
    }

    private void setSessionService(SessionData sessionService) {
        this.sessionService = sessionService;
    }
//    
}
