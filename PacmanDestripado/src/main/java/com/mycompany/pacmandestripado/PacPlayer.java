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
    public int dx,dy,view_dx,view_dy,req_dx,req_dy; //req_x and req_y are the "pressed direction"
    
    public PacPlayer(int x,int y){
        imageUp = new ImageIcon("images/RatUp.gif").getImage();
        imageRight = new ImageIcon("images/RatRight.gif").getImage();
        imageDown = new ImageIcon("images/RatDown.gif").getImage();
        imageLeft = new ImageIcon("images/RatLeft.gif").getImage();
        this.x=x;
        this.y=y;
    }
    public void MovePacman(int BLOCK_SIZE,int N_BLOCKS, short[] screenData, int req_dx, int req_dy, int score) {
        int pos;
        short ch;

        if (req_dx == -x && req_dy == -y) {//this is done pre movement?
            dx = req_dx;
            dy = req_dy;
            view_dx = dx;
            view_dy = dy;
        }

        if (x % BLOCK_SIZE == 0 && y % BLOCK_SIZE == 0) {
            pos = x / BLOCK_SIZE + N_BLOCKS * (int) (y / BLOCK_SIZE);
            ch = screenData[pos];
/*
            if ((ch & 16) != 0) {
                screenData[pos] = (short) (ch & 15);//this is not the same "screenData" of board class...
                score++; //this shall be the new counter of how many prizes are still on screen
            }
*/          
            
            if (req_dx != 0 || req_dy != 0) {//stoping on collision with walls
                if (!((req_dx == -1 && req_dy == 0 && (ch & 1) != 0)
                        || (req_dx == 1 && req_dy == 0 && (ch & 4) != 0)
                        || (req_dx == 0 && req_dy == -1 && (ch & 2) != 0)
                        || (req_dx == 0 && req_dy == 1 && (ch & 8) != 0))) {
                    dx = req_dx;
                    dy = req_dy;
                    view_dx = dx;
                    view_dy = dy;
                }
            }
            
            // Check for standstill
            if ((dx == -1 && dy == 0 && (ch & 1) != 0)
                    || (dx == 1 && dy == 0 && (ch & 4) != 0)
                    || (dx == 0 && dy == -1 && (ch & 2) != 0)
                    || (dx == 0 && dy == 1 && (ch & 8) != 0)) {
                dx = 0;
                dy = 0;
            }
        }
        x = x + req_dx;
        y = y + req_dy;
    }
}
