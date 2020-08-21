package invadem;

import processing.core.PApplet;
import processing.core.PImage;


// super class, that is abstract, ensures that all the objects created have the following variables, and all the classes implement
// the following methods
public abstract class GameObject {
    protected PImage GameObjectImage;
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public GameObject(PImage GameObjectImage, int x, int y, int width, int height){
        // object images
        this.GameObjectImage = GameObjectImage;
        // coordinates
        this.x = x;
        this.y = y;
        //dimensions of the object
        this.width = width;
        this.height = height;
    }
    // getters and draw method
    public abstract void draw(PApplet app);
    public abstract int getX();
    public abstract int getY();
    public abstract int getWidth();
    public abstract int getHeight();

}


