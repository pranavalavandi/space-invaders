package invadem;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

import java.util.ArrayList;

public class App extends PApplet {

    // all object needed for the operation of the game
    private Tank tank;
    private Tank tank2;
    private ProjectileCollection allProjectiles;
    private BarriersCollection allBarriers;
    private InvaderSwarm allInvaders;

    // all variables required for the tank to shoot only once when the spacebar is pressed
    private boolean spacePressed = false;
    private boolean spacePressed2 = false;
    private boolean bulletShot = false;
    private boolean bulletShot2 = false;

    // all variables local to the game, allowing the screen to change, the levels to get harder, having a highscore in the game, and enabling features with multiplayer mode
    private int gameTimer = 0;
    private int level = 1;
    private int invaderShoot = 0;
    public static int gameScore = 0;
    private int gameHighScore = 10000;
    private static boolean multiPlayer = false;
    private int ammo = 250;


    // loading all images as instance variables so that each overarching class can instantiate their own objects
    private PImage gameOver;
    private PImage nextLevel;
    public static PImage tankImage;
    public static PImage tankImageBlue;
    public static PImage tankImageRed;
    public static PImage projectileImage;
    public static PImage barrierLeft1;
    public static PImage barrierLeft2;
    public static PImage barrierLeft3;
    public static PImage barrierTop1;
    public static PImage barrierTop2;
    public static PImage barrierTop3;
    public static PImage barrierRight1;
    public static PImage barrierRight2;
    public static PImage barrierRight3;
    public static PImage barrierSolid1;
    public static PImage barrierSolid2;
    public static PImage barrierSolid3;
    public static PImage invaderArmoured1;
    public static PImage invaderArmoured2;
    public static PImage invaderPowered1;
    public static PImage invaderPowered2;
    public static PImage invaderRegular1;
    public static PImage invaderRegular2;


    public App() {
        //Set up your objects

        allInvaders = new InvaderSwarm();
        allProjectiles = new ProjectileCollection();
        allBarriers = new BarriersCollection();

    }

    public void setup() {
        frameRate(60);

        //load all the images, while consuming a lot of lines best way to allocate instantiation to their own classes.
        tankImage = loadImage("tank1.png");
        tankImageBlue = loadImage("tankblue.png");
        tankImageRed = loadImage("tankred.png");
        tank = new Tank(tankImage, 320, 450, 22, 16);
        tank2 = new Tank(tankImageRed, 220, 450, 22, 16);
        projectileImage = loadImage("projectile.png");
        barrierLeft1 = loadImage("barrier_left1.png");
        barrierLeft2 = loadImage("barrier_left2.png");
        barrierLeft3 = loadImage("barrier_left3.png");
        barrierTop1 = loadImage("barrier_top1.png");
        barrierTop2 = loadImage("barrier_top2.png");
        barrierTop3 = loadImage("barrier_top3.png");
        barrierRight1 = loadImage("barrier_right1.png");
        barrierRight2 = loadImage("barrier_right2.png");
        barrierRight3 = loadImage("barrier_right3.png");
        barrierSolid1 = loadImage("barrier_solid1.png");
        barrierSolid2 = loadImage("barrier_solid2.png");
        barrierSolid3 = loadImage("barrier_solid3.png");
        invaderArmoured1 = loadImage("invader1_armoured.png");
        invaderArmoured2 = loadImage("invader2_armoured.png");
        invaderPowered1 = loadImage("invader1_power.png");
        invaderPowered2 = loadImage("invader2_power.png");
        invaderRegular1 = loadImage("invader1.png");
        invaderRegular2 = loadImage("invader2.png");
        gameOver = loadImage("gameover.png");
        nextLevel = loadImage("nextlevel.png");

        //loading the font for the score and highscore to be displayed along with multiplayer features
        PFont gameFont = createFont("PressStart2P-Regular.ttf", 10);
        textFont(gameFont);
        restartGame();

    }

    public Tank getTank() {
        return tank;
    }
    public Tank getTank2() {
        return tank2;
    }

    public ProjectileCollection getAllProjectiles(){
        return allProjectiles;
    }

    // getter method to check whether multiplayer mode is enabled
    public static boolean isMultiPlayer() {
        return multiPlayer;
    }

    // method to restart the game on a new level or if the game is over.
    public void restartGame() {
        allBarriers.reset();                // all barriers are redrawn at full health
        allInvaders.reset();                // all invaders are redrawn at the top with full health
        allProjectiles.reset();             // all projectile lists are emptied
        tank.reset();                       // both tanks are returned to the centre with full health
        tank2.reset();
        //ammo reduces by 50 each level starting at 250. As such when the game is restart the ammo is also reset
        ammo = 100 + 50*(5-level);
        gameTimer++;
        //timer on invaders shooting is set to 0
        invaderShoot = 0;
    }

    public void settings() {
        size(640, 480);
    }



    public void tankShoot(){
        //pass in the ProjectileCollection Object so that projectiles can be added to the list of tank projectiles
        tank.tankShoot(allProjectiles);
        // if multiplayer mode is enabled then reduce the ammunition available by one
        if(multiPlayer) {
            ammo -= 1;
        }
    }
    public void tank2Shoot(){
        //pass in the ProjectileCollection Object so that projectiles can be added to the list of tank projectiles
        tank2.tankShoot(allProjectiles);
        // if multiplayer mode is enabled then reduce the ammunition available by one
        if(multiPlayer) {
            ammo -= 1;
        }
    }

