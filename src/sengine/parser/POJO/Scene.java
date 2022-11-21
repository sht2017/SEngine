package sengine.parser.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Scene {

    @SerializedName("cam")
    @Expose
    private Cam cam = null;


    @SerializedName("objs")
    @Expose
    private ArrayList<Model> objs = null;

    public Cam getCam() {
        return cam;
    }

    public void setCam(Cam cam) {
        this.cam = cam;
    }

    public ArrayList<Model> getObjs() {
        return objs;
    }

    public void setObjs(ArrayList<Model> objs) {
        this.objs = objs;
    }

    public static class Cam {
        @SerializedName("position")
        @Expose
        private ArrayList<Double> position = null;
        @SerializedName("gaze")
        @Expose
        private ArrayList<Double> gaze = null;
        @SerializedName("top")
        @Expose
        private ArrayList<Double> top = null;

        public ArrayList<Double> getPosition() {
            return position;
        }

        public void setPosition(ArrayList<Double> position) {
            this.position = position;
        }

        public ArrayList<Double> getGaze() {
            return gaze;
        }

        public void setGaze(ArrayList<Double> gaze) {
            this.gaze = gaze;
        }

        public ArrayList<Double> getTop() {
            return top;
        }

        public void setTop(ArrayList<Double> top) {
            this.top = top;
        }

    }
}