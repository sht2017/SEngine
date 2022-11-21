package sengine.parser.POJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import sengine.parser.POJO.object.Obj;


public class Model {

    @SerializedName("obj")
    @Expose
    private Obj obj = null;

    public Obj getObj() {
        return obj;
    }

    public void setObj(Obj obj) {
        this.obj = obj;
    }

}