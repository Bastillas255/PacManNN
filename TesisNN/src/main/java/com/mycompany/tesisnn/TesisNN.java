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

    public static void main(String[] args) {
        NeuralNetwork nn = new NeuralNetwork(2,10,1);
        
        List<Double>output;
        
	nn.fit(X, Y, 50000);
        
	double [][] input = {{0,0},{0,1},{1,0},{1,1}};
        for(double d[]:input)
        {
            output = nn.predict(d);
            //System.out.println(output.toString());
	}
        
        MatrixFileReader fr = new MatrixFileReader();
        try {
            fr.readFile();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TesisNN.class.getName()).log(Level.SEVERE, null, ex);
        }
            //Ciclos for usados para verificar cómo se lee la matriz, para tener una guía de como construir
            //la matriz real con los datos del pacman para la red neuronal
            /*
            String c = "";
            for(int i=0; i<X.length; i++){
            for(int j=0; j<2;j++){
            c = c + String.valueOf(X[i][j]) + " ";
            }
            System.out.println(c);
            c = "";
            }
            System.out.println(c);
            for(int i=0; i<Y.length; i++){
            c = c + String.valueOf(Y[i][0])+ " ";
            }
            System.out.println(c);*/
    }
}
