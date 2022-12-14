/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pacmandestripado;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author GigaPC
 */

//pac should stop time on standstill and accept movement only after moving one entire block
/**
    how to stand still? was that even possible in the original java project?
    * nope,it wasn't possible
    * not even in the arcade pacman
    * should advance a intire square then time stop
*/  
//ghost should have A* to search for pac


public class BoardDestripado extends JPanel implements ActionListener{
    private Ghost ghost;
    private PacPlayer pac;
    
    private Dimension d;
    private Timer timer;
    public boolean isPaused;
    
    private final int BLOCK_SIZE = 24;
    private final int N_BLOCKS = 15;
    private final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE;
    private final short levelData[] = {
	3, 10, 10, 10, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 6,
        5, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 16, 0, 4,
        5, 0, 0, 0, 1, 0, 16, 0, 0, 0, 0, 0, 0, 0, 4,
        5, 0, 0, 0, 1, 0, 0, 8, 0, 0, 0, 0, 0, 0, 4,
        1, 2, 2, 2, 0, 0, 4, 0, 1, 0, 0, 0, 0, 0, 4,
        1, 0, 0, 0, 0, 0, 4, 0, 1, 0, 0, 0, 0, 8, 4,
        9, 0, 0, 0, 8, 8, 12, 0, 9, 8, 8, 0, 4, 0, 5,
        1, 1, 0, 4, 0, 0, 0, 0, 0, 0, 0, 1, 4, 0, 5,
        1, 1, 0, 0, 2, 2, 6, 0, 3, 2, 2, 0, 4, 0, 5,
        1, 1, 0, 0, 0, 0, 4, 0, 1, 0, 0, 0, 4, 0, 5,
        1, 1, 0, 16, 0, 0, 4, 0, 1, 0, 0, 0, 4, 0, 5,
        1, 1, 0, 0, 0, 0, 0, 2, 0, 0, 16, 0, 4, 0, 5,
        1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 4,
        1, 9, 8, 8, 8, 8, 8, 8, 8, 8, 0, 0, 0, 0, 4,
        9, 8, 8, 8, 8, 8, 8, 8, 8, 8, 9, 8, 8, 8, 12
    };
//1,  2, 4 and 8 represent left. top, right, and bottom corners respectively. Number 16 is a point
    private short[] screenData;
    int score=0;//this should be 0 and count up to the maximum numbers of prizes on screen
    private final int N_Prizes=4;
    int pos;
    short ch;

    public int req_dx,req_dy;//this are pacman inputs catch through key event
    private final Color mazeColor = new Color(5, 100, 5);
    private final Color dotColor = new Color(192, 192, 0);
        
    
    public BoardDestripado() {
        
        initVariables();
        initBoard();
    }
    


    
    private void initVariables() {
        ghost = new Ghost(4 * BLOCK_SIZE, 4 * BLOCK_SIZE);
        pac=new PacPlayer(7 * BLOCK_SIZE,11 * BLOCK_SIZE);
        screenData = new short[N_BLOCKS * N_BLOCKS];

        d = new Dimension(400, 400);
        
        timer = new Timer(40, this);
        timer.start();
    }
    
    private void initBoard() {    
        addKeyListener(new TAdapter());

        setFocusable(true);

        setBackground(Color.black);
    }
    
    private void showIntroScreen(Graphics2D g2d) {

        g2d.setColor(new Color(0, 32, 48));
        g2d.fillRect(50, 360 / 2 - 30, 360 - 100, 50); //360=SCREEN_SIZE
        g2d.setColor(Color.white);
        g2d.drawRect(50, 360 / 2 - 30, 360 - 100, 50);

        String s = "Press 's' to start";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = this.getFontMetrics(small);

        g2d.setColor(Color.white);
        g2d.setFont(small);
        g2d.drawString(s, (360 - metr.stringWidth(s)) / 2, 360 / 2);
    }
    
