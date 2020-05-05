package gestioninformacionproyectos.Controller;

import gestioninformacionproyectos.Configuration.Files;
import javax.swing.JOptionPane;

/**
 *
 * @author jaflorez
 */
public class Export {
    Files objFiles;

    public Export() {
        objFiles = new Files();
    }
    
    public void exportToTxt(String[] data, String name){
        objFiles.openWriteFile(name + ".txt");
        String lineaTexto;
        if(!esta_no_esta(name,data)){
            for(int i = 0; i < data.length; i++){
                lineaTexto = data[i];
                objFiles.writeAndRowJump(lineaTexto);
            }
            JOptionPane.showMessageDialog(null, "Se ha exportado correctamente el archivo '" + name + "' en la carpeta raiz del proyecto");
        }
        objFiles.closeFileWriter();
    }
    
    public boolean esta_no_esta(String name, String[] data){
        String cad;
        objFiles.openFileReader(name + ".txt");
        int i = 0;
        long n = objFiles.countLines();
        objFiles.closeFileReader();
        boolean sw = false;
        objFiles.openFileReader(name + ".txt");
        while(i < n && sw == false){
            cad = objFiles.readFile();
            if (cad.equals(data[i]))
                sw = true;
            i = i+1;
        }
        JOptionPane.showMessageDialog(null, "Se ha exportado correctamente el archivo '" + name + "' en la carpeta raiz del proyecto");
        return sw;
    }
}