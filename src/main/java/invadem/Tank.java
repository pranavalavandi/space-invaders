package invadem;

import processing.core.PApplet;
import processing.core.PImage;

public class Tank extends GameObject{
    private boolean alive;
    private int shotsTaken;
    public boolean moveLeft = false;
    public boolean moveRight = false;


    public Tank (PImage TankImage, int x, int y, int width, int height){
        super(TankImage,x,y,width,height);
        this.alive = true;
        shotsTaken = 0;


    }
    // reset the tanks status when the game goes to a new level or restarts
    // begins at full health and is alive
    public void reset(){
        this.alive = true;
        this.shotsTaken = 0;
    }

    public int getX(){
        return super.x;
    }
    public int getY(){
        return super.y;
    }
    public int getWidth(){
        return super.width;
    }
    public int getHeight(){
        return super.height;
    }
    public boolean getAlive(){
        return this.alive;
    }
    public int getShotsTaken(){return this.shotsTaken;}

    // change the tanks sprite, used for multiplayer mode to change the tank colour to blue
    public void changeSprite(PImage p){
        super.GameObjectImage = p;
    }
    public void draw(PApplet app){
        app.image(super.GameObjectImage,x,y,width,height);
    }

    //ove the tank by the integer amount prescribed
    public void move(int movement){
        if(this.x > 180 && this.x < 460){
            this.x += movement;
        }else if(this.x == 180 && movement == 1){
            this.x = 181; // tank cant move left past 180
        }else if(this.x == 460 && movement == -1){
            this.x = 459; // tank cant move right past 460
        }
    }

    // tank takes damage, if the damage is more than 3 the tank is then dead
    public void tankHit(){
        shotsTaken++;
        if(shotsTaken >= 3){
            this.alive = false;
        }
    }
    // dictate the movement required based on the booleans that are set in the app method
    public void moveTank(){
        if (moveLeft && moveRight) {
            move(0);
        } else if (moveRight) {
            move(1);
        } else if (moveLeft) {
            move(-1);
        }
    }

    // when spacebar is hit the tank shoots a projectile, this is where it happens. Projectiles are stored in the Projectile Collection
    public void tankShoot(ProjectileCollection allProjectiles){
        allProjectiles.addProjectile(new Projectile(App.projectileImage, getX() + 11, getY(), 1, 3, true));
    }

}
