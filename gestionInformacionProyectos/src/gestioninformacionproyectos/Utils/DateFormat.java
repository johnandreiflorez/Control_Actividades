package gestioninformacionproyectos.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author srestrepo
 */
public class DateFormat {
    
    String dateError = "";

    public String castDateToString(Date date) {
        java.sql.Date newDate;
        newDate = new java.sql.Date(date.getTime());
        return newDate.toString();
    }
    
    public boolean compareTwoDates(String startDate, String endDate) {
        boolean validate = false;
        if(startDate.compareTo(endDate) > 0) {
           this.dateError = "Fecha inicial es mayor a la fecha final.";
        } else if(startDate.compareTo(endDate) < 0) {
           System.out.println("Fecha inicial es menor a la fecha final");
           validate = true;
        } else if(startDate.compareTo(endDate) == 0) {
           this.dateError = "Ambas fechas son iguales.";
           validate = false;
        }
        return validate;
    }
    
    public Date stringToDate(String date) {
        Date newDate = null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            newDate = format.parse(date);
            System.out.println(newDate);
        } catch (ParseException ex) {
            Logger.getLogger(DateFormat.class.getName()).log(Level.SEVERE, null, ex);
        }
        return newDate;
    }

    public String getDateError() {
        return dateError;
    }

    public void setDateError(String dateError) {
        this.dateError = dateError;
    }
    
    
}
