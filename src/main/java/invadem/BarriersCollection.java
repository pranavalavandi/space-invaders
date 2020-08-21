package invadem;
import processing.core.PApplet;

import java.util.ArrayList;
public class BarriersCollection {
    private ArrayList<Barrier> allBarriers;
    // static variables to dictate the spacing between the 3 barriers
    private static int barrierSpacing = 90;
    private static int initialPosition = 230;
    // get the barrier height to know when to end the game
    private static int barrierHeight;

    public BarriersCollection(){
        allBarriers = new ArrayList<>();

    }
    // initialise all the barriers with full health in an arrayList
    public void reset(){
        barrierHeight = 420;
        allBarriers.clear();
        for(int i = 0;i<3;i++){
            allBarriers.add(new Barrier(App.barrierLeft1,App.barrierLeft2,App.barrierLeft3, initialPosition+ barrierSpacing*i, 412, 8, 8));
            allBarriers.add(new Barrier(App.barrierTop1,App.barrierTop2,App.barrierTop3,initialPosition + 8 + barrierSpacing*i, 412, 8, 8));
            allBarriers.add(new Barrier(App.barrierRight1,App.barrierRight2,App.barrierRight3,initialPosition + 16 + barrierSpacing*i, 412, 8, 8));
            allBarriers.add(new Barrier(App.barrierSolid1,App.barrierSolid2,App.barrierSolid3,initialPosition + barrierSpacing*i, 420, 8, 8));
            allBarriers.add(new Barrier(App.barrierSolid1,App.barrierSolid2,App.barrierSolid3,initialPosition + 16 + barrierSpacing*i, 420, 8, 8));
            allBarriers.add(new Barrier(App.barrierSolid1,App.barrierSolid2,App.barrierSolid3,initialPosition + barrierSpacing*i, 428, 8, 8));
            allBarriers.add(new Barrier(App.barrierSolid1,App.barrierSolid2,App.barrierSolid3,initialPosition + 16 + barrierSpacing*i, 428, 8, 8));
        }

    }

    public void draw(PApplet app){
        for(Barrier x : allBarriers){
            x.draw(app);
        }
    }

    public ArrayList<Barrier> getAllBarriers() {
        return allBarriers;
    }
    public Barrier getBarrier(int k){
        return allBarriers.get(k);
    }
    public int getBarrierHeight(){
        return barrierHeight;
    }

    // remove a barrier; needed if it has taken 3 hits already
    public void removeBarrier(int k){
        allBarriers.remove(k);
    }
    // given integer change the sprite of that corresponding barrier
    public void changeBarrierSprite(int k){
        allBarriers.get(k).changeSprite();
    }
}

