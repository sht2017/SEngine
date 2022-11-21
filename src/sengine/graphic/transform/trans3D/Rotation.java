package sengine.graphic.transform.trans3D;

import sengine.math.Matrix;
import sengine.math.Vector;

public class Rotation {
    public Matrix RotationX(Matrix matrix, double angle) {
        final Matrix factor = new Matrix(new double[][]{
                {1, 0, 0, 0},
                {0, Math.cos(angle), -Math.sin(angle), 0},
                {0, Math.sin(angle), Math.cos(angle), 0},
                {0, 0, 0, 1}
        });
        return matrix.times(factor);
    }

    public Matrix RotationY(Matrix matrix, double angle) {
        final Matrix factor = new Matrix(new double[][]{
                {Math.cos(angle), 0, Math.sin(angle), 0},
                {0, 1, 0, 0},
                {-Math.sin(angle), 0, Math.cos(angle), 0},
                {0, 0, 0, 1}
        });
        return matrix.times(factor);
    }

    public Matrix RotationZ(Matrix matrix, double angle) {
        final Matrix factor = new Matrix(new double[][]{
                {Math.cos(angle), -Math.sin(angle), 0, 0},
                {Math.sin(angle), Math.cos(angle), 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        });
        return matrix.times(factor);
    }

    public Vector RotationX(Vector vector, double angle) {
        return new Vector(RotationX((Matrix) vector, angle), true);
    }

    public Vector RotationY(Vector vector, double angle) {
        return new Vector(RotationY((Matrix) vector, angle), true);
    }

    public Vector RotationZ(Vector vector, double angle) {
        return new Vector(RotationZ((Matrix) vector, angle), true);
    }
}
