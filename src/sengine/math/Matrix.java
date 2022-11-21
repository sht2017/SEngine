package sengine.math;

import sengine.math.exceptions.MatrixSizeException;

public class Matrix {
    private final int rowLength, colLength;
    private double[][] mArray;

    public Matrix(int rowLength, int colLength) {
        this.rowLength = rowLength;
        this.colLength = colLength;
        this.mArray = new double[rowLength][colLength];
    }

    public Matrix(double[][] matrix) {
        this.rowLength = matrix.length;
        this.colLength = matrix[0].length;
        this.mArray = matrix;
    }

    public static void main(String[] arg) {
        double[][] m3 = {
                {1, 3, 5, 9},
                {1, 3, 1, 7},
                {4, 3, 9, 7},
                {5, 2, 0, 9}
        };
//        double[][] m3 ={
//                {3,0,2}
//        };
        Matrix mx3 = new Matrix(m3);
        mx3.display();
        mx3.transpose().display();
        //mx3.inverse().display();
        //mx0.times(-1).display();
    }

    public static Matrix identityOf(Matrix matrix) {
        if (matrix.colLength != matrix.rowLength) {
            throw new MatrixSizeException("Matrix has identity has to be square (same number of rows and columns).");
        }
        double[][] identity = new double[matrix.rowLength][matrix.colLength];
        for (int m = 0; m < matrix.rowLength; m++) {
            for (int n = 0; n < matrix.colLength; n++) {
                if (m == n) {
                    identity[m][n] = 1;
                }
            }
        }
        return new Matrix(identity);
    }

    public static Matrix identityOf(int size) {
        double[][] identity = new double[size][size];
        for (int m = 0; m < size; m++) {
            for (int n = 0; n < size; n++) {
                if (m == n) {
                    identity[m][n] = 1;
                }
            }
        }
        return new Matrix(identity);
    }

    public static Matrix times(Matrix matrix0, Matrix matrix1) {
        if (matrix0.colLength != matrix1.rowLength) {
            throw new MatrixSizeException();
        }
        double[][] product = new double[matrix0.rowLength][matrix1.colLength];
        for (int m0 = 0; m0 < matrix0.rowLength; m0++) {
            for (int n = 0; n < matrix0.colLength; n++) {
                for (int m1 = 0; m1 < matrix1.colLength; m1++) {
                    product[m0][m1] += matrix0.mArray[m0][n] * matrix1.mArray[n][m1];
                }
            }
        }
        return new Matrix(product);
    }

    public static Matrix times(Matrix matrix, double value) {
        double[][] product = new double[matrix.rowLength][matrix.colLength];
        for (int m = 0; m < matrix.rowLength; m++) {
            for (int n = 0; n < matrix.colLength; n++) {
                product[m][n] = matrix.mArray[m][n] * value;
            }
        }
        return new Matrix(product);
    }

    public static Matrix times(double value, Matrix matrix) {
        double[][] product = new double[matrix.rowLength][matrix.colLength];
        for (int m = 0; m < matrix.rowLength; m++) {
            for (int n = 0; n < matrix.colLength; n++) {
                product[m][n] = matrix.mArray[m][n] * value;
            }
        }
        return new Matrix(product);
    }

    public static Matrix add(Matrix matrix0, Matrix matrix1) {
        if (matrix0.rowLength != matrix1.rowLength || matrix0.colLength != matrix1.colLength) {
            throw new MatrixSizeException();
        }
        double[][] sum = new double[matrix0.rowLength][matrix0.colLength];
        for (int m = 0; m < matrix0.rowLength; m++) {
            for (int n = 0; n < matrix0.colLength; n++) {
                sum[m][n] = matrix0.mArray[m][n] + matrix1.mArray[m][n];
            }
        }
        return new Matrix(sum);
    }

    public static Matrix minus(Matrix matrix0, Matrix matrix1) {
        if (matrix0.rowLength != matrix1.rowLength || matrix0.colLength != matrix1.colLength) {
            throw new MatrixSizeException();
        }
        double[][] difference = new double[matrix0.rowLength][matrix0.colLength];
        for (int m = 0; m < matrix0.rowLength; m++) {
            for (int n = 0; n < matrix0.colLength; n++) {
                difference[m][n] = matrix0.mArray[m][n] - matrix1.mArray[m][n];
            }
        }
        return new Matrix(difference);
    }

    //mArray getter
    public double[][] getArray() {
        return mArray;
    }

    //mArray setter
    public void setArray(double[][] array) {
        mArray = array;
    }

    //entry getter
    public double getEntry(int m, int n) {
        return mArray[m][n];
    }

    //entry setter
    public void setEntry(int m, int n, double value) {
        mArray[m][n] = value;
    }

    //column length getter
    public int getColLength() {
        return colLength;
    }

    //row length getter
    public int getRowLength() {
        return rowLength;
    }

    public Matrix transpose() {
        double[][] mTransposed = new double[colLength][rowLength];
        for (int m = 0; m < rowLength; m++) {
            for (int n = 0; n < colLength; n++) {
                mTransposed[n][m] = mArray[m][n];
            }
        }
        return new Matrix(mTransposed);
    }

    public Matrix identity() {
        if (colLength != rowLength) {
            throw new MatrixSizeException("Matrix has identity have to be square (same number of rows and columns).");
        }
        double[][] identity = new double[rowLength][colLength];
        for (int m = 0; m < rowLength; m++) {
            for (int n = 0; n < colLength; n++) {
                if (m == n) {
                    identity[m][n] = 1;
                }
            }
        }
        return new Matrix(identity);
    }

