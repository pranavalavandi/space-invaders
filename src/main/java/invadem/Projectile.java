package invadem;

import processing.core.PApplet;
import processing.core.PImage;

public class Projectile extends GameObject {
    private boolean tankProjectile;


    public Projectile(PImage ProjectileImage, int x, int y, int width, int height, boolean h){
        super(ProjectileImage,x,y,width,height);
        this.tankProjectile = h;
    }

    // change the y coordinate of the Projectile to simulate movement. If its a tank projectile make it move up
    // if its an invader projectile make it move down
    public void tick(){
        if(this.tankProjectile) {
            this.y -= 1;
        }else{
            this.y += 1;
        }
    }
    public void draw(PApplet app) {
        app.image(super.GameObjectImage,x,y,width,height);
        tick();
    }

    //getters
    public int getX(){
        return super.x;
    }
    public int getY(){
        return  super.y;
    }
    public int getWidth(){
        return super.width;
    }
    public int getHeight(){
        return super.height;
    }
    public boolean getTankProjectile(){
        return this.tankProjectile;
    }

}
