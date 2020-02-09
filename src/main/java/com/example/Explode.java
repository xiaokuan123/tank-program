package com.example;

import lombok.Data;

import java.awt.*;
import java.util.concurrent.ThreadPoolExecutor;

@Data
public class Explode {

    private int x,y;
    private Boolean live =true;
    private TankFrame tankFrame;
    private int count=0;
    public static final int T_WIDTH = ResourceMges.EXPODELIST[0].getWidth();
    public static final int T_THINTH = ResourceMges.EXPODELIST[0].getHeight();

    public Explode(int x, int y, TankFrame tankFrame) {
        super();
        this.x = x;
        this.y = y;
        this.tankFrame =tankFrame;
    }
    public void paint(Graphics g) {

        //todo 展现图片方向
            int xk =x + TankEnemy.E_WIDTH/2 - T_WIDTH/2;
            int yk =y + TankEnemy.E_THINTH /2 - T_WIDTH/2;
          g.drawImage(ResourceMges.EXPODELIST[count++],xk,yk,null);
          //
        if(count >=ResourceMges.EXPODELIST.length){
            tankFrame.explodeList.remove(this);
        }
        //todo 声音
        new Thread(()->{
            new Audio("audio/explode.wav").play();
        }).start();
    }

}
