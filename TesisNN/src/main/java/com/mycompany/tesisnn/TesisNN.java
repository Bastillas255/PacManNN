/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.tesisnn;

import java.util.List;

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
                    System.out.println(output.toString());
		}		
    }
}
