package sengine.math;

import sengine.math.exceptions.VectorSizeException;

public class Vector extends Matrix {
    final private Type type;
    final private boolean homogeneous;
    private boolean vector;

    public Vector(double[][] matrix, boolean homogeneous) {
        super(matrix);
        if (matrix.length > 1 && matrix[0].length > 1) {
            throw new VectorSizeException();
        }
        this.homogeneous = homogeneous;
        if (matrix.length == 1 && matrix[0].length > 1) {
            type = Type.rowVector;
        } else if (matrix.length > 1 && matrix[0].length == 1) {
            type = Type.columnVector;
        } else {
            type = null;
        }
        if (homogeneous) {
            vector = getW() == 0;
        } else {
            vector = false;
        }
    }

    public Vector(Matrix matrix, boolean homogeneous) {
        this(matrix.getArray(), homogeneous);
    }

    public Vector(int size, Type type, boolean homogeneous) {
        super(type == Type.rowVector ? 1 : size, type == Type.columnVector ? 1 : size);
        this.type = type;
        this.homogeneous = homogeneous;
        if (homogeneous) {
            vector = getW() == 0;
        } else {
            vector = false;
        }
    }

    public Vector(int size, Type type, boolean homogeneous, double... array) {
        this(size, type, homogeneous);
        setVectorArray(array);
    }

    public Vector(int size, Type type, boolean homogeneous, boolean isVector) {
        this(size, type, homogeneous);
        vector = isVector;
    }

    public Vector(int size, Type type, boolean homogeneous, boolean isVector, double... array) {
        this(size, type, homogeneous);
        setVectorArray(array);
        vector = isVector;
    }

    public static void main(String[] args) {
        double[][] vecR = {
                {1, 6, 8}
        };
        Vector vec = new Vector(vecR, false);
        //vec.display();
        //vec.toHomogeneous().display();
        //System.out.println(vec.toHomogeneous().getW());
        Vector x = vec.toHomogeneous();
        x.display();
        x.toCartesian().display();
        x.setW(10);
        System.out.println(x.transpose().isHomogeneous());
    }

    public Type getType() {
        return type;
    }

    public boolean isVector() {
        return vector;
    }

    public boolean isHomogeneous() {
        return homogeneous;
    }

    public double getW() {
        if (homogeneous) {
            return getEntry(getRowLength() - 1, getColLength() - 1);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public void setW(double value) {
        if (homogeneous) {
            setEntry(getRowLength() - 1, getColLength() - 1, value);
            vector = getW() == 0;
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public double[] getVectorArray() {
        double[] vecArray;
        if (type == Type.rowVector) {
            vecArray = new double[getColLength()];
            for (int n = 0; n < getColLength(); n++)
                vecArray[n] = getEntry(0, n);
            return vecArray;
        } else if (type == Type.columnVector) {
            vecArray = new double[getColLength()];
            for (int m = 0; m < getRowLength(); m++)
                vecArray[m] = getEntry(m, 0);
            return vecArray;
        } else {
            return new double[]{getEntry(0, 0)};
        }
    }

    public void setVectorArray(double... array) {
        if (type == Type.rowVector) {
            if (array.length != getColLength())
                throw new VectorSizeException();
            for (int n = 0; n < getColLength(); n++)
                setEntry(0, n, array[n]);
        } else if (type == Type.columnVector) {
            if (array.length != getRowLength())
                throw new VectorSizeException();
            for (int m = 0; m < getRowLength(); m++)
                setEntry(m, 0, array[m]);
        } else {
            if (array.length != 1)
                throw new VectorSizeException();
            setEntry(0, 0, array[0]);
        }
    }

    public void toVector() {
        if (homogeneous)
            setW(0);
        else
            vector = true;
    }

    public void toVertex() {
        if (homogeneous)
            setW(1);
        else
            vector = false;
    }

    public Vector toCartesian() {
        if (homogeneous) {
            Vector resultVec = new Vector(type == Type.rowVector ? getColLength() - 1 : getRowLength() - 1, type, false);
            for (int m = 0; m < resultVec.getRowLength(); m++) {
                for (int n = 0; n < resultVec.getColLength(); n++) {
                    if (type == Type.rowVector) {
                        if (vector)
                            resultVec.setEntry(m, n, getEntry(m, n));
                        else
                            resultVec.setEntry(m, n, getEntry(m, n) / getEntry(0, resultVec.getColLength()));
                    } else {
                        if (vector)
                            resultVec.setEntry(m, n, getEntry(m, n));
                        else
                            resultVec.setEntry(m, n, getEntry(m, n) / getEntry(resultVec.getRowLength(), 0));
                    }
                }
            }
            return resultVec;
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public Vector toHomogeneous() {
        if (!homogeneous) {
            Vector resultVec = new Vector(type == Type.rowVector ? getColLength() + 1 : getRowLength() + 1, type, true);
            for (int m = 0; m < resultVec.getRowLength(); m++) {
                for (int n = 0; n < resultVec.getColLength(); n++) {
                    if (type == Type.rowVector) {
                        if (vector)
                            resultVec.setEntry(m, n, (n != resultVec.getColLength() - 1) ? getEntry(m, n) : 0);
                        else
                            resultVec.setEntry(m, n, (n != resultVec.getColLength() - 1) ? getEntry(m, n) : 1);

                    } else {
                        if (vector)
                            resultVec.setEntry(m, n, (m != resultVec.getRowLength() - 1) ? getEntry(m, n) : 0);
                        else
                            resultVec.setEntry(m, n, (m != resultVec.getRowLength() - 1) ? getEntry(m, n) : 1);
                    }
                }
            }
            return resultVec;
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public Vector transpose() {
        return new Vector(new Matrix(this.getArray()).transpose(), homogeneous);
    }

    public enum Type {
        rowVector, columnVector
    }

}
