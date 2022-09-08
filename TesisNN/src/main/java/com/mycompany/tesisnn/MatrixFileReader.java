/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tesisnn;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author rohta
 */
public class MatrixFileReader {
    
    private static int ROWS = 4;
    private static int COLUMNS = 2;
    
    public static void main(String[] args) throws FileNotFoundException {
        readFile();
    }

    public static void readFile() throws FileNotFoundException {
        try{
            double[][] numArray = new double[ROWS][COLUMNS];
            Scanner sc = new Scanner(chooseTextFile());
            while(sc.hasNextLine()){
                for(int i = 0; i < numArray.length; i++){
                    String[] line = sc.nextLine().trim().split("," + " ");
                    for(int j = 0; j < line.length; j++){
                        numArray[i][j] = Double.parseDouble(line[j]);
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
    
    private static void getFileMatrixDimensions(){
        //Básicamente recorrer la primera línea para obtener las columnas, y recorrer
        //el resto del archivo para contar las filas
        
    }

    private static File chooseTextFile() {
        FileDialog dialog = new FileDialog((Frame) null, "Seleccione archivo con matriz");
        dialog.setMode(FileDialog.LOAD);
        dialog.setVisible(true);
        File[] file = dialog.getFiles();
        return file[0];
    }
}
