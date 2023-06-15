package com.mirea.kt.android.kyrsovaya_yudakova;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("description")
    @Expose
    private String description;


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
