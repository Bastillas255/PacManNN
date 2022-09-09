/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.tesisnn;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GigaPC
 */
public class TesisNN {
    static double [][] X= {
			{0,0},
			{1,0},
			{0,1},
			{1,1}
	};
	static double [][] Y= {
			{0},{1},{1},{0}
	};

    public static void main(String[] args) throws Exception{
        NeuralNetwork nn = new NeuralNetwork(2,10,1);
        
        List<Double>output;
        
	nn.fit(X, Y, 50000);
        
	double [][] input = {{0,0},{0,1},{1,0},{1,1}};
        for(double d[]:input)
        {
            output = nn.predict(d);
            //System.out.println(output.toString());
	}
        
        double[][] matrizTest = new double[100][15];
        for(int i=0; i<100; i++){
            for(int j=0; j<15; j++){
                matrizTest[i][j] = j+1;
            }
        }
        double[][] newMatriz;
        MatrixFileHandler fh = new MatrixFileHandler();
        try {
            fh.writeFile(matrizTest, "Guardar matriz de testing");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TesisNN.class.getName()).log(Level.SEVERE, null, ex);
        }
        newMatriz = fh.readFile();
        fh.printMatrix(newMatriz);
    }
}
