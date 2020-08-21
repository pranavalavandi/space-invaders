package invadem;

import org.junit.Test;
import static org.junit.Assert.*;

public class ProjectileTest {


    @Test
    // test object isnt null
    public void testProjectileConstruction() {
        Projectile proj = new Projectile(App.projectileImage, 30, 100, 1, 3, true);
        assertNotNull(proj);
    }

    @Test
    // test isnt null with proper initialisation
    public void testProjectileIsFriendly() {
        Projectile proj = new Projectile(App.projectileImage, 30, 100, 1, 3, true);
        assertTrue(proj.getTankProjectile());
    }

    @Test
    // test that an invader projectile isnt friendly
    public void testProjectileIsNotFriendly() {
        Projectile proj = new Projectile(App.projectileImage, 30, 100, 1, 3, false);
        assertFalse(proj.getTankProjectile());
    }

    @Test
    // test X getter
    public void testProjectileGetX() {
        Projectile proj = new Projectile(App.projectileImage, 30, 100, 1, 3, false);
        assertEquals(30,proj.getX());
    }

    @Test
    // test Y getter
    public void testProjectileGetY() {
        Projectile proj = new Projectile(App.projectileImage, 30, 100, 1, 3, false);
        assertEquals(100,proj.getY());
    }


    @Test
    // test Width getter
    public void testProjectileGetWidth() {
        Projectile proj = new Projectile(App.projectileImage, 30, 100, 1, 3, false);
        assertEquals(1,proj.getWidth());
    }

    @Test
    // test Height getter
    public void testProjectileGetHeight() {
        Projectile proj = new Projectile(App.projectileImage, 30, 100, 1, 5, false);
        assertEquals(5,proj.getHeight());
    }

    @Test
    // test a tank projectile moves properly
    public void testTankProjectileTick() {
        Projectile proj = new Projectile(App.projectileImage, 30, 100, 1, 5, true);
        proj.tick();
        assertEquals(99,proj.getY());
    }

    @Test
    // test invader projectile moves properly
    public void testInvaderProjectileTick() {
        Projectile proj = new Projectile(App.projectileImage, 30, 100, 1, 5, false);
        proj.tick();
        assertEquals(101,proj.getY());
    }

    @Test
    // test reset makes new arraylists for both projectiles
    public void testResetProjectileCollection(){
        ProjectileCollection x = new ProjectileCollection();
        x.addProjectile(new Projectile(App.projectileImage, 30, 100, 1, 5, false));
        x.reset();
        assertEquals(0, x.getInvaderProjectiles().size());
    }

    @Test
    // test reset makes new arraylists for both projectiles
    public void testResetProjectileCollection2(){
        ProjectileCollection x = new ProjectileCollection();
        x.addProjectile(new Projectile(App.projectileImage, 30, 100, 1, 5, true));
        x.reset();
        assertEquals(0, x.getTankProjectiles().size());
    }

    @Test
    // test projectiles out of range are deleted
    public void testCleanProjectiles(){
        ProjectileCollection x = new ProjectileCollection();
        x.addProjectile(new Projectile(App.projectileImage, 30, -1, 1, 5, true));
        x.addProjectile(new Projectile(App.projectileImage, 50, -1, 1, 5, true));
        x.addProjectile(new Projectile(App.projectileImage, 30, 481, 1, 5, false));
        x.addProjectile(new Projectile(App.projectileImage, 50, 481, 1, 5, false));

        x.cleanProjectiles();
        assertEquals(0, x.getAllProjectiles().size());
    }

    @Test
    // test collision algorithm works
    public void testGameObjectCollision(){
        Tank tank = new Tank(App.tankImage, 30, 100, 16,22);
        ProjectileCollection x = new ProjectileCollection();
        x.addProjectile(new Projectile(App.projectileImage, 30, 100, 1, 5, false));
        assertTrue(x.Collision(tank, x.getInvaderProjectiles().get(0)));
    }

    @Test
    // test a collision between projectile and tank
    public void testProjectileTankCollision(){
        Tank tank = new Tank(App.tankImage, 30, 100, 16,22);
        ProjectileCollection x = new ProjectileCollection();
        x.addProjectile(new Projectile(App.projectileImage, 30, 100, 1, 5, false));
        x.projectileTankCollision(tank);
        assertEquals(0,x.getInvaderProjectiles().size());
    }

    @Test
    // test an array of projectiles where only some of them collide
    public void testProjectileTankCollision2(){
        Tank tank = new Tank(App.tankImage, 30, 100, 16,22);
        ProjectileCollection x = new ProjectileCollection();
        x.addProjectile(new Projectile(App.projectileImage, 30, 100, 2, 5, false));
        x.addProjectile(new Projectile(App.projectileImage, 40, 100, 1, 5, false));
        x.projectileTankCollision(tank);
        assertEquals(1,x.getInvaderProjectiles().size());
    }

    @Test
    // test collision between projectile and barrier
    public void testProjectileBarrierCollision(){
        BarriersCollection x = new BarriersCollection();
        x.reset();
        ProjectileCollection y = new ProjectileCollection();

        y.addProjectile(new Projectile(App.projectileImage, 230, 412, 2, 5, false));
        y.addProjectile(new Projectile(App.projectileImage, 230, 420, 1, 3, true));
        y.addProjectile(new Projectile(App.projectileImage, 238, 300, 1, 3, true));
        y.addProjectile(new Projectile(App.projectileImage, 238, 300, 2, 5, false));

        y.projectileBarrierCollision(x);

        assertEquals(2, y.getAllProjectiles().size());
    }

    @Test
    // test a barrier is removed when it is collided with 3 times
    public void testProjectileBarrierCollisionRemovingBarrier(){
        BarriersCollection x = new BarriersCollection();
        x.reset();
        ProjectileCollection y = new ProjectileCollection();
        y.addProjectile(new Projectile(App.projectileImage, 230, 412, 1, 3, false));
        y.projectileBarrierCollision(x);
        y.addProjectile(new Projectile(App.projectileImage, 230, 412, 1, 3, false));
        y.projectileBarrierCollision(x);
        y.addProjectile(new Projectile(App.projectileImage, 230, 412, 1, 3, false));
        y.projectileBarrierCollision(x);

        assertEquals(0, x.getBarrier(0).getHits());
    }

    @Test
    // test an armoured invader doesnt die, but takes 1 hit if collided with
    public void testProjectileArmouredInvaderCollision(){
        ProjectileCollection y = new ProjectileCollection();
        InvaderSwarm x = new InvaderSwarm();
        x.reset();
        y.addProjectile(new Projectile(App.projectileImage, 185, 55, 2, 5, true));
        y.projectileInvaderCollision(x);
        assertEquals(2, x.getInvaders().get(0).getHitPoints());
    }

    @Test
    // test a non armoured invader instantly dies when collided with
    public void testProjectileNotArmouredInvaderCollision(){
        ProjectileCollection y = new ProjectileCollection();
        InvaderSwarm x = new InvaderSwarm();
        x.reset();
        y.addProjectile(new Projectile(App.projectileImage, 185, 85, 2, 5, true));
        y.projectileInvaderCollision(x);
        assertEquals(1, x.getInvaders().get(10).getHitPoints()); //invader should be removed
    }












//    @Test
//    public void testProjectileIntersect() {
//        Projectile proj = /* Your Constructor Here */
//        Invader inv = /* Your Constructor Here */
//        Tank tank = /* Your Constructor Here */
//        assertFalse(proj.intersect(inv));
//        assertFalse(proj.intersect(tank));
//    }

}
