package invadem;

import org.junit.Before;
import org.junit.Test;
import processing.event.KeyEvent;

import static org.junit.Assert.*;

public class AppTest extends App{

    @Before
    public void AppTesting(){
        runSketch(new String[]{"--location=0,0",""}, this);
        delay(3000);
    }

    @Test
    // check space pressed actually creates a projectile
    public void TestSpacePressed() {
        delay(2000);
        keyCode = 32;
        keyPressed();
        this.getTank().tankShoot(this.getAllProjectiles());
        keyReleased();
        assertEquals(1,this.getAllProjectiles().getAllProjectiles().size());

    }

    @Test
    // test when multiplayer is hit the boolean is set to true
    public void testIsMultiplayer(){
        delay(2000);
        keyCode = 77;
        keyPressed();
        keyReleased();
        assertEquals(true, isMultiPlayer());
    }

    @Test
    // test the invaders shoot
    public void testInvaderShoot(){
        delay(5100);
        assertEquals(1, this.getAllProjectiles().getAllProjectiles().size());
    }
    @Test
    // check if hitting left arrow moves tank left
    public void testMoveTankLeft(){
        delay(200);
        key = CODED;
        keyCode = LEFT;
        keyPressed();
        this.moveTank();
        keyReleased();
        assertEquals(319, this.getTank().getX());
    }

    @Test
    // check tank can move right
    public void testMoveTankRight(){
        delay(200);
        key = CODED;
        keyCode = RIGHT;
        keyPressed();
        this.moveTank();
        keyReleased();
        assertEquals(321, this.getTank().getX());
    }

    @Test
    // test second tank can move left
    public void testMoveTank2Left(){
        delay(2000);
        keyCode = 77;
        keyPressed();
        keyReleased();

        keyCode = 65;
        keyPressed();
        this.moveTank();
        keyReleased();
        assertEquals(219, this.getTank2().getX());
    }

    @Test
    // test second tank can move right
    public void testMoveTank2Right(){
        delay(2000);
        keyCode = 77;
        keyPressed();
        keyReleased();

        keyCode = 68;
        keyPressed();
        this.moveTank();
        keyReleased();
        assertEquals(221, this.getTank2().getX());
    }

    @Test
    // test second tank can move right
    public void testGameOver(){
        delay(42000);
        assertEquals(true, this.gameOver());
    }






}
