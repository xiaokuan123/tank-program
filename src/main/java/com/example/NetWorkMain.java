package com.example;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.TimeUnit;

public class NetWorkMain {
    public static void main(String[] args) {
        TankFrame tankFrame = new TankFrame();
        int enemyTankCount = Integer.parseInt(ConfitTank.getInfo("countTankEnemy").toString());
        for(int i=0;i<enemyTankCount;i++){
            tankFrame.enemyList.add(new TankEnemy(50+i*100,150,Dir.DOWN,tankFrame,Group.BLUE.getCode()));
        }
        while (true){
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tankFrame.repaint();
        }

    }
}
