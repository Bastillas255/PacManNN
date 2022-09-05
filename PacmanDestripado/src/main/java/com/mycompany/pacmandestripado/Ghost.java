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
    public int x, y, dx, dy;
    
    short i;
    int pos;
    int count;

    public Ghost(int x,int y){
        Image = new ImageIcon("images/PacCat.gif").getImage();
        this.x=x;
        this.y=y;
    }
    public void MoveGhost(int BLOCK_SIZE,int N_BLOCKS, short[] screenData){
        if (x % BLOCK_SIZE == 0 && y % BLOCK_SIZE == 0) {//lol this line is for grid based movement
            pos = x / BLOCK_SIZE + N_BLOCKS * (int) (y / BLOCK_SIZE);//where is the ghost?
                if ((screenData[pos] & 1) == 0) {
                    dx = -1;
                    dy = 0;
                }

                if ((screenData[pos] & 2) == 0) {
                    dx = 0;
                    dy= -1;
                }

                if ((screenData[pos] & 4) == 0) {
                    dx = 1;
                    dy = 0;
                    count++;
                }

                if ((screenData[pos] & 8) == 0) {
                    dx = 0;
                    dy = 1;
                    count++;
                }

                if (count == 0) {
                        dx = -dx;
                        dy = -dy;
                }

        } else {
            count = (int) (Math.random() % 4);
            if(count%2==0){
                if(count==0)
                    dx = 1;else dx=-1;
            }else{
                if(count==1)
                    dy = 1;else dy=-1;
            }
        }
    }
}