    private void initLevel() {

        int i;
        for (i = 0; i < N_BLOCKS * N_BLOCKS; i++) {
            screenData[i] = levelData[i];
        }
    }
@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, d.width, d.height);

        //showIntroScreen(g2d);
        initLevel();
        drawMaze(g2d);
        
        //isPaused=pac.x % BLOCK_SIZE == 0 && pac.y % BLOCK_SIZE == 0;//pausamos si se completo un movimiento
        //req_dx=nn.outputx;//esperamos por un output de NN, si tenemos output despausamos
        
        if(!isPaused){
            ghost.MoveGhost(BLOCK_SIZE, N_BLOCKS, screenData);
            drawGhost(g2d, ghost.x-4, ghost.y-8);//this should be change at one point, hitbox maybe to different to the sprite offset
            pac.MovePacman(BLOCK_SIZE, N_BLOCKS, screenData,req_dx,req_dy);
            drawPacman(g2d);
            gameStateCheck();
        }else{
            drawGhost(g2d, ghost.x-4, ghost.y-8);
            drawPacman(g2d);
        }
        
        
        //g2d.drawImage(ii, 5, 5, this);
        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
        //
    }
    private void drawGhost(Graphics2D g2d, int x, int y) {

        g2d.drawImage(ghost.Image, x, y, this);
    }
    private void drawPacman(Graphics2D g2d) {

        if (pac.view_dx == -1) {
            g2d.drawImage(pac.imageLeft, pac.x -4, pac.y -4, this);
        } else if (pac.view_dx == 1) {
            g2d.drawImage(pac.imageRight, pac.x -4, pac.y -4, this);
        } else if (pac.view_dy == -1) {
           g2d.drawImage(pac.imageUp, pac.x -4, pac.y -4, this);
        } else {
           g2d.drawImage(pac.imageDown, pac.x -4, pac.y -4, this);
        }
    }
    
    private void gameStateCheck(){
        //pacman kill code
        if (pac.x > (ghost.x - 12) && pac.x < (ghost.x + 12) && pac.y > (ghost.y - 12) && pac.y < (ghost.y + 12)) {
                //finish game
                System.out.println("ghost-pac collision");
            }
        //all prizes on screen clear
        pos = pac.x / BLOCK_SIZE + N_BLOCKS * (int) (pac.y / BLOCK_SIZE);
        ch = screenData[pos];

        if ((ch & 16) != 0) {
            screenData[pos] = (short) (ch & 15);//this is not the same "screenData" of board class...
            score++; //this shall be the new counter of how many prizes are still on screen
        }
        if(score==N_Prizes){
            //finish game
            System.out.println("pac level clear");
        }
    }

    private void drawMaze(Graphics2D g2d) {

        short i = 0;
        int x, y;

        for (y = 0; y < SCREEN_SIZE; y += BLOCK_SIZE) {
            for (x = 0; x < SCREEN_SIZE; x += BLOCK_SIZE) {

                g2d.setColor(mazeColor);
                g2d.setStroke(new BasicStroke(2));

                if ((screenData[i] & 1) != 0) { 
                    g2d.drawLine(x, y, x, y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 2) != 0) { 
                    g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y);
                }

                if ((screenData[i] & 4) != 0) { 
                    g2d.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 8) != 0) { 
                    g2d.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }

                if ((screenData[i] & 16) != 0) { 
                    g2d.setColor(dotColor);
                    g2d.fillRect(x + 11, y + 11, 2, 2);
                }

                i++;
            }
        }
    }


    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e){

            int key = e.getKeyCode();

            switch (key) {
                case KeyEvent.VK_LEFT:
                    req_dx = -1;
                    req_dy = 0;
                    break;
                case KeyEvent.VK_RIGHT:
                    req_dx = 1;
                    req_dy = 0;
                    break;
                case KeyEvent.VK_UP:
                    req_dx = 0;
                    req_dy = -1;
                    break;
                case KeyEvent.VK_DOWN:
                    req_dx = 0;
                    req_dy = 1;
                    break;
                case KeyEvent.VK_PAUSE:
                    isPaused=!isPaused;
                    break;
                default:
                    break;
            }
            
        }

        @Override
        public void keyReleased(KeyEvent e) {

            int key = e.getKeyCode();

            if (key == Event.LEFT || key == Event.RIGHT
                    || key == Event.UP || key == Event.DOWN) {
                req_dx = 0;
                req_dy = 0;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        repaint();
    }
}
