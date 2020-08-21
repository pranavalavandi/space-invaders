package invadem;

import processing.core.PApplet;
import processing.core.PImage;

public class Barrier extends GameObject {

    private PImage[] sprites;       // all the sprites to change the images when the barrier takes damage
    private int hits;               // count the damage the barrier has taken
    private boolean isDead;         // check if the barrier is dead


    public Barrier(PImage barrierImage, PImage damaged1, PImage damaged2, int x, int y, int width, int height){
        super(barrierImage, x, y, width, height);
        sprites = new PImage[]{barrierImage,damaged1,damaged2};
        this.hits = 0;
        this.isDead = false;
    }

    public void draw(PApplet app){
        app.image(super.GameObjectImage,x,y,width,height);
    }

    // getters as usual
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
    public PImage getSprite(){return super.GameObjectImage;}
    public int getHits(){
        return this.hits;
    }
    public boolean dead(){ return this.isDead;}

    // lets the tank take damage, if the hits are greater than 2 then the boolean isDead is converted to true since the barrier is demolished
    public void hit(){
        hits++;
        if(hits == 2){
            isDead = true;
        }
    }

    // changes the sprite based on the number of hits
    public void changeSprite(){
        if(getHits() == 1){
            super.GameObjectImage = this.sprites[1];

        }else if(getHits() == 2){
            super.GameObjectImage = this.sprites[2];
        }
    }



}
