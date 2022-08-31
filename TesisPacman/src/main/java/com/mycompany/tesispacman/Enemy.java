/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tesispacman;

import java.awt.Graphics2D;
import java.awt.Image;

/**
 *
 * @author GigaPC
 */
public class Enemy {
        public Image ghost;
        private final int MAX_GHOSTS = 1;
        public int dx, dy;
        public int ghost_x, ghost_y, ghost_dx, ghost_dy, ghostSpeed;

        private int N_GHOSTS = 1;
        
        
        public void moveGhosts(int BLOCK_SIZE,int N_BLOCKS,short[] screenData) {

        short i;
        int pos;
        int count;
            if (ghost_x % BLOCK_SIZE == 0 && ghost_y % BLOCK_SIZE == 0) {
                pos = ghost_x / BLOCK_SIZE + N_BLOCKS * (int) (ghost_y / BLOCK_SIZE);

                count = 0;

                if ((screenData[pos] & 1) == 0 && ghost_dx != 1) {
                    dx = -1;
                    dy = 0;
                    
                }

                if ((screenData[pos] & 2) == 0 && ghost_dy != 1) {
                    dx = 0;
                    dy = -1;
                    
                }

                if ((screenData[pos] & 4) == 0 && ghost_dx != -1) {
                    dx = 1;
                    dy = 0;
                    
                }

                if ((screenData[pos] & 8) == 0 && ghost_dy != -1) {
                    dx = 0;
                    dy = 1;
                    
                }

                if (count == 0) {

                    if ((screenData[pos] & 15) == 15) {
                        ghost_dx = 0;
                        ghost_dy = 0;
                    } else {
                        ghost_dx = -ghost_dx;
                        ghost_dy = -ghost_dy;
                    }

                } else {

                    count = (int) (Math.random() * count);

                    if (count > 3) {
                        count = 3;
                    }

                    ghost_dx = dx;
                    ghost_dy = dy;
                }

            }

            ghost_x = ghost_x + (ghost_dx * ghostSpeed);
            ghost_y = ghost_y + (ghost_dy * ghostSpeed);
    }
}
