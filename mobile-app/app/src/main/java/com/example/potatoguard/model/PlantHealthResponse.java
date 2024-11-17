package com.example.potatoguard.model;

import com.google.gson.annotations.SerializedName;

public class PlantHealthResponse {
    @SerializedName("class")
    private String diseaseLabel;
    private double confidence;

    public String getDiseaseLabel() {
        return diseaseLabel;
    }

    public void setDiseaseLabel(String diseaseLabel) {
        this.diseaseLabel = diseaseLabel;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }
}
