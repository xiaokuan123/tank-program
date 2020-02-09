package com.example;

import org.apache.commons.io.filefilter.OrFileFilter;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.example.Dir.*;

public class TankFrame extends Frame {
    public static final int GAME_WIDTH = 1080,GAME_HEIGHT = 960;
    Tank tank = new Tank(500,500, DOWN,this);

    List<Explode>  explodeList = new ArrayList<>();

    //Bullot bullot = new Bullot(400,400,UP,this);
    List<TankEnemy> enemyList = new ArrayList<>();

    List<Bullot> listBulls = new ArrayList<>();
    public TankFrame() {
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setResizable(false);
        this.setTitle("tank warld");
        this.setVisible(true);
        this.addKeyListener(new MyKeyListen());
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    Image offScreenImage= null;
    @Override
    public void update(Graphics g) {
        if(offScreenImage ==null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics graphics = offScreenImage.getGraphics();
        Color color = graphics.getColor();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0,GAME_WIDTH,GAME_WIDTH);
        graphics.setColor(color);
        paint(graphics);
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.BLUE);
        g.drawString("子弹的数量"+listBulls.size(),10,100);
        g.drawString("敌方坦克的数量"+enemyList.size(),10,80);
        g.drawString("爆炸的数量"+explodeList.size(),10,120);
        g.setColor(color);
        tank.paint(g);
        //todo 敌人的坦克
        ememyTank(g);
        for(int i=0;i<listBulls.size(); i++){
            listBulls.get(i).paint(g);
        }
        //todo 子弹和坦克碰撞
        for(int i=0;i < enemyList.size();i++){
            for(int j=0;j<listBulls.size();j++){
                listBulls.get(j).collison(enemyList.get(i));
            }
        }
        //todo 爆炸
        for(int i=0;i<explodeList.size();i++){
            explodeList.get(i).paint(g);
        }

    }

    private void ememyTank(Graphics g) {
        for(int i=0; i< enemyList.size();i++){
            enemyList.get(i).paint(g);
        }
       /* Random random = new Random();
            Color color = g.getColor();
            g.setColor(Color.RED);
            enemy.paint(g);
            g.setColor(color);*/
    }

    class MyKeyListen extends KeyAdapter {
        Boolean VL = false;
        Boolean VR = false;
        Boolean VU = false;
        Boolean VD = false;
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    VL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    VR = true;
                    break;
                case KeyEvent.VK_UP:
                    VU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    VD = true;
                    break;
                default:
                    break;
            };
            setKeytank();
        }

        @Override
            public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
           switch (key) {
                case KeyEvent.VK_LEFT:
                    VL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    VR = false;
                    break;
                case KeyEvent.VK_UP:
                    VU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    VD = false;
                    break;
               case KeyEvent.VK_CONTROL:
                  // VD = false;
                   tank.makerBullot();
                   break;
                default:
                    break;
            };
            setKeytank();
        }

        public void  setKeytank(){
            if(!VL&&!VR&&!VU&&!VD){
                tank.setMoveing(false);
            }else {
                tank.setMoveing(true);
                if(VL) {
                    tank.setDir(LIFT);
                };
                if(VR){
                    tank.setDir(RIGHT);
                };
                if(VU){
                    tank.setDir(UP) ;
                };
                if(VD){
                    tank.setDir(DOWN);
                }
            }


        }

    }
}