    public void projectileShoot(){
        // check if the counter is right, i.e. checking if the right amount of seconds has passed since the last projectile was shot by an invader
        // before shooting the next projectile
        if(invaderShoot == (5-level+1) * 60) {
            // pass in allProjectiles so it can be added to an array of invaderProjectiles
            allInvaders.shootProjectile(allProjectiles);
            invaderShoot = 0;
        }
        //bump the invader shoot counter
        invaderShoot++;
    }

    public void keyPressed() {
        if(key == CODED) {
            // if the key is left or right, define the instance variables dictating which way to move.
            // these were considering being made private but since all the method would do is change it to the opposite boolean value it was much more efficient to make it public
            if (keyCode == LEFT) {
                tank.moveLeft = true;
            } else if (keyCode == RIGHT) {
                tank.moveRight = true;
            }
        }if(keyCode == 32){             // if tank1 presses spacebar change spacePressed to true
            spacePressed = true;
        }
        if(keyCode == 87){              // if tank2 spacebar is pressed (w) change spacePressed2 to true
            spacePressed2 = true;
        }if(keyCode == 65){             // check if tank2 wants to move left (a)
            tank2.moveLeft = true;
        }else if(keyCode == 68){        //tank 2 move right (d)
            tank2.moveRight = true;
        }
        if(keyCode == 77){              // if m is hit turn multiplayer on
            multiPlayer = true;
        }
    }

    // identical method to above, now setting all the booleans to false if the key is released
    public void keyReleased() {
        if(key == CODED) {
            if (keyCode == LEFT) {
                tank.moveLeft = false;
            } else if (keyCode == RIGHT) {
                tank.moveRight = false;
            }
        }
        if(keyCode == 32){
            bulletShot = false;
            spacePressed = false;
        }if(keyCode == 87){
            bulletShot2 = false;
            spacePressed2 = false;

        }if(keyCode == 65){
            tank2.moveLeft = false;
        }else if(keyCode == 68){
            tank2.moveRight = false;
        }
    }

    // call both tank.moveTank() functions to move both the tanks
    public void moveTank(){
        tank.moveTank();
        if(multiPlayer) {
            tank2.moveTank();
        }
    }

    // change the image of the first tank to blue, and output multiplayer on and the ammunition left
    public void multiPlayer(){
        tank.changeSprite(tankImageBlue);
        text("Multiplayer ON", 15,200);
        text("Ammo: " + ammo, 15,250);
    }

    public boolean gameOver(){
        // if neither tank is alive or the invaders are too low, return true meaning the game is over
        if(!tank.getAlive() || !tank2.getAlive() || ammo == 0 || (allInvaders.getLowestInvader() + 16 + 20) == allBarriers.getBarrierHeight()){
            return true;
        }else {
        // if not return false
            return false;
        }
    }

    public boolean gameWon(){
        // return gameWon = true if all the invaders have been killed, meaning the size of the invaders array is 0
        if(allInvaders.getInvaders().size() == 0){
            return true;
        }
        return false;
    }


    public void score(){
        // print out the gamescore
        text("Game Score: " + gameScore, 15, 20);
    }

    public void gameHighScore(){
        // check if the highscore is lower then the game score, if so change it and print out the highscore
        if(gameHighScore < gameScore) gameHighScore = gameScore;
        String s2 = "High Score: " + gameHighScore;
        text(s2, 15,40);
    }


    public void draw() {
        background(0);
        if(gameWon() && gameTimer < 180){
            // if the game is won output the next level screen and hold it for 3 seconds
            image(nextLevel,259,236);
            gameTimer++;
        }else if(gameOver() && gameTimer < 180){
            // if the game is over output the game over screen, reset the game level to 1 and the game score to 0
            gameHighScore();
            image(gameOver,259,236);
            gameTimer++;
            level = 1;
            gameScore = 0;

        }else if(gameTimer == 180){
            // once the 3 seconds has passed regardless of whether the game has been won or lost, restart the game and if the game has been won then bump the level
            restartGame();
            if(gameWon()) {
                if (level < 5) {
                    level++;
                } else {
                    level = 5;
                }
            }

        }else{
            // if neither then continue the game functionality normally
            moveTank();
            allProjectiles.collisionProcessing(allBarriers,allInvaders,tank,tank2);     // pass all objects to deal with collisions
            projectileShoot();                                                          // shoot projectiles from invaders
            allProjectiles.cleanProjectiles();                                          // clean the projectile list if out of game range to reduce the lag in game
            if (spacePressed && !bulletShot) {
                //shoot bullet and change the boolean to true so spacebar cannot be held to spam shoot
                tankShoot();
                bulletShot = true;
            }

            tank.draw(this);

            if(multiPlayer) {
                // if multiplayer is enabled, then able the same shooting functionality, and draw the second tank as well
                multiPlayer();
                if (spacePressed2 && !bulletShot2) {
                    tank2Shoot();
                    bulletShot2 = true;
                }
                tank2.draw(this);
            }
            // draw all other nontank objects
            allBarriers.draw(this);
            allProjectiles.drawProjectiles(this);
            allInvaders.draw(this);
            // set the game timer to 0
            gameTimer = 0;
        }
        // print the game score and the game high score
        score();
        gameHighScore();
    }

    public static void main(String[] args) {
        PApplet.main("invadem.App");
    }

}
