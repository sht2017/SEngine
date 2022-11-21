package sengine.graphic;

import sengine.math.Vector;

public class AnyObject {
    private Vector position = new Vector(3, Vector.Type.columnVector, false);

    public AnyObject(double x, double y, double z) {
        position.setVectorArray(x, y, z);
    }

    public static void main(String[] args) {
        AnyObject o = new AnyObject(0, 0, 0);
    }

    public Vector getPosition() {
        return position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public void setPosition(double x, double y, double z) {
        this.position.setVectorArray(x, y, z);
    }
}
