package sengine.graphic.transform.trans3D;

import sengine.math.Matrix;
import sengine.math.Vector;

public class Scale {
    public static Matrix Scale(Matrix matrix, double scaleX, double scaleY, double scaleZ) {
        final Matrix factor = new Matrix(new double[][]{
                {scaleX, 0, 0, 0},
                {0, scaleY, 0, 0},
                {0, 0, scaleZ, 0},
                {0, 0, 0, 1}
        });
        return matrix.times(factor);
    }

    public static Matrix Scale(Matrix matrix, double uniformScale) {
        final Matrix factor = new Matrix(new double[][]{
                {uniformScale, 0, 0, 0},
                {0, uniformScale, 0, 0},
                {0, 0, uniformScale, 0},
                {0, 0, 0, 1}
        });
        return matrix.times(factor);
    }

    public static Vector Scale(Vector vector, double scaleX, double scaleY, double scaleZ) {
        return new Vector(Scale((Matrix) vector, scaleX, scaleY, scaleZ), true);
    }

    public static Vector Scale(Vector vector, double uniformScale) {
        return new Vector(Scale((Matrix) vector, uniformScale), true);
    }
}
