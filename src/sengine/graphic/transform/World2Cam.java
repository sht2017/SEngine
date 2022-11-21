package sengine.graphic.transform;

import sengine.basic.World;
import sengine.graphic.CameraObject;
import sengine.math.Matrix;
import sengine.math.Vector;

public class World2Cam {
    public World2Cam(World world) {
        CameraObject cam = world.getActivatedCamera();
        Vector camPos = (cam.getPosition()).toHomogeneous();
        Matrix camPosTranslation = camPos.identity();
        camPosTranslation.setArray(new double[][]{
                {1, 0, 0, -camPos.getEntry(0, 0)},
                {0, 1, 0, -camPos.getEntry(1, 1)},
                {0, 0, 1, -camPos.getEntry(2, 2)},
                {0, 0, 0, 1}
        });

    }
}
