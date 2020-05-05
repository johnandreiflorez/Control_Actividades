package gestioninformacionproyectos.Configuration;
import java.sql.*;

public class Conexion {
    ResultSet result;
    Statement statement;
    Connection connection;
    
    public Conexion(){
        result = null;
        statement = null;
        connection = null;
    }
    
    public ResultSet ejecutarSelect(String sentence){
        try{
            statement = connection.createStatement();
            result = statement.executeQuery(sentence);
        }catch(SQLException exp){
            System.out.println(exp.getMessage());	
	}
        return result;
    }
    
    public void ejecutarUpdate(String sentence){
        try{
            statement = connection.createStatement();
            statement.executeUpdate(sentence);
        }catch(SQLException exp){
            System.out.println(exp.getMessage());
        }
    }
    public void abriBD(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/dbproyectofinal", "root", "");
	}catch(SQLException exp){
            System.out.println("ERROR: " + exp.getMessage());
	}catch(Exception exp){
            System.out.println("ERROR: " + exp.getMessage());
	}
    }
    public void cerrarBd(){
	try{
            connection.close(); //Cerrar Conexi�n
	}catch(SQLException exp){
            System.out.println(exp.getMessage());
	}catch(Exception exp){
            System.out.println(exp.getMessage());
	}
    }
}
