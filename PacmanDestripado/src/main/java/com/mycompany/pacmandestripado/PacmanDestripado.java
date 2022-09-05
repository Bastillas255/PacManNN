/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.mycompany.pacmandestripado;

import java.awt.EventQueue;
import javax.swing.JFrame;


/**
 *
 * @author GigaPC
 */
//tenemos el board, ahora a dibujar el maze
public class PacmanDestripado extends JFrame{

   public PacmanDestripado() {
        
        initUI();
    }
    
    private void initUI() {
        
        add(new BoardDestripado());
        
        setTitle("Pacman");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(380, 420);
        setLocationRelativeTo(null);
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {

            PacmanDestripado ex = new PacmanDestripado();
            ex.setVisible(true);
        });
    }

}
