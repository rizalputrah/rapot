package com.example.owl.rapot.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by OWL on 10/05/2016.
 */
public class Result {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("mapel")
    @Expose
    private String mapel;
    @SerializedName("nilai")
    @Expose
    private String nilai;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    private Rapot[] rapot;

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The mapel
     */
    public String getMapel() {
        return mapel;
    }

    /**
     *
     * @param mapel
     * The mapel
     */
    public void setMapel(String mapel) {
        this.mapel = mapel;
    }

    /**
     *
     * @return
     * The nilai
     */
    public String getNilai() {
        return nilai;
    }

    /**
     *
     * @param nilai
     * The nilai
     */
    public void setNilai(String nilai) {
        this.nilai = nilai;
    }

    /**
     *
     * @return
     * The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     * The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }


}
