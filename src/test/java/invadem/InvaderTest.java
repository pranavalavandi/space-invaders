package invadem;

import org.junit.Test;
import static org.junit.Assert.*;


public class InvaderTest {

    @Test
    // test construction isnt null
    public void testArmouredInvaderConstruction() {
        Invader x = new Invader(App.invaderArmoured1,App.invaderArmoured2, 185, 55, 16, 16, "armoured invader");
        assertNotNull(x);

    }

    @Test
    // test proper construction isnt null- power invader
    public void testPowerInvaderConstruction() {
        Invader x = new Invader(App.invaderArmoured1,App.invaderArmoured2, 185, 55, 16, 16, "power invader");
        assertNotNull(x);
    }

    @Test
    // test proper construction isnt null- regular invader
    public void testRegularInvaderConstruction() {
        Invader x = new Invader(App.invaderArmoured1,App.invaderArmoured2, 185, 55, 16, 16, "regular invader");
        assertNotNull(x);
    }


    @Test
    // test projectile fired isnt null
    public void testRegularInvaderFireProjectile() {
        Invader x = new Invader(App.invaderArmoured1,App.invaderArmoured2, 185, 55, 16, 16, "regular invader");
        assertNotNull(x.shoot());
    }

    @Test
    // test projectile fired isnt null- power invader
    public void testPowerInvaderFireProjectile() {
        Invader x = new Invader(App.invaderArmoured1,App.invaderArmoured2, 185, 55, 16, 16, "power invader");
        assertNotNull(x.shoot());
    }

    @Test
    // test x getter
    public void testGetX() {
        Invader x = new Invader(App.invaderArmoured1,App.invaderArmoured2, 185, 55, 16, 16, "regular invader");
        assertEquals(185,x.getX());
    }

    @Test
    // test y getter
    public void testGetY() {
        Invader x = new Invader(App.invaderArmoured1,App.invaderArmoured2, 185, 55, 16, 16, "regular invader");
        assertEquals(55,x.getY());
    }

    @Test
    // test width getter
    public void testGetWidth() {
        Invader x = new Invader(App.invaderArmoured1,App.invaderArmoured2, 185, 55, 16, 18, "regular invader");
        assertEquals(16,x.getWidth());
    }

    @Test
    // test height getter
    public void testGetHeight() {
        Invader x = new Invader(App.invaderArmoured1,App.invaderArmoured2, 185, 55, 16, 18, "regular invader");
        assertEquals(18,x.getHeight());
    }

    @Test
    // test invader health is 0 when damage taken
    public void takeDamage() {
        Invader x = new Invader(App.invaderArmoured1,App.invaderArmoured2, 185, 55, 16, 18, "regular invader");
        x.takeDamage();
        assertEquals(0,x.getHitPoints());
    }

    @Test
    // test invader health is 3 when damage taken; armoured invader
    public void takeDamageArmoured() {
        Invader x = new Invader(App.invaderArmoured1,App.invaderArmoured2, 185, 55, 16, 18, "armoured invader");
        x.takeDamage();
        x.takeDamage();
        assertEquals(1,x.getHitPoints());
    }

    @Test
    // test invader dead boolean works
    public void testInvaderDead() {
        Invader x = new Invader(App.invaderArmoured1,App.invaderArmoured2, 185, 55, 16, 18, "regular invader");
        x.takeDamage();
        assertEquals(true, x.isDead());
    }

    @Test
    // test the armoured invader is alive after one shot
    public void testInvaderAlive() {
        Invader x = new Invader(App.invaderArmoured1,App.invaderArmoured2, 185, 55, 16, 18, "regular invader");
        assertEquals(false, x.isDead());
    }

    @Test
    // test the invaders move correctly in the game
    public void testTick(){
        Invader x = new Invader(App.invaderArmoured1,App.invaderArmoured2, 185, 55, 16, 18, "armoured invader");
        for(int i = 0;i<=74;i++){
            if(i <= 30){
                x.tick();
                assertEquals(App.invaderArmoured1, x.getSprite());
            }else if(i <= 38){
                x.tick();
                assertEquals(App.invaderArmoured2, x.getSprite());
            }else if(i <= 68){
                x.tick();
                assertEquals(App.invaderArmoured1, x.getSprite());
            }else if(i <= 74){
                x.tick();
                assertEquals(App.invaderArmoured2, x.getSprite());
            }
        }
    }

    @Test
    // test swarm is constructed properly
    public void testInvaderSwarmConstruction(){
        InvaderSwarm x = new InvaderSwarm();
        assertNotNull(x);
    }

    @Test
    // test a regular invader element is as required
    public void testInvaderSwarmElements1(){
        InvaderSwarm x = new InvaderSwarm();
        x.reset();
        assertTrue("regular invader".equals(x.getInvaders().get(38).getInvaderType()));
    }

    @Test
    // test a armoured invader element is as required
    public void testInvaderSwarmElements2(){
        InvaderSwarm x = new InvaderSwarm();
        x.reset();
        assertTrue("armoured invader".equals(x.getInvaders().get(1).getInvaderType()));
    }

    @Test
    // test a regular invader element is as required
    public void testInvaderSwarmElements3(){
        InvaderSwarm x = new InvaderSwarm();
        x.reset();
        assertTrue("power invader".equals(x.getInvaders().get(13).getInvaderType()));
    }

    @Test
    // test the get lowest invader method produces the right integer
    public void testGetLowestInvader(){
        InvaderSwarm x = new InvaderSwarm();
        x.reset();
        assertTrue(145 == x.getLowestInvader());
    }

    @Test
    // test invaderTakeDamage works
    public void testInvaderSwarmTakeDamage(){
        InvaderSwarm x = new InvaderSwarm();
        x.reset();
        x.invaderTakeDamage(5);
        assertTrue(2 == x.getInvaders().get(5).getHitPoints());
    }

    @Test
    // test a correct invader is correctly removed
    public void testRemoveInvader(){
        InvaderSwarm x = new InvaderSwarm();
        x.reset();
        x.removeInvader(0);
        assertTrue(185 != x.getInvaders().get(0).getX()); // 185 is the x coordinate of the removed invader
    }

    @Test
    // test getting the shooting invader doesn't return a null value
    public void testGetShootingInvader(){
        InvaderSwarm x = new InvaderSwarm();
        x.reset();
        assertNotNull(x.getShootingInvader());
    }

    @Test
    // test a projectile is actually shot by the invaders
    public void testShootProjectile(){
        ProjectileCollection y = new ProjectileCollection();
        InvaderSwarm x = new InvaderSwarm();
        x.reset();
        x.shootProjectile(y);
        assertTrue(1 == y.getAllProjectiles().size());
    }

}
