/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pacmandestripado;

import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 *
 * @author GigaPC
 */
public class Ghost {
    public Image Image;
    public int x, y, dx, dy,otherDx,otherDy;
    
    short i;
    int pos;
    int count;

    public Ghost(int x,int y){
        Image = new ImageIcon("images/PacCat.gif").getImage();
        this.x=x;
        this.y=y;
        dx=1;
        dy=0;
        otherDx=-1;
    }
    public void MoveGhost(int BLOCK_SIZE,int N_BLOCKS, short[] screenData){
        if (x % BLOCK_SIZE == 0 && y % BLOCK_SIZE == 0) {//lol this line is for grid based movement
            pos = x / BLOCK_SIZE + N_BLOCKS * (int) (y / BLOCK_SIZE);//where is the ghost?
            count=0;
            if ((screenData[pos] & 1) == 0 && dx != 1) {
                otherDx = -1;
                otherDy = 0;
                count++;
            }

            if ((screenData[pos] & 2) == 0 && dy != 1) {
                otherDx = 0;
                otherDy= -1;
                count++;
            }

            if ((screenData[pos] & 4) == 0 && dx != -1) {
                otherDx = 1;
                otherDy = 0;
                count++;
            }

            if ((screenData[pos] & 8) == 0 && dy != -1) {
                otherDx = 0;
                otherDy = 1;
                count++;
            }

            if (count == 0) {
                if ((screenData[pos] & 15) == 15) {
                    dx = 0;
                    dy = 0;
                }else{
                    dx = -dx;
                    dy = -dy;
                }

            } else {
                count = (int) (Math.random() * count);

                if (count > 3) {
                    count = 3;
                }
                dx=otherDx;
                dy=otherDy;
            }
        
        
        }
        //System.out.println(dx+" & "+dy);
        x = x + dx;
        y = y + dy;
    }
}


