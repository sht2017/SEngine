package sengine.basic;

import sengine.graphic.CameraObject;
import sengine.graphic.ModelObject;

import java.util.ArrayList;

public class World {
    private ArrayList<ModelObject> models;
    private CameraObject activatedCamera;

    public CameraObject getActivatedCamera() {
        return activatedCamera;
    }

    public void setActivatedCamera(CameraObject camera) {
        activatedCamera = camera;
    }

    public ArrayList<ModelObject> getModels() {
        return models;
    }

    public void addModel(ModelObject model) {
        models.add(model);
    }

    public void deleteModel(ModelObject model) {
        models.remove(model);
    }

    public void deleteModel(int index) {
        models.remove(index);
    }
}
