package com.example;

import lombok.Data;

import java.awt.*;
import java.security.cert.TrustAnchor;

@Data
public class Bullot {

    private int x,y;

    private Dir dir=Dir.DOWN;

    private Boolean live=true;

    private TankFrame tk=null;

    private String group ;
    private static final int NUM = 40;
    //定义子弹的正方形位置
    Rectangle bulltR= new Rectangle();

    public static final int B_WIDTH = ResourceMges.bulltU.getWidth();
    public static final int B_THINTH = ResourceMges.bulltU.getHeight();
    public Bullot(int x, int y, Dir dir,TankFrame tk,String group) {
        super();
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tk = tk;
        this.group = group;
    }
    public void paint(Graphics g) {
        if(!live){
             tk.listBulls.remove(this);
        }
        switch (dir) {
            case LIFT:
                g.drawImage(ResourceMges.bulltL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMges.bulltR,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMges.bulltU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMges.bulltD,x,y,null);
                break;
            default:
                break;
        }
       /* Color color = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, WIDTH, THINTH);
        g.setColor(color);*/
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
        if(x<0||y<0||x > TankFrame.GAME_HEIGHT|| y>TankFrame.GAME_WIDTH){
            this.live = false;
        }
        //todo x y,有变化时从新赋值
        bulltR.setRect(x,y,B_WIDTH,B_THINTH);

    }
    public  void collison(TankEnemy enemy){
        //todo 如果是自己的子弹和坦克就返回
        if(enemy.getGroup().equals(this.group)){
            return;
        }
        //todo 怎么让这个正方形只new 一次
        //Rectangle bulltR = new Rectangle(x,y,B_WIDTH,B_THINTH);
        Rectangle emenTankR = enemy.getEmenTankR();//new Rectangle(enemy.getX(),enemy.getY(),TankEnemy.E_WIDTH,TankEnemy.E_THINTH);
        if(bulltR.intersects(emenTankR)){
            tk.explodeList.add(new Explode(enemy.getX(),enemy.getY(),this.tk));
            this.die();
            enemy.die();
        }
    }
    public void die(){
        live=false;
    }
}
