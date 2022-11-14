package sengine.basic;

public class Image {
    private int[][][] data;

    public Image(int[][][] data) {
        this.data = data;
    }

    public int[][][] getData() {
        return data;
    }

    public void setData(int[][][] data) {
        this.data = data;
    }

    public void setData(int x, int y, int[] color) {
        data[x][y] = color;
    }

    public int getWidth() {
        return data.length;
    }

    public int getHeight() {
        return data[0].length;
    }
}
