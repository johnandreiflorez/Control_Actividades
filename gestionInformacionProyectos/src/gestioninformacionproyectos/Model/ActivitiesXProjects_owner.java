package gestioninformacionproyectos.Model;

/**
 *
 * @author jaflorez
 */
public class ActivitiesXProjects_owner {

    private String codeOwner, assignmentDate, nameOwner, nameActiv, nameProy;
    private int codeActivity, codeProject;

    public ActivitiesXProjects_owner(String codeOwner, int codeActivity, int codeProject, String assignmentDate) {
        this.codeOwner = codeOwner;
        this.codeActivity = codeActivity;
        this.codeProject = codeProject;
        this.assignmentDate = assignmentDate;
    }

    public ActivitiesXProjects_owner() {}

    public String getCodeOwner() {
        return codeOwner;
    }

    public void setCodeOwner(String codeOwner) {
        this.codeOwner = codeOwner;
    }

    public String getAssignmentDate() {
        return assignmentDate;
    }

    public void setAssignmentDate(String assignmentDate) {
        this.assignmentDate = assignmentDate;
    }

    public int getCodeActivity() {
        return codeActivity;
    }

    public void setCodeActivity(int codeActivity) {
        this.codeActivity = codeActivity;
    }

    public int getCodeProject() {
        return codeProject;
    }

    public void setCodeProject(int codeProject) {
        this.codeProject = codeProject;
    }

    public String getNameOwner() {
        return nameOwner;
    }

    public void setNameOwner(String nameOwner) {
        this.nameOwner = nameOwner;
    }

    public String getNameActiv() {
        return nameActiv;
    }

    public void setNameActiv(String nameActiv) {
        this.nameActiv = nameActiv;
    }

    public String getNameProy() {
        return nameProy;
    }

    public void setNameProy(String nameProy) {
        this.nameProy = nameProy;
    }
    
}
