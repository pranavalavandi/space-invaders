package invadem;

import org.junit.Test;
import static org.junit.Assert.*;

public class TankTest {

    @Test
    // test the tank isn't a null object
    public void testTankConstruction() {
        Tank tank = new Tank(null, 0, 0, 0,0);
        assertNotNull(tank);
    }

    @Test
    // test again whether its not null with proper initialisation
    public void testTankConstruction2() {
        Tank tank = new Tank(App.tankImage, 100, 100, 22,16);
        assertNotNull(tank);
    }

    @Test
    // check the tank has been hit once, check if the shots taken is 1
    public void testTankHit() {
        Tank tank = new Tank(App.tankImage, 100, 100, 22,16);
        tank.tankHit();
        assertTrue(1 == tank.getShotsTaken());
    }

    @Test
    // checking that restore resets the tanks health
    public void testTankReset(){
        Tank tank = new Tank(App.tankImage, 100, 100, 22,16);
        tank.tankHit();
        tank.reset();
        assertEquals(0, tank.getShotsTaken());
    }

    @Test
    // check the tank properly moves by comparing x coordinates
    public void testTankMove(){
        Tank tank = new Tank(App.tankImage, 200, 100, 22,16);
        tank.move(-1);
        assertEquals(199, tank.getX());
    }

    @Test
    // check the tank does not get stuck when it moves to the right extreme
    public void testTankMoveRightExtreme(){
        Tank tank = new Tank(App.tankImage, 180, 100, 22,16);
        tank.move(1);
        assertEquals(181, tank.getX());
    }

    @Test
    // check the tank does not get stuck when it moves to the left extreme
    public void testTankMoveLeftExtreme(){
        Tank tank = new Tank(App.tankImage, 460, 100, 22,16);
        tank.move(-1);
        assertEquals(459, tank.getX());
    }

    @Test
    // test Width getter
    public void testTankWidth(){
        Tank tank = new Tank(App.tankImage, 460, 100, 50,1);
        assertEquals(50, tank.getWidth());
    }

    @Test
    // test Height getter
    public void testTankHeight(){
        Tank tank = new Tank(App.tankImage, 460, 100, 50,1);
        assertEquals(1, tank.getHeight());
    }

    @Test
    // test X getter
    public void testGetX(){
        Tank tank = new Tank(App.tankImage, 460, 100, 50,1);
        assertEquals(460, tank.getX());
    }

    @Test
    // test Y getter
    public void testGetY(){
        Tank tank = new Tank(App.tankImage, 460, 100, 50,1);
        assertEquals(100, tank.getY());
    }

    @Test
    // test that when the tank shoots a projectile a projectile is actually created
    public void testShootProjectile(){
        Tank tank = new Tank(App.tankImage, 460, 100, 16,22);
        ProjectileCollection x = new ProjectileCollection();
        tank.tankShoot(x);
        assertEquals(1, x.getAllProjectiles().size());
    }

    @Test
    // check normal left movement
    public void testMovementLeftBoolean(){
        Tank tank = new Tank(App.tankImage, 300, 100, 16,22);
        tank.moveLeft = true;
        tank.moveTank();
        assertEquals(299, tank.getX());

    }

    @Test
    // check normal right movement
    public void testMovementRightBoolean(){
        Tank tank = new Tank(App.tankImage, 300, 100, 16,22);
        tank.moveRight = true;
        tank.moveTank();
        assertEquals(301, tank.getX());

    }

    @Test
    // check tank does move when both keys are pressed
    public void testMovementBothBoolean(){
        Tank tank = new Tank(App.tankImage, 300, 100, 16,22);
        tank.moveRight = true;
        tank.moveLeft = true;
        tank.moveTank();
        assertEquals(300, tank.getX());

    }

    @Test
    // check the tank is actually dead when hit 3 times
    public void tankDead(){
        Tank tank = new Tank(App.tankImage, 300, 100, 16,22);
        tank.tankHit();
        tank.tankHit();
        tank.tankHit();
        assertEquals(false, tank.getAlive());
    }












//    @Test
//    public void testTankIsNotDead() {
//        Tank tank = new Tank(null, 0, 0);
//        assertEquals(true, tank.isDead());
//    }

}
