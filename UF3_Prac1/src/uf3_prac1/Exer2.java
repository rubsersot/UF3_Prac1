/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package uf3_prac1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author ausias
 */
public class Exer2 {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(System.in);
        File arxiu = new File("./textos.txt");
        FileWriter writer;
        PrintWriter pw;
        
        // Comprobar si el archivo existe
        if (arxiu.exists()) {
            writer = new FileWriter(arxiu, true); // agregar al final
        } else {
            arxiu.createNewFile(); // crear nuevo archivo
            writer = new FileWriter(arxiu, false);
        }
        pw = new PrintWriter(writer);
        
        String missatge = scan.nextLine();
        while(!missatge.equals("")){
            if(missatge.equals("@ESBORRA")){
                pw.flush();
                writer.close();
                arxiu.delete();
                arxiu.createNewFile();
                writer = new FileWriter(arxiu, false);
                pw = new PrintWriter(writer);
            
            }
            else{
                pw.println(missatge);
            }
            missatge = scan.nextLine();
        }
        
        pw.flush();
        writer.close();        
        scan.close();
    }
    
}
