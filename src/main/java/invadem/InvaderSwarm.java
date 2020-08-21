package invadem;

import java.util.ArrayList;
import processing.core.PApplet;


public class InvaderSwarm{
    private ArrayList<Invader> invaderCollection;
    private int widthSpacing = 26;
    private int heightSpacing = 30;

    public InvaderSwarm(){
        this.invaderCollection = new ArrayList<>();
    }

    // initialise all the Invaders in their formation with full health
    // Row 1: Armoured invader. Row 2: power invader. Row 3/4: regular invader
    public void reset(){
        this.invaderCollection.clear();
        for(int i = 0; i< 4; i++){
            for (int k = 0; k< 10; k++){
                if(i == 0){
                    this.invaderCollection.add(new Invader(App.invaderArmoured1,App.invaderArmoured2, 185 + widthSpacing * k, 55 + heightSpacing * i, 16, 16, "armoured invader"));
                }else if(i == 1){
                    this.invaderCollection.add(new Invader(App.invaderPowered1,App.invaderPowered2, 185 + widthSpacing * k, 55 + heightSpacing * i, 16, 16, "power invader"));
                }else {
                    this.invaderCollection.add(new Invader(App.invaderRegular1,App.invaderRegular2, 185 + widthSpacing * k, 55 + heightSpacing * i, 16, 16, "regular invader"));
                }
            }
        }
    }

    public ArrayList<Invader> getInvaders(){
        return this.invaderCollection;
    }
    // get the lowest invader to know when to end the game when the invaders are too low
    public int getLowestInvader(){
        int height = 0;
        for(Invader x : invaderCollection){
            if(x.getY() >= height){
                height = x.getY();
            }
        }
        return height;
    }

    public void draw(PApplet app){
        for(Invader x : this.invaderCollection){
            x.draw(app);
        }
    }

    // remove an invader if it is dead
    public void removeInvader(int k){
        this.invaderCollection.remove(k);
    }
    // let the corresponding invader take damage
    public void invaderTakeDamage(int k){
        this.invaderCollection.get(k).takeDamage();
    }

    // gives a random invader object that will shoot
    public Invader getShootingInvader(){
        return this.invaderCollection.get((int)(Math.random() * ((this.invaderCollection.size()))));
    }

    // create a new projectile based on that invaders location
    public void shootProjectile(ProjectileCollection allProjectiles){
        Invader token = getShootingInvader();
        allProjectiles.addProjectile(token.shoot());
    }
}
