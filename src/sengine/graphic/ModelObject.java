package sengine.graphic;


import java.util.ArrayList;

public class ModelObject extends AnyObject {
    private final ArrayList<MeshObject> meshes = new ArrayList<>();

    public ModelObject(double Px, double Py, double Pz) {
        super(Px, Py, Pz);
    }

    public ArrayList<MeshObject> getMeshes() {
        return meshes;
    }

    public void addMesh(MeshObject mesh) {
        meshes.add(mesh);
    }

    public void deleteMesh(MeshObject mesh) {
        meshes.remove(mesh);
    }

    public void deleteMesh(int index) {
        meshes.remove(index);
    }
}
