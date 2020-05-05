package gestioninformacionproyectos.Configuration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author srestrepo
 */
public class Files {
    
    int count;
    FileWriter fileWriter;
    PrintWriter printWriter;
    File file;
    FileReader fileReader;
    BufferedReader bufferedReader;
    
    public String readFile(){
        String line = "";
        try {
            line = bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return line;
    }
    
    public void openFileReader(String rute) {
        try {
            file = new File(rute);
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void closeFileReader(){
        try {
            fileReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void openWriteFile(String rute){
        try {
            fileWriter = new FileWriter(rute, true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void closeFileWriter(){
        try {
            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void writeAndRowJump(String rowText) {
        try {
            printWriter = new PrintWriter(fileWriter);
            printWriter.println(rowText);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public int countLines(){
        String line;
        this.count = 0;
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                this.count++;
            }
            fileReader.close();
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
        return this.count;
    }
    
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
}
