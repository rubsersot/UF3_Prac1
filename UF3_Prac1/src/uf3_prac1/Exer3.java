package uf3_prac1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import utils.Utils;

public class Exer3 {

    public static void main(String[] args) throws IOException {
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
        while (!missatge.equals("")) {
            if (missatge.equals("@ESBORRA")) {
                pw.flush();
                writer.close();
                arxiu.delete();
                arxiu.createNewFile();
                writer = new FileWriter(arxiu, false);
                pw = new PrintWriter(writer);

            } else {
                pw.println(missatge);
            }
            missatge = scan.nextLine();
        }

        pw.flush();
        writer.close();

        //Llegir
        FileReader reader = new FileReader(arxiu);
        BufferedReader buffer = new BufferedReader(reader);

        mostrarMenu();
        int opcio = Utils.LlegirInt();

        switch (opcio) {
            case 1:
                String linea = buffer.readLine();
                while (linea != null) {
                    System.out.println(linea);
                    linea = buffer.readLine();
                }   break;
            case 2:
                System.out.print("Introdueix quina línia vols llegir: ");
                int linia = Utils.LlegirInt(1, (int) arxiu.length());
                for (int i = 1; i < linia; ++i) {
                    String entrada = buffer.readLine();
                }   String resultat = buffer.readLine();
                System.out.println(resultat);
                break;
            case 3:
                System.out.println("Introdueix la línia que vols afegir: ");
                String entrada = scan.nextLine();
                //Crear fitxer temporal
                File temporal = new File("./temporal.txt");
                FileWriter writer2 = new FileWriter(temporal, false);
                PrintWriter pw2 = new PrintWriter(writer2);
                //Afegir el contingut a la primera línia
                pw2.println(entrada);
                boolean fin = false;
                //Copiar els continguts de l'original al temporal
                for(int i = 0; i < arxiu.length() && !fin; ++i){
                    String leer = buffer.readLine();
                    if(leer==null){
                        fin = true;
                    }
                    else{
                        pw2.println(leer);
                    }
                }
                //Substituir el fitxer original pel temporal el qual tindrà els canvis
                pw2.flush();
                writer2.close();
                arxiu.delete();
                temporal.renameTo(arxiu);
                temporal.delete();
                break;
            default:
                System.out.println("Opció no vàlida, torna a introduir-la");
                break;
        }

        scan.close();
    }

    static void mostrarMenu() {
        System.out.println("Selecciona una opció: ");
        System.out.println("1. Mostrar tot el contingut del fitxer");
        System.out.println("2. Mostrar el contingut d'una línia en concret");
        System.out.println("3. Insertar una línia a l'inici del fitxer");
    }

}
