package invadem;

import processing.core.PApplet;
import processing.core.PImage;

public class Invader extends GameObject {

    private int count = 0;
    private PImage[] sprites;
    private int[] velocity = {1,-1};
    private boolean frame = true; // boolean to draw the Invaders explained later
    private String invaderName;
    private int hitPoints;
    private int damage;
    private boolean isDead;



    public Invader (PImage invaderImage, PImage sprite1, int x, int y, int width, int height, String name){
        super(invaderImage,x, y, width, height);
        this.sprites = new PImage[]{invaderImage, sprite1};
        this.invaderName = name;
        // initialise the Invaders, giving them their damage and their health based on the name provided
        if(name.equals("armoured invader")){
            this.hitPoints = 3;
            this.damage = 1;
        }else if(name.equals("power invader")){
            this.hitPoints = 1;
            this.damage = 3;
        }else if(name.equals("regular invader")){
            this.hitPoints = 1;
            this.damage = 1;
        }

        this.isDead = false;


    }

    public void draw(PApplet app){
        // draw is done only every second frame as required through the this.frame boolean. which swaps everyframe
        // and a frame is drawn if and only if the boolean is true
        app.image(super.GameObjectImage,x,y,width,height);
        if(this.frame) {
            tick();
        }
        this.frame = !this.frame;
    }
    //getters
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
    public int getHitPoints(){return this.hitPoints;}
    public String getInvaderType(){return this.invaderName;}
    public boolean isDead(){
        return this.isDead;
    }
    public PImage getSprite(){return super.GameObjectImage;}


    // Invader takes damage. If the hitpoints left are 0 then the invader is dead.
    public void takeDamage(){
        this.hitPoints -= 1;
        if(this.hitPoints == 0){
            this.isDead = true;
        }
    }

    // change the sprite, required to change as the invader is moving down
    public void changeSprite(int x){
        super.GameObjectImage = this.sprites[x];
    }

    // shoot a projectile based on the invader type, shoot a different projectile as required
    public Projectile shoot(){
        if(getInvaderType().equals("armoured invader") ||getInvaderType().equals("regular invader")){
            return new Projectile(App.projectileImage,getX() + 8,  getY() + 8,1,3, false);

        }else{
            return new Projectile(App.projectileImage,getX() + 8,  getY() + 8,2,5, false);

        }
    }

    // for the first 30 frames move right
    // for the next 8 frames move down and change the sprite so legs are open
    // for the next 30 frames move left
    // for the next 8 frames move down
    // repeat
    public void tick(){
        if(this.count <= 30){
            changeSprite(0);
            this.x += velocity[0];
            this.count++;
        }else if(this.count <= 38){
            changeSprite(1);
            this.y += velocity[0];
            this.count++;
        }else if(this.count <= 68){
            changeSprite(0);
            this.x += velocity[1];
            this.count++;
        }else if(this.count <= 74){
            changeSprite(1);
            this.y += velocity[0];
            this.count++;
        }

        if(this.count == 75){
            // set count to 0 so process can begin again
            this.count = 0;
        }
    }


}
