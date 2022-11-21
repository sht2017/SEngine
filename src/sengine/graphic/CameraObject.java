package sengine.graphic;

import sengine.math.Vector;

public class CameraObject extends AnyObject {
    private Vector direction = new Vector(3, Vector.Type.columnVector, false, true);
    private Vector top = new Vector(3, Vector.Type.columnVector, false, true);

    public CameraObject(double Px, double Py, double Pz, double Dx, double Dy, double Dz, double Tx, double Ty, double Tz) {
        super(Px, Py, Pz);
        direction.setVectorArray(Dx, Dy, Dz);
        top.setVectorArray(Tx, Ty, Tz);
    }

    public void setDirection(double x, double y, double z) {
        this.direction.setVectorArray(x, y, z);
    }

    public void setTop(double x, double y, double z) {
        this.top.setVectorArray(x, y, z);
    }

    public Vector getDirection() {
        return direction;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }

    public Vector getTop() {
        return top;
    }

    public void setTop(Vector top) {
        this.top = top;
    }

}
