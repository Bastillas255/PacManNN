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
    public boolean canMove;
    public int x, y,pos;
    public int dx,dy,view_dx,view_dy,req_dx,req_dy; //req_x and req_y are the "pressed direction"
    
    public PacPlayer(int x,int y){
        imageUp = new ImageIcon("images/RatUp.gif").getImage();
        imageRight = new ImageIcon("images/RatRight.gif").getImage();
        imageDown = new ImageIcon("images/RatDown.gif").getImage();
        imageLeft = new ImageIcon("images/RatLeft.gif").getImage();
        this.x=x;
        this.y=y;
    }
    public void MovePacman(int BLOCK_SIZE,int N_BLOCKS, short[] screenData, int req_dx, int req_dy) {
        //System.out.println("views: "+view_dx+" & "+ view_dy);
        //System.out.println("deltas: "+dx+" & "+ dy);
        
        //dx and dy are not being updated as they should be
        
        if (req_dx == -dx && req_dy == -dy) {//this is done pre movement?
            dx = req_dx;
            dy = req_dy;
            view_dx = dx;
            view_dy = dy;
        }
        //how to know where movement has stoped?
        canMove =x % BLOCK_SIZE == 0 && y % BLOCK_SIZE == 0;//if canMove is true
        //

        if (x % BLOCK_SIZE == 0 && y % BLOCK_SIZE == 0) {
            pos = x / BLOCK_SIZE + N_BLOCKS * (int) (y / BLOCK_SIZE);
            
            if (req_dx != 0 || req_dy != 0) {//stoping on collision with walls
                if (!((req_dx == -1 && req_dy == 0 && (screenData[pos] & 1) != 0)
                        || (req_dx == 1 && req_dy == 0 && (screenData[pos] & 4) != 0)
                        || (req_dx == 0 && req_dy == -1 && (screenData[pos] & 2) != 0)
                        || (req_dx == 0 && req_dy == 1 && (screenData[pos] & 8) != 0))) {
                    dx = req_dx;
                    dy = req_dy;
                    view_dx = dx;
                    view_dy = dy;
                }
            }
            
            // Check for standstill
            if ((dx == -1 && dy == 0 && (screenData[pos] & 1) != 0)
                    || (dx == 1 && dy == 0 && (screenData[pos] & 4) != 0)
                    || (dx == 0 && dy == -1 && (screenData[pos] & 2) != 0)
                    || (dx == 0 && dy == 1 && (screenData[pos] & 8) != 0)) {

                dx = 0;
                dy = 0;
            }
        }
        x = x + dx;
        y = y + dy;
    }
}
