package gestioninformacionproyectos.Configuration;

/**
 *
 * @author srestrepo
 */
public class SessionData {
    
    String id;
    String name;
    String lastName;
    String email;
    boolean openSession = false;

    public boolean isOpenSession() {
        return openSession;
    }

    public void setOpenSession(boolean openSession) {
        this.openSession = openSession;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
