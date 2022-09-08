/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tesisnn;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author rohta
 */
public class MatrixFileReader {
    
    private static int ROWS = 1;
    private static int COLUMNS = 1;
    
    public static void main(String[] args) throws FileNotFoundException {
        readFile();
    }
/**
 * Hace al usuario elegir un archivo .txt, lo lee y extrae los datos de la matriz
 * que contenga, vigilar que los valoren se detecten como double y no float o int
 * @throws FileNotFoundException 
 */
    public static void readFile() throws FileNotFoundException {
        try{
            File file = chooseTextFile();
            Scanner sc = new Scanner(file);
            getFileMatrixDimensions(file);
            double[][] numArray = new double[ROWS][COLUMNS];
            while(sc.hasNextLine()){
                for(int i = 0; i < numArray.length; i++){
                    String[] line = sc.nextLine().trim().split("," + " ");
                    for(int j = 0; j < line.length; j++){
                        numArray[i][j] = Double.parseDouble(line[j]);   //En archivos creados por uno vigilar cómo detecta el tipo de número
                                                                        //No parseará float a double, asegurarse de que los datos del archivos se detecten como double
                    }
                    System.out.println(line.length);
                }
            }
        printMatrix(numArray);
        }
        catch(FileNotFoundException e){
            System.out.println("Algo salió mal: ");
            e.printStackTrace();
        }
    }
    
    private static void printMatrix(double[][] m){
        for(int i=0; i<m.length;i++){
            for(int j=0; j<m[i].length; j++){
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }
    /**
     * Recorre el archivo con el propósito de obtener las dimensiones de la matriz
     * @param file Archivo con matriz de double
     */
    private static void getFileMatrixDimensions(File file){
        //Básicamente recorrer la primera línea para obtener las columnas, y recorrer
        //el resto del archivo para contar las filas
        try{
            int rows = 0;
            Scanner sc = new Scanner(file);
            if(sc.hasNextLine()){
                String[] line = sc.nextLine().trim().split("," + " ");
                COLUMNS = line.length;
                rows++;
                while(sc.hasNextLine()){
                    sc.nextLine();
                    rows++;
                }
                ROWS = rows;
                System.out.println("Dimensiones: ROWS = " + ROWS + " | COLUMNS: " + COLUMNS);
            }
            
        }catch(FileNotFoundException e){
            System.out.println(e);
        }
        
    }

    private static File chooseTextFile() {
        FileDialog dialog = new FileDialog((Frame) null, "Seleccione archivo con matriz");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        File[] file = dialog.getFiles();
        return file[0];
    }
}