    /*
     * DETERMINANT AND INVERSE NOT FINISHED
     * Tried to find a way solve determinant without recursion
     * Filed, uses recursion ;(
     */
    public double determinant() {
        if (colLength != rowLength) {
            throw new MatrixSizeException("Matrix has determinant have to be square (same number of rows and columns).");
        }
        switch (colLength) {
            case 1:
                return mArray[0][0];
            case 2:
                return mArray[0][0] * mArray[1][1] - mArray[0][1] * mArray[1][0];
            case 3:
                return mArray[0][0] * (
                        mArray[1][1] * mArray[2][2] -
                                mArray[1][2] * mArray[2][1]
                ) -
                        mArray[0][1] * (
                                mArray[1][0] * mArray[2][2] -
                                        mArray[1][2] * mArray[2][0]
                        ) +
                        mArray[0][2] * (
                                mArray[1][0] * mArray[2][1] -
                                        mArray[1][1] * mArray[2][0]
                        );
            default:
                return determinant(this);
        }
    }

    private double determinant(Matrix matrix) {
        if (matrix.rowLength < 3) {
            return matrix.mArray[0][0] * matrix.mArray[1][1] - matrix.mArray[0][1] * matrix.mArray[1][0];
        } else {
            int result = 0;
            for (int i = 0; i < matrix.colLength; i++) {
                double[][] subMatArray = new double[matrix.rowLength - 1][matrix.colLength - 1];
                for (int m = 0; m < subMatArray.length; m++) {
                    //noinspection ManualArrayCopy
                    for (int n = 0; n < subMatArray[0].length; n++) {
                        subMatArray[m][n] = matrix.mArray[m + 1][n + (n >= i ? 1 : 0)];
                    }
                }
                double subMatDet = matrix.mArray[0][i] * determinant(new Matrix(subMatArray));
                result += (i % 2 == 0) ? subMatDet : -subMatDet;
            }
            return result;
        }
    }

    public Matrix inverse() {
        if (colLength != rowLength) {
            throw new MatrixSizeException("Matrix has inverse have to be square (same number of rows and columns).");
        }
        double determinant = determinant();
        if (determinant == 0) {
            throw new ArithmeticException("Determinant cannot be zero.");
        }
        switch (colLength) {
            case 1:
                return this;
            case 2:
                final double[][] inverse = {
                        {mArray[1][1], -mArray[0][1]},
                        {-mArray[1][0], mArray[0][0]}
                };
                return (new Matrix(inverse)).times(1.0 / determinant);
            default:
                return (minor(this)).transpose().times(1.0 / determinant);
        }
    }

    private Matrix minor(Matrix matrix) {
        double[][] minorMatArray = new double[matrix.rowLength][matrix.colLength];
        for (int m = 0; m < minorMatArray.length; m++) {
            for (int n = 0; n < minorMatArray[0].length; n++) {
                double[][] subMatArray = new double[matrix.rowLength - 1][matrix.colLength - 1];
                for (int m1 = 0; m1 < subMatArray.length; m1++) {
                    //noinspection ManualArrayCopy
                    for (int n1 = 0; n1 < subMatArray[0].length; n1++) {
                        subMatArray[m1][n1] = matrix.mArray[m1 + (m1 >= m ? 1 : 0)][n1 + (n1 >= n ? 1 : 0)];
                    }
                }
                minorMatArray[m][n] = (n % 2 == 0 ? 1 : -1) * (m % 2 == 0 ? 1 : -1) * (new Matrix(subMatArray)).determinant();

            }
        }
        return new Matrix(minorMatArray);
    }

    public Matrix times(Matrix matrix) {
        if (colLength != matrix.rowLength) {
            throw new MatrixSizeException();
        }
        double[][] product = new double[rowLength][matrix.colLength];
        for (int m0 = 0; m0 < rowLength; m0++) {
            for (int n = 0; n < colLength; n++) {
                for (int m1 = 0; m1 < matrix.colLength; m1++) {
                    product[m0][m1] += mArray[m0][n] * matrix.mArray[n][m1];
                }
            }
        }
        return new Matrix(product);
    }

    public Matrix times(double value) {
        double[][] product = new double[rowLength][colLength];
        for (int m = 0; m < rowLength; m++) {
            for (int n = 0; n < colLength; n++) {
                product[m][n] = mArray[m][n] * value;
            }
        }
        return new Matrix(product);
    }

    public Matrix add(Matrix matrix) {
        if (rowLength != matrix.rowLength || colLength != matrix.colLength) {
            throw new MatrixSizeException();
        }
        double[][] sum = new double[rowLength][colLength];
        for (int m = 0; m < rowLength; m++) {
            for (int n = 0; n < colLength; n++) {
                sum[m][n] = mArray[m][n] + matrix.mArray[m][n];
            }
        }
        return new Matrix(sum);
    }

    public Matrix minus(Matrix matrix) {
        if (rowLength != matrix.rowLength || colLength != matrix.colLength) {
            throw new MatrixSizeException();
        }
        double[][] difference = new double[rowLength][colLength];
        for (int m = 0; m < rowLength; m++) {
            for (int n = 0; n < colLength; n++) {
                difference[m][n] = mArray[m][n] - matrix.mArray[m][n];
            }
        }
        return new Matrix(difference);
    }

    public void display() {
        for (int m = 0; m < rowLength; m++) {
            for (int n = 0; n < colLength; n++) {
                System.out.printf("%4.2f ", mArray[m][n]);
            }
            System.out.println();
        }
        System.out.println();
    }

}
