package invadem;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertArrayEquals;

public class BarrierTest {

    @Test
    // test barrier constructed properly
    public void barrierConstruction() {
        Barrier b = new Barrier(App.barrierLeft1,App.barrierLeft2,App.barrierLeft3, 200, 412, 8, 8);
        b.hit();
        assertNotNull(b);
    }

    @Test
    // test the barrier is still alive when initialise
    public void testBarrierNotDestroyed() {
        Barrier b = new Barrier(App.barrierLeft1,App.barrierLeft2,App.barrierLeft3, 200, 412, 8, 8);
        assertEquals(false, b.dead());
    }

    @Test
    // test the barriers hitpoints changes when hit
    public void testBarrierHitPoints1() {
        Barrier b = new Barrier(App.barrierLeft1,App.barrierLeft2,App.barrierLeft3, 200, 412, 8, 8);
        b.hit();
        assertEquals(1, b.getHits());
    }


    @Test
    // test the barriers hitpoints changes when hit, more than once
    public void testBarrierHitPointsMax() {
        Barrier b = new Barrier(App.barrierLeft1,App.barrierLeft2,App.barrierLeft3, 200, 412, 8, 8);
        b.hit();
        b.hit();
        assertEquals(2, b.getHits());

    }


    @Test
    // test barrier is destroyed when hit too many times
    public void testBarrierIsDestroyed() {
        Barrier b = new Barrier(App.barrierLeft1,App.barrierLeft2,App.barrierLeft3, 200, 412, 8, 8);
        b.hit();
        b.hit();
        b.hit();
        assertEquals(true, b.dead());
    }

    @Test
    // test x getter
    public void testGetX(){
        Barrier b = new Barrier(App.barrierLeft1,App.barrierLeft2,App.barrierLeft3, 200, 412, 8, 8);
        assertEquals(200,b.getX());
    }

    @Test
    // test y getter
    public void testGetY(){
        Barrier b = new Barrier(App.barrierLeft1,App.barrierLeft2,App.barrierLeft3, 200, 412, 8, 8);
        assertEquals(412,b.getY());
    }

    @Test
    // test width getter
    public void testGetWidth(){
        Barrier b = new Barrier(App.barrierLeft1,App.barrierLeft2,App.barrierLeft3, 200, 412, 8, 8);
        assertEquals(8,b.getWidth());
    }

    @Test
    // test height getter
    public void testGetHeight(){
        Barrier b = new Barrier(App.barrierLeft1,App.barrierLeft2,App.barrierLeft3, 200, 412, 8, 10);
        assertEquals(10,b.getHeight());
    }


    @Test
    // test the sprite is changed correctly
    public void testBarriersCollectionChangeSprite(){
        BarriersCollection x = new BarriersCollection();
        x.reset();
        x.getBarrier(0).hit();
        x.changeBarrierSprite(0);

        assertTrue(App.barrierLeft2 ==  x.getBarrier(0).getSprite());

    }

    @Test
    // test the height of the barriers is returned correctly
    public void testBarriersCollectionGetHeight(){
        BarriersCollection x = new BarriersCollection();
        x.reset();
        assertEquals(420,x.getBarrierHeight());
    }

    @Test
    // test a barrier is removed properly. Barriers are manually created and through the overarching class as such
    public void testBarriersCollectionRemoveBarrier(){
        BarriersCollection x = new BarriersCollection();

        ArrayList<Barrier> allBarriers  = new ArrayList<>();
        for(int i = 0;i<3;i++){
            allBarriers.add(new Barrier(App.barrierLeft1,App.barrierLeft2,App.barrierLeft3, 230+ 90*i, 412, 8, 8));
            allBarriers.add(new Barrier(App.barrierTop1,App.barrierTop2,App.barrierTop3,230 + 8 + 90*i, 412, 8, 8));
            allBarriers.add(new Barrier(App.barrierRight1,App.barrierRight2,App.barrierRight3,230 + 16 + 90*i, 412, 8, 8));
            allBarriers.add(new Barrier(App.barrierSolid1,App.barrierSolid2,App.barrierSolid3,230 + 90*i, 420, 8, 8));
            allBarriers.add(new Barrier(App.barrierSolid1,App.barrierSolid2,App.barrierSolid3,230 + 16 + 90*i, 420, 8, 8));
            allBarriers.add(new Barrier(App.barrierSolid1,App.barrierSolid2,App.barrierSolid3,230 + 90*i, 428, 8, 8));
            allBarriers.add(new Barrier(App.barrierSolid1,App.barrierSolid2,App.barrierSolid3,230 + 16 + 90*i, 428, 8, 8));
        }

        x.reset();
        x.removeBarrier(4);

        assertTrue(allBarriers.get(4).getX()  != x.getBarrier(4).getX());
    }

}
