package sengine.graphic.transform.trans3D;

import sengine.math.Matrix;
import sengine.math.Vector;

public class Translation {
    public static Matrix Translation(Matrix matrix, double transX, double transY, double transZ) {
        final Matrix factor = new Matrix(new double[][]{
                {1, 0, 0, transX},
                {0, 1, 0, transY},
                {0, 0, 1, transZ},
                {0, 0, 0, 1}
        });
        return matrix.times(factor);
    }

    public static Vector Translation(Vector vector, double transX, double transY, double transZ) {
        return new Vector(Translation((Matrix) vector, transX, transY, transZ), true);
    }
}
