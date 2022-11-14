package sengine.io;

public class CSVParser extends FileHandler {

    public CSVParser(String path) {
        super(path);
    }

    public static void main(String[] args) {
        CSVParser file = new CSVParser("test.txt");
        System.out.println(file.read(""));
    }

    public String[] parseString() {
        return read("").split(",");
    }

    public int[] parseInt() {
        String[] origin = read("").split(",");
        int[] result = new int[origin.length];
        for (int i = 0; i < origin.length; i++) {
            result[i] = Integer.parseInt(origin[i]);
        }
        return result;
    }

    public float[] parseFloat() {
        String[] origin = read("").split(",");
        float[] result = new float[origin.length];
        for (int i = 0; i < origin.length; i++) {
            result[i] = Float.parseFloat(origin[i]);
        }
        return result;
    }

    public double[] parseDouble() {
        String[] origin = read("").split(",");
        double[] result = new double[origin.length];
        for (int i = 0; i < origin.length; i++) {
            result[i] = Double.parseDouble(origin[i]);
        }
        return result;
    }

}
