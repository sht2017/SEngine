package sengine.parser.POJO.object;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Obj {

    @SerializedName("mesh")
    @Expose
    private ArrayList<ArrayList<ArrayList<Double>>> mesh = null;
    @SerializedName("color")
    @Expose
    private String color;


    public ArrayList<ArrayList<ArrayList<Double>>> getMesh() {
        return mesh;
    }

    public void setMesh(ArrayList<ArrayList<ArrayList<Double>>> mesh) {
        this.mesh = mesh;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
