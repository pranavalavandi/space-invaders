package invadem;

import processing.core.PApplet;

import java.util.ArrayList;

public class ProjectileCollection {
    // arrayLists for all the projectiles from the enemy invaders and the friendly tanks
    private ArrayList<Projectile> tankProjectiles;
    private ArrayList<Projectile> invaderProjectiles;



    public ProjectileCollection(){
        //call reset to initialise new arrayLists
        reset();
    }
    public void reset(){
        // create new arrayLists, when the game is over or a new level this will delete all the projectiles
        tankProjectiles = new ArrayList<>();
        invaderProjectiles = new ArrayList<>();
    }

    public void addProjectile(Projectile e){
        // add a projectile, if it is a tank projectile add it to the list of tank projectiles, else add it to the list of invader projectiles
        if(e.getTankProjectile()){
            tankProjectiles.add(e);
        }else{
            invaderProjectiles.add(e);
        }

    }
    // draw method to draw all the projectiles
    public void drawProjectiles(PApplet app){
        // loop through each projectile and call the individual draw method for each of them
        for (Projectile x : getAllProjectiles()){
            x.draw(app);
        }
    }
    // getter methods
    public ArrayList<Projectile> getAllProjectiles(){
        ArrayList<Projectile> x = new ArrayList<>();
        x.addAll(tankProjectiles);
        x.addAll(invaderProjectiles);
        return x;
    }
    public ArrayList<Projectile> getTankProjectiles(){
        return this.tankProjectiles;
    }
    public ArrayList<Projectile> getInvaderProjectiles(){
        return this.invaderProjectiles;
    }

    // group all the collision methods into 1 so only 1 needs to be called in App
    public void collisionProcessing(BarriersCollection allBarriers, InvaderSwarm allInvaders, Tank tank, Tank tank2){
        projectileBarrierCollision(allBarriers);
        projectileInvaderCollision(allInvaders);
        projectileTankCollision(tank);
        if(App.isMultiPlayer()) {
           projectileTankCollision(tank2);
        }
    }

    // If any of the projectiles are out of the screen, then delete them so that the game does not lag. That is if they are
    // higher than 480 or lower than 0
    public void cleanProjectiles(){
        for(int i = 0;i < tankProjectiles.size(); i++){
            if(tankProjectiles.get(i).getY() < 0){
                tankProjectiles.remove(i);
                i -= 1;
            }
        }
        for(int i = 0;i < invaderProjectiles.size(); i++){
            if(invaderProjectiles.get(i).getY() > 480){
                invaderProjectiles.remove(i);
                i -= 1;
            }
        }
    }

    // collision algorithm to detect a collision between two objects
    public <T extends GameObject,S extends GameObject>  boolean Collision (T r1, S r2){
        return (r1.getX() < r2.getX() + r2.getWidth())
                && ((r1.getX() + r1.getWidth()) > r2.getX())
                && (r1.getY() < (r2.getY() + r2.getHeight()))
                && ((r1.getHeight() + r1.getY()) > r2.getY());
    }

    public void projectileInvaderCollision(InvaderSwarm allInvaders) {
        for (int i = 0; i < tankProjectiles.size(); i++) {
            for (int k = 0; k < allInvaders.getInvaders().size(); k++) {
                // if a collision is detected
                if (Collision(tankProjectiles.get(i), allInvaders.getInvaders().get(k))) {
                    allInvaders.invaderTakeDamage(k); // the invader should take damage
                    // relevant score is added to the game
                    if (allInvaders.getInvaders().get(k).getInvaderType().equals("armoured invader") && allInvaders.getInvaders().get(k).isDead()){
                        App.gameScore += 250;
                    } else if(allInvaders.getInvaders().get(k).getInvaderType().equals("power invader")){
                        App.gameScore += 250;
                    }else if(allInvaders.getInvaders().get(k).getInvaderType().equals("regular invader")){
                        App.gameScore += 100;
                    }
                    // if the invader is dead delete it
                    if(allInvaders.getInvaders().get(k).isDead()){
                        allInvaders.removeInvader(k);
                    }
                    // remove the projectile
                    tankProjectiles.remove(tankProjectiles.get(i));
                    // deal with the counter so everything is looped through properly and if a list is empty loop is broken
                    if (i != 0) {
                        i -= 1;
                    }else{
                        break;
                    }

                }
            }
        }
    }

    // collisions between the projectile and tank
    public void projectileTankCollision(Tank tank){
        for (int i = 0;i< invaderProjectiles.size();i++){
            if(Collision( invaderProjectiles.get(i),tank) && ! invaderProjectiles.get(i).getTankProjectile()){
                if(tank.getAlive()) {
                    // if the projectile is width 2 then the tank takes 3 hits, else it takes 1
                    if( invaderProjectiles.get(i).getWidth() == 2){
                        tank.tankHit();
                        tank.tankHit();
                        tank.tankHit();
                    }else {
                        tank.tankHit();
                    }
                }
                // remove the projectile
                 invaderProjectiles.remove(i);
                if(i != 0){
                    i -= 1;
                }
            }
        }
    }

    public void projectileBarrierCollisionAlgorithm(BarriersCollection allBarriers, ArrayList<Projectile> x){
        for (int i = 0; i < x.size(); i++) {
            for (int k = 0; k < allBarriers.getAllBarriers().size(); k++) {
                if(Collision(x.get(i),allBarriers.getBarrier(k))){
                    // if the barrier is dead remove the barrier (also done if the projectile is from the power invader)
                    if(allBarriers.getBarrier(k).dead() || x.get(i).getWidth() == 2){
                        allBarriers.removeBarrier(k);
                        if(k != 0){
                            k -= 1;
                        }
                    }else {
                        // if not let the barrier take damage and change the sprite
                        allBarriers.getBarrier(k).hit();
                        allBarriers.changeBarrierSprite(k);
                    }
                    x.remove(i);
                    if(i != 0){
                        i -= 1;
                    }else{
                        break;
                    }

                }
            }
        }
    }

    // collision between all projectiles and barriers
    public void projectileBarrierCollision(BarriersCollection allBarriers){
        projectileBarrierCollisionAlgorithm(allBarriers, tankProjectiles);
        projectileBarrierCollisionAlgorithm(allBarriers, invaderProjectiles);
    }



}
