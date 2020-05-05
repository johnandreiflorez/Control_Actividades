package gestioninformacionproyectos.Model;

/**
 *
 * @author COMPUFACIL03
 */
public class Activity {

    int code;
    String description, startDate, endDate;

    public Activity(int code, String description, String startDate, String endDate) {
        this.code = code;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
}
