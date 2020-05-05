package gestioninformacionproyectos.Model;

/**
 *
 * @author COMPUFACIL03
 */
public class Owner {
    
    String code, name, numberPhone, numberCelPhone;

    public Owner(String code, String name, String numberPhone, String numberCelPhone) {
        this.code = code;
        this.name = name;
        this.numberPhone = numberPhone;
        this.numberCelPhone = numberCelPhone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getNumberCelPhone() {
        return numberCelPhone;
    }

    public void setNumberCelPhone(String numberCelPhone) {
        this.numberCelPhone = numberCelPhone;
    }
    
}
