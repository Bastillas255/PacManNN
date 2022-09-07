/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pacmandestripado;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author GigaPC
 */
public class PacPlayer {
    public Image imageUp, imageLeft, imageRight, imageDown;    
    public int x, y;
    public int dx, dy,view_dx,view_dy;

    private final int PACMAN_SPEED = 3;
    
    public PacPlayer(int x,int y){
        imageUp = new ImageIcon("images/RatUp.gif").getImage();
        imageRight = new ImageIcon("images/RatRight.gif").getImage();
        imageDown = new ImageIcon("images/RatDown.gif").getImage();
        imageLeft = new ImageIcon("images/RatLeft.gif").getImage();
        this.x=x;
        this.y=y;
    }
    private void movePacman(int BLOCK_SIZE,int N_BLOCKS, short[] screenData) {
        
    }
}
