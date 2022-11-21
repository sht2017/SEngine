package sengine.graphic;


import sengine.math.Vector;

import java.util.ArrayList;

public class MeshObject extends AnyObject {
    private final ArrayList<Vector> vertexes = new ArrayList<>();

    public MeshObject(double Px, double Py, double Pz) {
        super(Px, Py, Pz);
    }

    public ArrayList<Vector> getVertexes() {
        return vertexes;
    }

    public void addVertex(double x, double y, double z) {
        addVertex(new Vector(3, Vector.Type.columnVector, false, x, y, z));
    }

    public void addVertex(Vector vector) {
        vertexes.add(vector);
    }
}
