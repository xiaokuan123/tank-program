package com.example;

import lombok.Data;

import java.awt.*;
import java.util.Random;

@Data
public class TankEnemy {

    private int x,y;

    private Dir dir=Dir.DOWN;

    private Boolean live =true;
    private TankFrame tankFrame;
    private String group;
    public static final int E_WIDTH = ResourceMges.ememyTankD.getWidth();
    public static final int E_THINTH = ResourceMges.ememyTankD.getHeight();
    private static final int NUM = 10;
    Rectangle emenTankR = new Rectangle();
   Random random = new Random();

    public TankEnemy(int x, int y, Dir dir, TankFrame tankFrame,String group) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame =tankFrame;
        this.group = group;
    }
    public void paint(Graphics g) {
        if(!live){
            this.tankFrame.enemyList.remove(this);
        }
       /* Color color = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 50, 50);
        g.setColor(color);*/
        g.drawImage(ResourceMges.ememyTankD,x,y,null);
        move();
    }

    private void move() {
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
        //todo 敌人坦克随机开火
        if(random.nextInt(100) > 95 && this.getGroup().equals(Group.BLUE.getCode())){
               fire();
        }
        //todo 随机换方向
        if(random.nextInt(100) > 95 && this.getGroup().equals(Group.BLUE.getCode())){
            this.dir = Dir.values()[random.nextInt(4)] ;
        }
        //todo 调整坦克方向
        boundCheck();
        //todo 设置方位
        emenTankR.setRect(x,y,E_WIDTH,E_THINTH);
    }

    private void boundCheck() {
        if(x <= 2 ){
            this.dir = Dir.RIGHT;
        }else if(y <= 20 ){
            this.dir = Dir.DOWN;
        }else if( x >= TankFrame.GAME_WIDTH - E_WIDTH -2){
            this.dir = Dir.LIFT;
        }else if( y >= TankFrame.GAME_HEIGHT - E_THINTH-2 ){
            this.dir = Dir.UP;
        }
    }

    public void fire(){
        int xk =x + E_WIDTH /2 - Bullot.B_WIDTH/2;
        int yk =y + E_THINTH /2 - Bullot.B_THINTH/2;
        tankFrame.listBulls.add(new Bullot(xk,yk,this.dir,this.tankFrame,Group.BLUE.getCode()));
    }


    public void die(){
        live=false;
    }
}
