package gestioninformacionproyectos.Model;

/**
 *
 * @author COMPUFACIL03
 */
public class Project {
    
    int code;
    String title,startDate,endDate;
    double cost;

    public Project(int code, String title, String startDate, String endDate, double cost) {
        this.code = code;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cost = cost;
    }

    public Project() {}

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
    
}
