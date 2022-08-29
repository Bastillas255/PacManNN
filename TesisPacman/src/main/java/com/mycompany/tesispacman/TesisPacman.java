/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.tesispacman;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 *
 * @author GigaPC
 */
public class TesisPacman extends JFrame {

    public TesisPacman() {
        
        initUI();
    }
    
    private void initUI() {
        
        add(new Board());
        
        setTitle("Pacman");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(380, 420);
        setLocationRelativeTo(null);
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {

            TesisPacman ex = new TesisPacman();
            ex.setVisible(true);
        });
    }
}
