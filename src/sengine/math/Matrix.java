package sengine.math;

import sengine.math.exceptions.MatrixSizeException;

public class Matrix {
    public final int rows,cols;
    private double[][] mArray;

    public Matrix(int rows, int cols){
        this.rows=rows;
        this.cols=cols;
        this.mArray=new double[rows][cols];
    }
    public Matrix(double[][] matrix){
        this.rows=matrix.length;
        this.cols=matrix[0].length;
        this.mArray=matrix;
    }
    public Matrix transpose(){
        double[][] mTransposed = new double[rows][cols];
        for (int m=0;m<rows;m++){
            for (int n=0;n<cols;n++){
                mTransposed[m][n]=mArray[n][m];
            }
        }
        return new Matrix(mTransposed);
    }

    public Matrix identity(){
        if (cols!=rows){
            throw new MatrixSizeException("Matrix has identity have to be square (same number of rows and columns).");
        }
        double[][] identity = new double[rows][cols];
        for (int m=0; m<rows; m++){
            for (int n=0; n<cols; n++){
                if (m==n){
                    identity[m][n]=1;
                }
            }
        }
        return new Matrix(identity);
    }

    /*
    * DETERMINANT AND INVERSE NOT FINISHED
    * Tried to find a way solve determinant without recursion
    *
    */
    public double determinant(){
        if (cols!=rows){
            throw new MatrixSizeException("Matrix has determinant have to be square (same number of rows and columns).");
        }
        if (cols==1){
            return mArray[0][0];
        }
        return 0;
    }

    public Matrix times(Matrix matrix){
        if (cols!=matrix.rows){
            throw new MatrixSizeException();
        }
        double[][] product = new double[rows][matrix.cols];
        for (int m0=0; m0<rows; m0++){
            for (int n=0; n<cols; n++){
                for (int m1=0; m1<matrix.cols;m1++){
                    product[m0][m1]+=mArray[m0][n]*matrix.getArray()[n][m1];
                }
            }
        }
        return new Matrix(product);
    }

    public Matrix times(double value){
        double[][] product = new double[rows][cols];
        for (int m=0; m<rows; m++){
            for (int n=0; n<cols; n++){
                product[m][n]=mArray[m][n]*value;
            }
        }
        return new Matrix(product);
    }
    public Matrix add(Matrix matrix){
        if (rows!=matrix.rows||cols!= matrix.cols){
            throw new MatrixSizeException();
        }
        double[][] sum = new double[rows][cols];
        for (int m=0; m<rows; m++){
            for (int n=0; n<cols; n++){
                sum[m][n]=mArray[m][n]+matrix.getArray()[m][n];
            }
        }
        return new Matrix(sum);
    }
    public Matrix minus(Matrix matrix){
        if (rows!=matrix.rows||cols!= matrix.cols){
            throw new MatrixSizeException();
        }
        double[][] difference = new double[rows][cols];
        for (int m=0; m<rows; m++){
            for (int n=0; n<cols; n++){
                difference[m][n]=mArray[m][n]-matrix.getArray()[m][n];
            }
        }
        return new Matrix(difference);
    }
    public double getEntry(int m, int n){
        return mArray[m][n];
    }
    public double[][] getArray() {
        return mArray;
    }

    public void setEntry(int m, int n, double value){
        mArray[m][n]=value;
    }
    public void setArray(double[][] matrix) {
        mArray = matrix;
    }

    public void display(){
        for (int m=0;m<rows;m++){
            for (int n=0;n<cols;n++){
                System.out.printf("%4.2f ",mArray[m][n]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static Matrix identityOf(Matrix matrix){
        if (matrix.cols!=matrix.rows){
            throw new MatrixSizeException("Matrix has identity has to be square (same number of rows and columns).");
        }
        double[][] identity = new double[matrix.rows][matrix.cols];
        for (int m=0; m<matrix.rows; m++){
            for (int n=0; n<matrix.cols; n++){
                if (m==n){
                    identity[m][n]=1;
                }
            }
        }
        return new Matrix(identity);
    }
    public static Matrix identityOf(int size){
        double[][] identity = new double[size][size];
        for (int m=0; m<size; m++){
            for (int n=0; n<size; n++){
                if (m==n){
                    identity[m][n]=1;
                }
            }
        }
        return new Matrix(identity);
    }
    public static Matrix times(Matrix matrix0, Matrix matrix1){
        if (matrix0.cols!=matrix1.rows){
            throw new MatrixSizeException();
        }
        double[][] product = new double[matrix0.rows][matrix1.cols];
        for (int m0=0; m0< matrix0.rows; m0++){
            for (int n=0; n< matrix0.cols; n++){
                for (int m1=0; m1<matrix1.cols;m1++){
                    product[m0][m1]+=matrix0.getArray()[m0][n]*matrix1.getArray()[n][m1];
                }
            }
        }
        return new Matrix(product);
    }
    public static Matrix times(Matrix matrix, double value){
        double[][] product = new double[matrix.rows][matrix.cols];
        for (int m=0; m<matrix.rows; m++){
            for (int n=0; n<matrix.cols; n++){
                product[m][n]=matrix.getArray()[m][n]*value;
            }
        }
        return new Matrix(product);
    }
    public static Matrix times(double value, Matrix matrix){
        double[][] product = new double[matrix.rows][matrix.cols];
        for (int m=0; m<matrix.rows; m++){
            for (int n=0; n<matrix.cols; n++){
                product[m][n]=matrix.getArray()[m][n]*value;
            }
        }
        return new Matrix(product);
    }

    public static Matrix add(Matrix matrix0, Matrix matrix1){
        if (matrix0.rows!=matrix1.rows||matrix0.cols!= matrix1.cols){
            throw new MatrixSizeException();
        }
        double[][] sum = new double[matrix0.rows][matrix0.cols];
        for (int m=0; m<matrix0.rows; m++){
            for (int n=0; n<matrix0.cols; n++){
                sum[m][n]=matrix0.getArray()[m][n]+matrix1.getArray()[m][n];
            }
        }
        return new Matrix(sum);
    }
    public static Matrix minus(Matrix matrix0, Matrix matrix1){
        if (matrix0.rows!=matrix1.rows||matrix0.cols!= matrix1.cols){
            throw new MatrixSizeException();
        }
        double[][] difference = new double[matrix0.rows][matrix0.cols];
        for (int m=0; m<matrix0.rows; m++){
            for (int n=0; n<matrix0.cols; n++){
                difference[m][n]=matrix0.getArray()[m][n]-matrix1.getArray()[m][n];
            }
        }
        return new Matrix(difference);
    }
    public static void main(String[] arg){
        double[][] m0={
                {1,3},
                {5,2}
        };
        double[][] m1={
                {3,6},
                {2,7}
        };
        Matrix mx0 = new Matrix(m0);
        Matrix mx1 = new Matrix(m1);
        mx0.display();
        mx1.display();
        Matrix.times(mx0,mx1).display();
        Matrix.times(mx1,mx0).display();
        mx0.identity().display();
        Matrix.identityOf(mx1).display();
        //mx0.times(-1).display();
    }
}
