package gestioninformacionproyectos.Controller;

import gestioninformacionproyectos.Configuration.Conexion;
import gestioninformacionproyectos.Model.ActivitiesXProjects_owner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author jaflorez
 */
public class ActivitiesXProjects_ownerController {

    Conexion conexion;
    ActivitiesXProjects_owner apo;
    String table = "resp_act_proy";

    public ActivitiesXProjects_ownerController(ActivitiesXProjects_owner apo) {
        this.apo = apo;
    }
    
    public boolean validate(String codresp, int codactiv, int codproy){
        boolean validate = false;
        String SQL = "SELECT * FROM " + this.table + " WHERE codresp = '" + codresp + 
                    "' AND codactiv = " + codactiv + " AND codproy = " + codproy;
        this.conexion = new Conexion();
        this.conexion.abriBD();
        ResultSet resultSet = this.conexion.ejecutarSelect(SQL);
        try {
            if(resultSet.next()){
                System.out.println("APO existe");
                validate = true;
            }else{
                System.out.println("APO no existe");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.conexion.cerrarBd();
        return validate;
    }
    
    public void createGestionProyecto(){
        String SQL = "INSERT INTO " + this.table + " (codresp, codactiv, codproy, fecha_asig) " +
                    "VALUES('" + this.apo.getCodeOwner()+ "', " + this.apo.getCodeActivity() +
                    ", " + this.apo.getCodeProject() + ", '" + this.apo.getAssignmentDate() + "')";
        this.conexion = new Conexion();
        this.conexion.abriBD();
        this.conexion.ejecutarUpdate(SQL);
        this.conexion.cerrarBd();
    }
    
    public void updateGestionProyecto(String codresp, int codactiv, int codproy) {
        String SQL = "UPDATE " + this.table + " SET codresp = '" + this.apo.getCodeOwner() + "', " +
                    "codactiv = " + this.apo.getCodeActivity() + ", " +
                    "codproy = " + this.apo.getCodeProject()+ ", " +
                    "fecha_asig = '" + this.apo.getAssignmentDate() + "' " + 
                    "WHERE codresp = '" + codresp + "' AND codactiv = " + codactiv + " AND codproy = " + codproy;
        this.conexion = new Conexion();
        this.conexion.abriBD();
        this.conexion.ejecutarUpdate(SQL);
        this.conexion.cerrarBd();
    }
    
    public void deleteGestionProyecto(String codresp, int codactiv, int codproy) {
        String SQL = "DELETE FROM " + this.table + " WHERE codresp = '" + codresp + 
                    "' AND codactiv = " + codactiv + " AND codproy = " + codproy;
        this.conexion = new Conexion();
        this.conexion.abriBD();
        this.conexion.ejecutarUpdate(SQL);
        this.conexion.cerrarBd();
    }
    
    public ActivitiesXProjects_owner consultGestionProyecto(String codresp, int codactiv, int codproy){
        String SQL = "CALL Consulta_x_Registro(" + codresp + ", " + codactiv + ", " + codproy + ")";
        this.conexion = new Conexion();
        this.conexion.abriBD();
        ResultSet resultSet = this.conexion.ejecutarSelect(SQL);
        try {
            if(resultSet.next()) {
                this.apo.setCodeOwner(resultSet.getString("COD_RESP"));
                this.apo.setNameOwner(resultSet.getString("NOMBRE_RESP"));
                this.apo.setCodeActivity(resultSet.getInt("COD_ACTIV"));
                this.apo.setNameActiv(resultSet.getString("NOMBRE_ACTIV"));
                this.apo.setCodeProject(resultSet.getInt("COD_PROY"));
                this.apo.setNameProy(resultSet.getString("NOMBRE_PROY"));
                this.apo.setAssignmentDate(resultSet.getString("FECHA_ASIG"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.conexion.cerrarBd();
        return this.apo;
    }
    
    public String[][] consultAllGestionProyecto() {
        String[][] matriz = null;
        String SQL = "CALL Consulta_Registros()";
        this.conexion = new Conexion();
        this.conexion.abriBD();
        ResultSet resultSet = this.conexion.ejecutarSelect(SQL);
        try {
            int i = 0;
            matriz = new String[countData(this.table)][7];
            while (resultSet.next()) {                
                matriz[i][0] = resultSet.getString("COD_RESP");
                matriz[i][1] = resultSet.getString("NOMBRE_RESP");
                matriz[i][2] = Integer.toString(resultSet.getInt("COD_ACTIV"));
                matriz[i][3] = resultSet.getString("NOMBRE_ACTIV"); 
                matriz[i][4] = Integer.toString(resultSet.getInt("COD_PROY"));
                matriz[i][5] = resultSet.getString("NOMBRE_PROY"); 
                matriz[i][6] = resultSet.getString("FECHA_ASIG"); 
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProjectController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.conexion.cerrarBd();
        return matriz;
    }
    
    public String[] getDataCombo(String column, String tableName){
        String[] arrayOwners = null;
        String SQL = "SELECT CONCAT(codigo, '-', " + column + ") FROM " + tableName;
        this.conexion = new Conexion();
        this.conexion.abriBD();
        ResultSet resultSet = this.conexion.ejecutarSelect(SQL);
        int i = 0;
        arrayOwners = new String[countData(tableName)];
        try {
            while (resultSet.next()) {
                arrayOwners[i] = resultSet.getString(1);
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ActivitiesXProjects_ownerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrayOwners;
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
    
//    public void guardar(){
//        String assignmentDate,codeOwner;
//        int codeActivity, codeProject;
//        assignmentDate = objRelation.getAssignmentDate();
//        codeOwner = objRelation.getCodeOwner();
//        codeActivity = objRelation.getCodeActivity();
//        codeProject = objRelation.getCodeProject();
//        if(validationDates()){
//            String cmdSql="INSERT INTO resp_act_proy VALUES('"+codeOwner+"','"+codeActivity+"','"+codeProject+"',"+assignmentDate+")";
//                                    
//            Conexion objConexion= new Conexion();
//            objConexion.abriBD();
//            objConexion.ejecutarUpdate(cmdSql);
//            objConexion.cerrarBd();
//        }else
//            JOptionPane.showMessageDialog(null, "ERROR, LA FECHA DE LA ACTIVIDAD ES SUPERIOR AL FIN DEL PROYECTO");
//   }
//   public boolean validationDates(){
//       
//       ResultSet objResultSet;
//       int codeActivity = objRelation.getCodeActivity();
//       String endDate="",dateEND="";
//       String cmdSql = "SELECT * FROM actividad WHERE codigo='"+codeActivity+"'";
//       Conexion objConexion= new Conexion();
//       objConexion.abriBD();
//       objResultSet=objConexion.ejecutarSelect(cmdSql);
//       try{
//        if(objResultSet.next()){
//                endDate=objResultSet.getString("fechafin"); 
//            }
//       objConexion.cerrarBd();
//       }
//       catch(Exception objExp){
//           System.out.println(objExp.getMessage());
//       }
//       int codeProject = objRelation.getCodeProject();
//       cmdSql = "SELECT * FROM proyecto WHERE codigo='"+codeProject+"'";
//       objConexion.abriBD();
//       objResultSet=objConexion.ejecutarSelect(cmdSql);
//       try{
//        if(objResultSet.next()){
//                dateEND=objResultSet.getString("fechafin"); 
//            }
//       objConexion.cerrarBd();
//       }
//       catch(Exception objExp){
//           System.out.println(objExp.getMessage());
//       }
//       int date_1=Integer.parseInt(endDate.split("-")[0]);
//       int date_2 =Integer.parseInt(dateEND.split("-")[0]);
//       if(date_1>date_2)
//           return false;
//       else {
//           if(date_1==date_2){
//               date_1=Integer.parseInt(endDate.split("-")[1]);
//                date_2 =Integer.parseInt(dateEND.split("-")[1]);
//               if(date_1>date_2)
//                   return false;
//                else
//                   if(date_1==date_2){
//                       date_1=Integer.parseInt(endDate.split("-")[2]);
//                        date_2 =Integer.parseInt(dateEND.split("-")[2]);
//                       if(date_1>date_2)
//                            return false;
//                   }
//           }
//       }
//       return true;
//   }
//   public void modificar(){
//        String assignmentDate,codeOwner;
//        int codeActivity, codeProject;
//        assignmentDate = objRelation.getAssignmentDate();
//        codeOwner = objRelation.getCodeOwner();
//        codeActivity = objRelation.getCodeActivity();
//        codeProject = objRelation.getCodeProject();
//       String cmdSql="UPDATE resp_act_proy SET fecha_asig='"+assignmentDate+"',codactiv="+codeActivity+
//                      "',codproy="+codeProject+" WHERE codresp='"+codeOwner+"'";
//       Conexion objConexion= new Conexion();
//       objConexion.abriBD();
//       objConexion.ejecutarUpdate(cmdSql);
//       objConexion.cerrarBd();
//   }  
//   public void borrar(){
//       String codeOwner;
//       codeOwner = objRelation.getCodeOwner();
//       String cmdSql="DELETE FROM resp_act_proy WHERE codresp='"+codeOwner+"'";
//       Conexion objConexion= new Conexion();
//       objConexion.abriBD();
//       objConexion.ejecutarUpdate(cmdSql);
//       objConexion.cerrarBd();
//   }  
//   public ActivitiesXProjects_owner consultar(){
//       ResultSet objResultSet;
//       String code;
//       code = objRelation.getCodeOwner();
//       String cmdSql = "SELECT * FROM resp_act_proy WHERE codresp='"+code+"'";
//       Conexion objConexion= new Conexion();
//       objConexion.abriBD();
//       objResultSet=objConexion.ejecutarSelect(cmdSql);
//       try{
//        if(objResultSet.next()){
//           int codActivity=objResultSet.getInt("codactiv");
//           int codeProject=objResultSet.getInt("codproy");
//           String assignmentDate=objResultSet.getString("fecha_asig");
//           objRelation.setAssignmentDate(assignmentDate);
//           objRelation.setCodeActivity(codActivity);
//           objRelation.setCodeProject(codeProject);
//        }
//       objConexion.cerrarBd();
//       }
//       catch(Exception objExp){
//           System.out.println(objExp.getMessage());
//       }
//       return objRelation;
//   }
//    public String[][] list(){
//        String[][] mat=null;
//        ResultSet objResultSet;
//        Conexion objConexion= new Conexion();
//        try{
//        String cmdSql="SELECT count(*) FROM resp_act_proy";
//       objConexion.abriBD();
//       objResultSet=objConexion.ejecutarSelect(cmdSql);
//       //objResultSet apunta al encabezado de la tabla resultado de la consulta
//       int n= 0;
//       if(objResultSet.next())n= objResultSet.getInt(1);
//
//       cmdSql="SELECT * FROM resp_act_proy";
//        mat=new String[n][4];
//        objResultSet=objConexion.ejecutarSelect(cmdSql);
//        int i=0;
//        while(objResultSet.next()){
//            mat[i][0]=Integer.toString(objResultSet.getInt("codresp"));
//            mat[i][1]=objResultSet.getString("codactiv");
//            mat[i][2]=objResultSet.getString("codproy");
//            mat[i][3]=objResultSet.getString("fecha_asig");
//            i++;
//        }
//       }
//       catch(Exception objExp){
//           System.out.println(objExp.getMessage());
//       }
//       objConexion.cerrarBd();
//       return mat;
//    }
}
