package com.example.owl.rapot.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OWL on 04/05/2016.
 */
public class Rapot {

    @SerializedName("result")
    @Expose
    private Result[] result;
    private Rapot[] rapot;

    /**
     *
     * @return
     * The result
     */
    public Result[] getResult() {
        return result;
    }

    /**
     *
     * @param result
     * The result
     */
    public void setResult(Result[] result) {
        this.result = result;
    }

    public Rapot[] getRapot(){
        return rapot;
    }

}
