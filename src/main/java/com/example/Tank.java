package com.example;

import lombok.Data;

import java.awt.*;

@Data
public class Tank {

    private int x,y;

    private Dir dir=Dir.DOWN;

    private Boolean moveing = false;

    private Boolean live =true;
    private TankFrame tankFrame;

    private static final int NUM = 20;
    public static final int T_WIDTH = ResourceMges.tankU.getWidth();
    public static final int T_THINTH = ResourceMges.tankU.getHeight();

    public Tank(int x, int y, Dir dir,TankFrame tankFrame) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame =tankFrame;
    }
    public void paint(Graphics g) {

        //todo 展现图片方向
        switch (dir) {
            case LIFT:
                g.drawImage(ResourceMges.tankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMges.tankR,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMges.tankU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMges.tankD,x,y,null);
                break;
            default:
                break;
        }
      //todo 移动
        move();
    }

    private void move() {
        if(!moveing){
            return;
        }
        switch (dir) {
            case LIFT:
                x  -= NUM;
                break;
            case RIGHT:
                x += NUM;
                break;
            case UP:
                y -= NUM;
                break;
            case DOWN:
                y += NUM;
                break;
            default:
                break;
        }
        ;
    }
    public void makerBullot(){
        int xk =x + T_WIDTH /2 - Bullot.B_WIDTH/2;
        int yk =y + T_THINTH /2 - Bullot.B_THINTH/2;;
      tankFrame.listBulls.add(new Bullot(xk,yk,this.dir,this.tankFrame,Group.GOOD.getCode()));
      //
      new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
    }
}
